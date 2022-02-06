/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.biblioteka.so.bookCategory;

import rs.ac.bg.fon.ps.biblioteka.repository.impl.RepositoryBookCategory;
import rs.ac.bg.fon.ps.biblioteka.so.AbstractSO;

/**
 *
 * @author Dragana Stefanovic
 */
public class GetBookCategoryIdSO extends AbstractSO{
    RepositoryBookCategory repositoryBookCategory;

    public GetBookCategoryIdSO() {
        repositoryBookCategory=new RepositoryBookCategory();
    }
    

    @Override
    protected void precondition(Object param) throws Exception {
          if(param==null || !(param instanceof String))
            throw new Exception("Poslati objekat je neodgovarajuceg tipa!");
          }

    @Override
    protected Object executeOperation(Object param) throws Exception {
     return repositoryBookCategory.getBookCategoryId((String)param);
    }
    
}
