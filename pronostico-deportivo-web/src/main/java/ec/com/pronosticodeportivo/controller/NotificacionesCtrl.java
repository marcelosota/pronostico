package ec.com.pronosticodeportivo.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ec.com.pronosticodeportivo.dao.PartidoDao;
import ec.com.pronosticodeportivo.dao.UsuarioDao;
import ec.com.pronosticodeportivo.dao.VwPartidosPronosticosUsuarioDao;
import ec.com.pronosticodeportivo.modelo.Partido;
import ec.com.pronosticodeportivo.modelo.Usuario;
import ec.com.pronosticodeportivo.modelo.VwPartidosPronosticosUsuario;
import ec.com.pronosticodeportivo.servicios.PartidoServicio;
import ec.com.pronosticodeportivo.util.CorreoElectronico;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

@Named
@ViewScoped
public class NotificacionesCtrl implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	private VwPartidosPronosticosUsuarioDao vwPronostico;
	
	@EJB
	private UsuarioDao usuarioDao;
	
	@EJB
	private PartidoDao partidoDao;
	
	@EJB
	private PartidoServicio partidoServicio;
	
	private List<Partido> listaPartido;
	private Partido partidoSeleccionado;
	
	@PostConstruct
	protected void init() {
		
		List<Partido> listaPartidos = partidoDao.getPartidoNotificacion();
		for(Partido partido : listaPartidos) {
			partidoSeleccionado = partido;
			notificarPartido();
		}
		listaPartido = new ArrayList<>();
		listaPartido = partidoDao.getPartidosPendientes();
	}
	
	public void notificarPartido() {
		List<VwPartidosPronosticosUsuario> lista = vwPronostico.getPronosticos(partidoSeleccionado.getPartidoId());
		
		List<Integer> destinatarios = new ArrayList<>();
		StringBuilder tabla = new StringBuilder("<table style=\"border:2px solid black;border-collapse:collapse;\">");
		tabla.append("		<tr style=\"background-color:#045FB4;color:white;border:2px solid black;border-collapse:collapse;\">");
		tabla.append("			<th style=\"border:2px solid black;border-collapse:collapse;\">Participante</th>");
		tabla.append("			<th style=\"border:2px solid black;border-collapse:collapse;\">Fecha registro</th>");
		tabla.append("			<th style=\"border:2px solid black;border-collapse:collapse;\">&nbsp;&nbsp;Local&nbsp;&nbsp;</th>");
		tabla.append("			<th style=\"border:2px solid black;border-collapse:collapse;\">Pronóstico Local</th>");
		tabla.append("			<th style=\"border:2px solid black;border-collapse:collapse;\">Pronóstico Visitante</th>");
		tabla.append("			<th style=\"border:2px solid black;border-collapse:collapse;\">Visitante</th>");
		tabla.append("		</tr>");
		
		for(VwPartidosPronosticosUsuario item : lista) {
			destinatarios.add(item.getUsuarioId());
			tabla.append("<tr>");
			tabla.append("<td style=\"background-color:#F6D8CE;border:2px solid black;border-collapse:collapse;\">").append(item.getNombre()).append("</td>");
			
			if(item.getFechaRegistro() != null)
				tabla.append("<td style=\"background-color:#8BC1AC;border:2px solid black;border-collapse:collapse;text-align:center;\">").append(item.getFechaRegistro().toString()).append("</td>");
			else
				tabla.append("<td style=\"background-color:#8BC1AC;border:2px solid black;border-collapse:collapse;text-align:center;\" />");
			
			tabla.append("<td style=\"background-color:#A9BCF5;border:2px solid black;border-collapse:collapse;\">").append(item.getLocal()).append("</td>");
			
			if(item.getPronosticoLocal() != null)
				tabla.append("<td style=\"background-color:#F5F6CE;border:2px solid black;border-collapse:collapse;text-align:center;\">").append(item.getPronosticoLocal().toString());
			else
				tabla.append("<td style=\"background-color:#F5F6CE;border:2px solid black;border-collapse:collapse;text-align:center;\">");
			if(item.getPronosticoPenalesLocal() != null)
				tabla.append("<b> (").append(item.getPronosticoPenalesLocal().toString()).append(")</b>");
			tabla.append("</td>");
			
			if(item.getPronosticoVisitante() != null)
				tabla.append("<td style=\"background-color:#E3F6CE;border:2px solid black;border-collapse:collapse;text-align:center;\">").append(item.getPronosticoVisitante().toString());
			else
				tabla.append("<td style=\"background-color:#E3F6CE;border:2px solid black;border-collapse:collapse;text-align:center;\">");
			if(item.getPronosticoPenalesVisitante() != null)
				tabla.append("<b> (").append(item.getPronosticoPenalesVisitante().toString()).append(")</b>");
			tabla.append("</td>");
			tabla.append("<td style=\"background-color:#CECEF6;border:2px solid black;border-collapse:collapse;\">").append(item.getVisitante()).append("</td>");
			tabla.append("</tr>");
			
		}
		tabla.append("</table>");
		if(destinatarios.size() > 0) {
			List<Usuario> listaUsuario = usuarioDao.getUsuarios(destinatarios);
			StringBuilder to = new StringBuilder("");
			for(Usuario item : listaUsuario) {
				if(!to.toString().equals(""))
					to.append(", ");
				to.append(item.getEmail());
			}
			CorreoElectronico.enviarConGMail(to.toString(), 
					"Pronostico partido "+partidoSeleccionado.getEquipo1().getNombre()+" - "+partidoSeleccionado.getEquipo2().getNombre(), 
					tabla.toString());
			partidoSeleccionado.setNotificado(Boolean.TRUE);
			partidoServicio.update(partidoSeleccionado);
		}
		
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
