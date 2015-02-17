package com.richardscollin.shoppingwithfriends;

import java.util.HashSet;

/**
 * Created by John on 2/6/2015.
 */
public class Person {

    private String email;
    private String passwordHash;
    private FriendList friends;


    /**
     * Person constructor.
     *
     * @param email email of user
     * @param passwordHash hash of password to log in
     */
    public Person(String email, String passwordHash) {
        this.email = email;
        this.passwordHash = passwordHash;
        friends = new FriendList();
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

    /**
     * Method that returns string of email
     * @return string of email
     */
    public String toString() {
        return getEmail();
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

    /**
     * Method that gets the array of friends
     * @return array of friends
     */
    public Person[] getFriends() {
        return friends.toArray();
    }

    /**
     * Created by John on 2/6/2015.
     */
    public static class FriendList {

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
