/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing;

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
public class TestingHamThem {
    private String ma;
    private String soNguoi,loai,gia;
    private boolean t, f;
    private boolean expected;
    private TimKiemPhongController tk;
    public TestingHamThem(String ma, String soNguoi, String loai, String gia, boolean t, boolean f, boolean expected){
        this.ma = ma;
        this.soNguoi = soNguoi;
        this.loai = loai;
        this.gia = gia;
        this.t = t;
        this.f = f;
        this.expected = expected;
    }
    @Before
    public void init(){
        this.tk = new TimKiemPhongController();
    }
    @Parameterized.Parameters
    public static Collection testData(){
        return Arrays.asList(new Object[][]{
            //trung voi phong da co
            {"1", "2", "A", "12345677", true, false, false}, 
            //suc chua la so
            {"130", "A", "A", "12345677", true, false, false},
            //ma {A, B, C, D}
            {"130", "2", "1A", "12345677", true, false, false}, 
            //gia la so
            {"130", "2", "A", "A12345677", true, false, false},
            //can chon tình trạng phòng 
            {"130", "2", "A", "12345677", false, false, false}, 
            //Không có hai tính trạng cùng lúc
            {"130", "2", "A", "12345677", true, true, false},
            //Mã phòng rỗng
            {"", "2", "A", "12345677", true, false, false}, 
            //Sức chứa rỗng
            {"130", "", "A", "A12345677", true, false, false},
            //Loại phòng rỗng
            {"130", "2", "", "12345677", true, false, false}, 
            //Gía phòng rỗng
            {"130", "2", "A", "", true, false, false},
            //Đúng
            {"130", "2", "A", "12345677", true, false, true}, 
            //Ký tự đặc biệt ở mã 
            {"/;.;/", "A", "A", "12345677", false, false, false},
            //Ký tự đặc biệt ở sức chứa
            {"130", "./'/'/", "A", "12345677", true, true, false},
            //Ký tự đặc biệt ở loại
            {"130", "2", ".';.,;", "A12345677", true, false, false},
            //Ký tự đặc biệt ở giá
            {"130", "2", "A", ".;/';/'", false, false, false},
            //không chọn nhập giá trị
            {"", "", "", "", false, false, false},
            //Không nhập giá trị chỉ chọn tình trạng
            {"", "", "", "", true, false, false}, 
            //Tất cả đều sai
            {"A", "A", ";/;.", "A12345677", true, false, false}
            
        });
    }
    
    
    @Test 
    public void test1(){
        Assert.assertEquals(this.expected, this.tk.themPhong(ma, soNguoi, loai, gia, t, f));
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
