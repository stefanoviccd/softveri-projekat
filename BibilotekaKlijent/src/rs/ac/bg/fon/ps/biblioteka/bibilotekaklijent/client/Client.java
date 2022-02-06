/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package rs.ac.bg.fon.ps.biblioteka.bibilotekaklijent.client;

import java.awt.Window;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.net.Socket;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.ps.biblioteka.bibilotekaklijent.communication.Communication;
import rs.ac.bg.fon.ps.biblioteka.bibilotekaklijent.controller.ControllerUI;
import rs.ac.bg.fon.ps.biblioteka.communication.Operations;
import rs.ac.bg.fon.ps.biblioteka.communication.Receiver;
import rs.ac.bg.fon.ps.biblioteka.communication.Request;
import rs.ac.bg.fon.ps.biblioteka.communication.Response;
import rs.ac.bg.fon.ps.biblioteka.communication.Sender;
import rs.ac.bg.fon.ps.bibliotekaklijent.view.FrmLogin;
import rs.ac.bg.fon.ps.bibliotekaklijent.view.FrmMain;

/**
 *
 * @author Dragana Stefanovic
 */
public class Client {

    private FrmLogin frm;
    private FrmMain frmMain;

    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Client klijent = new Client();
        try {
            klijent.connect();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Server nije pokrenut.");
        }

    }

    public void connect() throws IOException {
        Socket soket = new Socket("localhost", 9000);
        System.out.println("Klijent se povezao..");
        Receiver receiver = new Receiver(soket);
        Sender sender = new Sender(soket);
        Communication.getInstance().setReceiver(receiver);
        Communication.getInstance().setSender(sender);
        ThreadListener listener = new ThreadListener(receiver, sender);

        frm = new FrmLogin();
        frm.setVisible(true);

    }

    class ThreadListener extends Thread {

        private Receiver receiver;
        private Sender sender;

        public ThreadListener(Receiver receiver, Sender sender) {
            this.receiver = receiver;
            this.sender = sender;
            start();
        }

        @Override
        public void run() {
            try {
                while (!isInterrupted()) {
                    sleep(3000);

                    Request request = new Request(Operations.STOP, null);
                    sender.send(request);

                    Response response = (Response) receiver.receive();
                    handleResponse(response);
                }

            } catch (Exception ex) {
                System.out.println("Prekinuta konekcija.");
                System.exit(0);
                
                

            }
        }

        private void handleResponse(Response response) {
            try {
                if (response != null && response.getOperation() == Operations.SERVER_STOPPED) {
                    Request request = new Request(Operations.STOP, null);
                    sender.send(request);

                    ControllerUI.getInstance().finish();
                    this.interrupt();
                } else {

                }
            } catch (Exception ex) {
                System.out.println("Konekcija prekinuta>> " + ex.getMessage());
            }
        }

    }
}
