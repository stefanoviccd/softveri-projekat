/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package rs.ac.bg.fon.ps.bibliotekaklijent.view;

import java.io.IOException;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.ps.biblioteka.bibilotekaklijent.controller.ControllerUI;
import rs.ac.bg.fon.ps.biblioteka.communication.CurrentUser;
import rs.ac.bg.fon.ps.biblioteka.communication.Response;
import rs.ac.bg.fon.ps.biblioteka.communication.ResponseType;
import rs.ac.bg.fon.ps.biblioteka.model.Librarian;
import rs.ac.bg.fon.ps.bibliotekaklijent.validation.ValidationException;
import rs.ac.bg.fon.ps.bibliotekaklijent.validation.Validator;

/**
 *
 * @author Dragana Stefanovic
 */
public class FrmLogin extends javax.swing.JFrame {
    

    private static Librarian currentUser;
    private FrmMain frmMain;

    public FrmMain getFrmMain() {
        return frmMain;
    }
    

    /**
     * Creates new form FrmLogin
     */
    public FrmLogin() {
        initComponents();
        FrameCenter.CenteredFrame(this);
        setTitle("Prijava");

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblUsername = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        lblPassword = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(100, 100, 0, 0));

        lblUsername.setText("Username");

        txtUsername.setText("worker");

        lblPassword.setText("Password");

        txtPassword.setText("worker");

        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnLogin, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                    .addComponent(txtUsername)
                    .addComponent(txtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        try {
            validateForm();
            String user = txtUsername.getText().trim();
            String password = String.valueOf(txtPassword.getPassword());
            currentUser = ControllerUI.getInstance().login(user, password);
            CurrentUser.setCurrentUser(currentUser);
            JOptionPane.showMessageDialog(this, "Dobrodošli, " + currentUser.getUsername());
            this.dispose();
            frmMain=new FrmMain(currentUser);
            frmMain.setVisible(true);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Greska: " + "Server prestao sa radom.", "Greska", JOptionPane.ERROR_MESSAGE);
            this.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Greska: " + e.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            
        }

    }//GEN-LAST:event_btnLoginActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables

    private void validateForm() throws ValidationException {
        Validator.startValidation().validateNotNullOrEmpty(txtUsername.getText(), "Morate uneti korisnicko ime!")
                .validateNotNullOrEmpty(String.valueOf(txtPassword.getPassword()), "Morate uneti sifru!")
                .throwIfInvalide();
    }

    public void login(Response response) {
        if (response.getResponseType().equals(ResponseType.ERROR)) {
            JOptionPane.showMessageDialog(this, "Greska: " + response.getException().getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            currentUser = (Librarian) response.getResult();
            CurrentUser.setCurrentUser(currentUser);
            JOptionPane.showMessageDialog(this, "Dobrodošli, " + currentUser.getUsername());
            this.dispose();
            FrmMain frm = new FrmMain(currentUser);

            frm.setVisible(true);
        }

    }

}
