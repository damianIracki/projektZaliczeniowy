package pl.coderslab.app;



import com.sun.mail.smtp.SMTPTransport;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Properties;

public class SendMail {
    private static final String HOST = "smtp.poczta.interia.pl";
    private static final int  PORT = 465;

    private static final String FROM = "apptestuser@interia.pl";

    private static final String password = "mamatata123";

    private static final String TO = "apptestuser2@interia.pl";

    private static final String SUBJECT = "test";

    private static final String CONTENT = "To mój peirwszy wysłąny email";


    public static void main(String[] args) {
        try{
            new SendMail().send();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


    public void send() throws MessagingException {

        Properties properties = System.getProperties();
        properties.setProperty("mail.smtps.host", HOST);

        properties.setProperty("mail.smpt.port", Integer.toString(PORT));
        properties.setProperty("mail.smtp.socketFactory.port", "465");

        Session session = Session.getInstance(properties, null);
        final MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(FROM));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(TO, false));
        msg.setSubject(SUBJECT);
        msg.setText(CONTENT);

        SMTPTransport transport =(SMTPTransport) session.getTransport("smpts");
        transport.connect(HOST, FROM, password);
        transport.sendMessage(msg, msg.getAllRecipients());
        transport.close();
    }
}
