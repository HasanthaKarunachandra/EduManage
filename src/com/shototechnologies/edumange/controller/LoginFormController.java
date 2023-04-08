package com.shototechnologies.edumange.controller;

import com.shototechnologies.edumange.db.Database;
import com.shototechnologies.edumange.model.User;
import com.shototechnologies.edumange.util.security.PasswordManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.util.Optional;

public class LoginFormController {
    public AnchorPane context;
    public TextField txtEmail;
    public PasswordField txtPassword;

    public void loginOnAction(ActionEvent actionEvent) throws IOException {
        String email=txtEmail.getText().toLowerCase();
        String password=txtPassword.getText().trim();

        Optional<User>selectedUser= Database.userTable.stream().filter(e->e.getEmail().equals(email)).findFirst();
        if (selectedUser.isPresent()){
            if(new PasswordManager().checkPassword(password,selectedUser.get().getPassword())){
                System.out.println(selectedUser.get().toString());
                setUi("DashboardForm");
            }else{
                new Alert(Alert.AlertType.ERROR,
                        "Wrong Password!").show();
            }
        }else{
            new Alert(Alert.AlertType.WARNING,
                    String.format("User not found (%s)",email)).show();
        }
    }

    public void forgotPasswordOnAction(ActionEvent actionEvent) {
    }

    public void createAnAccountOnAction(ActionEvent actionEvent) throws IOException {
        setUi("SignupForm");
    }
    private void setUi(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(
                new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.centerOnScreen();

    }
}
