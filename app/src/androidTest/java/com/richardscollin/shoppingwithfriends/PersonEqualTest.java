package com.richardscollin.shoppingwithfriends;

import junit.framework.TestCase ;

public class PersonEqualTest extends TestCase{

   // @Test
    public void testSamePerson() {
        Person user = new Person("name", "email", "passwordHash");
        Person tester;
        tester = user;
        assertTrue(user.equals(tester));
    }

   // @Test
    public void testDifferentPersons() {
        Person user = new Person("name", "email", "passwordHash");
        Person tester = new Person("name2", "email2", "passwordHash2");
        assertFalse(user.equals(tester));
    }


   // @Test
    public void testDifferentObjects() {
        Person user = new Person("name", "email", "passwordHash");
        Object tester = new String("");
        assertFalse(user.equals(tester));
    }

   // @Test
    public void testSamePersonDifferent() {
        Person user = new Person("name", "email", "passwordHash");
        Person tester = new Person("name", "email", "passwordHash");
        assertTrue(user.equals(tester));
    }
}