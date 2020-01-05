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
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class GiaoDienNhanVienController implements Initializable {

    @FXML
    private Button btnPhong;
    @FXML
    private Button btnKH;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void khachHang(ActionEvent event) throws IOException{
        Parent login = FXMLLoader.load(getClass().getResource("NhapKhachHang.fxml"));
        Scene loginScene = new Scene(login);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.show();
    }
    
    public void phong(ActionEvent event) throws IOException{
        Parent login = FXMLLoader.load(getClass().getResource("TimKiemPhong.fxml"));
        Scene loginScene = new Scene(login);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.show();
    }
    
    // load giao dien nhân viên
    public void nhanVien(ActionEvent event) throws IOException{
        Parent login = FXMLLoader.load(getClass().getResource("ThongTinNhanVien.fxml"));
        Scene loginScene = new Scene(login);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.show();
    }
    
    // load giao dien bieuDo
    public void bieuDo(ActionEvent event) throws IOException{
        Parent login = FXMLLoader.load(getClass().getResource("BieuDo.fxml"));
        Scene loginScene = new Scene(login);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.show();
    }
    
}
