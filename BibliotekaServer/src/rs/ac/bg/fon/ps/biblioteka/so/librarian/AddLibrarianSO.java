/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.biblioteka.so.librarian;

import rs.ac.bg.fon.ps.biblioteka.broker.DatabaseBroker;
import rs.ac.bg.fon.ps.biblioteka.model.Librarian;
import rs.ac.bg.fon.ps.biblioteka.repository.impl.RepositoryLibrarian;
import rs.ac.bg.fon.ps.biblioteka.so.AbstractSO;

/**
 *
 * @author Dragana Stefanovic
 */
public class AddLibrarianSO extends AbstractSO{
    private RepositoryLibrarian repositoryLibrarian;
    private DatabaseBroker dbBroker;

    public AddLibrarianSO() {
        repositoryLibrarian=new RepositoryLibrarian();
        dbBroker=new DatabaseBroker();
    }
    
            

    @Override
    protected void precondition(Object param) throws Exception {
         if (param == null || !(param instanceof Librarian)) {
            throw new Exception("Poslati objekat je neodgovarajuceg tipa!");
        } else {
            Librarian librarian=(Librarian) param;
            //   checkValueConstraints(user);
            checkStructuralConstraints(librarian);
        }
        }

    @Override
    protected Object executeOperation(Object param) throws Exception {
        repositoryLibrarian.add((Librarian)param);
        return null;
       }

    private void checkStructuralConstraints(Librarian librarian) throws Exception {
         boolean exists = checkIfExists(librarian);
        if (exists) {
            throw new Exception("Korisnicko ime je u upotrebi. Pokusajte ponovo.");
        }
            }

    private boolean checkIfExists(Librarian librarian) throws Exception {
         try {
            return repositoryLibrarian.checkIfExists(librarian);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Greska prilikom provere postojanja bibliotekara u bazi.", e);
        }
           }
    
}
