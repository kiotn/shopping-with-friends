package com.richardscollin.shoppingwithfriends;

import android.app.Application;
import android.graphics.AvoidXfermode;
import android.test.ApplicationTestCase;
import junit.framework.Assert;

import java.util.HashSet;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class PersonEqualTest extends ApplicationTestCase<Application> {
    public PersonEqualTest() {
        super(Application.class);
    }

    public void testSamePerson() {
        Person user = new Person("name", "email", "passwordHash");
        Person tester;
        tester = user;
        assertTrue(user.equals(tester));
    }

    public void testDifferentPersons() {
        Person user = new Person("name", "email", "passwordHash");
        Person tester = new Person("name2", "email2", "passwordHash2");
        assertFalse(user.equals(tester));
    }

    public void testDifferentObjects() {
        Person user = new Person("name", "email", "passwordHash");
        Object tester = new String("");
        assertFalse(user.equals(tester));
    }

    public void testSamePersonDifferent() {
        Person user = new Person("name", "email", "passwordHash");
        Person tester = new Person("name", "email", "passwordHash");
        assertTrue(user.equals(tester));
    }
}