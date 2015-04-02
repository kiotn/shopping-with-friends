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

    public void testRaveenaRemoveFriendBySize() {
        //Remove friend
        //Remove a user in the app
        Model.populate();
        assertTrue(Model.getUsers().size() == 8);
        Model.removeUser(users[0]);
        assertTrue(Model.getUsers().size() == 7);

    }

    public void testRaveenaRemoveFriendByMembership() {
        //Remove friend
        //Remove a user in the app
        Model.populate();
        User george = users[0];
        assertTrue(Model.checkMembership(george));
        Model.removeUser(george);
        assertFalse(Model.checkMembership(george));
    }

    public void testRaveenaRemoveAll() {
        for (int i = 0; i < 10; i++) {
            Model.populate();
        }
        assertTrue(Model.getUsers().size() == 8);
        for (User i : users) {
            Model.removeUser(i);
        }
        assertTrue(Model.getUsers().size()== 2);
            //Guest and admin do not use comparison, and cannot be removed from app.
    }

    public void testRaveenaRemoveNonExistantUser() {
        //add a new user
        User george = new Person("George Burdell", "foo@example.com", "" + passwordHash);
        Model.registerUser(george);
        assertTrue(Model.checkMembership(george));
        assertTrue(Model.getUsers().size() == 1);
        assertTrue(Model.checkMembership(george));
        Model.removeUser(george);
        assertFalse(Model.checkMembership(george));
        assertTrue(Model.getUsers().size() == 0);
        assertFalse(Model.checkMembership(george));
        //assert true
        //remove that user
        //asesert false
    }
}