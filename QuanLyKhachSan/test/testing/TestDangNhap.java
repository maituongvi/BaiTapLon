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
import org.junit.runners.Parameterized;
import quanlykhachsan.FXMLDocumentController;
import quanlykhachsan.NhapKhachHangController;
import quanlykhachsan.TimKiemPhongController;

/**
 *
 * @author HP
 */
public class TestDangNhap {
    private String tk;
    private String mk;
    private FXMLDocumentController a;
    @Test // tài khoản và mật khẩu hợp lệ
    public void test(){
        tk = "maituongvi";
        mk ="123456";
        boolean expected=true;
        boolean actual = FXMLDocumentController.login(tk, mk);
        Assert.assertEquals(expected, actual);
    }
    
    @Test // tài khoản hoặc mật khẩu rỗng
    public void test1(){
        tk = "";
        mk ="123456";
        boolean expected=false;
        boolean actual = FXMLDocumentController.login(tk, mk);
        Assert.assertEquals(expected, actual);
    }
    
    @Test // tài khoản không đúng
    public void test2(){
        tk = "mai";
        mk ="123456";
        boolean expected=false;
        boolean actual = FXMLDocumentController.login(tk, mk);
        Assert.assertEquals(expected, actual);
    }
    
    @Test // mật khẩu không đúng
    public void test3(){
        tk = "maituongvi";
        mk ="12";
        boolean expected=false;
        boolean actual = FXMLDocumentController.login(tk, mk);
        Assert.assertEquals(expected, actual);
    }
}
