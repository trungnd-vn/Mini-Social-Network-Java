/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungnd.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import trungnd.blo.AccountBLO;
import trungnd.db.Account;
import trungnd.object.ErrorObj;
import trungnd.object.encryptPassword;

/**
 *
 * @author HOME
 */
public class LoginController extends HttpServlet {
    private static final String ERROR = "error.jsp";
    private static final String MEMBER = "SearchArticleController";
    private static final String ADMIN = "SearchArticleController";
    private static final String INVALID = "loginPage.jsp";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String email = request.getParameter("txtEmail");
//            System.out.println(email);
            String password = request.getParameter("txtPassword");
//            System.out.println(password);
            boolean valid = true;
            ErrorObj errorObj = new ErrorObj();
            
            if (email.length() == 0) {
                errorObj.setEmailError("*Email can't be blank!");
                valid = false;
            } else {
                if (!email.matches("[a-zA-Z0-9]{3,50}@[a-zA-Z0-9]{3,50}[.][a-zA-Z0-9]{2,7}([.][a-zA-Z]{1,2})?")) {
                    errorObj.setEmailError("*Error type of email!");
                    valid = false;
                }
            }
            if (password.length() == 0) {
               errorObj.setPasswordError("*Password can't be blank!");
               valid = false;
            }
            if (valid) {
                AccountBLO blo = new AccountBLO();
                encryptPassword encrypt = new encryptPassword();
                String passEncrypt = encrypt.encryptPass(password);
                boolean result = blo.checkLogin(email, passEncrypt);
                if (!result) {
                    request.setAttribute("ERROR", "Invalid Email Or Password!");
                    url = INVALID;
                } else {
                    HttpSession session = request.getSession(); 
                    Account acc = new Account();
                    acc = blo.loginPage(email);
                    switch (acc.getRoleName()) {
                        case "member":
                            session.setAttribute("ROLE", acc.getRoleName());
                            url = MEMBER;
                            break;
                        case "admin":
                            session.setAttribute("ROLE", acc.getRoleName());
                            url = ADMIN;
                            break;
                        default:
                            request.setAttribute("ERROR", "Your role is invalid");
                            break;
                    }
                    session.setAttribute("EMAILID", acc.getAccID());
                    session.setAttribute("NAME", acc.getAccName()); 
                    request.setAttribute("PAGEID", 1);
                    session.setAttribute("ACCOUNT", acc);
                }
            } else {
                url = INVALID;
                request.setAttribute("INVALID", errorObj);
            }
            
        } catch (Exception e) {
            log("ERROR at LoginController : " + e.getMessage());
//            e.printStackTrace();
            
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
