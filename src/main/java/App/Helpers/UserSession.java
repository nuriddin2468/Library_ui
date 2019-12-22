package App.Helpers;
// singleton design pattern
public final class UserSession {

    private static UserSession instance;

    private static String username;
    private static String name;
    private static int role;

    public static String getName() {
        return name;
    }

    public static String getUsername() {
        return username;
    }

    public static int getRole() {
        return role;
    }

    public static void setName(String l_name) {
        name = l_name;
    }

    public static void setRole(int l_role) {
        role = l_role;
    }

    public static void setUsername(String l_username) {
        username = l_username;
    }

    private UserSession(){
    }

    public static UserSession getInstance() {
        if(instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public static void cleanUserSession() {
        username = null;// or null
        name = null;// or null
        role = 0;
    }

}
