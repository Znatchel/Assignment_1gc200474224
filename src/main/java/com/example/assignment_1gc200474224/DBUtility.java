package com.example.assignment_1gc200474224;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeMap;
import java.util.TreeSet;


public class DBUtility {
    //private static String user = "student"; //MySQL server username
    //private static String pw = "student";   //MySQL server password

    private static String user = "root"; //MySQL server username
    private static String pw = "root";   //MySQL server password

    //jdbc:mysql = Java DataBase Connector to MySQL
    //127.0.0.1 = IP address where the MySQL server is
    //3306 = Port # that MySQL server is accessible on
    //F22 = the database name
    //private static String connectUrl = "jdbc:mysql://127.0.0.1:3306/F22";
    private static String connectUrl = "jdbc:mysql://localhost:3306/nuclearoption";

    public static ArrayList<NuclearArms> getNukesFromDB()
    {
        ArrayList<NuclearArms> nukes = new ArrayList<>();

        //create the sql string we want to run on the database
        String sql = "SELECT * FROM nuclearoption.`nuclear-warhead-stockpiles`";

        //the try () is called "try with resources".  Anything opened in the () will
        //automatically close when the try block is done.
        try(
                //1.  connect to the database
                Connection conn = DriverManager.getConnection(connectUrl,user,pw);

                //2.  create a statement object
                Statement statement = conn.createStatement();

                //3.  use the statement object to run the sql and return a ResultSet object
                ResultSet resultSet = statement.executeQuery(sql);
        )
        {
            //4.  loop over the resultSet and create Topping objects
            while (resultSet.next())
            {
                String country = resultSet.getString("entity");
                String countryCode = resultSet.getString("code");
                int year = resultSet.getInt("year");
                int amountOfWeapons = resultSet.getInt("nuclear_weapons_stockpile");
                int id = resultSet.getInt("id");
                NuclearArms newNuclearArms = new NuclearArms(country,countryCode,year,amountOfWeapons,id);
                nukes.add(newNuclearArms);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return nukes;
    }
    public static ObservableList<PieChart.Data> getYearSummary()
    {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        //create the sql string we want to run on the database
        String sql ="SELECT Entity, `nuclear_weapons_stockpile`" +

                "FROM nuclearoption.`nuclear-warhead-stockpiles`" +

                "WHERE Year LIKE '2000' AND nuclear_weapons_stockpile > '0';";



        //the try () is called "try with resources".  Anything opened in the () will
        //automatically close when the try block is done.
        try(
                //1.  connect to the database
                Connection conn = DriverManager.getConnection(connectUrl,user,pw);

                //2.  create a statement object
                Statement statement = conn.createStatement();

                //3.  use the statement object to run the sql and return a ResultSet object
                ResultSet resultSet = statement.executeQuery(sql);
        )
        {
            //4.  loop over the resultSet and data points for our PieChart.Data
            while (resultSet.next())
            {
                String Entity = resultSet.getString("Entity");
                int nuclear_weapons_stockpile = resultSet.getInt("nuclear_weapons_stockpile");
                pieChartData.add(new PieChart.Data(Entity,nuclear_weapons_stockpile));
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return pieChartData;
    }
}
