package ru.dorofeev.helpforrust.fragments.blueprint;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import ru.dorofeev.helpforrust.models.Blueprint;
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

    public BlueprintFragmentViewModel(@NonNull Application application) {
        super(application);
        database = FirebaseDatabase.getInstance();
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
}
