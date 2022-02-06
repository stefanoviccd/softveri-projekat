/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.biblioteka.so.user;

import java.util.ArrayList;
import java.util.List;
import rs.ac.bg.fon.ps.biblioteka.db.DbConnectionFactory;
import rs.ac.bg.fon.ps.biblioteka.model.User;
import rs.ac.bg.fon.ps.biblioteka.model.UserCategory;
import rs.ac.bg.fon.ps.biblioteka.repository.impl.RepositoryUser;
import rs.ac.bg.fon.ps.biblioteka.repository.impl.RepositoryUserCard;
import rs.ac.bg.fon.ps.biblioteka.repository.impl.RepositoryUserCategory;
import rs.ac.bg.fon.ps.biblioteka.so.AbstractSO;

/**
 *
 * @author Dragana Stefanovic
 */
public class UpdateUserSO extends AbstractSO {

    RepositoryUser repositoryUser;
    RepositoryUserCategory repositoryUserCategory;
    private RepositoryUserCard repositoryUserCard;

    public UpdateUserSO() {
        repositoryUser = new RepositoryUser();
        repositoryUserCategory = new RepositoryUserCategory();
        repositoryUserCard = new RepositoryUserCard();
    }

    @Override
    protected void precondition(Object param) throws Exception {
        if (param == null) {
            throw new Exception("Nije poslat parametar!");
        }
        List<User> users = (List<User>) param;

        if (users.size() < 2 || users.get(0).equals(null) || users.get(1).equals(null)) {
            throw new Exception("Nisu poslati potrebni parametri!");
        }
        if (!(users.get(0) instanceof User) || !(users.get(1) instanceof User)) {
            throw new Exception("Poslati objekat je neodgovarajuceg tipa!");
        } else {
            User newUser = ((List<User>) param).get(1);
            checkStructuralConstraints(newUser);
        }

    }

    @Override
    protected Object executeOperation(Object param) throws Exception {
        List<User> usersForUpdate = (List<User>) param;
        User oldUser = usersForUpdate.get(0);
        User newUser = usersForUpdate.get(1);
        String query = "SELECT * FROM kategorijaclanova WHERE naziv='" + newUser.getUserCategory().getName() + "'";
        UserCategory uc = repositoryUserCategory.getByQuery(query).get(0);
        newUser.setUserCategory(uc);
        try {
            repositoryUser.edit(oldUser, newUser);
            if (!oldUser.getUsercard().getCardNumber().equals(newUser.getUsercard().getCardNumber())) {
                repositoryUserCard.updateCardNumber(oldUser.getUsercard(), newUser.getUsercard());
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Greska prilikom izmene podataka o korisniku.", e);
        }

    }

    private void checkStructuralConstraints(User user) throws Exception {
        boolean exists = checkIfExists(user);
        if (exists) {
            throw new Exception("Korisnik postoji.");
        }

    }

    private boolean checkIfExists(User user) throws Exception {
        try {
            return repositoryUser.checkIfExists(user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Greska prilikom provere postojanja korisnika u bazi.", e);
        }
    }

}
