/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.bibliotekaklijent.view.tableModel;

import java.time.LocalDate;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import rs.ac.bg.fon.ps.biblioteka.model.Book;
import rs.ac.bg.fon.ps.biblioteka.model.Rent;

/**
 *
 * @author Dragana Stefanovic
 */
public class TableModelUserRents extends AbstractTableModel {
      private List<Rent> rents;
    private String[] columnNames=new String[]{ "Knjiga", "Datum iznajmljivanja", "Datum vracanja"};
    private Class[] columnClasses=new Class[]{String.class, LocalDate.class, LocalDate.class};
    public TableModelUserRents(List<Rent> k){
        rents=k;
    }
    public void setRents(List<Rent> k){
    this.rents=k;
    fireTableDataChanged();}

   
    

    @Override
    public int getRowCount() {
        if(rents==null) return 0;
        return rents.size();
           }

    @Override
    public int getColumnCount() {
       return columnNames.length;}

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
       Rent r=rents.get(rowIndex);
        switch(columnIndex){
            case 0: return r.getBook().getBookName();
            case 1: return r.getRentalDate();
            case 2: return r.getReturnDate()==null ? "Nije vracena" : r.getReturnDate();

        }
        return "n/a";
         
          }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public Rent getRent(int selectedrow) {
       return rents.get(selectedrow);  }
    public void deleteRent(int rent){
    rents.remove(rent);
    
    fireTableDataChanged();
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
    
    
    
}
