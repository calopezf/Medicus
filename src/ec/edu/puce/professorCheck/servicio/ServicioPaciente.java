package ec.edu.puce.professorCheck.servicio;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;

import ec.edu.puce.professorCheck.crud.ServicioCrud;
import ec.edu.puce.professorCheck.modelo.Usuario;

@Stateless(name = "servicioPaciente")
@LocalBean
public class ServicioPaciente implements Serializable {

	/**
	 * Serial generado.
	 */
	private static final long serialVersionUID = 1909874650020936123L;

	@EJB
	private ServicioCrud servicioCrud;

	@SuppressWarnings("unchecked")
	public List<Usuario> buscarPacientesActivos(String filtroNombres,
			String filtroIdentificacion) {
		StringBuilder sql = new StringBuilder(
				"select paciente from Usuario medico, Usuario paciente, MedicoPaciente mp \n");
		sql.append("where medico.email = mp.pk.emailMedico \n");
		sql.append("and paciente.email = mp.pk.emailPaciente \n");
		sql.append("and mp.estado = 'ACT' \n");
		if (StringUtils.isNotBlank(filtroNombres)) {
			sql.append("and upper(paciente.nombre ||' '|| paciente.apellido) like ?1 \n");
		}
		if (StringUtils.isNotBlank(filtroIdentificacion)) {
			sql.append("and paciente.identificacion = ?2 \n");
		}
		Query query = servicioCrud.getEntityManager().createQuery(
				sql.toString());
		if (StringUtils.isNotBlank(filtroNombres)) {
			query.setParameter(1, "%" + filtroNombres.toUpperCase() + "%");
		}
		if (StringUtils.isNotBlank(filtroIdentificacion)) {
			query.setParameter(2, filtroIdentificacion);
		}
		return query.getResultList();
	}

}
