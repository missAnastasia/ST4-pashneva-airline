package ua.nure.pashneva.SummaryTask4.web.command;

import org.apache.log4j.Logger;

import java.util.Map;
import java.util.TreeMap;

/**
 * Holder for all commands.<br/>
 * 
 * @author D.Kolesnikov
 * 
 */
public class CommandContainer {
	
	private static final Logger LOG = Logger.getLogger(CommandContainer.class);
	
	private static Map<String, Command> commands = new TreeMap<String, Command>();
	
	static {
		// common commands
		commands.put("loginCommand", new LoginCommand());
		commands.put("getLoginPageCommand", new GetLoginPageCommand());
		commands.put("logoutCommand", new LogoutCommand());
		commands.put("getUserInfoPageCommand", new GetUserInfoPageCommand());
		commands.put("noCommand", new NoCommand());
		commands.put("getHomePageCommand", new GetHomePageCommand());
		commands.put("getEditUserPageCommand", new GetEditUserPageCommand());
		commands.put("registerClientCommand", new RegisterClientCommand());
		commands.put("changeLocaleCommand", new ChangeLocaleCommand());
		commands.put("getSettingsPageCommand", new GetSettingsPageCommand());
		commands.put("getRegisterClientPageCommand", new GetRegisterClientPageCommand());
		commands.put("getContactsPageCommand", new GetContactsPageCommand());
		commands.put("getErrorMessagePageCommand", new GetErrorMessagePageCommand());
		commands.put("getProductsPageCommand", new GetProductsPageCommand());
		commands.put("updateUserInfoCommand", new UpdateUserInfoCommand());
		commands.put("getChangeUserDataPageCommand", new GetChangeUserDataPageCommand());
		commands.put("getChangePasswordPageCommand", new GetChangePasswordPageCommand());
		commands.put("changePasswordCommand", new ChangePasswordCommand());
		commands.put("confirmRegistrationCommand", new ConfirmRegistrationCommand());
		commands.put("getSuccessMessagePageCommand", new GetSuccessMessagePageCommand());
		commands.put("getStaffFlightsPageCommand", new GetStaffFlightsPageCommand());
		commands.put("getDispatcherFlightsPageCommand", new GetDispatcherFlightsPageCommand());
		commands.put("getDispatcherFlightInfoPageCommand", new GetDispatcherFlightInfoPageCommand());
		commands.put("changeFlightStatusCommand", new ChangeFlightStatusCommand());
		commands.put("changeBrigadeCommand", new ChangeBrigadeCommand());
		commands.put("getDispatcherBrigadesPageCommand", new GetDispatcherBrigadesPageCommand());

		// client commands
		commands.put("listMenu", new ListMenuCommand());
		
		// admin commands
		commands.put("listOrders", new ListOrdersCommand());
		commands.put("changeUserStatusCommand", new ChangeUserStatusCommand());
		commands.put("getUserAccountsPageCommand", new GetUserAccountsPageCommand());

		
		LOG.debug("Command container was successfully initialized");
		LOG.trace("Number of commands --> " + commands.size());
	}

	/**
	 * Returns command object with the given name.
	 * 
	 * @param commandName
	 *            Name of the command.
	 * @return Command object.
	 */
	public static Command get(String commandName) {
		if (commandName == null || !commands.containsKey(commandName)) {
			LOG.trace("Command not found, name --> " + commandName);
			return commands.get("noCommand"); 
		}
		
		return commands.get(commandName);
	}
	
}