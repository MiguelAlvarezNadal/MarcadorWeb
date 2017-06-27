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
	table{margin:auto;width:800px;text-align:center;padding:20px;margin-top:200px;}
	th{border:solid thin black;padding:20px;}
	td{padding:10px;}
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

				//Actualizamos el marcador
				var caja_local = document.getElementById("goleslocal");
				var caja_visitante = document.getElementById("golesvisitante");
				caja_local.innerHTML = infojson.marcador.goles_local;
				caja_visitante.innerHTML = infojson.marcador.goles_visitante;
				
				//Actualizamos los comentarios
				var tabla = document.getElementById("tablacomentarios");
				while(tabla.rows.length > 1){//Recorro la tabla
					tabla.deleteRow(1);//Borro las filas
				}
				for(var i = 0; i < infojson.listacomentatios.length; i++){	
					var tr_nueva = tabla.insertRow(1);
					var td_min = tr_nueva.insertCell(0);
					var td_comentario = tr_nueva.insertCell(1);
					td_min.innerHTML = infojson.listacomentatios[i].minuto + "'";
					td_comentario.innerHTML = infojson.listacomentatios[i].comentario;
				}
				
				var cajafoto = document.getElementById("fotopartido");
				cajafoto.src = infojson.fotopartido;
			}
		}
	}
</script>
</head>
<body onload="programarllamada()">
	<div id="divfoto">
		<img src="inicio.jpg" width="800" height="400" />
	</div>
	<div class="goles" id="goleslocal">${marcador.goles_local }</div>
	<div id="resultado"><h1>Resultado</h1></div>
	<div class="goles" id="golesvisitante">${marcador.goles_visitante }</div>
	<div id="comentarios">
		<table id="tablacomentarios">
			<th>Minuto</th>
			<th>Comentario</th>
			<th>FOTO</th>
			<tr>
				<td>${comentario.minuto }'</td>
				<td>${comentario.comentario }</td>
				<td><img src="${imagen.ruta }" width="200" height="100"  id="fotopartido"/></td>
			</tr>
		</table>
	</div>
</body>
</html>