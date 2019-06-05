/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import JFrame.quanlimuontra;
import static database.Database.conectionJDBC;
import static database.Database.getData;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author sam
 */
public class Mquanlimuontra {
    public static boolean checkDG(String ma){
        int temp=0;
         String sql = "select * from DOCGIA WHERE MADOCGIA='" + ma + "'";
            ResultSet rs = getData(conectionJDBC(), sql);
            try {
                while (rs.next()) {
                    temp++;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            if(temp>=1){
                return true;
            }else{
                return false;
            }
    }
    public static int getSoSachDangMuon(String ma) {
        int dangmuon=0;
        String strmuon = "select COUNT(MASACH) FROM QTMUON WHERE NGAYTRA IS NULL AND MADOCGIA='" + ma + "' GROUP BY MADOCGIA";
        ResultSet rs = getData(conectionJDBC(), strmuon);
        try {
            while (rs.next()) {
                dangmuon = Integer.parseInt(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Mquanlimuontra.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dangmuon;
    }
    public static int getSoSachQuaHan(String ma){
        int quahan=0;
         String strhan = "select COUNT(MASACH) FROM QTMUON WHERE NGAYTRA IS NULL AND NGAYHETHAN < SYSDATE AND MADOCGIA='" + ma + "' GROUP BY MADOCGIA";
                ResultSet    rs = getData(conectionJDBC(), strhan);
        try {
            while (rs.next()) {
                quahan = Integer.parseInt(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Mquanlimuontra.class.getName()).log(Level.SEVERE, null, ex);
        }
        return quahan;
    }
    public static String LayTenDocGia(String ma){
        String ten="";
         String duyet = "select DOCGIA.TEN FROM DOCGIA WHERE MADOCGIA='" + ma + "'";
               ResultSet rs = getData(conectionJDBC(), duyet);
                
        try {
            while (rs.next()) {
                ten = rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Mquanlimuontra.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ten;
    }
    public static boolean CheckMaSach(String masach) {
        String sqlmasach = "select TRANGTHAI FROM SACH WHERE MASACH='" + masach + "'";
        ResultSet rs = getData(conectionJDBC(), sqlmasach);
        boolean temp = false;
        try {
            while (rs.next()) {
//                    this.TextTrangThaiSach.setText(rs.getNString(1));
//                    this.LBTinhTrangSach.setVisible(false);
                temp = true;
            }
            rs.close();
//                if (temp <= 0) {
//                    this.LBTinhTrangSach.setVisible(true);
//                    this.TextTrangThaiSach.setText("");
//                }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
            return temp;
    }
    public static String LayTrangThaiSach(String masach){
        String sqlmasach = "select TRANGTHAI FROM SACH WHERE MASACH='" + masach + "'";
        ResultSet rs = getData(conectionJDBC(), sqlmasach);
        String temp = "";
        try {
            while (rs.next()) {
//                    this.TextTrangThaiSach.setText(rs.getNString(1));
            temp= rs.getNString(1);
//                    this.LBTinhTrangSach.setVisible(false);
               // temp = true;
            }
            rs.close();
//                if (temp <= 0) {
//                    this.LBTinhTrangSach.setVisible(true);
//                    this.TextTrangThaiSach.setText("");
//                }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return temp;
    }
    public static DefaultTableModel ThemSachVaoBangTam(DefaultTableModel md,String masach){
        DefaultTableModel model = md;
            String[] temp = new String[2];
            String sql = "SELECT * FROM SACH WHERE MASACH='" + masach + "'";
            ResultSet rs = getData(conectionJDBC(), sql);
            try {
                while (rs.next()) {
                    temp[0] = rs.getString("MASACH");
                    temp[1] = rs.getString("TENSACH");
                    model.addRow(temp);
                }
                //this.TableMuonTam.setModel(model);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return model;
    } 
    public static void ThemVaoQTMuon(JTable table,int sodongtam,String ma){
        PreparedStatement st;
            try {
                for (int i = 0; i < sodongtam; i++) {
                    String sqlqt = "insert into QTMUON VALUES('" + (String) table.getValueAt(i, 0) + "',SYSDATE,'" + ma + "',ADD_MONTHS(SYSDATE,3),null,null)";
                    st = conectionJDBC().prepareCall(sqlqt);
                    st.executeUpdate();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
    }
    public static void ThemPhieuMuon(JTable table,int sodongtam, String maphieumuon,String madg){
        PreparedStatement st;
        try {
                for (int i = 0; i < sodongtam; i++) {
                    String sqlpm = "insert into PHIEUMUON VALUES('" + maphieumuon + "','" + (String) table.getValueAt(i, 0) + "','" + madg + "',SYSDATE, ADD_MONTHS(SYSDATE,3))";
                    st = conectionJDBC().prepareCall(sqlpm);
                    st.executeUpdate();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
    }
    public static void ThayDoiTrangThaiSach(JTable table, int sodongtam){
        PreparedStatement st;
         for (int i = 0; i < sodongtam; i++) {
                String sqlupdate = "UPDATE SACH SET TRANGTHAI='Da muon' WHERE MASACH='" + (String) table.getValueAt(i, 0) + "'";
                try {
                    st = conectionJDBC().prepareCall(sqlupdate);
                    st.executeUpdate();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
    }
    public static DefaultTableModel LoadDanhSachTra( String ma){
        
             DefaultTableModel model = new DefaultTableModel();
                    String[] ar = {"Mã độc giả", "Mã sách", "Ngày mượn", "Ngày hết hạn","Trạng thái"};
                    model.setColumnIdentifiers(ar);
                    String load = "SELECT * FROM QTMUON WHERE MADOCGIA='" + ma + "' AND NGAYTRA IS NULL";
                    ResultSet rs = getData(conectionJDBC(), load);
                    String[] arr = new String[5];
            try {
                while (rs.next()) {
                    arr[0] = rs.getString("MADOCGIA");
                    arr[1] = rs.getString("MASACH");
                    arr[2] = rs.getString("NGAYMUON");
                    arr[3] = rs.getString("NGAYHETHAN");
                    arr[4]=rs.getString("TRANGTHAI");
                    model.addRow(arr);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Mquanlimuontra.class.getName()).log(Level.SEVERE, null, ex);
            }
                    return model;
        
//        String load = "SELECT * FROM DOCGIA WHERE MADOCGIA='" + this.TextMaDocGiaTra.getText() + "'";
//            int temp = 0;
//            ResultSet rs = getData(conectionJDBC(), load);
//            try {
//                while (rs.next()) {
//                    temp = 1;
//                }
//                rs.close();
//                if (temp == 0) {
//                    this.LBTrangThaiDocGia.setVisible(true);
//                } else {
//                    DefaultTableModel model = new DefaultTableModel();
//                    String[] ar = {"Mã độc giả", "Mã sách", "Ngày mượn", "Ngày hết hạn"};
//                    model.setColumnIdentifiers(ar);
//                    load = "SELECT * FROM QTMUON WHERE MADOCGIA='" + this.TextMaDocGiaTra.getText() + "' AND NGAYTRA IS NULL";
//                    rs = getData(conectionJDBC(), load);
//                    String[] arr = new String[4];
//                    while (rs.next()) {
//                        arr[0] = rs.getString("MADOCGIA");
//                        arr[1] = rs.getString("MASACH");
//                        arr[2] = rs.getString("NGAYMUON");
//                        arr[3] = rs.getString("NGAYHETHAN");
//                        model.addRow(arr);
//                    }
//                    this.TableTra.setModel(model);
//                    this.LBTrangThaiDocGia.setVisible(false);
//                }
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//            }
    } 
    public static int TinhTienPhat(String ma) {
        String sql = "{?=call TINH_Phat(?,?)}";
        // int index=this.TableTra.getSelectedRow();
        int output = 0;
        try {
            CallableStatement st = conectionJDBC().prepareCall(sql);
            //st.setString(2, (String) this.TableTra.getValueAt(index, 0));
            st.setString(2, ma);
            st.setInt(3, 2000);
            st.registerOutParameter(1, Types.INTEGER);
            st.execute();
            output = st.getInt(1);
            // this.TextTienPhat.setText(String.valueOf(output));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return output;
    }
    public static void CapNhatTrangThaiMuon(){
        String sql="UPDATE QTMUON SET TRANGTHAI='phat' WHERE NGAYTRA IS NULL AND NGAYHETHAN < SYSDATE";
        try {
            PreparedStatement st=conectionJDBC().prepareCall(sql);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Mquanlimuontra.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void TraSach(JTable table, int index) {
        // int index=this.TableTra.getSelectedRow();
        //System.out.println(index);
        if (index < 0) {
            JOptionPane.showMessageDialog(null, "Không có sách được chọn");
            //return null;
        }
        //String tra="UPDATE QTMUON SET NGAYTRA=SYSDATE,TRANGTHAI=null WHERE MASACH='"+table.getValueAt(index, 1)+"' AND MADOCGIA='"+table.getValueAt(index, 0)+"' AND NGAYTRA IS NULL";
        String tra = "UPDATE QTMUON SET NGAYTRA=SYSDATE WHERE MASACH='" + table.getValueAt(index, 1) + "' AND MADOCGIA='" + table.getValueAt(index, 0) + "' AND NGAYTRA IS NULL";
        try {
            PreparedStatement st = conectionJDBC().prepareCall(tra);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(quanlimuontra.class.getName()).log(Level.SEVERE, null, ex);
        }
        String update = "UPDATE SACH SET TRANGTHAI='Chua muon' WHERE MASACH='" + table.getValueAt(index, 1) + "'";
        try {
            PreparedStatement st = conectionJDBC().prepareCall(update);
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Đã thanh toán tiền phạt");
        } catch (SQLException ex) {
            Logger.getLogger(quanlimuontra.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Đã trả sách");
        //this.setdefaultTra();
    }
    public static void TraTien(String ma){
        String temp="";
        PreparedStatement st;
        String tim="SELECT MASACH FROM SACH JOIN QTMUON ON SACH.MASACH=QTMUON.MASACH WHERE TRANGTHAI='phat' AND MADOCGIA='"+ma+"' AND NGAYTRA IS NULL";
        try {
            ResultSet rs=getData(conectionJDBC(),tim);
            while(rs.next()){
                temp=rs.getString("MASACH");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Mquanlimuontra.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql1="UPDATE QTMUON SET NGAYTRA=SYSDATE WHERE MADOCGIA='" + ma + "' AND NGAYTRA IS NULL AND TRANGTHAI='phat'";
        try {
            st=conectionJDBC().prepareCall(sql1);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Mquanlimuontra.class.getName()).log(Level.SEVERE, null, ex);
        }
        String update = "UPDATE SACH SET TRANGTHAI='Chua muon' WHERE MASACH='" + temp + "'";
        try {
            st = conectionJDBC().prepareCall(update);
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Đã thanh toán tiền phạt");
        } catch (SQLException ex) {
            Logger.getLogger(quanlimuontra.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql2="UPDATE QTMUON SET TRANGTHAI=null WHERE MADOCGIA='"+ma+"' AND TRANGTHAI='phat'";
        try {
            st=conectionJDBC().prepareCall(sql2);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Mquanlimuontra.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static DefaultTableModel setDefaultMuon(){
        String sql = "SELECT * FROM PHIEUMUON";
        ResultSet rs = getData(conectionJDBC(), sql);
        DefaultTableModel model = new DefaultTableModel();
        String[] title = {"Mã phiếu mượn", "Mã sách", "Mã độc giả", "Ngày mượn", "Ngày hết hạn"};
        String[] ar = new String[6];
        model.setColumnIdentifiers(title);
        try {
            while (rs.next()) {
                ar[0] = rs.getString("MAPHIEUMUON");
                ar[1] = rs.getString("MASACH");
                ar[2] = rs.getString("MADOCGIA");
                ar[3] = rs.getString("NGAYMUON");
                ar[4] = rs.getString("NGAYHETHAN");
                //ar[5]=rs.getString("TRANGTHAI");
                model.addRow(ar);
            }
            //this.TableMuon.setModel(model);
        } catch (SQLException ex) {
            Logger.getLogger(quanlimuontra.class.getName()).log(Level.SEVERE, null, ex);
        }
        return model;
    } 
    public static DefaultTableModel setDefaultTra(){
         String sql="SELECT * FROM QTMUON WHERE NGAYTRA IS NULL";
        ResultSet rs=getData(conectionJDBC(), sql);
        DefaultTableModel model=new DefaultTableModel();
        String[] title={"Mã độc giả","Mã sách","Ngày mượn","Ngày hết hạn","Trạng thái"};
        model.setColumnIdentifiers(title);
                String[] ar=new String[5];
        try {
            while(rs.next()){
                ar[0]=rs.getString("MADOCGIA");
                ar[1]=rs.getString("MASACH");
                ar[2]=rs.getString("NGAYMUON");
                ar[3]=rs.getString("NGAYHETHAN");
                ar[4]=rs.getString("TRANGTHAI");
                model.addRow(ar);
            }
            //this.TableTra.setModel(model);
        } catch (SQLException ex) {
            Logger.getLogger(quanlimuontra.class.getName()).log(Level.SEVERE, null, ex);
        }
        return model;
    }
}
