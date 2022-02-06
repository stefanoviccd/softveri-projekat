/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.biblioteka.server.threads;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;
import rs.ac.bg.fon.ps.biblioteka.communication.Operations;
import rs.ac.bg.fon.ps.biblioteka.communication.Receiver;
import rs.ac.bg.fon.ps.biblioteka.communication.Request;
import rs.ac.bg.fon.ps.biblioteka.communication.Response;
import rs.ac.bg.fon.ps.biblioteka.communication.ResponseType;
import rs.ac.bg.fon.ps.biblioteka.communication.Sender;
import rs.ac.bg.fon.ps.biblioteka.controller.Controller;
import rs.ac.bg.fon.ps.biblioteka.model.Author;
import rs.ac.bg.fon.ps.biblioteka.model.Book;
import rs.ac.bg.fon.ps.biblioteka.model.BookCategory;
import rs.ac.bg.fon.ps.biblioteka.model.Librarian;
import rs.ac.bg.fon.ps.biblioteka.model.Rent;
import rs.ac.bg.fon.ps.biblioteka.model.User;
import rs.ac.bg.fon.ps.biblioteka.model.UserCategory;

/**
 *
 * @author Dragana Stefanovic
 */
public class HandleClientThread extends Thread {

    private ServerThread server;
    private Socket socket;
    private Librarian user;

    public Socket getSocket() {
        return socket;
    }

    public Librarian getUser() {
        return user;
    }
    

    public HandleClientThread(ServerThread serverthread, Socket socket) {
        this.server = serverthread;
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            handleClientsRequest(socket);
        } catch (SocketException se) {
            System.out.println("Klijent prekinuo program.");
   
        } catch (Exception ex) {
            System.out.println("Klijent prekinuo program.");
        }

    }

    void stopCommunication() {
        try {
            Response response = new Response();
            response.setOperation(Operations.SERVER_STOPPED);
            new Sender(socket).send(response);
            sleep(1000);
            socket.close();

        } catch (IOException ex) {
            System.out.println("Error stopping communication - class Handle..");
        } catch (Exception ex) {
            System.out.println("Error stopping communication - class Handle..");
        }
    }

    public Response handleClientsRequest(Socket socket) throws Exception {
        Response response = null;
        while (true) {

            try {
                Request request = (Request) new Receiver(socket).receive();

                switch (request.getOperation()) {
                    case Operations.LOGIN:
                        Librarian user = (Librarian) request.getArgument();
                        response = login(user);
                        break;
                    case Operations.GET_USERS_BY_USER_CARD:
                        response = getUsersByUserCard((String) request.getArgument());
                        break;
                    case Operations.GET_USERS_BY_NAME:
                        response = getUsersByName((String) request.getArgument());
                        break;
                    case Operations.GET_USERS:

                        response = getUsers();
                        break;
                    case Operations.DELETE_USER:

                        response = deleteUser(request);
                        break;
                    case Operations.UPDATE_USER:
                        response = updateUser(request);
                        break;
                    case Operations.GET_USER_CATEGORIES:
                        response = getUserCategories();
                        break;
                    case Operations.ADD_USER:
                        response = addUser(request);
                        break;
                    case Operations.GET_AUTHORS:
                        response = getAuthors();
                        break;
                    case Operations.ADD_BOOK:
                        response = addBook(request);
                        break;
                    case Operations.GET_BOOKS:
                        response = getBooks();
                        break;
                    case Operations.GET_BOOKS_BY_QUERY:
                        response = getBooksByQuery(request);
                        break;
                    case Operations.GET_BOOK_CATEGORIES:
                        response = getBookCategories();
                        break;
                    case Operations.GET_BOOK_CATEGORIY_ID:
                        response = getBookCategoryId(request);
                        break;
                    case Operations.DELETE_BOOK:
                        response = deleteBook(request);
                        break;
                    case Operations.RENT_BOOK:
                        response = rentBook(request);
                        break;
                    case Operations.GET_USER_RENTS:
                        response = getUserRents(request);
                        break;
                    case Operations.GET_ALL_USER_RENTS:
                        response = getAllUserRents(request);
                        break;
                    case Operations.RESTORE_BOOK:
                        response = restoreBook(request);
                        break;
                    case Operations.GET_RENTALS:
                        response = getRentals(request);
                        break;
                    case Operations.LOGOUT:
                        logout(request);
                        server.logout(this);
                        
                        break;

                    default: ;
                }
                new Sender(socket).send(response);

            } catch (ClassNotFoundException ex) {
                System.out.println("Klijent je prekinuo program.");
                server.setUserLoggedIn(user, false);
            }

        }

    }

    private Response login(Librarian user) {
        Response response = new Response();
        try {
            Librarian dbUser = Controller.getInstance().login(user.getUsername(), user.getPassword());
            response.setResponseType(ResponseType.SUCCESS);
            response.setResult(dbUser);
            response.setOperation(Operations.LOGIN);
            server.setUserLoggedIn(dbUser,true);
            this.user=dbUser;
        } catch (Exception ex) {
            response.setResponseType(ResponseType.ERROR);
            response.setException(new Exception(ex.getMessage()));
            ex.printStackTrace();
            response.setOperation(Operations.LOGIN);

        }
        return response;
    }

    private Response getUsersByUserCard(String cardNumber) {
        Response response = new Response();
        try {
            List<User> users = Controller.getInstance().getUsersByUsersCard(cardNumber);
            response.setResponseType(ResponseType.SUCCESS);
            response.setResult(users);
        } catch (Exception e) {
            response.setResponseType(ResponseType.ERROR);
            response.setException(e);
        }
        return response;
    }

    private Response getUsers() {
        Response response = new Response();
        try {
            List<User> users = Controller.getInstance().getUsers();
            response.setResponseType(ResponseType.SUCCESS);
            response.setResult(users);
        } catch (Exception e) {
            response.setResponseType(ResponseType.ERROR);
            response.setException(e);
        }
        return response;
    }

    private Response deleteUser(Request request) {
        Response response = new Response();
        try {
            Controller.getInstance().deleteUser((User) request.getArgument());
            response.setResponseType(ResponseType.SUCCESS);

        } catch (Exception e) {
            e.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(e);
        }
        return response;
    }

    private Response updateUser(Request request) {
        User oldUser = ((List<User>) request.getArgument()).get(0);
        User newUser = ((List<User>) request.getArgument()).get(1);
        Response response = new Response();
        try {

            Controller.getInstance().updateUser(oldUser, newUser);
            response.setResponseType(ResponseType.SUCCESS);

        } catch (Exception e) {
            e.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(e);
        }
        return response;
    }

    private Response getUserCategories() {
        Response response = new Response();
        try {
            List<UserCategory> userCategories = Controller.getInstance().getUserCategories();
            response.setResponseType(ResponseType.SUCCESS);
            response.setResult(userCategories);

        } catch (Exception e) {
            e.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(e);
        }
        return response;
    }

    private Response addUser(Request request) {
        User user = (User) request.getArgument();
        Response response = new Response();
        try {
            Controller.getInstance().addUser(user);
            response.setResponseType(ResponseType.SUCCESS);

        } catch (Exception e) {
            e.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(e);
        }
        return response;

    }

    private Response addBook(Request request) {
        Book book = (Book) request.getArgument();
        Response response = new Response();
        try {
            Controller.getInstance().addBook(book);
            response.setResponseType(ResponseType.SUCCESS);

        } catch (Exception e) {
            e.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(e);
        }
        return response;
    }

    private Response getBooks() {
        Response response = new Response();
        try {
            List<Book> books = Controller.getInstance().getBooks();
            response.setResponseType(ResponseType.SUCCESS);
            response.setResult(books);
        } catch (Exception e) {
            e.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(e);
        }
        return response;
    }

    private Response getBookCategories() {
        Response response = new Response();
        try {
            BookCategory[] bookCategories = Controller.getInstance().getBookCategories();
            response.setResponseType(ResponseType.SUCCESS);
            response.setResult(bookCategories);

        } catch (Exception e) {
            e.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(e);
        }
        return response;
    }

    private Response getBookCategoryId(Request request) {
        Response response = new Response();
        try {
            Long kategorijaId = Controller.getInstance().getBookCategoryId(((BookCategory) request.getArgument()).toString());
            response.setResponseType(ResponseType.SUCCESS);
            response.setResult(kategorijaId);
        } catch (Exception e) {
            e.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(e);
        }
        return response;
    }

    private Response getAuthors() {
        Response response = new Response();
        try {
            List<Author> authors = Controller.getInstance().getAuthors();
            response.setResponseType(ResponseType.SUCCESS);
            response.setResult(authors);
        } catch (Exception e) {
            e.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(e);
        }
        return response;
    }

    private Response getBooksByQuery(Request request) {
        String query = (String) request.getArgument();
        Response response = new Response();
        try {
            List<Book> books = Controller.getInstance().getBooksByQuery(query);
            response.setResponseType(ResponseType.SUCCESS);
            response.setResult(books);
        } catch (Exception e) {
            e.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(e);
        }
        return response;

    }

    private Response deleteBook(Request request) {
        Response response = new Response();
        try {
            Controller.getInstance().deleteBook((Book) request.getArgument());
            response.setResponseType(ResponseType.SUCCESS);

        } catch (Exception e) {
            e.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(e);
        }
        return response;
    }

    private Response rentBook(Request request) {
        Response response = new Response();
        User u = (User) ((List<Object>) request.getArgument()).get(0);
        Book b = (Book) ((List<Object>) request.getArgument()).get(1);
        try {
            Controller.getInstance().rentBook(u, b);
            response.setResponseType(ResponseType.SUCCESS);

        } catch (Exception e) {
            e.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(e);
        }
        return response;

    }

    private Response getUserRents(Request request) {
        Response response = new Response();
        User u = (User) request.getArgument();

        try {
            List<Rent> rents = Controller.getInstance().getUserRents(u);
            response.setResult(rents);
            response.setResponseType(ResponseType.SUCCESS);

        } catch (Exception e) {
            e.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(e);
        }
        return response;

    }

    private Response restoreBook(Request request) {
        Response response = new Response();
        Rent rental = (Rent) request.getArgument();
        try {
            Controller.getInstance().restoreBook(rental);
            response.setResponseType(ResponseType.SUCCESS);

        } catch (Exception e) {
            e.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(e);
        }
        return response;
    }

    private Response getRentals(Request request) {
        Response response = new Response();

        try {
            List<Rent> rents = Controller.getInstance().getRentList();
            response.setResult(rents);
            response.setResponseType(ResponseType.SUCCESS);

        } catch (Exception e) {
            e.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(e);
        }
        return response;
    }

    private Response getAllUserRents(Request request) {
        Response response = new Response();
        User u = (User) request.getArgument();

        try {
            List<Rent> rents = Controller.getInstance().getAllUserRents(u);
            response.setResult(rents);
            response.setResponseType(ResponseType.SUCCESS);

        } catch (Exception e) {
            e.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(e);
        }
        return response;
    }

    private Response getUsersByName(String name) {
        Response response = new Response();
        try {
            List<User> users = Controller.getInstance().getUsersByName(name);
            response.setResponseType(ResponseType.SUCCESS);
            response.setResult(users);
        } catch (Exception e) {
            response.setResponseType(ResponseType.ERROR);
            response.setException(e);
        }
        return response;
        }

    private void logout(Request request) {
        Librarian user=(Librarian) request.getArgument();
        Controller.getInstance().logout(user);
        server.setUserLoggedIn(user, false);
        }

}
