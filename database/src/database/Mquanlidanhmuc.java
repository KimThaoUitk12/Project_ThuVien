/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import static database.Database.TaoMa;
import static database.Database.conectionJDBC;
import static database.Database.getData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author sam
 */
public class Mquanlidanhmuc {
    public static void ThemNXB(String tennxb,String diachi,String sdt){
        String them="INSERT INTO NHAXUATBAN VALUES(?,?,?,?)";
            try {
                PreparedStatement st=conectionJDBC ().prepareCall(them);
                st.setString(1, TaoMa("nxb"));
                st.setString(2, tennxb);
                st.setString(3, diachi);
                st.setString(4, sdt);
                st.executeUpdate();
//                this.TableNXB.getSelectionModel().clearSelection();
//                this.setDefaultNXB();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
    }
    public static DefaultTableModel setDefaultNXB(){
         DefaultTableModel model = new DefaultTableModel();
        String[] ar = {"STT", "Mã NXB", "Tên NXB", "Địa chỉ", "Số điện thoại"};
        model.setColumnIdentifiers(ar);
        String sqldocgia = "SELECT * FROM NHAXUATBAN";
        String[] arr = new String[5];
        ResultSet rs = getData(conectionJDBC(), sqldocgia);
        try {
            int i = 1;
            while (rs.next()) {
                arr[0] = String.valueOf(i);
                arr[1] = rs.getString("MANXB");
                arr[2] = rs.getString("TENNXB");
                arr[3] = rs.getString("DIACHI");
                arr[4] = rs.getString("SDT");
                i++;
                model.addRow(arr);
            }
//            this.TableNXB.setModel(model);
//            this.BSuaNXB.setEnabled(false);
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return model;
    }
    public static void XoaNXB(String ma){
        //int index=this.TableNXB.getSelectedRow();
            String xoa="DELETE FROM NHAXUATBAN WHERE MANXB='"+ma+"'";
            try {
                PreparedStatement st=conectionJDBC ().prepareCall(xoa);
                st.executeUpdate();
                //this.TableNXB.getSelectionModel().clearSelection();
                //this.setDefaultNXB();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
    }
    public static void SuaNXB(String ma,String tennxb,String diachi,String sdt){
        String sua = "UPDATE NHAXUATBAN SET TENNXB=?, DIACHI=?, SDT=? WHERE MANXB='" + ma + "'";
            try {
                PreparedStatement st = conectionJDBC().prepareCall(sua);
                st.setString(1, tennxb);
                st.setString(2, diachi);
                st.setString(3, sdt);
                st.executeUpdate();
                //this.TableNXB.getSelectionModel().clearSelection();
               // this.TableNXB.removeAll();
                //this.setDefaultNXB();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
    }
}
