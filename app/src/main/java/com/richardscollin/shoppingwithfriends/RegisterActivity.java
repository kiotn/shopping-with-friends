package com.richardscollin.shoppingwithfriends;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * registration activity
 *
 * Team 4
 */
public class RegisterActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    /**
     * Method that registers. Converts email and password into strings and creating hashcode per person
     * @param v View
     */
    public void register(View v) {
        String email = ((TextView) findViewById(R.id.registerEmail)).getText().toString();
        String password = ((TextView) findViewById(R.id.registerPassword)).getText().toString();
        String name = ((TextView) findViewById(R.id.registerName)).getText().toString();

        Model.registerUser(new Person(name, email, "" + password.hashCode()));

        Toast toast = Toast.makeText(getApplicationContext(), "Registering user: "
                + email, Toast.LENGTH_LONG);
        toast.show();
        Model.saveData();
        finish();
    }

    /**
     * method that cancels Registration
     * @param v View
     */
    public void cancelRegistration(View v) {
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_register, menu);
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
}
