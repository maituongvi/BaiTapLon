/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing;

import QLKS.pojo.LoaiPhong;
import QLKS.pojo.Phong;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import quanlykhachsan.Utils;

/**
 *
 * @author Admin
 */
public class TestingLayDanhSachPhong {
    
    List <Phong> expected = new ArrayList<Phong>();
    
    @Test
    public void testDL() throws ParseException{
        expected.add(new Phong(1, 2, new LoaiPhong(1, "A") , 1500000.0, 0));
        expected.add(new Phong(2, 1, new LoaiPhong(2, "B") , 2000000.0, 0));
        expected.add(new Phong(3, 4, new LoaiPhong(4, "D") , 3000000.0, 1));
        expected.add(new Phong(4, 3, new LoaiPhong(3, "C") , 2500000.0, 0));
        expected.add(new Phong(5, 4, new LoaiPhong(1, "A") , 1500000.00, 1));
        expected.add(new Phong(6, 2, new LoaiPhong(1, "A") , 1050000.0, 0));
        expected.add(new Phong(7, 1, new LoaiPhong(2, "B") , 10000000.0, 0));
        expected.add(new Phong(8, 3, new LoaiPhong(1, "A") , 3500000.0, 1));
        expected.add(new Phong(9, 4, new LoaiPhong(2, "B") , 1500000.0, 0));
        expected.add(new Phong(10, 4, new LoaiPhong(2, "B") , 30000000.0, 0));
//        expected.add(new Phong(11, 2, new LoaiPhong(3, "C") , 30000000.0, 1));
    
        List <Phong> actual =Utils.laydsPhong("", "", -2);
        
        assertEquals(actual.size(), expected.size());
        for (int i = 0; i < actual.size(); i++) {
            Phong kh1 = actual.get(i);
            Phong kh2 = expected.get(i);
            assertEquals(actual.get(i).getMaPhong(), expected.get(i).getMaPhong());
            assertEquals(actual.get(i).getLoaiPhong(), expected.get(i).getLoaiPhong());
            assertEquals((int)actual.get(i).getGiaPhong(), (int)expected.get(i).getGiaPhong());
            assertEquals((actual.get(i).getTinhTrangPhong()), expected.get(i).getTinhTrangPhong());

        }

    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
