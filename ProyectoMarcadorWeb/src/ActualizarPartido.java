

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
//import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class ActualizarPartido
 */
@WebServlet("/ActualizarPartido")
@MultipartConfig
public class ActualizarPartido extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActualizarPartido() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	private String obtenerNombreFichero(Part part){
		String nombre = null;
		for(String cd:part.getHeader("content-disposition").split(";")){
			if(cd.trim().startsWith("filename")){
				//Recogemos el nombre del fichero.
				String filename = cd.substring(cd.indexOf("=")+1).trim().replace("\"", "");
				nombre = filename.substring(filename.lastIndexOf('/')+1).substring(filename.lastIndexOf('\\')+1);
			}
		}
		return nombre;
	}
	
	private boolean formatoFicheroValido(String nombre_fichero){
		//TODO Comprobar que el nombre del fichero acaba en JPG
		return true;
	}
	
	private void guardarFichero(Part fichero, String nombre_fichero) throws Exception{
		String ruta_serv = getServletContext().getRealPath("");
		//Con File.separator nos permite poner una / o \ dependiendo si estamos en Linux o Windows
		File server_file = new File(ruta_serv + File.separator + nombre_fichero);
		byte[] buffer_read = new byte[1024];
		
		try(BufferedInputStream bis = new BufferedInputStream(fichero.getInputStream()); 
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(server_file)))
		{
			int byteleidos = 0;
			
			while((byteleidos = bis.read(buffer_read))!= -1){
				bos.write(buffer_read, 0, byteleidos);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		String goll = request.getParameter("goll");
		String golv = request.getParameter("golv");
		String minuto = request.getParameter("minuto");
		String comentario = request.getParameter("comentario");
		
		//TODO Guardar los datos.
		int goles_local = Integer.parseInt(goll);
		int goles_visitante = Integer.parseInt(golv);
		Marcador marcador_nuevo = new Marcador(goles_local, goles_visitante);
		ConsultarResultado.actualizarMarcador(marcador_nuevo);
		
		int minuto_partido = Integer.parseInt(minuto);
		Comentario comentario_nuevo = new Comentario(minuto_partido, comentario);
		ConsultarResultado.actualizarComentarios(comentario_nuevo);
		
		//Procesar la subida del fichero
		Part filePart = null;
		String nombre_fichero = null;
		filePart = request.getPart("file");
		nombre_fichero = obtenerNombreFichero(filePart);
		
		if(formatoFicheroValido(nombre_fichero)){
			try {
				guardarFichero(filePart, nombre_fichero);
				ConsultarResultado.actualizarFoto(nombre_fichero);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new ServletException();
			}
		}
		
		request.getRequestDispatcher("actualizarresultado.html").forward(request, response);
	}

}
