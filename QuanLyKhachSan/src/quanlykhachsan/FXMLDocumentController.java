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
import javafx.scene.control.Alert;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.hql.internal.ast.tree.Statement;
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
    
    public void dangNhap(ActionEvent event) throws IOException {
        String tk =taiKhoan.getText();
        String mk = matKhau.getText();
        
        if(!tk.isEmpty() && !mk.isEmpty()){
            if (login(tk, mk)) {
                Parent login = FXMLLoader.load(getClass().getResource("GiaoDienNhanVien.fxml"));
                Scene loginScene = new Scene(login);

                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(loginScene);
                window.show();
            }
            else {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Lỗi đăng nhập ");
                a.setContentText("Thông tin tài khoản hoặc mật khẩu không hợp lệ.\nVui lòng kiểm tra lại");
                a.show();
            }
        }
        else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Lỗi đăng nhập ");
            a.setContentText("Vui lòng nhập đầy đủ thông tin.");
            a.show();
        }
            
        //SessionFactory factory = HibernateUtil.getSessionFactory();
        //Session session = factory.openSession();
        //Query q = session.createQuery("from TaiKhoan");
        //List<TaiKhoan> rs = q.list();

        //rs.forEach(e-> {
        //   if(e.getTaiKhoan().equals(taiKhoan.getText())  && e.getMatKhau().equals(matKhau.getText()))
        //   {
                 
        //       try {
        //            Parent login = FXMLLoader.load(getClass().getResource("GiaoDienNhanVien.fxml"));
        //          Scene loginScene = new Scene(login);
        //
        //          Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //            window.setScene(loginScene);
        //            window.show();
        //       } catch (IOException ex) {
        //           System.out.println(ex.getMessage());
        //       }
        //   }
        //});
        
        //session.close();
    }
    
//    public void login(ActionEvent event) throws IOException{
//        Parent login = FXMLLoader.load(getClass().getResource("DangNhap.fxml"));
//        Scene loginScene = new Scene(login);
//
//        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        window.setScene(loginScene);
//        window.show();
//    }
    public static boolean login(String tk, String mk) {
        boolean check = false;
        
        if (!tk.isEmpty() && !mk.isEmpty()) {
            SessionFactory factory = HibernateUtil.getSessionFactory();
            Session session = factory.openSession();
            
            Query q = session.createQuery("FROM TaiKhoan a WHERE a.taiKhoan = :loc");
            q.setParameter("loc",tk );
            List<TaiKhoan> ds = q.list();
            
            if(!ds.isEmpty()){
                for(TaiKhoan e: ds)
                    if(e.getTaiKhoan().equals(tk)  && e.getMatKhau().equals(mk)){
                    check = true;
                }
            }
            else check = false;
//            Query q = session.createQuery("from TaiKhoan");
//             List<TaiKhoan> rs = q.list();
//            for( TaiKhoan e : rs){
//                if(e.getTaiKhoan().equals(tk)  && e.getMatKhau().equals(mk)){
//                    check = true;
//                }
//            }

            session.close();
        } else {
            check = false;
        }
        return check;
    }

    
   
    
}
