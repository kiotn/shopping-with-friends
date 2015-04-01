package com.richardscollin.shoppingwithfriends;

import junit.framework.TestCase;

public class PersonRatingTest extends TestCase {


    public void testNoRating() throws Exception {
        Person user = new Person("name", "email", "passwordHash");
        assertEquals(0.0, user.getRating());
    }

    public void testOneRating() throws Exception {
        Person user = new Person("name", "email", "passwordHash");
        user.giveRating(4);
        assertEquals(4.0, user.getRating());
    }

    public void testTwoRatings() throws Exception {
        Person user = new Person("name", "email", "passwordHash");
        user.giveRating(4);
        user.giveRating(5);
        assertEquals(4.5, user.getRating());
    }

    public void testMultipleRatings() throws Exception {
        Person user = new Person("name", "email", "passwordHash");
        user.giveRating(4);
        user.giveRating(5);
        user.giveRating(3);
        assertEquals(4.0, user.getRating());
    }

}