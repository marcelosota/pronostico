/**
 * 
 */
package ec.com.pronosticodeportivo.servicios;

import ec.com.persistencia.servicio.GenericService;
import ec.com.pronosticodeportivo.modelo.VwTablaPosiciones;
import jakarta.ejb.Local;

/**
 * @author Marcelo
 *
 */
@Local
public interface VwTablaPosicionesServicio extends GenericService<VwTablaPosiciones, Integer> {

}
