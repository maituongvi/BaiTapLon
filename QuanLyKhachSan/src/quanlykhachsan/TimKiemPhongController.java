/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlykhachsan;
import QLKS.pojo.ChiTietHoaDon;
import QLKS.pojo.KhachHang;
import QLKS.pojo.LoaiPhong;
import QLKS.pojo.Phong;
import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import static quanlykhachsan.NhapKhachHangController.checkNhapTenKhachHang;
import static quanlykhachsan.NhapKhachHangController.checkSDT;
import static quanlykhachsan.Utils.dem;
import static quanlykhachsan.Utils.noiDung;
import static quanlykhachsan.Utils.tieuDe;
import static quanlykhachsan.Utils.traloaiPhong;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class TimKiemPhongController implements Initializable {
    SessionFactory factory = HibernateUtil.getSessionFactory();
    ObservableList<String> listLoaiP =FXCollections.observableArrayList("A", "B","C", "D");
    ObservableList<String> listNguoi =FXCollections.observableArrayList("1", "2","3", "4");
    @FXML 
    private ComboBox<String> cbLoaiP;
    @FXML 
    private TextField tfSoN;
//    @FXML 
//    private ComboBox<String> cbSoN;
    
    
    @FXML
    private RadioButton rbBusy;
    @FXML
    private RadioButton rbAv;
    
    @FXML 
    private TableView<Phong> tbPhong;
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
    private TableColumn tcStartDay;
    @FXML
    private TableColumn tcEndDay;
    @FXML
    private Button btnCapNhat;
    @FXML 
    private TextField tfMa;
    @FXML 
    private TextField tfSucChua;
    @FXML 
    private ComboBox<String> cbLoai;
    @FXML
    private RadioButton rdT;
    @FXML
    private RadioButton rdF;
    @FXML
    private TextField tfgia;
    
    @FXML
    private Button btnXoa;
    @FXML
    private Button btnBook;
    
    @FXML
    private DatePicker dbStart;
    @FXML
    private DatePicker dbEnd;
    
    @FXML 
    private Label lbId;
    @FXML 
    private Label lbNu;
    @FXML 
    private Label lbTy;
    @FXML
    private Label lbPri;
    
    public int i = 0;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        ToggleGroup group = new ToggleGroup();
        rbAv.setToggleGroup(group);
        rbBusy.setToggleGroup(group);
//      rbAv.setSelected(true);
        
        ToggleGroup groupHT = new ToggleGroup();
        rdT.setToggleGroup(groupHT);
        rdF.setToggleGroup(groupHT);
        this.tcType.setCellValueFactory((new PropertyValueFactory("loaiPhong")));
        this.tcNumberP.setCellValueFactory(new PropertyValueFactory("sucChua"));
        this.tcNumberR.setCellValueFactory(new PropertyValueFactory("maPhong"));
        this.tcPrice.setCellValueFactory(new PropertyValueFactory("giaP"));
        this.tcStatus.setCellValueFactory(new PropertyValueFactory("tinhTrangP"));
        this.tcStartDay.setCellValueFactory(new PropertyValueFactory("ngayDen"));
        this.tcEndDay.setCellValueFactory(new PropertyValueFactory("ngayDi"));
        cbLoaiP.setItems(listLoaiP);
        cbLoai.setItems(listLoaiP);
        this.btnBook.setVisible(false);
        //kich lay mot thong tin khach hang
        tfSucChua.clear();
        cbLoai.getSelectionModel().clearSelection();
        tfgia.clear();
        rdT.setSelected(false);
        rdF.setSelected(false);
        tfMa.clear();
        this.tbPhong.setRowFactory((TableView<Phong> param) -> {
            TableRow row = new TableRow();
            
            row.setOnMouseClicked(et -> {
//                this.btnCapNhat.setVisible(true);
                
                Utils.ph = this.tbPhong.getSelectionModel().getSelectedItem();
                this.tfMa.setText(String.format("%d",Utils.ph.getMaPhong()));
                this.tfSucChua.setText(String.format("%d",Utils.ph.getSucChua()));
                this.cbLoai.getSelectionModel().select(Utils.ph.getLoaiPhong());
                this.tfgia.setText(String.format("%2.0f",Utils.ph.getGiaPhong()));
                this.btnBook.setVisible(true);
                
                if ("Đã đặt".equals(Utils.ph.getTinhTrangP()) == true){
                    rdF.setSelected(true);
                    
                }
                else{
                    rdT.setSelected(true);
                    
                    
                    
            
                }
                      
                
            });
//            try {
//                    loadSua();
//                } catch (IOException ex) {
//                    Logger.getLogger(TimKiemPhongController.class.getName()).log(Level.SEVERE, null, ex);
//                }
            
            return row;
        });
        
    }
    
    
    
    //Tim kiem phong voi 3 du kien
    public void timKiemPhongHandler(ActionEvent e) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        String loai = cbLoaiP.getSelectionModel().getSelectedItem();
        int loaiP = traloaiPhong(loai);
        String nguoi = tfSoN.getText();
        
    
        
        int tinhTrang = -1;
        if (rbAv.isSelected())
            tinhTrang = 0;
        else
            if (rbBusy.isSelected())
                tinhTrang = 1;
            else
                tinhTrang = -1;
        
        this.tbPhong.getItems().clear();
        if  (!tfSoN.getText().isEmpty() && Utils.checkSoNguoi(nguoi) == false)
        {
            a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Nhắc nhở");
            a.setContentText(" Nhập sai số người không hợp lệ (số người là số nguyên và nhỏ hơn 10^10!");
            a.show();
            
        }
        else {
            List<Phong> ph = Utils.laydsPhong(loai, nguoi, tinhTrang);
            this.tbPhong.getItems().addAll(ph);
            if (tbPhong.getItems().isEmpty())
                {
                    
                    a = new Alert(Alert.AlertType.INFORMATION);
                    a.setTitle(Utils.tieuDe);
                    a.setContentText(Utils.noiDung);
                    a.show();
                }tfMa.clear();
            tfSoN.clear();
            tfSucChua.clear();
            tfgia.clear();
            cbLoaiP.setValue("");
            cbLoai.setValue("");
            tfSucChua.clear();
            rdF.setSelected(false);
            rdF.setSelected(false);
            rbAv.setSelected(false);
            rbBusy.setSelected(false);
            
//            if (Utils.dem == -1){
//                a = new Alert(Alert.AlertType.INFORMATION);
//                a.setTitle(Utils.tieuDe);
//                a.setContentText(Utils.tieuDe);
//                a.show();
//            }
            
            
        }
        
        
        

         
        
//        if (tbPhong.getItems().isEmpty())
//                    {
//                        a.setTitle("Thông báo ");
//                        switch (tinhTrang) {
//                            case 0:
//                                a.setContentText(" Hết phòng trống!");
//                                a.show();
//                                break;
//                            case 1:
//                                a.setContentText(" Chưa có phòng đã đặt!");
//                                a.show();
//                                break;
//                            case -1:
//                                a = new Alert(Alert.AlertType.ERROR);
//                                a.setTitle("Nhắc nhở ");
//                                a.setContentText(" Bạn cần nhập dữ liệu!");
//                                a.show();
//                                break;
//                           
//                            default:
//                                a.setContentText(" Không có phòng nào đã được đặt!");
//                                a.show();
//                                break;
//                        }
//                    }
//       
//        if (tbPhong.getItems().isEmpty() && (tinhTrang != -1 || tinhTrang == -2 )&& ("".equals(nguoi) && loaiP == 0))
//        {
//            a.setTitle("Thông báo ");
//            a.setContentText(" Không tồn tại phòng bạn muốn tìm!");
//            a.show();
//        }
        
    }
    
    public void loadAllPhong(String ma, String sucChua, int tinhT){
        this.tbPhong.getItems().clear();
        List<Phong> ph = Utils.laydsPhong(ma, sucChua, tinhT);
        tfMa.clear();
        tfSoN.clear();
        tfSucChua.clear();
        tfgia.clear();
        cbLoaiP.setValue("");
        cbLoai.setValue("");
        tfSucChua.clear();
        rdF.setSelected(false);
        rdF.setSelected(false);
        rbAv.setSelected(false);
        rbBusy.setSelected(false);
        this.tbPhong.getItems().addAll(ph);
    }
    
    //hiện tất cả
    public void hienAllHandler(ActionEvent event){
        
        loadAllPhong("", "", -2);
    }
    
    
    //Đóng app
    public void closeHandler(ActionEvent event) throws IOException{
//        Phong ph= this.tbPhong.getSelectionModel().getSelectedItem();
////        this.lbId.setText(String.format("%d",ph.getMaPhong()));
//        this.lbId.setText(tfMa.getText());
//        this.lbNu.setText(tfSucChua.getText());
//        this.lbPri.setText(tfgia.getText());
////        this.lbNu.setText(String.format("%d",ph.getSucChua()));
//        this.lbTy.setText(ph.getLoaiPhong());
////        this.lbPri.setText(String.format("%2.0f",ph.getGiaPhong()));
        Parent root = FXMLLoader.load(getClass().getResource("TimKiemPhong.fxml"));
        
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.close();
        
    }
    
    //thêm phòng
     public void ThemHandler(ActionEvent event){
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        String soNguoi = tfSucChua.getText();
        String loai = (String)cbLoai.getSelectionModel().getSelectedItem();
        String gia = tfgia.getText();
        boolean t = rdT.isSelected();
        boolean f = rdF.isSelected();
        String ma = tfMa.getText();
      
        boolean tp = themPhong(ma,soNguoi, loai, gia, t, f);
        if (tp == true){
            a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Kết quả ");
            a.setContentText(Utils.content);
            a.show();
            loadAllPhong("", "", -2);
        }
        else{
            a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Kết quả ");
            a.setContentText(Utils.content);
            a.show();
        }
        
    }

     //Thêm phòng
     public boolean themPhong(String ma, String soNguoi,String loai, String gia, boolean t, boolean f ){
        Session session = factory.openSession();
        Criteria cr =session.createCriteria(Phong.class);
        
        boolean check = true;
        if (Utils.kiemTraNhapPhong(ma, soNguoi,loai, gia, t, f) == false){
            Utils.content = Utils.checkNhap;
            check = false;
            
        }
        else{
            Transaction trans =null;
            Criterion m =  Restrictions.eq("maPhong", Integer.parseInt(ma));
            if (!cr.add(Restrictions.or(m)).list().isEmpty()){
//                a = new Alert(Alert.AlertType.ERROR);
//                a.setTitle("Kết quả ");
//                a.setContentText(" Số phòng bạn nhập đã tồn tại ");
//                a.show();
                  Utils.content = " Số phòng bạn nhập đã tồn tại ";
                check = false;
            }
            else{
                try {
               trans = session.beginTransaction();
               int tinhTP = 0;
               if (t)
                   tinhTP = 1;
               int loaiP = Utils.traloaiPhong(loai);
               Phong ph = new Phong(Integer.parseInt(ma), Integer.parseInt(soNguoi),new LoaiPhong(loaiP, loai),  Double.parseDouble(gia) ,tinhTP);

               session.save(ph);
               trans.commit();

               // Xuất thông báo thành công 
//               a = new Alert(Alert.AlertType.INFORMATION);
//               a.setTitle("Kết quả ");
//               a.setContentText(" Thêm khách hàng thành công");
//               a.show();
               Utils.content = " Thêm khách hàng thành công";
               check = true;
               loadAllPhong("", "", -2);
            } catch (Exception e) {
                if (trans == null)
                    trans.rollback();
 //               a = new Alert(Alert.AlertType.ERROR);
 //               a.setTitle("Kết quả ");
 //               a.setContentText(" Thêm khách hàng thất bại");
 //               a.show();
                 Utils.content = " Thêm khách hàng thất bại";
                check = false;
            } finally{
                session.close();
            }
            }
        }
        
        
       
        return check;
     }
     
     //Hiện đặt phòng
     public void hienDatPhongHandler(ActionEvent event) throws IOException{
         Parent root = FXMLLoader.load(getClass().getResource("BookRoom.fxml"));
        
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
     }
     
   
     
     
     
      //cap nhap phòng
     public void CapNhatHandler(ActionEvent event){
        String soNguoi = tfSucChua.getText();
        String loai = (String)cbLoai.getSelectionModel().getSelectedItem();
        String gia = tfgia.getText();
        boolean t = rdT.isSelected();
        boolean f = rdF.isSelected();
        String ma = tfMa.getText();
        if (Utils.kiemTraNhapPhong(ma, soNguoi,loai, gia, t, f) == false)
        {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Chú ý");
            a.setContentText(Utils.checkNhap);
            a.show();
        }
        else {
            Phong ph = this.tbPhong.getSelectionModel().getSelectedItem();
            if(ph == null){
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Chú ý");
                a.setContentText(" Bạn chưa chọn phòng để cập nhật.");
                a.show();
            }
            else
            {
                int tinhTP = 1;
                if (t == true)
                    tinhTP = 0;
                int loaiP = Utils.traloaiPhong(loai);
                ph.setSucChua(Integer.parseInt(soNguoi));
                ph.setGiaP(Double.parseDouble(gia)); 
                ph.setTinhTrangP(tinhTP);
                ph.setLoaiPhong(new LoaiPhong(loaiP, loai));
                Utils.CapNhatPhong(ph);
                Utils.content = "Cập nhật khách hàng thành công.";
                loadAllPhong("", "", -2);
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setTitle("Thông báo");
                a.setContentText("Cập nhật khách hàng thành công.");
                a.show(); 
                
            }
               
         }
        
            
    }
     

    
    //Xoa mot dong
    public void deleteRowOnTable(ActionEvent event){
        if (tfMa.getText().isEmpty()){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Cảnh báo ");
            a.setContentText("Bạn chưa chọn phòng để xóa. ");
            a.show();
        } else {
            btnXoa.setOnAction(et->{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Bạn chắc chưa? ");
            alert.showAndWait().ifPresent(rs ->{
                if(rs == ButtonType.OK){
                    Phong ph = this.tbPhong.getSelectionModel().getSelectedItem();
                    Utils.xoaPhong(ph);
                    loadAllPhong("", "", -2);
                }
            });
        });
        }
    }
    
    
    //Quay về trang chủ
    public void trangChuHandler(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("TrangChu.fxml"));
        
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    
    //load đặt
    public void loadDatPhong() throws IOException{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("GiaoDienDP.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        
    }
    
   
    
}