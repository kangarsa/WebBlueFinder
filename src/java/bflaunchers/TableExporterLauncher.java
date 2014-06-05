package bflaunchers;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import wbflisteners.ObservableProcess;

public class TableExporterLauncher extends ObservableProcess {
	String userName;
	String password;
	String databaseFrom;
	String databaseTo;
	boolean overwrite;
	ArrayList<String> fromTables;
	ArrayList<String> toTables;
	
	TableExporterLauncher(String userName, String password, String databaseFrom, String databaseTo, boolean overwrite){
		this.userName=userName;
		this.password=password;
		this.databaseFrom=databaseFrom;
		this.databaseTo=databaseTo;
		this.overwrite=overwrite;
		this.fromTables=new ArrayList<String>();
		this.toTables=new ArrayList<String>();
	}

	public void getFromTables(){
		System.out.println("TELA1");
		if (fromTables.size() < 1) {
			System.out.println("TELA12");
			try{
				System.out.println("TELA13");
				String cmd = "mysql --user="+this.userName+" --password="+this.password+" -e \"SHOW TABLES FROM "+this.databaseFrom+";\" -B";
				System.out.println(cmd);
				Process p = Runtime.getRuntime().
						exec(new String[]{"/bin/bash","-c",cmd}); 
		
				// Se obtiene el stream de salida del programa 
				InputStream is = p.getInputStream(); 
				
				// Se prepara un bufferedReader para poder leer la salida más comodamente. 
				BufferedReader br = new BufferedReader (new InputStreamReader (is)); 
				String aux = br.readLine(); 
				
				// Mientras se haya leido alguna linea 
				if(aux.equals("Tables_in_"+this.databaseFrom)){
					while (aux!=null) 
					{ 
						// Se escribe la linea en pantalla 
						System.out.println (aux); 
						this.fromTables.add(aux);
						// y se lee la siguiente. 
						aux = br.readLine(); 
					}
					
				}
				p.waitFor();
			}
			catch (Exception e) 
			{ 
				// Excepciones si hay algún problema al arrancar el ejecutable o al leer su salida.*/
				e.printStackTrace(); 
			}	
		}
	}

	public void getToTables(){
		if (toTables.size() < 1) {
			try{
				String cmd = "mysql --user="+this.userName+" --password="+this.password+" -e \"SHOW TABLES FROM "+this.databaseTo+";\" -B";
				System.out.println(cmd);
				Process p = Runtime.getRuntime().
						exec(new String[]{"/bin/bash","-c",cmd}); 
		
				// Se obtiene el stream de salida del programa 
				InputStream is = p.getInputStream(); 
				
				// Se prepara un bufferedReader para poder leer la salida más comodamente. 
				BufferedReader br = new BufferedReader (new InputStreamReader (is)); 
				String aux = br.readLine(); 
				
				// Mientras se haya leido alguna linea 
				if(aux.equals("Tables_in_"+this.databaseTo)){
					while (aux!=null) 
					{ 
						// Se escribe la linea en pantalla 
						System.out.println (aux); 
						this.toTables.add(aux);
						// y se lee la siguiente. 
						aux = br.readLine(); 
					}
					
				}
				p.waitFor();
			}
			catch (Exception e) 
			{ 
				// Excepciones si hay algún problema al arrancar el ejecutable o al leer su salida.*/
				e.printStackTrace(); 
			}	
		}
	}
	
	public void launch(String tableFrom, String tableTo){
		System.out.println("TEL0.0");
		this.getFromTables();
		System.out.println("TEL1.0");
		this.getToTables();
		System.out.println("TEL2.0");
		this.launch(this.userName, this.password, this.databaseFrom, tableFrom, this.databaseTo, tableTo, this.overwrite);
	}
	
	public void launch (String user, String pass, String dbFrom, String tableFrom, String dbTo, String tableTo, boolean overwrite) {
		System.out.println("TEL0");
		if(this.fromTables.contains(tableFrom) & this.toTables.contains(tableTo)){
			System.out.println("TEL1");
			try {
				System.out.println("TEL2");
				if(overwrite){
					System.out.println("TEL3");
					String cmd = "mysql --user="+user+" --password="+pass+" -e \"DROP TABLE IF EXISTS "+dbTo+"."+tableTo+";\"";
					System.out.println(cmd);
					Process p = Runtime.getRuntime().
							exec(new String[]{"/bin/bash","-c",cmd}); 

					System.out.println("TEL4");
					// Se obtiene el stream de salida del programa 
					InputStream is = p.getInputStream(); 
					
					// Se prepara un bufferedReader para poder leer la salida más comodamente. 
					BufferedReader br = new BufferedReader (new InputStreamReader (is)); 
					String aux = br.readLine(); 
					
					// Mientras se haya leido alguna linea 
					while (aux!=null) 
					{ 
						// Se escribe la linea en pantalla 
						System.out.println (aux); 
						
						// y se lee la siguiente. 
						aux = br.readLine(); 
					} 
					p.waitFor();
				}
				System.out.println("TEL5");
				// Se crea la DB.  -h localhost
				String cmd = "mysql --user="+user+" --password="+pass+" -e \"RENAME TABLE "+dbFrom+"."+tableFrom+" TO "+dbTo+"."+tableTo+";\"";
				System.out.println(cmd);
				Process p = Runtime.getRuntime().
						exec(new String[]{"/bin/bash","-c",cmd}); 
				// Se obtiene el stream de salida del programa 
				InputStream is = p.getInputStream(); 

				System.out.println("TEL6");
				// Se prepara un bufferedReader para poder leer la salida más comodamente. 
				BufferedReader br = new BufferedReader (new InputStreamReader (is)); 
				
				// Se lee la primera linea 
				//String aux = br.readLine();
				String aux = ""; 
				
				// Mientras se haya leido alguna linea 
				while ((aux+=br.readLine()) != null) 
				{ 
					// Se escribe la linea en pantalla 
					//System.out.println (aux); 
					
					// y se lee la siguiente. 
					aux += br.readLine(); 
				} 
				System.out.println (aux); 
				p.waitFor();
			}  
			catch (Exception e) 
			{ 
				System.out.println("TEL99");
				// Excepciones si hay algún problema al arrancar el ejecutable o al leer su salida.*/
				e.printStackTrace(); 
			}		
		}

		System.out.println("TEL99AA");
	}

}
