package suny.keycloak.mypassowrdform;

import com.google.auto.service.AutoService;
import org.keycloak.authentication.Authenticator;
import org.keycloak.authentication.AuthenticatorFactory;
import org.keycloak.authentication.authenticators.browser.UsernamePasswordForm;
import org.keycloak.authentication.authenticators.browser.UsernamePasswordFormFactory;
import org.keycloak.models.KeycloakSession;

/**
 *
 */
// notice the factory class is AuthenticatorFactory!!!
@AutoService(AuthenticatorFactory.class)
public class MyUsernamePasswordFormFactory extends UsernamePasswordFormFactory {

    private static final String PROVIDER_ID = "my-username-password-form";
    private static final UsernamePasswordForm SINGLETON = new MyUsernamePasswordForm();

    @Override
    public Authenticator create(KeycloakSession session) {
        return SINGLETON;
    }

    @Override
    public String getId() {
        return PROVIDER_ID;
    }

    @Override
    public String getDisplayType() {
        return "custom username password authenticator";
    }

}
