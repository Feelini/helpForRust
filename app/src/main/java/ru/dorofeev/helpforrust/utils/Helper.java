package ru.dorofeev.helpforrust.utils;

import java.util.ArrayList;
import java.util.List;

import ru.dorofeev.helpforrust.models.Item;
import ru.dorofeev.helpforrust.models.ItemCompound;
import ru.dorofeev.helpforrust.models.ItemCompoundWithValue;
import ru.dorofeev.helpforrust.models.ItemWeapon;
import ru.dorofeev.helpforrust.models.ItemWithValue;
import ru.dorofeev.helpforrust.models.WeaponItemList;
import ru.dorofeev.helpforrust.models.Weapons;
import ru.dorofeev.helpforrust.models.WeaponsSubject;
import ru.dorofeev.helpforrust.models.WeaponsWithValue;

public class Helper {

    private static Helper instance;

    public static Helper getInstance(){
        if (instance == null){
            instance = new Helper();
        }
        return instance;
    }

    public List<WeaponItemList> getWeaponsItemsList(List<WeaponsSubject> weaponsSubjectList, List<Weapons> weaponsList,
                                                    List<Item> itemList, List<ItemWeapon> itemWeaponList,
                                                    List<ItemCompound> itemCompoundList){
        List<WeaponItemList> weaponItemList = new ArrayList<>();
        WeaponsWithValue weaponsWithValue;
        List<ItemWithValue> itemWithValues = new ArrayList<>();
        List<ItemWithValue> itemCompoundWithValues = new ArrayList<>();
        for (WeaponsSubject weaponsSubject: weaponsSubjectList){
            weaponsWithValue = new WeaponsWithValue(weaponsList.get((int) weaponsSubject.getWeapons_id() - 1), weaponsSubject.getValue());
            for (ItemWeapon itemWeapon: itemWeaponList){
                if (itemWeapon.getWeapons_id() == weaponsSubject.getWeapons_id()){
                    ItemWithValue itemWithValue = new ItemWithValue(itemList.get((int) itemWeapon.getItems_id() - 1), itemWeapon.getValue());
                    itemWithValues.add(itemWithValue);
                    for (ItemCompound itemCompound: itemCompoundList){
                        if (itemCompound.getItems_id() == itemWithValue.getItem().getId()){
                            ItemWithValue itemCompoundWithValue = new ItemWithValue(itemList.get((int) itemCompound.getItems_compound_id() - 1), itemCompound.getValue_compound());
                            itemCompoundWithValues.add(itemCompoundWithValue);
                        }
                    }
                }
            }
            weaponItemList.add(new WeaponItemList(weaponsWithValue, itemWithValues, itemCompoundWithValues));
        }

        return weaponItemList;
    }
}
