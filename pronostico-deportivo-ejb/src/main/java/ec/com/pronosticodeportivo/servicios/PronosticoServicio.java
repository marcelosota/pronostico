/**
 * 
 */
package ec.com.pronosticodeportivo.servicios;

import java.util.List;

import ec.com.persistencia.servicio.GenericService;
import ec.com.pronosticodeportivo.modelo.Partido;
import ec.com.pronosticodeportivo.modelo.Pronostico;
import jakarta.ejb.Local;

/**
 * @author Marcelo
 *
 */
@Local
public interface PronosticoServicio extends GenericService<Pronostico, Integer> {

	List<Pronostico> obtenerPronosticosIngreso(String cedula);

	Pronostico buscarPronosticoPorPartidoUsuario(Integer partidoId, String cedula);

	void actualizarPuntajesPronosticos(Partido partidoSeleccionado);

}
