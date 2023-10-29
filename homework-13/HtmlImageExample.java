import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class HtmlImageExample {
  public static void main (String args[]) throws Exception {
    String host = "smtp.gmail.com";
    String from = "ziksmmaster2022@gmail.com";
    String to = "ralitsavuntsova1999@gmail.com";
    String file = args[0];

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

    // Create the message
    Message message = new MimeMessage(session);

    // Fill its headers
    message.setSubject("Embedded Image");
    message.setFrom(new InternetAddress(from));
    message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

    // Create your new message part
    BodyPart messageBodyPart = new MimeBodyPart();

    // Set the HTML content, be sure it references the attachment
    String htmlMessage = "<html>Hi there,<br>";
    htmlMessage += String.format("See this cool pic: <img src=\"cid:image\" />");
    htmlMessage += "</html>";

    // Set the content of the body part
    messageBodyPart.setContent(htmlMessage, "text/html");

    // Create a related multi-part to combine the parts
    Multipart multipart = new MimeMultipart();

    // Add body part to multipart
    multipart.addBodyPart(messageBodyPart);

    // Create part for the image
    BodyPart imageBodyPart = new MimeBodyPart();

    // Fetch the image and associate to part
    DataSource fds = new FileDataSource(file);
    imageBodyPart.setDataHandler(new DataHandler(fds));

    // Add a header to connect to the HTML
    imageBodyPart.setHeader("Content-ID", "<image>");

    // Add part to multi-part
    multipart.addBodyPart(imageBodyPart);

    // Associate multi-part with message
    message.setContent(multipart);

    // Send message
    Transport.send(message, from, to);
  }
}
