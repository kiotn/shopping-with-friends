package com.richardscollin.shoppingwithfriends;

import android.location.Location;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by john on 3/23/15.
 * Class for guest user.
 */
public class Guest implements User {

    public Guest() {

    }

    @Override
    public String getName() {
        return "guest account";
    }

    @Override
    public String getEmail() {
        return "null@guest";
    }

    @Override
    public String getPasswordHash() {
        return "0";
    }

    @Override
    public void giveRating(int n) {

    }

    @Override
    public int getRating() {
        return 0;
    }

    @Override
    public void addFriend(User p) {

    }

    @Override
    public void removeFriend(User p) {

    }

    @Override
    public void registerInterest(String name, double cost) {

    }

    @Override
    public void registerSale(String item, String location, double cost, Location gpsLocation) {

    }

    @Override
    public String getInterestsToString() {
        return "";
    }

    @Override
    public String getSalesToString() {
        return "";
    }

    @Override
    public User[] getFriends() {
        return new User[0];
    }

    @Override
    public Collection<Person.Sale> getSales() {
        return new HashSet<>();
    }

    @Override
    public Collection<Person.Interest> getInterests() {
        return null;
    }
}
