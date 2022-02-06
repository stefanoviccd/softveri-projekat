/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.biblioteka.so.book;

import java.util.List;
import rs.ac.bg.fon.ps.biblioteka.model.Author;
import rs.ac.bg.fon.ps.biblioteka.model.Book;
import rs.ac.bg.fon.ps.biblioteka.repository.impl.RepositoryAuthor;
import rs.ac.bg.fon.ps.biblioteka.repository.impl.RepositoryBook;
import rs.ac.bg.fon.ps.biblioteka.so.AbstractSO;
import rs.ac.bg.fon.ps.biblioteka.so.validator.BookValidator;
import rs.ac.bg.fon.ps.biblioteka.so.validator.ValidationException;

/**
 *
 * @author Dragana Stefanovic
 */
public class AddBookSO extends AbstractSO {

    RepositoryBook repositoryBook;
    RepositoryAuthor repositoryAuthor;

    public AddBookSO() {
        repositoryBook = new RepositoryBook();
        repositoryAuthor = new RepositoryAuthor();
    }

    @Override
    protected void precondition(Object param) throws Exception {
        if (param == null || !(param instanceof Book)) {
            throw new Exception("Poslati objekat je neodgovarajuceg tipa!");
        } else {
            Book book = (Book) param;
            checkValueConstraints(book);
            checkStructuralConstraints(book);
        }
    }

    @Override
    protected Object executeOperation(Object param) throws Exception {
        Book book = (Book) param;
        repositoryBook.connect();
        List<Author> dbAuthors = repositoryAuthor.getByQuery("SELECT * FROM autor WHERE imePrezime='" + book.getAuthor().getAuthorName() + "'");
        try {
            if (dbAuthors.size() == 0) {
                repositoryAuthor.add(book.getAuthor());
                
            }
            Author a=repositoryAuthor.getByQuery("SELECT * FROM autor WHERE imePrezime='" + book.getAuthor().getAuthorName() + "'").get(0);
            book.setAuthor(a);
            repositoryBook.add(book);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Greska prilikom dodavanja knjige.", e);
        }

        return null;
    }

    public boolean exists(Book book) throws Exception {
        try {
            List<Book> dbBooks = repositoryBook.getAll();
            for (Book dbBook : dbBooks) {
                if (dbBook.getBookName().equals(book.getBookName()) && dbBook.getAuthor().getAuthorId().equals(book.getAuthor().getAuthorId())) {
                    return true;
                }

            }
        } catch (Exception ex) {ex.printStackTrace();
            throw new Exception("Greska prilikom provere uslova: AddBookSO");
            
        }
        return false;
    }

    private void checkValueConstraints(Book param) throws ValidationException {
        BookValidator.startValidation().validateValueIsPositive(param.getNumberInStock(), "Kolicina mora biti veca od 0!").throwIfInvalide();

    }

    private void checkStructuralConstraints(Book param) throws ValidationException {
        BookValidator.startValidation().validateNotNull(param.getAuthor(), "Autor knjige mora biti dodat!")
                .validateAlreadyExists(param, this, "Knjiga postoji u bazi!")
                .throwIfInvalide();
    }

}
