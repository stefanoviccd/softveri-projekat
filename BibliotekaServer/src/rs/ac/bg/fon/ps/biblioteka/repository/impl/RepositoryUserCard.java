/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.biblioteka.repository.impl;

import java.io.IOException;
import rs.ac.bg.fon.ps.biblioteka.db.DbConnectionFactory;
import rs.ac.bg.fon.ps.biblioteka.db.DbRepository;
import rs.ac.bg.fon.ps.biblioteka.exception.NoUserCartFoundException;
import rs.ac.bg.fon.ps.biblioteka.model.UserCard;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.ac.bg.fon.ps.biblioteka.broker.DatabaseBroker;

/**
 *
 * @author Dragana Stefanovic
 */
public class RepositoryUserCard implements DbRepository<UserCard, Long> {

    private Statement statement;
    private PreparedStatement pstatement;
    private DatabaseBroker dbBroker;

    public RepositoryUserCard() {
        dbBroker = new DatabaseBroker();
    }

    @Override
    public List<UserCard> getAll() throws Exception {
        //TODO: Implement later
        return null;
    }

    @Override
    public void add(UserCard t) throws Exception {

        dbBroker.add(t);
    }

    @Override
    public void delete(UserCard t) throws Exception {

        dbBroker.delete(t);
    }

    @Override
    public void edit(UserCard oldOne, UserCard newOne) throws Exception {
        //Implement later
    }

    @Override
    public List<UserCard> getByQuery(String query) throws Exception {
        List<UserCard> cards = new ArrayList<>();
        UserCard card = new UserCard();

        statement = DbConnectionFactory.getInstance().getConnection().createStatement();
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            card.setId(rs.getLong(1));
            card.setCardNumber(rs.getString(2));
            card.setIssueDate(rs.getDate(3).toLocalDate());
            card.setExpiryDate(rs.getDate(4).toLocalDate());
            cards.add(card);

        }
        statement.close();
        if (cards.size() > 0) {
            return cards;
        }
        throw new NoUserCartFoundException("Clanska karta ne postoji!");
    }

    public void updateCardNumber(UserCard oldCard, UserCard newCard) throws SQLException {
        try {

            String query = "UPDATE clanskakarta SET brojClanskeKarte= " + newCard.getCardNumber() + " WHERE id=" + oldCard.getId();
            Statement s = DbConnectionFactory.getInstance().getConnection().createStatement();
            s.executeUpdate(query);
            query = "UPDATE clanskakarta SET datumIzdavanja='" + Date.valueOf(newCard.getIssueDate()) + "' WHERE id=" + oldCard.getId();
            s.executeUpdate(query);
            query = "UPDATE clanskakarta SET datumIsteka='" + Date.valueOf(newCard.getExpiryDate()) + "' WHERE id=" + oldCard.getId();
            s.executeUpdate(query);
            this.commit();
            s.close();

        } catch (IOException ex) {
            Logger.getLogger(RepositoryUser.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
