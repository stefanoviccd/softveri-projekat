/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.biblioteka.so.user;

import rs.ac.bg.fon.ps.biblioteka.model.User;
import rs.ac.bg.fon.ps.biblioteka.model.UserCard;
import rs.ac.bg.fon.ps.biblioteka.model.UserCategory;
import rs.ac.bg.fon.ps.biblioteka.repository.impl.RepositoryUser;
import rs.ac.bg.fon.ps.biblioteka.repository.impl.RepositoryUserCard;
import rs.ac.bg.fon.ps.biblioteka.repository.impl.RepositoryUserCategory;
import rs.ac.bg.fon.ps.biblioteka.so.AbstractSO;

/**
 *
 * @author Dragana Stefanovic
 */
public class AddUserSO extends AbstractSO {

    RepositoryUser repositoryUser;
    RepositoryUserCard repositoryUserCard;
    RepositoryUserCategory repositoryUserCategory;

    public AddUserSO() {
        repositoryUser = new RepositoryUser();
        repositoryUserCard=new RepositoryUserCard();
        repositoryUserCategory=new RepositoryUserCategory();
    }

    @Override
    protected void precondition(Object param) throws Exception {
        if (param == null || !(param instanceof User)) {
            throw new Exception("Poslati objekat je neodgovarajuceg tipa!");
        } else {
            User user = (User) param;
            //   checkValueConstraints(user);
            checkStructuralConstraints(user);
        }
    }

    @Override
    protected Object executeOperation(Object param) throws Exception {
        User u=(User) param;
        repositoryUserCard.add(u.getUsercard());
        String query="SELECT * FROM clanskakarta WHERE brojClanskeKarte= '"+u.getUsercard().getCardNumber()+"'";
        UserCard card=repositoryUserCard.getByQuery(query).get(0);
        u.setUsercard(card);
        query = "SELECT * FROM kategorijaclanova WHERE naziv='" + u.getUserCategory().getName() + "'";
        UserCategory category=repositoryUserCategory.getByQuery(query).get(0);
        u.setUserCategory(category);
        repositoryUser.add(u);
        
        return null;
    }

    private void checkStructuralConstraints(User user) throws Exception {
        boolean exists = checkIfExists(user,false);
        if (exists) {
            throw new Exception("Korisnik postoji.");
        }

    }

    private boolean checkIfExists(User user, boolean includeUserCard) throws Exception {
        try {
            return repositoryUser.checkIfExists(user, includeUserCard);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Greska prilikom provere postojanja korisnika u bazi.", e);
        }
    }

}
