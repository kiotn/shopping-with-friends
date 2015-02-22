package com.richardscollin.shoppingwithfriends;

import java.util.HashSet;

/**
 * Created by John on 2/6/2015.
 */
public final class RegisteredUsers {

    private static HashSet<Person> users = new HashSet<>();
    private static int userSize = 0;
    private static Person currentPerson;

    /**
     * Dummy method to add some users to the app and
     * setup some friendships.
     */
    public static void populate() {
        //Create users with a quantity of 6, the number of the beast
        Person p1 = new Person("George Burdell", "foo@example.com", "" + "hello".hashCode());
        Person p2 = new Person("Bob Jones", "test@gatech.edu", "" + "world".hashCode());
        Person p3 = new Person("Donald Knith", "icecube@gmail.com", "" + "speaking".hashCode());
        Person p4 = new Person("Richard Feynmen", "tennisplayer@yahoo.com", "" + "from the".hashCode());
        Person p5 = new Person("Alan Turang", "nigerianprince@scam.org", "" + "planet".hashCode());
        Person p6 = new Person("Ronald McDonald", "evenbiggerscam@itttech.edu", "" + "jupiter".hashCode());
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

        //Add some sales
        p1.registerSale("Laptop", 535.54, "Best Buy");
        p1.registerSale("Dress Shirt", 30.05, "Costco");
        p1.registerSale("Pilot Pens", 12.65, "Staples");
        p3.registerSale("Ice Tray", 1.99, "Home Depot");
        p4.registerSale("Tennis Balls", 21.99, "Sports  Authority");
        p4.registerSale("Tennis Shoes", 49.99, "Tennis Warehouse");
        p5.registerSale("My Investment", 3500.00, "Africa");
        p6.registerSale("2 year associates", 60000.99, "ITT Tech");


    }

    /**
     * This will be the person currently logged in.
     *
     * @param email email of the person to be logged in.
     */
    public static void setCurrentPerson(String email) {
        currentPerson = getPerson(0, email);
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

    /**
     * Register this person into the app.
     * @param toAdd Person to add
     * @return true if added, false if not added.
     */
    public static boolean registerUser(Person toAdd) {
        if (users.contains(toAdd)) {
            return false;
        }
        users.add(toAdd);
        userSize++;
        return true;
    }

    /**
     * Remove this person from the app.
     * @param toRemove Person to remove
     * @return true if removed, false otherwise.
     */
    public static boolean removeUser(Person toRemove) {
        if (users.contains(toRemove)) {
            users.remove(toRemove);
            userSize--;
            return true;
        }
        return false;
    }

    /**
     * Get the users registered
     * @return HashSet of the people.
     */
    public static HashSet<Person> getUsers() {
        return users;
    }

    public static Person getPerson(int notUsed, String email) {
        Person result = null;
        for (Person i : users) {
            if (i.getEmail().equals(email)) {
                result = i;
            }
        }
        return result;
    }

    public static Person getPerson(String name) {
        Person result = null;
        for (Person i : users) {
            if (i.getName().equals(name)) {
                result = i;
            }
        }
        return result;
    }

    /**
     * Know how many people are registered.
     * @return int counting users.
     */
    public int getSize() {
        return userSize;
    }
}
