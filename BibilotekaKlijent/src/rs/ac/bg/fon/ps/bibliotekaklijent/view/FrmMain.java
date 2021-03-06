/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package rs.ac.bg.fon.ps.bibliotekaklijent.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.ps.biblioteka.bibilotekaklijent.client.Client;
import rs.ac.bg.fon.ps.biblioteka.bibilotekaklijent.client.thread.ServerStoppedListener;
import rs.ac.bg.fon.ps.biblioteka.bibilotekaklijent.communication.Communication;
import rs.ac.bg.fon.ps.biblioteka.bibilotekaklijent.controller.ControllerUI;
import rs.ac.bg.fon.ps.biblioteka.communication.CurrentUser;
import rs.ac.bg.fon.ps.biblioteka.model.Librarian;

/**
 *
 * @author Dragana Stefanovic
 */
public class FrmMain extends javax.swing.JFrame {
    private Librarian currentUser;

    /**
     * Creates new form MainForm
     */
    public FrmMain(Librarian currentUser) {
        initComponents();
        this.currentUser=currentUser;
        setTitle("Šabača biblioteka - glavna strana");
        txtUlogovanKorisnik.setText(CurrentUser.getCurrentUser().getUsername());
        txtUlogovanKorisnik.setEditable(false);
        FrameCenter.CenteredFrame(this);
        ImageIcon img = new ImageIcon("biblioteka.jpg");

        lblPhoto.setIcon(img);
        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
                try {

                    ControllerUI.getInstance().logout(currentUser);
                    System.exit(0);

                } catch (Exception ex) {
                    System.out.println("Konekcija prekinuta.");
                       System.exit(0);
                }
            }

            @Override
            public void windowClosed(WindowEvent e) {
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });

        ControllerUI.getInstance().setServerStoppedListener(new ServerStoppedListener() {
            @Override
            public void serverStopped() {
                stop();
            }
        });
    }

    private void stop() {
        JOptionPane.showMessageDialog(this, "Konekcija sa serverom je prekinuta. Molimo Vas, pokušajte kasnije.", "Prekid konekcije", JOptionPane.ERROR_MESSAGE);
        this.dispose();

        System.exit(0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jLabel2 = new javax.swing.JLabel();
        txtUlogovanKorisnik = new javax.swing.JTextField();
        lblPhoto = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jmiPretragaClanova = new javax.swing.JMenuItem();
        jmiDodajClana = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jmiSveKnjige = new javax.swing.JMenuItem();
        jmiDodajKnjigu = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jmiIznajmi = new javax.swing.JMenuItem();
        jmiRazduzi = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jmiStatistika = new javax.swing.JMenuItem();

        jMenu3.setText("File");
        jMenuBar2.add(jMenu3);

        jMenu4.setText("Edit");
        jMenuBar2.add(jMenu4);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(300, 300, 0, 0));
        setResizable(false);

        jLabel2.setText("Ulogovan korisnik: ");

        btnLogout.setText("Odjava");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        jMenu1.setText("Clanovi");

        jmiPretragaClanova.setText("Pretraga");
        jmiPretragaClanova.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiPretragaClanovaActionPerformed(evt);
            }
        });
        jMenu1.add(jmiPretragaClanova);

        jmiDodajClana.setText("Dodaj");
        jmiDodajClana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiDodajClanaActionPerformed(evt);
            }
        });
        jMenu1.add(jmiDodajClana);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Knjige");

        jmiSveKnjige.setText("Pretraga");
        jmiSveKnjige.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiSveKnjigeActionPerformed(evt);
            }
        });
        jMenu2.add(jmiSveKnjige);

        jmiDodajKnjigu.setText("Dodaj");
        jmiDodajKnjigu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiDodajKnjiguActionPerformed(evt);
            }
        });
        jMenu2.add(jmiDodajKnjigu);

        jMenuBar1.add(jMenu2);

        jMenu5.setText("Operacije");

        jmiIznajmi.setText("Iznajmi knjigu");
        jmiIznajmi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiIznajmiActionPerformed(evt);
            }
        });
        jMenu5.add(jmiIznajmi);

        jmiRazduzi.setText("Razduzi knjigu");
        jmiRazduzi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiRazduziActionPerformed(evt);
            }
        });
        jMenu5.add(jmiRazduzi);

        jMenuBar1.add(jMenu5);

        jMenu6.setText("Statistika");

        jmiStatistika.setText("Prikazi statistiku");
        jmiStatistika.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiStatistikaActionPerformed(evt);
            }
        });
        jMenu6.add(jmiStatistika);

        jMenuBar1.add(jMenu6);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtUlogovanKorisnik, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 220, Short.MAX_VALUE)
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
            .addComponent(lblPhoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblPhoto, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUlogovanKorisnik, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLogout))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jmiSveKnjigeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiSveKnjigeActionPerformed
        new FrmBooks(this, true).setVisible(true);
    }//GEN-LAST:event_jmiSveKnjigeActionPerformed

    private void jmiDodajClanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiDodajClanaActionPerformed
        JOptionPane.showMessageDialog(this, "Sistem je kreirao novog člana.");
        new FrmAddUser(this, true).setVisible(true);
    }//GEN-LAST:event_jmiDodajClanaActionPerformed

    private void jmiPretragaClanovaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiPretragaClanovaActionPerformed
        new FrmUsers(this, true).setVisible(true);
    }//GEN-LAST:event_jmiPretragaClanovaActionPerformed

    private void jmiDodajKnjiguActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiDodajKnjiguActionPerformed
        JOptionPane.showMessageDialog(this, "Sistem je kreirao novu knjigu.");
        new FrmAddBook(this, true).setVisible(true);
    }//GEN-LAST:event_jmiDodajKnjiguActionPerformed

    private void jmiStatistikaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiStatistikaActionPerformed
        new FrmStatistics(this, true).setVisible(true);
    }//GEN-LAST:event_jmiStatistikaActionPerformed

    private void jmiIznajmiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiIznajmiActionPerformed
        new FrmRentBook(this, true).setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_jmiIznajmiActionPerformed

    private void jmiRazduziActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiRazduziActionPerformed
        new FrmRestoreBook(this, true).setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_jmiRazduziActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        try {
            Communication.getInstance().logout(currentUser);
        } catch (Exception ex) {
            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);

        }
        this.dispose();
        try {
            new Client().connect();
        } catch (IOException ex) {
            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnLogoutActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogout;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jmiDodajClana;
    private javax.swing.JMenuItem jmiDodajKnjigu;
    private javax.swing.JMenuItem jmiIznajmi;
    private javax.swing.JMenuItem jmiPretragaClanova;
    private javax.swing.JMenuItem jmiRazduzi;
    private javax.swing.JMenuItem jmiStatistika;
    private javax.swing.JMenuItem jmiSveKnjige;
    private javax.swing.JLabel lblPhoto;
    private javax.swing.JTextField txtUlogovanKorisnik;
    // End of variables declaration//GEN-END:variables
}
