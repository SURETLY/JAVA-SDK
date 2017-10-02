package network;

import com.oracle.tools.packager.Log;

public class SessionManager {

    private String id;
    private String token;
    private String randomId = Utils.makeId(8);

    public String getId() {
        return id;
    }

    public SessionManager(String id, String token) {
        this.id = id;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public String getVerify() {
        String verify = id + "-" + randomId + "-" + Utils.md5Custom(randomId + token);
        Log.info("verify=" + verify);
        return verify;
    }
}
