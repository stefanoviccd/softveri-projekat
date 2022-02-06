/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.biblioteka.repository.impl;

import java.io.IOException;
import rs.ac.bg.fon.ps.biblioteka.db.DbConnectionFactory;
import rs.ac.bg.fon.ps.biblioteka.db.DbRepository;
import rs.ac.bg.fon.ps.biblioteka.model.Author;
import rs.ac.bg.fon.ps.biblioteka.model.BookCategory;
import rs.ac.bg.fon.ps.biblioteka.model.Book;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.ac.bg.fon.ps.biblioteka.broker.DatabaseBroker;
import rs.ac.bg.fon.ps.biblioteka.so.book.GetBooksByQuerySO;
import rs.ac.bg.fon.ps.biblioteka.so.bookCategory.GetBookCategoryIdSO;

/**
 *
 * @author Dragana Stefanovic
 */
public class RepositoryBook implements DbRepository<Book, Long> {

    RepositoryAuthor repositoryAuthor = new RepositoryAuthor();
    RepositoryBookCategory repositoryBookCategory = new RepositoryBookCategory();
    Statement statement;
    PreparedStatement ps;
    private DatabaseBroker databaseBroker;

    public RepositoryBook() {
        repositoryAuthor = new RepositoryAuthor();
        repositoryBookCategory = new RepositoryBookCategory();
        databaseBroker = new DatabaseBroker();

    }

    @Override
    public List<Book> getAll() throws Exception {
        return getByQuery("");

    }

    @Override
    public void add(Book t) throws Exception {

        boolean status = updateIfExists(t);
        if (status == false) {
            databaseBroker.add(t);

        }

    }

    @Override
    public void delete(Book t) throws Exception {
        databaseBroker.delete(t);

    }

    @Override
    public void edit(Book oldBook, Book newBook) throws Exception {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Book> getByQuery(String query) throws Exception {
        List<Book> books = new ArrayList<>();
        String dbQuery = "SELECT k.id as sifraKnjige, k.naziv as nazivKnjige, k.godinaIzdanja as godinaIzdanja, k.kolicina as kolicina, p.id as pisacId, p.imePrezime as imePisca, kk.naziv as nazivKategorije FROM knjiga k INNER JOIN autor p ON (k.pisacId=p.id) INNER JOIN kategorijaKnjiga kk ON (kk.id=k.kategorijaId)" + query;
        statement = DbConnectionFactory.getInstance().getConnection().createStatement();
        System.out.println(query);
        ResultSet rs = statement.executeQuery(dbQuery);
        while (rs.next()) {
            Book k = new Book();
            k.setBookid(rs.getLong("sifraKnjige"));
            k.setBookName(rs.getString("nazivKnjige"));
            k.setIssueDate(rs.getInt("godinaIzdanja"));
            k.setNumberInStock(rs.getInt("kolicina"));
            Author author = new Author();
            author.setAuthorId(rs.getLong("pisacId"));
            author.setAuthorName(rs.getString("imePisca"));
            BookCategory category = BookCategory.valueOf(rs.getString("nazivKategorije"));
            k.setAuthor(author);
            k.setBookCategory(category);
            books.add(k);

        }
        statement.close();
        return books;

    }

    public boolean checkIfBooksExist(Author author) throws SQLException {
        boolean exist = false;
        String query = "SELECT * from knjiga WHERE pisacId=" + author.getAuthorId();
        try {
            statement = DbConnectionFactory.getInstance().getConnection().createStatement();
        } catch (IOException ex) {
            Logger.getLogger(RepositoryBook.class.getName()).log(Level.SEVERE, null, ex);
        }
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {
            exist = true;
        }
        rs.close();
        statement.close();
        return exist;

    }

    private boolean updateIfExists(Book t) throws Exception {
        Long categoryId = (Long) new GetBookCategoryIdSO().execute(t.getBookCategory().toString());
        String query = "SELECT * FROM knjiga WHERE naziv='" + t.getBookName() + "' AND godinaIzdanja=" + t.getIssueDate() + " AND kategorijaId=" + categoryId + " AND pisacId=" + t.getAuthor().getAuthorId();
        List<Book> dbBooks = getByQuery(query);
        if (dbBooks.size() > 0) {
            Book dbBook = dbBooks.get(0);
            int newAmount = dbBook.getNumberInStock() + t.getNumberInStock();
            query = "UPDATE knjiga SET kolicina= " + newAmount + " WHERE id=" + dbBook.getBookid();
            statement = DbConnectionFactory.getInstance().getConnection().createStatement();
            statement.execute(query);
            statement.close();
            return true;
        }
        return false;
    }

    public void updateBookCount(Book b, int i) throws SQLException, IOException {
        int num = b.getNumberInStock() + i;
        String updateBookCount = "UPDATE knjiga SET kolicina= " + num + " WHERE id=" + b.getBookid();
        statement = DbConnectionFactory.getInstance().getConnection().createStatement();
        statement.executeUpdate(updateBookCount);
    }
     public int checkBookCount(Book b) throws Exception {
        
        try {
            String query = " WHERE k.id=" + b.getBookid();
            Book book = getByQuery(query).get(0);
            return book.getNumberInStock();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Greska prilikom provere stanja na skladistu.", e);

        }

    }
}
