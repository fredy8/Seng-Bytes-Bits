package Interfaces;

import Entidades.Articulo;
import Entidades.Editor;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
            Template.writeHeader(out, "Artículos", request.getRequestURI());
            out.write("<h1 class='text-center'>Listado de Artículos</h1>");

            HttpSession session = request.getSession();
            Editor editor = (Editor) session.getAttribute("editor");
            
            List<Articulo> articulos = Articulo.getAll();
            out.write("<table class='table'><thead><tr>");
            if (editor != null) {
                if (editor.isJudge()) {
                    out.write("<th style='width:25%;'>Título</th>");
                    out.write("<th style='width:65%;'>Resumen Breve</th>");
                    out.write("<th style='width:10%;'>Votado</th>");
                } else if (editor.isChief()) {
                    out.write("<th style='width:10%;'>Publicar</th>");
                    out.write("<th style='width:25%;'>Título</th>");
                    out.write("<th style='width:55%;'>Resumen Breve</th>");
                    out.write("<th style='width:10%;'>Votos</th>");
                } else {
                    out.write("<th style='width:30%;'>Título</th>");
                    out.write("<th style='width:70%;'>Resumen Breve</th>");
                }
            } else {
                out.write("<th style='width:30%;'>Título</th>");
                out.write("<th style='width:70%;'>Resumen Breve</th>");
            }
            out.write("</tr></thead><tbody>");
            
            for (Articulo articulo : articulos) {
                out.write("<tr>");
                boolean autorReciente = articulo.authorsPublishedRecently();
                if (editor != null && editor.isChief()) {
                    out.write("<td><input " + (articulo.publicado() || autorReciente ? "disabled='disabled' " : "") +  "class='articulo' name='" + articulo.getId() + "' type='checkbox' onclick='permitePublicacion(6, 9)'></td>");
                }
                out.write("<td><a href='Articulo?id=" + articulo.getId() + "'>" + articulo.getTitulo() + "</a></td>");
                out.write("<td>" + articulo.getResumen() +"</td>");
                out.write("<td>");
                if (editor != null) {
                    if (editor.isJudge()) {
                        if (editor.voted(articulo.getId())) {
                            out.write("<span class='label label-default'>Votado</span>");
                        } else if (autorReciente) {
                            out.write("<span class='label label-default'>Autor Reciente</span>");
                        } else if (editor.canVote(articulo)) {
                            out.write("<form action='Votar?id=" + articulo.getId() + "' method='POST'>");
                            out.write("<input type='submit' value='votar' class='btn btn-sm btn-primary'>");
                            out.write("</form>");
                        }
                    } else if (editor.isChief()) {
                        if (articulo.publicado()) {
                            out.write("<span class='label label-default'>Publicado</span>");
                        } else if (autorReciente) {
                            out.write("<span class='label label-default'>Autor Reciente</span>");
                        } else {
                            out.write(articulo.getVotos() + "");
                        }
                    }
                }
                out.write("</td>");
                out.write("</tr>");
            }
            out.write("</tbody></table>");
            out.write("<form class='form-inline'>");
            out.write("<button onclick=\"window.location.href = 'CrearArticulo'; return false; \" class='btn btn-primary'>Crear Articulo</button>");
            if (editor != null && editor.isChief()) {
                out.write("<button class='btn btn-success' style='margin-left: 20px;' onclick=\"$('#publicacion').slideToggle(100); return false;\">Publicar</button>");
                out.write("</form>");
                out.write("<form class='form-inline' action='Publicar' method='POST'>");
                out.write("<div id='publicacion'>");
                out.write("<input type='hidden' id='articulos' name='articulos'>");
                out.write("<script src='compilarArticulos.js'></script>");
                out.write("<h3>Titulo</h2>");
                out.write("<input type='text' class='form-control' style='width: 100%;' name='titulo'>");
                out.write("<h2>Carta de Editor en Jefe</h2>");
                out.write("<textarea class='form-control' rows='6' style='width: 100%;' name='carta'></textarea><br>");
                out.write("<input onclick='compilarArticulos()' type='submit' style='margin-top: 20px' class='btn btn-primary' id='publicar' disabled='disabled' value='Publicar Revista'>");
                out.write("</div>");
                out.write("<script>$('#publicacion').slideToggle(100);</script>");
            }
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
