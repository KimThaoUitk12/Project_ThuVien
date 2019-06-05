

package QLTV;

import java.sql.*;
import javax.swing.*;

public class User {
    public static Connection con=null;
    public static ResultSet rs=null;
    public static PreparedStatement pps=null;
    public String userName;
    public String pass;
    public void setuser(String username){
        this.userName=username;
    }
    public void setpass( String pass){
        this.pass=pass;
    }
    public String getuser (){
        return userName;
    }
    public String getpass(){
        return pass;
    }
    public static ResultSet btlogin(String user, String pass){
        String sql="SELECT * FROM userss WHERE username=? and password=?"; 
        try{
            pps=con.prepareCall(sql);
            pps.setString(1, user);
            pps.setString(2, pass);
             return rs=pps.executeQuery();
        }catch(Exception e){
            return rs=null;
        }
    }
}
