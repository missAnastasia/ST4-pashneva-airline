package ua.nure.pashneva.SummaryTask4.mail;

import ua.nure.pashneva.SummaryTask4.exception.AppException;
import ua.nure.pashneva.SummaryTask4.web.util.Path;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Class for creating e-mail messages and managing of sending this messages.
 *
 * @author Anastasia Pashneva
 */
public class MailManager {

    /**
     * Object of Properties class which contains mail server settings.
     */
    private static Properties serverProperties = new Properties();

    /**
     * Object of Properties class which contains senders e-mail addresses and passwords.
     */
    private static Properties sendersProperties = new Properties();

    private MailManager() {
    }

    /**
     * Method for setting server and senders properties.
     * @param serverProperties object of Properties class with mail server settings.
     * @param sendersProperties object of Properties class with senders settings.
     */
    public static void setProperties(Properties serverProperties, Properties sendersProperties) {
        MailManager.serverProperties = serverProperties;
        MailManager.sendersProperties = sendersProperties;
    }

    /**
     * Method for creating and sending e-mail for confirmation of registration user in system.
     *
     * @param recipient e-mail address of user who is registering in system.
     *                  This parameter is included in the confirmation link from the message text.
     * @param request object of HttpServletRequest through which localization resources can be accessed
     *                for generating a registration confirmation message for recipient.
     * @return true - e-mail message sent to recipient, otherwise - throws AppException instance.
     * @throws AppException with exception message if MessagingException object was caught in try section.
     */
    public static boolean sendNewPasswordConfirmationMail(String recipient, HttpServletRequest request) throws AppException {
        String senderMail = sendersProperties.getProperty("sender_username");
        String senderPassword = sendersProperties.getProperty("sender_password");

        Sender sender = new Sender(senderMail, senderPassword, serverProperties);

        String messageSubject = ResourceBundle.getBundle("resources", request.getLocale())
                .getString("mail.new_password.confirmation.subject");
        String messageText = ResourceBundle.getBundle("resources", request.getLocale())
                .getString("mail.new_password.confirmation.text") + System.lineSeparator() +
                Path.HOST + Path.COMMAND_CONFIRM_PASSWORD + recipient;

        try {
            sender.send(messageSubject, messageText, recipient);
            return true;
        } catch (MessagingException e) {
           throw new AppException(e.getMessage());
        }
    }
}
