package ru.dorofeev.helpforrust.repo.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {CheckedItemEntity.class}, version = 1, exportSchema = false)
public abstract class CheckedItemsDatabase extends RoomDatabase {
    private static volatile CheckedItemsDatabase instance;

    public static CheckedItemsDatabase getInstance(final Context context){
        if (instance == null){
            synchronized (CheckedItemsDatabase.class){
                if (instance == null){
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            CheckedItemsDatabase.class,
                            "db_help_for_rust.db")
                            .build();
                }
            }
        }

        return instance;
    }

    public abstract CheckedItemsDao checkedItemsDao();
}
