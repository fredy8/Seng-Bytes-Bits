/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Controles.ControlAltaSuscriptor;
import Entidades.Articulo;
import Entidades.Suscriptor;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Beto
 */
@WebServlet(name = "CrearSuscriptor", urlPatterns = {"/CrearSuscriptor"})
public class CrearSuscriptor extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void crearSuscriptor(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String direccion = request.getParameter("direccion");
        String tarjeta_de_credito = request.getParameter("tarjeta_de_credito");
        
        ControlAltaSuscriptor.altaSuscriptor(new Suscriptor(nombre, apellido, direccion, tarjeta_de_credito));
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
    protected void crearSuscriptorForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Template.writeHeader(out, "Crear Suscriptor", request.getRequestURI());
            
            out.write("<form action='CrearSuscriptor' method='POST'>");
            out.write("<h3>Nombre</h3><input type='text' name='nombre' class='form-control'><br>");
            out.write("<h3>Apellido</h3><input type='text' name='apellido' class='form-control'><br>");
            out.write("<h3>Direccion</h3><input type='text' name='direccion' class='form-control'><br>");
            out.write("<h3>Tarjeta de Crédito</h3><input type='text' name='tarjeta_de_credito' class='form-control'><br>");
            out.write("<input type='submit' class='btn btn-primary btn-block' value='Crear'>");
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
        crearSuscriptorForm(request, response);
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
        crearSuscriptor(request, response);
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
