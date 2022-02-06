/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.biblioteka.so.rent;

import java.util.List;
import rs.ac.bg.fon.ps.biblioteka.model.Rent;
import rs.ac.bg.fon.ps.biblioteka.model.User;
import rs.ac.bg.fon.ps.biblioteka.repository.impl.RepositoryRent;
import rs.ac.bg.fon.ps.biblioteka.so.AbstractSO;

/**
 *
 * @author Dragana Stefanovic
 */
public class GetAllUserRentsSO extends AbstractSO {

    RepositoryRent repositoryRent;

    public GetAllUserRentsSO() {
        repositoryRent = new RepositoryRent();
    }

    @Override
    protected void precondition(Object param) throws Exception {
        if (param == null || !(param instanceof User)) {
            throw new Exception("Poslat je parametar neodgovarajuceg tipa!");
        }
    }

    @Override
    protected Object executeOperation(Object param) throws Exception {
        List<Rent> rents = repositoryRent.getAllUserRents((User) param);
        return rents;
    }

}
