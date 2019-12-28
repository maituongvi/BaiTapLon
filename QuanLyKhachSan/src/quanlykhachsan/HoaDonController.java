/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlykhachsan;

import QLKS.pojo.ChiTietHoaDon;
import QLKS.pojo.HoaDon;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import static quanlykhachsan.Utils.hd;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class HoaDonController implements Initializable {
    @FXML
    Label lbMaHB;
    @FXML
    Label lbNgayLap;
    @FXML
    Label lbTenKH;
    @FXML
    Label lbMaNV;
    @FXML
    Label lbTongTien;
    @FXML
    Label lbThanhToan;
    @FXML
    GridPane gpHD;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        lbMaHB.setText(Utils.hd.getMaHD());
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        lbTenKH.setText(Utils.hd.getKh().getTenKH());
        lbNgayLap.setText(df.format(Utils.hd.getNgayXuatHD()));
        lbMaNV.setText(String.format("%s",Utils.hd.getNv()));
        List<ChiTietHoaDon> lscthd = Utils.laydsCTHD(Utils.hd);
        int size = lscthd.size();
        if (size == 0){
                gpHD.add(setLB(String.format("%s", Utils.cthd.getMaPhong())),0,  1);
                gpHD.add(setLB(df.format(Utils.cthd.getNgayDen())),1,  1);
                gpHD.add(setLB(df.format(Utils.cthd.getNgayDi())),2, 1);
                gpHD.add(setLB(String.format("%,.2f", Utils.cthd.getGiaTien())),3, 1);
                double tien = Utils.cthd.getGiaTien()* Utils.khoangCachHaiNgay(Utils.cthd.getNgayDen(), Utils.cthd.getNgayDi());
                gpHD.add(setLB(String.format("%,.2f", tien)),4, 1);

            }
        else
            for (int i = 0; i < size; i++ ){
                gpHD.add(setLB(String.format("%s", lscthd.get(i).getMaPhong())),0, i + 1);
                gpHD.add(setLB(df.format(lscthd.get(i).getNgayDen())),1, i + 1);
                gpHD.add(setLB(df.format(lscthd.get(i).getNgayDi())),2, i + 1);
                gpHD.add(setLB(String.format("%,.2f", lscthd.get(i).getGiaTien())),3, i + 1);
                double tien = lscthd.get(i).getGiaTien()* Utils.khoangCachHaiNgay(lscthd.get(i).getNgayDen(), lscthd.get(i).getNgayDi());
                gpHD.add(setLB(String.format("%,.2f", tien)),4, i + 1);
            }
        
        lbTongTien.setText(String.format("%,.2f%s", Utils.hd.getTongTien(), " VNĐ"));
        lbThanhToan.setText(String.format("%,.2f%s", Utils.hd.getTongTien(), " VNĐ"));
        
        
    }    
    
    
    public Label setLB(String s){
        return new Label(s);
    }
}
