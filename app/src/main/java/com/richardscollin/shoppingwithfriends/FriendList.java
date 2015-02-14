package com.richardscollin.shoppingwithfriends;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by John on 2/6/2015.
 */
public class FriendList {

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