package bflaunchers;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;

import wbflisteners.ObservableProcess;

public class DumpLoaderLauncher extends ObservableProcess {
	
	public void launch (String path, String db, String user, String pass, boolean clean) {
		Date inicio = new Date();
		String cl="";
		if(!clean){
			cl="[IF NOT EXISTS] ";
		}
		try { 
			// Se crea la DB.  -h localhost
			String cmd = "mysql --user="+user+" --password="+pass+" -e \"CREATE DATABASE "+cl+db+";\"";
			System.out.println(cmd);
			Process p = Runtime.getRuntime().
					exec(new String[]{"/bin/bash","-c",cmd}); 
			p.waitFor();
			
			String cmd2 = "mysql -h localhost -u"+user+" -p"+pass+" "+db+" < "+path;
			// Se carga el dump. 
			System.out.println(cmd2);
			Process p2 = Runtime.getRuntime().
					exec(new String[]{"/bin/bash","-c",cmd2}); 
			
			
			// Se obtiene el stream de salida del programa 
			InputStream is = p2.getInputStream(); 
			
			// Se prepara un bufferedReader para poder leer la salida más comodamente. 
			BufferedReader br = new BufferedReader (new InputStreamReader (is)); 
			
			// Se lee la primera linea 
			String aux = br.readLine(); 
			
			// Mientras se haya leido alguna linea 
			while (aux!=null) 
			{ 
				// Se escribe la linea en pantalla 
				System.out.println (aux); 
				
				// y se lee la siguiente. 
				aux = br.readLine(); 
			} 
			p2.waitFor();
		}  
		catch (Exception e) 
		{ 
			// Excepciones si hay algún problema al arrancar el ejecutable o al leer su salida.*/
			e.printStackTrace(); 
		}		
		Date fin = new Date();
		System.out.println("DumpLoaderLauncher Time: "+ (fin.getTime() - inicio.getTime()));
		this.notifyFinished();
	}

}
