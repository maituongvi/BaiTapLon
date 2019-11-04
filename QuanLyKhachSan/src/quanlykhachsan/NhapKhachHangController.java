/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlykhachsan;

import QLKS.pojo.KhachHang;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class NhapKhachHangController implements Initializable {

    /**
     * Initializes the controller class.
     */
    ObservableList<String> list =FXCollections.observableArrayList("Nam", "Nữ","Khác");
    @FXML
    private ComboBox comboBox ;
    @FXML
    private TextField name;
    @FXML
    private TextField phone;
    @FXML 
    private DatePicker dateOfBirth;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        comboBox.setItems(list);
    }    
    
    public void Them(ActionEvent event){
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        
        Transaction trans = session.beginTransaction();
        String id = UUID.randomUUID().toString();
        Date ns = Date.from(dateOfBirth.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        KhachHang k1 = new KhachHang(id, name.getText(),  ns,(String)comboBox.getValue() ,phone.getText());
//        KhachHang k = new KhachHang(id,name.getText(), dateOfBirth.getValue(), 
//                comboBox.getAccessibleText(),phone.getText());
        session.save(k1);
        trans.commit();
        session.close();
       
        
    }
}
