/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.biblioteka.repository.impl;

import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.ac.bg.fon.ps.biblioteka.broker.DatabaseBroker;
import rs.ac.bg.fon.ps.biblioteka.db.DbConnectionFactory;
import rs.ac.bg.fon.ps.biblioteka.model.Author;
import rs.ac.bg.fon.ps.biblioteka.model.Book;
import rs.ac.bg.fon.ps.biblioteka.model.BookCategory;
import rs.ac.bg.fon.ps.biblioteka.model.Rent;
import rs.ac.bg.fon.ps.biblioteka.model.User;
import rs.ac.bg.fon.ps.biblioteka.repository.Repository;

/**
 *
 * @author Dragana Stefanovic
 */
public class RepositoryRent implements Repository {

    private Statement statement;
    private PreparedStatement ps;
    DatabaseBroker dbBroker;

    public RepositoryRent() {
        dbBroker = new DatabaseBroker();
    }

    @Override
    public List<Rent> getAll() throws Exception {
        return getByQuery("");
    }

    @Override
    public void add(Object t) throws Exception {
        //TODO: implement later
    }

    @Override
    public void edit(Object stari, Object novi) throws Exception {
        //TODO: implement later
    }

    @Override
    public void delete(Object t) throws Exception {
        //TODO: implement later
    }

    @Override
    public List getByQuery(String query) throws Exception {
        String dbQuery = "SELECT i.id as iznId, i.datumIznajmljivanja as datIznajmljivanja, i.datumVracanja as datVracanja, k.id as sifraKnjige, k.naziv as nazivKnjige, k.godinaIzdanja as godinaIzdanja, k.kolicina as kolicina, p.id as pisacId, p.imePrezime as imePisca, kk.naziv as nazivKategorije FROM knjiga k INNER JOIN iznajmljivanje i ON (i.knjigaId=k.id) INNER JOIN autor p ON (k.pisacId=p.id) INNER JOIN kategorijaKnjiga kk ON (kk.id=k.kategorijaId)" + query;

        statement = DbConnectionFactory.getInstance().getConnection().createStatement();
        ResultSet rs = statement.executeQuery(dbQuery);
        List<Rent> rents = new ArrayList<>();
        while (rs.next()) {
            Book b = new Book();
            Rent r = new Rent();
            b.setBookid(rs.getLong("sifraKnjige"));
            b.setBookName(rs.getString("nazivKnjige"));
            b.setIssueDate(rs.getInt("godinaIzdanja"));
            b.setNumberInStock(rs.getInt("kolicina"));
            Author author = new Author();
            author.setAuthorId(rs.getLong("pisacId"));
            author.setAuthorName(rs.getString("imePisca"));
            BookCategory category = BookCategory.valueOf(rs.getString("nazivKategorije"));
            b.setAuthor(author);
            b.setBookCategory(category);
            r.setId(rs.getLong("iznId"));
            r.setBook(b);
            r.setRentalDate(rs.getDate("datIznajmljivanja").toLocalDate());
            if(rs.getDate("datVracanja")!=null) r.setReturnDate(rs.getDate("datVracanja").toLocalDate());

            rents.add(r);

        }
        return rents;

    }

    public List<Rent> getUserRents(User u) throws Exception {
        String query = " WHERE clanId=" + u.getUserId() + " AND datumVracanja IS NULL";
        return getByQuery(query);

    }

    public void rentBook(User u, Book b) throws Exception {
        Rent r = new Rent();
        r.setBook(b);
        r.setUser(u);
        r.setRentalDate(LocalDate.now());
        dbBroker.add(r);

    }

    public void restoreBook(Rent rental) throws Exception {
        String query = "UPDATE iznajmljivanje SET datumVracanja=? WHERE id=" + rental.getId();
        ps = DbConnectionFactory.getInstance().getConnection().prepareStatement(query);
        ps.setDate(1, Date.valueOf(LocalDate.now()));
        ps.executeUpdate();

    }

    public void deleteBookRents(Book t) throws SQLException {
        String query = "DELETE FROM iznajmljivanje WHERE knjigaId=" + t.getBookid();
        try {
            statement = DbConnectionFactory.getInstance().getConnection().createStatement();
        } catch (IOException ex) {
            Logger.getLogger(RepositoryRent.class.getName()).log(Level.SEVERE, null, ex);
        }
        statement.execute(query);

    }

    public List<Rent> getAllUserRents(User u) throws SQLException, IOException, Exception {
        String query = " WHERE clanId=" + u.getUserId();
        return getByQuery(query);

    }

}
