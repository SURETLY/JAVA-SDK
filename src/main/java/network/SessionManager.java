package network;

import com.oracle.tools.packager.Log;

public class SessionManager {

    private static String iId;
    private static String tToken;
    private static String randomId = Utils.makeId(8);

    public String getId() {
        return iId;
    }

    public static void setId(String id) {
        iId = id;
    }

    public static String getToken() {
        return tToken;
    }

    public static void setToken(String token) {
        tToken = token;
    }

    public static String getVerify() {
        String verify = iId + "-" + randomId + "-" + Utils.md5Custom(randomId + tToken);
        Log.info("verify=" + verify);
        return verify;
    }
}
