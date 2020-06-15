package ru.dorofeev.helpforrust.models.allDb;

import java.util.List;

import ru.dorofeev.helpforrust.models.Item;
import ru.dorofeev.helpforrust.models.ItemCompound;
import ru.dorofeev.helpforrust.models.ItemWeapon;
import ru.dorofeev.helpforrust.models.Subject;
import ru.dorofeev.helpforrust.models.Weapons;
import ru.dorofeev.helpforrust.models.WeaponsSubject;

public class RaidCalculatorList {
    private List<Item> Items;
    private List<ItemCompound> ItemsCompound;
    private List<ItemWeapon> ItemsWeapons;
    private List<Subject> Subject;
    private List<Weapons> Weapons;
    private List<WeaponsSubject> WeaponsSubject;

    public RaidCalculatorList() {
    }

    public RaidCalculatorList(List<Item> items, List<ItemCompound> itemsCompound, List<ItemWeapon> itemsWeapons, List<ru.dorofeev.helpforrust.models.Subject> subject, List<ru.dorofeev.helpforrust.models.Weapons> weapons, List<ru.dorofeev.helpforrust.models.WeaponsSubject> weaponsSubject) {
        Items = items;
        ItemsCompound = itemsCompound;
        ItemsWeapons = itemsWeapons;
        Subject = subject;
        Weapons = weapons;
        WeaponsSubject = weaponsSubject;
    }

    public List<Item> getItems() {
        return Items;
    }

    public void setItems(List<Item> items) {
        Items = items;
    }

    public List<ItemCompound> getItemsCompound() {
        return ItemsCompound;
    }

    public void setItemsCompound(List<ItemCompound> itemsCompound) {
        ItemsCompound = itemsCompound;
    }

    public List<ItemWeapon> getItemsWeapons() {
        return ItemsWeapons;
    }

    public void setItemsWeapons(List<ItemWeapon> itemsWeapons) {
        ItemsWeapons = itemsWeapons;
    }

    public List<ru.dorofeev.helpforrust.models.Subject> getSubject() {
        return Subject;
    }

    public void setSubject(List<ru.dorofeev.helpforrust.models.Subject> subject) {
        Subject = subject;
    }

    public List<ru.dorofeev.helpforrust.models.Weapons> getWeapons() {
        return Weapons;
    }

    public void setWeapons(List<ru.dorofeev.helpforrust.models.Weapons> weapons) {
        Weapons = weapons;
    }

    public List<ru.dorofeev.helpforrust.models.WeaponsSubject> getWeaponsSubject() {
        return WeaponsSubject;
    }

    public void setWeaponsSubject(List<ru.dorofeev.helpforrust.models.WeaponsSubject> weaponsSubject) {
        WeaponsSubject = weaponsSubject;
    }
}
