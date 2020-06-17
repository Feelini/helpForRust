package ru.dorofeev.helpforrust.repo;

import android.app.Application;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
import io.reactivex.rxjava3.schedulers.Schedulers;
import ru.dorofeev.helpforrust.repo.database.CheckedItemEntity;
import ru.dorofeev.helpforrust.repo.database.CheckedItemsDao;
import ru.dorofeev.helpforrust.repo.database.CheckedItemsDatabase;

public class RepositoryImpl implements Repository {

    private CheckedItemsDao checkedItemsDao;

    public RepositoryImpl(Application application) {
        checkedItemsDao = CheckedItemsDatabase.getInstance(application).checkedItemsDao();
    }

    @Override
    public Single<Long> insertCheckedItem(int position, String level) {
        return Single.create((SingleOnSubscribe<Long>) emitter -> {
            long id = checkedItemsDao.insert(position, level);
            emitter.onSuccess(id);
        }).subscribeOn(Schedulers.io());
    }

    @Override
    public Single<List<CheckedItemEntity>> getAllCheckedItems(String level) {
        return Single.create((SingleOnSubscribe<List<CheckedItemEntity>>) emitter -> {
            List<CheckedItemEntity> checkedItemEntities = checkedItemsDao.getAllCheckedItems(level);
            emitter.onSuccess(checkedItemEntities);
        }).subscribeOn(Schedulers.io());
    }

    @Override
    public Single<Integer> deleteCheckedItem(long id) {
        return Single.create((SingleOnSubscribe<Integer>) emitter -> {
            int deleteRowsNum = checkedItemsDao.deleteCheckedItem(id);
            emitter.onSuccess(deleteRowsNum);
        }).subscribeOn(Schedulers.io());
    }

    @Override
    public Single<Integer> deleteAllCheckedItems(String level) {
        return Single.create((SingleOnSubscribe<Integer>) emitter -> {
            int deleteRowsNum = checkedItemsDao.deleteAllCheckedItems(level);
            emitter.onSuccess(deleteRowsNum);
        }).subscribeOn(Schedulers.io());
    }
}
