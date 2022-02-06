/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.biblioteka.so.bookCategory;

import rs.ac.bg.fon.ps.biblioteka.model.BookCategory;
import rs.ac.bg.fon.ps.biblioteka.so.AbstractSO;

/**
 *
 * @author Dragana Stefanovic
 */
public class GetBookCategoriesSO extends AbstractSO{

    @Override
    protected void precondition(Object param) throws Exception {
        //no precondition to check
            }

    @Override
    protected Object executeOperation(Object param) throws Exception {
          return BookCategory.values(); }
    
}
