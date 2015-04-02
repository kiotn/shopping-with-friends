package com.richardscollin.shoppingwithfriends;

import android.app.Application;
import android.test.ApplicationTestCase;

import java.util.HashSet;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class CheckMemberTest extends ApplicationTestCase<Application> {
    public CheckMemberTest() {
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

    public void testCheckEmpty() {
        assertTrue(null == Model.getCurrentPerson());
        assertEquals(new HashSet<User>(), Model.getUsers());
        Person hanna = new Person("Hanna Peter", "lala@gmail.com", "" + passwordHash);
        assertFalse(Model.checkMembership(hanna));
    }

    public void testAfterPopulate() {
        Model.populate();
        //check every member
        for (User i : users) {
            assertTrue(Model.checkMembership(i));
        }
        //check specific member
        User george = users[0];
        assertTrue(Model.checkMembership(george));
    }

    public void testAfterAdd() {
        Person perry = new Person("Perry Platypus", "perry@platypus.com", "" + passwordHash);
        Model.registerUser(perry);
        assertTrue(Model.checkMembership(perry));
    }

    public void testAfterRemove() {
        Model.populate();
        User george = users[0];
        Model.removeUser(george);
        assertFalse(Model.checkMembership(george));
    }
}