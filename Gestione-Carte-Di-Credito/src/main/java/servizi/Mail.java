package servizi;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {
    public static void inviaEmail(String email, String passwordC) throws AddressException, MessagingException {
        String mittente = "clara.dipiazza7@gmail.com";
        String destinatario = email;
        String host = "smtp.gmail.com";
        final String username = "clara.dipiazza7@gmail.com";
        final String password = "xinl ofrb rxcu fgsy";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        
     // Aggiungi il logging per verificare se passwordC è stato passato correttamente
        System.out.println("Valore di passwordC prima della chiamata a inviaEmail(): " + passwordC);
        
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(mittente));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
        message.setSubject("Ecco la tua nuova password");
        message.setText("Password:" + passwordC + "\n");
        Transport.send(message);
    }
}