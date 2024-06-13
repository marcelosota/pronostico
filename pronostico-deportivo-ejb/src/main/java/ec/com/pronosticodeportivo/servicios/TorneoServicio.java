/**
 * 
 */
package ec.com.pronosticodeportivo.servicios;

import ec.com.persistencia.servicio.GenericService;
import ec.com.pronosticodeportivo.modelo.Torneo;
import jakarta.ejb.Local;

/**
 * @author Marcelo
 *
 */
@Local
public interface TorneoServicio extends GenericService<Torneo, Integer> {

}
