/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package rs.ac.bg.fon.ps.bibliotekaklijent.view;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import rs.ac.bg.fon.ps.biblioteka.bibilotekaklijent.controller.ControllerUI;
import rs.ac.bg.fon.ps.biblioteka.model.Rent;
import rs.ac.bg.fon.ps.biblioteka.model.User;
import rs.ac.bg.fon.ps.bibliotekaklijent.validation.ValidationException;
import rs.ac.bg.fon.ps.bibliotekaklijent.validation.Validator;
import rs.ac.bg.fon.ps.bibliotekaklijent.view.tableModel.TableModelBook;
import rs.ac.bg.fon.ps.bibliotekaklijent.view.tableModel.TableModelRentedBook;
import rs.ac.bg.fon.ps.bibliotekaklijent.view.tableModel.TableModelUser;

/**
 *
 * @author Dragana Stefanovic
 */
public class FrmRestoreBook extends javax.swing.JDialog {

    /**
     * Creates new form FrmRestoreBook
     */
    public FrmRestoreBook(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        FrameCenter.CenteredFrame(this);
        prepareForm();
        setTitle("Razduživanje knjige");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblClanskaKarta = new javax.swing.JLabel();
        txtClanskaKarta = new javax.swing.JTextField();
        btnFindUser = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblUsers = new javax.swing.JTable();
        tbtRentedBooks = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblBooks = new javax.swing.JTable();
        lblClanskaKarta1 = new javax.swing.JLabel();
        txtImePrezime = new javax.swing.JTextField();
        btnFindUserByname = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        btnRestore = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Razduživanje knjige"));
        jPanel1.setPreferredSize(new java.awt.Dimension(812, 432));

        lblClanskaKarta.setText("Pretraži broj članske karte");

        btnFindUser.setText("Pronađi korisnika");
        btnFindUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindUserActionPerformed(evt);
            }
        });

        tblUsers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "Ime", "Prezime", "Telefon", "Adresa"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblUsers);

        tbtRentedBooks.setText("Zadužene knjige");

        tblBooks.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Naziv", "Autor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblBooks);

        lblClanskaKarta1.setText("Pretraži ime i prezime");

        btnFindUserByname.setText("Pronađi korisnika");
        btnFindUserByname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindUserBynameActionPerformed(evt);
            }
        });

        jButton1.setText("Prikazi  zadužene knjige");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblClanskaKarta1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblClanskaKarta, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE))
                        .addGap(83, 83, 83)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtClanskaKarta, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                            .addComponent(txtImePrezime))
                        .addGap(43, 43, 43))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tbtRentedBooks, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnFindUser, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFindUserByname, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblClanskaKarta)
                    .addComponent(txtClanskaKarta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFindUser))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblClanskaKarta1)
                    .addComponent(txtImePrezime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFindUserByname))
                .addGap(52, 52, 52)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(tbtRentedBooks, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap(51, Short.MAX_VALUE))
        );

        btnRestore.setText("Razduži knjigu");
        btnRestore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestoreActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(btnRestore, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(522, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 689, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnRestore)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFindUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindUserActionPerformed
        try {
            validateInput(txtClanskaKarta);
            String cardNumber = txtClanskaKarta.getText().trim();
            List<User> clanovi = (List<User>) ControllerUI.getInstance().getUsersByUsersCard(cardNumber);
            if (clanovi == null || clanovi.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Sistem ne moze da pronadje clana.","Greska", JOptionPane.ERROR_MESSAGE);
                ((TableModelUser) tblUsers.getModel()).setUsers(new ArrayList<>());
            } else {
                JOptionPane.showMessageDialog(this, "Sistem je pronašao člana.");
                ((TableModelUser) tblUsers.getModel()).setUsers(clanovi);
  
            }

        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnFindUserActionPerformed

    private void btnRestoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestoreActionPerformed
        int selectedBook = tblBooks.getSelectedRow();
        if (selectedBook != -1) {
            try {
                restoreBook(selectedBook);
                JOptionPane.showMessageDialog(this,"Sistem je izvršio razduživanje knjige.");
               // JOptionPane.showMessageDialog(this,"Sistem ne može da izvrši razduživanje.","Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnRestoreActionPerformed

    private void btnFindUserBynameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindUserBynameActionPerformed
          try {
            validateInput(txtImePrezime);
            String name = txtImePrezime.getText().trim();
            List<User> users = ControllerUI.getInstance().getUsersByName(name);
            if (users == null || users.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Sistem ne moze da pronadje clana po zadatoj vrednosti.", "Greska", JOptionPane.ERROR_MESSAGE);
                ((TableModelUser) tblUsers.getModel()).setUsers(new ArrayList<>());
                ((TableModelRentedBook) tblBooks.getModel()).setRents(new ArrayList<>());
            } else {
                JOptionPane.showMessageDialog(this, "Sistem je pronašao člana.");
                ((TableModelUser) tblUsers.getModel()).setUsers(users);
            }
        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnFindUserBynameActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         int selectedRow=tblUsers.getSelectedRow();
         if(selectedRow!=-1){
          User u = ((TableModelUser)tblUsers.getModel()).getUser(selectedRow);
                List<Rent> userRents;
             try {
                 userRents = ControllerUI.getInstance().getUserRents(u);
                 ((TableModelRentedBook) tblBooks.getModel()).setRents(userRents);
             } catch (Exception ex) {
                 Logger.getLogger(FrmRestoreBook.class.getName()).log(Level.SEVERE, null, ex);
             }
               }
       
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(FrmRestoreBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmRestoreBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmRestoreBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmRestoreBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmRestoreBook dialog = new FrmRestoreBook(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFindUser;
    private javax.swing.JButton btnFindUserByname;
    private javax.swing.JButton btnRestore;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblClanskaKarta;
    private javax.swing.JLabel lblClanskaKarta1;
    private javax.swing.JTable tblBooks;
    private javax.swing.JTable tblUsers;
    private javax.swing.JLabel tbtRentedBooks;
    private javax.swing.JTextField txtClanskaKarta;
    private javax.swing.JTextField txtImePrezime;
    // End of variables declaration//GEN-END:variables

    private void prepareForm() {
        TableModelUser tmu = new TableModelUser();
        tmu.setUsers(new ArrayList<>());
        tblUsers.setModel(tmu);
        TableModelRentedBook tmb = new TableModelRentedBook(new ArrayList<>());
        tblBooks.setModel(tmb);
    }

    private void validateInput(JTextField txtClanskaKarta) throws ValidationException {
        Validator.startValidation().validateNotNullOrEmpty(txtClanskaKarta.getText(), "Unesite vrednost pretrage!").throwIfInvalide();
    }

    private void restoreBook(int selectedRental) throws Exception {
        Rent rental = ((TableModelRentedBook) tblBooks.getModel()).getRent(selectedRental);
        ControllerUI.getInstance().restoreBook(rental);
        ((TableModelRentedBook) tblBooks.getModel()).deleteRent(selectedRental);
        ((TableModelRentedBook) tblBooks.getModel()).fireTableDataChanged();

    }
}
