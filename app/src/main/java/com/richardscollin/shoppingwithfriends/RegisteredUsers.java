package com.richardscollin.shoppingwithfriends;

import java.util.HashSet;

/**
 * Created by John on 2/6/2015.
 */
public final class RegisteredUsers {

    private static HashSet<Person> users = new HashSet<>();
    private static int userSize = 0;
    private static Person currentPerson;

    public static void populate() {
        //Create users with a quantity of 6, the number of the beast
        Person p1 = new Person("foo@example.com", "" + "hello".hashCode());
        Person p2 = new Person("test@gatech.edu", "" + "world".hashCode());
        Person p3 = new Person("icecube@gmail.com", "" + "speaking".hashCode());
        Person p4 = new Person("tennisplayer@yahoo.com", "" + "from the".hashCode());
        Person p5 = new Person("nigerianprince@scam.org", "" + "planet".hashCode());
        Person p6 = new Person("evenbiggerscam@itttech.edu", "" + "jupiter".hashCode());
        //register them
        users.add(p1);
        users.add(p2);
        users.add(p3);
        users.add(p4);
        users.add(p5);
        users.add(p6);

        //setup some friendships.
        p6.addFriend(p5);
        p1.addFriend(p2);
        p1.addFriend(p5);


    }

    public static void setCurrentPerson(String email) {
        currentPerson = getPerson(email);
    }

    public static Person getCurrentPerson() {
        return currentPerson;
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

    public static Person getPerson(String email) {
        Person result = null;
        for (Person i : users) {
            if (i.getEmail().equals(email)) {
                result = i;
            }
        }
        return result;
    }

    public int getSize() {
        return userSize;
    }
}
