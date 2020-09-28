package practicasisi.comparador;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



public class PrimeraFuente{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		
	public Document ObtenerHTML(String marca) {
		String url = "";
		
		if(marca.equals("Audi")) {
			url="https://www.coches.com/coches-segunda-mano/audi.html";
		}else if(marca.equals("BMW")) {
			url="https://www.coches.com/coches-segunda-mano/bmw.html";
		}else if(marca.equals("Ford")) {
			url="https://www.coches.com/coches-segunda-mano/ford.html";
		}else if(marca.equals("Honda")) {
			url="https://www.coches.com/coches-segunda-mano/honda.html";
		}else if(marca.equals("Mercedes")) {
			url="https://www.coches.com/coches-segunda-mano/mercedes.html";
		}else if(marca.equals("Mini")) {
			url="https://www.coches.com/coches-segunda-mano/mini.html";
		}else if(marca.equals("Peugeot")) {
			url="https://www.coches.com/coches-segunda-mano/peugeot.html";
		}else if(marca.equals("Seat")) {
			url="https://www.coches.com/coches-segunda-mano/seat.html";
		}
		
		Response response = null;
	    //try {
	    //	response = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(100000).ignoreHttpErrors(true).execute();
	    //	System.out.println("Codigo:" + response.statusCode());
	    //} catch (IOException ex) {
	    //	System.out.println("Excepci�n al obtener el Status Code: " + ex.getMessage());
	    //}
	    
	    
	    Document doc = null;
    	try {
			doc = Jsoup.connect(url).userAgent("Mozilla/5.0").get();
					
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    return doc;
	    
	}
	
	
	public ArrayList<ArrayList<String>> ObtenerDatos(Document doc) {
		
		ArrayList<ArrayList<String>> datos= new ArrayList<ArrayList <String>>();
		Element body = doc.body();
		Elements articulos = body.getElementsByClass("script__pill");
		//mt-Card-hover  mt-Card--topAd
		String prec="";
		
		for (Element articulo : articulos){
			
				Elements lis = articulo.select("h2");
				ArrayList<String> meter= new ArrayList<String>();
				//meter.add(articulo.select("h2").text());
				//prec=lis.select("span:contains(cv)").text();
				//MARCA Y MODELO
				meter.add(articulo.getElementsByClass("make-model-version").text().trim());
				
				//POTENCIA
				prec=articulo.getElementsByClass("cv").text();
				prec = prec.replace("cv","");
				meter.add(prec.trim());
				//COMBUSTIBLE
				meter.add(articulo.getElementsByClass("gas").text().trim());
				//ZONA GEOGRAFICA
				
				meter.add(articulo.getElementsByClass("location").text().trim());
				//FECHA MATRICULACION
				
				meter.add(articulo.getElementsByClass("year").text().trim());
				//PRECIO
				
				prec=articulo.getElementsByClass("price").text();
				//prec = prec.replace("Con financiación","");
				prec = prec.replace("€","");
				prec = prec.replace("\u20AC", "");
				prec = prec.replace(".", "");
				meter.add(prec.trim());
				//KILÓMETROS
				
				//lis = articulo.select("li:contains(Kilómetros)");
				prec=articulo.getElementsByClass("km").text();
				prec = prec.replace("km","");
				prec = prec.replace(".","");
//				prec=lis.select("span").text();
				meter.add(prec.trim());
				//URL
				lis=articulo.getElementsByClass("primary-link");
				prec=lis.attr("href");
				meter.add(prec.trim());
				//IMAGEN
				
				lis=articulo.getElementsByClass("main-photo");
				prec=lis.attr("src");
				meter.add(prec.trim());
				//El selector span:nth-child(x) busca al padre de span y elige al elemento hijo en la posición x
		  //  datos = "\n"+articulo.select("h2").text();
		    datos.add(meter);
		}
		
		/*
		 * Tipo de combustible.
Potencia y tamaño del motor.
Kilómetros recorridos del vehículo.
Marca y modelo.
Zona geográfica.
Fecha de matriculación.
Precio.
*/
		 
		return datos;
	}
	 
	
	public Coleccion Buscar(String marca) {
		  ArrayList<ArrayList<String>> datos=new ArrayList<ArrayList<String>>();

		  Document doc=ObtenerHTML(marca);
		    datos=ObtenerDatos(doc);
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
		    	
		    	Oferta o = new Oferta(linea.get(0), Integer.parseInt(linea.get(1)), linea.get(2), linea.get(3), Integer.parseInt(linea.get(4)), Integer.parseInt(linea.get(5)),Integer.parseInt(linea.get(6)), linea.get(7),linea.get(8));
		    	//System.out.println(linea.get(0));
		    	coleccion.pushOferta(o);
		    }
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
