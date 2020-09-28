package practicasisi.comparador;
import java.io.FileReader; 
import java.util.Iterator; 
import java.util.Map;

import org.jsoup.nodes.Document;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class API {
	String url="";
	
	public String ObtenerDatosParaJSON(String marca) 
			throws IOException {
		//ArrayList<ArrayList<String>> datos= new ArrayList<ArrayList <String>>();
		String datos="";
		if(marca.equals("Audi")) {
			url="https://www.carnext.com/api2/occasions/cars?locale=es-es&size=17&makeModels[audi]=";
		}else if(marca.equals("BMW")) {
			url="https://www.carnext.com/api2/occasions/cars?locale=es-es&size=17&makeModels[bmw]=";
		}else if(marca.equals("Ford")) {
			url="https://www.carnext.com/api2/occasions/cars?locale=es-es&size=17&makeModels[ford]=";
		}else if(marca.equals("Honda")) {
			url="https://www.carnext.com/api2/occasions/cars?locale=es-es&size=17&makeModels[honda]=";
		}else if(marca.equals("Mercedes")) {
			url="https://www.carnext.com/api2/occasions/cars?locale=es-es&size=17&makeModels[mercedes-benz]=";
		}else if(marca.equals("Mini")) {
			url="https://www.carnext.com/api2/occasions/cars?locale=es-es&size=17&makeModels[mini]=";
		}else if(marca.equals("Peugeot")) {
			url="https://www.carnext.com/api2/occasions/cars?locale=es-es&size=17&makeModels[peugeot]=";
		}else if(marca.equals("Seat")) {
			url="https://www.carnext.com/api2/occasions/cars?locale=es-es&size=17&makeModels[seat]=";
		}	
		//  ["+marca+"]
			
			URL obj = new URL(url);
			URLConnection con = obj.openConnection();
			 BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			 String linea;
	            while ((linea = in.readLine()) != null) {
	                datos=datos+linea;
	            }
	            in.close();
	            
             System.out.print(datos);
			  
			return datos;
		}
	
	public JsonObject CambioJSON(String datos) 
			{
		 //Object obj = new JSONParser().parse(datos); 
//		 JSONParser obj = new JSONParser();
		JsonObject jsonObject = new Gson().fromJson(datos, JsonObject.class);
	        return jsonObject;
	}
	
	
	
	
	public ArrayList<ArrayList<String>> obtenerDatos(JsonObject json){
		ArrayList<ArrayList<String>> datos= new ArrayList<ArrayList<String>>();

		try {
			JsonArray array=json.getAsJsonArray("occasions");

			String prec="";
			int posicion = 0;
			int indice = 0;
			//int num_ofertas= json.get("totalCount").getAsInt();
			int num_ofertas = array.size();
			while(indice < num_ofertas) {
				ArrayList<String> meter= new ArrayList<String>();

				JsonElement elemento=array.get(indice);
				//System.out.print("\n\n\nelemento " + (indice + 1) + "/" + num_ofertas + ": " + elemento);
				
				JsonObject elementoObject = elemento.getAsJsonObject();
				
				//PARA MARCA Y MODELO
				prec = elementoObject.get("make").getAsString() + " ";
				prec += elementoObject.get("model").getAsString() + " ";
				//String tipo = elementoObject.get("type").getAsString();
				//prec += tipo.substring(0, tipo.lastIndexOf(" ")).trim();
				prec += elementoObject.get("type").getAsString();

				//System.out.print("\nMarca y modelo: " + prec);

				meter.add(prec);
				
				//POTENCIA
				prec=elementoObject.get("type").getAsString();
				posicion=prec.lastIndexOf(" ");
				prec=prec.substring(posicion+1);
				meter.add(prec);
				
				//System.out.print("\nPotencia: " + prec);

				//COMBUSTIBLE
				prec=elementoObject.get("fuel").getAsString();
				if(prec.equals("gasoline")) {
					meter.add("Gasolina");
				}else if(prec.equals("diesel")) {
					meter.add("Diesel");
				}else {
					meter.add("Electrico");
				}
				//System.out.print("\nCombustible: " + prec);

				
				//ZONA GEOGRAFICA
				prec=elementoObject.get("retailLocationCode").getAsString();
				meter.add(prec.substring(0, 1).toUpperCase() + prec.substring(1));
				//System.out.print("\nProvincia: " + prec);

			
				//FECHA MATRICULACION
				prec=Integer.toString(elementoObject.get("yearOfConstruction").getAsInt());
				meter.add(prec);
				//System.out.print("\nFecha: " + prec);

			
				//PRECIO
				prec=Integer.toString(elementoObject.get("salePrice").getAsJsonObject().get("amount").getAsInt());
				meter.add(prec);
				//System.out.print("\nPrecio: " + prec);

			
				//KILÓMETROS
				prec=Integer.toString(elementoObject.get("mileage").getAsJsonObject().get("amount").getAsInt());
				meter.add(prec);
				//System.out.print("\nKm: " + prec);
				
			
				//URL
				prec="https://www.carnext.com/es-es/coches/";
				prec+=elementoObject.get("make").getAsString() +"/";
				prec+=elementoObject.get("model").getAsString() +"/";
				prec+=elementoObject.get("id").getAsString() +"/";
				meter.add(prec);
				//System.out.print("\nLink: " + prec);

			
				//IMAGEN
				//elemento2=objeto.getAsJsonObject("image");
				//prec=elemento2.get("urlTemplate").getAsString();
				prec=elementoObject.get("image").getAsJsonObject().get("urlTemplate").getAsString();

				prec=prec.replace("{size}","400");
				prec=prec.replace("{dimension}","width");
				meter.add(prec);
				//System.out.print("\nImagen: " + prec);

				datos.add(meter);
				
				indice++;
				
				
			}
			

		}
		catch(Exception ex) {
			System.out.print(ex.getMessage());
		}
		
		return datos;
	}
	
	
	
	public Coleccion Buscar(ArrayList<ArrayList<String>> datos) {
		  

		  
		    
		    Coleccion coleccion = new Coleccion();
		    //System.out.println("hola");
		    for(ArrayList<String> linea : datos) {
		    	
		    	//System.out.println(linea.get(0));//MARCA Y MODELO
		    	//System.out.println(linea.get(1));//PTENCIA
		    	//System.out.println(linea.get(2));//COMBUSTIBLE
		    	//System.out.println(linea.get(3));//PROVINCIA
		    	//System.out.println(linea.get(4));//AÑO
		    	//System.out.println(linea.get(5));//PRECIO
		    	//System.out.println(linea.get(6));//KM
		    	//System.out.println(linea.get(7));//LINK
		    	//System.out.println(linea.get(8));//IMAGEN
		    	
		    	/*if(linea.get(1).trim().equals("")) {
		    		linea.set(1, "0");
		    	}
		    	if(linea.get(4).trim().equals("")) {
		    		linea.set(4, "0");
		    	}
		    	if(linea.get(5).trim().equals("")) {
		    		linea.set(5, "0");
		    	}
		    	if(linea.get(6).trim().equals("")) {
		    		linea.set(6, "0");
		    	}*/
		    	
		    	if(!tryParseInt(linea.get(1).trim())) {
		    		linea.set(1, "0");
		    	}
		    	if(!tryParseInt(linea.get(4).trim())) {
		    		linea.set(4, "0");
		    	}
		    	if(!tryParseInt(linea.get(5).trim())) {
		    		linea.set(5, "0");
		    	}
		    	if(!tryParseInt(linea.get(6).trim())) {
		    		linea.set(6, "0");
		    	}

		    	
		    	//System.out.println("\n Antes");
		    	Oferta o = new Oferta(linea.get(0), Integer.parseInt(linea.get(1)), linea.get(2), linea.get(3), Integer.parseInt(linea.get(4)), Integer.parseInt(linea.get(5)),Integer.parseInt(linea.get(6)), linea.get(7),linea.get(8));
		    	//System.out.println(linea.get(0));
		    	coleccion.pushOferta(o);
		    	//System.out.println("Despues\n");
		    }
		    //System.out.println("Fin");
		    return coleccion;
		}
	
	boolean tryParseInt(String value) {  
	     try {  
	         Integer.parseInt(value);  
	         return true;  
	      } catch (NumberFormatException e) {  
	         return false;  
	      }  
	}
	
}
