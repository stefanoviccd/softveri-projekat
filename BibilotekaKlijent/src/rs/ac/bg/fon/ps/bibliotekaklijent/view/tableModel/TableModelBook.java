/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.bibliotekaklijent.view.tableModel;

import rs.ac.bg.fon.ps.biblioteka.model.Author;
import rs.ac.bg.fon.ps.biblioteka.model.BookCategory;
import rs.ac.bg.fon.ps.biblioteka.model.Book;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Dragana Stefanovic
 */
public class TableModelBook extends AbstractTableModel {
    private List<Book> knjige;
    private String[] columnNames=new String[]{"Naziv", "Autor", "Godina izdanja", "Kategorija", "Na stanju"};
    private Class[] columnClasses=new Class[]{Long.class, String.class, Author.class, Integer.class, BookCategory.class, Integer.class};
    public TableModelBook(List<Book> k){
        knjige=k;
    }
    public void setKnjige(List<Book> knjige){
    this.knjige=knjige;
    fireTableDataChanged();}

    @Override
    public int getRowCount() {
        if(knjige==null) return 0;
        return knjige.size();
           }

    @Override
    public int getColumnCount() {
       return columnNames.length;}

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Book k=knjige.get(rowIndex);
        switch(columnIndex){
            case 0: return k.getBookName();
            case 1: return k.getAuthor();
            case 2: return k.getIssueDate();
            case 3: return k.getBookCategory();
            case 4: return k.getNumberInStock();
        }
        return "n/a";
         
          }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public Book getKnjiga(int selectedrow) {
       return knjige.get(selectedrow);  }
    public void deleteKnjiga(Book knjiga){
    knjige.remove(knjiga);
    fireTableDataChanged();
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
    
    
    
}
