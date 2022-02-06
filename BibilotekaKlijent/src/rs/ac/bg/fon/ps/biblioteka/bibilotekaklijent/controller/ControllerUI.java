/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.biblioteka.bibilotekaklijent.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.ac.bg.fon.ps.biblioteka.bibilotekaklijent.client.thread.ServerStoppedListener;
import rs.ac.bg.fon.ps.biblioteka.bibilotekaklijent.communication.Communication;
import rs.ac.bg.fon.ps.biblioteka.communication.Operations;
import rs.ac.bg.fon.ps.biblioteka.communication.Request;
import rs.ac.bg.fon.ps.biblioteka.communication.Response;
import rs.ac.bg.fon.ps.biblioteka.communication.ResponseType;
import rs.ac.bg.fon.ps.biblioteka.model.Author;
import rs.ac.bg.fon.ps.biblioteka.model.Book;
import rs.ac.bg.fon.ps.biblioteka.model.BookCategory;
import rs.ac.bg.fon.ps.biblioteka.model.Librarian;
import rs.ac.bg.fon.ps.biblioteka.model.Rent;
import rs.ac.bg.fon.ps.biblioteka.model.User;
import rs.ac.bg.fon.ps.biblioteka.model.UserCard;
import rs.ac.bg.fon.ps.biblioteka.model.UserCategory;

/**
 *
 * @author Dragana Stefanovic
 */
public class ControllerUI {

    private static ControllerUI instance;
    private Librarian currentUser;
    private ServerStoppedListener serverStoppedListener;

    public void setServerStoppedListener(ServerStoppedListener serverStoppedListener) {
        this.serverStoppedListener = serverStoppedListener;
    }

    private ControllerUI() {

    }

    public void finish() {
        if (serverStoppedListener != null) {
            serverStoppedListener.serverStopped();
        }
    }

    public static ControllerUI getInstance() {
        if (instance == null) {
            instance = new ControllerUI();
        }
        return instance;
    }

    public Librarian login(String user, String pass) throws SQLException, Exception {
        Librarian b = new Librarian();
        b.setUsername(user);
        b.setPassword(pass);
        Request req = new Request(Operations.LOGIN, b);
        Response response = Communication.getInstance().login(req);
        if (response == null || !response.getResponseType().equals(ResponseType.SUCCESS)) {
            throw response.getException();
        }
        return (Librarian) response.getResult();

    }

    public Librarian getCurrentUser() {
        return this.currentUser;
    }

    public List<UserCategory> getUserCategories() throws Exception {
        Request request = new Request(Operations.GET_USER_CATEGORIES, null);
        Response response = Communication.getInstance().sendRequest(request);

        if (response.getResponseType().equals(ResponseType.ERROR)) {
            throw response.getException();
        }
        return (List<UserCategory>) response.getResult();
    }

    public void addUser(User user) throws SQLException, Exception {
        Request request = new Request(Operations.ADD_USER, user);
        Response response = Communication.getInstance().sendRequest(request);
        if (response.getResponseType().equals(ResponseType.ERROR)) {
            throw response.getException();
        }

    }

    public List<User> getUsers() throws Exception {
        Request request = new Request(Operations.GET_USERS, null);
        Response response = Communication.getInstance().sendRequest(request);
        if (response.getResponseType().equals(ResponseType.ERROR)) {
            throw new Exception();
        }
        return (List<User>) response.getResult();

    }

    public UserCard getUserCardById(long id) throws Exception {

        return null;
    }

    public void deleteUser(User user) throws Exception {
        Request request = new Request();
        request.setOperation(Operations.DELETE_USER);
        request.setArgument(user);
        Response response = Communication.getInstance().sendRequest(request);
        if (response.getResponseType().equals(ResponseType.ERROR)) {
            throw response.getException();
        }

    }

    public List<User> getUsersByUsersCard(String cardNumber) throws Exception {
        Request request = new Request();
        request.setOperation(Operations.GET_USERS_BY_USER_CARD);

        request.setArgument(cardNumber);
        Response response = Communication.getInstance().sendRequest(request);
        if (response.getResponseType().equals(ResponseType.ERROR)) {
            throw response.getException();
        }
        return (List<User>) response.getResult();

    }

    public void updateUser(User oldUser, User newUser) throws SQLException, Exception {
        List<User> usersForUpdating = new ArrayList<>();
        usersForUpdating.add(oldUser);
        usersForUpdating.add(newUser);

        Request request = new Request(Operations.UPDATE_USER, usersForUpdating);
        Response response = Communication.getInstance().sendRequest(request);
        if (response.getResponseType().equals(ResponseType.ERROR)) {
            throw new Exception();
        }

    }

    public List<Author> getAuthors() throws Exception {
        Request request = new Request(Operations.GET_AUTHORS, null);
        Response response = Communication.getInstance().sendRequest(request);
        if (response.getResponseType().equals(ResponseType.ERROR)) {
            throw response.getException();
        }
        return (List<Author>) response.getResult();
    }

    public void addBook(Book book) throws Exception {
        Request request = new Request(Operations.ADD_BOOK, book);
        Response response = Communication.getInstance().sendRequest(request);
        if (response.getResponseType().equals(ResponseType.SUCCESS)) {

        } else {
            throw response.getException();
        }

    }

    public BookCategory[] getBookCategories() throws Exception {
        Request request = new Request(Operations.GET_BOOK_CATEGORIES, null);
        Response response;
        response = Communication.getInstance().sendRequest(request);
        if (response.getResponseType().equals(ResponseType.ERROR)) {
            throw response.getException();
        }
        return (BookCategory[]) response.getResult();

    }

    public List<Book> getBooks() throws Exception {
        Request request = new Request(Operations.GET_BOOKS, null);
        Response response = Communication.getInstance().sendRequest(request);
        if (response.getResponseType().equals(ResponseType.ERROR)) {
            throw response.getException();
        }
        return (List<Book>) response.getResult();
    }

    public List<Book> getBooksByQuery(String query) throws Exception {
        Request request = new Request(Operations.GET_BOOKS_BY_QUERY, query);
        Response response = Communication.getInstance().sendRequest(request);
        if (response.getResponseType().equals(ResponseType.ERROR)) {
            throw response.getException();
        }
        return (List<Book>) response.getResult();
    }

    public Long getBookCategoryId(BookCategory category) throws SQLException, Exception {
        Request request = new Request(Operations.GET_BOOK_CATEGORIY_ID, category);
        Response response = Communication.getInstance().sendRequest(request);
        if (response.getResponseType().equals(ResponseType.ERROR)) {
            throw response.getException();
        }
        return (Long) response.getResult();
    }

    public void deleteBook(Book book) throws SQLException, Exception {
        Request request = new Request(Operations.DELETE_BOOK, book);
        Response response = Communication.getInstance().sendRequest(request);
        if (response.getResponseType().equals(ResponseType.ERROR)) {
            throw response.getException();
        }

    }

    public void rentBook(User u, Book b) throws Exception {
        List<Object> userbook = new ArrayList<>();
        userbook.add(u);
        userbook.add(b);
        Request request = new Request();
        request.setArgument(userbook);
        request.setOperation(Operations.RENT_BOOK);
        Response response = Communication.getInstance().sendRequest(request);
        if (response.getResponseType().equals(ResponseType.ERROR)) {
            throw response.getException();
        }

    }

    public List<Rent> getUserRents(User u) throws Exception {
        Request r = new Request();
        List<Rent> userRents = new ArrayList<>();
        r.setOperation(Operations.GET_USER_RENTS);
        r.setArgument(u);
        Response resp = Communication.getInstance().sendRequest(r);
        if (resp.getResponseType().equals(ResponseType.ERROR)) {
            throw resp.getException();
        }
        userRents = (List<Rent>) resp.getResult();
        return userRents;
    }

    public void restoreBook(Rent rental) throws Exception {
        Request request = new Request();
        request.setOperation(Operations.RESTORE_BOOK);
        request.setArgument(rental);
        Response response = Communication.getInstance().sendRequest(request);
        if (response.getResponseType().equals(ResponseType.ERROR)) {
            throw response.getException();

        }
    }

    public List<Rent> getRentList() throws Exception {
        Request request = new Request();
        request.setOperation(Operations.GET_RENTALS);
        Response response = Communication.getInstance().sendRequest(request);
        if (response.getResponseType().equals(ResponseType.ERROR)) {
            throw response.getException();
        }
        return (List<Rent>) response.getResult();

    }

    public void logout(Librarian user) {
        try {
            Communication.getInstance().logout(user);
        } catch (Exception ex) {
            Logger.getLogger(ControllerUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Rent> getAllUserRents(User u) throws Exception {
         Request r = new Request();
        List<Rent> userRents = new ArrayList<>();
        r.setOperation(Operations.GET_ALL_USER_RENTS);
        r.setArgument(u);
        Response resp = Communication.getInstance().sendRequest(r);
        if (resp.getResponseType().equals(ResponseType.ERROR)) {
            throw resp.getException();
        }
        userRents = (List<Rent>) resp.getResult();
        System.out.println("KOLIKO JE VRACENO:"+userRents.size());
        return userRents;
            }

    public List<User> getUsersByName(String name) throws Exception {
         Request request = new Request();
        request.setOperation(Operations.GET_USERS_BY_NAME);

        request.setArgument(name);
        Response response = Communication.getInstance().sendRequest(request);
        if (response.getResponseType().equals(ResponseType.ERROR)) {
            throw response.getException();
        }
        return (List<User>) response.getResult();
        }

}
