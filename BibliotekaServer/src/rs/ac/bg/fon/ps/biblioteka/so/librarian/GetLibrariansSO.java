/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.biblioteka.so.librarian;

import rs.ac.bg.fon.ps.biblioteka.repository.impl.RepositoryLibrarian;
import rs.ac.bg.fon.ps.biblioteka.so.AbstractSO;

/**
 *
 * @author Dragana Stefanovic
 */
public class GetLibrariansSO extends AbstractSO{
   private RepositoryLibrarian repositoryLibrarian;

    public GetLibrariansSO() {
        repositoryLibrarian=new RepositoryLibrarian();
    }
   

    @Override
    protected void precondition(Object param) throws Exception {
        //no recondition to check
           }

    @Override
    protected Object executeOperation(Object param) throws Exception {
        return repositoryLibrarian.getAll();
           }
    
}
