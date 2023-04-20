package com.hms.hms_dashboard_01;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Collection;

import java.util.Collection;

public class RegisterController {

    @FXML
    private TextField userName;
    @FXML
    private PasswordField password;
    @FXML
    private Button registerButton;
//takes the username and password and adds it to the pending registrations list and opens the login page again
//    Needs to have a proper form that resembles the Student class
//    Needs to have an option to go back to the login page without registering

    public void register(ActionEvent event) throws IOException {
        PendingRegistration.pendingRegistrations.add(new Student(userName.getText(), password.getText()));
        Alert registered = new Alert(Alert.AlertType.INFORMATION);
        registered.setTitle("Registration");
        registered.setHeaderText("Registration Successful");
        registered.setContentText("Your account is pending approval");
        registered.showAndWait();

        Parent root = FXMLLoader.load(getClass().getResource("hms_login.fxml"));
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }


    public void backToLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hms_login.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
