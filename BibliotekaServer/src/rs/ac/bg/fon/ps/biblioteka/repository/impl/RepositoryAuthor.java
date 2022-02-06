/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.biblioteka.repository.impl;

import rs.ac.bg.fon.ps.biblioteka.db.DbConnectionFactory;
import rs.ac.bg.fon.ps.biblioteka.db.DbRepository;
import rs.ac.bg.fon.ps.biblioteka.model.Author;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import rs.ac.bg.fon.ps.biblioteka.broker.DatabaseBroker;
import rs.ac.bg.fon.ps.biblioteka.model.AbstractDO;

/**
 *
 * @author Dragana Stefanovic
 */
public class RepositoryAuthor implements DbRepository<Author, Long> {

    private Statement statement;
    private DatabaseBroker dbBroker;

    public RepositoryAuthor() {
        dbBroker=new DatabaseBroker();
    }
    

    @Override
    public List<Author> getAll() throws Exception {
        List<Author> authors = new ArrayList<>();
        String query = "SELECT * FROM AUTOR ORDER BY imePrezime ASC";
        statement = DbConnectionFactory.getInstance().getConnection().createStatement();
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            Author a = new Author(rs.getLong(1), rs.getString(2));
            authors.add(a);

        }
        statement.close();
        rs.close();

        return authors;

    }

    @Override
    public void add(Author t) throws Exception {
        dbBroker.add(t);
        
    }

    @Override
    public void edit(Author stari, Author novi) throws Exception {
        //TODO: Implement later
    }

    @Override
    public void delete(Author author) throws SQLException, Exception{
            dbBroker.delete(author);
   
        

    }

    @Override
    public List<Author> getByQuery(String query) throws Exception {
        List<Author> dbAuthors = new ArrayList<>();
        Author dbAuthor;
        statement = DbConnectionFactory.getInstance().getConnection().createStatement();
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            dbAuthor = new Author();
            dbAuthor.setAuthorId(rs.getLong("id"));
            dbAuthor.setAuthorName(rs.getString("imePrezime"));
            dbAuthors.add(dbAuthor);
        }
        statement.close();
        rs.close();

        return dbAuthors;
    }





 

}
