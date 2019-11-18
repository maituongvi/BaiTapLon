/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing;

import QLKS.pojo.KhachHang;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import quanlykhachsan.NhapKhachHangController;
import quanlykhachsan.Utils;

/**
 *
 * @author HP
 */
public class testLayDsKH {
    List <KhachHang> expected = new ArrayList<KhachHang>();
    
    
    // test lấy toàn bộ danh sách khách hàng
    @Test
    public void test() throws ParseException{
        SimpleDateFormat fm = new SimpleDateFormat("dd-MM-yyyy");
        
        Date ns2 =fm.parse("18-06-1999");
        expected.add(new KhachHang("3e333e16-fa57-4cdf-b3bc-ef47b31afa68", "Mai Tường Vi", ns2, "Nữ", "0356847078"));
        
        Date ns1 =fm.parse("31-10-2019");
        expected.add(new KhachHang("fb9069b7-7a9b-4bcb-9014-1b8aaea563b3", "Nhung", ns1, "Nữ", "0123456788"));
        
    
        List <KhachHang> actual =Utils.laydsKH("", 0);
        
        assertEquals(actual.size(), expected.size());
        for (int i = 0; i < actual.size(); i++) {
            KhachHang kh1 = actual.get(i);
            KhachHang kh2 = expected.get(i);
            assertEquals(actual.get(i).getMaKH(), expected.get(i).getMaKH());
            assertEquals(actual.get(i).getTenKH(), expected.get(i).getTenKH());
            assertEquals(actual.get(i).getGioiTinh(), expected.get(i).getGioiTinh());
            assertEquals(fm.format(actual.get(i).getNgaySinh()), fm.format(expected.get(i).getNgaySinh()));
            assertEquals(actual.get(i).getSdt(), expected.get(i).getSdt());

        }

    }
    
    // test lấy danh sách khách hàng tên cho chứa chữ Mai
    @Test
    public void test1() throws ParseException{
        SimpleDateFormat fm = new SimpleDateFormat("dd-MM-yyyy");
        
        Date ns2 =fm.parse("18-06-1999");
        expected.add(new KhachHang("3e333e16-fa57-4cdf-b3bc-ef47b31afa68", "Mai Tường Vi", ns2, "Nữ", "0356847078"));
     
        List <KhachHang> actual =Utils.laydsKH("Mai", 0);
        
        assertEquals(actual.size(), expected.size());
        for (int i = 0; i < actual.size(); i++) {
            KhachHang kh1 = actual.get(i);
            KhachHang kh2 = expected.get(i);
            assertEquals(actual.get(i).getMaKH(), expected.get(i).getMaKH());
            assertEquals(actual.get(i).getTenKH(), expected.get(i).getTenKH());
            assertEquals(actual.get(i).getGioiTinh(), expected.get(i).getGioiTinh());
            assertEquals(fm.format(actual.get(i).getNgaySinh()), fm.format(expected.get(i).getNgaySinh()));
            assertEquals(actual.get(i).getSdt(), expected.get(i).getSdt());

        }

    }
    
    
    
}
