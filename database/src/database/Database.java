/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

//import java.sql.Connection;
//import java.sql.DriverManager;
//import javax.swing.JOptionPane;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.TimeZone;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
/**
 *
 * @author ADMIN
 */
public class Database {

   public static Connection conectionJDBC (){
       try{
           Class.forName("oracle.jdbc.driver.OracleDriver");
           Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system2", "system2");
          //System.out.println("thanh cong");
           return con;
       }catch(Exception e){
           JOptionPane.showMessageDialog(null, "ket noi that bai");
       }
       return null;
   }
   public static ResultSet getData(Connection con,String sql){
           ResultSet rs=null;
       try {
           Statement st=con.createStatement();
           rs=st.executeQuery(sql);
           return rs;
       } catch (SQLException ex) {
           Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
           JOptionPane.showMessageDialog(null, "lay du lieu that bai");
       }
       return rs;
   }
   public static boolean checkSession(String id){
       int temp=0;
       String sqlcheck="select * from taikhoan where STATESESSION='on' and TENTAIKHOAN='"+id+"'";
       ResultSet rs=getData(conectionJDBC (),sqlcheck);
       try {
           while(rs.next()){
               temp++;
           }
       } catch (SQLException ex) {
           Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
           System.out.println("loi check session");
       }
       if(temp==1){
           return true;
       }else{
           return false;
       }
   }
    public static int DemThamSo(String str) {
        int temp = 0;
        for (int i = 0; i < str.length(); i++) {
            // Nếu ký tự tại vị trí thứ i bằng 'a' thì tăng count lên 1
            if (str.charAt(i) == '?') {
                temp++;
            }
        }
            return temp;
    }
   public static void UpDateDB(JFrame j,String sql,String[] temp){
       try {
           PreparedStatement pr=conectionJDBC().prepareStatement(sql);
           for(int i=0;i<DemThamSo(sql);i++){
               pr.setString(i+1, temp[i]);
           }
           pr.execute();
       } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, "lỗi trong quá trình cập nhật");
           Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
   public static java.sql.Date ConvertUtilDateToSqlDate(java.util.Date date){
       java.sql.Date result=new java.sql.Date(date.getTime());
       return result;
   }
   public static int StringToInt(String str) {
        return Integer.parseInt(str.substring(2, 5));
    }
   public static String TaoMa(String str){
       String ma="", bang="",tam="";
       switch (str){
           case "thethuvien":{
               ma="MATHE";
               bang="THETHUVIEN";
               tam="00";
               break;
           }
           case "docgia":{
               ma="MADOCGIA";
               bang="DOCGIA";
               tam="DG";
               break;
           }
           case "nhanvien":{
               ma="MANHANVIEN";
               bang="NHANVIEN";
               tam="TT";
               break;
           }
           case "phieumuon":{
               ma="MAPHIEUMUON";
               bang="PHIEUMUON";
               tam="PM";
               break;
           }
           case "nxb":{
               ma="MANXB";
               bang="NHAXUATBAN";
               tam="CC";
               break;
           }
       }
        int dem = 1;
        Vector<String> v = new Vector<String>();
        String sql = "select "+ma+" FROM "+bang, temp = "";
        ResultSet rs = getData(conectionJDBC(), sql);
        try {
            while (rs.next()) {
                v.add(rs.getString(ma));
            }
            Collections.sort(v);
            for (int i = 0; i < v.size(); i++) {
                System.out.print(v.get(i) + " ");
            }
            if (dem >= v.size()) {
                temp += String.valueOf(StringToInt(v.get(v.size() - 1)) + 1);
            } else {
                for (int i = 0; i < v.size() - 1; i++) {
                    if (StringToInt(v.get(i + 1)) - StringToInt(v.get(i)) > 1) {
                        //System.out.println(StringToInt(v.get(i)) + 1);
                        temp += String.valueOf(StringToInt(v.get(i)) + 1);
                        break;
                    } else {
                        if (StringToInt(v.get(i + 1)) - StringToInt(v.get(i)) > 0) {
                            dem++;
                        }
                    }
                }
                System.out.println("dem:" + dem);
            }
            if (dem >= v.size()) {
//                temp += String.valueOf(StringToInt(v.get(v.size() - 1)) + 1);
                temp += String.valueOf(dem + 1);
            }
            //System.out.println(StringToInt(v.get(v.size()-1)) + 1);
            System.out.println("temp:" + temp);
            if (Integer.parseInt(temp) < 10) {
                temp = tam+"00" + temp;
            } else if (Integer.parseInt(temp) >= 10 && Integer.parseInt(temp) < 100) {
                temp = tam+"0" + temp;
            } else if (Integer.parseInt(temp) >= 100) {
                temp = tam + temp;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return temp;
   }
   public static java.util.Date StringToUttilDate(String str){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            // set formatter to use UTC (instead of JVM default timezone)
            java.util.Date dateutil=null;
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
            try {
                // parse it as midnight (no shift to 01:00)
                java.util.Date date = sdf.parse((String) str);
                 dateutil = new java.util.Date(date.getTime());
                //this.DateNgaySinh.setDate(dateutil);
            } catch (ParseException ex) {
               ex.printStackTrace();
            }
            return dateutil;
   }
   public static String DateToString(Date date){
       return new SimpleDateFormat("dd-MM-yyyy").format(new java.sql.Date(date.getTime()));
   }
    public static void main(String[] args) {
       
        
    }
   
}
