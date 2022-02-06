/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.biblioteka.db;

import java.io.IOException;
import rs.ac.bg.fon.ps.biblioteka.repository.Repository;
import java.sql.SQLException;


/**
 *
 * @author Cartman
 */
public interface DbRepository<T, K> extends Repository<T, K> {
    default public void connect() throws SQLException, IOException{
        DbConnectionFactory.getInstance().getConnection();
    }
    
    default public void disconnect() throws SQLException, IOException{
        DbConnectionFactory.getInstance().getConnection().close();
    }
    
    default public void commit() throws SQLException, IOException{
        DbConnectionFactory.getInstance().getConnection().commit();
    }
    
    default public void rollback() throws SQLException, IOException{
        DbConnectionFactory.getInstance().getConnection().rollback();
    }
}
