/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.biblioteka.repository.impl;

import rs.ac.bg.fon.ps.biblioteka.db.DbConnectionFactory;
import rs.ac.bg.fon.ps.biblioteka.db.DbRepository;
import rs.ac.bg.fon.ps.biblioteka.model.BookCategory;
import java.util.List;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Dragana Stefanovic
 */
public class RepositoryBookCategory implements DbRepository<BookCategory, Long> {

    private Statement statement;

    public Long getBookCategoryId(String name) throws Exception {
        String upit = "SELECT id FROM kategorijaknjiga WHERE naziv='" + name + "'";
        statement = DbConnectionFactory.getInstance().getConnection().createStatement();
        ResultSet rs = statement.executeQuery(upit);
        Long id = null;
        if (rs.next()) {
            id = rs.getLong("id");
        }
        statement.close();
        rs.close();
        return id;

    }

    String getCategoryName(Long categoryId) throws Exception {
        String query = "SELECT naziv FROM kategorijaknjiga WHERE id=" + categoryId;
        statement = DbConnectionFactory.getInstance().getConnection().createStatement();
        ResultSet rs = statement.executeQuery(query);
        String name = null;
        if (rs.next()) {
            name = rs.getString("naziv");
        }
        statement.close();
        rs.close();
        return name;
    }

    @Override
    public List<BookCategory> getAll() throws Exception {
        List<BookCategory>  returnValue=new ArrayList<>();
      BookCategory[] categories=BookCategory.values();
        for (BookCategory category : categories) {
            returnValue.add(category);
            
        }
        return returnValue;
          }

    @Override
    public void add(BookCategory t) throws Exception {
         //TODO: Implement later
        }

    @Override
    public void edit(BookCategory odlOne, BookCategory newOne) throws Exception {
         //TODO: Implement later
            }

    @Override
    public void delete(BookCategory t) throws Exception {
         //TODO: Implement later
          }

    @Override
    public List<BookCategory> getByQuery(String query) throws Exception {
         //TODO: Implement later
         return null;
          }

}
