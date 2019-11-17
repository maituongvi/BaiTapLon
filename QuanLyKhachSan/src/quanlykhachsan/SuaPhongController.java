/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlykhachsan;
import QLKS.pojo.LoaiPhong;
import QLKS.pojo.Phong;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class SuaPhongController implements Initializable {
    SessionFactory factory = HibernateUtil.getSessionFactory();
    ObservableList<String> listLoaiP =FXCollections.observableArrayList("A", "B","C", "D");
    ObservableList<String> listNguoi =FXCollections.observableArrayList("1", "2","3", "4");
    @FXML 
    private ComboBox<String> cbLoaiP;
    @FXML 
    private TextField tfChucNang;
    @FXML 
    private ComboBox<String> cbSoN;
    @FXML
    ToggleGroup group = new ToggleGroup();
    @FXML
    private RadioButton rbBusyS;
    @FXML
    private RadioButton rbAvS;
    
    @FXML 
    private TableView tbKQ;
    @FXML
    private TableColumn tcType;
    @FXML
    private TableColumn tcNumberR;
    @FXML
    private TableColumn tcNumberP;
    @FXML
    private TableColumn tcPrice;
    @FXML
    private TableColumn tcStatus;
    
    @FXML
    public void timKiemHandler(ActionEvent e){
        timKiem();
    }
    
   

    
    private ObservableList<Phong> loadPhong() {
        
        
        Session session = factory.openSession();
        Criteria cr = session.createCriteria(Phong.class);
        Criteria cr1 = session.createCriteria(LoaiPhong.class);
        List<Phong> phongs = cr.list();
        session.close();
        Iterator iterator = phongs.iterator();
        
        return FXCollections.observableArrayList(phongs);
       
    }
    
    private void hienThiPhong() {
        
        
        this.tcType.setCellValueFactory((new PropertyValueFactory("loaiPhong")));
        this.tcNumberP.setCellValueFactory(new PropertyValueFactory("sucChua"));
        this.tcNumberR.setCellValueFactory(new PropertyValueFactory("maPhong"));
        this.tcPrice.setCellValueFactory(new PropertyValueFactory("giaP"));
        this.tcStatus.setCellValueFactory(new PropertyValueFactory("tinhTrangP"));
        
        this.tbKQ.setItems(loadPhong());
       
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         
        rbAvS.setToggleGroup(group);
        rbBusyS.setToggleGroup(group);
        rbAvS.setSelected(true);
//        cbLoaiP.setItems(listLoaiP);
//        cbSoN.setItems(listNguoi);
        
        
    }
    @FXML
    public void hienAllHandler(ActionEvent event){
        hienThiPhong();
    }
    @FXML
    public void trangChuHandler(ActionEvent event) throws IOException{
         Parent root = FXMLLoader.load(getClass().getResource("TrangChu.fxml"));
        
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    public void timKiem(){
        Session session = this.factory.openSession();
        String loai = cbLoaiP.getSelectionModel().getSelectedItem();
        Alert a = new Alert(Alert.AlertType.INFORMATION);
   
        int type = 0;
        String nguoi = cbSoN.getSelectionModel().getSelectedItem();
       if (loai != null){
           switch(loai){
                case "A":
                    type = 1;
                    break;
                case "B":
                    type = 2;
                    break;
                case "C":
                    type = 3;
                    break;
                case "D":
                    type = 4;
                    break;
        }
       }
        
        int tinhTrang = -1;
        if (rbAvS.isSelected())
            tinhTrang = 0;
        else
            tinhTrang = 1;
        
        
        Criteria cr = session.createCriteria(Phong.class);
//        int soNguoi = Integer.parseInt(nguoi);
//        Criterion soN = Restrictions.eq("sucChua", soNguoi);
//        Criterion tinhT = Restrictions.eq("tinhTrangP", tinhTrang);
        
       int soNguoi = 0;
        Criterion tinhT =  Restrictions.eq("tinhTrangP", tinhTrang);
        
        if (loai == null && nguoi == null || loai == "" && nguoi == ""){
            
           if (cr.add(Restrictions.or(tinhT)).list().isEmpty())
                    {
                        a.setTitle("Thông báo ");
                        if (tinhTrang == 0)
                            a.setContentText(" Hết phòng trống!");
                        else
                            a.setContentText(" Không có phòng nào đã được đặt!");
                        a.show();
                    }
           else
                cr.add(Restrictions.or(tinhT));
        }
        else 
            if (loai == null && nguoi != null)
            {  
                soNguoi = Integer.parseInt(nguoi);
                        Criterion soN = Restrictions.eq("sucChua", soNguoi);

                        cr.add(Restrictions.and(soN, tinhT));
                        
            }else 
                if (nguoi == null && loai != null ){
                        Criterion l =  Restrictions.eq("loaiPhong", new LoaiPhong(type, loai));

                     cr.add(Restrictions.and(l, tinhT));
                }
                else {
                    Criterion l =  Restrictions.eq("loaiPhong", new LoaiPhong(type, loai));
                    soNguoi = Integer.parseInt(nguoi);
                    Criterion soN = Restrictions.eq("sucChua", soNguoi);
                     cr.add(Restrictions.and(l, tinhT, soN));
                }
                
            
//            if (tinhT == null)
//            {
//                
//                a.setTitle("Thông báo ");
//                a.setContentText(" Không còn phòng trống!");
//                a.show();
//            }
//            else
//                cr.add(Restrictions.or(tinhT));
//        }else 
//            if (loai == "")
//        {
//            int soNguoi = Integer.parseInt(nguoi);
//            Criterion soN = Restrictions.eq("sucChua", soNguoi);
//            cr.add(Restrictions.and(soN, tinhT));
//        }else if (nguoi == ""){
//            
//            Criterion l =  Restrictions.eq("loaiPhong", new LoaiPhong(type, loai));
//            cr.add(Restrictions.or(tinhT, l));
//        }
//        else 
//        {
//            int soNguoi = Integer.parseInt(nguoi);
//            Criterion soN = Restrictions.eq("sucChua", soNguoi);
//            Criterion l =  Restrictions.eq("loaiPhong", new LoaiPhong(type, loai));
//            if (Restrictions.and(l, soN, tinhT) == null ){
////                Alert a = new Alert(Alert.AlertType.INFORMATION);
//                a.setTitle("Thông báo ");
//                a.setContentText(" Phòng bạn muốn tìm không tồn tại");
//                a.show();
//            
//            }else
//                cr.add(Restrictions.and(l, soN, tinhT));
//        }
        List<LoaiPhong> phongs = cr.list();
        if (!cr.list().isEmpty())
        {
        } else {
            a.setTitle("Thông báo ");
            a.setContentText(" Không tồn tại phòng bạn muốn tìm!");
            a.show();
        }
        session.close();
         
        this.tcType.setCellValueFactory((new PropertyValueFactory("loaiPhong")));
        this.tcNumberP.setCellValueFactory(new PropertyValueFactory("sucChua"));
        this.tcNumberR.setCellValueFactory(new PropertyValueFactory("maPhong"));
        this.tcPrice.setCellValueFactory(new PropertyValueFactory("giaP"));
        this.tcStatus.setCellValueFactory(new PropertyValueFactory("tinhTrangP"));
        this.tbKQ.setItems(FXCollections.observableArrayList(phongs));
//        }
    }
}
