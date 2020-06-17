package ru.dorofeev.helpforrust.repo;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import ru.dorofeev.helpforrust.repo.database.CheckedItemEntity;

public interface Repository {
    Single<Long> insertCheckedItem(int position, String level);
    Single<List<CheckedItemEntity>> getAllCheckedItems(String level);
    Single<Integer> deleteCheckedItem(long id);
    Single<Integer> deleteAllCheckedItems(String level);
}
