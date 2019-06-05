/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QLTV;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.String;
import static database.Database.conectionJDBC;
import static database.Database.getData;
import database.Mquanlinhanvien;
import static database.Mquanlinhanvien.CheckAccountNhanVien;
import static database.Mquanlinhanvien.changePass;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
//import java.sql.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author sam
 */
public class Cquanlinhanvien {

    public static boolean CheckRePassword(String pass1, String pass2) {
        if (pass1.equals(pass2)) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "nhập lại mật khẩu sai");
            return false;
        }
    }

    public static void DoiPassNV(String user, String cu, String moi, String remoi) {
        if (CheckAccountNhanVien(user, cu)) {
            if (CheckRePassword(moi, remoi)) {
                changePass(user, remoi);
            }
        }
    }
    public static String XuLiGioiTinh(boolean gt1, boolean gt2) {
        if (gt1 && gt2) {
            JOptionPane.showMessageDialog(null, "chỉ được chọn nam hoặc nữ");
        } else {
            if (gt1 == false && gt2 == false) {
                JOptionPane.showMessageDialog(null, "hãy chọn giới tính");
            } 
        }
        if (gt1) {
            return "nam";
        } else {
            return "nu";
        }
    }
    public static void CThemnv(String tentk,String pass,String ma,String ten,Date ngaysinh,String sdt,String email,String gioitinh,String diachi){
        if(tentk.equals("")||pass.equals("")||ma.equals("")||ten.equals("")||sdt.equals("")||email.equals("")||diachi.equals("")){
            JOptionPane.showMessageDialog(null, "nhập thiếu thông tin");
            return;
        }
        Mquanlinhanvien.MThemNV(tentk, pass, ma, ten, ngaysinh, sdt, email, gioitinh, diachi);
    }

}
