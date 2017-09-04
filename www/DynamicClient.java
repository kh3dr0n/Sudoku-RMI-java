import java.rmi.server.RMIClassLoader;
import java.util.Properties;
import java.util.*;
import java.lang.reflect.Constructor;
import java.rmi.RMISecurityManager;

public class DynamicClient{
	public DynamicClient(String[] args) throws Exception{
		Properties p = System.getProperties();
		String url = p.getProperty("java.rmi.server.codebase");
		Class ClasseClient = RMIClassLoader.loadClass(url,"Client");

		Constructor [] c = ClasseClient.getConstructors();
		c[0].newInstance(new Object[]{args});

		
	}
	public static void main(String[] args){
		System.setSecurityManager(new RMISecurityManager());
		try{
			DynamicClient cli = new DynamicClient(args);
		}catch(Exception e){
			System.out.println(e.toString());
		}
	}
}