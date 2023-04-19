package com.shototechnologies.edumange.controller;

import com.shototechnologies.edumange.db.Database;
import com.shototechnologies.edumange.model.Program;
import com.shototechnologies.edumange.model.Student;
import com.shototechnologies.edumange.model.Teacher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ProgramsFormController {

    public AnchorPane context;


    public TextField txtCode;


    public TextField txtName;


    public TextField txtSearch;


    public Button btn;


    public TableView<?> tblPrograms;


    public TableColumn<?, ?> colId;


    public TableColumn<?, ?> colName;


    public TableColumn<?, ?> colTeacher;


    public TableColumn<?, ?> colTechnologies;


    public TableColumn<?, ?> colCost;


    public TableColumn<?, ?> colOption;


    public TextField txtContact;


    public ComboBox<String> cmbTeacher;


    public TextField txtTechnology;


    public TableView<?> tblTechnology;


    public TableColumn<?, ?> colTCode;


    public TableColumn<?, ?> colTName;


    public TableColumn<?, ?> colTRemove;

    public void initialize(){
        setProgramCode();
        setTeachers();

    }

    ArrayList<String> teachersArray=new ArrayList<>();

    private void setTeachers() {
        for (Teacher t:Database.teacherTable
             ) {
            teachersArray.add(t.getCode()+". "+t.getName());

        }
        ObservableList<String> obList = FXCollections.observableArrayList(teachersArray);
        cmbTeacher.setItems(obList);
    }

    private void setProgramCode() {
        if (!Database.programTable.isEmpty()){
            Program lastProgram = Database.programTable.get(
                    Database.programTable.size()-1
            );
            String lastId= lastProgram.getCode();
            String splitData[]=lastId.split("-");
            String lastIdIntegerNumberAsAString=splitData[1];
            int lastIntegerIdAsInt=Integer.parseInt(lastIdIntegerNumberAsAString);
            lastIntegerIdAsInt++;
            String generatedStudentId="P-"+lastIntegerIdAsInt;
            txtCode.setText(generatedStudentId);

        }else {
            txtCode.setText("P-1");
        }

    }


    public void backToHomeOnAction(ActionEvent event) throws IOException {
        setUi("DashboardForm");

    }


    public void newProgramOnAction(ActionEvent event) {

    }


    public void saveOnAction(ActionEvent event) {

    }

    private void setUi(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(
                new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.centerOnScreen();

    }

}
