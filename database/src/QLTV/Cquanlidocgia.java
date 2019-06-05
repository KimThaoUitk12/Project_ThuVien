/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QLTV;

import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author sam
 */
public class Cquanlidocgia {
    public static boolean CheckNgayThang(Date a,Date b){
        if(!(a==null)&&!(b==null)){
            return true;
        }else{
             JOptionPane.showMessageDialog(null, "Thiếu ngày tháng");
            return false;
        }
    }

    public static int ChonKieuDuyet(boolean bl1, boolean bl2) {
        if (bl1 && bl2) {
            JOptionPane.showMessageDialog(null, "Chỉ được chọn 1 kiểu duyệt");
            return 0;
        } else {
            if (!bl1 && !bl2) {
                JOptionPane.showMessageDialog(null, "Phải chọn 1 kiểu duyệt");
                return 0;
            } else {
                if (bl1 && !bl2) {
                    return 1;
                } else {
                    return 2;
                }
            }

        }

    }
}
