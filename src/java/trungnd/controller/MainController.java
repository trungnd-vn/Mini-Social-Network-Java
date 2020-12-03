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

/**
 *
 * @author HOME
 */
public class MainController extends HttpServlet {
    private static final String ERROR = "error.jsp";
    private static final String LOGIN = "LoginController";
    private static final String LOGOUT = "LogOutController";
    private static final String SIGNUP = "createAccount.jsp";

    private static final String CREATE_ACC = "SignUpController";
    private static final String CANCEL = "loginPage.jsp";

    private static final String SEARCH = "SearchArticleController";
    private static final String SHOWDETAILS = "ShowDetailsController";
    private static final String POST = "PostController";
    private static final String POST_COMMENT = "PostCommentController";
            
    private static final String DELETEPOST = "DeletePostController";
    private static final String DEL_COMMENT = "DeleteCommentController";
    
    private static final String LIKE = "LikeController";
    private static final String DISLIKE = "DislikeController";
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
            String action = request.getParameter("action");
            switch (action) {
                case "Sign In":
                    url = LOGIN;
                    break;
                case "Log Out":
                    url = LOGOUT;
                    break;
                case "Sign Up":
                    url = SIGNUP;
                    break;
                case "Create New Account":
                    url = CREATE_ACC;
                    break;
                case "Cancel":
                    url = CANCEL;
                    break;
                case "Search":
                    url = SEARCH;
                    request.setAttribute("PAGEID", 1);
                    break;
                case "ShowDetails":
                    url = SHOWDETAILS;
                    break;
                case "Post":
                    url = POST;
                    break;
                case "Delete":
                    url = DELETEPOST;
                    break;
                case "Post Comment":
                    url = POST_COMMENT;
                    break;
                case "Del":
                    url = DEL_COMMENT;
                    break;
                case "Like":
                    url = LIKE;
                    break;
                case "Dislike":
                    url = DISLIKE;
                    break;
                default:
                    request.setAttribute("ERROR", "Your action is invalid");
                    break;
            }
        } catch (Exception e) {
            log("ERROR at MainController : " + e.getMessage());
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
