/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.biblioteka.repository.impl;

import rs.ac.bg.fon.ps.biblioteka.controller.Controller;
import rs.ac.bg.fon.ps.biblioteka.db.DbConnectionFactory;
import rs.ac.bg.fon.ps.biblioteka.db.DbRepository;
import rs.ac.bg.fon.ps.biblioteka.model.User;
import rs.ac.bg.fon.ps.biblioteka.model.UserCard;
import rs.ac.bg.fon.ps.biblioteka.model.UserCategory;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;
import rs.ac.bg.fon.ps.biblioteka.broker.DatabaseBroker;
import rs.ac.bg.fon.ps.biblioteka.model.Book;
import rs.ac.bg.fon.ps.biblioteka.model.Rent;

/**
 *
 * @author Dragana Stefanovic
 */
public class RepositoryUser implements DbRepository<User, Long> {

    private Statement statement;
    private PreparedStatement ps;
    private DatabaseBroker dbBroker;

    public RepositoryUser() {
        dbBroker = new DatabaseBroker();
    }

    @Override
    public List<User> getAll() throws Exception {
        return getByQuery("");
    }

    @Override
    public void add(User t) throws Exception {
        dbBroker.add(t);

    }

    @Override
    public void edit(User oldU, User newU) throws Exception {
        String queryUser = "UPDATE clan SET ime=?, prezime=?, brojTelefona=?, adresa=?, kategorijaid=? WHERE id=?";
        ps = DbConnectionFactory.getInstance().getConnection().prepareStatement(queryUser);
        ps.setString(1, newU.getName());
        ps.setString(2, newU.getLastName());
        ps.setString(3, newU.getPhoneNumber());
        ps.setString(4, newU.getAddress());
        ps.setLong(5, newU.getUserCategory().getUserCategoryId());
        ps.setLong(6, oldU.getUserId());
        ps.executeUpdate();
        ps.close();

    }

    @Override
    public void delete(User t) throws Exception {
        dbBroker.delete(t);

    }

    public List<User> getusersByUserCard(String cardNumber) throws Exception {
        List<User> users = new ArrayList<>();
        String query = " WHERE ck.brojClanskeKarte='" + cardNumber + "'";
        return getByQuery(query);

    }

    @Override
    public List<User> getByQuery(String query) throws Exception {
        List<User> users = new ArrayList<>();
        String upit = "SELECT c.id as id, c.ime as ime, c.prezime as prezime, c.brojTelefona as brojTelefona, c.adresa as adresa, c.kategorijaId as kategorijaid, kat.naziv as nazivKategorije, kat.popustNaClanarinu as popust, ck.id as ckId, ck.brojClanskeKarte as brojClanskeKarte, ck.datumIzdavanja as datumIzdavanja, ck.datumIsteka as datumIsteka FROM clan c INNER JOIN clanskakarta ck ON (c.clanskaKartaId=ck.id) INNER JOIN kategorijaclanova kat ON (c.kategorijaId=kat.id)  " + query;
        statement = DbConnectionFactory.getInstance().getConnection().createStatement();
        ResultSet rs = statement.executeQuery(upit);
        while (rs.next()) {
            User user = new User();
            user.setUserId(rs.getLong("id"));
            user.setName(rs.getString("ime"));
            user.setLastName(rs.getString("prezime"));
            user.setPhoneNumber(rs.getString("brojTelefona"));
            user.setAddress(rs.getString("adresa"));
            UserCategory uc = new UserCategory();
            uc.setUserCategoryId(rs.getLong("kategorijaid"));
            uc.setName(rs.getString("nazivKategorije"));
            uc.setMembershipFeeDiscount(rs.getDouble("popust"));
            user.setUserCategory(uc);
            UserCard card = new UserCard();
            card.setId(rs.getLong("ckId"));
            card.setCardNumber(rs.getString("brojClanskeKarte"));
            card.setExpiryDate(rs.getDate("datumIsteka").toLocalDate());
            card.setIssueDate(rs.getDate("datumIzdavanja").toLocalDate());
            user.setUsercard(card);
            users.add(user);

        }
        statement.close();
        rs.close();
        return users;
    }

    private void updateBookCount(Book b, int value) throws Exception {
        int num = b.getNumberInStock() + value;
        String updateBookCount = "UPDATE knjiga SET kolicina= " + num + " WHERE id=" + b.getBookid();
        statement = DbConnectionFactory.getInstance().getConnection().createStatement();
        statement.executeUpdate(updateBookCount);
    }

    public boolean checkIfExists(User user) throws Exception {
        List<User> users = getAll();
        for (User u : users) {
            if (u.getName().equals(user.getName()) && u.getLastName().equals(user.getLastName()) && u.getAddress().equals(user.getAddress()) && u.getPhoneNumber().equals(user.getPhoneNumber()) && u.getUserCategory().equals(user.getUserCategory()) && u.getUsercard().getCardNumber().equals(user.getUsercard().getCardNumber())) {
                return true;
            }
        }
        return false;
    }

    public boolean checkIfRentsExist(User user) throws Exception {
        List<Rent> rents = Controller.getInstance().getUserRents(user);
        if (rents.size() > 0) {
            return true;
        }
        return false;

    }

    public boolean checkIfExists(User user, boolean includeUserCard) throws Exception {
        List<User> users = getAll();
        for (User u : users) {
            if (u.getName().equals(user.getName()) && u.getLastName().equals(user.getLastName()) && u.getAddress().equals(user.getAddress()) && u.getPhoneNumber().equals(user.getPhoneNumber()) && u.getUserCategory().equals(user.getUserCategory())) {
                return true;
            }
        }
        return false;

    }

}
