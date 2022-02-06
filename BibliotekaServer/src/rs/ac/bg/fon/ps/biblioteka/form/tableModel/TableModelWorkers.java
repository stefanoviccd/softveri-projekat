/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.biblioteka.form.tableModel;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.table.AbstractTableModel;
import rs.ac.bg.fon.ps.biblioteka.controller.Controller;
import rs.ac.bg.fon.ps.biblioteka.model.Librarian;

/**
 *
 * @author Dragana Stefanovic
 */
public class TableModelWorkers extends AbstractTableModel {

    private List<Librarian> workers;
    private String[] columnNames = {"Ime", "Prezime", "Korisničko ime", "Šifra", "Prijavljen"};
    private Class[] colClasses = {String.class, String.class, String.class, String.class, Boolean.class};

    public TableModelWorkers() throws Exception {
        workers = Controller.getInstance().getLibrarians();

    }

    @Override
    public int getRowCount() {
        return workers == null ? 0 : workers.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Librarian worker = workers.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return worker.getFirstName();
            case 1:
                return worker.getLastName();
            case 2:
                return worker.getUsername();
            case 3:
                return worker.getPassword();
            case 4:
                return worker.isLoggedIn();
            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public void removeWorker(int selectedRow) throws Exception {
        Librarian worker = workers.get(selectedRow);
        try {
            if(worker.isLoggedIn()==true)
                throw new Exception("Radnik je prijavljen na sistem, ne moze se obrisati.");
            Controller.getInstance().removeLibrarian(worker);
            workers.remove(selectedRow);
            fireTableDataChanged();
        } catch (Exception ex) {
            throw ex;
        }

    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return colClasses[columnIndex];
    }

    public void updateWorker(Librarian dbUser, boolean loogedIn) {
        for (int i = 0; i < workers.size(); i++) {
            if (workers.get(i).getUsername().equals(dbUser.getUsername())) {
                workers.get(i).setLoggedIn(loogedIn);
                fireTableDataChanged();
                break;
            }
        }

    }
    public void refreshView() throws Exception{
        workers = Controller.getInstance().getLibrarians();
        fireTableDataChanged();
        
    }

}
