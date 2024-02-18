package controller;

import au.edu.uts.ap.javafx.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.scene.text.*;
import java.text.DecimalFormat;
import model.Membership;

public class MMSlipController extends Controller<Membership>{
    public final DecimalFormat df = new DecimalFormat("#.00");
    private Membership getMembership() {
        return model;
    }
    @FXML
    private Text totalCredits;
    @FXML
    private Text payPerCredits;
    @FXML
    private Text totalExpense;
    @FXML
    private Text dollarAvailable;
    @FXML
    private Text gasdeductionRate;
    @FXML
    private Text deductionRate;
    
    
    @FXML
    public void initialize() {
        //name.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        //type.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        //expCol.setCellValueFactory(cellData -> cellData.getValue().expenseProperty().asString("$%.2f"));
        totalCredits.setText(df.format(model.gettotalCredits()));
        payPerCredits.setText(df.format(model.getPayPerCredit()));
        //totalExpense.setText("$" + df.format(model.getexpense()));
        totalExpense.textProperty().bind(getMembership().expenseProperty().asString("$%.2f"));
        dollarAvailable.setText("$" + df.format(model.getDollarAvailable()));
        gasdeductionRate.setText("0" + df.format(model.getGasdeductionRate()));
        deductionRate.setText("0" + df.format(model.getdeductionRate()));
    }
    
    
    private String getTotalCredits() {
        return Double.toString(model.gettotalCredits());
    }
    
    private String getPayPerCredit() {
        return Double.toString(model.getPayPerCredit());
    }
    

    private String getDollarAvailable() {
        return Double.toString(model.getDollarAvailable());
    }
    
    private String getGasdeductionRate() {
        return Double.toString(model.getGasdeductionRate());
    }
    
    private String getDeductionRate() {
        return Double.toString(model.getdeductionRate());
    }
   
    @FXML
    public void handleClose(ActionEvent event) {
        stage.close();
    }
}
