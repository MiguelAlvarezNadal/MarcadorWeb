import java.util.List;

public class InfoPartido {
	//BEAN/POJO(Plain Old Java Object)
	private Marcador marcador;
	private List<Comentario> listacomentatios;
	
	public Marcador getMarcador() {
		return marcador;
	}
	public void setMarcador(Marcador marcador) {
		this.marcador = marcador;
	}
	public List<Comentario> getListacomentatios() {
		return listacomentatios;
	}
	public void setListacomentatios(List<Comentario> listacomentatios) {
		this.listacomentatios = listacomentatios;
	}
	
	public InfoPartido(Marcador marcador, List<Comentario> listacomentatios) {
		super();
		this.marcador = marcador;
		this.listacomentatios = listacomentatios;
	}
}
