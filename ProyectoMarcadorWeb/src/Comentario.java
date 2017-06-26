
public class Comentario {

	private int minuto;
	private String comentario;
	
	public Comentario() {
		
	}
	public Comentario(int minuto, String comentario) {
		super();
		this.minuto = minuto;
		this.comentario = comentario;
	}
	
	public int getMinuto() {
		return minuto;
	}
	public void setMinuto(int minuto) {
		this.minuto = minuto;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	
	
}
