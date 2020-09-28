package practicasisi.comparador;

public class Oferta implements Comparable<Oferta> {
	private String marca;
	private int potencia;
	private String combustible;
	private String provincia;
	private int fecha;
	private int precio;
	private int km;
	private String link;
	private String imagen;
	private int ponderacion;
	
	private static int ponPotencia = 5;
	private static int ponCombustible = 30;
	private static int ponProvincia = 50;
	private static int ponFecha = 1;
	private static int ponPrecio = 10;
	private static int ponKm = 5;

	public Oferta() {
		
	}

	public Oferta(String marca, int potencia,String combustible, String provincia, int fecha, int precio, int km, String link,String imagen) {
		this.marca = marca;
		this.potencia = potencia;
		this.combustible = combustible;
		this.provincia = provincia;
		this.fecha = fecha;
		this.precio = precio;
		this.km = km;
		this.link=link;
		this.imagen=imagen;
		this.ponderacion = 0;
	}
	
	
	
	  public String getMarca() {
	    return marca;
	  }
	  
	  public void setMarca(String marca) {
	    this.marca = marca;
	  }
	  
	  public int getPotencia() {
	    return potencia;
	  }
 
	  public void setPotencia(int potencia) {
	    this.potencia = potencia;
	  }
	  
	  public String getCombustible() {
		    return combustible;
	  }
	 
	  public void setCombustible(String combustible) {
		    this.combustible = combustible;
	  }
	  
	  public String getProvincia() {
	    return provincia;
	  }
 
	  public void setProvincia(String provincia) {
	    this.provincia = provincia;
	  }
	  
	  public int getFecha() {
	    return fecha;
	  }
 
	  public void setFecha(int fecha) {
	    this.fecha = fecha;
	  }
	  
	  public int getPrecio() {
	    return precio;
	  }
 
	  public void setPrecio(int precio) {
	    this.precio = precio;
	  }
	  
	  public int getKm() {
	    return km;
	  }
 
	  public void setKm(int km) {
	    this.km = km;
	  }
	  
	  public String getLink() {
		    return link;
	  }
	 
	  public void setLink(String link) {
		    this.link = link;
	  }
	  public String getImagen() {
		    return imagen;
	  }
	 
	  public void setImagen(String imagen) {
		    this.imagen = imagen;
	  }
	  
	  public int getPonderacion() {
		  return ponderacion;
	  }
	  
	  public void ponderar(int potencia, String combustible, String provincia, int fecha, int precio, int km) {
		  
		  this.ponderacion = 0;
		  if(potencia != 0) {
			  int difPotencia = this.potencia - potencia;
			  this.ponderacion -= Math.abs(difPotencia/10) * ponPotencia;
		  }
		  
		  if(!combustible.trim().equals("")) {
			  if(this.combustible.toLowerCase().equals(combustible.trim().toLowerCase())) {
				  this.ponderacion += ponCombustible;
			  }
			  else {
				  this.ponderacion += -ponCombustible;
			  }
		  }
		  		  
		  if(!provincia.trim().equals("")) {
			  String p1 = this.provincia.toLowerCase();
			  String p2 = provincia.toLowerCase();
			  
			  p1.replace("á", "a");
			  p1.replace("é", "e");
			  p1.replace("í", "i");
			  p1.replace("ó", "o");
			  p1.replace("ú", "u");
			  p1.replace("Á", "A");
			  p1.replace("É", "E");
			  p1.replace("Í", "I");
			  p1.replace("Ó", "O");
			  p1.replace("Ú", "U");
			  p1.replace("ñ", "n");
			  p1.replace("Ñ", "N");
			  p2.replace("á", "a");
			  p2.replace("é", "e");
			  p2.replace("í", "i");
			  p2.replace("ó", "o");
			  p2.replace("ú", "u");
			  p2.replace("Á", "A");
			  p2.replace("É", "E");
			  p2.replace("Í", "I");
			  p2.replace("Ó", "O");
			  p2.replace("Ú", "U");
			  p2.replace("ñ", "n");
			  p2.replace("Ñ", "N");
			  
			  if(p1.equals(p2.trim())) {
				  this.ponderacion += ponProvincia;
			  }
		  }
		  
		  if(fecha != 0) {
			  int difFecha = this.fecha - fecha;
			  this.ponderacion += difFecha * ponFecha;
		  }
		  
		  if(precio != 0) {
			  int difPrecio = precio - this.precio;
			  this.ponderacion += (difPrecio/1000) * ponPrecio;
		  }
		  
		  if(km != 0) {
			  int difKm = km - this.km;
			  this.ponderacion += (difKm/5000) * ponKm;
		  }
		  
		  //System.out.print(this.ponderacion);
	  }

	@Override
	public int compareTo(Oferta o) {
		return o.ponderacion - this.ponderacion;
	}
	  


	  
	  
}
