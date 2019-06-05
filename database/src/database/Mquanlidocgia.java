/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import JFrame.quanlidocgia;
import static database.Database.TaoMa;
import static database.Database.conectionJDBC;
import static database.Database.getData;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author sam
 */
public class Mquanlidocgia {

    public static DefaultTableModel LayDocGiaMuonTheoNgay(Date ngaydau, Date ngaycuoi) {
        String tungay, denngay;
        java.sql.Date dtungay, ddenngay;
        dtungay = new java.sql.Date(ngaydau.getTime());
        ddenngay = new java.sql.Date(ngaycuoi.getTime());
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");
        tungay = dateFormat.format(dtungay);
        denngay = dateFormat.format(ddenngay);
        System.out.println(tungay + "   " + denngay);
        String sql1 = "SELECT DOCGIA.MADOCGIA,THETHUVIEN.MATHE,DOCGIA.TEN FROM DOCGIA JOIN THETHUVIEN ON THETHUVIEN.MADOCGIA=DOCGIA.MADOCGIA JOIN QTMUON ON QTMUON.MADOCGIA=DOCGIA.MADOCGIA where ngaymuon <= TO_DATE('" + denngay + "','dd-MM-YYYY') and ngaymuon >= TO_DATE('" + tungay + "','dd-MM-YYYY') GROUP BY DOCGIA.MADOCGIA,THETHUVIEN.MATHE,DOCGIA.TEN";
        DefaultTableModel model = new DefaultTableModel();
        String[] ar = {"stt", "Mã thẻ", "Mã độc giả", "tên độc giả", "Số sách đã mượn"};
        Object[] temp = new Object[5];
        int i = 1;
        model.setColumnIdentifiers(ar);
        ResultSet rs = getData(conectionJDBC(), sql1);
        try {
            while (rs.next()) {
                temp[0] = i;
                i++;
                temp[1] = rs.getString("MATHE");
                temp[2] = rs.getString("MADOCGIA");
                temp[3] = rs.getString("TEN");
                String sql2 = "select count(*) from qtmuon where ngaymuon <= TO_DATE('" + denngay + "','dd-MM-YYYY') and ngaymuon >= TO_DATE('" + tungay + "','dd-MM-YYYY') group by madocgia having madocgia='" + temp[2] + "'";
                ResultSet rss = getData(conectionJDBC(), sql2);
                while (rss.next()) {
                    temp[4] = rss.getInt(1);
                }
                model.addRow(temp);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return model;
    }

    public static DefaultTableModel LayDocGiaMuonTheoThang(int thang, int nam) {
//         int thang = this.ChonThangMuon.getMonth();
//                int nam = this.ChonNamMuon.getYear();
        String sql1 = "SELECT DOCGIA.MADOCGIA,THETHUVIEN.MATHE,DOCGIA.TEN FROM DOCGIA JOIN THETHUVIEN ON THETHUVIEN.MADOCGIA=DOCGIA.MADOCGIA JOIN QTMUON ON QTMUON.MADOCGIA=DOCGIA.MADOCGIA where EXTRACT(MONTH FROM ngaymuon) =" + thang + " and EXTRACT(YEAR FROM ngaymuon) = " + nam + " GROUP BY DOCGIA.MADOCGIA,THETHUVIEN.MATHE,DOCGIA.TEN";
        DefaultTableModel model = new DefaultTableModel();
        String[] ar = {"stt", "Mã thẻ", "Mã độc giả", "tên độc giả", "Số sách đã mượn"};
        Object[] temp = new Object[5];
        int i = 1;
        model.setColumnIdentifiers(ar);
        ResultSet rs = getData(conectionJDBC(), sql1);
        try {
            while (rs.next()) {
                temp[0] = i;
                i++;
                temp[1] = rs.getString("MATHE");
                temp[2] = rs.getString("MADOCGIA");
                temp[3] = rs.getString("TEN");
                String sql2 = "select count(*) from qtmuon where EXTRACT(MONTH FROM ngaymuon) =" + thang + " and EXTRACT(YEAR FROM ngaymuon) = " + nam + " group by madocgia having madocgia='" + temp[2] + "'";
                ResultSet rss = getData(conectionJDBC(), sql2);
                while (rss.next()) {
                    temp[4] = rss.getInt(1);
                }
                model.addRow(temp);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return model;
    }

    public static DefaultTableModel LayDocGiaHanTheoNgay(Date ngaydau, Date ngaycuoi) {
            String tungay, denngay;
            java.sql.Date dtungay, ddenngay;
            dtungay = new java.sql.Date(ngaydau.getTime());
            ddenngay = new java.sql.Date(ngaycuoi.getTime());
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");
            tungay = dateFormat.format(dtungay);
            denngay = dateFormat.format(ddenngay);
            System.out.println(tungay + "   " + denngay);
            String sql1 = "SELECT DOCGIA.MADOCGIA,THETHUVIEN.MATHE,DOCGIA.TEN FROM DOCGIA JOIN THETHUVIEN ON THETHUVIEN.MADOCGIA=DOCGIA.MADOCGIA JOIN QTMUON ON QTMUON.MADOCGIA=DOCGIA.MADOCGIA where ngaymuon <= TO_DATE('" + denngay + "','dd-MM-YYYY') and ngaymuon >= TO_DATE('" + tungay + "','dd-MM-YYYY') and NGAYTRA is null GROUP BY DOCGIA.MADOCGIA,THETHUVIEN.MATHE,DOCGIA.TEN";
            DefaultTableModel model = new DefaultTableModel();
            String[] ar = {"stt", "Mã thẻ", "Mã độc giả", "tên độc giả", "Số sách quá hạn"};
            Object[] temp = new Object[5];
            int i = 1;
            model.setColumnIdentifiers(ar);
            ResultSet rs = getData(conectionJDBC(), sql1);
            try {
                while (rs.next()) {
                    temp[0] = i;
                    i++;
                    temp[1] = rs.getString("MATHE");
                    temp[2] = rs.getString("MADOCGIA");
                    temp[3] = rs.getString("TEN");
                    String sql2 = "select count(*) from qtmuon where ngaymuon <= TO_DATE('" + denngay + "','dd-MM-YYYY') and ngaymuon >= TO_DATE('" + tungay + "','dd-MM-YYYY') and NGAYTRA is null group by madocgia having madocgia='" + temp[2] + "'";
                    ResultSet rss = getData(conectionJDBC(), sql2);
                    while (rss.next()) {
                        temp[4] = rss.getInt(1);
                    }
                    model.addRow(temp);
                }
            } catch (SQLException ex) {
               ex.printStackTrace();
            }
        return model;
    }
    public static DefaultTableModel LayDocGiaHanTheoThang(int thang, int nam) {
//        int thang = this.ChonThangHan.getMonth();
//        int nam = this.ChonNamHan.getYear();
        thang++;// vì khi lấy giá trị từ ô tháng giá trị luôn <1 so với cái mình chọn vd:april=3
        System.out.println(thang + "-" + nam);
        String sql1 = "SELECT DOCGIA.MADOCGIA,THETHUVIEN.MATHE,DOCGIA.TEN FROM DOCGIA JOIN THETHUVIEN ON THETHUVIEN.MADOCGIA=DOCGIA.MADOCGIA JOIN QTMUON ON QTMUON.MADOCGIA=DOCGIA.MADOCGIA where EXTRACT(MONTH FROM ngaymuon) =" + thang + " and EXTRACT(YEAR FROM ngaymuon) = " + nam + " and NGAYTRA IS NULL GROUP BY DOCGIA.MADOCGIA,THETHUVIEN.MATHE,DOCGIA.TEN";
        DefaultTableModel model = new DefaultTableModel();
        String[] ar = {"stt", "Mã thẻ", "Mã độc giả", "Tên độc giả", "Số lượng sách quá hạn"};
        Object[] temp = new Object[5];
        int i = 1;
        model.setColumnIdentifiers(ar);
        ResultSet rs = getData(conectionJDBC(), sql1);
        try {
            while (rs.next()) {
                temp[0] = i;
                i++;
                temp[1] = rs.getString("MATHE");
                temp[2] = rs.getString("MADOCGIA");
                temp[3] = rs.getString("TEN");
                String sql2 = "select count(*) from qtmuon where EXTRACT(MONTH FROM ngaymuon) =" + thang + " and EXTRACT(YEAR FROM ngaymuon) = " + nam + " and NGAYTRA IS NULL group by madocgia having madocgia='" + temp[2] + "'";
                ResultSet rss = getData(conectionJDBC(), sql2);
                while (rss.next()) {
                    temp[4] = rss.getInt(1);
                }
                System.out.println(i);
                model.addRow(temp);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return model;
    }
    
    public static void XoaDG(String ma) {
//        DefaultTableModel modelxoa = (DefaultTableModel) this.TableDocGia.getModel();
//        String ma = (String) modelxoa.getValueAt(index, 0);
//        modelxoa.removeRow(index);
//        this.TableDocGia.setModel(modelxoa);
        String sqlcall = "{call XOA_DOCGIA(?)}";
        try {
            CallableStatement cast = (conectionJDBC()).prepareCall(sqlcall);
            cast.setString(1, ma);
            cast.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public static void ThemDG(String ten,Date ngaysinh,String sdt,String diachi,String lop,String doituong,String gioitinh){
        String sql = "{call ADD_DOCGIA(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            String user = TaoMa("docgia");
            String mathe = TaoMa("thethuvien");
            String strDate = new SimpleDateFormat("dd-MM-yyyy").format(new java.sql.Date((ngaysinh).getTime()));
            CallableStatement cst;
            //System.out.println(strDate);
            try {
                cst = conectionJDBC().prepareCall(sql);
                cst.setString(1, user);
                cst.setString(2, user);
                cst.setString(3, "doc gia");
                cst.setString(4, user);
                //cst.setString(5, this.TextTen.getText());
                cst.setString(5, ten);
                cst.setString(6, strDate);
                //cst.setString(7, this.TextSDT.getText());
                cst.setString(7, sdt);
                cst.setString(8, diachi);
                if (doituong.equals("Giao vien")) {
                    cst.setString(9, null);
                }else{
                    cst.setInt(9, Integer.parseInt(lop));
                }
//                } else {
//                    cst.setInt(9, Integer.parseInt((String) this.CBBoxLop.getSelectedItem()));
//                }
              //  cst.setString(9, lop);
                cst.setString(10, doituong);
                cst.setString(11, gioitinh);
                cst.setString(12, mathe);
                cst.setString(13, mathe);
                cst.setString(14, user);
                cst.setString(15, null);
                cst.execute();
                JOptionPane.showMessageDialog(null, "Thêm độc giả thành công");
                //this.setDefaultDocgia();
            } catch (SQLException ex) {
                Logger.getLogger(quanlidocgia.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    public static void SuaDG( String ma, String ten, String gioitinh, String diachi, String sdt, Date ngaysinh, String doituong, String lop) {
        String sqlupdate = "UPDATE DOCGIA SET TEN=?, GIOITINH=?, DIACHI=?, SDT=?, NGAYSINH=TO_DATE(?,'dd-MM-yyyy'), DOITUONG=?, LOP=? WHERE MADOCGIA='" + ma + "'";
        try {
            PreparedStatement update = conectionJDBC().prepareStatement(sqlupdate);
            update.setString(1, ten);
            update.setString(2, gioitinh);
            update.setString(3, diachi);
            update.setString(4, sdt);
            String strDate = new SimpleDateFormat("dd-MM-yyyy").format(new java.sql.Date(ngaysinh.getTime()));
            update.setString(5, strDate);
            update.setString(6, doituong);
            update.setString(7, lop);
            if (doituong.equals("Giao vien")) {
                update.setString(7, null);
            }
            update.execute();
            JOptionPane.showMessageDialog(null, "cập nhật độc giả thành công");
            //this.setDefaultDocgia();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "dữ liệu để cập nhật độc giả có lỗi");
            Logger.getLogger(quanlidocgia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static DefaultTableModel setDefaultDocgia(){
        String sql = "select * from docgia join thethuvien on docgia.madocgia=thethuvien.madocgia";
        ResultSet rs = getData(conectionJDBC(), sql);
        String[] dl = new String[9];
        DefaultTableModel model = new DefaultTableModel();
        String[] ar = {"Mã độc giả", "Tên độc giả", "Mã thẻ", "giới tính", "Địa chỉ", "Ngày sinh", "Số điện thoại", "Đối tượng", "Lớp"};
        model.setColumnIdentifiers(ar);
        try {
            while (rs.next()) {
                dl[0] = rs.getString("MADOCGIA");
                dl[1] = rs.getString("TEN");
                dl[2] = rs.getString("MATHE");
                dl[3] = rs.getString("GIOITINH");
                dl[4] = rs.getString("DIACHI");
                dl[5] = rs.getString("NGAYSINH");
                dl[6] = rs.getString("SDT");
                dl[7] = rs.getString("DOITUONG");
                dl[8] = rs.getString("LOP");
                model.addRow(dl);
            }
            //this.TableDocGia.setModel(model);
        } catch (SQLException ex) {
            Logger.getLogger(quanlidocgia.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "lỗi trong quá trình lấy danh sách độc giả");
        }
        return model;
    }
}
