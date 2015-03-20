package com.richardscollin.shoppingwithfriends;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class LoggedInActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        TextView hello = (TextView) findViewById(R.id.greeter);
        hello.setText("Hello, " + Model.getCurrentPerson().getName());
        Toast toast = Toast.makeText(getApplicationContext(), "Logged in",Toast.LENGTH_SHORT);
        toast.show();

        processMatches();


        ArrayAdapter<Person> nameAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, Model.getUsers().toArray());
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(nameAdapter);

    }

    /**
     * Method to look into other friends' sale list and report if there is a match.
     * The method will pop up a toast message to the user notifying them of the user and the item for
     * which there is a match. The user can then go into the friends list and view the sale.
     */
    public void processMatches() {
        //No indents to hide how bad this is.
        for (Person.Interest interest : Model.getCurrentPerson().getInterests()) {
        for (Person friend : Model.getCurrentPerson().getFriends()) {
        for (Person.Sale sale : friend.getSales()) {
                    if (interest.getName().toLowerCase().equals(sale.getItemName().toLowerCase())) {
                        if (interest.getCost() >= sale.getPrice()) {
                            Toast.makeText(this, "Match Found!\n" + friend.getName() +
                                    " has a sale for " + sale.getItemName()
                                    , Toast.LENGTH_LONG ).show();
                        } else {
                        }
                    }
                }
            }
        }
    }

    /**
     * Log the user out.
     *
     * @param v view
     */
    public void logOut(View v) {
        Model.setCurrentPerson(null);
        Toast toast = Toast.makeText(getApplicationContext(), "Logged out", Toast.LENGTH_SHORT);
        toast.show();
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_hello, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * launch the friends activity
     *
     * @param view View
     */
    public void launchFriend(View view) {
        startActivity(new Intent(this, ItemListActivity.class));
    }

    /**
     * User can add a friend via this method. Fired with the add friend button is clicked.
     * @param view view
     */
    public void addFriend(View view) {
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        String name = spinner.getSelectedItem().toString();
        Person toAdd = Model.getPerson(name);
        if (null == toAdd) {
            Toast.makeText(getApplicationContext(),
                    "user doesn't exist", Toast.LENGTH_SHORT).show();
            return;
        } else if (toAdd.getName().equals(Model.getCurrentPerson().getName())) {
            Toast.makeText(getApplicationContext(),
                    "Can't add yourself!", Toast.LENGTH_SHORT).show();
            return;
        }
        boolean isInFriends = false;
        for (Person i : Model.getCurrentPerson().getFriends()) {
            if (i.getName().equals(toAdd.getName())) {
                isInFriends = true;
            }
        }
        if (isInFriends) {
            Toast.makeText(getApplicationContext(),
                    "user is already in friends", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast toast = Toast.makeText(getApplicationContext(), "Adding friend " + name, Toast.LENGTH_SHORT);
        toast.show();
        Model.getCurrentPerson().addFriend(
                toAdd
        );
    }

    /**
     * Button fires this method. Removes the user pointed at by the spinner.
     * @param view view
     */
    public void removeFriend(View view) {
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        String name = spinner.getSelectedItem().toString();
        Person toRemove = Model.getPerson(name);

        if (null == toRemove) {
            Toast.makeText(getApplicationContext(),
                    "user doesn't exist", Toast.LENGTH_SHORT).show();
            return;
        } else if (toRemove.getName().equals(Model.getCurrentPerson().getName())) {
            Toast.makeText(getApplicationContext(),
                    "Can't remove yourself!", Toast.LENGTH_SHORT).show();
            return;
        }
        boolean isInFriends = false;
        for (Person i : Model.getCurrentPerson().getFriends()) {
            if (i.getName().equals(toRemove.getName())) {
                isInFriends = true;
            }
        }
        if (!isInFriends) {
            Toast.makeText(getApplicationContext(),
                    "user is already not in friends", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(getApplicationContext(), "Removing friend " + name, Toast.LENGTH_SHORT).show();
        Model.getCurrentPerson().removeFriend(toRemove);
    }

    /**
     * Fired by button. Launches activity for the logged in user to write an interest.
     * @param view view
     */
    public void launchInterest(View view) {
        Intent intent = new Intent(this, RegisterInterest.class);
        startActivity(intent);
    }

    public void reportSale(View view) {
        Intent intent = new Intent(this, SalesReportActivity.class);
        startActivity(intent);
    }

    public void launchMap(View view) {
        Intent intent = new Intent(this, SalesMap.class);
        startActivity(intent);
    }
}
