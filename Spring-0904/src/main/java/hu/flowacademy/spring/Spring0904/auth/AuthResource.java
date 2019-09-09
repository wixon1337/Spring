package hu.flowacademy.spring.Spring0904.auth;

import hu.flowacademy.spring.Spring0904.Memory;
import hu.flowacademy.spring.Spring0904.WebComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class AuthResource {

    private static final String USERNAME = "feri";
    private static final String PASSWORD = "sor";

    @Autowired
    private WebComponent webComponent;

    /*
    @Autowired
    public void setWebComponent(WebComponent webComponent) { // setter injektálás
        this.webComponent = webComponent;
    }*/

    /*    private final WebComponent webComponent; // injektálás konstruktorban
    public AuthResource(WebComponent webComponent) {
        this.webComponent = webComponent;
    }*/

/*    @PostMapping("/api/auth") // megnéztük evvel az összes header kulcs-párt és "rájöttünk" hogy authorizationnel jön a usern/passw
    public String authenticate(HttpServletRequest request) {
        request.getHeaderNames().asIterator().forEachRemaining(name -> {
            System.out.println(name + " = " + request.getHeader(name));
        });
        return null;
    }*/

    @PostMapping("/api/auth")
    public String authenticate(@RequestHeader("authorization") String encodedAuth) {
        encodedAuth = encodedAuth.replaceAll("Basic ", "");
        String decodeRaw = new String(Base64.getDecoder().decode(encodedAuth));
        if (decodeRaw.contains(":")) {
            String[] usernamePasswordArr = decodeRaw.split(":");

            if (usernamePasswordArr.length == 2 &&
                    (Memory.loginCredentials.containsKey(usernamePasswordArr[0]) && Memory.loginCredentials.get(usernamePasswordArr[0]).equals(usernamePasswordArr[1]))) {
                String uuid =  UUID.randomUUID().toString();
                webComponent.add(uuid);
                return uuid;
            }
        }
        return null;
    }

/*    @PostMapping("/api/auth")
    public String authenticate(@RequestHeader("authorization") String encodedAuth) {
        encodedAuth = encodedAuth.replaceAll("Basic ", "");
        String decodeRaw = new String(Base64.getDecoder().decode(encodedAuth));

        if (decodeRaw.contains(":")) {
            String[] usernamePasswordArr = decodeRaw.split(":");
            if (usernamePasswordArr.length == 2 &&
                    (USERNAME.equals(usernamePasswordArr[0]) && PASSWORD.equals(usernamePasswordArr[1]))) {
                String uuid =  UUID.randomUUID().toString();
                webComponent.add(uuid);
                return uuid;
            }
        }
        return null;
    }*/

    @GetMapping("/api/secure")
    public ResponseEntity<String> getSecureData(@RequestHeader("my-auth-id") String uuid) {
        if (webComponent.hasUUID(uuid)) {
            return ResponseEntity.ok("i love my car");
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
    }

    @PostMapping("/api/register")
    public Map<String,String> register(@RequestBody LoginData loginData) {
        Memory.loginCredentials.put(loginData.getUsername(), loginData.getPassword());
        return Memory.loginCredentials;
    }

}
