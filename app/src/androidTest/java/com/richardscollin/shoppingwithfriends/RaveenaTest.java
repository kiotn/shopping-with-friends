package com.richardscollin.shoppingwithfriends;

import android.app.Application;
import android.test.ApplicationTestCase;

import java.util.HashSet;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class RaveenaTest extends ApplicationTestCase<Application> {
    public RaveenaTest() {
        super(Application.class);
    }

    int passwordHash = "hello".hashCode();
    User[] users = {
            new Person("George Burdell", "foo@example.com", "" + passwordHash),
            new Person("Bob Jones", "test@gatech.edu", "" + passwordHash),
            new Person("Donald Knith", "icecube@gmail.com", "" + passwordHash),
            new Person("Richard Feynmen", "tennisplayer@yahoo.com", "" + passwordHash),
            new Person("Alan Turang", "nigerianprince@scam.org", "" + passwordHash),
            new Person("Ronald McDonald", "evenbiggerscam@itttech.edu", "" + passwordHash),
            new Admin("" + passwordHash),
            new Guest()
    };

    public void setUp() {
        Model.setUsers(new HashSet<User>());
        Model.setCurrentPerson(null);
    }

    public void testNoPopulate() {
        //Do not populate the user list.
        assertTrue(null == Model.getCurrentPerson());
        assertEquals(new HashSet<User>(), Model.getUsers());
    }

    public void testOnePopulate() {
        Model.populate();
        assertTrue(Model.getUsers().size() == 8);
        for (User i : users) {
            assertTrue(Model.checkMembership(i));
        }
    }

    public void testMultPopulate() {
        for (int i = 0; i < 100; i++) {
            Model.populate();
        }
        assertTrue("Size was: " + Model.getUsers().size(), Model.getUsers().size() == 8);
        for (User i : users) {
            assertTrue(Model.checkMembership(i));
        }
    }

    public void testPopulateAndRemove() {
        for (int i = 0; i < 38; i++) {  //try a prime
            Model.populate();
        }
        for (User i : users) {
            assertTrue(Model.checkMembership(i));
        }
        for (User i : users) {
            Model.removeUser(i);
        }

        assertTrue("Size was: " + Model.getUsers().size(), Model.getUsers().size() == 2);
            //Guest and admin do not use comparison, and cannot be removed from app.
    }

    public void testAddOneThenPopulate() {
        Model.registerUser(new Person("Hi", "Hi", "" + 21));
        Model.populate();
        assertTrue(Model.getUsers().size() == 1);
        Model.removeUser(new Person("Hi", "Hi", "" + 21));
        assertTrue(Model.getUsers().size() == 0);
        Model.populate();
        assertTrue(Model.getUsers().size() == 8);
    }
}