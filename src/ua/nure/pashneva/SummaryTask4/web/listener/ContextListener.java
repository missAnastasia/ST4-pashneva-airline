package ua.nure.pashneva.SummaryTask4.web.listener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import ua.nure.pashneva.SummaryTask4.mail.MailManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Context listener.
 * 
 * @author D.Kolesnikov
 * 
 */
public class ContextListener implements ServletContextListener {

	private static final Logger LOG = Logger.getLogger(ContextListener.class);

	public void contextDestroyed(ServletContextEvent event) {
		log("Servlet context destruction starts");
		// no op
		log("Servlet context destruction finished");
	}

	public void contextInitialized(ServletContextEvent event) {
		log("Servlet context initialization starts");

		ServletContext servletContext = event.getServletContext();
		initLog4J(servletContext);
		initCommandContainer();
		initLocalization(servletContext);
        initMailProperties(servletContext);
	
		log("Servlet context initialization finished");
	}

	/**
	 * Initializes log4j framework.
	 * 
	 * @param servletContext
	 */
	private void initLog4J(ServletContext servletContext) {
		log("Log4J initialization started");
		try {
			PropertyConfigurator.configure(
				servletContext.getRealPath("WEB-INF/log4j.properties"));
			LOG.debug("Log4j has been initialized");
		} catch (Exception ex) {
			log("Cannot configure Log4j");
			ex.printStackTrace();
		}		
		log("Log4J initialization finished");
	}
	

	private void initCommandContainer() {
		
		// initialize commands container
		// just load class to JVM
		try {
			Class.forName("ua.nure.pashneva.SummaryTask4.web.command.CommandContainer");
		} catch (ClassNotFoundException ex) {
			throw new IllegalStateException("Cannot initialize Command Container");
		}
	}

	private void initLocalization(ServletContext servletContext) {
        String localesFileName = servletContext.getInitParameter("locales");

        // obtain reale path on server
        String localesFileRealPath = servletContext.getRealPath(localesFileName);

        // locad descriptions
        Properties locales = new Properties();
        try {
            locales.load(new FileInputStream(localesFileRealPath));
        } catch (IOException e) {
            throw new IllegalStateException("Cannot load locales properties");
        }

        // save descriptions to servlet context
        servletContext.setAttribute("locales", locales);
	}

    private void initMailProperties(ServletContext servletContext) {
        String mailServerPropertiesFileName = servletContext.getInitParameter("mail_server_properties");
        String mailSendersPropertiesFileName = servletContext.getInitParameter("mail_senders_properties");


        String mailServerPropertiesFileRealPath = servletContext.getRealPath(mailServerPropertiesFileName);
        String mailSendersPropertiesFileRealPath = servletContext.getRealPath(mailSendersPropertiesFileName);


        Properties mailServerProperties = new Properties();
        Properties mailSendersProperties = new Properties();

        try {
            Class.forName("ua.nure.pashneva.SummaryTask4.mail.MailManager");
            Class.forName("ua.nure.pashneva.SummaryTask4.mail.Sender");

            mailServerProperties.load(new FileInputStream(mailServerPropertiesFileRealPath));
            mailSendersProperties.load(new FileInputStream(mailSendersPropertiesFileRealPath));

            MailManager.setProperties(mailServerProperties, mailSendersProperties);
        } catch (ClassNotFoundException eCl) {
            throw new IllegalStateException("Cannot initialize MailManager");
        } catch (IOException eIO) {
            throw new IllegalStateException("Cannot load mailServerProperties or mailSendersProperties properties");
        }

    }

	private void log(String msg) {
		System.out.println("[ContextListener] " + msg);
	}
}