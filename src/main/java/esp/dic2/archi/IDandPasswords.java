package esp.dic2.archi;

import java.util.HashMap;

public class IDandPasswords {
    HashMap<String, String> login_pass = new HashMap<String, String>();

    public IDandPasswords() {
        login_pass.put("admin", "admin");
        login_pass.put("cbk", "cutiepie");
        login_pass.put("lamine", "BG_bf");

    }

    protected HashMap<String, String> getLoginInfo() {
        return login_pass;
    }
}
