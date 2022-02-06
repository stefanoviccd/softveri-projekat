/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.biblioteka.bibilotekaklijent.communication;

import java.io.IOException;
import java.net.Socket;
import rs.ac.bg.fon.ps.biblioteka.communication.Operations;
import rs.ac.bg.fon.ps.biblioteka.communication.Receiver;
import rs.ac.bg.fon.ps.biblioteka.communication.Request;
import rs.ac.bg.fon.ps.biblioteka.communication.Response;
import rs.ac.bg.fon.ps.biblioteka.communication.Sender;
import rs.ac.bg.fon.ps.biblioteka.model.Librarian;

/**
 *
 * @author Dragana Stefanovic
 */
public class Communication {

    private static Communication instance;
    private Socket socket;
    private Receiver receiver;
    private Sender sender;

    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

    private Communication() {
    }

    public static Communication getInstance() {
        if (instance == null) {
            instance = new Communication();
        }
        return instance;

    }

    public Response login(Request request) throws Exception {
        //posalji zahtev
        sender.send(request);
       // System.out.println("Zahtev za prijavom na sistem je poslat.");
        return (Response) receiver.receive();

    }

    public Response sendRequest(Request request) throws Exception {
        sender.send(request);
        return (Response) receiver.receive();

    }

    public Response receiveResponse() throws Exception {
        return (Response) receiver.receive();

    }



    public void logout(Librarian user) throws Exception {
        try {
            Request request = new Request(Operations.LOGOUT, user);
            sender.send(request);

        } catch (Exception ex) {

            throw new Exception("Error in getting logging out: " + ex.getMessage());
        }

    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

}
