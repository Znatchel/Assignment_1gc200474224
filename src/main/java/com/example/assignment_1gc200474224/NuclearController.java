package com.example.assignment_1gc200474224;


import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;

import java.util.ResourceBundle;

public class NuclearController implements Initializable {


    @FXML
    private TableView<NuclearArms> tableView;


    @FXML
    private TableColumn<NuclearArms, Integer> amountOfWeapons;

    @FXML
    private TableColumn<NuclearArms, String> country;

    @FXML
    private TableColumn<NuclearArms, String> countryCode;

    @FXML
    private TableColumn<NuclearArms, Integer> year;

    @FXML
    private TableColumn<NuclearArms, Integer> id;

    @FXML
    private Label totalNukesLabel;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Here");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        year.setCellValueFactory(new PropertyValueFactory<>("year"));
        countryCode.setCellValueFactory(new PropertyValueFactory<>("countryCode"));
        country.setCellValueFactory(new PropertyValueFactory<>("country"));
        amountOfWeapons.setCellValueFactory(new PropertyValueFactory<>("amountOfWeapons"));

        System.out.println(DBUtility.getNukesFromDB().size());
        tableView.getItems().addAll(DBUtility.getNukesFromDB());


    }
}