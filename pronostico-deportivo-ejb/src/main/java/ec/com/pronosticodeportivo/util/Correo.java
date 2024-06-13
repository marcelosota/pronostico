package ec.com.pronosticodeportivo.util;

import java.io.File;
import java.util.Map;
import java.util.Properties;

import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
import jakarta.mail.Authenticator;
import jakarta.mail.BodyPart;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.Part;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

public class Correo {

	static String host = "smtp.gmail.com";
	static String puerto = "587";
	static String usuarioCorreo = "pronostico.deportivo.ec";
	static String passwordCorreo = "pjdiehvwequleght";

	public void enviarCorreo(Mensaje mensaje) {

		final String username = usuarioCorreo;
		// provide Mailtrap's password
		final String password = passwordCorreo;

		/*
		 * Properties props = new Properties(); props.put("mail.smtp.auth", "true");
		 * props.put("mail.smtp.starttls.enable", "true"); props.put("mail.smtp.host",
		 * host); props.put("mail.smtp.port", puerto);
		 * 
		 * // create the Session object Session session = Session.getInstance(props, new
		 * Authenticator() {
		 * 
		 * @Override protected PasswordAuthentication getPasswordAuthentication() {
		 * return new PasswordAuthentication(username, password); } });
		 */
		Properties props = System.getProperties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", username);
		props.put("mail.smtp.clave", password);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.port", "587");

		Session session = Session.getDefaultInstance(props);

		try {
			// create a MimeMessage object
			Message message = new MimeMessage(session);
			// set From email field
			message.setFrom(new InternetAddress(mensaje.getRemitente()));

			// set To email field

			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(String.join(",", mensaje.getDestinatarios())));

			// set email subject field
			message.setSubject(mensaje.getAsunto());
			// set the content of the email message

			Multipart multipart = new MimeMultipart();
			MimeBodyPart htmlPart = new MimeBodyPart();

			if (mensaje.isTieneAdjunto()) {
				message = mensajeConAdjunto(mensaje, message);
			} else {
				htmlPart.setContent(mensaje.getTextoMensaje(), "text/html;charset=UTF-8");
				multipart.addBodyPart(htmlPart);
				message.setContent(multipart);

			}

			// message.setText(mensaje.getTextoMensaje());
			// send the email message
			Transport transport = session.getTransport("smtp");
			transport.connect("smtp.gmail.com", usuarioCorreo, passwordCorreo);
			transport.sendMessage(message, message.getAllRecipients());

//			Transport.send(message);
			transport.close();
			System.out.println("Email Message Sent Successfully");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	private Message mensajeConAdjunto(Mensaje mensaje, Message msj) {

		try {

			Multipart multipart = new MimeMultipart();

			MimeBodyPart textPart = new MimeBodyPart();
			textPart.setContent(mensaje.getTextoMensaje(), "text/html;charset=UTF-8");
			// BodyPart textPart = new MimeBodyPart();
			// textPart.setText(message.getText() != null ? message.getText() : "");

			multipart.addBodyPart(textPart);

			for (Map.Entry<String, File> archivo : mensaje.getListaAdjuntos().entrySet()) {
				BodyPart attachmentPart = new MimeBodyPart();
				DataSource source = new FileDataSource(archivo.getValue());

				attachmentPart.setDataHandler(new DataHandler(source));
				// attachmentPart.setDataHandler(new DataHandler(new URL(message.getUrl())));

				attachmentPart.setFileName(archivo.getValue().getName());

				attachmentPart.setDisposition(Part.ATTACHMENT);

				// attachmentPart.setHeader("Content-Type",
				// TipoArchivo.obtenerTipoArchivo(archivo.getKey()));
				// attachmentPart.setHeader("Content-Type", "application/pdf");

				multipart.addBodyPart(attachmentPart);
			}

			msj.setContent(multipart);
			return msj;

		} catch (MessagingException e) {
			System.err.println("MENSAJE MAL REPORTE");
			e.printStackTrace();
		}
		return null;
	}
}
