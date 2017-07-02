package ec.edu.puce.professorCheck.ctrl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;

import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import ec.edu.puce.professorCheck.modelo.Cita;
import ec.edu.puce.professorCheck.servicio.ServicioCita;

@ManagedBean(name = "citaCtrl")
@SessionScoped
public class CitaCtrl extends BaseCtrl implements Serializable {

	/**
	 * Serial generado.
	 */
	private static final long serialVersionUID = -1413279876098670294L;
	/**
	 * ServicioCita.
	 */
	@EJB
	private ServicioCita servicioCita;
	/**
	 * Agenda de medico.
	 */
	private ScheduleModel agendaEventModel;
	/**
	 * Evento para agenda.
	 */
	private ScheduleEvent event = new DefaultScheduleEvent();
	/**
	 * Citas de medico.
	 */
	private List<Cita> citas;

	@PostConstruct
	public void postConstructor() {
		this.agendaEventModel = new DefaultScheduleModel();
		this.obtenerAgenda();
	}

	public void obtenerAgenda() {
		this.citas = this.servicioCita.obtenerCitas(super.getRemoteUser());
		for (Cita cita : this.citas) {
			this.agendaEventModel.addEvent(new DefaultScheduleEvent(cita
					.getDescripcion(), cita.getFechaInicio(), cita
					.getFechaFin()));
		}
	}

	public void onDateSelect(SelectEvent selectEvent) {
		this.event = new DefaultScheduleEvent("",
				(Date) selectEvent.getObject(), (Date) selectEvent.getObject());
	}

	public void onEventSelect(SelectEvent selectEvent) {
		this.event = (ScheduleEvent) selectEvent.getObject();
	}

	public void onEventMove(ScheduleEntryMoveEvent event) {
		super.addInfoMessage("Event moved", "Day delta:" + event.getDayDelta()
				+ ", Minute delta:" + event.getMinuteDelta());
	}

	public void onEventResize(ScheduleEntryResizeEvent event) {
		super.addInfoMessage(
				"Event resized",
				"Day delta:" + event.getDayDelta() + ", Minute delta:"
						+ event.getMinuteDelta());
	}

	public void addEvent(ActionEvent actionEvent) {
		if (event.getId() == null)
			this.agendaEventModel.addEvent(event);
		else
			this.agendaEventModel.updateEvent(event);

		event = new DefaultScheduleEvent();
	}

	public ScheduleModel getAgendaEventModel() {
		return agendaEventModel;
	}

	public void setAgendaEventModel(ScheduleModel agendaEventModel) {
		this.agendaEventModel = agendaEventModel;
	}

	public ScheduleEvent getEvent() {
		return event;
	}

	public void setEvent(ScheduleEvent event) {
		this.event = event;
	}

}
