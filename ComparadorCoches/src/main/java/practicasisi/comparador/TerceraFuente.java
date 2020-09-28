package practicasisi.comparador;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Servlet implementation class TerceraFuente
 */
//@WebServlet(
//	    name = "TerceraFuente",
//	    urlPatterns = {"/tercerafuente"}
//	)
public class TerceraFuente  {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
  
//    @Override
//	  public void doGet(HttpServletRequest request, HttpServletResponse response) 
//	      throws IOException {
//
//	    response.setContentType("text/plain");
//	    response.setCharacterEncoding("UTF-8");
//	    ArrayList<ArrayList<String>> salida =new ArrayList<ArrayList<String>>();
//	    response.getWriter().print("Hola pr�ctica de ISI!\r\n");
//
//	    Document doc = ObtenerHTML();
//	    //response.getWriter().print(doc);
//	    
//	    salida= ObtenerDatos(doc);
//	    for(ArrayList<String> linea : salida) {
//	    	response.getWriter().print(linea+"\n");
//	    }
//	    //response.getWriter().print(salida);
//	  }
public ArrayList<ArrayList<String>> ObtenerDatos(Document doc) {
		
		ArrayList<ArrayList<String>> datos= new ArrayList<ArrayList <String>>();
		Element body = doc.body();
		Elements articulos = body.select("article.elemento-segunda-mano");
		//mt-Card-hover  mt-Card--topAd
		String prec="";
		
		for (Element articulo : articulos){
			
				Elements lis = articulo.getElementsByClass("nombre-vehiculo");
				ArrayList<String> meter= new ArrayList<String>();
				//meter.add(articulo.select("h2").text());
				//prec=lis.select("span:contains(cv)").text();
				//MARCA Y MODELO
				
				meter.add(lis.select("a").text());
				
				//POTENCIA
				
				lis=articulo.select("li");
				prec=lis.select(":contains(cv)").text();
				prec = prec.replace("CV","");
				meter.add(prec.trim());
				//COMBUSTIBLE
				Element li=articulo.select("ul").get(0);
				meter.add(li.child(1).text());
				//ZONA GEOGRAFICA
				prec=articulo.getElementsByClass("lugar").text();
				int inicio=prec.indexOf("(");
				//int fin=prec.indexOf(")");
				prec=prec.substring(inicio+1);
				prec=prec.replace(")","");
				meter.add(prec.trim());
				//FECHA MATRICULACION
				li=articulo.select("ul").get(0);
				meter.add(li.child(0).text());
//				meter.add(articulo.getElementsByClass("year").text());
				//PRECIO
				
				prec=articulo.select("strong").text();
				//prec = prec.replace("Con financiación","");
				prec = prec.replace("€","");
				prec = prec.replace("\u20AC", "");
				prec = prec.replace(".", "");
				meter.add(prec.trim());
				//KILÓMETROS
				lis=articulo.select("li");
				prec=lis.select(":contains(Km)").text();
				prec = prec.replace("Km","");
				prec = prec.replace(".", "");
				meter.add(prec.trim());
				
				//URL
				lis=articulo.getElementsByClass("datos");
				lis=lis.select("a");
				prec=lis.attr("href");
				meter.add(prec.trim());
				//IMAGEN
				lis=articulo.getElementsByClass("imagen");
				lis=lis.select("img");
				prec=lis.attr("data-src");
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
	public Document ObtenerHTML(String marca) {
		String url = "";
		if(marca.equals("Audi")) {
			url="https://www.motor.es/coches-segunda-mano/?marca=6";
		}else if(marca.equals("BMW")) {
			url="https://www.motor.es/coches-segunda-mano/?marca=9";
		}else if(marca.equals("Ford")) {
			url="https://www.motor.es/coches-segunda-mano/?marca=23";
		}else if(marca.equals("Honda")) {
			url="https://www.motor.es/coches-segunda-mano/?marca=25";
		}else if(marca.equals("Mercedes")) {
			url="https://www.motor.es/coches-segunda-mano/?marca=45";
		}else if(marca.equals("Mini")) {
			url="https://www.motor.es/coches-segunda-mano/?marca=48";
		}else if(marca.equals("Peugeot")) {
			url="https://www.motor.es/coches-segunda-mano/?marca=52";
		}else if(marca.equals("Seat")) {
			url="https://www.motor.es/coches-segunda-mano/?marca=60";
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
		    	
		    	
		    	if(linea.get(7).indexOf("www.motor.es") == -1) {
				    //System.out.print("SAL");
		    		continue;
		    	}
			    //System.out.print("Entra");

		    	Oferta o = new Oferta(linea.get(0), Integer.parseInt(linea.get(1)), linea.get(2), linea.get(3), Integer.parseInt(linea.get(4)), Integer.parseInt(linea.get(5)),Integer.parseInt(linea.get(6)), linea.get(7),linea.get(8));
		    	//System.out.println(linea.get(0));
		    	coleccion.pushOferta(o);
		    }
		    //System.out.print("FIN");
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
