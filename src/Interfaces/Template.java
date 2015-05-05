package Interfaces;

import java.io.PrintWriter;

public class Template {
    
    public static void writeHeader(PrintWriter out, String title, String url) {
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>" + title + "</title>");
        out.println("<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css'>");
        out.println("<link rel='stylesheet' href='main.css'>");
        out.println("<script src='https://code.jquery.com/jquery-2.1.4.min.js'></script>");
        out.println("<script src='http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js'></script>");
        out.println("<script src='VerArticulos.js'></script>");
        out.println("</head>");
        out.println("<body>");
        writeNavBar(out, url);
        out.println("<div class='container'>");
        out.println("<div class='container-fluid'>");   
    }
    
    public static void writeNavBar(PrintWriter out, String currentUrl) {
        String items[] = {"Articulos", "Crear Artículo", "Suscriptores", "Crear Suscriptor"};
        String hrefs[] = {"Articulos", "CrearArticulo", "Suscriptores", "CrearSuscriptor"};
        
        out.println("<nav class='navbar navbar-default'>");
        out.println("<div class='container-fluid'>");
        out.println("</div'>");
        out.println("<a class='navbar-brand' href='#'>Logo</a>");
        out.println("<div id='navbar'>");
        
        if (!currentUrl.equals("/Seng-Bytes-Bits/Login")) { 
            out.println("<ul class='nav navbar-nav'>");
            for (int i = 0; i < items.length; i++) {
                out.println("<li>");
                out.println("<a href='" + hrefs[i] + "'>" + items[i] + "</a>");
                out.println("</li>");
            }
            out.println("</ul>");
            out.println("<ul class='nav navbar-nav navbar-right'>");
            out.println("<li><a href='Salir'>Salir</a></li>");
            out.println("</ul>");
        }
        out.println("</div'>");
        out.println("</div'>");
        out.println("</nav>");
    }
    
    public static void writeFooter(PrintWriter out) {
        out.println("</div>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
    
}
