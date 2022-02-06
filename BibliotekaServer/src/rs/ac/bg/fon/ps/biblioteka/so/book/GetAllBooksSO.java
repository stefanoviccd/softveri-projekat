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
public class GetAllBooksSO extends AbstractSO {

    RepositoryBook repositoryBook;

    public GetAllBooksSO() {
        repositoryBook = new RepositoryBook();
    }
    

    @Override
    protected void precondition(Object param) throws Exception {
        //no precondidion to check

    }

    @Override
    protected Object executeOperation(Object books) throws Exception {
        books = new ArrayList<>();
        try {
            String query = " ORDER BY k.naziv ASC";
            repositoryBook.connect();
            books = repositoryBook.getByQuery(query);
            return books;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(" Greska prilikom ucitavanja knjiga.", e);
        }

    }

}
