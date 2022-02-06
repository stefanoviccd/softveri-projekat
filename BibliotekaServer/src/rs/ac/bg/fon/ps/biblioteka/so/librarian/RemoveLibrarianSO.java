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
public class RemoveLibrarianSO extends AbstractSO{
    private RepositoryLibrarian repositoryLibrarian;

    public RemoveLibrarianSO() {
        repositoryLibrarian=new RepositoryLibrarian();
    }
    

    @Override
    protected void precondition(Object param) throws Exception {
         if (param == null || !(param instanceof Librarian)) {
            throw new Exception("Poslati objekat je neodgovarajuceg tipa!");
        } else {
             Librarian lib=(Librarian) param;
            checkValueConstraints(lib);
        }
         }

    @Override
    protected Object executeOperation(Object param) throws Exception {
        repositoryLibrarian.delete((Librarian)param);
        return null;
          }

    private void checkValueConstraints(Librarian lib) throws Exception {
         if (lib.getId().equals(null)) {
            throw new Exception("Id radnika nije poslat!");
        }
            }
    
}
