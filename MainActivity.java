package com.example.boi.icecreamapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    TextView infoView;
    CheckBox peanutsCheck;
    CheckBox mandMsCheck;
    CheckBox almondsCheck;
    CheckBox strawberryCheck;
    CheckBox brownieCheck;
    CheckBox marshmallowCheck;
    CheckBox oreoCheck;
    CheckBox gummyCheck;
    TextView seekbarView;
    SeekBar seekBar;
    Spinner flavorSpinner;
    Spinner sizeSpinner;
    TextView priceView;
    String size = "small";
    String flavor = "vanilla";
    double price = 0.0;
    ArrayList<OrderItem> orders;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        infoView = (TextView) findViewById(R.id.infoView);
        peanutsCheck = (CheckBox) findViewById(R.id.peanutsCheck);
        mandMsCheck = (CheckBox) findViewById(R.id.mandMsCheck);
        almondsCheck = (CheckBox) findViewById(R.id.almondsCheck);
        strawberryCheck = (CheckBox) findViewById(R.id.strawberryCheck);
        brownieCheck = (CheckBox) findViewById(R.id.brownieCheck);
        marshmallowCheck = (CheckBox) findViewById(R.id.marshmallowCheck);
        oreoCheck = (CheckBox) findViewById(R.id.oreoCheck);
        gummyCheck = (CheckBox) findViewById(R.id.gummyCheck);
        seekbarView = (TextView) findViewById(R.id.seekbarView);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        flavorSpinner = (Spinner) findViewById(R.id.flavorSpinner);
        sizeSpinner = (Spinner) findViewById(R.id.sizeSpinner);
        priceView = (TextView) findViewById(R.id.priceView);
        orders = new ArrayList<OrderItem>();


        sizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                size = sizeSpinner.getSelectedItem().toString();
                calculatePrice();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        flavorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                flavor = flavorSpinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                seekbarView.setText(Integer.toString(i) + " oz.");
                calculatePrice();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        flavorSpinner = (Spinner) findViewById(R.id.flavorSpinner);
        sizeSpinner = (Spinner) findViewById(R.id.sizeSpinner);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        if(id == R.id.action_about){
            Intent i = new Intent(this, AboutActivity.class);
            startActivity(i);
        }

        if(id == R.id.action_orderHistory){
            Intent i = new Intent(this, OrderActivity.class);
            i.putExtra("DataKey", orders);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    public void processTheWorks(View view) {
        Log.d("DEBUG", "The Works");
        peanutsCheck.setChecked(true);
        mandMsCheck.setChecked(true);
        almondsCheck.setChecked(true);
        strawberryCheck.setChecked(true);
        marshmallowCheck.setChecked(true);
        gummyCheck.setChecked(true);
        oreoCheck.setChecked(true);
        brownieCheck.setChecked(true);
        seekBar.setProgress(3);
        sizeSpinner.setSelection(2);
        calculatePrice();
       // updateInfoDisplay();

    }

    public void calculatePrice(){
        price = 0.0;

        //Ice cream sizes
        if(size.equalsIgnoreCase("small")){
            price += 2.99;
        }
        else if(size.equalsIgnoreCase("medium")){
            price += 3.99;
        }
        else if(size.equalsIgnoreCase("large")){
            price += 4.99;
        }

        // toppings
        if (peanutsCheck.isChecked())
            price += 0.15;
        if (mandMsCheck.isChecked())
            price += 0.25;
        if(almondsCheck.isChecked())
            price += 0.15;
        if(brownieCheck.isChecked())
            price += 0.20;
        if(strawberryCheck.isChecked())
            price += 0.20;
        if(oreoCheck.isChecked())
            price += 0.20;
        if(gummyCheck.isChecked())
            price += 0.20;
        if(marshmallowCheck.isChecked())
            price += 0.15;

        // hot fudge
        if(seekBar.getProgress() == 1)
            price += 0.15;
        if(seekBar.getProgress() == 2)
            price += 0.25;
        if(seekBar.getProgress() == 3)
            price += 0.30;

        updateInfoDisplay();
    }

    public void updateInfoDisplay(){
        // price is at 2 decimals precision
        String str = String.format("%.2f", price);
        priceView.setText("$" + str);

    }

    public void processCheckboxChanged(View view) {
       calculatePrice();
    }

    public void processReset(View view) {
        Log.d("DEBUG", "Reset");
        peanutsCheck.setChecked(false);
        mandMsCheck.setChecked(false);
        almondsCheck.setChecked(false);
        strawberryCheck.setChecked(false);
        marshmallowCheck.setChecked(false);
        gummyCheck.setChecked(false);
        oreoCheck.setChecked(false);
        brownieCheck.setChecked(false);
        seekBar.setProgress(1);
        sizeSpinner.setSelection(0);
        calculatePrice();
    }

    public void processOrder(View view) {
        Log.d("DEBUG", "Order btn pressed");
        Date currentTime = Calendar.getInstance().getTime();
        String date = currentTime.toString();
        String str = String.format("%.2f", price);
        orders.add(new OrderItem(flavor, size, "$" + str, date));
        Toast.makeText(this, "Your order has been placed!", Toast.LENGTH_SHORT).show();
    }
}
