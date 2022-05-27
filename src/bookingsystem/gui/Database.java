package bookingsystem.gui;

import java.sql.*;
import java.util.Random;

public class Database {

    private static final String URL = "jdbc:derby:BookingSys_Ebd; create=true";
    private static final String USER_NAME = "pdc";
    private static final String PASSWORD = "pdc";
    public static int id;

    public static Connection conn;

    public void dbsetup() {
        try {
            conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            Statement statement = conn.createStatement();

            //REMOVE ONCE DONE
            statement.executeUpdate("DROP TABLE userInfo");

            if (!checkTableExisting("userInfo")) {
                statement.executeUpdate("CREATE TABLE userInfo(userid INTEGER, bus VARCHAR(100), boat VARCHAR(100), tram VARCHAR(100))");
                System.out.println("Create Table userInfo");
            }

            statement.executeUpdate("INSERT INTO userInfo VALUES(123456, 'testbus', 'testboat', 'testtram')");
            statement.executeUpdate("INSERT INTO userInfo VALUES(987654, 'testtttt1', 'testtttt2', 'testtttt3')");
            System.out.println("Insert data");

            statement.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private boolean checkTableExisting(String tableName) {
        boolean exists = false;
        try {
            ResultSet rs = conn.getMetaData().getTables(null, null, tableName, null);
            while (rs.next()) {
                String tName = rs.getString("TABLE_NAME");

                if (tName != null && tName.equals(tableName)) {
                    exists = true;
                    break;
                }
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return exists;
    }

    public void close() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean checkID(int id) {
        boolean exists = false;

        try {
            Statement statement = conn.createStatement();

            ResultSet rs = statement.executeQuery("SELECT userid FROM userInfo");
            while (rs.next()) {
                String userNum = rs.getString("userid");
                System.out.println("ID " + userNum);
                exists = (Integer.valueOf(userNum) == id);
            }
            statement.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return exists;
    }

    public int getNumber() {
        Random rand = new Random();
        id = rand.nextInt(1000000 - 100000) + 100000;

        //if it already exists it generates a new number
        while (checkID(id)) {
            id = rand.nextInt(1000000 - 100000) + 100000;
        }
        System.out.println(id);
        return id;
    }

    public void saveData(String booking) {
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate("INSERT INTO UserInfo " + "VALUES(" + id + ", '" + booking + "')");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void printRecipt(int oldID) {

        try {
            Statement statement = conn.createStatement();

            ResultSet rs = statement.executeQuery("SELECT * FROM userInfo WHERE userid = " + oldID);
            
            int size = 0;
            
            while (rs.next()) {
                int userNum = rs.getInt("userid");
                String userbus = rs.getString("bus");
                String userboat = rs.getString("boat");
                String usertram = rs.getString("tram");

                StartPanel.reciptPanel.reciptTxtFld.setText("ID: " + userNum + " \n\nBus: " + userbus + "\nBoat: " + userboat + "\nTram: " + usertram);
                size++;
            }
            
            if (size == 0) {
                StartPanel.reciptPanel.reciptTxtFld.setText("ID does not exist");
            }
            
            statement.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

//    public static void main(String[] args) {
//        Database dbManager = new Database();
//
//        System.out.println("SETTING UP DATABASE");
//        dbManager.dbsetup();
////        System.out.println("\nCHECKING ID");
////        dbManager.getNumber();
////        System.out.println("\nSave ID");
////        dbManager.saveData("");
////        System.out.println("\nCHECKING ID 2");
////        dbManager.getNumber();
//        System.out.println("Print Recipt");
//        dbManager.printRecipt(123456);
//
//        dbManager.close();
//    }
}