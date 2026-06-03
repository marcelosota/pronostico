/**
 * 
 */
package ec.com.pronosticodeportivo.servicios;

import java.math.BigDecimal;

import ec.com.persistencia.servicio.GenericService;
import ec.com.pronosticodeportivo.modelo.Etapa;
import jakarta.ejb.Local;

/**
 * @author Marcelo
 *
 */
@Local
public interface EtapaServicio extends GenericService<Etapa, Integer> {

	public BigDecimal getInscripcion();
}
