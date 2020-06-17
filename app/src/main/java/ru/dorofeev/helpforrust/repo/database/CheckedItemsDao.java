package ru.dorofeev.helpforrust.repo.database;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CheckedItemsDao {

    @Query("INSERT INTO checked_items(position, level) VALUES(:position, :level)")
    long insert(int position, String level);

    @Query("SELECT * FROM checked_items WHERE level = :level")
    List<CheckedItemEntity> getAllCheckedItems(String level);

    @Query("DELETE FROM checked_items WHERE id = :id")
    int deleteCheckedItem(long id);

    @Query("DELETE FROM checked_items WHERE level = :level")
    int deleteAllCheckedItems(String level);
}
