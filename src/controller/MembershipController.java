package controller;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.SuperMarket;
import model.InputException;
import model.Membership;


public class MembershipController extends Controller<Membership>{
    @FXML 
    private TextField name;
    @FXML
    private TextField email;
    @FXML 
    private TextField phone;
    @FXML 
    private TextField address;
    @FXML 
    private TextField ID;
    @FXML 
    private TextField expense;
    @FXML 
    private Text type;
    @FXML 
    private Button update;
    @FXML
    private Button add;
    
    public Validator validator = new Validator();
    
    private Membership getMembership() {
        return model;
    }
    
    private SuperMarket getSuperMarket() {
        return model.getSuperMarket();
    }
    
    private String getName() {
        return name.getText();
    }
    
    private String getEmail() {
        return email.getText();
    }
    
    private String getPhone() {
        return phone.getText();
    }
    
    private String getAddress() {
        return address.getText();
    }
    
    private String getID() {
        return ID.getText();
    }
    
    private String getexpense() {
        return expense.getText();
    }
    
    
    private String getType() {
        return type.getText();
    }
    
    private String setErrorMessage() {
        ArrayList<String> errorsList;
        errorsList = new ArrayList<>();
        validator.clear();
        validator.generateErrors(getName(), getEmail(), getPhone(),getAddress(), getID(), Double.parseDouble(getexpense()));
        for (String error: validator.errors()) {
            errorsList.add(error);
        }
        String result = errorsList.toString();
        return result;
    }
    
    @FXML
    private void initialize() {
        if (getMembership().getName().equals("")) {
            name.setText(getMembership().getName());
            email.setText(getMembership().getEmail());
            phone.setText(getMembership().getPhone());
            address.setText(getMembership().getAddress());
            ID.setText(getMembership().getID());
            expense.setText("" + getMembership().getexpense());
            type.setText(getMembership().getType());
            update.setDisable(true);
        }
        else {
            name.setText(getMembership().getName());
            email.setText(getMembership().getEmail());
            phone.setText(getMembership().getPhone());
            address.setText(getMembership().getAddress());
            ID.setText(getMembership().getID());
            expense.setText("" + getMembership().getexpense());
            type.setText(getMembership().getType());
            add.setDisable(true);
        }
    }
    
    @FXML 
    private void handleAdd() throws Exception {
        boolean valid = validator.isValid(getName(), getEmail(), getPhone(),getAddress(), getID(), Double.parseDouble(getexpense()));
            if (valid) {
                getMembership().updateDetails(getName(), getEmail(), getPhone(), getAddress(), getID(), Double.parseDouble(getexpense()));
                stage.close();  
            }
            else {
                Stage errorStage = new Stage();
                errorStage.getIcons().add(new Image("/view/error.png")); 
                ViewLoader.showStage(new InputException(setErrorMessage()), "/view/error.fxml", "Input Exceptions", errorStage);
            }
}
    
    @FXML 
    private void handleUpdate() throws Exception{
        boolean valid = validator.isValid(getName(), getEmail(), getPhone(),getAddress(), getID(), Double.parseDouble(getexpense()));
            if (valid) {
                getMembership().updateDetails(getName(), getEmail(), getPhone(),getAddress(), getID(), Double.parseDouble(getexpense()));
                stage.close();  
            }
            else {
                validator.generateErrors(getName(), getEmail(), getPhone(),getAddress(), getID(), Double.parseDouble(getexpense()));
                Stage errorStage = new Stage();
                errorStage.getIcons().add(new Image("/view/error.png")); 
                ViewLoader.showStage(new InputException(setErrorMessage()), "/view/error.fxml", "Input Exceptions", errorStage);
            }
    }
    
    @FXML
    private void handleClose(ActionEvent event) {
        stage.close();
    }
}
