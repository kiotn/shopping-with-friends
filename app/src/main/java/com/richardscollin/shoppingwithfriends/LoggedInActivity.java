package com.richardscollin.shoppingwithfriends;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class LoggedInActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
        TextView hello = (TextView) findViewById(R.id.greeter);
        hello.setText("Hello, " + RegisteredUsers.getCurrentPerson().getEmail());
    }

    /**
     * Log the user out.
     *
     * @param v
     */
    public void logOut(View v) {
        RegisteredUsers.setCurrentPerson(null);
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

    public void addFriend(View view) {
        TextView text = (TextView) findViewById(R.id.textView);
        String email = text.getText().toString();

        RegisteredUsers.getCurrentPerson().addFriend(
                RegisteredUsers.getPerson(email)
        );
    }
}