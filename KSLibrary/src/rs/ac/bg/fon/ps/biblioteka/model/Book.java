/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.biblioteka.model;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Dragana Stefanovic
 */
public class Book extends AbstractDO implements Serializable {
    private Long bookid;
    private String bookName;
    private Integer issueDate;
    private BookCategory bookCategory;
    private Author author;
    private Integer numberInStock;

    public Long getBookid() {
        return bookid;
    }

    public void setBookid(Long bookid) {
        this.bookid = bookid;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Integer getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Integer issueDate) {
        this.issueDate = issueDate;
    }

    public BookCategory getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(BookCategory bookCategory) {
        this.bookCategory = bookCategory;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Integer getNumberInStock() {
        return numberInStock;
    }

    public void setNumberInStock(Integer numberInStock) {
        this.numberInStock = numberInStock;
    }

    @Override
    public String getAttributeList() {
        return "naziv, godinaIzdanja, kategorijaId, pisacId, kolicina";
       }

    @Override
    public String getClassName() {
        return "knjiga";
        }

    @Override
    public String getAttributeValues() {
        int catId=getBookCategory().ordinal()+1;
        return "'"+getBookName()+"', "+getIssueDate()+", "+catId+", "+getAuthor().getAuthorId()+", "+getNumberInStock();
        
          }

    @Override
    public String getQueryCondition() {
        return "id="+getBookid();
            }
    
    
    
}
