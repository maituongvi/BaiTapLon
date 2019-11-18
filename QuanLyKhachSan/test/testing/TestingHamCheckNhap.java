/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing;
import javafx.scene.control.Alert;
import java.util.Arrays;
import java.util.Collection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import quanlykhachsan.TimKiemPhongController;
import quanlykhachsan.Utils;

/**
 *
 * @author Admin
 */
@RunWith(Parameterized.class)
public class TestingHamCheckNhap { 
   private String ma;
   private String soNguoi;
   private String loai;
   private String gia;
   private boolean t;
   private boolean f;
    private boolean expected;
    private TimKiemPhongController tk;
    
    public TestingHamCheckNhap(String ma, String soNguoi, String loai, String gia, boolean t, boolean f, boolean expected){
        this.ma = ma;
        this.soNguoi = soNguoi;
        this.loai = loai;
        this.gia = gia;
        this.t = t;
        this.f = f;
        this.expected = expected;
    }
//    @Before
//    public void init(){
//        this.tk = new TimKiemPhongController();
//    }
    @Parameterized.Parameters
    public static Collection testData(){
        return Arrays.asList(new Object[][]{
            //giá là số
            {"1", "2", "A", "123a45677", true, true, false},
            //đúng
            {"1", "2", "A", "12345677", true, false, true},
            //đúng
            {"1", "2", "C", "12345677", false, true, true},
            //nhập số phòng sai
            {"a", "a", "1A", "123a45677", false, false, false}
            //Loại phòng { A, B, C, D}
            ,{"1", "a", "1A", "123a45677", false, false, false}, 
            //Loại và số phòng sai
            {"a", "1", "1A", "123a45677",true, false, false},
            //chưa nhập số phòng
            {"", "1", "A", "12345677",true, false, false}, 
            //Chưa nhập sức chứa
            {"1", "", "1A", "12345677",true, false, false},
            //Chưa nhập loại phòng
            {"1", "1", "", "12345677",true, false, false},
            //Chưa nhập giá phòng
            {"1", "1", "A", "",true, false, false}, 
            //Chưa nhập tình trạng phòng
            {"1", "2", "A", "12345677",false, false, false}, 
            //Không có hai tình trạng phòng cùng 1 phòng
            {"1", "1", "A", "12345677",true, true, false},
            //Loại và sức chứa sai
            {"1", "A", "A", "12345677",true, false, false},
            //giá phòng là số
            {"1", "1", "A", "12 345677",true, false, false}, 
            //chưa nhập sức chứa và mã phòng sai
            {"1", "", "1A", "12345677",true, false, false}, 
            //chưa nhập loại và giá không phải số
            {"1", "1", "", "123a45677",true, false, false},
            //Chưa nhập dữ liệu chỉ chọn tình trạng phòng
            {"", "", "", "",true, false, false},
            //Chưa nhập dữ liệu
            {"", "", "", "",false, false, false}, 
            //Chưa nhập dữ liệu, và tình trạng chọn cả hai
            {"", "", "", "",true, true, false},
            //Ký tự đặt biệt ở số phòng
            {".;'.'", "2", "A ", "123hdg45677",true, false, false}, 
            //Ký tự đặt biệt ở sức chứa
            {"1", "/.;.'", "B", "2356665",false, false, false},
            //Ký tự đặt biệt loại phòng
            {"1", "2", "A ", ".;.',",true, false, false}, 
            //Ký tự đặt biệt ở tất cả các ô nhập
            {";//", "/.;.'", "/;/", "/;/;",false, false, false},
            
        });
    }
    
    
    @Test 
    public void test1(){
       
        boolean kiemTra = TimKiemPhongController.kiemTraNhapPhong(ma, soNguoi, loai, gia, t, f);
        Assert.assertEquals(expected, kiemTra);
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
