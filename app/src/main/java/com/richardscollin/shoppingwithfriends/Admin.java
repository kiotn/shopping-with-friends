package com.richardscollin.shoppingwithfriends;

import android.location.Location;
import java.util.Collection;
import java.util.HashSet;

/**
 * Created by john on 3/22/15.
 * Class for an admin user.
 */
public class Admin implements User {
    final private String passwordHash;

    public Admin (String pPasswordHash) {
        passwordHash = pPasswordHash;
    }

    public String getName() {
        return "root";
    }

    public String getEmail() {
        return "root@root";
    }

    @Override
    public String getPasswordHash() {
        return passwordHash;
    }

    public void giveRating(int num) {
        //swallow the rating.
    }

    public int getRating() {
        return Integer.MAX_VALUE;
    }

    public void addFriend(User p) {

    }

    @Override
    public void removeFriend(User p) {

    }

    public String toString() {
        return "root";
    }

    public void registerInterest(String name, double cost) {
        //swallow
    }

    @Override
    public void registerSale(String item, String location, double cost, Location gpsLocation) {
        //swallow
    }

    public String getInterestsToString() {
        return "";
    }

    @Override
    public String getSalesToString() {
        return "";
    }

    public Collection<Person.Interest> getInterests() {
        return new HashSet<>();
    }

    public User[] getFriends() {

        return Model.getUsers().toArray(new User[Model.getUsers().size()]);
    }

    public Collection<Person.Sale> getSales() {
        return new HashSet<>();
    }
}
