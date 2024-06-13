/**
 * 
 */
package ec.com.pronosticodeportivo.dao;

import java.util.List;

import ec.com.persistencia.dao.GenericDao;
import ec.com.pronosticodeportivo.modelo.Partido;
import ec.com.pronosticodeportivo.modelo.Pronostico;
import jakarta.ejb.Local;

/**
 * @author Marcelo
 *
 */
@Local
public interface PronosticoDao extends GenericDao<Pronostico, Integer> {

	List<Pronostico> obtenerPronosticosIngreso(String cedula);

	Pronostico buscarPronosticoPorPartidoUsuario(Integer partidoId, String cedula);

	void actualizarPuntajesPronosticos(Partido partidoSeleccionado);

}
