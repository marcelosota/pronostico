package ec.com.pronosticodeportivo.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ec.com.pronosticodeportivo.dao.PartidoDao;
import ec.com.pronosticodeportivo.modelo.Partido;
import ec.com.pronosticodeportivo.servicios.PartidoServicio;
import ec.com.pronosticodeportivo.servicios.PronosticoServicio;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

@Named("resultadosCtrl")
@ViewScoped
public class ResultadosCtrl implements Serializable {

	/**
	 * 
	 */
	
	@EJB
	private PartidoDao partidoDao;
	
	@EJB
	private PartidoServicio partidoServicio;

	@EJB
	private PronosticoServicio pronosticoServicio;

	private static final long serialVersionUID = 7058602135192992714L;
	private List<Partido> listaPartido;

	private Partido partidoSeleccionado;

	@PostConstruct
	public void init() {
		establecerTablas();
		
		partidoSeleccionado = new Partido();

	}
	private void establecerTablas() {
		listaPartido = new ArrayList<>();
		listaPartido = partidoDao.getPartidosPendientes();
		
	}
	public void registroSeleccionado() {
		System.out.println(partidoSeleccionado.getEquipo1().getSiglas());
		System.out.println(partidoSeleccionado.getEquipo2().getSiglas());
	}
	
	public void guardarResultado() {
		System.out.println("Partido guardado");
		if(partidoSeleccionado.getMarcadorLocal() != null && partidoSeleccionado.getMarcadorVisitante() != null 
			&& partidoSeleccionado.getMarcadorLocal() >= 0 && partidoSeleccionado.getMarcadorVisitante() >= 0) {
			partidoServicio.update(partidoSeleccionado);
			pronosticoServicio.actualizarPuntajesPronosticos(partidoSeleccionado);
		}

		partidoSeleccionado = null;
	}

	public List<Partido> getListaPartido() {
		return listaPartido;
	}

	public void setListaPartido(List<Partido> listaPartido) {
		this.listaPartido = listaPartido;
	}

	public Partido getPartidoSeleccionado() {
		return partidoSeleccionado;
	}

	public void setPartidoSeleccionado(Partido partidoSeleccionado) {
		this.partidoSeleccionado = partidoSeleccionado;
	}

}
