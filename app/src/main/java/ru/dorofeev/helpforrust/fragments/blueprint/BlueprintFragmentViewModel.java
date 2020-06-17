package ru.dorofeev.helpforrust.fragments.blueprint;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import ru.dorofeev.helpforrust.models.Blueprint;
import ru.dorofeev.helpforrust.repo.Repository;
import ru.dorofeev.helpforrust.repo.database.CheckedItemEntity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BlueprintFragmentViewModel extends AndroidViewModel {

    private MutableLiveData<List<Blueprint>> blueprintList = new MutableLiveData<>();
    private static FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private Repository repository;
    private MutableLiveData<List<CheckedItemEntity>> checkedItems = new MutableLiveData<>();
    private Disposable disposable;

    public BlueprintFragmentViewModel(@NonNull Application application, Repository repository) {
        super(application);
        database = FirebaseDatabase.getInstance();
        this.repository = repository;
    }

    public void fetchBlueprints(String level){
        databaseReference = database.getReference().child("Blueprints").child("Level").child(level);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Blueprint> blueprints = new ArrayList<>();
                if (dataSnapshot != null && dataSnapshot.exists()) {
                    for (DataSnapshot dataSnapshotChild : dataSnapshot.getChildren()) {
                        blueprints.add(dataSnapshotChild.getValue(Blueprint.class));
                    }
                    blueprintList.postValue(blueprints);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public LiveData<List<Blueprint>> getBlueprints(){
        return blueprintList;
    }

    public void fetchCheckedItems(String level) {
        disposable = repository.getAllCheckedItems(level)
                .observeOn(Schedulers.single())
                .subscribe(checkedItemEntities -> checkedItems.postValue(checkedItemEntities));
    }

    public LiveData<List<CheckedItemEntity>> getCheckedItems(){
        return checkedItems;
    }

    public void saveNewItem(int position, String level) {
        disposable = repository.insertCheckedItem(position, level)
                .observeOn(Schedulers.single())
                .subscribe(aVoid -> fetchCheckedItems(level));
    }

    public void deleteCheckedItem(long id, String level){
        disposable = repository.deleteCheckedItem(id)
                .observeOn(Schedulers.single())
                .subscribe(aVoid -> fetchCheckedItems(level));
    }

    public void deleteAllItems(String level){
        disposable = repository.deleteAllCheckedItems(level)
                .observeOn(Schedulers.single())
                .subscribe(aVoid -> fetchCheckedItems(level));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
