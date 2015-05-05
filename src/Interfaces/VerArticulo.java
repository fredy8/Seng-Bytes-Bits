/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entidades.Articulo;
import Entidades.Editor;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author alfredo_altamirano
 */
@WebServlet(name = "VerArticulo", urlPatterns = {"/Articulo"})
public class VerArticulo extends HttpServlet {

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
        
        int id = Integer.parseInt(request.getParameter("id"));
        Articulo articulo = Articulo.getById(id);
        List<Editor> editores = articulo.getEditores();
        
        try (PrintWriter out = response.getWriter()) {
            Template.writeHeader(out, articulo.getTitulo(), request.getRequestURI());
            
            out.write("<h3>Título</h3><br>");
            out.write(articulo.getTitulo() + editores.size());
            out.write("<h3>Texto</h3>");
            out.write("<textarea class='form-control' readonly>" + articulo.getTexto() + "</textarea>");
            
            
            out.write("<h3>Autores</h3>");
            out.write("<ul>");
            editores.forEach((Editor editor) -> {
                out.write("<li>");
                out.write(editor.getFullName());
                out.write("</li>");
            });
            out.write("</ul>");
            
            HttpSession session = request.getSession();
            Editor editor = (Editor) session.getAttribute("editor");
            if (editor != null) {
                if (editor.canVote(articulo)) {
                    out.write("<form action='Votar?id=" + id + "' method='POST'>");
                    out.write("<input type='submit' class='btn btn-primary pull-right' value='Votar'>");
                } else if (editor.voted(articulo.getId())) {
                    out.write("<h3><span class='label label-default pull-right'>Votado</span></h3>");
                }
            }
            
            Template.writeFooter(out);
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
