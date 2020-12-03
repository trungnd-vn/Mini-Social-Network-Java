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
import trungnd.blo.AccountBLO;
import trungnd.object.ErrorObj;
import trungnd.object.encryptPassword;

/**
 *
 * @author HOME
 */
public class SignUpController extends HttpServlet {
    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "loginPage.jsp";
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
            String emailID = request.getParameter("txtAccID");
            String name = request.getParameter("txtName");
            String password = request.getParameter("txtPassword");
            String passwordCon = request.getParameter("txtPasswordCon");
            String roleName = request.getParameter("txtRole");
            String status = request.getParameter("txtStatus");
            boolean valid = true;
            AccountBLO blo = new AccountBLO();
            ErrorObj errorObj = new ErrorObj();
            
            if (emailID.length() == 0) {
                valid = false;
                errorObj.setAccIDError("*Email can't be blank");
            } else if (!emailID.matches("[a-zA-Z0-9]{3,50}@[a-zA-Z0-9]{3,50}[.][a-zA-Z0-9]{2,7}([.][a-zA-Z]{1,2})?")) {
                valid = false;
                errorObj.setAccIDError("*Email must like example : abc123@gmail.com");
            }
            if (name.length() == 0) {
                valid = false;
                errorObj.setNameError("*Name can't be blank");
            }
            if (password.length() == 0) {
                valid = false;
                errorObj.setPasswordError("*Password can't be blank");
            } else if (password.length() > 100) {
                valid = false;
                errorObj.setPasswordError("*Password can't be over 100 chars");
            } 
            if (!passwordCon.equals(password)) {
                valid = false;
                errorObj.setPasswordConError("*Password must be retype exactly");
            } else if (passwordCon.length() == 0) {
                valid = false;
                errorObj.setPasswordConError("*Confirm Password can't be blank");
            }
            
            if (valid) {
                encryptPassword encrypt = new encryptPassword();
                String passEncrypt = encrypt.encryptPass(password);
                boolean result = blo.createAccount(emailID, passEncrypt, name, roleName, status);
                System.out.println(result);
                if (result) {
                    url = SUCCESS;
                } else {
                    request.setAttribute("DUPLICATE", "***Email is existed***");
                    url = INVALID;
                }
            } else {
                url = INVALID;
                request.setAttribute("INVALID", errorObj);
            }
        } catch (Exception e) {    
            log("ERROR at SignUpController: " + e.getMessage());
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
