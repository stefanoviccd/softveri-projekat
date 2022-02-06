/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.biblioteka.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.sql.Date;

/**
 *
 * @author Dragana Stefanovic
 */
public class Rent extends AbstractDO implements Serializable {
    private Long id;
    private Book book;
    private User user;
    private LocalDate rentalDate;
    private LocalDate returnDate;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(LocalDate rentalDate) {
        this.rentalDate = rentalDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String getAttributeList() {
        return "clanId, knjigaId, datumIznajmljivanja";
           }

    @Override
    public String getClassName() {
        return "iznajmljivanje";
           }

    @Override
    public String getAttributeValues() {
        return ""+user.getUserId()+", "+book.getBookid()+", "+"'"+Date.valueOf(rentalDate)+"'";
        }

    @Override
    public String getQueryCondition() {
        return null;
            }
    
    
    
}
