import java.io.*;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class ReplyExample {
  public static void main (String args[]) throws Exception {
    String host = "pop.gmail.com";
    String sendHost = "smtp.gmail.com";
    String username = "ziksmmaster2022@gmail.com";
    String password = "gkabysclpyadfzdh";

    Authenticator auth = new Authenticator() {
      @Override
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
      }
    };

    // Create empty properties
    Properties props = System.getProperties();
    props.put("mail.smtp.host", sendHost);
    props.put("mail.smtp.port", 587);
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");

    props.put("mail.pop3.host", host);
    props.put("mail.pop3.port", 995);
    props.put("mail.pop3.auth", "true");
    props.put("mail.pop3.starttls.enable", "true");
    props.put("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    props.put("mail.pop3.socketFactory.fallback", "false");

    // Get session
    Session session = Session.getDefaultInstance(props, auth);

    // Get the store
    Store store = session.getStore("pop3");
    store.connect(host, username, password);

    // Get folder
    Folder folder = store.getFolder("INBOX");
    folder.open(Folder.READ_ONLY);

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    // Get directory
    Message message[] = folder.getMessages();
    int messageLength = message.length;

    for (int i = 0; i < messageLength; i++) {
      // Display from field and subject
      System.out.println(i + ": " + message[i].getFrom()[0] + "\t" + message[i].getSubject());

      System.out.println("Do you want to reply to the message? [YES to reply; QUIT to end]");

      String line = reader.readLine();
      if ("YES".equals(line)) {
        // Create a reply message
        Message reply = message[i].reply(false);

        // Set the from field
        Address sender = new InternetAddress(username);
        reply.setFrom(sender);

        // Create the reply content, copying over the original text
        reply.setSubject(message[i].getSubject());

        // Set the content
        String messageText = getTextFromMessage(message[i]);
        System.out.println(messageText);

        reply.setText(messageText);

        // Send the message
        reply.setRecipient(Message.RecipientType.TO, message[i].getFrom()[0]);
        
        Transport.send(reply);
      } else if ("QUIT".equals(line)) {
        break;
      }
    }

    // Close connection
    folder.close(false);
    store.close();
  }

  private static String getTextFromMessage(Message message) throws MessagingException, IOException {
    if (message.isMimeType("text/plain")) {
      return message.getContent().toString();
    }
    if (message.isMimeType("multipart/*")) {
      MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
      return getTextFromMimeMultipart(mimeMultipart);
    }

    return "";
  }

  private static String getTextFromMimeMultipart(MimeMultipart mimeMultipart)  throws MessagingException, IOException{
    String result = "";
    for (int i = 0; i < mimeMultipart.getCount(); i++) {
      BodyPart bodyPart = mimeMultipart.getBodyPart(i);
      if (bodyPart.isMimeType("text/plain")) {
        return result + "\n" + bodyPart.getContent(); // without return, same text appears twice in my tests
      }
      result += "\n";
    }
    return result;
  }
}
