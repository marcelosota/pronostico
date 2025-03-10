package ec.com.pronostico.utils;

import java.util.Properties;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class CorreoElectronico
{
  public static void enviarConGMail(String destinatario, String asunto, String cuerpo)
  {
    String remitente = "pronostico.deportivo.ec";
    String contrasena = "Pronostico.2018";
    
    Properties props = System.getProperties();
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.user", remitente);
    props.put("mail.smtp.clave", contrasena);
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.port", "587");
    
    Session session = Session.getDefaultInstance(props);
    MimeMessage message = new MimeMessage(session);
    
    try
    {
      message.setFrom(new InternetAddress(remitente));
      message.addRecipients(RecipientType.TO, destinatario);
      
      Multipart multipart = new MimeMultipart();
      
      MimeBodyPart htmlPart = new MimeBodyPart();
      htmlPart.setContent(cuerpo, "text/html;charset=UTF-8");
      multipart.addBodyPart(htmlPart);
      
      message.setSubject(asunto);
      message.setContent(multipart);
      Transport transport = session.getTransport("smtp");
      transport.connect("smtp.gmail.com", remitente, contrasena);
      transport.sendMessage(message, message.getAllRecipients());
      transport.close();
    }
    catch (MessagingException me) {
      me.printStackTrace();
    }
  }
}
