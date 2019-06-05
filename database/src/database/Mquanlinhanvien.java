/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import JFrame.quanlinhanvien;
import static database.Database.TaoMa;
import static database.Database.conectionJDBC;
import static database.Database.getData;
import java.sql.CallableStatement;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author sam
 */
public class Mquanlinhanvien {

    public static boolean CheckAccountNhanVien(String user, String pass) {
        int temp = 0;
        String sql = "select * from TAIKHOAN where TENTK='" + user + "' and MATKHAU='" + pass + "'";
        ResultSet rs = getData(conectionJDBC(), sql);
        try {
            while (rs.next()) {
                temp++;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        if (temp >= 1) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "mật khẩu hoặc tên tài khoản sai");
            return false;
        }
    }

    public static void changePass(String user, String repass) {

        String sqlmk = "update TAIKHOAN set MATKHAU='" + repass + "' where TENTK='" + user + "'";
        try {
            PreparedStatement prst = conectionJDBC().prepareStatement(sqlmk);
            prst.execute();
            JOptionPane.showMessageDialog(null, "cập nhật thành công");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public static void MThemNV(String tentk,String pass,String ma,String ten,java.util.Date ngaysinh,String sdt,String email,String gioitinh,String diachi){
         String sql="{call ADD_NHANVIEN(?,?,?,?,?,?,?,?,?,?,?)}";
            String strDate = new SimpleDateFormat("dd-MM-yyyy").format(ngaysinh.getTime());
            try {
               // String User=TaoMa("nhanvien");
                CallableStatement call=conectionJDBC ().prepareCall(sql);
                call.setString(1, tentk);
                call.setString(2, pass);
                call.setString(3, "thu thu");
                call.setString(4, ma);
                call.setString(5, ten);
                call.setString(6, strDate);
                call.setString(7, sdt);
                call.setString(8, email);
                call.setString(9, gioitinh);
                call.setString(10, "thu thu");
                call.setString(11, diachi);
                call.execute();}
            catch(SQLException e){
                e.printStackTrace();
            }
    }
    public static void MXoaNV(String ma) {
        String sqlxoa = "{ call XOA_NHANVIEN(?) }";
        try {
            CallableStatement st = conectionJDBC().prepareCall(sqlxoa);
            st.setString(1, ma);
            st.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public static DefaultTableModel MTimNV(String texttim){
         String sqltim = "select * from NHANVIEN JOIN TAIKHOAN ON NHANVIEN.MANHANVIEN=TAIKHOAN.TENTK WHERE HOTEN LIKE '%" + texttim + "%' OR TENTK LIKE '%" + texttim + "%'";
            ResultSet rs = getData(conectionJDBC(), sqltim);
            String[] dataNV = new String[9];
            DefaultTableModel model = new DefaultTableModel();
            String[] title = {"Mã NV", "Tên NV", "Giới tính", "Địa chỉ", "Ngày sinh", "SDT", "Tài khoản", "Chức vụ", "Email"};
            model.setColumnIdentifiers(title);
            try {
                while (rs.next()) {
                    dataNV[0] = rs.getNString("MANHANVIEN");
                    dataNV[1] = rs.getNString("HOTEN");
                    dataNV[2] = rs.getNString("GIOITINH");
                    dataNV[3] = rs.getNString("DIACHI");
                    dataNV[4] = rs.getNString("NGAYSINH");
                    dataNV[5] = rs.getNString("SDT");
                    dataNV[6] = rs.getNString("TENTK");
                    dataNV[7] = rs.getNString("CHUCVU");
                    dataNV[8] = rs.getNString("EMAIL");
                    model.addRow(dataNV);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        return model;
    }
    public static void MSuaNV(String tennv,String gt, String diachi,Date ngaysinh,String sdt, String email,String tentk){
          String sql = "UPDATE NHANVIEN SET HOTEN=?, GIOITINH=?, DIACHI=?, NGAYSINH=TO_DATE(?,'dd-MM-yyyy'), SDT=?, EMAIL=? WHERE MANHANVIEN=?";
//            String strDate = new SimpleDateFormat("dd-MM-yyyy").format(new java.sql.Date(ngaysinh.getTime()));
            String strDate = Database.DateToString(new java.sql.Date(ngaysinh.getTime()));
            PreparedStatement st;
            try {
                st = conectionJDBC().prepareStatement(sql);
                st.setString(1, tennv);
                st.setString(7, tentk);
                st.setString(3, diachi);
                st.setString(5, sdt);
                st.setString(4, strDate);
                st.setString(6, email);
//                String gt;
//                if (this.RadioNam.isSelected() && this.RadioNu.isSelected()) {
//                    JOptionPane.showMessageDialog(null, "chỉ được chọn nam hoặc nữ");
//                }
//                if (this.RadioNam.isSelected() == false && this.RadioNu.isSelected() == false) {
//                    JOptionPane.showMessageDialog(null, "hãy chọn giới tính");
//                }
//                if (this.RadioNam.isSelected()) {
//                    gt = "nam";
//                } else {
//                    gt = "nu";
//                }
                st.setString(2, gt);
                st.executeUpdate();
                JOptionPane.showMessageDialog(null, "Sửa thông tin thành công");
                System.out.println("thành công");
//                this.setDefaultNhanVien();
//                this.BSuaNV.setEnabled(false);
//                this.BXoaNV.setEnabled(false);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
    }
    public static DefaultTableModel TaoDefaultNV(){
          String sql = "select * from TAIKHOAN JOIN NHANVIEN ON TAIKHOAN.TENTK=NHANVIEN.MANHANVIEN";
        ResultSet rs = getData(conectionJDBC(), sql);
        DefaultTableModel model = new DefaultTableModel();
        String[] title = {"Mã NV", "Tên NV", "Giới tính", "Địa chỉ", "Ngày sinh", "SDT", "Tài khoản", "Chức vụ", "Email"};
        model.setColumnIdentifiers(title);
        String[] dataNV = new String[9];
        try {
            while (rs.next()) {
                dataNV[0] = rs.getNString("MANHANVIEN");
                dataNV[1] = rs.getNString("HOTEN");
                dataNV[2] = rs.getNString("GIOITINH");
                dataNV[3] = rs.getNString("DIACHI");
                dataNV[4] = rs.getNString("NGAYSINH");
                dataNV[5] = rs.getNString("SDT");
                dataNV[6] = rs.getNString("TENTK");
                dataNV[7] = rs.getNString("CHUCVU");
                dataNV[8] = rs.getNString("EMAIL");
                model.addRow(dataNV);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return model;
    }
}
