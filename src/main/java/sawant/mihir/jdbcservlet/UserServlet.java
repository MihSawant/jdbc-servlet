package sawant.mihir.jdbcservlet;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sawant.mihir.jdbcservlet.dao.UserDao;
import sawant.mihir.jdbcservlet.util.DbUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet(name = "userServlet", value = "/")
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String pathRequest = req.getServletPath();
        switch (pathRequest){
            case "/new", "/insert" ->{
                RequestDispatcher insertDispatch = req.getRequestDispatcher("/new_user.jsp");
                insertDispatch.forward(req, resp);
            }
            case "/update", "/edit" ->{
                RequestDispatcher updateDispatch = req.getRequestDispatcher("/update.jsp");
                updateDispatch.forward(req, resp);
            }
            case "/delete" ->{
                RequestDispatcher deleteDispatch = req.getRequestDispatcher("/delete.jsp");
                deleteDispatch.forward(req, resp);
            }
            default-> {
                ResultSet allData = null;
                RequestDispatcher all = req.getRequestDispatcher("/display_all.jsp");
                try{
                    Connection conn = DbUtil.getDbConnection();
                    String findAll = "SELECT * from users";
                    PreparedStatement select = conn.prepareStatement(findAll);
                   allData = select.executeQuery();
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
                req.setAttribute("users", allData);
                all.forward(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathRequest = req.getServletPath();
        switch (pathRequest){
            case "/new", "/insert" ->{
                resp.setContentType("text/html");
                PrintWriter out = resp.getWriter();
                try(Connection conn = DbUtil.getDbConnection()){
                    UserDao userDao = new UserDao();
                    userDao.setName(req.getParameter("name"));
                    userDao.setDesignation(req.getParameter("designation"));

                    String insert = "INSERT INTO users(name, designation) VALUES(?, ?)";
                    PreparedStatement insertQuery = conn.prepareStatement(insert);
                    insertQuery.setString(1, userDao.getName());
                    insertQuery.setString(2, userDao.getDesignation());
                    int i = insertQuery.executeUpdate();
                    if(i == 1){
                       out.println("<h1>Record inserted into db successfull</h1>");
                    } else{
                        out.println("<p>Something went wrong !</p>");
                    }
                    out.close();
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
            case "/update", "/edit" ->{
                resp.setContentType("text/html");

                PrintWriter out = resp.getWriter();
                try(Connection conn = DbUtil.getDbConnection()){
                    UserDao userDao = new UserDao();
                    userDao.setName(req.getParameter("name"));
                    userDao.setDesignation(req.getParameter("designation"));

                    String update = "UPDATE users SET designation = ? WHERE name LIKE ?";
                    PreparedStatement updateQuery = conn.prepareStatement(update);
                    updateQuery.setString(1, userDao.getDesignation());
                    updateQuery.setString(2, userDao.getName());
                    int i = updateQuery.executeUpdate();
                    if(i == 1){
                        out.println("<h1>Record updated successfull</h1>");
                    } else{
                        out.println("<p>Something went wrong !</p>");
                    }
                    out.close();
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }

            }
            case "/delete" ->{
                resp.setContentType("text/html");

                PrintWriter out = resp.getWriter();
                try(Connection conn = DbUtil.getDbConnection()){
                    UserDao userDao = new UserDao();
                    userDao.setName(req.getParameter("name"));

                    String delete = "DELETE FROM users WHERE name LIKE ?";
                    PreparedStatement deleteQuery = conn.prepareStatement(delete);
                    deleteQuery.setString(1, userDao.getName());
                    int i = deleteQuery.executeUpdate();
                    if(i == 1){
                        out.println("<h1>Record deleted successfull</h1>");
                    } else{
                        out.println("<p>Something went wrong !</p>");
                    }
                    out.close();
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
            default -> {

            }
        }
    }
}
