package com.richardscollin.shoppingwithfriends;

/**
 * Created by John on 2/6/2015.
 */
public class Person {

    private String email;
    private String passwordHash;
    private FriendList friends;


    public Person(String email, String passwordHash) {
        this.email = email;
        this.passwordHash = passwordHash;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String toString() {
        return getEmail();
    }

    public void addFriend(Person person) {
        friends.addFriend(person);
    }
    public void removeFriend(Person person) {
        friends.removeFriend(person);
    }

    public Person[] getFriends() {
        return friends.toArray();
    }

}
