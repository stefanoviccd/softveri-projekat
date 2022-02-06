/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.biblioteka.so.author;

import rs.ac.bg.fon.ps.biblioteka.repository.impl.RepositoryAuthor;
import rs.ac.bg.fon.ps.biblioteka.so.AbstractSO;

/**
 *
 * @author Dragana Stefanovic
 */
public class GetAllAuthorsSO extends AbstractSO{
    RepositoryAuthor repositoryAuthor;

    public GetAllAuthorsSO() {
        repositoryAuthor=new RepositoryAuthor();
    }
    

    @Override
    protected void precondition(Object param) throws Exception {
        //no precondition to check
           }

    @Override
    protected Object executeOperation(Object param) throws Exception {
       return repositoryAuthor.getAll();
    }
    
}
