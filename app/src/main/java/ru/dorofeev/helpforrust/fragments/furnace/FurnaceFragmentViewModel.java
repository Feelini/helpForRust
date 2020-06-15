package ru.dorofeev.helpforrust.fragments.furnace;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import ru.dorofeev.helpforrust.models.Furnace;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FurnaceFragmentViewModel extends AndroidViewModel {

    private MutableLiveData<List<Furnace>> furnaceList = new MutableLiveData<>();
    private static FirebaseDatabase database;
    private DatabaseReference databaseReference;

    public FurnaceFragmentViewModel(@NonNull Application application) {
        super(application);
        database = FirebaseDatabase.getInstance();
    }

    public void fetchFurnace(){
        databaseReference = database.getReference().child("FurnaceInfo");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Furnace> furnaces = new ArrayList<>();
                if (dataSnapshot != null && dataSnapshot.exists()) {
                    for (DataSnapshot dataSnapshotChild : dataSnapshot.getChildren()) {
                        furnaces.add(dataSnapshotChild.getValue(Furnace.class));
                    }
                    furnaceList.postValue(furnaces);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public LiveData<List<Furnace>> getFurnace(){
        return furnaceList;
    }
}
