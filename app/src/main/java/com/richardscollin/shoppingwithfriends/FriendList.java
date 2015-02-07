package com.richardscollin.shoppingwithfriends;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by John on 2/6/2015.
 */
public class FriendList {

    private HashSet<Person> friends = new HashSet<>();

    public boolean addFriend(Person person) {
        if (checkMembership(person)) {
            return false;
        }
        friends.add(person);
        return true;
    }

    public boolean removeFriend(Person person) {
        if (!checkMembership(person)) {
            return false;
        }
        friends.remove(person);
        return true;
    }

    public boolean checkMembership(Person inQuestion) {
        return friends.contains(inQuestion);
    }

    public Person[] toArray() {
        return friends.toArray(new Person[friends.size()]);
    }
}
