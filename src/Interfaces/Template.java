package Interfaces;

import java.io.PrintWriter;

public class Template {
    
    public static void writeHeader(PrintWriter out, String title, boolean loggedIn) {
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>" + title + "</title>");
        out.println("<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css'>");
        out.println("<link rel='stylesheet' href='main.css'>");
        out.println("<script src='https://code.jquery.com/jquery-2.1.4.min.js'></script>");
        out.println("<script src='http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js'></script>");
        out.println("</head>");
        out.println("<body>");
        writeNavBar(out, loggedIn);
        out.println("<div class='main-container'>");
    }
    
    public static void writeNavBar(PrintWriter out, boolean loggedIn) {
        out.println("<nav class='navbar navbar-default'>");
        out.println("</nav>");
    }
    
    public static void writeFooter(PrintWriter out) {
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
    
}
