/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlykhachsan;


import QLKS.pojo.KhachHang;
import QLKS.pojo.NhanVien;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableRow;

import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ThongTinNhanVienController implements Initializable {

    /**
     * Initializes the controller class.
     */
    ObservableList<String> listGT =FXCollections.observableArrayList("Nam", "Nữ","Khác");
    ObservableList<String> listCV =FXCollections.observableArrayList("Quản lý",
                                                "Tạp vụ","Tiếp tân","Thu ngân");
    @FXML
    private ComboBox cbGioiTinh;
    @FXML
    private ComboBox cbChucVu;
    @FXML
    private TableView<NhanVien> tbNhanVien;
    @FXML
    private TableColumn clMaNV;
    @FXML
    private TableColumn clhoNV;
    @FXML
    private TableColumn cltenNV;
    @FXML
    private TableColumn clGioiTinh;
    @FXML
    private TableColumn clQueQuan;
    @FXML
    private TableColumn clNgaySinh;
    @FXML
    private TableColumn clNgayVaoLam;
    @FXML
    private TableColumn clChucVu;
    @FXML
    private TextField txtHoNV;
    @FXML
    private TextField txtTenNV;
    @FXML
    private TextField txtQueQuan;
    @FXML
    private DatePicker dpNgaySinh;
    @FXML
    private DatePicker dpNgayVaoLam;
    @FXML 
    private Button btnXoa;
    @FXML 
    private Button btnSua;
    @FXML
    private TextField txtTimKiem;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cbGioiTinh.setItems(listGT);
        cbChucVu.setItems(listCV);
        this.btnSua.setVisible(false);
        this.loadDsNV();
        
        this.tbNhanVien.setRowFactory((TableView<NhanVien> param) -> {
            TableRow row = new TableRow();
            
            row.setOnMouseClicked((MouseEvent et) -> {
                this.btnSua.setVisible(true);
                
                NhanVien nv = this.tbNhanVien.getSelectionModel().getSelectedItem();
                this.txtHoNV.setText(nv.getHoNV());
                this.cbGioiTinh.getSelectionModel().select(nv.getGioiTinh());
                this.cbChucVu.getSelectionModel().select(nv.getChucVu());
                this.txtTenNV.setText(nv.getTenNV());
                this.txtQueQuan.setText(nv.getQueQuan());
                LocalDate day = ((java.sql.Date) nv.getNgaySinh()).toLocalDate();
                this.dpNgaySinh.setValue(day);
                LocalDate nvl = ((java.sql.Date) nv.getNgayVaoLam()).toLocalDate();
                this.dpNgayVaoLam.setValue(nvl);
               
            });
            
            return row;
        });
        
        btnXoa.setOnAction(et->{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Bạn chắc chưa?");
            alert.showAndWait().ifPresent(rs ->{
                if(rs == ButtonType.OK){
                    NhanVien nv = this.tbNhanVien.getSelectionModel().getSelectedItem();
                    Utils.xoaNhanVien(nv);
                    
                }
            reloadTableView(Utils.laydsNV("", 0));
            this.cleanBangNhapThongTinNV();
               
            });
                
        });
        
        this.txtTimKiem.textProperty().addListener(et -> {
            List<NhanVien> nv = Utils.laydsNV(this.txtTimKiem.getText(), 0);
            reloadTableView(nv);
        });
    }  
    
    private void loadDsNV(){
        this.tbNhanVien.getItems().clear();
        clMaNV.setCellValueFactory(new PropertyValueFactory("idnhanVien"));
        clhoNV.setCellValueFactory(new PropertyValueFactory("hoNV"));
        cltenNV.setCellValueFactory(new PropertyValueFactory("tenNV"));
        clGioiTinh.setCellValueFactory(new PropertyValueFactory("gioiTinh"));
        clNgaySinh.setCellValueFactory(new PropertyValueFactory("ngaySinh"));
        clQueQuan.setCellValueFactory(new PropertyValueFactory("queQuan"));
        clNgayVaoLam.setCellValueFactory(new PropertyValueFactory("ngayVaoLam"));
        clChucVu.setCellValueFactory(new PropertyValueFactory("chucVu"));
        List<NhanVien> nhanvien = Utils.laydsNV("", 0);
        this.tbNhanVien.getItems().addAll(nhanvien);
   
    }
    
    public boolean checkNotEmpty(){
        boolean check = false;
        if (!txtHoNV.getText().isEmpty() || !txtTenNV.getText().isEmpty() || 
                !txtQueQuan.getText().isEmpty() || dpNgaySinh.getValue() != null
                || dpNgayVaoLam.getValue() != null || 
                cbGioiTinh.getSelectionModel().getSelectedItem() != null || 
                cbChucVu.getSelectionModel().getSelectedItem() != null)
            check = true;
        else 
            check = false;
        
        
        return check;
    }
    
    public void themNVHandler(){
        if(!checkNotEmpty()){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Lỗi nhập !!! ");
            a.setContentText("Vui lòng nhập đầy đủ thông tin nhân viên.");
            a.show();
            return;
        }
        
        NhapKhachHangController check = new NhapKhachHangController();
        if( !check.checkNhapTen(txtHoNV.getText()) || !check.checkNhapTen(txtTenNV.getText())){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Lỗi nhập !!! ");
            a.setContentText("Vui lòng nhập họ hoặc tên nhân viên hợp lệ!");
            a.show();
            return;
        }
        
        Date ngaySinh = Date.from(dpNgaySinh.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date ngayVaoLam = Date.from(dpNgayVaoLam.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        if( !check.checkNgayHopLe(ngaySinh) || !check.checkNgayHopLe(ngayVaoLam)){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Lỗi nhập !!! ");
            a.setContentText("Vui lòng nhập ngày hợp lệ!");
            a.show();
            return;
        }
        
        NhanVien nv = new NhanVien(txtHoNV.getText(), txtTenNV.getText(),
                txtQueQuan.getText(), ngaySinh, ngayVaoLam, (String)cbGioiTinh.getValue(),
                (String)cbChucVu.getValue());
        Utils.CapNhatNhanVien(nv);
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Thông báo");
        a.setContentText("Thêm nhân viên thành công");
        a.show();
        
        this.cleanBangNhapThongTinNV();
        this.reloadTableView(Utils.laydsNV("", 0));
    }
    
    private void reloadTableView(List<NhanVien> nhanvien) {
        this.tbNhanVien.getItems().clear();
        this.tbNhanVien.getItems().addAll(nhanvien);
    }
    
    public void cleanBangNhapThongTinNV(){
        this.txtHoNV.setText("");
        this.txtTenNV.setText("");
        this.txtQueQuan.setText("");
        this.cbChucVu.setValue(null);
        this.cbGioiTinh.setValue(null);
        this.dpNgaySinh.setValue(null);
        this.dpNgayVaoLam.setValue(null);
       
    }
    
    public void capNhatNV(){
        NhanVien nv = this.tbNhanVien.getSelectionModel().getSelectedItem();
        int id = nv.getIdnhanVien();
        if(nv == null){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Chú ý");
            a.setContentText(" Bạn chưa chọn nhân viên để cập nhật.");
            a.show();
            return;
        }
        
        if(!checkNotEmpty()){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Lỗi nhập !!! ");
            a.setContentText("Vui lòng nhập đầy đủ thông tin nhân viên.");
            a.show();
            return;
        }
        
        NhapKhachHangController check = new NhapKhachHangController();
        if( !check.checkNhapTen(txtHoNV.getText()) || !check.checkNhapTen(txtTenNV.getText())){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Lỗi nhập !!! ");
            a.setContentText("Vui lòng nhập họ hoặc tên nhân viên hợp lệ!");
            a.show();
            return;
        }
        
        Date ngaySinh = Date.from(dpNgaySinh.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date ngayVaoLam = Date.from(dpNgayVaoLam.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        if( !check.checkNgayHopLe(ngaySinh) || !check.checkNgayHopLe(ngayVaoLam)){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Lỗi nhập !!! ");
            a.setContentText("Vui lòng nhập ngày hợp lệ!");
            a.show();
            return;
        }
        
        nv = new NhanVien(id, txtHoNV.getText(), txtTenNV.getText(),
                txtQueQuan.getText(), ngaySinh, ngayVaoLam, (String)cbGioiTinh.getValue(),
                (String)cbChucVu.getValue());
        Utils.CapNhatNhanVien(nv);
        this.cleanBangNhapThongTinNV();
        this.reloadTableView(Utils.laydsNV("", 0));
    }
    
    public void troVe(ActionEvent event) throws IOException{
        Parent login = FXMLLoader.load(getClass().getResource("GiaoDienNhanVien.fxml"));
        Scene loginScene = new Scene(login);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.show();
    }
}
