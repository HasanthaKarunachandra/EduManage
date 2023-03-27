package com.shototechnologies.edumange.controller;

import com.shototechnologies.edumange.db.Database;
import com.shototechnologies.edumange.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SignupFormController {
    public AnchorPane context;
    public TextField txtFirstname;
    public PasswordField txtPassword;
    public TextField txtLastname;
    public TextField txtEmail;

    public void signUpOnAction(ActionEvent actionEvent) throws IOException {
        String email = txtEmail.getText().toLowerCase();
        String firstname=txtFirstname.getText();
        String lastName=txtLastname.getText();
        String password= txtPassword.getText().trim();
        Database.userTable.add(
                new User(firstname,lastName,email,password)
        );
        new Alert(Alert.AlertType.INFORMATION,"Welcome").show();
        setUi("LoginForm");

    }

    public void alreadyHaveAnAccountOnAction(ActionEvent actionEvent) throws IOException {
        setUi("LoginForm");
    }
    private void setUi(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(
                new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.centerOnScreen();

    }
}
