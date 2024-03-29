/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JFrame;

import database.Database;
import static database.MTimKiem.TaoCBBNXB;
import static database.MTimKiem.TaoCBBTheLoai;
import static database.MTimKiem.Tim;
import static database.MTimKiem.setDefaultTim;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class Timkiem extends javax.swing.JFrame implements ActionListener {

    public Timkiem() throws SQLException {
        initComponents();
        TaoCBBTheLoai(this.CBBoxTheLoai);
        TaoCBBNXB(this.CBBoxNXB);
        this.BHuy.addActionListener(this);
        this.BTim.addActionListener(this);
        //this.SetDefaultForSearch();
//        init();
//        init1();
    }
//    private void init(){
//          DefaultComboBoxModel model=new DefaultComboBoxModel();
//          model.addElement("Van hoc nuoc ngoai");
//          model.addElement("Van hoc trong ngoai");
//          model.addElement("Truyen ngan");
//          model.addElement("Bao");
//          model.addElement("Tu lieu tham khao");
//          CBBoxTheLoai.setModel(model);
//         
//      }
//    private void init1(){
//          DefaultComboBoxModel model=new DefaultComboBoxModel();
//          model.addElement("NXB Kim Dong");
//          model.addElement("NXB Tre");
//          model.addElement("NXB Giao duc");
//          model.addElement("NXB Đai hoc quoc gia TP HCM");
//          model.addElement("NXB Lao dong");
//          CBBoxNXB.setModel(model);
//         
//      }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        TextSach = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        TextTacGia = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        BTim = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        BHuy = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        CBBoxTheLoai = new javax.swing.JComboBox<>();
        CBBoxNXB = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tìm kiếm thông tin sách");

        jLabel1.setBackground(new java.awt.Color(255, 0, 0));
        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel1.setText("Tìm kiếm thông tin sách");
        jLabel1.setOpaque(true);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Tên sách");

        TextSach.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Thể loại");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Tên tác giả");

        TextTacGia.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Nhà xuất bản");

        BTim.setText("Tìm kiếm");
        BTim.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0), 3));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã sách", "Tên sách", "Loại sách", "Nhà xuất bản", "Tác giả", "Trạng thái"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 668, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        BHuy.setText("Hủy");
        BHuy.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        CBBoxTheLoai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        CBBoxTheLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBBoxTheLoaiActionPerformed(evt);
            }
        });

        CBBoxNXB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TextSach, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                    .addComponent(CBBoxTheLoai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(99, 99, 99)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TextTacGia, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                    .addComponent(CBBoxNXB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(157, 157, 157))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 607, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(281, 281, 281)
                        .addComponent(BTim, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(BHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(18, 18, 18)
                            .addComponent(jButton3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton4))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(54, 54, 54)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 699, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(TextSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(TextTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(CBBoxTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CBBoxNXB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BTim)
                    .addComponent(BHuy))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CBBoxTheLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBBoxTheLoaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CBBoxTheLoaiActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Timkiem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Timkiem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Timkiem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Timkiem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Timkiem TK = new Timkiem();
                    TK.setVisible(true);
                } catch (SQLException ex) {
                    System.out.println("ket noi that bai");
                    Logger.getLogger(Timkiem.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BHuy;
    private javax.swing.JButton BTim;
    private javax.swing.JComboBox<String> CBBoxNXB;
    private javax.swing.JComboBox<String> CBBoxTheLoai;
    private javax.swing.JTextField TextSach;
    private javax.swing.JTextField TextTacGia;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (e.getSource().equals(BTim)) {
//            String Sach,TG,TL,NXB;
//            Sach=TextSach.getText();
//            TG=TextTacGia.getText();
//            TL=CBBoxTheLoai.getSelectedItem().toString();
//            NXB=CBBoxNXB.getSelectedItem().toString();
//            String sqlsearch= "select * from sach join theloai on sach.matheloai=theloai.matheloai join nhaxuatban on nhaxuatban.manxb=sach.manxb where TENSACH LIKE '%"+Sach+"%' AND TACGIA LIKE '%"+TG+"%' AND TENNXB LIKE '%"+NXB+"%' AND TENTHELOAI LIKE '%"+TL+"%'";
//             Object[] ar=new Object[7];int i=1;
//        String[] title={"stt","Mã sách","Tên sách","Loại sách","Nhà xuất bản","Tác giả","Trạng thái"};
//        DefaultTableModel model=new DefaultTableModel();
//        model.setColumnIdentifiers(title);
//       ResultSet rs=Database.getData(Database.conectionJDBC (),sqlsearch);
//            try {
//                while(rs.next()){
//                    ar[0]=i;i++;
//                    ar[1]=rs.getString("MASACH");
//                    ar[2]=rs.getString("TENSACH");
//                    ar[3]=rs.getString("TENTHELOAI");
//                    ar[4]=rs.getString("TENNXB");
//                    ar[5]=rs.getString("TACGIA");
//                    ar[6]=rs.getString("TRANGTHAI");
//                    model.addRow(ar);
//                }    } catch (SQLException ex) {
//                Logger.getLogger(Timkiem.class.getName()).log(Level.SEVERE, null, ex);
//                JOptionPane.showMessageDialog(null, "loc du lieu bi loi");
//            }
            jTable1.setModel((Tim(TextSach.getText(), TextTacGia.getText(), CBBoxNXB.getSelectedItem().toString(), CBBoxTheLoai.getSelectedItem().toString())));
        }
        if (e.getSource().equals(BHuy)) {
            this.jTable1.setModel(setDefaultTim());
        }
    }
//    public void SetDefaultForSearch(){
//        String sqlTheLoai="select * from theloai";
//         ResultSet rs=Database.getData(Database.conectionJDBC (),sqlTheLoai);
//         String temp;
//        try {
//            this.CBBoxTheLoai.removeAllItems();
//            this.CBBoxTheLoai.addItem("");
//            while(rs.next()){
//                temp=rs.getString("TENTHELOAI");
//                this.CBBoxTheLoai.addItem(temp);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(Timkiem.class.getName()).log(Level.SEVERE, null, ex);
//            JOptionPane.showMessageDialog(null, "loi khi lay du lieu the loai");
//        }
//        String sqlnxb="select * from nhaxuatban";
//         rs=Database.getData(Database.conectionJDBC (),sqlnxb);
//        try {
//            this.CBBoxNXB.removeAllItems();
//            this.CBBoxNXB.addItem("");
//            while(rs.next()){
//                temp=rs.getString("TENNXB");
//               this.CBBoxNXB.addItem(temp);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(Timkiem.class.getName()).log(Level.SEVERE, null, ex);
//            JOptionPane.showMessageDialog(null, "loi khi lay du lieu nha xuat ban");
//        }
//        //this.CBBoxNXB=new JComboBox(nxb);
//        this.BHuy.addActionListener(this);
//        this.BTim.addActionListener(this);
//        try {
//            String sqlinit="select * from sach join theloai on sach.matheloai=theloai.matheloai join nhaxuatban on nhaxuatban.manxb=sach.manxb";
//            Object[] ar=new Object[7];int i=1;
//            String[] title={"stt","Mã sách","Tên sách","Loại sách","Nhà xuất bản","Tác giả","Trạng thái"};
//            DefaultTableModel model=new DefaultTableModel();
//            model.setColumnIdentifiers(title);
//            rs=Database.getData(Database.conectionJDBC (),sqlinit);
//            while(rs.next()){
//                ar[0]=i;i++;
//                ar[1]=rs.getString("MASACH");
//                ar[2]=rs.getString("TENSACH");
//                ar[3]=rs.getString("TENTHELOAI");
//                ar[4]=rs.getString("TENNXB");
//                ar[5]=rs.getString("TACGIA");
//                ar[6]=rs.getString("TRANGTHAI");
//                model.addRow(ar);
//            }
//            jTable1.setModel((DefaultTableModel)model);
//        } catch (SQLException ex) {
//            Logger.getLogger(Timkiem.class.getName()).log(Level.SEVERE, null, ex);
//            JOptionPane.showMessageDialog(null, "loi khi lay du lieu");
//        }
//    }
}
