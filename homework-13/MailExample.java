import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class MailExample {
  public static void main (String args[]) throws Exception {
    String host = "smtp.gmail.com";
    String from = "ziksmmaster2022@gmail.com";
    String to = "ralitsavuntsova1999@gmail.com";

    Authenticator auth = new Authenticator() {
      @Override
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(from, "gkabysclpyadfzdh");
      }
    };

    // Get system properties
    Properties props = System.getProperties();

    // Setup mail server
    props.put("mail.smtp.host", host);
    props.put("mail.smtp.port", 587);
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");

    // Get session
    Session session = Session.getDefaultInstance(props, auth);

    // Define message
    Message msg = new MimeMessage(session);

    // Set the from address
    Address sender = new InternetAddress(from);
    msg.setFrom(sender);

    // Set the to address
    Address receiver = new InternetAddress(to);
    msg.setRecipient(Message.RecipientType.TO, receiver);

    // Set the subject
    msg.setSubject("Hello");

    // Set the content
    msg.setContent("Hello again!", "text/plain");

    // Send message
    Transport.send(msg, from, to);
  }
}
