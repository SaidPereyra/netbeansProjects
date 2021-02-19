/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Waldir
 */
@WebServlet(name="CtrlSitio", urlPatterns={"/buzon","/error","/ayuda","/mensaje","/detalle"})
public class CtrlSitio extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String url = request.getServletPath();
        String recurso = "";
        switch(url){
            case "/buzon":
                recurso = "Buzón.html";
            break;
            case "/error":
                recurso = "Error.html";
            break;
            case "/ayuda":
                recurso = "Ayuda.html";
            break;            
            case "/detalle":
                recurso = "portafolioDetalle.html";
            break;
            case "/mensaje":
                try (PrintWriter out = response.getWriter()) {

                    String nombre = request.getParameter("nombre");
                    String asunto = request.getParameter("asunto");
                    String mensaje = request.getParameter("mensaje");

                    String de = "softwareconsultingservice69@gmail.com"; //CorreoDesdeElQueSeEnviaxd
                    String clave = "SCS123456.";
                    String para = "softwareconsultingservice69@gmail.com"; //CorreoAlQueSeEnvia
                    String host = "smtp.gmail.com";




                    Properties prop = System.getProperties();

                    prop.put("mail.smtp.starttls.enable","true");
                    prop.put("mail.smtp.host", host);
                    prop.put("mail.smtp.user",de);
                    prop.put("mail.smtp.password", clave);
                    prop.put("mail.smtp.port",587);
                    prop.put("mail.smtp.auth","true");

                    prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");

                    Session sesion = Session.getDefaultInstance(prop,null);

                    MimeMessage message = new MimeMessage(sesion);

                    message.setFrom(new InternetAddress(de));

                    message.setRecipient(Message.RecipientType.TO, new 
                    InternetAddress(para));

                    message.setSubject(asunto + " - SCS - Buzón de sugerencias");
                    message.setText("Hola, mi nombre es " + nombre + "\n" + mensaje + "\nPlantilla generada automáticamente por SCS.");

                    Transport transport = sesion.getTransport("smtp");

                    transport.connect(host,de,clave);

                    transport.sendMessage(message, message.getAllRecipients());

                    transport.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
            break;

        }
        request.getRequestDispatcher("/WEB-INF/views/"+recurso).forward(request, response);
    } 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
