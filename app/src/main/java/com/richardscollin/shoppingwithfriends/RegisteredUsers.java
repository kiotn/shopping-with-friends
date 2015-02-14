package com.richardscollin.shoppingwithfriends;

import java.util.HashSet;

/**
 * Created by John on 2/6/2015.
 */
public final class RegisteredUsers {

    private static HashSet<Person> users = new HashSet<>();
    private static int userSize = 0;

    public static void populate() {
        users.add(
                new Person("foo@example.com", "" + "hello".hashCode())
        );
        users.add(
                new Person("test@gatech.edu", "" + "test".hashCode())
        );
    }

    public static boolean checkMembership(Person inQuestion) {
        for (Person i : users) {
            if (i.getEmail().equals(inQuestion.getEmail())) {
                if (i.getPasswordHash().equals(inQuestion.getPasswordHash())) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean registerUser(Person toAdd) {
        if (users.contains(toAdd)) {
            return false;
        }
        users.add(toAdd);
        userSize++;
        return true;
    }

    public static boolean removeUser(Person toRemove) {
        if (users.contains(toRemove)) {
            users.remove(toRemove);
            userSize--;
            return true;
        }
        return false;
    }

    public static HashSet<Person> getUsers() {
        return users;
    }

    public int getSize() {
        return userSize;
    }
}
