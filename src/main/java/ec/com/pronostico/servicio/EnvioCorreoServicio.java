package ec.com.pronostico.servicio;

import java.io.UnsupportedEncodingException;
import javax.ejb.Local;
import javax.mail.MessagingException;

@Local
public abstract interface EnvioCorreoServicio
{
  public abstract void enviarCorreo(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7)
    throws UnsupportedEncodingException, MessagingException;
}
