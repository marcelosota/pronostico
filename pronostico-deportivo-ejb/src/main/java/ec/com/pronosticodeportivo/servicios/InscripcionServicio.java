/**
 * 
 */
package ec.com.pronosticodeportivo.servicios;

import ec.com.persistencia.servicio.GenericService;
import ec.com.pronosticodeportivo.modelo.Inscripcion;
import jakarta.ejb.Local;

/**
 * @author Marcelo
 *
 */
@Local
public interface InscripcionServicio extends GenericService<Inscripcion, Integer> {

}
