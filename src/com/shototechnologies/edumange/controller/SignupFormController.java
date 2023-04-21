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

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SignupFormController {
    public AnchorPane context;
    public TextField txtFirstname;
    public PasswordField txtPassword;
    public TextField txtLastname;
    public TextField txtEmail;



    public void alreadyHaveAnAccountOnAction(ActionEvent actionEvent) throws IOException {
        setUi("LoginForm");
    }
    private void setUi(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(
                new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.centerOnScreen();

    }
    public void signUpOnAction(ActionEvent actionEvent) throws IOException {
        String email = txtEmail.getText().toLowerCase();
        String firstname=txtFirstname.getText();
        String lastName=txtLastname.getText();


        String password= new PasswordManager().encrypt(txtPassword.getText().trim());


        User createUser = new User(firstname, lastName, email, password);
        try{
            boolean isSaved = signup(createUser);

            if(isSaved){
                new Alert(Alert.AlertType.INFORMATION,"Welcome").show();
                setUi("LoginForm");
            }else{
                new Alert(Alert.AlertType.WARNING,"Try Again!").show();
            }

        }catch(SQLException | ClassNotFoundException e1){
            new Alert(Alert.AlertType.ERROR,e1.toString()).show();
        }

    }

    private boolean signup(User user) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection=
        DriverManager.getConnection("jdbc:mysql://localhost:3306/lms","user","password");
        String sql="INSERT INTO User VALUES ('"+user.getEmail()+"','"+user.getFirstName()+"','"+user.getLastName()+"','"+user.getPassword()+"')";

        Statement statement=connection.createStatement();
        return statement.executeUpdate(sql)>0;
    }
}
