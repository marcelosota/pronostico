package ec.com.pronostico.servicio.impl;

import javax.ejb.Stateless;
import ec.com.pronostico.servicio.EnvioCorreoServicio;





























@Stateless(name="EnvioCorreoServicio")
public class EnvioCorreoImpl
  implements EnvioCorreoServicio
{
  static final String FROM = "pollafutbolera@gmail.com";
  static final String FROMNAME = "Polla Mundialista";
  static final String TO = "candrade@mipro.gob.ec";
  static final String SMTP_USERNAME = "christian19810414@gmail.com";
  static final String SMTP_PASSWORD = "Chr1$t1@n";
  static final String CONFIGSET = "ConfigSet";
  static final String HOST = "smtp.gmail.com";
  static final int PORT = 587;
  static final String SUBJECT = "Mensaje de Polla Mundialista";
  static final String BODY = String.join(
    System.getProperty("line.separator"), new CharSequence[] {
    "<h1>Mensaje Polla mundialista</h1>", 
    "<p>Mail de Prueba  ", 
    "<a href='https://github.com/javaee/javamail'>Javamail Package</a>", 
    " for <a href='https://www.java.com'>Java</a>." });
  
  /* Error */
  public void enviarCorreo(String from, String to, String asunto, String mensaje, String tipo, String textoPlano, String html)
    throws java.io.UnsupportedEncodingException, javax.mail.MessagingException
  {
    // Byte code:
    //   0: invokestatic 80	java/lang/System:getProperties	()Ljava/util/Properties;
    //   3: astore 8
    //   5: aload 8
    //   7: ldc 84
    //   9: ldc 86
    //   11: invokevirtual 88	java/util/Properties:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   14: pop
    //   15: aload 8
    //   17: ldc 94
    //   19: sipush 587
    //   22: invokestatic 96	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   25: invokevirtual 88	java/util/Properties:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   28: pop
    //   29: aload 8
    //   31: ldc 102
    //   33: ldc 104
    //   35: invokevirtual 88	java/util/Properties:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   38: pop
    //   39: aload 8
    //   41: ldc 106
    //   43: ldc 104
    //   45: invokevirtual 88	java/util/Properties:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   48: pop
    //   49: aload 8
    //   51: invokestatic 108	javax/mail/Session:getDefaultInstance	(Ljava/util/Properties;)Ljavax/mail/Session;
    //   54: astore 9
    //   56: new 114	javax/mail/internet/MimeMessage
    //   59: dup
    //   60: aload 9
    //   62: invokespecial 116	javax/mail/internet/MimeMessage:<init>	(Ljavax/mail/Session;)V
    //   65: astore 10
    //   67: aload 10
    //   69: new 119	javax/mail/internet/InternetAddress
    //   72: dup
    //   73: aload_1
    //   74: ldc 13
    //   76: invokespecial 121	javax/mail/internet/InternetAddress:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   79: invokevirtual 124	javax/mail/internet/MimeMessage:setFrom	(Ljavax/mail/Address;)V
    //   82: aload 10
    //   84: getstatic 128	javax/mail/Message$RecipientType:TO	Ljavax/mail/Message$RecipientType;
    //   87: new 119	javax/mail/internet/InternetAddress
    //   90: dup
    //   91: aload_2
    //   92: invokespecial 133	javax/mail/internet/InternetAddress:<init>	(Ljava/lang/String;)V
    //   95: invokevirtual 136	javax/mail/internet/MimeMessage:setRecipient	(Ljavax/mail/Message$RecipientType;Ljavax/mail/Address;)V
    //   98: aload 10
    //   100: aload_3
    //   101: invokevirtual 140	javax/mail/internet/MimeMessage:setSubject	(Ljava/lang/String;)V
    //   104: aload 10
    //   106: aload 7
    //   108: ldc -113
    //   110: invokevirtual 145	javax/mail/internet/MimeMessage:setContent	(Ljava/lang/Object;Ljava/lang/String;)V
    //   113: aload 10
    //   115: ldc -107
    //   117: ldc 25
    //   119: invokevirtual 151	javax/mail/internet/MimeMessage:setHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   122: aload 9
    //   124: invokevirtual 154	javax/mail/Session:getTransport	()Ljavax/mail/Transport;
    //   127: astore 11
    //   129: getstatic 158	java/lang/System:out	Ljava/io/PrintStream;
    //   132: ldc -94
    //   134: invokevirtual 164	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   137: aload 11
    //   139: ldc 28
    //   141: ldc 19
    //   143: ldc 22
    //   145: invokevirtual 169	javax/mail/Transport:connect	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   148: aload 11
    //   150: aload 10
    //   152: aload 10
    //   154: invokevirtual 175	javax/mail/internet/MimeMessage:getAllRecipients	()[Ljavax/mail/Address;
    //   157: invokevirtual 179	javax/mail/Transport:sendMessage	(Ljavax/mail/Message;[Ljavax/mail/Address;)V
    //   160: getstatic 158	java/lang/System:out	Ljava/io/PrintStream;
    //   163: ldc -73
    //   165: invokevirtual 164	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   168: goto +57 -> 225
    //   171: astore 12
    //   173: getstatic 158	java/lang/System:out	Ljava/io/PrintStream;
    //   176: ldc -71
    //   178: invokevirtual 164	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   181: getstatic 158	java/lang/System:out	Ljava/io/PrintStream;
    //   184: new 187	java/lang/StringBuilder
    //   187: dup
    //   188: ldc -67
    //   190: invokespecial 191	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   193: aload 12
    //   195: invokevirtual 192	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   198: invokevirtual 198	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   201: invokevirtual 202	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   204: invokevirtual 164	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   207: aload 11
    //   209: invokevirtual 205	javax/mail/Transport:close	()V
    //   212: goto +18 -> 230
    //   215: astore 13
    //   217: aload 11
    //   219: invokevirtual 205	javax/mail/Transport:close	()V
    //   222: aload 13
    //   224: athrow
    //   225: aload 11
    //   227: invokevirtual 205	javax/mail/Transport:close	()V
    //   230: return
    // Line number table:
    //   Java source line #58	-> byte code offset #0
    //   Java source line #59	-> byte code offset #5
    //   Java source line #60	-> byte code offset #15
    //   Java source line #61	-> byte code offset #29
    //   Java source line #62	-> byte code offset #39
    //   Java source line #65	-> byte code offset #49
    //   Java source line #68	-> byte code offset #56
    //   Java source line #69	-> byte code offset #67
    //   Java source line #70	-> byte code offset #82
    //   Java source line #71	-> byte code offset #98
    //   Java source line #72	-> byte code offset #104
    //   Java source line #76	-> byte code offset #113
    //   Java source line #79	-> byte code offset #122
    //   Java source line #84	-> byte code offset #129
    //   Java source line #87	-> byte code offset #137
    //   Java source line #90	-> byte code offset #148
    //   Java source line #91	-> byte code offset #160
    //   Java source line #92	-> byte code offset #168
    //   Java source line #93	-> byte code offset #171
    //   Java source line #94	-> byte code offset #173
    //   Java source line #95	-> byte code offset #181
    //   Java source line #100	-> byte code offset #207
    //   Java source line #98	-> byte code offset #215
    //   Java source line #100	-> byte code offset #217
    //   Java source line #101	-> byte code offset #222
    //   Java source line #100	-> byte code offset #225
    //   Java source line #102	-> byte code offset #230
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	231	0	this	EnvioCorreoImpl
    //   0	231	1	from	String
    //   0	231	2	to	String
    //   0	231	3	asunto	String
    //   0	231	4	mensaje	String
    //   0	231	5	tipo	String
    //   0	231	6	textoPlano	String
    //   0	231	7	html	String
    //   3	47	8	props	java.util.Properties
    //   54	69	9	session	javax.mail.Session
    //   65	88	10	msg	javax.mail.internet.MimeMessage
    //   127	99	11	transport	javax.mail.Transport
    //   171	23	12	ex	Exception
    //   215	8	13	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   129	168	171	java/lang/Exception
    //   129	207	215	finally
  }
}
