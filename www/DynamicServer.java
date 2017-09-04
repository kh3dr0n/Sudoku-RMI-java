import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
import java.rmi.server.RMIClassLoader;
import java.util.Properties;

/**
 * Created by Marwen on 12/25/16.
 */
public class DynamicServer {
    public static void main(String[] args) {
        try {

            if (System.getSecurityManager() == null)
                System.setSecurityManager(new RMISecurityManager());
            Registry registry = LocateRegistry.createRegistry(1099);


            System.out.println("Serveur : Construction de l'implementation ");


            //FabHelloApp fab= new FabHelloApp();

            Properties p = System.getProperties();
            String url = p.getProperty("java.rmi.server.codeBase");
            Class ClassServeur = RMIClassLoader.loadClass(url, "FabricSudoku");


            registry.rebind("Fabrique", (Remote) ClassServeur.newInstance());


            System.out.println("Fabrique lie dans le RMIregistry");


            System.out.println("Attente des invocations des clients ...");


        } catch (Exception e) {
            System.out.println("Le serveur est en panne");
        }
    }
}
