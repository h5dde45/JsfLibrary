
package com.books.servlets;

import com.books.controllers.SearchController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet(name = "ShowImage",urlPatterns = "/ShowImage")
public class ShowImage extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("image/jpeg");  
        OutputStream out = response.getOutputStream();  
        try {
            int id = Integer.valueOf(request.getParameter("id"));
            
            SearchController searchController =
                    (SearchController)request.getSession(false).getAttribute("searchController");
            
            byte[] image = searchController.getImage(id);         
            response.setContentLength(image.length);
            out.write(image);
        }catch (Exception ex){
            ex.printStackTrace();
        } finally {            
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
