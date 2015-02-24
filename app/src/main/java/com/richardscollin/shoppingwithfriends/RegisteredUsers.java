package com.richardscollin.shoppingwithfriends;

import android.content.Context;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

/**
 * Created by John on 2/6/2015.
 */
public final class RegisteredUsers {

    private static HashSet<Person> users = new HashSet<>();
    private static int userSize = 0;
    private static Person currentPerson;
    private static Context context;
    private static HashSet jsonStrings = new HashSet();

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
        saveData();
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
            saveData();
            return true;
        }
        return false;
    }

    public static void setUsers(HashSet<Person> userset) {
        users = userset;
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

    public static void setContext(Context context) {
        RegisteredUsers.context = context;
    }

    public static boolean saveData() {
        Gson gson = new Gson();
        jsonStrings = new HashSet();


        //load up the jsons into jsonStrings
        for (Person i : users) {
            jsonStrings.add(gson.toJson(i));
        }
        //load up this object into a json
        String cumulativeData = gson.toJson(jsonStrings);
        //write data to device.
        File file = new File(context.getFilesDir(), "appData");
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(cumulativeData);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean readData() {
        Gson gson = new Gson();
        users = new HashSet<>();


        //load up hashset from memory
        try {
            jsonStrings = gson.fromJson(new BufferedReader(
                    new FileReader(new File(context.getFilesDir(), "appData"))
                    ), HashSet.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        //go through each string in the hashset, and create a user for each one.
        for (Object i : jsonStrings) {
            users.add((Person) gson.fromJson((String) i, Person.class));
        }
        return true;
    }

    /**
     * Know how many people are registered.
     * @return int counting users.
     */
    public int getSize() {
        return userSize;
    }
}
