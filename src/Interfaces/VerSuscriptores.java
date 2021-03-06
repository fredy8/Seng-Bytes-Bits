/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Controles.ControlObtenerSuscriptores;
import Entidades.Editor;
import Entidades.Suscriptor;
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
 * @author Beto
 */
@WebServlet(name = "VerSuscriptores", urlPatterns = {"/Suscriptores"})
public class VerSuscriptores extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            Template.writeHeader(out, "Suscriptores", request.getRequestURI());
            out.write("<h1 class='text-center'>Listado de Suscriptores</h1>");

            List<Suscriptor> suscriptores = ControlObtenerSuscriptores.obtenerSuscriptores();
            String table;
            table = "<table class='table'><thead><tr>" +
                    "<th style='width:25%;'>Nombre Completo</th>" +
                    "<th style='width:25%;'>Dirección</th>" +
                    "<th style='width:25%;'>Suscripcion</th>" +
                    "<th style='width:25%;'></th>" +
                    "</tr></thead><tbody>";
            out.write(table);

            for (Suscriptor suscriptor : suscriptores) {
                String html = "<tr>" +
                    "<td>" + suscriptor.getFullName() +"</td>" +
                    "<td>" + suscriptor.getDireccion() +"</td>" +
                    "<td>" + suscriptor.getVencimiento() +"</td>" +
                    "<td><a href='RenovarSuscripcion?id=" + suscriptor.getId() + 
                        "' class='btn btn-sm btn-primary pull-right'>Renovar Suscripción</a>" +
                    "</td>" +
                  "</tr>";
                out.write(html);
            }
            out.write("</tbody></table>");
            out.write("<a href='CrearSuscriptor' class='btn btn-primary'>Crear Suscriptor</a>");
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
