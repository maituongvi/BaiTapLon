/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlykhachsan;

import QLKS.pojo.KhachHang;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author HP
 */
public class test {

    public static void main(String[] args) {
        List<KhachHang> list = Utils.laydsKH("", 0);
        KhachHang kh1 = list.get(0);
        System.out.println(kh1);
    }
}
