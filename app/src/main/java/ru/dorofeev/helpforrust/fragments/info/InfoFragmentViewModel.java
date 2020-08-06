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

import ru.dorofeev.helpforrust.models.InfoList;

public class InfoFragmentViewModel extends AndroidViewModel {
    private MutableLiveData<InfoList> infoList = new MutableLiveData<>();
    private static FirebaseDatabase database;
    private DatabaseReference databaseReference;

    public InfoFragmentViewModel(@NonNull Application application) {
        super(application);
        database = FirebaseDatabase.getInstance();
    }

    public void fetchInfoList(){
        databaseReference = database.getReference().child("Info");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot != null && dataSnapshot.exists()) {
                    infoList.postValue(dataSnapshot.getValue(InfoList.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public LiveData<InfoList> getInfoList(){
        return infoList;
    }
}
