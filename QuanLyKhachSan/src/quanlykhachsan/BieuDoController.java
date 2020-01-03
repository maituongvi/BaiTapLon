/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlykhachsan;

import QLKS.pojo.HoaDon;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class BieuDoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML 
    TextField tfnam;
    @FXML
    StackPane bd = new StackPane();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }    
    
    public void VeBieuDo(String nam) throws ParseException {
    }

    public void thongkeThangHandler(ActionEvent e) throws ParseException{
        this.bd.getChildren().clear();
        String nam = tfnam.getText();
        if (!Utils.checkNam(nam)){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Cảnh báo ");
            a.setContentText(" Bạn cần nhập năm hợp lệ. VD: 2019 ");
            a.show();
        }else{
            int n = Integer.parseInt(nam);
            List<Double> ls = Utils.listDoanhThuT(n);

            
            CategoryAxis xAxis = new CategoryAxis();
            xAxis.setLabel("Tháng");
            NumberAxis yAxis = new NumberAxis();
            yAxis.setLabel("Doanh thu");
            BarChart chart = new BarChart (xAxis, yAxis);
            for (int i = 1; i <= 12; i++){
                XYChart.Series s = new XYChart.Series();
                String thang = String.format("%s", i);
                s.setName(thang);
                s.getData().add(new XYChart.Data(thang, ls.get(i - 1)));
                chart.getData().add(s);
            }
//            XYChart.Series s1 = new XYChart.Series();
//            s1.setName("1");
//            s1.getData().add(new XYChart.Data("1", ls.get(0)));
//            XYChart.Series s2 = new XYChart.Series();
//            s2.setName("2");
//            s2.getData().add(new XYChart.Data("2", ls.get(1)));
//            XYChart.Series s3 = new XYChart.Series();
//            s3.setName("3");
//            s3.getData().add(new XYChart.Data("3", ls.get(2)));
//            XYChart.Series s4 = new XYChart.Series();
//            s4.setName("4");
//            s4.getData().add(new XYChart.Data("4", ls.get(3)));
//            XYChart.Series s5 = new XYChart.Series();
//            s5.setName("5");
//            s5.getData().add(new XYChart.Data("5", ls.get(4)));
//            XYChart.Series s6 = new XYChart.Series();
//            s6.setName("6");
//            s6.getData().add(new XYChart.Data("6", ls.get(5)));
//            XYChart.Series s7 = new XYChart.Series();
//            s7.setName("7");
//            s7.getData().add(new XYChart.Data("7", ls.get(6)));
//            XYChart.Series s8 = new XYChart.Series();
//            s8.setName("8");
//            s8.getData().add(new XYChart.Data("8", ls.get(7)));
//            XYChart.Series s9 = new XYChart.Series();
//            s9.setName("9");
//            s9.getData().add(new XYChart.Data("9", ls.get(8)));
//            XYChart.Series s10 = new XYChart.Series();
//            s10.setName("10");
//            s10.getData().add(new XYChart.Data("10", ls.get(9)));
//            XYChart.Series s11 = new XYChart.Series();
//            s11.setName("11");
//            s11.getData().add(new XYChart.Data("11", ls.get(10)));
//            XYChart.Series s12 = new XYChart.Series();
//            s12.setName("12");
//            s12.getData().add(new XYChart.Data("12", ls.get(11)));
//            chart.getData().addAll(s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11, s12);
            bd.getChildren().add(chart);
    }
        
        
    }
    
    
    public void thongkeQuyHandler(ActionEvent e) throws ParseException{
        this.bd.getChildren().clear();
        String nam = tfnam.getText();
         if (!Utils.checkNam(nam)){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Cảnh báo ");
            a.setContentText(" Bạn cần nhập năm hợp lệ. VD: 2019 ");
            a.show();
        }else{
              int n = Integer.parseInt(nam);
              List<Double> ls = Utils.listDoanhThuT(n);
            ArrayList<Double> lsK =  new ArrayList<>(4);

            for (int i = 0; i <= 3; i++){
                double tien = 0;
                for(int t = 0; t < 3; t++)
                    tien += ls.get(i * 3 + t);
                lsK.add(i, tien);
            }
            CategoryAxis xAxis = new CategoryAxis();
            xAxis.setLabel("Quý");
            NumberAxis yAxis = new NumberAxis();
            yAxis.setLabel("Doanh thu");
             BarChart chart = new BarChart (xAxis, yAxis);
             for (int i = 1; i <= 4; i++){
                XYChart.Series s = new XYChart.Series();
                String thang = String.format("%s", i);
                s.setName(thang);
                s.getData().add(new XYChart.Data(thang, lsK.get(i - 1)));
                chart.getData().add(s);
            }
//            XYChart.Series s1 = new XYChart.Series();
//            s1.setName("1");
//            s1.getData().add(new XYChart.Data("1", lsK.get(0)));
//            XYChart.Series s2 = new XYChart.Series();
//            s2.setName("2");
//            s2.getData().add(new XYChart.Data("2", lsK.get(1)));
//            XYChart.Series s3 = new XYChart.Series();
//            s3.setName("3");
//            s3.getData().add(new XYChart.Data("3", lsK.get(2)));
//            XYChart.Series s4 = new XYChart.Series();
//            s4.setName("4");
//            s4.getData().add(new XYChart.Data("4", lsK.get(3)));
//            chart.getData().addAll(s1, s2, s3, s4);
            bd.getChildren().add(chart);
         }
    }
    // bà chèn link trang chủ vô nhen
    public void quayLaiHandler(ActionEvent e) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("TimKiemPhong.fxml"));
        
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
}
