/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.biblioteka.model;

import java.io.Serializable;

/**
 *
 * @author Dragana Stefanovic
 */
public class Author extends AbstractDO implements Serializable{
    private Long authorId;
    private String authorName;

    public Author(Long autorID, String imePrezime) {
        this.authorId = autorID;
        this.authorName = imePrezime;
    }

    public Author() {
         }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    @Override
    public String toString() {
        return authorName;   }

    @Override
    public String getAttributeList() {
        return "imePrezime";
            }

    @Override
    public String getClassName() {
        return "autor";
         }

    @Override
    public String getAttributeValues() {
        return "'"+getAuthorName()+"'";
        
         }

    @Override
    public String getQueryCondition() {
        return "id= "+getAuthorId();
         }
    
    
}
