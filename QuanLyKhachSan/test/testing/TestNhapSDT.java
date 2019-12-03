/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing;
import org.junit.*;
import quanlykhachsan.NhapKhachHangController;

/**
 *
 * @author HP
 */
public class TestNhapSDT {
    
    String sdt;
    
    // test số điện thoại hợp lệ. Có mười chữ số và bắt đầu bằng số 0
    @Test
    public void test(){
        sdt ="0998524123";
        
        boolean actual = NhapKhachHangController.checkSDT(sdt);
        System.out.println(actual);
        boolean expected = true;
        Assert.assertEquals(expected, actual);
    }
    
    // test số điện thoại không hợp lệ. Bắt đầu bằng số khác 0
    @Test
    public void test2(){
        sdt ="998524123";
        
        boolean actual = NhapKhachHangController.checkSDT(sdt);
        System.out.println(actual);
        boolean expected = false;
        Assert.assertEquals(expected, actual);
    }
    
    // test số điện thoại không hợp lệ. Ít hơn 10 chữ số
    @Test
    public void test3(){
        sdt ="024123";
        
        boolean actual = NhapKhachHangController.checkSDT(sdt);
        System.out.println(actual);
        boolean expected = false;
        Assert.assertEquals(expected, actual);
    }
    
    // test số điện thoại không hợp lệ. Nhiều hơn 10 chữ số
    @Test
    public void test4(){
        sdt ="001571156023115";
        
        boolean actual = NhapKhachHangController.checkSDT(sdt);
        System.out.println(actual);
        boolean expected = false;
        Assert.assertEquals(expected, actual);
    }
    
    // test số điện thoại không hợp lệ. Kí tự không phải là chữ số
    @Test
    public void test5(){
        sdt ="0sfmk";
        
        boolean actual = NhapKhachHangController.checkSDT(sdt);
        System.out.println(actual);
        boolean expected = false;
        Assert.assertEquals(expected, actual);
    }
    
    
}
