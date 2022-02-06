/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.biblioteka.so.user;

import rs.ac.bg.fon.ps.biblioteka.db.DbConnectionFactory;
import rs.ac.bg.fon.ps.biblioteka.model.User;
import rs.ac.bg.fon.ps.biblioteka.repository.impl.RepositoryUser;
import rs.ac.bg.fon.ps.biblioteka.repository.impl.RepositoryUserCard;
import rs.ac.bg.fon.ps.biblioteka.so.AbstractSO;

/**
 *
 * @author Dragana Stefanovic
 */
public class DeleteUserSO extends AbstractSO {

    RepositoryUser repositoryUser;
    RepositoryUserCard repositoryUserCard;

    public DeleteUserSO() {
        repositoryUser = new RepositoryUser();
        repositoryUserCard = new RepositoryUserCard();
    }

    @Override
    protected void precondition(Object param) throws Exception {
        if (param == null || !(param instanceof User)) {
            throw new Exception("Poslati objekat je neodgovarajuceg tipa!");
        } else {
            User user = (User) param;
            checkValueConstraints(user);
            checkStructuralConstraints(user);
        }
    }

    @Override
    protected Object executeOperation(Object param) throws Exception {
        User user = (User) param;
        try {
            repositoryUser.delete(user);
            repositoryUserCard.delete(user.getUsercard());
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Greska prilikom brisanja korisnika." + e.getMessage(), e);
        }
        
    }

    private void checkStructuralConstraints(User user) throws Exception {
        checkRentsExist(user);
    }

    private void checkValueConstraints(User user) throws Exception {
        if (user.getUserId().equals(null)) {
            throw new Exception("Id korisnika nije poslat!");
        }
    }

    private void checkRentsExist(User user) throws Exception {
        if (repositoryUser.checkIfRentsExist(user)) {
            throw new Exception("Korisnik je zaduzio knjigu. Nije moguce dovrsiti operaciju brisanja.");
        }
    }
}
