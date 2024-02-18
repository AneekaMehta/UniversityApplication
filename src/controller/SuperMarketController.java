package controller;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Membership;
import model.SuperMarket;
import model.MMS;



public class SuperMarketController extends Controller<SuperMarket> {
    @FXML 
    private TextField name;
    @FXML
    private TextField email;
    @FXML
    private TableView<Membership> MembershipsTv;
    @FXML
    private Button delete;
    @FXML
    private Button select;
    @FXML
    private Button slip;
    
    public final SuperMarket getSuperMarket() {
        return model;
    }
    
    private String getName() {
        return name.getText();
    }
    
    private String getEmail() {
        return email.getText();
    }
    
    private Membership getSelectedMembership() {
        return MembershipsTv.getSelectionModel().getSelectedItem();
    }
    
    @FXML
    private void initialize() {
       name.textProperty().addListener(Event -> getSuperMarket().filterList(getName(), "0"));
       email.textProperty().addListener(Event -> getSuperMarket().filterList("0", getEmail()));
       MembershipsTv.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> delete.setDisable(newValue == null));
       MembershipsTv.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> select.setDisable(newValue == null));
       MembershipsTv.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> slip.setDisable(newValue == null));
       MembershipsTv.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }
    
    
    @FXML
    private void handleAdd() throws Exception{
        Membership Membership = new Membership("", "", "", "", "", 0.0);
        Stage addStage = new Stage();
        addStage.getIcons().add(new Image("/view/edit.png"));
        model.addMembership(Membership);
        ViewLoader.showStage(Membership, "/view/Membership.fxml", "Adding New Membership", addStage);
    }
    
    @FXML
    private void handleDelete() {
        model.removeMembership(MembershipsTv.getSelectionModel().getSelectedItem());
    }
    
    @FXML
    private void handleSelect(ActionEvent event) throws Exception{
        Stage selectStage = new Stage();
        selectStage.getIcons().add(new Image("/view/edit.png")); 
        ViewLoader.showStage(getSelectedMembership(), "/view/Membership.fxml", "Accessing File: " + getSelectedMembership().getName(), selectStage);
    }
    
    @FXML
    private void handleSlip(ActionEvent event) throws Exception{
        Image icon = new Image(getClass().getResourceAsStream("/view/edit.png"));
        Stage slipStage = new Stage();
        slipStage.getIcons().add(icon); 
        ViewLoader.showStage(getSelectedMembership(), "/view/slip.fxml", getSelectedMembership().getName() + " SLIP Report", slipStage);
    }
    
    @FXML
    private void handleReport(ActionEvent event) throws Exception{
        Stage reportStage = new Stage();
        reportStage.getIcons().add(new Image("/view/uts.jpeg")); 
        ViewLoader.showStage(new MMS(model), "/view/mms.fxml", "MMS Report", reportStage);
    }

    
    @FXML
    private void handleClose(ActionEvent event) {
        stage.close();
    }
}
