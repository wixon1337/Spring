package hu.flowacademy.feri2.Resource;


import hu.flowacademy.feri2.Model.User;
import hu.flowacademy.feri2.Repository.SecurityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;

    @Autowired
    private SecurityRepository securityRepository;

    @GetMapping("/")
    public String getUser(Model model, OAuth2AuthenticationToken authenticationToken) {
        OAuth2AuthorizedClient client = authorizedClientService.loadAuthorizedClient(
                authenticationToken.getAuthorizedClientRegistrationId(), authenticationToken.getName()
        );

        String userInfoEndpointUri = client.getClientRegistration()
                .getProviderDetails().getUserInfoEndpoint().getUri();

        if (!StringUtils.isEmpty(userInfoEndpointUri)) {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + client.getAccessToken()
                    .getTokenValue());
            HttpEntity entity = new HttpEntity("", headers);
            ResponseEntity<Map> response = restTemplate
                    .exchange(userInfoEndpointUri, HttpMethod.GET, entity, Map.class);
            Map userAttributes = response.getBody();
            model.addAttribute("name", userAttributes.get("name"));
            model.addAttribute("email", userAttributes.get("email"));
            model.addAttribute(userAttributes);

            if (securityRepository.findByName((String) userAttributes.get("name")) == null) {
                this.securityRepository.save(new User(
                        (String) userAttributes.get("name"),
                        (String) userAttributes.get("email"),
                        (String) userAttributes.get("picture"),
                        (String) userAttributes.get("sub"),
                        (String) userAttributes.get("given_name"),
                        (String) userAttributes.get("family_name"),
                        (boolean) userAttributes.get("email_verified"),
                        (String) userAttributes.get("locale")));

                if (Files.notExists(Paths.get("/home/vilmos/Flow/Spring/0923-feri2/"+ securityRepository.findByName((String) userAttributes.get("name")).getId() +".jpg"))){
                    try(InputStream in = new URL((String) userAttributes.get("picture")).openStream()){
                        Files.copy(in, Paths.get("/home/vilmos/Flow/Spring/0923-feri2/"+ securityRepository.findByName((String) userAttributes.get("name")).getId() +".jpg"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        System.out.println("DONE");
                    }
                }
            }
        }
        return model.toString();
    }
}
