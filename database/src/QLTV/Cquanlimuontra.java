/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QLTV;

import static database.Mquanlimuontra.CheckMaSach;
import static database.Mquanlimuontra.LayTrangThaiSach;
import static database.Mquanlimuontra.ThayDoiTrangThaiSach;
import static database.Mquanlimuontra.ThemPhieuMuon;
import static database.Mquanlimuontra.ThemVaoQTMuon;
import static database.Mquanlimuontra.checkDG;
import static database.Mquanlimuontra.getSoSachDangMuon;
import static database.Mquanlimuontra.getSoSachQuaHan;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author sam
 */
public class Cquanlimuontra {
    public static boolean LoadDG(String ma){
        if(checkDG(ma)){
//            muon=getSoSachDangMuon(ma);
//            quahan=getSoSachQuaHan(ma);
            return true;
        }else{
            return false;
        }
    }
    public static String KiemTraMaSach(String masach){
        if(CheckMaSach(masach)){
           return LayTrangThaiSach(masach);
        }else{
            return "";
        }
    }
    public static boolean CheckDanhSachTam(JTable table,int temp,String masach){
         for (int i = 0; i < temp; i++) {
                if (table.getValueAt(i, 0).equals(masach)) {
                    //this.BThemPhieuMuon.setEnabled(false);
                    JOptionPane.showMessageDialog(null, "Đã chọn sách này");
                    return false;
                }
            }
         return true;
    }
    public static void ChoMuonSach(JTable table,int sodongtam,String madg, String maphieumuon){
        ThemVaoQTMuon(table,sodongtam,madg);
        ThemPhieuMuon(table,sodongtam,maphieumuon,madg);
        ThayDoiTrangThaiSach(table, sodongtam);
    }
}
