/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.biblioteka.so.user;

import java.util.List;
import rs.ac.bg.fon.ps.biblioteka.db.DbConnectionFactory;
import rs.ac.bg.fon.ps.biblioteka.model.User;
import rs.ac.bg.fon.ps.biblioteka.repository.impl.RepositoryUser;
import rs.ac.bg.fon.ps.biblioteka.so.AbstractSO;

/**
 *
 * @author Dragana Stefanovic
 */
public class GetAllUsersSO extends AbstractSO{
    RepositoryUser repositoryUser;

    public GetAllUsersSO() {
        repositoryUser=new RepositoryUser();
    }
    

    @Override
    protected void precondition(Object param) throws Exception {
        //no preconditions
         }

    @Override
    protected Object executeOperation(Object param) throws Exception {
        try { 
            String query = " ORDER BY c.ime ASC";
            return repositoryUser.getByQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Greska prilikom ucitavanja korisnika.", e);
        }
    }
    
}
