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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alfredo_altamirano
 */
@WebServlet(name = "CrearArticulo", urlPatterns = {"/CrearArticulo"})
public class CrearArticulo extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void crearArticulo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String titulo = request.getParameter("titulo");
        String texto = request.getParameter("texto");
        List<Integer> idEditores = Arrays.asList(request.getParameter("autores").split(",")).stream().mapToInt((String str) -> Integer.parseInt(str)).boxed().collect(Collectors.toList());
        
        new Articulo(titulo, texto, idEditores).guardar();
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
    protected void crearArticuloForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<Editor> editores = Editor.getAll();
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Template.writeHeader(out, "Crear Articulo", request.getRequestURI());
            
            out.write("<form action='CrearArticulo'>");
            out.write("<h3>Titulo<h3><input type='text' name='titulo' class='form-control'><br>");
            out.write("<h3>Texto<h3><textarea class='form-control' rows='6' name='texto'></textarea><br>");
            out.write("<h3>Autores</h3><br>");
            editores.stream().forEach((e) -> {
                out.write("<label><input class='autor' id='autor" + e.getId() + "' type='checkbox'> " + e.getFullName() + "</label><br>");
            });
            out.write("<input id='autores' name='autores' type='hidden'>");
            out.write("<script src='compilarAutores.js'></script>");
            out.write("<input onclick='compilarAutores()' type='submit' class='btn btn-primary btn-block' value='Ingresar'>");
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
        crearArticuloForm(request, response);
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
        crearArticulo(request, response);
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
