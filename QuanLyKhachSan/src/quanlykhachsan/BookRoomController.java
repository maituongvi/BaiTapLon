/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlykhachsan;

import QLKS.pojo.ChiTietHoaDon;
import QLKS.pojo.HoaDon;
import QLKS.pojo.KhachHang;
import QLKS.pojo.NhanVien;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import static quanlykhachsan.Utils.factory;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class BookRoomController implements Initializable {
    @FXML
    private TextField tfGT ;
    @FXML
    private TextField tfTenKH;
    @FXML
    private TextField tfMaKH;
//    @FXML
//    private TextField tfGTKH;
    @FXML
    private TextField tfGTKH;
    @FXML
    private TextField tfSDT;
    @FXML
    private TextField tfMaP;
    @FXML
    private TextField tfLoaiP;
    @FXML
    private DatePicker dbStart;
    @FXML
    private DatePicker dbEnd;
    @FXML
    private TextField tfGiaP;
    @FXML
    private TextField tfSN;
    @FXML
    private TextField txtTimKiem;
    @FXML
    private Button search;
    String id = "";
    @FXML
    private TextField tfIdNV;
    List<KhachHang> kh = null;
    double tongTien;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tfMaP.setText(String.format("%d",Utils.ph.getMaPhong()));
        tfLoaiP.setText(Utils.ph.getLoaiPhong());
        tfGiaP.setText(String.format("%2.0f",Utils.ph.getGiaPhong()));
        tfSN.setText(String.format("%d",Utils.ph.getSucChua()) + " people");
        tfTenKH.clear();
        tfMaKH.clear();
        tfSDT.clear();
        txtTimKiem.clear();
        tfGTKH.clear();
        dbEnd.setValue(null);
        dbStart.setValue(null);
        
    }    
    
    //tìm kiếm theo số điện thoại
    public void searchKHHandler(ActionEvent event){
        Alert a = new Alert(Alert.AlertType.ERROR);
        if (!Utils.checkSDT(this.txtTimKiem.getText().trim())){
            a.setTitle("Thông báo ");
            a.setContentText(" Bạn cần nhập số điện thoại hợp lệ VD: 0708384593");
            a.show();
        }
        else {
            kh = Utils.laydsKHBangSDT(this.txtTimKiem.getText().trim(), 0);
            if (!kh.isEmpty()){
                this.tfTenKH.setText(kh.get(0).getTenKH());
                this.tfGTKH.setText(kh.get(0).getGioiTinh());
                this.tfSDT.setText(kh.get(0).getSdt());
                this.tfMaKH.setText(kh.get(0).getMaKH());
                LocalDate day =kh.get(0).getNgaySinh().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy");
                
                
                this.tfGT.setText(ft.format(kh.get(0).getNgaySinh()));
            }else {
                a = new Alert(Alert.AlertType.INFORMATION);
                a.setTitle("Thông báo ");
                a.setContentText(" Số điện thoại bạn nhập không tồn tại");
                a.show();
                
            }
        }
        
    }
    
    public void bookPhongHandler(ActionEvent event){
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        if (kh == null){
            a.setTitle("Cảnh báo ");
            a.setContentText(" Bạn cần tìm khách hàng trước khi đặt phòng! ");
            a.show();
        }else 
            if (dbStart.getValue() == null || dbEnd.getValue() == null){
            a.setTitle("Thông báo ");
            a.setContentText(" Bạn không được bỏ trống chọn ngày");
            a.show();
            } 
            else 
                if (tfIdNV.getText().isEmpty()){
                    a.setTitle("Thông báo ");
                    a.setContentText(" Bạn cần nhập mã nhân viên");
                    a.show();
                } else {
                    
                        Date ngStart = Date.from(dbStart.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                        Date ngEnd = Date.from(dbEnd.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                        boolean ck = Utils.checkNgayStart(ngStart, ngEnd);
                        if(ck == false){
                            a = new Alert(Alert.AlertType.ERROR);
                            a.setTitle("Chú ý ");
                            a.setContentText(Utils.noiDung);
                            a.show();
                        }else{
                            if (tfIdNV.getText().isEmpty()){
                                a = new Alert(Alert.AlertType.ERROR);
                                a.setTitle("Cảnh báo ");
                                a.setContentText(" Bạn cần nhập mã nhân viên ");
                                a.show();
                            }
                            else {
                                    if (!Utils.checkMaNV(tfIdNV.getText().trim())){
                                        a = new Alert(Alert.AlertType.ERROR);
                                        a.setTitle("Thông báo ");
                                        a.setContentText(" Mã nhân viên không hợp lệ. VD: mã hợp lệ: 1 ");
                                        a.show();
                                    }else{
                                        List<NhanVien> listNV = Utils.laydsNV(tfIdNV.getText().trim(), 0);
                                        a.setTitle("Thông báo ");
                                        a.setContentText(" Không tìm thấy mã nhân viên! "+ listNV);
                                        a.show();
                                        if (listNV.isEmpty()){
                                            a = new Alert(Alert.AlertType.ERROR);
                                            a.setTitle("Thông báo ");
                                            a.setContentText(" Không tìm thấy mã nhân viên! "+ listNV);
                                            a.show();
                                        }
                                        else {
                                            Session session = factory.openSession();
                                            Transaction trans =null;
                                            try {

                                                trans = session.beginTransaction();
                                                Utils.ph.setTinhTrangP(1);
                                                id = UUID.randomUUID().toString();
                                                Date now = new Date();
                                                if (Utils.laydsHD(kh.get(0), listNV.get(0)).isEmpty())
                                                    tongTien = Double.parseDouble(Utils.ph.getGiaP());
                                                else
                                                    tongTien += Double.parseDouble(Utils.ph.getGiaP());
                                                HoaDon hd = new HoaDon(id,listNV.get(0) , kh.get(0),now,tongTien);
                                                ChiTietHoaDon cthd = new ChiTietHoaDon(Utils.ph, hd, Utils.ph.getGiaP(), ngStart, ngStart);
                                                session.save(hd);
                                                session.save(cthd);
                                                trans.commit();
                                                Utils.content = " Bạn đã đặt phòng thành công";

                                             } catch (Exception e) {
                                                 if (trans == null)
                                                     trans.rollback();
                                  //               a = new Alert(Alert.AlertType.ERROR);
                                  //               a.setTitle("Kết quả ");
                                  //               a.setContentText(" Thêm khách hàng thất bại");
                                  //               a.show();
                                                  System.out.println(e.getMessage());
                                                  Utils.content = " Đặt phòng thất bại";

                                        } finally{
                                                 session.close();
                                            }

                                            a.setTitle("Thông báo ");
                                            a.setContentText(Utils.content);
                                            a.show();
                                        }
                                }
                            }
                            


                    
                }
        }
        
        
    }
    
     //Quay về trang phòng
     public void quayVeFormPhongHandler(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("TimKiemPhong.fxml"));
        
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
     }
     
     //reset trang phòng
     public void resetHandler(ActionEvent event) {
        tfTenKH.clear();
        tfMaKH.clear();
        tfGT.clear();
        tfSDT.clear();
        txtTimKiem.clear();
        tfGTKH.clear();
        dbEnd.setValue(null);
        dbStart.setValue(null);
     }
     
      //Đóng app
    public void closeAppHandler(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("BookRoom.fxml"));
        
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.close();
        
    }
    
    
}
