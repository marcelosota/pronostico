/**
 * 
 */
package ec.com.pronosticodeportivo.servicios;

import ec.com.persistencia.servicio.GenericService;
import ec.com.pronosticodeportivo.modelo.Equipo;
import jakarta.ejb.Local;

/**
 * @author Marcelo
 *
 */
@Local
public interface EquipoServicio extends GenericService<Equipo, Integer> {

}
