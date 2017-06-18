package ec.edu.puce.professorCheck.ctrl.negocio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.model.DefaultStreamedContent;

import ec.edu.puce.professorCheck.constantes.EnumEstado;
import ec.edu.puce.professorCheck.crud.ServicioCrud;
import ec.edu.puce.professorCheck.ctrl.BaseCtrl;
import ec.edu.puce.professorCheck.modelo.MedicoPaciente;
import ec.edu.puce.professorCheck.modelo.MedicoPacientePK;
import ec.edu.puce.professorCheck.modelo.Usuario;
import ec.edu.puce.professorCheck.servicio.ServicioPaciente;

@ManagedBean(name = "pacienteCtrl")
@ViewScoped
public class PacienteCtrl extends BaseCtrl implements Serializable {

	/**
	 * Serial generado.
	 */
	private static final long serialVersionUID = 5044703336430846868L;
	/**
	 * ServicioCrud.
	 */
	@EJB
	private ServicioCrud servicioCrud;
	/**
	 * ServicioPAciente.
	 */
	@EJB
	private ServicioPaciente servicioPaciente;
	/**
	 * Pacientes del medico actual.
	 */
	private List<Usuario> pacientes;
	private String filtroNombres;
	private String filtroIdentificacion;

	@PostConstruct
	public void postConstructor() {
		this.pacientes = new ArrayList<Usuario>();
		this.buscarPacientes(null);
	}

	public List<Usuario> getPacientes() {
		return pacientes;
	}

	public void setPacientes(List<Usuario> pacientes) {
		this.pacientes = pacientes;
	}

	public void buscarPacientes(ActionEvent event) {
		this.pacientes.clear();
		this.pacientes.addAll(this.servicioPaciente.buscarPacientesActivos(
				filtroNombres, filtroIdentificacion));
		for (Usuario paciente : this.pacientes) {
			File foto = new File(paciente.getFoto());
			try {
				paciente.setFotoTransient(new DefaultStreamedContent(
						new FileInputStream(foto), "image/jpg"));
			} catch (FileNotFoundException e) {
				paciente.setFotoTransient(null);
			}
		}
	}

	public String getFiltroNombres() {
		return filtroNombres;
	}

	public void setFiltroNombres(String filtroNombres) {
		this.filtroNombres = filtroNombres;
	}

	public String getFiltroIdentificacion() {
		return filtroIdentificacion;
	}

	public void setFiltroIdentificacion(String filtroIdentificacion) {
		this.filtroIdentificacion = filtroIdentificacion;
	}

}
