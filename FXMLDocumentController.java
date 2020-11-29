/* 
TextField not returning text

*/
package warehousemanager;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.String;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import warehousemanager.WarehouseModel;

/**
 *
 * @author Cattc
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private Label labelQuantity;
    @FXML
    private ChoiceBox stockChoice;
    @FXML
    private TextField stockQuantity;
    @FXML
    private TextField userInput;
    @FXML
    private ObservableList<String> list = FXCollections.observableArrayList();
    
    private WarehouseModel modelClass;
    
    @FXML
    public void  handleButtonAction(ActionEvent event) {  // add new button
        modelClass.addNewVariety(userInput.getText());
        modelClass.addStock(userInput.getText(), 0);
        System.out.println(userInput.getText());
        stockChoice.getItems().add(userInput.getText());
    }
    
    @FXML 
    public void display()  {
        labelQuantity.setText(Integer.toString(modelClass.getStock(label.getText())));
    }
    
    @FXML
    public void displayClear() {
        labelQuantity.setText("");
    }
    
    @FXML
    private void add() {
        String variety = (String) stockChoice.getValue();
        int amount = Integer.parseInt(stockQuantity.getText());
        if (variety == null)
        {
            System.out.println("Please select a variety");
        }
        else {
        modelClass.addStock(variety,amount);
        System.out.println(variety +" " + modelClass.getStock(variety));
        }
        
    }
    
    @FXML
    private void remove()
    {
        String variety = (String) stockChoice.getValue();
        int amount = Integer.parseInt(stockQuantity.getText());
        if (variety == null)
        {
            System.out.println("Please select a variety");
        }
        else {
        modelClass.removeStock(variety,amount);
        System.out.println(variety +" " + modelClass.getStock(variety));
        }
    }
    
    @FXML
    private void saveQuit()
    {
        WarehouseModel save = new WarehouseModel();
        save.save();
        Platform.exit();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        modelClass = new WarehouseModel();
        stockChoice.getItems().addAll(modelClass.getAllVarieties());
        label.textProperty().bind(stockChoice.getSelectionModel().selectedItemProperty());
    }    
    
}
