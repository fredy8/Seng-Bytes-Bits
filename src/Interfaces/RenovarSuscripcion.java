/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entidades.Suscriptor;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
@WebServlet(name = "RenovarSuscripcion", urlPatterns = {"/RenovarSuscripcion"})
public class RenovarSuscripcion extends HttpServlet {

    /**
     * Processes requests for HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     */
    protected void renovarSuscripcion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        int anios = Integer.parseInt(request.getParameter("tiempo_restante"));
        int id = Integer.parseInt(request.getParameter("id"));
        Suscriptor suscriptor = Suscriptor.getById(id);
        suscriptor.setTiempoRestante(anios);
        response.sendRedirect("Suscriptores");
    }
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void renovarSuscripcionForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        int id = Integer.parseInt(request.getParameter("id"));
        Suscriptor suscriptor = Suscriptor.getById(id);
        
        try (PrintWriter out = response.getWriter()) {
            Template.writeHeader(out, "Renovar Suscripción", request.getRequestURI());
            
            String options[] = {"1 año", "2 años", "3 años", "4 años", "5 años", "6 años", "7 años", "8 años"};
            String values[] = {"1", "2", "3", "4", "5", "6", "7", "8"};
            String htmlSelect = "<select name='tiempo_restante' class='inline form-control shorten'>";
            for(int i = 0; i < options.length ; i++){
                htmlSelect += "<option value='" + values[i] + "'>" + options[i] + "</option>";
            }
            htmlSelect += "</select><br><br>";
            
            out.write("<form action='RenovarSuscripcion' method='POST'>");
            out.write("<h3 class='inline'>Nombre: </h3><h3 class='thin inline'>" + suscriptor.getFullName() + "</h3><br><br>");
            out.write("<h3 class='inline'>Direccion: </h3><h3 class='thin inline'>" + suscriptor.getDireccion() + "</h3><br><br>");
            out.write("<h3 class='inline'>Tarjeta de Crédito: </h3><h3 class='thin inline'>" + suscriptor.getTarjeta() + "</h3><br><br>");
            out.write("<h3 class='inline'>Renovar por: </h3>" + htmlSelect);
            out.write("<input type='text' name='id' value='" + suscriptor.getId() + "' hidden>");
            out.write("<input type='submit' class='btn btn-primary btn-block' value='Renovar'>");
            
            out.write("</form>");
            
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
        renovarSuscripcionForm(request, response);
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
        try {
            renovarSuscripcion(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(RenovarSuscripcion.class.getName()).log(Level.SEVERE, null, ex);
        }
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
