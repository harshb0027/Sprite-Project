import java.util.Set;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.FormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import javax.security.enterprise.identitystore.PasswordHash;
import javax.ws.rs.core.Application;

/**
 * All the methods and variables in this class were generated
 */

@javax.ws.rs.ApplicationPath("webresources")
@Named
@ApplicationScoped
@BasicAuthenticationMechanismDefinition
//@FormAuthenticationMechanismDefinition(
//loginToContinue = @LoginToContinue(
//loginPage = "/login.xhtml",
//errorPage = "/error.xhtml"))


@DatabaseIdentityStoreDefinition(
        dataSourceLookup = "${'java:comp/DefaultDataSource'}",
        callerQuery = "#{'select PASSWORD from app.appuser where USERID = ?'}",
        groupsQuery = "select GROUPNAME from app.appuser where USERID = ?",
        hashAlgorithm = PasswordHash.class,
        priority = 10
)
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.mycompany.spriteharsh.resources.JavaEE8Resource.class);
        resources.add(service.SpriteFacadeREST.class);
    }
    
}


