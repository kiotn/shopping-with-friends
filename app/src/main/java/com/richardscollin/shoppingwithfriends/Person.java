package com.richardscollin.shoppingwithfriends;

/**
 * Created by John on 2/6/2015.
 */
public class Person {

    private String email;
    private String passwordHash;
    private FriendList friends;


    /**
     * Person constructor.
     *
     * @param email email of user
     * @param passwordHash hash of password to log in
     */
    public Person(String email, String passwordHash) {
        this.email = email;
        this.passwordHash = passwordHash;
    }

    /**
     *
     * Method that gets user's email
     * @return user's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Method that gets user's password
     * @return user's password
     */
    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     * Method that returns string of email
     * @return string of email
     */
    public String toString() {
        return getEmail();
    }

    /**
     * Method that adds person to friends
     * @param person
     */
    public void addFriend(Person person) {
        friends.addFriend(person);
    }

    /**
     * Method that removes person from friends
     * @param person
     */
    public void removeFriend(Person person) {
        friends.removeFriend(person);
    }

    /**
     * Method that gets the array of friends
     * @return array of friends
     */
    public Person[] getFriends() {
        return friends.toArray();
    }

}
