package com.richardscollin.shoppingwithfriends;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;


public class RegisterActivity extends ActionBarActivity {

    ArrayList<String> loginStuff = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Bundle extras = getIntent().getExtras();
        loginStuff = extras.getStringArrayList("loginInfo");
    }

    public void register(View v) {
        //add the email and password to the users string array in the main activity.
        //email:password

        String email = ((TextView) findViewById(R.id.registerEmail)).getText().toString();
        String password = ((TextView) findViewById(R.id.registerPassword)).getText().toString();

        String toBeAdded = email + ":" + password;

        LoginActivity.DUMMY_CREDENTIALS.add(toBeAdded);

        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
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
