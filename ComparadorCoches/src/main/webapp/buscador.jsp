<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

 <meta charset="UTF-8"> 
<meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" >
    <title>Busca tu nuevo coche</title>
    <link rel="stylesheet" type="text/css" media="screen" href="main.css">
</head>
<body>
 <header class="cabecera" >
        
        <a href="buscador.jsp"><img id="logo" src="https://cdn.pixabay.com/photo/2018/01/09/15/43/auto-3071895_960_720.png" alt="LOGO PRINCIPAL DE LA PÃGINA"></a>
        <h1 id="titulo">COMPARADOR DE COCHES</h1>

    </header>
    <main class="main">
    	<form class="cuadro" method="GET" action="/buscador">
    		<label class="formulario" for="usuario">Marca <br>
                <select name="marca" class="input" >
							<option value="">------</option>
							
                            <option value="Audi">Audi</option>
                            
                            <option value="BMW">BMW</option>
                            
                            <option value="Ford">Ford</option>

                            <option value="Honda">Honda</option>

                            <option value="Mercedes">Mercedes</option>

                            <option value="Mini">Mini</option>

                            <option value="Peugeot">Peugeot</option>
                            
                            <option value="Seat">Seat</option>
                            
                            </select>
            </label>
            <label class="formulario" for="correo">Potencia (cv)<br>
                <input class="input" type="number" name="potencia">
            </label>
            <label class="formulario" for="usuario">Provincia <br>
                <select class="input" name="provincia" >
                <option value='0'>------</option>
  <option value='alava'>Álava</option>
  <option value='albacete'>Albacete</option>
  <option value='alicante'>Alicante</option>
  <option value='almeria'>Almería</option>
  <option value='asturias'>Asturias</option>
  <option value='avila'>Ávila</option>
  <option value='badajoz'>Badajoz</option>
  <option value='barcelona'>Barcelona</option>
  <option value='burgos'>Burgos</option>
  <option value='caceres'>Cáceres</option>
  <option value='cadiz'>Cádiz</option>
  <option value='cantabria'>Cantabria</option>
  <option value='castellon'>Castellón</option>
  <option value='ceuta'>Ceuta</option>
  <option value='ciudadreal'>Ciudad Real</option>
  <option value='cordoba'>Córdoba</option>
  <option value='cuenca'>Cuenca</option>
  <option value='girona'>Girona</option>
  <option value='laspalmas'>Las Palmas</option>
  <option value='granada'>Granada</option>
  <option value='guadalajara'>Guadalajara</option>
  <option value='guipuzcoa'>Guipuzcoa</option>
  <option value='huelva'>Huelva</option>
  <option value='huesca'>Huesca</option>
  <option value='illesbalears'>Illes Balears</option>
  <option value='jaen'>Jaén</option>
  <option value='acoruÃ±a'>A Coruña</option>
  <option value='larioja'>La Rioja</option>
  <option value='leon'>León</option>
  <option value='lleida'>Lleida</option>
  <option value='lugo'>Lugo</option>
  <option value='madrid'>Madrid</option>
  <option value='malaga'>Málaga</option>
  <option value='melilla'>Melilla</option>
  <option value='murcia'>Murcia</option>
  <option value='navarra'>Navarra</option>
  <option value='ourense'>Ourense</option>
  <option value='palencia'>Palencia</option>
  <option value='pontevedra'>Pontevedra</option>
  <option value='salamanca'>Salamanca</option>
  <option value='segovia'>Segovia</option>
  <option value='sevilla'>Sevilla</option>
  <option value='soria'>Soria</option>
  <option value='tarragona'>Tarragona</option>
  <option value='santacruztenerife'>Santa Cruz de Tenerife</option>
  <option value='teruel'>Teruel</option>
  <option value='toledo'>Toledo</option>
  <option value='valencia'>Valencia</option>
  <option value='valladolid'>Valladolid</option>
  <option value='vizcaya'>Vizcaya</option>
  <option value='zamora'>Zamora</option>
  <option value='zaragoza'>Zaragoza</option>
</select>
            </label>
            <label class="formulario" for="correo">Año de matriculación <br>
                <input class="input" type="number" name="fecha">
            </label>
            <label class="formulario" for="correo">Precio(Euros) <br>
                <input class="input" type="number" name="precio">
            </label>
            
            <label class="formulario" for="correo">Kilómetros <br>
                <input class="input" type="number" name="km">
            </label>
                         <label class="formulario1" >Tipo Combustible<br>
                <select  class="input" name="combustible" >
							<option value="">------</option>
							
                            <option value="gasolina">Gasolina</option>
                            
                            <option value="diesel">Diesel</option>
                            
                            <option value="electrico">Eléctrico</option>
                            
                            </select>
            			</label>
            <label for="submit">
             <input id="submit" type="submit" value="Buscar" name="buscar" > 
             </label>
    </form>
    <section class="derecha">
    	<img id="logo1" src="https://cdn.pixabay.com/photo/2013/07/13/11/36/volkswagen-158463_960_720.png" alt="LOGO PRINCIPAL DE LA PÃGINA">
    </section>
     </main>
     <footer class="pie">
     <p>Autores: <b>Antonio Jiménez</b> y <b>Andrés López</b></p>
	 
     </footer>
    <!-- <h1>Hola prÃ¡ctica de ISI!</h1>
	<h2>Antonio JimÃ©nez</h2>
	<h2>AndrÃ©s LÃ³pez</h2> -->
    <!-- <table>
      <tr>
        <td colspan="2" style="font-weight:bold;">Available Servlets:</td>        
      </tr>
      <tr>
        <td><a href='/hello'>The servlet</a></td>
        <td><a href='/fuentemilanuncios'>Milanuncion.com</a></td>
        <td><a href='/fuenteCochesNet'>Coches.net</a></td>
        <td><a href='/tercerafuente'>TerceraFuente.net</a></td>
      </tr>
    </table> -->
  </body>
</html>