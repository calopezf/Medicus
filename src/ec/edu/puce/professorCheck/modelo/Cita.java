package ec.edu.puce.professorCheck.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ec.edu.puce.professorCheck.constantes.EnumEstado;

@Entity
@Table(name = "CITA")
public class Cita implements Serializable {

	/**
	 * Serial generado.
	 */
	private static final long serialVersionUID = -8190230753457555893L;

	@EmbeddedId
	private MedicoPacientePK pk;
	/**
	 * Fecha Inicio de la cita.
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaInicio;
	/**
	 * Fecha Fin de la cita.
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaFin;
	/**
	 * Propiedad que representa a la columna estado de la cita.
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "estado", length = 3)
	private EnumEstado estado;
	/**
	 * Propiedad que representa a la columna descripcion;
	 */
	private String descripcion;

	public Cita() {
	}

	public Cita(MedicoPacientePK pk, EnumEstado estado) {
		this.pk = pk;
		this.estado = estado;
	}

	public MedicoPacientePK getPk() {
		return pk;
	}

	public void setPk(MedicoPacientePK pk) {
		this.pk = pk;
	}

	public EnumEstado getEstado() {
		return estado;
	}

	public void setEstado(EnumEstado estado) {
		this.estado = estado;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
