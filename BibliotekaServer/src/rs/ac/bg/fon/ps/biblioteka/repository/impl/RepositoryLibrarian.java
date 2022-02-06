/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.biblioteka.repository.impl;

import java.io.IOException;
import rs.ac.bg.fon.ps.biblioteka.db.DbConnectionFactory;
import rs.ac.bg.fon.ps.biblioteka.db.DbRepository;
import rs.ac.bg.fon.ps.biblioteka.model.Librarian;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import rs.ac.bg.fon.ps.biblioteka.broker.DatabaseBroker;

/**
 *
 * @author Dragana Stefanovic
 */
public class RepositoryLibrarian implements DbRepository<Librarian, Long> {

    private DatabaseBroker databaseBroker;

    public RepositoryLibrarian() {
        databaseBroker = new DatabaseBroker();
    }

    public List<Librarian> getAll() throws Exception {
        List<Librarian> librarians = new ArrayList<>();
        String query = "SELECT * FROM bibliotekar";
        Statement statement = DbConnectionFactory.getInstance().getConnection().createStatement();
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            Librarian b = new Librarian(rs.getLong(1), rs.getString(2), rs.getString(3)
            );
            b.setFirstName(rs.getString("ime"));
            b.setLastName(rs.getString("prezime"));
            int prijavljen=rs.getInt("prijavljen");
            if(prijavljen==0) b.setLoggedIn(false);
            else b.setLoggedIn(true);
            librarians.add(b);
        }
        return librarians;

    }

    @Override
    public void add(Librarian t) throws Exception {
        databaseBroker.add(t);
    }

    @Override
    public void edit(Librarian oldOne, Librarian newOne) throws Exception {
        //TODO: Implement later
    }

    @Override
    public void delete(Librarian t) throws Exception {
        databaseBroker.delete(t);
    }

    @Override
    public List<Librarian> getByQuery(String query) throws Exception {
        List<Librarian> librarians = new ArrayList<>();

        Statement statement = DbConnectionFactory.getInstance().getConnection().createStatement();
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            Librarian b = new Librarian(rs.getLong(1), rs.getString(2), rs.getString(3)
            );
            librarians.add(b);
        }
        return librarians;
    }

    public boolean checkIfExists(Librarian librarian) throws Exception {
        List<Librarian> librarians = getAll();
        for (Librarian u : librarians) {
            if (u.getUsername().equals(librarian.getUsername())) {
                return true;
            }
        }
        return false;
          }

    public void setUserIsLoggedIn(Librarian currentUser) throws SQLException, IOException {
        String query = "UPDATE bibliotekar SET prijavljen=1 WHERE bibliotekarID="+currentUser.getId();
        Statement statement = DbConnectionFactory.getInstance().getConnection().createStatement();
        statement.executeUpdate(query);
        statement.close();
          }
        public void setUserIsLoggedOut(Librarian currentUser) throws SQLException, IOException {
        String query = "UPDATE bibliotekar SET prijavljen=0 WHERE bibliotekarID="+currentUser.getId();
        Statement statement = DbConnectionFactory.getInstance().getConnection().createStatement();
        statement.executeUpdate(query);
        this.commit();
            System.out.println("Status: izlogovan");
        statement.close();
          }

}
