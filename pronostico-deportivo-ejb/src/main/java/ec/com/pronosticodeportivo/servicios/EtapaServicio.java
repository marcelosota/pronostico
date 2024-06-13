/**
 * 
 */
package ec.com.pronosticodeportivo.servicios;

import ec.com.persistencia.servicio.GenericService;
import ec.com.pronosticodeportivo.modelo.Etapa;
import jakarta.ejb.Local;

/**
 * @author Marcelo
 *
 */
@Local
public interface EtapaServicio extends GenericService<Etapa, Integer> {

}
