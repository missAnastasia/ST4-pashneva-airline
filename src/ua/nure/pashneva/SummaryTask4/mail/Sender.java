package ua.nure.pashneva.SummaryTask4.mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * The class that sends the e-mail. <br/>
 * Mail server settings, senders and recipients data are specified through the class constructor.
 *
 * @author Anastasia Pashneva
 */
class Sender {

    /**
     * E-mail address of the sender.
     */
    private String userName;

    /**
     * Password of the sender.
     */
    private String password;

    /**
     * Object of Properties class which contains mail serves settings.
     */
    private Properties serverProperties = new Properties();

    Sender(String userName, String password, Properties serverProperties) {
        this.userName = userName;
        this.password = password;
        this.serverProperties = serverProperties;
    }

    /**
     * Method for sending e-mail message from determined sender to recipient e-mail address.
     *
     * @param subject text of the subject part of e-mail message.
     * @param text text of the main part of e-mail message.
     * @param toEmail recipient e-mail address.
     * @throws MessagingException
     */
    public void send(String subject, String text, String toEmail) throws MessagingException {
        Session session = Session.getDefaultInstance(serverProperties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(userName));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        message.setSubject(subject);
        message.setText(text);

        Transport.send(message);
    }
}