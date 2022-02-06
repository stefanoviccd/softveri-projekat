package rs.ac.bg.fon.ps.biblioteka.server;

import rs.ac.bg.fon.ps.biblioteka.form.ServerForm;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Dragana Stefanovic
 */
public class Server {

    public static void main(String[] args) {
        Server server = new Server();
        try {
            server.startServer();

        } catch (Exception ex) {
            System.out.println("Greska pri pokretanju servera: " + ex.getMessage());
        }
    }

    private void startServer() throws Exception {
        new ServerForm().setVisible(true);
    }

}
