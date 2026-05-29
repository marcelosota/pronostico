package ec.com.pronosticodeportivo.util;

import java.util.Properties;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;


public class CorreoElectronico
{
  public static void enviarConGMail(String destinatario, String asunto, String cuerpo)
  {
    final String remitente = "pronostico.deportivo.ec@gmail.com";
    String contrasena = "Pronostico.2018";
    
    //SE setea la contraseña de aplicación
    final String contrasenaApp = "ziyvoojczyruadrz";
    
    
    Properties props = System.getProperties();
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");    
    props.put("mail.smtp.ssl.protocols", "TLSv1.2");
    
//    Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
//        @Override
//        protected PasswordAuthentication getPasswordAuthentication() {
//            return new PasswordAuthentication(remitente, contrasena);
//        }
//    });
    
    Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication("pronostico.deportivo.ec@gmail.com", "ziyvoojczyruadrz"); 
        }
    });
    
    try
    {
    	MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(remitente));
        message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
        message.setSubject(asunto);

        // Estructura del contenido HTML
        Multipart multipart = new MimeMultipart();
        MimeBodyPart htmlPart = new MimeBodyPart();
        htmlPart.setContent(cuerpo, "text/html;charset=UTF-8");
        multipart.addBodyPart(htmlPart);
        message.setContent(multipart);

        // Envío directo simplificado usando el transporte de la sesión autenticada
        Transport.send(message);
        System.out.println("Correo enviado exitosamente.");
	} catch (MessagingException me) {
		me.printStackTrace();
	}
}
}
