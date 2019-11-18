/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlykhachsan;
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

    public static String content = "";
    public static String checkNhap = "";
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
        
        cbLoaiP.setItems(listLoaiP);
        cbLoai.setItems(listLoaiP);
//        this.btnCapNhat.setVisible(false);
        //kich lay mot thong tin khach hang
        this.tbPhong.setRowFactory((TableView<Phong> param) -> {
            TableRow row = new TableRow();
            
            row.setOnMouseClicked(et -> {
//                this.btnCapNhat.setVisible(true);
                
                Phong ph= this.tbPhong.getSelectionModel().getSelectedItem();
                this.tfMa.setText(String.format("%d",ph.getMaPhong()));
                this.tfSucChua.setText(String.format("%d",ph.getSucChua()));
                this.cbLoai.getSelectionModel().select(ph.getLoaiPhong());
                this.tfgia.setText(String.format("%.2f",ph.getGiaP()));
                
                if ("Đã đặt".equals(ph.getTinhTrangP()) == true)
                    rdF.setSelected(true);
                else
                    rdT.setSelected(true);  
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
        if (!tfSoN.getText().isEmpty() || !"".equals(tfSoN.getText())){
           if (checkSoNguoi(tfSoN.getText()) == false){
            a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Chú ý");
            a.setContentText(" Số phòng là một số nguyên!");
            a.show();
            nguoi = "-1";
            }  
        }
    
        
        int tinhTrang = -1;
        if (rbAv.isSelected())
            tinhTrang = 0;
        else
            if (rbBusy.isSelected())
                tinhTrang = 1;
            else
                tinhTrang = -1;
        
        this.tbPhong.getItems().clear();
         
        if (!"-1".equals(nguoi)){
            List<Phong> ph = Utils.laydsPhong(loai, nguoi, tinhTrang);
            this.tbPhong.getItems().addAll(ph);
        }
        if (tbPhong.getItems().isEmpty())
                    {
                        a.setTitle("Thông báo ");
                        switch (tinhTrang) {
                            case 0:
                                a.setContentText(" Hết phòng trống!");
                                a.show();
                                break;
                            case -1:
                                a = new Alert(Alert.AlertType.ERROR);
                                a.setTitle("Nhắc nhở ");
                                a.setContentText(" Bạn cần nhập dữ liệu!");
                                a.show();
                                break;
                           
                            default:
                                a.setContentText(" Không có phòng nào đã được đặt!");
                                a.show();
                                break;
                        }
                    }
        if (tbPhong.getItems().isEmpty() && (tinhTrang != -1 || tinhTrang == -2 )&& ("".equals(nguoi) && loaiP == 0))
        {
            a.setTitle("Thông báo ");
            a.setContentText(" Không tồn tại phòng bạn muốn tìm!");
            a.show();
        }
        
    }
    
    public void loadAllPhong(String ma, String sucChua, int tinhT){
        this.tbPhong.getItems().clear();
        List<Phong> ph = Utils.laydsPhong(ma, sucChua, tinhT);
        this.tbPhong.getItems().addAll(ph);
    }
    
    //hiện tất cả
    public void hienAllHandler(ActionEvent event){
        loadAllPhong("", "", -2);
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
            a.setContentText(content);
            a.show();
            loadAllPhong("", "", -2);
        }
        else{
            a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Kết quả ");
            a.setContentText(content);
            a.show();
        }
        tfSucChua.clear();
        cbLoai.getSelectionModel().clearSelection();
        tfgia.clear();
        rdT.setSelected(false);
        rdF.setSelected(false);
        tfMa.clear();
    }

     //Thêm phòng
     public boolean themPhong(String ma, String soNguoi,String loai, String gia, boolean t, boolean f ){
        Session session = factory.openSession();
        Criteria cr =session.createCriteria(Phong.class);
        
        boolean check = true;
        if (this.kiemTraNhapPhong(ma, soNguoi,loai, gia, t, f) == false){
            content = checkNhap;
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
                  content = " Số phòng bạn nhập đã tồn tại ";
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
               content = " Thêm khách hàng thành công";
               check = true;
               loadAllPhong("", "", -2);
           } catch (Exception e) {
               if (trans == null)
                   trans.rollback();
//               a = new Alert(Alert.AlertType.ERROR);
//               a.setTitle("Kết quả ");
//               a.setContentText(" Thêm khách hàng thất bại");
//               a.show();
                content = " Thêm khách hàng thất bại";
               check = false;
           } finally{
               session.close();
           }
            }
        }
        
        
       
        return check;
     }
     
      //cap nhap phòng
     public void CapNhatHandler(ActionEvent event){
        String soNguoi = tfSucChua.getText();
        String loai = (String)cbLoai.getSelectionModel().getSelectedItem();
        String gia = tfgia.getText();
        boolean t = rdT.isSelected();
        boolean f = rdF.isSelected();
        String ma = tfMa.getText();
        boolean cn = capNhat(ma,soNguoi, loai, gia, t, f);
        if (cn == true)
            loadAllPhong("", "", -2);
    }
     
    //sửa Phong
    public boolean capNhat(String ma, String soNguoi,String loai, String gia, boolean t, boolean f){
        boolean check = true;
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        if (this.kiemTraNhapPhong(ma, soNguoi,loai, gia, t, f) == false)
            check = false;
        else {
                Phong ph = this.tbPhong.getSelectionModel().getSelectedItem();
            if(ph == null){
                a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Chú ý");
                a.setContentText(" Bạn chưa chọn phòng để cập nhật.");
                a.show();
                check = false;
            }
            else
            {
                int tinhTP = 0;
            if (t == true)
                tinhTP = 0;
            int loaiP = Utils.traloaiPhong(loai);
            ph.setSucChua(Integer.parseInt(soNguoi));
            ph.setGiaP(Double.parseDouble(gia)); 
            ph.setTinhTrangP(tinhTP);
            ph.setLoaiPhong(new LoaiPhong(loaiP, loai));
            Utils.CapNhatPhong(ph);
            a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Thông báo");
            a.setContentText("Cập nhật khách hàng thành công.");
            a.show(); 
            }
               
         }
        
        
        
        return check;
    }
    
    //Xoa mot dong
    public void deleteRowOnTable(ActionEvent event){
        btnXoa.setOnAction(et->{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Bạn chắc chưa?");
            alert.showAndWait().ifPresent(rs ->{
                if(rs == ButtonType.OK){
                    Phong ph = this.tbPhong.getSelectionModel().getSelectedItem();
                    Utils.xoaPhong(ph);
                    
                }
               
            });
           loadAllPhong("", "", -2); 
        });
    }
    
    //Quay về trang chủ
    public void trangChuHandler(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("TrangChu.fxml"));
        
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    
    //load sửa
//    public void loadSua() throws IOException{
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("SuaPhong.fxml"));
//        Parent root = loader.load();
//        Scene scene = new Scene(root);
//        Stage stage = new Stage();
//        stage.setScene(scene);
//        stage.show();
//        
//    }
    
    // hàm kiểm tra nhập số người hợp lệ
    public static boolean checkSoNguoi(String s){
        boolean check = false;
        Pattern pattern = Pattern.compile("^\\d{1,9}$");
        Matcher mat = pattern.matcher(s);
        if(mat.find()){
            check = true;
        }
        return check;
    }
    
    
    // hàm kiểm tra nhập số mã phòng
    public static boolean checkMaPhong(String s){
        boolean check = false;
        
        Pattern pattern = Pattern.compile("^\\d{1,17}$");
        Matcher mat = pattern.matcher(s);
        if(mat.find()){
           check = true;
        }
        return check;
    }
    
    // hàm kiểm tra nhập số mã phòng
    public static boolean checkGiaPhong(String s){
        boolean check = false;
        Pattern pattern = Pattern.compile("^\\d{1,17}$");
        Matcher mat = pattern.matcher(s);
        if(mat.find()){
            check = true;
        }
        return check;
    }
    
    // hàm kiểm tra nhập loại
    public static boolean checkLoaiPhong(String s){
        boolean check = false;
        s = s.toUpperCase();
        if("A".equals(s) || "B".equals(s) || "C".equals(s) || "D".equals(s)) {
            check = true;
        }
        return check;
    }
    
    // hàm kiểm tra nhập tinh trang phong
    public static boolean checkTinhTrangPhong(boolean t, boolean f){
        boolean check = false;
        if((t && f == false )|| ( t == false && f)){
            check = true;
        }
        return check;
    }
    
    //kiem tra nhap
    public static boolean kiemTraNhapPhong(String ma, String soNguoi,String loai, String gia, boolean t, boolean f){
        boolean check = true;
        
        if(checkMaPhong(ma) == false){
//            Alert a = new Alert(Alert.AlertType.ERROR);
//            a.setTitle("Lỗi nhập !!! ");
//            a.setContentText(" Vui lòng nhập lại mã hợp lệ.");
//            a.show();
//            checkNhap = " Vui lòng nhập lại số phòng hợp lệ. ";
            check = false;
        }else
             if(checkSoNguoi(soNguoi) == false){
    //            Alert a = new Alert(Alert.AlertType.ERROR);
    //            a.setTitle("Lỗi nhập !!! ");
    //            a.setContentText(" Vui lòng nhập lại số người hợp lệ. VD:2 ");
    //            a.show()
    //            checkNhap = " Vui lòng nhập lại số người hợp lệ. VD:2 ";
                check = false;
            } else
                 if(checkLoaiPhong(loai) == false){
        //            Alert a = new Alert(Alert.AlertType.ERROR);
        //            a.setTitle("Lỗi nhập !!! ");
        //            a.setContentText(" Vui lòng chọn loại phòng hợp lệ.");
        //            a.show();
        //            checkNhap = " Vui lòng chọn loại phòng hợp lệ. ";
                    check = false;
                }else
                     if(checkGiaPhong(gia) == false){
        //            Alert a = new Alert(Alert.AlertType.ERROR);
        //            a.setTitle("Lỗi nhập !!! ");
        //            a.setContentText(" Vui lòng chọn loại phòng hợp lệ.");
        //            a.show();
        //            checkNhap = " Vui lòng nhập giá phòng hợp lệ. VD 1500000";
                    check = false;
                } else
                     if(checkTinhTrangPhong(t, f) == false){
        //            Alert a = new Alert(Alert.AlertType.ERROR);
        //            a.setTitle("Lỗi nhập !!! ");
        //            a.setContentText(" Vui lòng chọn tinh trạng phòng ");
        //            a.show();
        //            checkNhap = " Vui lòng chọn tinh trạng phòng ";
                    check = false;
                }

                
                
                
        
           
        return check;
    }
    
}