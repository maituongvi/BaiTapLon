/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import quanlykhachsan.TimKiemPhongController;

/**
 *
 * @author Admin
 */
public class TestNhapMaPhong {
    
    String ma;
    
    @Test
    public void testNull(){
        ma ="";
        
        boolean actual = TimKiemPhongController.checkMaPhong(ma);
        System.out.println(actual);
        boolean expected = false;
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void testMaChuSoVaChu(){
        ma ="1a";
        
        boolean actual = TimKiemPhongController.checkMaPhong(ma);
        System.out.println(actual);
        boolean expected = false;
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void testMaChu(){
        ma ="a";
        
        boolean actual = TimKiemPhongController.checkMaPhong(ma);
        System.out.println(actual);
        boolean expected = false;
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void testMaSo(){
        ma ="1";
        
        boolean actual = TimKiemPhongController.checkMaPhong(ma);
        System.out.println(actual);
        boolean expected = true;
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void testMaSoQuaLon(){
        ma ="1021345665201235485220022456522252445";
        
        boolean actual = TimKiemPhongController.checkMaPhong(ma);
        System.out.println(actual);
        boolean expected = false;
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void testDauCach(){
        ma ="5  2";
        
        boolean actual = TimKiemPhongController.checkMaPhong(ma);
        System.out.println(actual);
        boolean expected = false;
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void testDauCachSoChu(){
        ma ="1A b";
        
        boolean actual = TimKiemPhongController.checkMaPhong(ma);
        System.out.println(actual);
        boolean expected = false;
        Assert.assertEquals(expected, actual);
    }
    
     @Test
    public void testKyTuDB(){
        ma ="1..,;23A";
        
        boolean actual = TimKiemPhongController.checkGiaPhong(ma);
        System.out.println(actual);
        boolean expected = false;
        Assert.assertEquals(expected, actual);
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
