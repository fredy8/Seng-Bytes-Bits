/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Controles.ControlAltaEditor;
import Entidades.Editor;
import Entidades.Editor;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Beto
 */
@WebServlet(name = "CrearEditor", urlPatterns = {"/CrearEditor"})
public class CrearEditor extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void crearEditor(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        int tipo = Integer.parseInt(request.getParameter("tipo"));
        
        ControlAltaEditor.altaEditor(new Editor(username, password, nombre, apellido, tipo));
        response.sendRedirect("Articulos");
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
    protected void crearEditorForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
             
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Template.writeHeader(out, "Crear Editor", request.getRequestURI());
            
            out.write("<form action='CrearEditor' method='POST'>");
            out.write("<h3>Nombre de Usuario</h3><input type='text' name='username' class='form-control'><br>");
            out.write("<h3>Contraseña</h3><input type='password' name='password' class='form-control'><br>");
            out.write("<h3>Nombre</h3><input type='text' name='nombre' class='form-control'><br>");
            out.write("<h3>Apellido</h3><input type='text' name='apellido' class='form-control'><br>");
            out.write("<h3>Tipo [0-2]</h3><input type='text' name='tipo' class='form-control'><br>");
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
        crearEditorForm(request, response);
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
        crearEditor(request, response);
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
