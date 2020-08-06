package ru.dorofeev.helpforrust.models;

import java.util.List;

public class InfoList {

    private List<InfoListItem> infoListItems;
    private List<ItemOfInfoListItem> itemOfInfoListItemAndBuffs;

    public InfoList() {
    }

    public InfoList(List<InfoListItem> infoListItems, List<ItemOfInfoListItem> itemOfInfoListItemAndBuffs) {
        this.infoListItems = infoListItems;
        this.itemOfInfoListItemAndBuffs = itemOfInfoListItemAndBuffs;
    }

    public List<InfoListItem> getInfoListItems() {
        return infoListItems;
    }

    public void setInfoListItems(List<InfoListItem> infoListItems) {
        this.infoListItems = infoListItems;
    }

    public List<ItemOfInfoListItem> getItemOfInfoListItemAndBuffs() {
        return itemOfInfoListItemAndBuffs;
    }

    public void setItemOfInfoListItemAndBuffs(List<ItemOfInfoListItem> itemOfInfoListItemAndBuffs) {
        this.itemOfInfoListItemAndBuffs = itemOfInfoListItemAndBuffs;
    }
}
