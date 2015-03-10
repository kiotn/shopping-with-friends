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
    private HashSet<Sale> sales;


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
        sales = new HashSet<>();
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
        if (num > 5 || num < 0) {
            return;
        }
        int total = rating * ratingWeight + num;
        ratingWeight += 1;
        rating = total / ratingWeight;
        Model.saveData();
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
        Model.saveData();
    }

    /**
     * Method that removes person from friends
     * @param person
     */
    public void removeFriend(Person person) {
        friends.removeFriend(person);
        Model.saveData();
    }

    /**
     * Register a sale
     * @param name name of item
     * @param cost cost of item
     */
    public void registerInterest (String name, double cost) {
        interests.add(new Interest(name, cost));
        Model.saveData();
    }

    public void registerSale(String item, String location, double cost) {
        sales.add(new Sale(item, location, cost));
        Model.saveData();
    }

    /**
     * Get a string representation of the sales set
     * @return string printout of the sales.
     */
    public String getInterestsToString() {
        String result = "---N Interests: " + interests.size() + "---\n";
        for (Interest i : interests) {
            result += i.toString() + "\n";
        }
        return result;
    }

    public String getSalesToString() {
        String result = "---N Sales: " + sales.size() + "---\n";
        for (Sale i : sales) {
            result += i.toString() + "\n";
        }
        return result;
    }

    public HashSet<Interest> getInterests() {
        return interests;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (!email.equals(person.email)) return false;
        if (!name.equals(person.name)) return false;
        if (!sales.equals(person.getSales())) return false;
        if (!interests.equals(person.getInterests())) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + email.hashCode();
        result += 31 * result + sales.hashCode();
        result += 31 * result + interests.hashCode();
        return result;
    }

    /**
     * Method that gets the array of friends
     * @return array of friends
     */
    public Person[] getFriends() {
        return friends.toArray();
    }

    /**
     * Get the sales.
     * @return HashSet of the sales.
     */
    public HashSet<Sale> getSales() {
        return sales;
    }


    public class Interest {

        private double cost;
        private String name;

        /**
         * Constructor.
         * @param name Name of item the user wants.
         * @param cost Cost of the item the user wants.
         */
        public Interest(String name, double cost) {
            this.name = name;
            this.cost = cost;
        }

        /**
         * Get the cost of this interest.
         * @return the cost of this interset.
         */
        public double getCost() {
            return cost;
        }

        /**
         * Get the cost of this interest.
         * @return the cost of this interest.
         */
        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Interest interest = (Interest) o;

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

        /**
         * Get the string representation of this item.
         */
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
            Model.saveData();
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

    public class Sale{

        String itemName;
        String location;
        double price;

        /**
         * Constructor.
         * @param itemName name of item
         * @param location place of item sale
         * @param price price of item sale
         */
        public Sale(String itemName, String location, double price){
            this.itemName = itemName;
            this.location = location;
            this.price = price;
        }

        /**
         * Get the item name.
         * @return the item name.
         */
        public String getItemName() {
            return itemName;
        }

        /**
         * Get the location of the sale
         * @return the location
         */
        public String getLocation() {
            return location;
        }

        /**
         * Get the price of the item of this sale.
         * @return the price
         */
        public double getPrice() {
            return price;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Sale)) return false;

            Sale sale = (Sale) o;

            if (Double.compare(sale.price, price) != 0) return false;
            if (!itemName.equals(sale.itemName)) return false;
            if (!location.equals(sale.location)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result;
            long temp;
            result = itemName.hashCode();
            result = 31 * result + location.hashCode();
            temp = Double.doubleToLongBits(price);
            result = 31 * result + (int) (temp ^ (temp >>> 32));
            return result;
        }

        /**
         * get a string representation of this item.
         * @return the String representation of this item.
         */
        public String toString() {
            String result = "";
            result += itemName;
            result += " at $";
            result += price;
            return result;
        }
    }
}
