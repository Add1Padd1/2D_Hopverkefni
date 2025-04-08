package is.vinnsla;

public class ReceitData {
    private static Receit receit;

    public static Receit getReceit() {
        return receit;
    }

    public static void setReceit(Receit receit) {
        ReceitData.receit = receit;
    }
}
