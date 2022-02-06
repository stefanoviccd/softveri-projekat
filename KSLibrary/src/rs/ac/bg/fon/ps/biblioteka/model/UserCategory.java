/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package rs.ac.bg.fon.ps.biblioteka.model;

import java.io.Serializable;

/**
 *
 * @author Dragana Stefanovic
 */
public class UserCategory implements Serializable {
    private Long UserCategoryId;
    private String name;
    private Double membershipFeeDiscount;

    public Long getUserCategoryId() {
        return UserCategoryId;
    }

    public void setUserCategoryId(Long UserCategoryId) {
        this.UserCategoryId = UserCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMembershipFeeDiscount() {
        return membershipFeeDiscount;
    }

    public void setMembershipFeeDiscount(Double membershipFeeDiscount) {
        this.membershipFeeDiscount = membershipFeeDiscount;
    }

    @Override
    public String toString() {
       return name;
    }
    
    
    
}
