# what
2 parts are in this demo  
1. how to extend the default Keycloak username password form.    
2. how to reuse the Ldap functionality to validate username/password

# extend default username password form
1. extend the class UsernamePasswordFormFactory and register Java SPI for AuthenticatorFactory.class
2. extend the class UsernamePasswordForm

# reuse Ldap model
1. see in methode suny.keycloak.mypassowrdform.MyUsernamePasswordForm#validatePassword

# how to deploy custom extensions
1. mvn clean package
2. download keycloak zip file, https://www.keycloak.org/downloads, and unzip it
3. copy the package under target/xxxYourCustomExtension.jar to yourKeycloakHome/providers 
4. run keycloakHome/bin/kc.sh/bat build
5. run kc.bat start-dev --http-port 8088 --proxy edge --hostname-strict=false --spi-theme-static-max-age=-1 --spi-theme-cache-themes=false --spi-theme-cache-templates=false --log="console,file"
6. http://localhost:8088, initial username: admin, password: admin
7. duplicate authentication browser flow in xxRealm/Authentication/browser and add your custom authenticator and bind to browser flow
![img.png]
