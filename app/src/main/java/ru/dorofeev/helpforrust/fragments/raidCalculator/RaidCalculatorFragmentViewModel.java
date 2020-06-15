package ru.dorofeev.helpforrust.fragments.raidCalculator;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import ru.dorofeev.helpforrust.models.allDb.RaidCalculatorList;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RaidCalculatorFragmentViewModel extends AndroidViewModel {

    private MutableLiveData<RaidCalculatorList> raidCalculatorList = new MutableLiveData<>();
    private static FirebaseDatabase database;
    private DatabaseReference databaseReference;

    public RaidCalculatorFragmentViewModel(@NonNull Application application) {
        super(application);
        database = FirebaseDatabase.getInstance();
    }

    public void fetchRaidCalculator(){
        databaseReference = database.getReference().child("RaidCalculator");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot != null && dataSnapshot.exists()) {
                    raidCalculatorList.postValue(dataSnapshot.getValue(RaidCalculatorList.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public LiveData<RaidCalculatorList> getRaidCalculator(){
        return raidCalculatorList;
    }
}
