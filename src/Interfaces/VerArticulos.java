package Interfaces;

import Entidades.Articulo;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "VerArticulos", urlPatterns = {"/Articulos"})
public class VerArticulos extends HttpServlet {

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
            Template.writeHeader(out, "Publicaciones", request.getRequestURI());
            out.write("<h1 class='text-center'>Listado de Artículos</h1>");

            List<Articulo> articulos = Articulo.getAll();
            String table;
            table = "<table class='table'><thead><tr>" +
                    "<th style='width:30%;'>Título</th>" +
                    "<th style='width:70%;'>Resumen Breve</th>" +
                    "</tr></thead><tbody>";
            out.write(table);

            for (Articulo articulo : articulos) {
                String html = "<tr>" +
                    "<td>" + articulo.getTitulo() +"</td>" +
                    "<td>" + articulo.getResumen() +"</td>" +
                  "</tr>";
                out.write(html);
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
