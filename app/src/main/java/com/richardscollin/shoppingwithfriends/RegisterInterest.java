package com.richardscollin.shoppingwithfriends;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class RegisterInterest extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_interest);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register_sale, menu);
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
     * Register this sale to the current person.
     * @param view view
     */
    public void Register(View view) {
        TextView itemView = (TextView) findViewById(R.id.editItem);
        TextView priceView = (TextView) findViewById(R.id.editPrice);

        Model.getCurrentPerson().registerInterest(
                "" + itemView.getText(),
                Double.parseDouble("" + priceView.getText())
        );
        Model.saveData();
        finish();
    }


}
