/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.biblioteka.broker;

import java.io.IOException;
import rs.ac.bg.fon.ps.biblioteka.model.AbstractDO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import rs.ac.bg.fon.ps.biblioteka.db.DbConnectionFactory;

/**
 *
 * @author Dragana Stefanovic
 */
public class DatabaseBroker {
    public void add(AbstractDO abstractDO) throws SQLException, IOException{
        String query="INSERT INTO "+abstractDO.getClassName()+" ("+abstractDO.getAttributeList()+") VALUES ("+abstractDO.getAttributeValues()+")";
        Statement s= DbConnectionFactory.getInstance().getConnection().createStatement();
        s.executeUpdate(query);
        s.close();
       
    }
    public void delete(AbstractDO abstractDO) throws SQLException, IOException{
         String query="DELETE FROM "+abstractDO.getClassName()+" WHERE "+abstractDO.getQueryCondition();
        Statement s= DbConnectionFactory.getInstance().getConnection().createStatement();
        s.executeUpdate(query);
        s.close();
       
    }
    public void getAll(AbstractDO abstractDO) throws SQLException, IOException{
         String query="SELECT * FROM "+abstractDO.getClassName();
        Statement s= DbConnectionFactory.getInstance().getConnection().createStatement();
        s.executeUpdate(query);
        s.close();
        
    }
    
    
    
    
}
