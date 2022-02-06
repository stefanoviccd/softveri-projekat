/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.biblioteka.communication;

import rs.ac.bg.fon.ps.biblioteka.model.Librarian;

/**
 *
 * @author Dragana Stefanovic
 */
public class CurrentUser {
    private static Librarian currentUser;

    public static void setCurrentUser(Librarian currentUser) {
        CurrentUser.currentUser = currentUser;
    }

    public static Librarian getCurrentUser() {
        return currentUser;
    }
    
    
    
}
