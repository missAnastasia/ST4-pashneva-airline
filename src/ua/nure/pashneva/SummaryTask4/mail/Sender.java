package ua.nure.pashneva.SummaryTask4.mail;

import org.apache.log4j.Logger;

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

    private static final Logger LOG = Logger.getLogger(Sender.class);

    public Sender(String userName, String password, Properties serverProperties) {
        this.userName = userName;
        LOG.trace("UserName -->" + userName);
        this.password = password;
        LOG.trace("Password -->" + password);
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

        LOG.debug("Creating message");
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(userName));
        LOG.trace("Sending from -->" + userName);

        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        LOG.trace("Sending to -->" + toEmail);

        message.setSubject(subject);
        LOG.trace("Message subject -->" + subject);

        message.setText(text);
        LOG.trace("Message text -->" + text);

        Transport.send(message);
        LOG.debug("Message sent");
    }
}