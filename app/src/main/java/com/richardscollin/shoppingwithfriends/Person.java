package com.richardscollin.shoppingwithfriends;

import java.util.HashSet;

/**
 * Created by John on 2/6/2015.
 */
public class Person {

    private String name;
    private String email;
    private String passwordHash;
    private FriendList friends;
    private int rating;
    private int ratingWeight;
    private HashSet<Interest> interests;


    /**
     * Person constructor.
     *
     * @param email email of user
     * @param passwordHash hash of password to log in
     */
    public Person(String name, String email, String passwordHash) {
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        friends = new FriendList();
        interests = new HashSet<>();
    }

    /**
     * Get the name
     * @return the name
     */
    public String getName() {
        return name;
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
     * Give a rating to the user. Naive way of doing it, but it does weigh the ratings based on how
     * many rating there currently are.
     * @param num rating to give.
     */
    public void giveRating(int num) {
        int total = rating * ratingWeight + num;
        ratingWeight += 1;
        rating = total / ratingWeight;
        RegisteredUsers.saveData();
    }

    /**
     * Get this users rating
     * @return rating
     */
    public int getRating() {
        return rating;
    }

    /**
     * Method that returns string of email
     * @return string of email
     */
    public String toString() {
        return getName();
    }

    /**
     * Method that adds person to friends
     * @param person
     */
    public void addFriend(Person person) {
        if (friends.checkMembership(person)) {
            return;
        }
        friends.addFriend(person);
        RegisteredUsers.saveData();
    }

    /**
     * Method that removes person from friends
     * @param person
     */
    public void removeFriend(Person person) {
        friends.removeFriend(person);
        RegisteredUsers.saveData();
    }

    /**
     * Register a sale
     * @param name name of item
     * @param cost cost of item
     */
    public void registerInterest (String name, double cost) {
        interests.add(new Interest(name, cost));
        RegisteredUsers.saveData();
    }

    /**
     * Get a string representation of the sales set
     * @return string printout of the sales.
     */
    public String getInterests() {
        String result = "---N Interests: " + interests.size() + "---\n";
        for (Interest i : interests) {
            result += i.toString() + "\n";
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (!email.equals(person.email)) return false;
        if (!name.equals(person.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + email.hashCode();
        return result;
    }

    /**
     * Method that gets the array of friends
     * @return array of friends
     */
    public Person[] getFriends() {
        return friends.toArray();
    }



    private class Interest {
        private double cost;
        private String name;

        public Interest(String name, double cost) {
            this.name = name;
            this.cost = cost;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Interest interest = (Interest) o;

            if (Double.compare(interest.cost, cost) != 0) return false;
            if (!name.equals(interest.name)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result;
            long temp;
            temp = Double.doubleToLongBits(cost);
            result = (int) (temp ^ (temp >>> 32));
            result = 31 * result + name.hashCode();
            return result;
        }

        @Override

        public String toString() {
            String result = "";
            result += name;
            result += " at $";
            result += cost;
            return result;
        }

    }

    /**
     * Created by John on 2/6/2015.
     */
    private class FriendList {

        private HashSet<Person> friends = new HashSet<>();

        /**
         * Add a person to the friendlist.
         *
         *
         * @param person to be added
         * @return True if added. False if already added.
         */
        public boolean addFriend(Person person) {
            if (checkMembership(person)) {
                return false;
            }
            friends.add(person);
            return true;
        }


        /**
         * Remove a person from friendlist
         * @param person to be removed
         * @return True if removed. False if person does not exist.
         */
        public boolean removeFriend(Person person) {
            if (!checkMembership(person)) {
                //return false;
            }
            friends.remove(person);
            RegisteredUsers.saveData();
            return true;
        }

        /**
         * Method that checks if person is contained in friendslist
         * @param inQuestion to be checked in the system
         * @return True if inQuestion is contained in friends. False if inQuestion is not contained in friends
         */
        public boolean checkMembership(Person inQuestion) {
            return friends.contains(inQuestion);
        }

        /**
         * Method to get friends
         *
         * @return array of friends
         */
        public Person[] toArray() {
            return friends.toArray(new Person[friends.size()]);
        }
    }
}
