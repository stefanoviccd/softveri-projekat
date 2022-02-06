/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.biblioteka.so.rent;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import rs.ac.bg.fon.ps.biblioteka.communication.Response;
import rs.ac.bg.fon.ps.biblioteka.communication.ResponseType;
import rs.ac.bg.fon.ps.biblioteka.model.Book;
import rs.ac.bg.fon.ps.biblioteka.model.User;
import rs.ac.bg.fon.ps.biblioteka.model.UserCard;
import rs.ac.bg.fon.ps.biblioteka.repository.impl.RepositoryBook;
import rs.ac.bg.fon.ps.biblioteka.repository.impl.RepositoryRent;
import rs.ac.bg.fon.ps.biblioteka.repository.impl.RepositoryUser;
import rs.ac.bg.fon.ps.biblioteka.so.AbstractSO;
import rs.ac.bg.fon.ps.biblioteka.so.book.GetBooksByQuerySO;

/**
 *
 * @author Dragana Stefanovic
 */
public class RentBookSO extends AbstractSO {

    RepositoryRent repositoryRent;
    RepositoryBook repositoryBook;

    public RentBookSO() {
        repositoryRent = new RepositoryRent();
        repositoryBook=new RepositoryBook();
    }

    @Override
    protected void precondition(Object param) throws Exception {
        if (param == null || ((List<Object>) param).size() < 2) {
            throw new Exception("Poslati objekat je neodgovarajuceg tipa!");
        }
        if (!(((List<Object>) param).get(0) instanceof User) || !(((List<Object>) param).get(1) instanceof Book)) {
            throw new Exception("Poslati argumenti su neodgovarajuceg tipa!");
        } else {
            User user = (User) ((List<Object>) param).get(0);
            Book book = (Book) ((List<Object>) param).get(1);
            checkValueConstraints(user, book);
            checkStructuralConstraints(user, book);
        }
    }

    private void checkValueConstraints(User user, Book book) throws Exception {
        //TO DO -proveriti da li je clanska karta istekla
        checkUserCardIsValid(user.getUsercard());
    }

    private void checkStructuralConstraints(User user, Book book) throws Exception {
        int userRents;
        int booksOnStorage;
        userRents = checkUserRents(user);
        if (userRents == 2) {
            throw new Exception("Korisnik ima dve zadužene knjige!");
        }

        booksOnStorage = checkBookCount(book);
        if (booksOnStorage <= 0) {
            throw new Exception("Nema knjiga na stanju!");
        }

    }

    @Override
    protected Object executeOperation(Object param) throws Exception {
        User u = (User) ((List<Object>) param).get(0);
        Book b = (Book) ((List<Object>) param).get(1);
        try {
            repositoryRent.rentBook(u, b);
            updateBookCount(b,-1);
        } catch (Exception ex) {

            throw new Exception(ex.getMessage());
        }
        return null;
    }

    private int checkUserRents(User u) throws Exception {
        return repositoryRent.getUserRents(u).size();
    }

   

    private void checkUserCardIsValid(UserCard usercard) throws Exception {
       if(usercard.getExpiryDate().isBefore(LocalDate.now()))
           throw new Exception("Clanska karta korisnika je istekla. Nije moguce iznajmiti knjigu.");
    }

    private void updateBookCount(Book b, int i) throws SQLException, IOException {
        repositoryBook.updateBookCount(b,i);
        
         }

    private int checkBookCount(Book book) throws Exception {
        return repositoryBook.checkBookCount(book);
        }
}
