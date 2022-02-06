/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.biblioteka.so.book;

import java.util.ArrayList;
import java.util.List;
import rs.ac.bg.fon.ps.biblioteka.model.Book;
import rs.ac.bg.fon.ps.biblioteka.repository.impl.RepositoryBook;
import rs.ac.bg.fon.ps.biblioteka.so.AbstractSO;

/**
 *
 * @author Dragana Stefanovic
 */
public class GetBooksByQuerySO extends AbstractSO{
private RepositoryBook repositoryBook;

    public GetBooksByQuerySO() {
        repositoryBook=new RepositoryBook();
    }

   

    @Override
    protected void precondition(Object param) throws Exception {
        if(param==null || !(param instanceof String))
            throw new Exception("Poslati objekat je neodgovarajuceg tipa!");
    }

    @Override
    protected Object executeOperation(Object param) throws Exception {
        List<Book> books = new ArrayList<>();
        String query=(String) param;
        try {
            books = repositoryBook.getByQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Greska prilikom ucitavanja knjiga.", e);
        }
        return books;
    }
    
}
