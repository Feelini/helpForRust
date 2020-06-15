package ru.dorofeev.helpforrust.fragments.raidCalculator;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import ru.dorofeev.helpforrust.models.Item;
import ru.dorofeev.helpforrust.models.ItemCompound;
import ru.dorofeev.helpforrust.models.ItemWeapon;
import ru.dorofeev.helpforrust.models.Subject;
import ru.dorofeev.helpforrust.models.Weapons;
import ru.dorofeev.helpforrust.models.WeaponsSubject;
import ru.dorofeev.helpforrust.models.allDb.Db;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RaidCalculatorFragmentViewModel extends AndroidViewModel {

    private MutableLiveData<List<Subject>> subjectList = new MutableLiveData<>();
    private MutableLiveData<List<WeaponsSubject>> weaponsSubject = new MutableLiveData<>();
    private MutableLiveData<List<Weapons>> weapons = new MutableLiveData<>();
    private MutableLiveData<List<ItemWeapon>> itemWeapons = new MutableLiveData<>();
    private MutableLiveData<List<ItemCompound>> itemCompounds = new MutableLiveData<>();
    private MutableLiveData<List<Item>> items = new MutableLiveData<>();
    private MutableLiveData<Db> db = new MutableLiveData<>();
    private static FirebaseDatabase database;
    private DatabaseReference databaseReference;

    public RaidCalculatorFragmentViewModel(@NonNull Application application) {
        super(application);
        database = FirebaseDatabase.getInstance();
    }

    public void fetchSubjects(){
        databaseReference = database.getReference().child("RaidCalculator").child("Subject");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Subject> subjects = new ArrayList<>();
                if (dataSnapshot != null && dataSnapshot.exists()) {
                    for (DataSnapshot dataSnapshotChild : dataSnapshot.getChildren()) {
                        subjects.add(dataSnapshotChild.getValue(Subject.class));
                    }
                    subjectList.postValue(subjects);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public LiveData<List<Subject>> getSubjects(){
        return subjectList;
    }

    public void fetchWeaponsSubject(long subjectId){
        Query query = database.getReference().child("RaidCalculator").child("WeaponsSubject");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<WeaponsSubject> weaponsSubjects = new ArrayList<>();
                if (dataSnapshot != null && dataSnapshot.exists()) {
                    for (DataSnapshot dataSnapshotChild : dataSnapshot.getChildren()) {
                        WeaponsSubject weaponsSubject = dataSnapshotChild.getValue(WeaponsSubject.class);
                        if (weaponsSubject.getSubject_id() == subjectId){
                            weaponsSubjects.add(weaponsSubject);
                        }
                    }
                    weaponsSubject.postValue(weaponsSubjects);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public LiveData<List<WeaponsSubject>> getWeaponsSubject(){
        return weaponsSubject;
    }

    public void fetchWeapons(){
        databaseReference = database.getReference().child("RaidCalculator").child("Weapons");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Weapons> weapons1 = new ArrayList<>();
                if (dataSnapshot != null && dataSnapshot.exists()) {
                    for (DataSnapshot dataSnapshotChild : dataSnapshot.getChildren()) {
                        weapons1.add(dataSnapshotChild.getValue(Weapons.class));
                    }
                    weapons.postValue(weapons1);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public LiveData<List<Weapons>> getWeapons(){
        return weapons;
    }

    public void fetchItemWeapons(){
        databaseReference = database.getReference().child("RaidCalculator").child("ItemsWeapons");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<ItemWeapon> weapons1 = new ArrayList<>();
                if (dataSnapshot != null && dataSnapshot.exists()) {
                    for (DataSnapshot dataSnapshotChild : dataSnapshot.getChildren()) {
                        weapons1.add(dataSnapshotChild.getValue(ItemWeapon.class));
                    }
                    itemWeapons.postValue(weapons1);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public LiveData<List<ItemWeapon>> getItemWeapons(){
        return itemWeapons;
    }

    public void fetchItemCompounds(){
        databaseReference = database.getReference().child("RaidCalculator").child("ItemsCompound");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<ItemCompound> weapons1 = new ArrayList<>();
                if (dataSnapshot != null && dataSnapshot.exists()) {
                    for (DataSnapshot dataSnapshotChild : dataSnapshot.getChildren()) {
                        weapons1.add(dataSnapshotChild.getValue(ItemCompound.class));
                    }
                    itemCompounds.postValue(weapons1);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public LiveData<List<ItemCompound>> getItemCompounds(){
        return itemCompounds;
    }

    public void fetchItems(){
        databaseReference = database.getReference().child("RaidCalculator").child("Items");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Item> items1 = new ArrayList<>();
                if (dataSnapshot != null && dataSnapshot.exists()) {
                    for (DataSnapshot dataSnapshotChild : dataSnapshot.getChildren()) {
                        items1.add(dataSnapshotChild.getValue(Item.class));
                    }
                    items.postValue(items1);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public LiveData<List<Item>> getItems(){
        return items;
    }

    public void fetchDb(){
        databaseReference = database.getReference();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot != null && dataSnapshot.exists()) {
                    Db newDb = dataSnapshot.getValue(Db.class);
                    db.postValue(newDb);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public LiveData<Db> getDb(){
        return db;
    }
}
