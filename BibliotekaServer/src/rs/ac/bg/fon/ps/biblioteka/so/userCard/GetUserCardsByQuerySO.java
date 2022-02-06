/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.biblioteka.so.userCard;

import java.util.ArrayList;
import java.util.List;
import rs.ac.bg.fon.ps.biblioteka.model.Book;
import rs.ac.bg.fon.ps.biblioteka.model.UserCard;
import rs.ac.bg.fon.ps.biblioteka.repository.impl.RepositoryUserCard;
import rs.ac.bg.fon.ps.biblioteka.so.AbstractSO;

/**
 *
 * @author Dragana Stefanovic
 */
public class GetUserCardsByQuerySO extends AbstractSO{
    RepositoryUserCard repositoryUserCard;

    public GetUserCardsByQuerySO() {
        repositoryUserCard=new RepositoryUserCard();
    }
    
    @Override
    protected void precondition(Object param) throws Exception {
        if(param==null || !(param instanceof String))
            throw new Exception("Poslati objekat je neodgovarajuceg tipa!");
    }

    @Override
    protected Object executeOperation(Object param) throws Exception {
        List<UserCard> cards = new ArrayList<>();
        String query=(String) param;
        try {
            cards = repositoryUserCard.getByQuery(query);
            return cards;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Greska prilikom ucitavanja knjiga.", e);
        }
        
    }
    
}
