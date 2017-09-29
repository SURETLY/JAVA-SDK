package network;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Session {

    private Logger log = LoggerFactory.getLogger(Session.class);

    private final String iId;
    private final String tToken;
    private final String randomId = Utils.makeId(8);

    public Session(String iId, String tToken) {
        this.iId = iId;
        this.tToken = tToken;
    }

    public String getId() {
        return iId;
    }

    public String getToken() {
        return tToken;
    }

    public String getVerify() {
        String verify = iId + "-" + randomId + "-" + Utils.md5Custom(randomId + tToken);
        log.debug("verify=" + verify);
        return verify;
    }
}
