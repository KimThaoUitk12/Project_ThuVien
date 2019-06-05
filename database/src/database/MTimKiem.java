/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import JFrame.Timkiem;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author sam
 */
public class MTimKiem {

    public static DefaultTableModel Tim(String Sach, String TG, String NXB, String TL) {
        String sqlsearch = "select * from sach join theloai on sach.matheloai=theloai.matheloai join nhaxuatban on nhaxuatban.manxb=sach.manxb where TENSACH LIKE '%" + Sach + "%' AND TACGIA LIKE '%" + TG + "%' AND TENNXB LIKE '%" + NXB + "%' AND TENTHELOAI LIKE '%" + TL + "%'";
        Object[] ar = new Object[7];
        int i = 1;
        String[] title = {"stt", "Mã sách", "Tên sách", "Loại sách", "Nhà xuất bản", "Tác giả", "Trạng thái"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(title);
        ResultSet rs = Database.getData(Database.conectionJDBC(), sqlsearch);
        try {
            while (rs.next()) {
                ar[0] = i;
                i++;
                ar[1] = rs.getString("MASACH");
                ar[2] = rs.getString("TENSACH");
                ar[3] = rs.getString("TENTHELOAI");
                ar[4] = rs.getString("TENNXB");
                ar[5] = rs.getString("TACGIA");
                ar[6] = rs.getString("TRANGTHAI");
                model.addRow(ar);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Timkiem.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "loc du lieu bi loi");
        }
        return model;
    }
    public static JComboBox TaoCBBTheLoai(JComboBox jc){
        //JComboBox CBBoxTheLoai=new JComboBox();
        String sqlTheLoai="select * from theloai";
         ResultSet rs=Database.getData(Database.conectionJDBC (),sqlTheLoai);
         String temp;
            jc.removeAllItems();
            jc.addItem("");
        try {
            while(rs.next()){
                temp=rs.getString("TENTHELOAI");
                jc.addItem(temp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Timkiem.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "loi khi lay du lieu the loai");
        }
        return jc;
    }
    public static JComboBox TaoCBBNXB(JComboBox jc){
        String sqlnxb="select * from nhaxuatban";
        ResultSet rs=Database.getData(Database.conectionJDBC (),sqlnxb);
        //JComboBox CBBoxNXB=new JComboBox();
        try {
            jc.removeAllItems();
            jc.addItem("");
            while(rs.next()){
               String temp=rs.getString("TENNXB");
               jc.addItem(temp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Timkiem.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "loi khi lay du lieu nha xuat ban");
        }
        return jc;
    } 
    public static DefaultTableModel setDefaultTim(){
        DefaultTableModel model=new DefaultTableModel();
        try {
            String sqlinit="select * from sach join theloai on sach.matheloai=theloai.matheloai join nhaxuatban on nhaxuatban.manxb=sach.manxb";
            Object[] ar=new Object[7];int i=1;
            String[] title={"stt","Mã sách","Tên sách","Loại sách","Nhà xuất bản","Tác giả","Trạng thái"};
            //DefaultTableModel model=new DefaultTableModel();
            model.setColumnIdentifiers(title);
            ResultSet rs=Database.getData(Database.conectionJDBC (),sqlinit);
            while(rs.next()){
                ar[0]=i;i++;
                ar[1]=rs.getString("MASACH");
                ar[2]=rs.getString("TENSACH");
                ar[3]=rs.getString("TENTHELOAI");
                ar[4]=rs.getString("TENNXB");
                ar[5]=rs.getString("TACGIA");
                ar[6]=rs.getString("TRANGTHAI");
                model.addRow(ar);
            }
            //jTable1.setModel((DefaultTableModel)model);
        } catch (SQLException ex) {
            Logger.getLogger(Timkiem.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "loi khi lay du lieu");
        }
        return model;
    }
}
