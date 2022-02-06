/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.biblioteka.so.userCard;

import rs.ac.bg.fon.ps.biblioteka.model.UserCard;
import rs.ac.bg.fon.ps.biblioteka.repository.impl.RepositoryUserCard;
import rs.ac.bg.fon.ps.biblioteka.so.AbstractSO;

/**
 *
 * @author Dragana Stefanovic
 */
public class DeleteUserCardSO extends AbstractSO{
    RepositoryUserCard repositoryUserCard;

    public DeleteUserCardSO() {
        repositoryUserCard=new RepositoryUserCard();
    }
    

    @Override
    protected void precondition(Object param) throws Exception {
        if(param==null || !(param instanceof UserCard))
            throw new Exception("Postati parametar je neodgovarajuceg tipa!");
        
        }

    @Override
    protected Object executeOperation(Object param) throws Exception {
        repositoryUserCard.delete((UserCard) param);
        return null;
        
        
    }
    
}
