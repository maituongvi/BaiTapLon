/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlykhachsan;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 *
 * @author HP
 */
public class FXMLDocumentController implements Initializable {
    @FXML
    private AnchorPane base;
    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    } 
    
    public void DangNhap(ActionEvent e) throws IOException{
        Parent login = FXMLLoader.load(getClass().getResource("DangNhap.fxml"));
        Scene loginScene = new Scene(login);
        
        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.show();
    }
   
    
}
