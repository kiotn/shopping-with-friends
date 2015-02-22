package com.richardscollin.shoppingwithfriends;

import java.util.HashSet;

/**
 * Created by John on 2/6/2015.
 */
public class Person {

    private String name;
    private String email;
    private String passwordHash;
    private FriendList friends;
    private int rating;
    private int ratingWeight;
    private HashSet<Sale> sales;


    /**
     * Person constructor.
     *
     * @param email email of user
     * @param passwordHash hash of password to log in
     */
    public Person(String name, String email, String passwordHash) {
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        friends = new FriendList();
        sales = new HashSet<>();
    }

    /**
     * Get the name
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * Method that gets user's email
     * @return user's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Method that gets user's password
     * @return user's password
     */
    public String getPasswordHash() {
        return passwordHash;
    }

    public void giveRating(int num) {
        int total = rating * ratingWeight + num;
        ratingWeight += 1;
        rating = total / ratingWeight;
    }

    public int getRating() {
        return rating;
    }

    /**
     * Method that returns string of email
     * @return string of email
     */
    public String toString() {
        return getName();
    }

    /**
     * Method that adds person to friends
     * @param person
     */
    public void addFriend(Person person) {
        //person.addFriend(this);
        friends.addFriend(person);
    }

    /**
     * Method that removes person from friends
     * @param person
     */
    public void removeFriend(Person person) {
        //person.removeFriend(this);
        friends.removeFriend(person);
    }

    public void registerSale (String name, double cost, String location) {
        sales.add(new Sale(name, cost, location));
    }

    public String getSales() {
        String result = "";
        for (Sale i : sales) {
            result += i.toString() + "\n";
        }
        return result;
    }

    /**
     * Method that gets the array of friends
     * @return array of friends
     */
    public Person[] getFriends() {
        return friends.toArray();
    }

    private class Sale {
        private double cost;
        private String name;
        private String location;

        public Sale(String name, double cost, String location) {
            this.name = name;
            this.cost = cost;
            this.location = location;
        }

        @Override
        public String toString() {
            String result = "";
            result += name;
            result += " at $";
            result += cost;
            result += "\n\t at ";
            result += location;
            return result;
        }

    }

    /**
     * Created by John on 2/6/2015.
     */
    private class FriendList {

        private HashSet<Person> friends = new HashSet<>();

        /**
         * Add a person to the friendlist.
         *
         *
         * @param person to be added
         * @return True if added. False if already added.
         */
        public boolean addFriend(Person person) {
            if (checkMembership(person)) {
                return false;
            }
            friends.add(person);
            return true;
        }


        /**
         * Remove a person from friendlist
         * @param person to be removed
         * @return True if removed. False if person does not exist.
         */
        public boolean removeFriend(Person person) {
            if (!checkMembership(person)) {
                return false;
            }
            friends.remove(person);
            return true;
        }

        /**
         * Method that checks if person is contained in friendslist
         * @param inQuestion to be checked in the system
         * @return True if inQuestion is contained in friends. False if inQuestion is not contained in friends
         */
        public boolean checkMembership(Person inQuestion) {
            return friends.contains(inQuestion);
        }

        /**
         * Method to get friends
         *
         * @return array of friends
         */
        public Person[] toArray() {
            return friends.toArray(new Person[friends.size()]);
        }
    }
}
