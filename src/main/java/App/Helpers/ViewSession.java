package App.Helpers;

public final class ViewSession {
    private static int id;
    private static ViewSession instance;

    public static ViewSession getInstance() {
        if(instance == null) {
            instance = new ViewSession();
        }
        return instance;
    }

    public void cleanViewSession() {
        id = -1;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int idx) {
        id = idx;
    }
}
