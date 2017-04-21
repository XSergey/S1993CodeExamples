package com.vermilionsynergy.magadiskileton;
import java.util.HashMap;
public class ApiConfigList {

    private HashMap<String, String[]> baseResource = new HashMap<String, String[]>() {
        {
            put("CustomerAccount", new String[]{"/account/info", ":email"});
            put("CustomerCart", new String[]{"/account/cart", ":email"});
            put("CustomerSignIn", new String[]{"/auth/customer/token", ":email", ":password"});
            put("CustomerSignUp", new String[]{"/account/create", ":customerId"});
        }
    };

    public ApiConfigList() {/*...*/}
    /**
     * Get Resource parameters by alias
     * @param String alias
     * @return String baseResource
     * */
    public String[] getResourceByAlias(String alias){
        return this.baseResource.get(alias);
    }
}
