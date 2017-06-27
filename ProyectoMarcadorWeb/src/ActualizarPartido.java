

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ActualizarPartido
 */
@WebServlet("/ActualizarPartido")
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
		
		request.getRequestDispatcher("actualizarresultado.html").forward(request, response);
	}

}
