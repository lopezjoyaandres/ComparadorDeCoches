package practicasisi.comparador;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class SegundaFuente{
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
		
		
			public Document ObtenerHTML(String marca) {
				Response response1=null;
				Document doc=null;
				String url="";//"https://www.autocasion.com/coches-segunda-mano/alfa-romeo-ocasion?page=2";
				//System.out.println(marca);
				if(marca.equals("Audi")) {
					url="https://www.autocasion.com/coches-segunda-mano/audi-ocasion";
				}else if(marca.equals("BMW")) {
					url="https://www.autocasion.com/coches-segunda-mano/bmw-ocasion";
				}else if(marca.equals("Ford")) {
					url="https://www.autocasion.com/coches-segunda-mano/ford-ocasion";
				}else if(marca.equals("Honda")) {
					url="https://www.autocasion.com/coches-segunda-mano/honda-ocasion";
				}else if(marca.equals("Mercedes")) {
					url="https://www.autocasion.com/coches-segunda-mano/mercedes-benz-ocasion";
				}else if(marca.equals("Mini")) {
					url="https://www.autocasion.com/coches-segunda-mano/mini-ocasion";
				}else if(marca.equals("Peugeot")) {
					url="https://www.autocasion.com/coches-segunda-mano/peugeot-ocasion";
				}else if(marca.equals("Seat")) {
					url="https://www.autocasion.com/coches-segunda-mano/seat-ocasion";
				}
				 //try {
				 //   	response1 = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(100000).ignoreHttpErrors(true).execute();
				 //   	
				 //   } catch (IOException ex) {
				 //   	System.out.println("Excepción al obtener el Status Code: " + ex.getMessage());
				 //   }
				  
				  try {
					   doc = Jsoup.connect(url).get();
					 
				  } catch (IOException ex) {
				    	System.out.println("Excepcion al obtener el documento: " + ex.getMessage());
				    }
				
				return doc;
			}
			public ArrayList<ArrayList<String>> ObtenerDatos(Document doc) {
				ArrayList<ArrayList<String>> datos= new ArrayList<ArrayList <String>>();
				Element body = doc.body();
				Elements articulos = body.getElementsByClass("anuncio");
				
				String prec="";
				for (Element articulo : articulos){
					
						Elements lis = articulo.select("li");
						
						ArrayList<String> meter= new ArrayList<String>();
						//meter.add(articulo.select("h2").text());
						//prec=lis.select("span:contains(cv)").text();
						//MARCA Y MODELO
						meter.add(articulo.select("h2").text().trim());
						//POTENCIA
						prec=lis.select("span:contains(cv)").text();
						prec = prec.replace("cv","");
						meter.add(prec.trim());
						//COMBUSTIBLE
						prec=lis.select("li:contains(Combustible)").select("span").text();
						meter.add(prec.trim());
						//ZONA GEOGRAFICA
						lis = articulo.select("li:contains(Provincia)");
						prec=lis.select("span").text();
						meter.add(lis.select("span").text().trim());
						//FECHA MATRICULACION
						lis = articulo.select("li:contains(Matriculación)");
						prec=lis.select("span").text();
						meter.add(lis.select("span").text().trim());
						//PRECIO
						
						prec=articulo.select(".precio").not(".icon icon-flecha-corta-abajo").text();
						//prec=lis.not("financiacion").text();
						prec = prec.replace("Con financiación","");
						prec = prec.replace("€","");
						prec = prec.replace("\u20AC", "");
						prec = prec.replace(".", "");
						prec = prec.trim();
						int index = prec.lastIndexOf(" ");
						if(index > 0) {
							prec = prec.substring(index);
						}
						meter.add(prec.trim());
						//KILÓMETROS
						
						lis = articulo.select("li:contains(Kilómetros)");
						prec=lis.select("span").text();
						//prec = prec.replace("Kilómetros:","");
						prec = prec.replace("km","");
						prec = prec.replace(".", "");
						meter.add(prec.trim());
						//URL
						
						
						lis=articulo.select("a");
						prec="https://www.autocasion.com"+lis.attr("href");
						meter.add(prec.trim());
				    //datos.add(meter);
				    //IMAGEN
				    lis=articulo.select("img");
				    prec="https:"+lis.attr("src");
				    meter.add(prec.trim());
				    
				    datos.add(meter);
				}
//				articulos = body.getElementsByClass("anuncio");
//				for (Element articulo : articulos){
//					lis = articulo.select("li:contains(Kilómetros)");
//				}
				
				
				
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


