/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.biblioteka.so.librarian;

import java.util.List;
import rs.ac.bg.fon.ps.biblioteka.model.Librarian;
import rs.ac.bg.fon.ps.biblioteka.repository.impl.RepositoryLibrarian;
import rs.ac.bg.fon.ps.biblioteka.so.AbstractSO;

/**
 *
 * @author Dragana Stefanovic
 */
public class LoginSO extends AbstractSO {

    private RepositoryLibrarian repositoryLibrarian;

    public LoginSO() {
        repositoryLibrarian = new RepositoryLibrarian();
    }

    @Override
    protected void precondition(Object param) throws Exception {
        if (param == null || !(param instanceof Librarian)) {
            throw new Exception("Poslati objekat je neodgovarajuceg tipa!");
        } 
        if(((Librarian)param).getUsername().equals(null) || ((Librarian)param).getPassword().equals(null))
            throw new Exception("Nisu poslati kredencijali!");
    }

    @Override
    protected Object executeOperation(Object param) throws Exception {
        List<Librarian> librarians;
        Librarian currentUser;
        Librarian user = (Librarian) param;
        try {
            librarians = (List<Librarian>) repositoryLibrarian.getAll();
            for (Librarian b : librarians) {
                if (b.getUsername().equals(user.getUsername()) && b.getPassword().equals(user.getPassword())) {
                    currentUser = b;
                    if(currentUser.isLoggedIn()==true){
                    throw  new Exception("Korisnik je vec prijavljen.");
                    }
                    repositoryLibrarian.setUserIsLoggedIn(currentUser);
                    return currentUser;
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception(ex.getMessage());
       
        }
        throw new Exception("Nepoznat korisnik!");

    }

}
