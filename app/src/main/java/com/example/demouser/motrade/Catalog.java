package com.example.demouser.motrade;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by demouser on 1/16/18.
 */

public class Catalog {

    private ArrayList<Listing> masterList;
    private ArrayList<String> names;
    private HashMap<Listing.Category, ArrayList<Listing>> categoryMap;

    public Catalog() {
        masterList = new ArrayList<>();
        categoryMap = new HashMap<>();
    }

    /**
     * create a new listing and add to list
     * @param name
     * @param description
     * @param sellerName
     * @param category
     */
    public void addListing(String name, String description, String sellerName, Listing.Category category) {

        Listing newListing = new Listing(name, description, sellerName, category);
        //add to list
        masterList.add(newListing);
        names.add(name);
        //add to map
        if(categoryMap.containsKey(category)) {
            //add to list at key
            ArrayList<Listing> list = categoryMap.get(category);
            list.add(newListing);
        } else {
            //make new list with listing and add to map
            ArrayList<Listing> newList = new ArrayList<>();
            newList.add(newListing);
            categoryMap.put(category, newList);
        }
    }

    /**
     * delete listing
     * @param deleteListing
     */
    public void deleteListing(Listing deleteListing) {
        //remove from list
        masterList.remove(deleteListing);
        //remove from map
        ArrayList<Listing> list = categoryMap.get(deleteListing.getCategory());
        list.remove(deleteListing);
    }

    /**
     * getter for master list
     * @return masterList
     */
    public ArrayList<Listing> getMasterList() {
        return masterList;
    }

    /**
     * getter for master list
     * @return masterList
     */
    public ArrayList<String> getNamesList() {
        return names;
    }

    /**
     * getter for individual category list
     * @param category
     * @return list at category
     */
    public ArrayList<Listing> getCategory(Listing.Category category) {
        return categoryMap.get(category);
    }
}

