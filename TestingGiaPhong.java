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
public class TestingGiaPhong {
    
    String gia;
    
    @Test
    public void testNull(){
        gia ="";
        
        boolean actual = TimKiemPhongController.checkGiaPhong(gia);
        System.out.println(actual);
        boolean expected = false;
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void testChu(){
        gia ="A";
        
        boolean actual = TimKiemPhongController.checkGiaPhong(gia);
        System.out.println(actual);
        boolean expected = false;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSo(){
        gia ="123";
        
        boolean actual = TimKiemPhongController.checkGiaPhong(gia);
        System.out.println(actual);
        boolean expected = true;
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void testSoChu(){
        gia ="123A";
        
        boolean actual = TimKiemPhongController.checkGiaPhong(gia);
        System.out.println(actual);
        boolean expected = false;
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void testChuSo(){
        gia ="A123";
        
        boolean actual = TimKiemPhongController.checkGiaPhong(gia);
        System.out.println(actual);
        boolean expected = false;
        Assert.assertEquals(expected, actual);
    } 
    
    @Test
    public void testSoChuCach(){
        gia ="123A ";
        
        boolean actual = TimKiemPhongController.checkGiaPhong(gia);
        System.out.println(actual);
        boolean expected = false;
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void testKyTuDB(){
        gia ="1..,;23A";
        
        boolean actual = TimKiemPhongController.checkGiaPhong(gia);
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
