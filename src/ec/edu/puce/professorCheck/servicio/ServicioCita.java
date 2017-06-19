package ec.edu.puce.professorCheck.servicio;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import ec.edu.puce.professorCheck.crud.ServicioCrud;
import ec.edu.puce.professorCheck.modelo.Cita;
import ec.edu.puce.professorCheck.modelo.MedicoPacientePK;

@Stateless(name = "servicioCita")
@LocalBean
public class ServicioCita implements Serializable {

	/**
	 * Serial generado.
	 */
	private static final long serialVersionUID = -8480675322791655829L;
	/**
	 * ServicioCrud.
	 */
	@EJB
	private ServicioCrud servicioCrud;

	/**
	 * Metodo que obtiene las citas del medico.
	 * 
	 * @param emailMedico
	 * @return citas medicas.SS
	 */
	public List<Cita> obtenerCitas(String emailMedico) {
		Cita cita = new Cita(new MedicoPacientePK(emailMedico, null), null);
		List<Cita> citas = this.servicioCrud.findOrder(cita, "fechaInicio");
		return citas;
	}

}
