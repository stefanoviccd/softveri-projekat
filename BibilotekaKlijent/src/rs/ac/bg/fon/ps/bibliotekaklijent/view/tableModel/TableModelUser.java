/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.bibliotekaklijent.view.tableModel;

import java.time.LocalDate;
import rs.ac.bg.fon.ps.biblioteka.model.User;
import rs.ac.bg.fon.ps.biblioteka.model.UserCard;
import rs.ac.bg.fon.ps.biblioteka.model.UserCategory;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import rs.ac.bg.fon.ps.biblioteka.bibilotekaklijent.communication.Communication;
import rs.ac.bg.fon.ps.biblioteka.bibilotekaklijent.controller.ControllerUI;
import rs.ac.bg.fon.ps.biblioteka.communication.Operations;
import rs.ac.bg.fon.ps.biblioteka.communication.Request;
import rs.ac.bg.fon.ps.biblioteka.communication.Response;
import rs.ac.bg.fon.ps.biblioteka.communication.ResponseType;


/**
 *
 * @author Dragana Stefanovic
 */
public class TableModelUser extends AbstractTableModel{
    private List<User> users;
    private String[] columnNames=new String[]{"Ime", "Prezime", "Broj clanske karte"};
    private Class[] columnClasses=new Class[]{String.class, String.class, String.class};
    public TableModelUser() {
        try {
            Request request=new Request();
            request.setOperation(Operations.GET_USERS);
            Response response=Communication.getInstance().sendRequest(request);
            users=(List<User>)response.getResult();
                   
           
        } catch (Exception ex) {
           ex.printStackTrace();
            }
    }

    public void setUsers(List<User> users) {
        this.users = users;
        fireTableDataChanged();
    }
    

    @Override
    public int getRowCount() {
        if(users==null) return 0;
        return users.size();
         }

    @Override
    public int getColumnCount() {
        return columnNames.length;
       }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
       return false;}

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        User clan=users.get(rowIndex);
        switch(columnIndex){
            case 0: return clan.getName();
            case 1: return clan.getLastName();
            case 2: return clan.getUsercard().getCardNumber();

            
        }
        return "n/a";
                
               
        }

    @Override
    public String getColumnName(int column) {
       return columnNames[column];}

    @Override
    public Class<?> getColumnClass(int columnIndex) {
      return columnClasses[columnIndex];}

    public void removeClan(int selectedRow) throws Exception {
        User user=users.get(selectedRow);
        ControllerUI.getInstance().deleteUser(user);
        users.remove(user);
         fireTableDataChanged();}
       
       

    public List<User> getUsers() {
        return users; }
    public User getUser(int selecterRow){
    return users.get(selecterRow);}
   
    
    
}
