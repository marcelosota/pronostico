package ec.com.pronosticodeportivo.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ec.com.pronosticodeportivo.modelo.Partido;
import ec.com.pronosticodeportivo.modelo.Pronostico;
import ec.com.pronosticodeportivo.modelo.Usuario;
import ec.com.pronosticodeportivo.modelo.VwPartidosPronosticosUsuario;
import ec.com.pronosticodeportivo.modelo.VwTablaPosiciones;
import ec.com.pronosticodeportivo.servicios.PartidoServicio;
import ec.com.pronosticodeportivo.servicios.PronosticoServicio;
import ec.com.pronosticodeportivo.servicios.UsuarioServicio;
import ec.com.pronosticodeportivo.servicios.VwListaPartidoServicio;
import ec.com.pronosticodeportivo.servicios.VwPartidosPronosticosUsuarioServicio;
import ec.com.pronosticodeportivo.servicios.VwTablaPosicionesServicio;
import ec.com.pronosticodeportivo.session.Sesion;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

@Named(value = "marcadorCtrl")
@ViewScoped
public class MarcadorCtrl implements Serializable {

	/**
	 * 
	 */

	@EJB
	private VwListaPartidoServicio vwListaPartidoServicio;

	@EJB
	private PartidoServicio partidoServicio;

	@EJB
	private VwTablaPosicionesServicio vwTablaPosicionesServicio;

	@EJB
	private PronosticoServicio pronosticoServicio;

	@EJB
	private UsuarioServicio usuarioServicio;

	@EJB
	private VwPartidosPronosticosUsuarioServicio vwPronosticoServicio;

	private static final long serialVersionUID = 7058602135192992714L;
	private List<VwTablaPosiciones> listaParticipantes;
	private List<Pronostico> listaPartido;
	private List<VwPartidosPronosticosUsuario> listaPronostico;

	private Pronostico partidoSeleccionado;

	private Sesion objSesion = new Sesion();

	private Usuario usuario;
	
	@PostConstruct
	public void init() {
		
		if (objSesion.getCedula() != null && objSesion.getNombre() != null) {
			usuario=usuarioServicio.buscarUsuarioPorLogin(objSesion.getCedula());

		
		listaPartido = new ArrayList<>();
		cargarPartidosIngreso();
		
		listaParticipantes = new ArrayList<>();
		cargarTablaPosiciones();
		
		listaPronostico = new ArrayList<>();
		cargarPronosticos();
			
		partidoSeleccionado = new Pronostico();
		}else {
			cerrarSesion();
		}
		
	}

	private void cargarPronosticos() {
		listaPronostico=vwPronosticoServicio.buscarPronosticoPorUsuario(objSesion.getCedula());
		
	}

	private void cargarTablaPosiciones() {
		listaParticipantes = vwTablaPosicionesServicio.findAll();
	}

	private void cargarPartidosIngreso() {
		listaPartido=new ArrayList<>();
		List<Partido> listaPartidos= partidoServicio.obtenerPartidosIngreso();
		
		for (Partido partido : listaPartidos) {
			Pronostico pronostico=pronosticoServicio.buscarPronosticoPorPartidoUsuario(partido.getPartidoId(),objSesion.getCedula());
			pronostico.setPartido(partido);
			pronostico.setUsuario(usuario);
			listaPartido.add(pronostico);
		}

	}

	public void cerrarSesion() {
		objSesion.cerrarSession();
		ExternalContext context = FacesContext.getCurrentInstance()
				.getExternalContext();
		try {
			context.redirect(context.getRequestContextPath() + "/index.jsf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String salir() {
		objSesion.cerrarSession();
		return "logout";
	}
	
	public void registroSeleccionado() {
		System.out.println(partidoSeleccionado.getPartido().getEquipo1().getSiglas());
		System.out.println(partidoSeleccionado.getPartido().getEquipo2().getSiglas());
	}

	public void guardarPronostico() {
		partidoSeleccionado.setFechaRegistro(Calendar.getInstance().getTime());
		partidoSeleccionado.setPuntaje(Short.valueOf("0"));
		pronosticoServicio.update(partidoSeleccionado);
		cargarPartidosIngreso();
		System.out.println("Partido guardado");
		partidoSeleccionado = null;
		
		
	}

	public void asignarPartido() {
		partidoSeleccionado = (Pronostico) FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.get("pronostico");
	}

	public List<VwTablaPosiciones> getListaParticipantes() {
		return listaParticipantes;
	}

	public void setListaParticipantes(List<VwTablaPosiciones> listaParticipantes) {
		this.listaParticipantes = listaParticipantes;
	}

	public List<Pronostico> getListaPartido() {
		return listaPartido;
	}

	public void setListaPartido(List<Pronostico> listaPartido) {
		this.listaPartido = listaPartido;
	}

	public List<VwPartidosPronosticosUsuario> getListaPronostico() {
		return listaPronostico;
	}

	public void setListaPronostico(List<VwPartidosPronosticosUsuario> listaPronostico) {
		this.listaPronostico = listaPronostico;
	}

	public Pronostico getPartidoSeleccionado() {
		return partidoSeleccionado;
	}

	public void setPartidoSeleccionado(Pronostico partidoSeleccionado) {
		this.partidoSeleccionado = partidoSeleccionado;
	}

	public Integer getTotalPuntos() {
		Integer res=0;
		for (VwPartidosPronosticosUsuario item : listaPronostico) {
			if(item.getPuntaje()!=null)
				res=res+(item.getPuntaje().intValue());
		}
		
		return res;
				//listaPronostico.stream().mapToInt(VwPartidosPronosticosUsuario::getPuntaje).sum();
	}


}
