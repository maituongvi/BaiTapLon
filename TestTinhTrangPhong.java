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
public class TestTinhTrangPhong {
    
   boolean t, f;
    
    @Test
    public void test1(){
        t = false;
        f = false;
        
        boolean actual = TimKiemPhongController.checkTinhTrangPhong(t, f);
        System.out.println(actual);
        boolean expected = false;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void test2(){
        t = true;
        f = true;
        
        boolean actual = TimKiemPhongController.checkTinhTrangPhong(t, f);
        System.out.println(actual);
        boolean expected = false;
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void test3(){
        t = false;
        f = true;
        
        boolean actual = TimKiemPhongController.checkTinhTrangPhong(t, f);
        System.out.println(actual);
        boolean expected = true;
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void test4(){
        t = true;
        f = false;
        
        boolean actual = TimKiemPhongController.checkTinhTrangPhong(t, f);
        System.out.println(actual);
        boolean expected = true;
        Assert.assertEquals(expected, actual);
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
