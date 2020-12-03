/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungnd.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import trungnd.blo.ArticleBLO;
import trungnd.db.Account;
import trungnd.object.RandomID;

/**
 *
 * @author HOME
 */
public class PostController extends HttpServlet {
    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "indexMember.jsp";
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
            String realPath_DB = "";
            boolean isMultiPart = ServletFileUpload.isMultipartContent(request);
            if (!isMultiPart) {
            } else {
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                List items = null;
                try {
                    items = upload.parseRequest(request);
                } catch (FileUploadException e) {
                    log("ERROR FileUploadException at PostController: " + e.getMessage());
                    e.printStackTrace();
                }
                Iterator iter = items.iterator();
                Hashtable params = new Hashtable();
                String fileName = null;
                while (iter.hasNext()) {
                    FileItem item = (FileItem) iter.next();
                    if (item.isFormField()) {
                        params.put(item.getFieldName(), item.getString());
                    } else {
                        try {
                            String itemName = item.getName();
                            fileName = itemName.substring(itemName.lastIndexOf("\\") + 1);
                            System.out.println("path " + fileName);
                            String realPath = getServletContext().getRealPath("/") 
                                    + "images\\" + fileName;
                            System.out.println("Real Path " + realPath);
                            realPath_DB = fileName;
                            File savedFile = new File(realPath);
                            item.write(savedFile);
                        } catch (Exception e) {
                            log("ERROR ImageUploadException at PostController: " + e.getMessage());
                            e.printStackTrace();
                        }
                    }
                }
                RandomID rdID = new RandomID();
                String articleID = rdID.getRandomString(5);
                System.out.println(articleID);
                String articleTitle = (String) params.get("txtPostTitle");
//                System.out.println(articleTitle);
                String articleDescription = (String) params.get("txtPostDescription");
//                System.out.println(articleDescription);
                Date articleDate = new Date();           
                String articleStatus = "Active";

                HttpSession session = request.getSession();
                Account accID = (Account) session.getAttribute("ACCOUNT");
                String articleImage = realPath_DB;
                if (articleImage.length() == 0) {
                    articleImage = null;
                }
                
//                System.out.println("articleTitle" + articleTitle + articleTitle.length());
//                System.out.println("articleDescription" + articleDescription + articleTitle.length());
                ArticleBLO blo = new ArticleBLO();

                if (accID != null) {
                    if (articleTitle.length() != 0 || articleDescription.length() != 0) {
                        blo.createArticle(articleID, articleTitle, articleDescription, articleDate, articleStatus, articleImage, accID);
                        request.setAttribute("NOTI", "Post Successful - Please Reload Page");
                    } else {
                        request.setAttribute("NOTI", "Post UnSuccessful - Try Again");
                    }          
                    url = SUCCESS;
                }
            }        
        } catch (Exception e) {
            log("Error at PostController: " + e.getMessage());
            e.printStackTrace();
        } finally {
//            request.getRequestDispatcher(url).forward(request, response);
            response.sendRedirect("SearchArticleController?pageIDPaging=1&txtSearch=");
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
