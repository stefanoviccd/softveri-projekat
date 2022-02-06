/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.biblioteka.so.book;

import java.util.List;
import rs.ac.bg.fon.ps.biblioteka.db.DbConnectionFactory;
import rs.ac.bg.fon.ps.biblioteka.model.Author;
import rs.ac.bg.fon.ps.biblioteka.model.Book;
import rs.ac.bg.fon.ps.biblioteka.model.Rent;
import rs.ac.bg.fon.ps.biblioteka.repository.impl.RepositoryAuthor;
import rs.ac.bg.fon.ps.biblioteka.repository.impl.RepositoryBook;
import rs.ac.bg.fon.ps.biblioteka.so.AbstractSO;
import java.sql.Statement;
import rs.ac.bg.fon.ps.biblioteka.repository.impl.RepositoryRent;

/**
 *
 * @author Dragana Stefanovic
 */
public class DeleteBookSO extends AbstractSO {

    private RepositoryBook repositoryBook;
    private RepositoryAuthor repositoryAuthor;
    private RepositoryRent repositoryRent;

    public DeleteBookSO() {
        repositoryBook = new RepositoryBook();
        repositoryAuthor = new RepositoryAuthor();
        repositoryRent = new RepositoryRent();
    }

    @Override
    protected void precondition(Object param) throws Exception {
        if (param == null || !(param instanceof Book)) {
            throw new Exception("Poslati objekat je neodgovarajuceg tipa!");
            
        } else {
            Book book = (Book) param;
            try {
                checkStructuralConstraints(book);
              
            } catch (Exception ex) {
                throw new Exception(ex.getMessage());
            }
        }
    }

    @Override
    protected Object executeOperation(Object param) throws Exception {
        Book b = (Book) param;
        repositoryRent.deleteBookRents(b);
        repositoryBook.delete(b);
        try {
            removeAuthor(b.getAuthor());
        } catch (Exception e) {
            System.out.println("Autor nije obrisan.");
        }
        return null;
    }

    private void checkStructuralConstraints(Book book) throws Exception {
        checkIfRentsExist(book);
    }

    private void checkIfRentsExist(Book book) throws Exception {
        try {
            String query = " WHERE knjigaId=" + book.getBookid() + " AND datumVracanja IS NULL";
            List<Rent> rents = repositoryRent.getByQuery(query);
            if (rents!=null && rents.size() > 0) {
                throw new Exception("Primerci knjige su zaduzeni. Nije moguce dovrsiti operaciju brisanja.");
            }
        } catch (Exception ex) {
            throw ex;
        }

    }

    private void removeAuthor(Author author) throws Exception {

        boolean exist = repositoryBook.checkIfBooksExist(author);
        Statement statement = DbConnectionFactory.getInstance().getConnection().createStatement();

        if (exist) {
            throw new Exception("Knjige ovog autora postoje na stanju. Nije moguce dovrsiti operaciju brisanja.");
        } else {
            repositoryAuthor.delete(author);
        }
    }

}
