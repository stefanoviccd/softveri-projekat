/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.biblioteka.so.userCategory;

import rs.ac.bg.fon.ps.biblioteka.repository.impl.RepositoryUserCategory;
import rs.ac.bg.fon.ps.biblioteka.so.AbstractSO;

/**
 *
 * @author Dragana Stefanovic
 */
public class GetAllUserCategoriesSO  extends AbstractSO{
    RepositoryUserCategory repositoryUserCategory;

    public GetAllUserCategoriesSO() {
        repositoryUserCategory= new RepositoryUserCategory();
    }
    

    @Override
    protected void precondition(Object param) throws Exception {
       
    }

    @Override
    protected Object executeOperation(Object param) throws Exception {
        return repositoryUserCategory.getAll();
    }
    
}
