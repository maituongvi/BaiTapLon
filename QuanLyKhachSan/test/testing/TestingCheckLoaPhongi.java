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

public class TestingCheckLoaPhongi {
    
     String loai;
    
    @Test
    public void testNull(){
        loai ="";
        
        boolean actual = TimKiemPhongController.checkLoaiPhong(loai);
        System.out.println(actual);
        boolean expected = false;
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void testA(){
        loai ="A";
        
        boolean actual = TimKiemPhongController.checkLoaiPhong(loai);
        System.out.println(actual);
        boolean expected = true;
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void testB(){
        loai ="B";
        
        boolean actual = TimKiemPhongController.checkLoaiPhong(loai);
        System.out.println(actual);
        boolean expected = true;
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void testC(){
        loai ="C";
        
        boolean actual = TimKiemPhongController.checkLoaiPhong(loai);
        System.out.println(actual);
        boolean expected = true;
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void testD(){
        loai ="D";
        
        boolean actual = TimKiemPhongController.checkLoaiPhong(loai);
        System.out.println(actual);
        boolean expected = true;
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void testChu(){
        loai ="AA";
        
        boolean actual = TimKiemPhongController.checkLoaiPhong(loai);
        System.out.println(actual);
        boolean expected = false;
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void testChuCach(){
        loai ="A A";
        
        boolean actual = TimKiemPhongController.checkLoaiPhong(loai);
        System.out.println(actual);
        boolean expected = false;
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void testSo(){
        loai ="1";
        
        boolean actual = TimKiemPhongController.checkLoaiPhong(loai);
        System.out.println(actual);
        boolean expected = false;
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void testSoChu(){
        loai ="1A";
        
        boolean actual = TimKiemPhongController.checkLoaiPhong(loai);
        System.out.println(actual);
        boolean expected = false;
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void testChuSo(){
        loai ="A1";
        
        boolean actual = TimKiemPhongController.checkLoaiPhong(loai);
        System.out.println(actual);
        boolean expected = false;
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void testKiTuDB(){
        loai =".,;;;";
        
        boolean actual = TimKiemPhongController.checkLoaiPhong(loai);
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
