package com.richardscollin.shoppingwithfriends;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class SalesReportActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_report);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sales_report, menu);
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
     * Handle the sale data to the appropriate place.
     * @param view View
     */
    public void passRegisterSaleItem(View view) {
        TextView saleName = (TextView) findViewById(R.id.saleName);
        TextView salePrice = (TextView) findViewById(R.id.salePrice);
        TextView saleLocation = (TextView) findViewById(R.id.saleLocation);


        boolean nullFlag = true;
        nullFlag &= null != saleName.getText();
        nullFlag &= null != salePrice.getText();
        nullFlag &= null != saleLocation.getText();

        if (!nullFlag) {
            Toast.makeText(this, "You are missing text", Toast.LENGTH_SHORT).show();
            return;
        }

        Model.getCurrentPerson().registerSale("" + saleName.getText(), "" + saleLocation.getText(),
                Double.parseDouble("" + salePrice.getText()));

        finish();

    }
}
