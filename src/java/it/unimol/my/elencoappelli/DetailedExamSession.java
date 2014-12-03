package it.unimol.my.elencoappelli;

import it.unimol.my.examsession.ExamSession;

import java.util.Date;

/**
 * Questa classe fornisce una astrazione dell'appello d'esame. In particolare
 * conserva le informazioni relative alla materia, alla data dell'appello, al
 * periodo in cui e' possibile iscriversi, alla descrizione dell'appello, al
 * professore, al numero di iscritti ed al numero di CFU.
 *
 * @author Giuseppe Bianco
 *
 */
public class DetailedExamSession extends ExamSession {

	/**
	 * Informazioni dell'appello
	 */
	private ExamSessionInfo info;
	/**
	 * Data ultima in cui è possibile prenotarsi
	 */
	private Date expiringDate;

	/**
	 * Il tipo della sessione (eg. Autunnale, Estiva...) dell'appello
	 */
	private String sessionType;

	/**
	 * numero di iscritti
	 */
	private String enrolled;

	/**
	 * Costruttore per la creazione di un oggetto ExamSession senza parametri.
	 */
	public DetailedExamSession() {
		super();
	}

	/**
	 * Costruttore completo per la creazione di un oggetto ExamSession. Prende
	 * in imput tutte le informazioni disponibili dalla pagina della lista degli
	 * appelli.
	 * 
	 * @param info
	 *            Le informazioni necessarie per il dettaglio dell'appello
	 * @param name
	 *            Il nome dell'esame
	 * @param date
	 *            La data dell'appello
	 * @param expiringDate
	 *            La data ultima entro la quale è consentita la prenotazione
	 * @param room
	 *            L'aula dove si tiene l'esame
	 * @param sessionType
	 *            Il tipo della sessione (eg. Autunnale, Estiva...)
	 * @param professor
	 *            I nomi dei professori
	 * @param enrolled
	 *            Il numero di prenotati all'esame
	 * @param notes
	 *            Le note dell'esame
	 * @param cfu
	 *            I CFU dell'esame
	 */
	public DetailedExamSession(ExamSessionInfo info, String name, Date date,
			Date expiringDate, String room, String sessionType,
			String professor, String enrolled, String notes, String cfu) {
		super(name, date, room, professor, notes, cfu);
		this.info = info;
		this.expiringDate = expiringDate;
		this.sessionType = sessionType;
		this.enrolled = enrolled;
	}

	public ExamSessionInfo getInfo() {
		return info;
	}

	public void setInfo(ExamSessionInfo info) {
		this.info = info;
	}

	/**
	 * Ritorna un oggetto Date che rappresenta la data ultima in cui è possibile
	 * prenotarsi all'esame
	 * 
	 * @return La data ultima in cui è possibile prenotarsi all'esame
	 */
	public Date getExpiringDate() {
		return expiringDate;
	}

	/**
	 * Prende in input un oggetto di tipo Date che rappresenta la data ultima in
	 * cui è possibile prenotarsi all'esame
	 * 
	 * @param expiringDate
	 *            Un oggetto di tipo Date che rappresenta la data ultima in cui
	 *            è possibile prenotarsi all'esame
	 */
	public void setExpiringDate(Date expiringDate) {
		this.expiringDate = expiringDate;
	}

	/**
	 * Ritorna una stringa che identifica l'anno o il tipo della sessione
	 * dell'appello
	 * 
	 * @return l'anno o il tipo della sessione dell'appello come stringa
	 */
	public String getSessionType() {
		return sessionType;
	}

	/**
	 * Metodo per impostare l'anno o il tipo della sessione dell'appello
	 * 
	 * @param sessionType
	 *            stringa che rappresenta la sessione dell'appello
	 */
	public void setSessionType(String sessionType) {
		this.sessionType = sessionType;
	}

	/**
	 * Ritorna il numero di iscritti all'appello
	 * 
	 * @return una stringa contenente il numero di iscritti all'appello
	 */
	public String getEnrolled() {
		return enrolled;
	}

	/**
	 * Serve per impostare il numero di iscritti all'appello
	 * 
	 * @param enrolled
	 *            stringa contenente il numero di iscritti all'appello
	 *            (decimale)
	 */
	public void setEnrolled(String enrolled) {
		this.enrolled = enrolled;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DetailedExamSession [info=" + info + ", expiringDate="
				+ expiringDate + ", sessionType=" + sessionType + ", enrolled="
				+ enrolled + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((enrolled == null) ? 0 : enrolled.hashCode());
		result = prime * result
				+ ((expiringDate == null) ? 0 : expiringDate.hashCode());
		result = prime * result + ((info == null) ? 0 : info.hashCode());
		result = prime * result
				+ ((sessionType == null) ? 0 : sessionType.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		DetailedExamSession other = (DetailedExamSession) obj;
		if (enrolled == null) {
			if (other.enrolled != null)
				return false;
		} else if (!enrolled.equals(other.enrolled))
			return false;
		if (expiringDate == null) {
			if (other.expiringDate != null)
				return false;
		} else if (!expiringDate.equals(other.expiringDate))
			return false;
		if (info == null) {
			if (other.info != null)
				return false;
		} else if (!info.equals(other.info))
			return false;
		if (sessionType == null) {
			if (other.sessionType != null)
				return false;
		} else if (!sessionType.equals(other.sessionType))
			return false;
		return true;
	}

}
