package com.richardscollin.shoppingwithfriends;

import android.graphics.AvoidXfermode;
import android.location.Location;
import android.widget.Toast;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by john on 3/22/15.
 */
public class Admin implements User {

    String email;
    String passwordHash;

    public Admin (String email, String passwordHash) {
        this.email = email;
        this.passwordHash = passwordHash;
    }

    public String getName() {
        return "root";
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getPasswordHash() {
        return null;
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
        return (User[]) Model.getUsers().toArray(new Person[Model.getUsers().size()]);
    }

    public Collection<Person.Sale> getSales() {
        return new HashSet<>();
    }
}
