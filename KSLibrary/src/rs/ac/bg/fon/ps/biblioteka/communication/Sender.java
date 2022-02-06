/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.biblioteka.communication;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dragana Stefanovic
 */
public class Sender {
    private Socket soket;

    public Sender(Socket soket) {
        this.soket = soket;
    }
    
    public synchronized void send(Object object) throws Exception{
        ObjectOutputStream out=null;
        try {
            out = new ObjectOutputStream(new BufferedOutputStream(soket.getOutputStream()));
            out.writeObject(object);
            out.flush();
            
        } catch (IOException ex) {
           throw new Exception("Error sending object."+ex.getMessage());
        } 
        }
     
    
}
