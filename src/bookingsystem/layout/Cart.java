package bookingsystem;

import bookingsystem.layout.SetReservation;
import java.util.ArrayList;

public class Cart {

    // Save the vehical type and seats to an array outputs the whole cart to CLI
    public static ArrayList<String> busCart = new ArrayList<String>();
    public static ArrayList<String> boatCart = new ArrayList<String>();
    public static ArrayList<String> tramCart = new ArrayList<String>();

    public static void addBusCart() {
        busCart.add(SetReservation.busBooking);
    }

    public static void addBoatCart() {
        boatCart.add(SetReservation.boatBooking);
    }

    public static void addTramCart() {
        tramCart.add(SetReservation.tramBooking);
    }  
}