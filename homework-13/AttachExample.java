import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class AttachExample {
  public static void main (String args[]) throws Exception {
    String host = "smtp.gmail.com";
    String from = "ziksmmaster2022@gmail.com";
    String to = "ralitsavuntsova1999@gmail.com";
    String filename = args[0];

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
    Session session = Session.getInstance(props, auth);

    Address sender = new InternetAddress(from);
    Address receiver = new InternetAddress(to);

    // Define message
    Message msg = new MimeMessage(session);
    msg.setSubject("Attachment");
    msg.setFrom(sender);
    msg.setRecipient(Message.RecipientType.TO, receiver);

    // Create the message part
    BodyPart messageBodyPart = new MimeBodyPart();

    // Fill the message
    messageBodyPart.setText("Mail Body");

    // Create a Multipart
    Multipart multipart = new MimeMultipart();

    // Add part one
    multipart.addBodyPart(messageBodyPart);

    //
    // Part two is attachment
    //

    // Create second body part
    MimeBodyPart attachmentPart = new MimeBodyPart();

    // Get the attachment
    DataSource source = new FileDataSource(filename);

    // Set the data handler to the attachment
    attachmentPart.setDataHandler(new DataHandler(source));

    // Set the filename
    attachmentPart.setFileName(filename);;

    // Add part two
    multipart.addBodyPart(attachmentPart);

    // Put parts in message
    msg.setContent(multipart);

    // Send the message
    Transport.send(msg, from, to);
  }
}
