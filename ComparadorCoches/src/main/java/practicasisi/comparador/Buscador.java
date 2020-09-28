package practicasisi.comparador;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.JsonObject;

import sun.rmi.runtime.Log;

@WebServlet(
	    name = "Buscador",
	    urlPatterns = {"/buscador"}
	)

public class Buscador extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	  public void doGet(HttpServletRequest request, HttpServletResponse response) 
	      throws IOException {
		String datos="";
		
		try {
			ArrayList<ArrayList<String>> datosMatriz= new ArrayList<ArrayList<String>>();
			Coleccion coleccion = new Coleccion();
			String marca = (String) request.getParameter("marca");
			String potencia = (String) request.getParameter("potencia");
			String combustible = (String) request.getParameter("combustible");
			String provincia = (String) request.getParameter("provincia");
			String fecha = (String) request.getParameter("fecha");
			String precio = (String) request.getParameter("precio");
			String km = (String) request.getParameter("km");
			//System.out.println(marca);
			
			
			
			try {
				//System.out.print("\n Inicio");
				API api=new API();
				//System.out.print("\n Objeto creado");

				datos=api.ObtenerDatosParaJSON(marca);
				//System.out.print("\n Datos obtenidos");

				JsonObject js=api.CambioJSON(datos);
				//System.out.print("\n Datos cambiados");

				//js.get("totalCount").getAsString();
				//System.out.println(js.get("totalCount").getAsString());
				datosMatriz=api.obtenerDatos(js);
				//System.out.print("\n Datos obtenidos");

				Coleccion coleccion4= api.Buscar(datosMatriz);
				coleccion.merge(coleccion4);
			}
			catch (Exception ex){
				log(ex.getMessage());
			}
			
			
			//System.out.print("\n Coleccion cargada");

			try {
				PrimeraFuente primeraFuente = new PrimeraFuente();
				Coleccion coleccion1 = primeraFuente.Buscar(marca);
				coleccion.merge(coleccion1);
			}
			catch (Exception ex){
				log(ex.getMessage());
			}
			
			try {
				SegundaFuente segundaFuente = new SegundaFuente();
				Coleccion coleccion2 = segundaFuente.Buscar(marca);
				coleccion.merge(coleccion2);
			}
			catch (Exception ex){
				log(ex.getMessage());
			}
			
			try {
				TerceraFuente terceraFuente=new TerceraFuente();
				Coleccion coleccion3 = terceraFuente.Buscar(marca);
				coleccion.merge(coleccion3);
			}
			catch (Exception ex){
				log(ex.getMessage());
			}
			
			
			
			
			
			
			
			if(potencia.trim().equals("")) {
				potencia = "0";
	    	}
	    	if(fecha.trim().equals("")) {
	    		fecha = "0";
	    	}
	    	if(precio.trim().equals("")) {
	    		precio = "0";
	    	}
	    	if(km.trim().equals("")) {
	    		km = "0";
	    	}
			
			coleccion.ponderar(Integer.parseInt(potencia), combustible, provincia, Integer.parseInt(fecha), Integer.parseInt(precio), Integer.parseInt(km));
			
			request.setAttribute("ofertas", coleccion.getOfertas());
			request.getRequestDispatcher("resultado.jsp").forward(request, response);
			
			}
		catch(Exception ex){
			
		}
	  }
	
	
	
	
}
