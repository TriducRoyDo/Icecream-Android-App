package com.example.boi.icecreamapp;

import java.io.Serializable;

/**
 * Created by Triduc Do on 3/25/2018.
 */

public class OrderItem implements Serializable{
    String flavor;
    String size;
    String price;
    String timeOfOrder;

    OrderItem(String flavor, String size, String price, String timeOfOrder){
        this.flavor = flavor;
        this.size = size;
        this.price = price;
        this.timeOfOrder = timeOfOrder;
    }

    @Override
    public String toString(){
        return "Date: " + timeOfOrder + "\nFlavor: " + flavor + "\nSize: " + size
                + "\nTotal: " + price;
    }
}
