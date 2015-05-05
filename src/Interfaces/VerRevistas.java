/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entidades.Articulo;
import Entidades.Revista;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Beto
 */
@WebServlet(name = "VerRevistas", urlPatterns = {"/Revistas"})
public class VerRevistas extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Template.writeHeader(out, "Revistas", request.getRequestURI());
                        
            out.write("<h1 class='text-center'>Listado de Revistas</h1>");
            out.write("<table class='table'><thead><tr>");
            out.write("<th style='width:40%'>Título</th>");
            out.write("<th style='width:60%'>Artículos</th>");
            out.write("</tr></thead><tbody>");
            
            List<Revista> revistas = Revista.getAll();
            
            
            for (Revista revista : revistas) {
                Integer counter = 1;
                out.write("<tr>");
                out.write("<td><b>" + revista.getTitulo() + "</b></td>");
                out.write("<td>");
                
                for (Integer idArticulo : revista.getIdArticulos()) {
                    Articulo articulo = Articulo.getById(idArticulo);
                    out.write("<a href='Articulo?id=" + idArticulo.toString() + "'>" + counter.toString() + ".- <i>" + articulo.getTitulo() + "</i></a><br>");
                    counter = counter + 1;
                }
               
                out.write("</td>");
                out.write("</tr>");
                
            }
            
            out.write("</tbody></table>");
            
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(VerRevistas.class.getName()).log(Level.SEVERE, null, ex);
        }
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
