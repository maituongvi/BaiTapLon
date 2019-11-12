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
    
    @Test
    public void test(){
        sdt ="0998524123";
        
        boolean actual = NhapKhachHangController.checkSDT(sdt);
        System.out.println(actual);
        boolean expected = true;
        Assert.assertEquals(expected, actual);
    }
    
    
}
