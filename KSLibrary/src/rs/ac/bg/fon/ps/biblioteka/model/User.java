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
public class User extends AbstractDO implements Serializable {
    private Long userId;
    private String name;
    private String lastName;
    private String phoneNumber;
    private String address;
    private UserCategory userCategory;
    private UserCard usercard;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public UserCategory getUserCategory() {
        return userCategory;
    }

    public void setUserCategory(UserCategory userCategory) {
        this.userCategory = userCategory;
    }

    public UserCard getUsercard() {
        return usercard;
    }

    public void setUsercard(UserCard usercard) {
        this.usercard = usercard;
    }

    @Override
    public String getAttributeList() {
        return "ime, prezime, brojTelefona, adresa, kategorijaId, clanskaKartaId";
          }

    @Override
    public String getClassName() {
        return "clan";
            }

    @Override
    public String getAttributeValues() {
         return "'"+name+"', '"+lastName+"', '"+phoneNumber+"', '"+address+"', "+userCategory.getUserCategoryId()+", "+usercard.getId();
        
          }

    @Override
    public String getQueryCondition() {
        return "id="+userId;
        }
    
    
    
}
