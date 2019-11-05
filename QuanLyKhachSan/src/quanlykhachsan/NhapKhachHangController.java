/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlykhachsan;

import QLKS.pojo.KhachHang;
import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.Criteria;
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
    @FXML
    private TableColumn colName;
    @FXML
    private TableColumn colGender;
    @FXML
    private TableColumn colDateOfBirth;
    @FXML
    private TableColumn colPhone;
    @FXML
    private TableView<KhachHang> tbKhachHang;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        comboBox.setItems(list);
        
        
        
        
    }    
    
    public void Them(ActionEvent event){
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Transaction trans =null;
        
        try {
            trans = session.beginTransaction();
            String id = UUID.randomUUID().toString();
            Date ns = Date.from(dateOfBirth.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            KhachHang k1 = new KhachHang(id, name.getText(),  ns,(String)comboBox.getValue() ,phone.getText());
            session.save(k1);
            trans.commit();
            
            // Xuất thông báo thành công 
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Kết quả ");
            a.setContentText(" Thêm khách hàng thành công");
            a.show();
        } catch (Exception e) {
            if (trans == null)
                trans.rollback();
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Kết quả ");
            a.setContentText(" Thêm khách hàng thất bại");
            a.show();
        } finally{
            
            session.close();
        }
        
       
        
    }
    
    public ObservableList<KhachHang> getKhachHang(){
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Criteria cr = session.createCriteria(KhachHang.class);
        List<KhachHang> kh = cr.list();
        
        return FXCollections.observableArrayList(kh);
    }
    
    public void loadDSKhachHang(){
        this.colName.setCellValueFactory(new PropertyValueFactory("tenKH"));
        this.colGender.setCellValueFactory(new PropertyValueFactory("gioiTinh"));
        this.colDateOfBirth.setCellValueFactory(new PropertyValueFactory("ngaySinh"));
        this.colPhone.setCellValueFactory(new PropertyValueFactory("sdt"));
        this.tbKhachHang.setItems(this.getKhachHang());
    }
    

}
