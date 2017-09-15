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
		commands.put("loginCommand", new LoginCommand());
		commands.put("getLoginPageCommand", new GetLoginPageCommand());
		commands.put("logoutCommand", new LogoutCommand());
		commands.put("getUserInfoPageCommand", new GetUserInfoPageCommand());
		commands.put("noCommand", new NoCommand());
		commands.put("getEditUserPageCommand", new GetEditUserPageCommand());
		commands.put("changeLocaleCommand", new ChangeLocaleCommand());
		commands.put("getSettingsPageCommand", new GetSettingsPageCommand());
		commands.put("getErrorMessagePageCommand", new GetErrorMessagePageCommand());
		commands.put("updateUserInfoCommand", new UpdateUserInfoCommand());
		commands.put("getChangePasswordPageCommand", new GetChangePasswordPageCommand());
		commands.put("changePasswordCommand", new ChangePasswordCommand());
		commands.put("confirmNewPasswordCommand", new ConfirmNewPasswordCommand());
		commands.put("getSuccessMessagePageCommand", new GetSuccessMessagePageCommand());
		commands.put("getStaffFlightsPageCommand", new GetStaffFlightsPageCommand());
		commands.put("getDispatcherFlightsPageCommand", new GetDispatcherFlightsPageCommand());
		commands.put("getDispatcherFlightInfoPageCommand", new GetDispatcherFlightInfoPageCommand());
		commands.put("getDispatcherRequestInfoPageCommand", new GetDispatcherRequestInfoPageCommand());
		commands.put("changeFlightStatusCommand", new ChangeFlightStatusCommand());
		commands.put("changeBrigadeCommand", new ChangeBrigadeCommand());
		commands.put("getDispatcherBrigadesPageCommand", new GetDispatcherBrigadesPageCommand());
		commands.put("getDispatcherRequestsPageCommand", new GetDispatcherRequestsPageCommand());
		commands.put("getStaffFlightInfoPageCommand", new GetStaffFlightInfoPageCommand());
		commands.put("deleteDispatcherRequestCommand", new DeleteDispatcherRequestCommand());
		commands.put("addDispatcherRequestCommand", new AddDispatcherRequestCommand());
		commands.put("getDispatcherEditRequestPageCommand", new GetDispatcherEditRequestPageCommand());
		commands.put("changeDispatcherRequestMessageCommand", new ChangeDispatcherRequestMessageCommand());
		commands.put("getDispatcherAddRequestPageCommand", new GetDispatcherAddRequestPageCommand());
		commands.put("getAdminNewRequestsPageCommand", new GetAdminNewRequestsPageCommand());
		commands.put("getAdminRequestInfoPageCommand", new GetAdminRequestInfoPageCommand());
		commands.put("changeRequestStatusCommand", new ChangeRequestStatusCommand());
		commands.put("getAdminStaffPageCommand", new GetAdminStaffPageCommand());
		commands.put("getAdminStaffInfoPageCommand", new GetAdminStaffInfoPageCommand());
		commands.put("deleteAdminStaffCommand", new DeleteAdminStaffCommand());
		commands.put("getAdminEditStaffPageCommand", new GetAdminEditStaffPageCommand());
		commands.put("getAdminAddStaffPageCommand", new GetAdminAddStaffPageCommand());
		commands.put("addAdminStaffCommand", new AddAdminStaffCommand());
		commands.put("editAdminStaffCommand", new EditAdminStaffCommand());
		commands.put("getDispatcherBrigadeInfoPageCommand", new GetDispatcherBrigadeInfoPageCommand());
		commands.put("getDispatcherAddBrigadePageCommand", new GetDispatcherAddBrigadePageCommand());
		commands.put("getAdminFlightsPageCommand", new GetAdminFlightsPageCommand());
		commands.put("getAdminFlightInfoPageCommand", new GetAdminFlightInfoPageCommand());
		commands.put("getAdminAddFlightPageCommand", new GetAdminAddFlightPageCommand());
		commands.put("getAdminEditFlightPageCommand", new GetAdminEditFlightPageCommand());
		commands.put("editAdminFlightCommand", new EditAdminFlightCommand());
		commands.put("addAdminFlightCommand", new AddAdminFlightCommand());
		commands.put("deleteAdminFlightCommand", new DeleteAdminFlightCommand());
		commands.put("getDispatcherChangeFlightBrigadePageCommand", new GetDispatcherChangeFlightBrigadePageCommand());
		commands.put("getDispatcherChangeFlightStatusPageCommand", new GetDispatcherChangeFlightStatusPageCommand());
		commands.put("addDispatcherBrigadeCommand", new AddDispatcherBrigadeCommand());
		commands.put("deleteDispatcherBrigadeCommand", new DeleteDispatcherBrigadeCommand());
		commands.put("getDispatcherEditBrigadePageCommand", new GetDispatcherEditBrigadePageCommand());
		commands.put("editDispatcherBrigadeCommand", new EditDispatcherBrigadeCommand());

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