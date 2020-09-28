<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
<title>Insert title here</title>
<meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
    <link rel="stylesheet" type="text/css" media="screen" href="main.css">
</head>
<body>
	<header class="cabecera" id="cab" >
        
        <a href="buscador.jsp"><img id="logo" src="https://cdn.pixabay.com/photo/2018/01/09/15/43/auto-3071895_960_720.png" alt="LOGO PRINCIPAL DE LA PÁGINA"></a>
        <h1 id="titulo">COMPARADOR DE COCHES</h1>

    </header>
    <main class="main1">
    <section class="subtitulo">
    <h2 class="valor">Resultado de la b&uacute;squeda</h2>
    </section>
    
    <c:forEach var="oferta" items="${ofertas}">
    	<section class="apartado">
    	<a href=${oferta.link}>
    		
    	<img class="foto_coche" src="${oferta.imagen}" alt="FOTO COCHE">
    		<section class="desc">
    			<article class="apartado_peque1">
    				<p class="letra">Marca-Modelo: ${oferta.marca}</p>  
    			</article>
    			<article class="apartado_peque">
    				<p class="letra">Potencia(cv): ${oferta.potencia} CV</p>
    			</article>
    			<article class="apartado_peque">
    				<p class="letra">Tipo de combustible: ${oferta.combustible}</p>
    			</article>
    			<article class="apartado_peque">
    				<p class="letra">Provincia: ${oferta.provincia}</p>
    			</article>
    			<article class="apartado_peque">
    				<p class="letra">Precio(Euros): ${oferta.precio} &euro;</p>
    			</article>
    			<article class="apartado_peque">
    				<p class="letra">Kilometros: ${oferta.km} km</p>
    			</article>
    			<article class="apartado_peque">
    				<p class="letra">A&ntilde;o de matriculaci&oacute;n: ${oferta.fecha}</p>
    			</article>
    			
    			
    			
    	
			
    		</section>
    		</a>
    	</section>
    </c:forEach>
    
    	
    	
    		
    	    	
    </main>

</body>
</body>
</html>