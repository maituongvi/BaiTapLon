/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlykhachsan;

import QLKS.pojo.NhanVien;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
    ObservableList<String> listCV =FXCollections.observableArrayList("Quản lý", "Tạp vụ","Tiếp tân","Thu ngân");
    @FXML
    private ComboBox cbGioiTinh;
    @FXML
    private ComboBox cbChucVu;
    @FXML
    private TableView<NhanVien> tbNhanVien;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cbGioiTinh.setItems(listGT);
        cbChucVu.setItems(listCV);
        this.loadDsNV();
    }  
    
    private void loadDsNV(){
        List<NhanVien> nhanvien = Utils.laydsNV("", 0);
        TableColumn clMaNV = new TableColumn("Mã NV");
        clMaNV.setCellValueFactory(new PropertyValueFactory("idnhanVien"));
        TableColumn clHo = new TableColumn("Họ");
        clHo.setCellValueFactory(new PropertyValueFactory("hoNV"));
        this.tbNhanVien.getColumns().addAll(clMaNV,clHo);
        this.tbNhanVien.getItems().addAll(nhanvien);
    }
    
}
