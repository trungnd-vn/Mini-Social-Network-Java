/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungnd.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import trungnd.blo.ArticleBLO;

/**
 *
 * @author HOME
 */
public class SearchArticleController extends HttpServlet {
    private static final String ERROR = "error.jsp";
    private static final String SUCCESS_MEMBER = "indexMember.jsp";
    private static final String SUCCESS_ADMIN = "indexAdmin.jsp";
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
            throws ServletException, IOException, IllegalStateException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String searchValue = request.getParameter("txtSearch");
            if ("".equals(searchValue)) {
                searchValue = "";
            }
            int currentPage;
            if (request.getAttribute("PAGEID") != null) {
                currentPage = (int) request.getAttribute("PAGEID");
            } else {
                currentPage = Integer.parseInt(request.getParameter("pageIDPaging")); //từ paging
            } 
            int pageMaxSize = 20;
//            System.out.println(currentPage);

            ArticleBLO blo = new ArticleBLO();
            List result = blo.searchByLikeInput(searchValue, currentPage, pageMaxSize);
//            for (Object object : result) {
//                System.out.println(object);
//            }
            if (result != null) {                   
                request.setAttribute("LIST_ARTICLE", result);   
                request.setAttribute("CURRENT_PAGE", currentPage);
                request.setAttribute("ARTICLE_COUNT", blo.getAmountFindByLikeInput(searchValue, pageMaxSize));
                HttpSession session = request.getSession();
                String roleName = (String) session.getAttribute("ROLE");
                if ("member".equals(roleName)) {
                    url = SUCCESS_MEMBER;
                } else if ("admin".equals(roleName)) {
                    url = SUCCESS_ADMIN;
                }            
            }           
        } catch (Exception e) {
            log("ERROR at SearchArticleController: " + e.getMessage());
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
