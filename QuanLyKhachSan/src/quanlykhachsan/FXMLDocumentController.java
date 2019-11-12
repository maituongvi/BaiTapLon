/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlykhachsan;

import QLKS.pojo.TaiKhoan;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import quanlykhachsan.HibernateUtil;


/**
 *
 * @author HP
 */
public class FXMLDocumentController implements Initializable {
    @FXML
    private AnchorPane base;
    
    @FXML
    private TextField taiKhoan;
    
    @FXML 
    private TextField matKhau;
    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    } 
    
    public void dangNhap(ActionEvent event) {
       SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Query q = session.createQuery("from TaiKhoan");
        List<TaiKhoan> rs = q.list();
//        rs.forEach(e->{
//            System.out.println(e.getIdNhanVien());
//            System.out.println(e.getTaiKhoan());
//            System.out.println(e.getMatKhau());
//        });
   
        rs.forEach(e-> {
           if(e.getTaiKhoan().equals(taiKhoan.getText())  && e.getMatKhau().equals(matKhau.getText()))
           {
                 
               try {
                    Parent login = FXMLLoader.load(getClass().getResource("GiaoDienNhanVien.fxml"));
                    Scene loginScene = new Scene(login);

                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setScene(loginScene);
                    window.show();
               } catch (IOException ex) {
                   System.out.println(ex.getMessage());
               }
           }
        });
        
        session.close();
    }
    
    public void login(ActionEvent event) throws IOException{
        Parent login = FXMLLoader.load(getClass().getResource("DangNhap.fxml"));
        Scene loginScene = new Scene(login);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.show();
    }
    
    
   
    
}
