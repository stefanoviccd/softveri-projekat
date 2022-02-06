/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.biblioteka.so.librarian;

import rs.ac.bg.fon.ps.biblioteka.model.Librarian;
import rs.ac.bg.fon.ps.biblioteka.repository.impl.RepositoryLibrarian;
import rs.ac.bg.fon.ps.biblioteka.so.AbstractSO;

/**
 *
 * @author Dragana Stefanovic
 */
public class LogOutSO extends AbstractSO{
    private RepositoryLibrarian RepositoryLibrarian;

    public LogOutSO() {
        RepositoryLibrarian=new RepositoryLibrarian();
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
        Librarian l=(Librarian) param;
        RepositoryLibrarian.setUserIsLoggedOut(l);
        return null;
        
            }
    
}
