package controller;
import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.SuperMarket;
import model.Session;

public class MMSLoginController extends Controller<Session> {
    
    @FXML 
    private TextField email;
    @FXML 
    private PasswordField password;
    @FXML 
    private Label errorMessage;
    
    private String getEmail() {
        return email.getText();
    }
    
    private String getPassword() {
        return password.getText();
    }
    
    private void setEmail() {
        email.setText("");
    }
    
    private void setPassword() {
        password.setText("");
    }
    
    @FXML
    private void handleOk(ActionEvent event) throws Exception {
        SuperMarket superMarket = model.getSuperMarket(getEmail(), getPassword());
        if (superMarket == null) {
            errorMessage.setText("Incorrect login details");
            setEmail();
            setPassword();
        }
        else if(superMarket.login(getEmail(), getPassword())) {
            Stage adminStage = new Stage();
            adminStage.getIcons().add(new Image("/view/SuperMarket.png")); 
            ViewLoader.showStage(superMarket, "/view/SuperMarket.fxml", "Session Admin: " + superMarket.getName(), adminStage);
            stage.close();
        }
    }
    
    @FXML
    private void handleExit(ActionEvent event) {
        stage.close();
    }
}

