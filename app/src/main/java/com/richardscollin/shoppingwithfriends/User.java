package com.richardscollin.shoppingwithfriends;

import android.location.Location;

import java.util.Collection;

/**
 * Created by john on 3/23/15.
 * Class for User interface, most high level interface for registered user.
 */
public interface User {

    /**
     * Get the name of this User.
     * @return name of User.
     */
    String getName();

    /**
     * Get the email of this user.
     * @return the email of this user.
     */
    String getEmail();

    /**
     * get the hash of the password of this user.
     * @return the passwordhash, in a string.
     */
    String getPasswordHash();

    /**
     * Give a rating to this user. It is up to the implementation of User
     * to decide what to do with the rating.
     * @param n the rating to give.
     */
    void giveRating(int n);

    /**
     * Get the current rating of this User.
     * @return rating.
     */
    int getRating();

    /**
     * Add a friend to this User's friendlist. Up to the implementation to decide
     * what do to.
     * @param p User to add
     */
    void addFriend(User p);

    /**
     * Remove a friend from this Users' friendlist. Up to the implementation again.
     * @param p User to remove.
     */
    void removeFriend(User p);

    /**
     * Register an interest with this User.
     * @param name name of the item.
     * @param cost cots of the item.
     */
    void registerInterest(String name, double cost);

    /**
     * Register a sale with this User.
     * @param item name of the item.
     * @param location Where the item was found.
     * @param cost cost of the item.
     * @param gpsLocation the gpsLocation of the item.
     */
    void registerSale(String item, String location, double cost, Location gpsLocation);

    /**
     * Get the set of interests, in a string readable format. Ideal for printing to the device screen
     * for another User to view all of the interests.
     * @return the string of Interests.
     */
    String getInterestsToString();

    /**
     * Similar to getInterestsToString, but with Sales.
     * @return the string of Sales.
     */
    String getSalesToString();

    /**
     * Get a list of friends of this User. Implementation specific.
     * @return list of Users.
     */
    User[] getFriends();

    /**
     * Get the raw set of Sales.
     * @return the collection of Sales.
     */
    Collection<Person.Sale> getSales();

    /**
     * Similar to getSales, but for interests.
     * @return the collection of Interests.
     */
    Collection<Person.Interest> getInterests();

}
