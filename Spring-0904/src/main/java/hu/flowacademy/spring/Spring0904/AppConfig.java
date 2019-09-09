package hu.flowacademy.spring.Spring0904;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

@Configuration
public class AppConfig {
    @Bean
    @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode =
            ScopedProxyMode.TARGET_CLASS)
    public LoginService loginService() {
        return new LoginService();
    }

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_APPLICATION, proxyMode =
            ScopedProxyMode.TARGET_CLASS )
    public MyMemoService memoService() { return new MyMemoService();}

    @Bean
    @Scope("singleton")
    public ThingsService thingsService() { return new ThingsService();}
}
