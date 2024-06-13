/**
 * 
 */
package ec.com.pronosticodeportivo.servicios;

import ec.com.persistencia.servicio.GenericService;
import ec.com.pronosticodeportivo.modelo.VwListaPartido;
import jakarta.ejb.Local;

/**
 * @author Marcelo
 *
 */
@Local
public interface VwListaPartidoServicio extends GenericService<VwListaPartido, Integer> {

}
