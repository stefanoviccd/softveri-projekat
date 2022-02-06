/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.biblioteka.so.validator;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import rs.ac.bg.fon.ps.biblioteka.model.Book;
import rs.ac.bg.fon.ps.biblioteka.so.AbstractSO;
import rs.ac.bg.fon.ps.biblioteka.so.book.AddBookSO;

/**
 *
 * @author Dragana Stefanovic
 */
public class BookValidator {

    private final List<String> validationErros;

    private BookValidator() {
        validationErros = new ArrayList();
    }

    public static BookValidator startValidation() {
        return new BookValidator();
    }

    public BookValidator validateValueIsPositive(int value, String errorMessage) throws ValidationException {
        if (value <= 0) {
            this.validationErros.add(errorMessage);

        
        }
        return this;
    }
    
    public BookValidator validateNotNull(Object value, String errorMessage) throws ValidationException {
        if (value == null) {
            this.validationErros.add(errorMessage);
        }
        return this;
    }
    public BookValidator validateAlreadyExists(Object value, AddBookSO so, String errorMessage) throws ValidationException {
        Book book=(Book) value;
        so=(AddBookSO) so;
        try {
            if (so.exists(book)) {
                this.validationErros.add("Knjiga postoji u bazi!");
            }
        } catch (Exception ex) {
            this.validationErros.add(ex.getMessage());
        }
        return this;
    }
    


   

    public void throwIfInvalide() throws ValidationException {
        if (!validationErros.isEmpty()) {
            throw new ValidationException(this.validationErros.stream().collect(Collectors.joining("\n")));
        }
    }

 

}
