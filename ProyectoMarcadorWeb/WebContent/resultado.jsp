<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Resultado</title>
<style>
	.goles{width:800px;font-size:40px;color:black;margin:auto;text-align:center}
	#goleslocal{float:left;margin-left:267px;width:200px;padding:50px;background-color:blue;margin-top:20px;}
	#golesvisitante{float:left;margin-top:20px;width:200px;padding:50px;background-color:blue;}
	#resultado{width:200px;padding-top:35px;padding-bottom:35px;float:left;margin-top:20px;text-align:center;}
	img{margin-top:50px;}
	#divfoto{text-align:center;}
	table{margin:auto;width:800px;border:solid thin black;text-align:center;padding:20px;margin-top:200px;}
</style>
<script>
	function programarllamada(){
		setInterval(actualizarResultado, 10000);
	}
	var xmlHttp = new XMLHttpRequest();
	function actualizarResultado(){
		//Iniciamos el Ajax.
		xmlHttp.onreadystatechange = mensajerecibido;
		xmlHttp.open('GET', 'ActualizarInfoPartido', true);
		xmlHttp.send(null);

	}
	function mensajerecibido(){
		if(xmlHttp.readyState == 4){
			if(xmlHttp.status == 200){
				//Habremos recibido un Json
				console.log(xmlHttp.responseText);
				var infojson = JSON.parse(xmlHttp.responseText);
				alert("Goles Visitante" + infojson.marcador.goles_visitante);
				alert("Goles Locales" + infojson.marcador.goles_local);
			}
		}
	}
</script>
</head>
<body onload="programarllamada()">
	<div id="divfoto">
		<img src="${imagen.ruta }" width="800" height="400" />
	</div>
	<div class="goles" id="goleslocal">${marcador.goles_local }</div>
	<div id="resultado"><h1>Resultado</h1></div>
	<div class="goles" id="golesvisitante">${marcador.goles_visitante }</div>
	<div id="comentarios">
		<table>
			<th>Minuto</th>
			<th>Comentario</th>
			<tr>
				<td>${comentario.minuto }'</td>
				<td>${comentario.comentario }</td>
			</tr>
		</table>
	</div>
</body>
</html>