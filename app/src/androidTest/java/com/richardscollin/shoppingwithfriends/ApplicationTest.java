package com.richardscollin.shoppingwithfriends;

import android.app.Application;
import android.test.ApplicationTestCase;

import java.util.HashSet;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    public void setUp() {
        Model.setUsers(new HashSet<User>());
        Model.setCurrentPerson(null);
    }

    public void testNoPopulate() {
        //...
    }

    public void testOnePopulate() {

    }

    public void testMultPopulate() {

    }
}