package com.richardscollin.shoppingwithfriends;

import android.content.Context;
import android.widget.Toast;

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
public final class Model {

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
        Person p2 = new Person("Bob Jones", "test@gatech.edu", "" + "hello".hashCode());
        Person p3 = new Person("Donald Knith", "icecube@gmail.com", "" + "hello".hashCode());
        Person p4 = new Person("Richard Feynmen", "tennisplayer@yahoo.com", "" + "hello".hashCode());
        Person p5 = new Person("Alan Turang", "nigerianprince@scam.org", "" + "hello".hashCode());
        Person p6 = new Person("Ronald McDonald", "evenbiggerscam@itttech.edu", "" + "hello".hashCode());
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
        p1.registerInterest("Laptop", 535.54);
        p1.registerInterest("Dress Shirt", 30.05);
        p1.registerInterest("Pilot Pens", 12.65);
        p3.registerInterest("Ice Tray", 1.99);
        p4.registerInterest("Tennis Balls", 21.99);
        p4.registerInterest("Tennis Shoes", 49.99);
        p5.registerInterest("My Investment", 3500.00);
        p6.registerInterest("2 year associates", 60000.99);
    }

    /**
     * This will be the person currently logged in.
     *
     * @param email email of the person to be logged in.
     */
    public static void setCurrentPerson(String email) {
        currentPerson = getPerson(0, email);
    }

    /**
     * Get a reference to the user that is logged in
     * @return the logged in user
     */
    public static Person getCurrentPerson() {
        return currentPerson;
    }

    /**
     * Check if someone is registered.
     * @param inQuestion person to check
     * @return true if registered.
     */
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

    /**
     * Get the users to this collection.
     * @param userset set of users to replace current set.
     */
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

    /**
     * Get a person by email.
     * @param notUsed dummy arg to overload
     * @param email email of person to get
     * @return null if person is not registered, otherwise reference.
     */
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
     * Give the application context to this class.
     * Necessary for file operations.
     * @param context context of the application.
     */
    public static void setContext(Context context) {
        Model.context = context;
    }

    /**
     * Save all the data to the device
     * @return true if saved, false otherwise
     */
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
            //Toast.makeText(context, "Data saved to device", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Read the data from the device, and overwrite the current data set.
     * @return true if read, false if not read.
     */
    public static boolean readData() {
        Gson gson = new Gson();
        users = new HashSet<>();


        //load up hashset from memory
        try {
            jsonStrings = gson.fromJson(new BufferedReader(
                    new FileReader(new File(context.getFilesDir(), "appData"))
                    ), HashSet.class);
            //Toast.makeText(context, "Data read from device", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        users = new HashSet<>();
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
