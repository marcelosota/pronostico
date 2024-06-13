/**
 * 
 */
package ec.com.pronosticodeportivo.servicios;

import java.util.List;

import ec.com.persistencia.servicio.GenericService;
import ec.com.pronosticodeportivo.modelo.Partido;
import jakarta.ejb.Local;

/**
 * @author Marcelo
 *
 */
@Local
public interface PartidoServicio extends GenericService<Partido, Integer> {

	List<Partido> obtenerPartidosIngreso();

}
