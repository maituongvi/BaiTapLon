/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlykhachsan;

import QLKS.pojo.KhachHang;
import java.net.URL;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import quanlykhachsan.HibernateUtil;

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
    SessionFactory factory = HibernateUtil.getSessionFactory();
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
    @FXML
    private TextField txtTimKiem;
    @FXML
    private Button btnCapNhat;
    @FXML
    private Button btnXoa;
    @Override
    
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
        
        comboBox.setItems(list);
        
        this.colName.setCellValueFactory(new PropertyValueFactory("tenKH"));
        this.colGender.setCellValueFactory(new PropertyValueFactory("gioiTinh"));
        this.colDateOfBirth.setCellValueFactory(new PropertyValueFactory("ngaySinh"));
        this.colPhone.setCellValueFactory(new PropertyValueFactory("sdt"));
        
        this.btnCapNhat.setVisible(false);
        this.txtTimKiem.textProperty().addListener(et -> {
            List<KhachHang> kh = Utils.laydsKH(this.txtTimKiem.getText(), 0);
            reloadTableView(kh);
        });
        
        // bắt sự kiện click 1 dòng trên TableView
        this.tbKhachHang.setRowFactory((TableView<KhachHang> param) -> {
            TableRow row = new TableRow();
            
            row.setOnMouseClicked((MouseEvent et) -> {
                this.btnCapNhat.setVisible(true);
                
                KhachHang kh = this.tbKhachHang.getSelectionModel().getSelectedItem();
                this.name.setText(kh.getTenKH());
                this.comboBox.getSelectionModel().select(kh.getGioiTinh());
                this.phone.setText(kh.getSdt());
                LocalDate day =kh.getNgaySinh().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                this.dateOfBirth.setValue(day);
               
            });
            
            return row;
        });
        
    }    
    
    public void Them(ActionEvent event){
        this.kiemTraNhapHopLe();
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
            reloadTableView(Utils.laydsKH("", 0));
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

    //Khi click button watching
    public void loadDSKhachHang(){
        this.tbKhachHang.getItems().clear();
        List<KhachHang> kh = Utils.laydsKH("", 0);
        this.tbKhachHang.getItems().addAll(kh);
    }
    
    
    // Khi click button update
    public void capNhat(){
        KhachHang kh = this.tbKhachHang.getSelectionModel().getSelectedItem();
        if(kh == null){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Chú ý");
            a.setContentText(" Bạn chưa chọn khách hàng để cập nhật.");
            a.show();
            return;
        }
        kh.setTenKH(this.name.getText());
        kh.setGioiTinh((String) comboBox.getValue());
        kh.setNgaySinh(Date.from(this.dateOfBirth.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        kh.setSdt(this.phone.getText());
        
        Utils.CapNhatKhachHang(kh);
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Thông báo");
        a.setContentText("Cập nhật khách hàng thành công.");
        a.show();
        
        reloadTableView(Utils.laydsKH("", 0));
    }
    
   
    
    // hàm kiểm tra nhập số điện thoại hợp lệ
    public static boolean checkSDT(String s){
        boolean check = false;
        Pattern pattern = Pattern.compile("^0\\d{9}$");
        Matcher mat = pattern.matcher(s);
        if(mat.find()){
            check = true;
        }
        return check;
    }
    
    // hàm kiểm tra nhập trên khách hàng hợp lệ
    public static boolean checkNhapTenKhachHang(String s){
        boolean check = false;
        Pattern pattern = Pattern.compile("^[a-zA-z\\s\\p{L}]{3,40}$");
        Matcher mat = pattern.matcher(s);
        if(mat.find()){
            check = true;
        }
        return check;
    }
    
    
    
    public void deleteRowOnTable(ActionEvent event){
        btnXoa.setOnAction(et->{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Bạn chắc chưa?");
            alert.showAndWait().ifPresent(rs ->{
                if(rs == ButtonType.OK){
                    KhachHang kh = this.tbKhachHang.getSelectionModel().getSelectedItem();
                    Utils.xoaKhachHang(kh);
                    reloadTableView(Utils.laydsKH("", 0));
                }
               
            });
                
        });
    }
    
    
    private void reloadTableView(List<KhachHang> khachHang) {
        this.tbKhachHang.getItems().clear();
        this.tbKhachHang.getItems().addAll(khachHang);
    }

    private Object toInstant() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void kiemTraNhapHopLe(){
        if(checkNhapTenKhachHang(name.getText()) == false){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Lỗi nhập !!! ");
            a.setContentText(" Vui lòng nhập lại tên hợp lệ.");
            a.show();
            return;
        }
        
        
        if(checkSDT(phone.getText()) == false){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Lỗi nhập !!! ");
            a.setContentText(" Vui lòng nhập lại số điện thoại hợp lệ. VD:0356847078");
            a.show();
            return;
        }
       
    }
    
 
   

}
