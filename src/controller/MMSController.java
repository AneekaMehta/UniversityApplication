package controller;


import au.edu.uts.ap.javafx.Controller;
import java.text.DecimalFormat;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.text.Text;
import model.MMS;
import model.MMSreport;



public class MMSController extends Controller<MMS>{
    @FXML 
    private TableView<MMSreport> mmsTable;
    @FXML
    private TableColumn<MMSreport, String> Name;
    @FXML
    private TableColumn<MMSreport, String> Type;
    @FXML 
    private TableColumn<MMSreport, String> Expense;
    @FXML
    private Text totalExpense;
    @FXML 
    private TableColumn<MMSreport, String> Credits;
    @FXML
    private Text totalCredits;
    @FXML 
    private TableColumn<MMSreport, String> Gas;
    @FXML
    private Text avgGas;
    @FXML
    private TableColumn<MMSreport, String> Deduct;
    @FXML
    private Text avgDeduct;
    @FXML 
    private TableColumn<MMSreport, String> Pay;
    @FXML
    private Text avgPay;
    @FXML 
    private TableColumn<MMSreport, String> DollarAvailable;
    @FXML
    private Text totalDollarAvailable;
    
    public final DecimalFormat df = new DecimalFormat("#.00");
    
    public MMS getMMS() {
        return model;
    }
    
    @FXML 
    private void initialize() {
        mmsTable.setItems(model.reports());
        //your fx id.setItems(put the observable list here); (such as the returned data of the getmemberhips function)
        //fx:id of your first column.setCellValueFactory(cellData -> cellData.getValue().someProperty());
        mmsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        //Expense.setItems(model.reports().expense);
        Name.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        Type.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        Expense.setCellValueFactory(cellData -> cellData.getValue().expenseProperty().asString("$%.2f"));
        totalExpense.setText("$" + df.format(model.getTotalExpense()));
        Credits.setCellValueFactory(cellData -> cellData.getValue().totalCreditsProperty().asString("%.2f"));
        totalCredits.setText("$" + df.format(model.getTotalCredits()));
        Gas.setCellValueFactory(cellData -> cellData.getValue().GasdeductionRateProperty().asString("%.2f"));
        avgGas.setText("0"+ df.format(model.getAvgGasdeductionRate()));
        Deduct.setCellValueFactory(cellData -> cellData.getValue().deductionRateProperty().asString("%.2f"));
        avgDeduct.setText("0"+ df.format(model.getAvgdeductionRate()));
        Pay.setCellValueFactory(cellData -> cellData.getValue().PayPerCreditProperty().asString("%.2f"));
        avgPay.setText(df.format(model.getAvgPayPerCredit()));
        DollarAvailable.setCellValueFactory(cellData -> cellData.getValue().DollarAvailableProperty().asString("$%.2f"));
        totalDollarAvailable.setText("$" + df.format(model.getTotalDollarAvailable()));
    }
    
    @FXML 
    private void handleClose(ActionEvent event) throws Exception {
        stage.close();
    }
}
