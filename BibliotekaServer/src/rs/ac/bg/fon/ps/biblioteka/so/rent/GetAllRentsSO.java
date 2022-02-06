/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.biblioteka.so.rent;

import rs.ac.bg.fon.ps.biblioteka.repository.impl.RepositoryRent;
import rs.ac.bg.fon.ps.biblioteka.so.AbstractSO;

/**
 *
 * @author Dragana Stefanovic
 */
public class GetAllRentsSO extends AbstractSO{
    RepositoryRent repositoryRent;

    public GetAllRentsSO() {
        repositoryRent=new RepositoryRent();
    }
    

    @Override
    protected void precondition(Object param) throws Exception {
        //no precondition to check
    }

    @Override
    protected Object executeOperation(Object param) throws Exception {
        String query=" WHERE i.datumVracanja IS NULL";
       return repositoryRent.getByQuery(query);
    }
    
}
