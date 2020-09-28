package practicasisi.comparador;

import java.util.ArrayList;
import java.util.Collections;

public class Coleccion {
	private ArrayList<Oferta> ofertas;
	
	public Coleccion() {
		ofertas = new ArrayList<Oferta>();
	}
	
	public Coleccion(ArrayList<Oferta> ofertas) {
		this.ofertas = ofertas;
	}
	
	public void pushOferta(Oferta oferta) {
		ofertas.add(oferta);
	}
	
	
	public Oferta popOferta() {
		Oferta o = ofertas.get(ofertas.size()-1);
		ofertas.remove(ofertas.size()-1);
		return o;
	}
	
	public int count() {
		return ofertas.size();
	}
	
	public ArrayList<Oferta> getOfertas(){
		return this.ofertas;
	}
	
	public void setOfertas(ArrayList<Oferta> ofertas) {
		this.ofertas = ofertas;
	}
	
	public void merge(ArrayList<Oferta> ofertas) {
		this.ofertas.addAll(ofertas);
	}
	
	public void merge(Coleccion col) {
		this.ofertas.addAll(col.ofertas);
	}

	
	public void ponderar(int potencia, String combustible, String provincia, int fecha, int precio, int km) {
		for(Oferta o : this.ofertas) {
			o.ponderar(potencia, combustible, provincia, fecha, precio, km);
		}
		Collections.sort(this.ofertas);		
	}
	
}
