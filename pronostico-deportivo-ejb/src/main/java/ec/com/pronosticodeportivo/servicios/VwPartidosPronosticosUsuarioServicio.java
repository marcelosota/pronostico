/**
 * 
 */
package ec.com.pronosticodeportivo.servicios;

import java.util.List;

import ec.com.persistencia.servicio.GenericService;
import ec.com.pronosticodeportivo.modelo.VwPartidosPronosticosUsuario;
import jakarta.ejb.Local;

/**
 * @author Marcelo
 *
 */
@Local
public interface VwPartidosPronosticosUsuarioServicio extends GenericService<VwPartidosPronosticosUsuario, Integer> {

	List<VwPartidosPronosticosUsuario> buscarPronosticoPorUsuario(String cedula);

}
