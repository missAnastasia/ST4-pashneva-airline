package ua.nure.pashneva.SummaryTask4.web.util;

public class Path {

    public static final String HOST = "localhost:8080";

    // pages
    public static final String PAGE_LOGIN = "/loginView.jsp";
    public static final String PAGE_REGISTER_CLIENT = "/registerClientView.jsp";
    public static final String PAGE_CONDITIONS = "/WEB-INF/jsp/client/conditionsView.jsp";
    public static final String PAGE_EDIT_USER = "/WEB-INF/jsp/admin/editUserView.jsp";
    public static final String PAGE_ERROR_PAGE = "/WEB-INF/jsp/errorMessageView.jsp";
    public static final String PAGE_SUCCESS_PAGE = "/WEB-INF/jsp/successMessageView.jsp";
    public static final String PAGE_LIST_MENU = "/WEB-INF/jsp/client/list_menu.jsp";
    public static final String PAGE_LIST_ORDERS = "/WEB-INF/jsp/admin/list_orders.jsp";
    public static final String PAGE_ADMIN_USER_ACCOUNTS = "/WEB-INF/jsp/admin/userAccountsView.jsp";
    public static final String PAGE_USER_INFO = "/WEB-INF/jsp/userInfoView.jsp";
    public static final String PAGE_CHANGE_USER_DATA = "/WEB-INF/jsp/changeUserDataView.jsp";
    public static final String PAGE_CHANGE_PASSWORD = "/WEB-INF/jsp/changePasswordView.jsp";
    public static final String PAGE_CHANGE_LOCALE = "/WEB-INF/jsp/changeLocale.jsp";
    public static final String PAGE_HOME = "/homeView.jsp";
    public static final String PAGE_CONTACTS = "/contactsView.jsp";
    public static final String PAGE_ABOUT_US = "/aboutView.jsp";
    public static final String PAGE_SETTINGS = "/settingsView.jsp";
    public static final String PAGE_PRODUCTS = "/WEB-INF/jsp/client/productsView.jsp";

    // commands
    public static final String COMMAND = "/controller?command=";
    public static final String COMMAND_CONFIRM_REGISTRATION = "/controller?command=confirmRegistrationCommand&login=";
    public static final String COMMAND_HOME = "/controller?command=getHomePageCommand";
    public static final String COMMAND_LOGIN = "/controller?command=loginCommand";
    public static final String COMMAND_USER_INFO = "/redirect?command=getUserInfoPageCommand";
    public static final String COMMAND_CONDITIONS = "/redirect?command=getConditionsPageCommand";
    public static final String COMMAND_PRODUCTS = "/controller?command=getProductsPageCommand";
    public static final String COMMAND_ADMIN_USER_ACCOUNTS = "/redirect?command=getUserAccountsPageCommand";
    /*public static final String COMMAND_HOME = "controller?command=getHomePageCommand";*/

    public static final String COMMAND_MESSAGE_SUCCESS = "/controller?command=getSuccessMessagePageCommand&message=";
    public static final String COMMAND_MESSAGE_ERROR = "/controller?command=getErrorMessagePageCommand&message=";

    private Path() {
    }
    /*public static final String COMMAND_LIST_ORDERS = "/controller?command=listOrders";
    public static final String COMMAND_LIST_MENU = "/controller?command=listMenu";*/
}
