package bookingsystem.util;

import bookingsystem.BookingSystem;
import bookingsystem.layout.SetReservation;
import java.util.ArrayList;

public class Cart {

    /*
    Save the vehical type and seats to an array 
    outputs the whole cart to CLI
     */
    public static ArrayList<String> busCart = new ArrayList<String>();
    public static ArrayList<String> boatCart = new ArrayList<String>();
    public static ArrayList<String> tramCart = new ArrayList<String>();

    public static void addBusCart() {
        //debug
        BookingSystem.getLog().addEntry("Function: addBusCart()");

        busCart.add(SetReservation.busBooking);
        System.out.println(busCart);
    }

    public static void addBoatCart() {
        //debug
        BookingSystem.getLog().addEntry("Function: addBoatCart()");

        boatCart.add(SetReservation.boatBooking);
        System.out.println(boatCart);
    }

    public static void addTramCart() {
        //debug
        BookingSystem.getLog().addEntry("Function: addTramCart()");

        tramCart.add(SetReservation.tramBooking);
        System.out.println(tramCart);
    }

}
