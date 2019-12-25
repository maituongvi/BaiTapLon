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
        List<ChiTietHoaDon> cthd = Utils.laydsCTHD(Utils.hd);
        int size = cthd.size();
        System.out.print(size);
         
        
        for (int i = 0; i < size; i++ ){
                gpHD.add(setLB(String.format("%s", cthd.get(i).getMaPhong())),0, i + 1);
                gpHD.add(setLB(df.format(cthd.get(i).getNgayDen())),1, i + 1);
                gpHD.add(setLB(df.format(cthd.get(i).getNgayDi())),2, i + 1);
                gpHD.add(setLB(String.format("%,.2f", cthd.get(i).getGiaTien())),3, i + 1);
                double tien = cthd.get(i).getGiaTien()* Utils.khoangCachHaiNgay(cthd.get(i).getNgayDen(), cthd.get(i).getNgayDi());
                gpHD.add(setLB(String.format("%,.2f", tien)),4, i + 1);

            }
        lbTongTien.setText(String.format("%,.2f%s", Utils.hd.getTongTien(), " VNĐ"));
        lbThanhToan.setText(String.format("%,.2f%s", Utils.hd.getTongTien(), " VNĐ"));
        
        
    }    
    
    
    public Label setLB(String s){
        return new Label(s);
    }
}
