package ru.dorofeev.helpforrust.fragments.info;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import ru.dorofeev.helpforrust.models.Furnace;
import ru.dorofeev.helpforrust.models.InfoListItem;

public class InfoFragmentViewModel extends AndroidViewModel {
    private MutableLiveData<List<InfoListItem>> infoList = new MutableLiveData<>();
    private static FirebaseDatabase database;
    private DatabaseReference databaseReference;

    public InfoFragmentViewModel(@NonNull Application application) {
        super(application);
        database = FirebaseDatabase.getInstance();
    }

    public void fetchInfoList(){
        databaseReference = database.getReference().child("Info").child("List");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<InfoListItem> infoListItems = new ArrayList<>();
                if (dataSnapshot != null && dataSnapshot.exists()) {
                    for (DataSnapshot dataSnapshotChild : dataSnapshot.getChildren()) {
                        infoListItems.add(dataSnapshotChild.getValue(InfoListItem.class));
                    }
                    infoList.postValue(infoListItems);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public LiveData<List<InfoListItem>> getInfoList(){
        return infoList;
    }
}
