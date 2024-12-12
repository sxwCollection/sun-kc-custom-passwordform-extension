package suny.keycloak.mypassowrdform;

import jakarta.ws.rs.core.MultivaluedMap;
import org.jboss.logging.Logger;
import org.keycloak.authentication.AuthenticationFlowContext;
import org.keycloak.authentication.authenticators.browser.UsernamePasswordForm;
import org.keycloak.component.ComponentModel;
import org.keycloak.models.UserCredentialModel;
import org.keycloak.models.UserModel;
import org.keycloak.storage.ldap.LDAPStorageProvider;
import org.keycloak.storage.ldap.LDAPStorageProviderFactory;

import java.util.Optional;

/**
 *
 */
public class MyUsernamePasswordForm extends UsernamePasswordForm {

    /**
     */
    @Override
    public boolean validatePassword(AuthenticationFlowContext context, UserModel user,
                                    MultivaluedMap<String, String> inputData, boolean clearUser) {

        //firstly validate user with Ldap user federation, if user not found in ldap, validate user in keycloak
        String password = inputData.getFirst("password");
        LDAPStorageProviderFactory factory = new LDAPStorageProviderFactory();
        factory.init(null);
        Optional<ComponentModel> valid = context.getRealm().getComponentsStream().filter(c -> {
            if (c.getName().contains("yourLdapConfigName")) {
                log.info("found your Ldap model");
                LDAPStorageProvider ldapStorageProvider = factory.create(context.getSession(), c);
                return ldapStorageProvider.isValid(context.getRealm(), user, UserCredentialModel.password(password));
            }
            return false;
        }).findAny();
        factory.close();
        return valid.isPresent() || super.validatePassword(context, user, inputData, clearUser);
    }
}
