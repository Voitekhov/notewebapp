package net.ru.voitekhov.notewebapp.util;

public class SecurityUtil {

    private static int id = 100000;

    public SecurityUtil() {
    }

    public static void setAuthUserId(int id) {
        SecurityUtil.id = id;
    }

    public static int authUserId() {
        return id;
    }
}
