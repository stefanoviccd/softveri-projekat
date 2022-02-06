/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.biblioteka.controller;

import rs.ac.bg.fon.ps.biblioteka.db.DbConnectionFactory;
import rs.ac.bg.fon.ps.biblioteka.model.Author;
import rs.ac.bg.fon.ps.biblioteka.model.Librarian;
import rs.ac.bg.fon.ps.biblioteka.model.User;
import rs.ac.bg.fon.ps.biblioteka.model.UserCard;
import rs.ac.bg.fon.ps.biblioteka.model.UserCategory;
import rs.ac.bg.fon.ps.biblioteka.model.BookCategory;
import rs.ac.bg.fon.ps.biblioteka.model.Book;
import rs.ac.bg.fon.ps.biblioteka.repository.impl.RepositoryAuthor;
import rs.ac.bg.fon.ps.biblioteka.repository.impl.RepositoryLibrarian;
import rs.ac.bg.fon.ps.biblioteka.repository.impl.RepositoryUser;
import rs.ac.bg.fon.ps.biblioteka.repository.impl.RepositoryUserCard;
import rs.ac.bg.fon.ps.biblioteka.repository.impl.RepositoryBookCategory;
import rs.ac.bg.fon.ps.biblioteka.repository.impl.RepositoryUserCategory;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.ac.bg.fon.ps.biblioteka.model.Rent;
import rs.ac.bg.fon.ps.biblioteka.so.AbstractSO;
import rs.ac.bg.fon.ps.biblioteka.so.author.GetAllAuthorsSO;
import rs.ac.bg.fon.ps.biblioteka.so.book.AddBookSO;
import rs.ac.bg.fon.ps.biblioteka.so.book.DeleteBookSO;
import rs.ac.bg.fon.ps.biblioteka.so.book.GetAllBooksSO;
import rs.ac.bg.fon.ps.biblioteka.so.book.GetBooksByQuerySO;
import rs.ac.bg.fon.ps.biblioteka.so.bookCategory.GetBookCategoriesSO;
import rs.ac.bg.fon.ps.biblioteka.so.bookCategory.GetBookCategoryIdSO;
import rs.ac.bg.fon.ps.biblioteka.so.librarian.AddLibrarianSO;
import rs.ac.bg.fon.ps.biblioteka.so.librarian.GetLibrariansSO;
import rs.ac.bg.fon.ps.biblioteka.so.librarian.LogOutSO;
import rs.ac.bg.fon.ps.biblioteka.so.rent.RentBookSO;
import rs.ac.bg.fon.ps.biblioteka.so.rent.RestoreBookSO;
import rs.ac.bg.fon.ps.biblioteka.so.librarian.LoginSO;
import rs.ac.bg.fon.ps.biblioteka.so.librarian.RemoveLibrarianSO;
import rs.ac.bg.fon.ps.biblioteka.so.rent.GetAllRentsSO;
import rs.ac.bg.fon.ps.biblioteka.so.rent.GetAllUserRentsSO;
import rs.ac.bg.fon.ps.biblioteka.so.rent.GetUserRentsSO;
import rs.ac.bg.fon.ps.biblioteka.so.user.AddUserSO;
import rs.ac.bg.fon.ps.biblioteka.so.user.DeleteUserSO;
import rs.ac.bg.fon.ps.biblioteka.so.user.GetAllUsersSO;
import rs.ac.bg.fon.ps.biblioteka.so.user.GetUsersByQuerySO;
import rs.ac.bg.fon.ps.biblioteka.so.user.UpdateUserSO;
import rs.ac.bg.fon.ps.biblioteka.so.userCard.DeleteUserCardSO;
import rs.ac.bg.fon.ps.biblioteka.so.userCard.GetUserCardsByQuerySO;
import rs.ac.bg.fon.ps.biblioteka.so.userCategory.GetAllUserCategoriesSO;

/**
 *
 * @author Dragana Stefanovic
 */
public class Controller {
    
    private static Controller instance;
    private Librarian currentUser;
    private RepositoryLibrarian repositoryLibrarian;
    private RepositoryUserCategory repositoryUserCategories;
    private RepositoryUser repositoryUser;
    private RepositoryUserCard repositoryUserCard;
    private RepositoryAuthor repositoryAuthor;
    private RepositoryBookCategory repositoryBookCategory;
    
    private Controller() {
        repositoryLibrarian = new RepositoryLibrarian();
        repositoryUserCategories = new RepositoryUserCategory();
        repositoryUser = new RepositoryUser();
        repositoryUserCard = new RepositoryUserCard();
        repositoryAuthor = new RepositoryAuthor();
        repositoryBookCategory = new RepositoryBookCategory();
    }
    
    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }
    
    public Librarian login(String user, String pass) throws Exception {
        LoginSO loginSO = new LoginSO();
        try {
            Librarian lib = new Librarian();
            lib.setUsername(user);
            lib.setPassword(pass);
            currentUser = (Librarian) loginSO.execute(lib);
            return currentUser;
            
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
            
        }
        
    }
    
    public Librarian getCurrentUser() {
        return this.currentUser;
    }
    
    public List<UserCategory> getUserCategories() throws Exception {
        GetAllUserCategoriesSO getAllUserCategoriesSO = new GetAllUserCategoriesSO();
        List<UserCategory> categories;
        try {
            categories = (List<UserCategory>) getAllUserCategoriesSO.execute(null);
            return categories;
            
        } catch (SQLException ex) {
            DbConnectionFactory.getInstance().getConnection().rollback();
            throw new Exception(ex.getMessage());
            
        }
    }
    
    public void addUser(User user) throws Exception {
        AddUserSO addUserSo = new AddUserSO();
        addUserSo.execute(user);
        
    }
    
    public List<User> getUsers() throws Exception {
        GetAllUsersSO getAllUsersSO = new GetAllUsersSO();
        try {
            return (List<User>) getAllUsersSO.execute(null);
        } catch (Exception e) {
            
            throw new Exception(e.getMessage());
        }
        
    }
    
    public UserCard getUserCardById(long id) throws Exception {
        GetUserCardsByQuerySO getUserCardsByQuerySO = new GetUserCardsByQuerySO();
        try {
            String query = "SELECT * FROM clanskakarta WHERE id=" + id;
            List<UserCard> userCards = (List<UserCard>) getUserCardsByQuerySO.execute(query);
            DbConnectionFactory.getInstance().getConnection().commit();
            return userCards.get(0);
        } catch (Exception e) {
            DbConnectionFactory.getInstance().getConnection().rollback();
            throw new Exception(e.getMessage());
        }
        
    }
    
    public void deleteUser(User user) throws Exception {
        DeleteUserSO deleteUserSO = new DeleteUserSO();
        try {
            deleteUserSO.execute(user);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    public void deleteUserCard(UserCard card) throws SQLException, Exception {
        DeleteUserCardSO deleteUserCardSO = new DeleteUserCardSO();
        try {
            deleteUserCardSO.execute(card);
        } catch (Exception e) {
            
            throw new Exception(e.getMessage());
        }
    }
    
    public List<User> getUsersByUsersCard(String brojCk) throws Exception {
        GetUsersByQuerySO getUsersByQuerySO = new GetUsersByQuerySO();
        try {
           // String query = "SELECT c.id as id, c.ime as ime, c.prezime as prezime, c.brojTelefona as bt, c.adresa as adresa, c.kategorijaId as kategorija, c.clanskaKartaId as ck FROM clan c INNER JOIN clanskakarta ck ON (c.clanskaKartaId=ck.id) WHERE ck.brojClanskeKarte='" + brojCk + "'";
             String query = " WHERE ck.brojClanskeKarte='" + brojCk + "'";
             return (List<User>) getUsersByQuerySO.execute(query);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        
    }
    
    public void updateUser(User oldUser, User newUser) throws Exception {
        List<User> usersForUpdate = new ArrayList<>();
        usersForUpdate.add(oldUser);
        usersForUpdate.add(newUser);
        UpdateUserSO updateUserSO = new UpdateUserSO();
        try {
            updateUserSO.execute(usersForUpdate);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        
    }
    
    public List<Author> getAuthors() throws Exception {
        GetAllAuthorsSO getAllAuthorsSO = new GetAllAuthorsSO();
        List<Author> authors = new ArrayList<>();
        try {
            authors = (List<Author>) getAllAuthorsSO.execute(null);
            return authors;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        
    }
    
    public void addBook(Book book) throws Exception {
        AbstractSO addbookSO = new AddBookSO();
        addbookSO.execute(book);
    }
    
    public BookCategory[] getBookCategories() throws Exception {
        //return BookCategory.values();
        BookCategory[] bookCategories = null;
        AbstractSO getBookCategoriesSO = new GetBookCategoriesSO();
        bookCategories = (BookCategory[]) getBookCategoriesSO.execute(null);
        return bookCategories;
        
    }
    
    public List<Book> getBooks() throws Exception {
        List<Book> books = new ArrayList<>();
        AbstractSO getAllBooksSO = new GetAllBooksSO();
        books = (List<Book>) getAllBooksSO.execute(null);
        return books;
    }
    
    public List<Book> getBooksByQuery(String query) throws Exception {
        GetBooksByQuerySO getBooksByQuerySO = new GetBooksByQuerySO();
        List<Book> books = (List<Book>) getBooksByQuerySO.execute(query);
        return books;
        
    }
    
    public Long getBookCategoryId(String categoryName) throws Exception {
        GetBookCategoryIdSO getBookCategoryIdSO = new GetBookCategoryIdSO();
        try {
            return (Long) getBookCategoryIdSO.execute(categoryName);
        } catch (SQLException e) {
            throw new Exception(e.getMessage());
        }
        
    }
    
    public void deleteBook(Book book) throws Exception {
        DeleteBookSO deleteBookSO = new DeleteBookSO();
        try {
            deleteBookSO.execute(book);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        
    }
    
    public void rentBook(User u, Book b) throws Exception {
        RentBookSO rentBookSO = new RentBookSO();
        List<Object> parameters = new ArrayList<>();
        parameters.add(u);
        parameters.add(b);
        rentBookSO.execute(parameters);
        
    }
    
    public void restoreBook(Rent rental) throws Exception {
        RestoreBookSO restoreBookSO = new RestoreBookSO();
        
        try {
            restoreBookSO.execute(rental);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    public List<Rent> getRentList() throws Exception {
        GetAllRentsSO getAllRentsSO = new GetAllRentsSO();
        try {
            List<Rent> userRents = (List<Rent>) getAllRentsSO.execute(null);
            return userRents;
            
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    public List<Rent> getUserRents(User u) throws Exception {
        GetUserRentsSO getUserRentsSO = new GetUserRentsSO();
        try {
            List<Rent> userRents = (List<Rent>) getUserRentsSO.execute(u);
            return userRents;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        
    }
    
    public List<Rent> getAllUserRents(User u) throws Exception {
        GetAllUserRentsSO getAllUserRentsSO = new GetAllUserRentsSO();
        try {
            List<Rent> userRents = (List<Rent>) getAllUserRentsSO.execute(u);
            System.out.println("DOSLO DO KONTROLERA: " + userRents.size());
            return userRents;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        
    }
    
    public void addLibrarian(Librarian librarian) throws Exception {
        AddLibrarianSO addLibrarianSO = new AddLibrarianSO();
        addLibrarianSO.execute(librarian);
    }
    
    public List<Librarian> getLibrarians() throws Exception {
        GetLibrariansSO getLibrariansSO = new GetLibrariansSO();
        return (List<Librarian>) getLibrariansSO.execute(null);
    }
    
    public void removeLibrarian(Librarian librarian) throws Exception {
        RemoveLibrarianSO removeLibrarianSO = new RemoveLibrarianSO();
        removeLibrarianSO.execute(librarian);
        
    }
    
    public List<User> getUsersByName(String name) throws Exception {
        GetAllUsersSO getAllUsersSO = new GetAllUsersSO();
        try {
            List<User> allUsers = (List<User>) getAllUsersSO.execute(null);
            List<User> queriedUsers = new ArrayList<>();
            for (User u : allUsers) {
                if (name.contains(" ")) {
                    if (name.toLowerCase().contains(u.getName().toLowerCase()) && (name.toLowerCase().contains(u.getLastName().toLowerCase())) || u.getLastName().toLowerCase().contains(name.toLowerCase().substring(name.indexOf(" ") + 1))) {
                        queriedUsers.add(u);
                    }
                } else {
                    if (name.toLowerCase().contains(u.getName().toLowerCase()) || u.getName().toLowerCase().contains(name.toLowerCase()) || name.toLowerCase().contains(u.getLastName().toLowerCase()) || u.getLastName().toLowerCase().contains(name.toLowerCase())) {
                        queriedUsers.add(u);
                    }
                }
                
            }
            return queriedUsers;
            
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    public void logout(Librarian user) {
        LogOutSO logoutSO = new LogOutSO();
        try {
            logoutSO.execute(user);
        } catch (Exception ex) {
           ex.printStackTrace();
        }
    }
}
