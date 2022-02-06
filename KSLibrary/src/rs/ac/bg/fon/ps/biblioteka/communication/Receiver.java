/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.biblioteka.communication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dragana Stefanovic
 */
public class Receiver {

    private Socket soket;

    public Receiver(Socket soket) {
        this.soket = soket;
    }

    public synchronized Object receive() throws IOException, ClassNotFoundException {
        ObjectInputStream in;
        try {

            in = new ObjectInputStream(soket.getInputStream());
            return in.readObject();

        } catch (IOException ex) {

            throw new IOException("Error receiving object." + ex.getMessage());

        } catch (ClassNotFoundException ex) {

            throw new ClassNotFoundException("Error receiving object." + ex.getMessage());
        }

    }

}
