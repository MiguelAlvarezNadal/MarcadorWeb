
public class Marcador {

	private int goles_local;
	private int goles_visitante;
	
	public int getGoles_local() {
		return goles_local;
	}
	public void setGoles_local(int goles_local) {
		this.goles_local = goles_local;
	}
	public int getGoles_visitante() {
		return goles_visitante;
	}
	public void setGoles_visitante(int goles_visitante) {
		this.goles_visitante = goles_visitante;
	}
	
	public Marcador(){
		
	}
	public Marcador(int goles_local, int goles_visitante) {
		super();
		this.goles_local = goles_local;
		this.goles_visitante = goles_visitante;
	}
	
	
}
