package cours.uahb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class Utils {

    private  static String token;

    public String getConnectedUser(){
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        return authentication.getName();
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        Utils.token = token;
    }
}
