package it.unimol.my.elencoappelli;

/**
 * Questa classe fornisce una astrazione dell'appello d'esame. In particolare
 * conserva le informazioni relative alla materia, alla data dell'appello, al
 * periodo in cui e' possibile iscriversi, alla descrizione dell'appello, al
 * professore, al numero di iscritti ed al numero di CFU.
 *
 * @author Giuseppe Bianco
 *
 */
public class ExamSession {

	/**
	 * Materia dell'appello
	 */
	private String matter;
	/**
	 * Data dell'appello
	 */
	private String session;
	/**
	 * periodo in cui e' possibile iscriversi all'appello
	 */
	private String expiringDate;
	/**
	 * descrizione dell'appello
	 */
	private String description;
	/**
	 * sessione relativa all'appello
	 */
	private String sessions;
	/**
	 * professiore/i responsabili dell'esame
	 */
	private String professor;
	/**
	 * numero di iscritti
	 */
	private String subscribers;
	/**
	 * numero di CFU
	 */
	private String cfu;

	/**
	 * Costruttore completo per la creazione di un oggetto ExamSession. Prende
	 * in imput tutte le informazioni disponibili dalla pagina della lista degli
	 * appelli.
	 * 
	 * @param pMatter
	 *            materia dell'appello
	 * @param pSession
	 *            data dell'appello
	 * @param pExpiringDate
	 *            periodo in cui e' possibile iscriversi all'appello
	 * @param pDescription
	 *            descrizione dell'appello
	 * @param pSessions
	 *            sessione relativa all'appello
	 * @param pProfessor
	 *            professiore/i responsabili dell'esame
	 * @param pSubscribers
	 *            numero di iscritti
	 * @param pCfu
	 *            numero di CFU
	 */
	public ExamSession(String pMatter, String pSession, String pExpiringDate,
			String pDescription, String pSessions, String pProfessor,
			String pSubscribers, String pCfu) {
		this.matter = pMatter;
		this.session = pSession;
		this.expiringDate = pExpiringDate;
		this.description = pDescription;
		this.sessions = pSessions;
		this.professor = pProfessor;
		this.subscribers = pSubscribers;
		this.cfu = pCfu;
	}

	/**
	 * Costruttore per la creazione di un oggetto ExamSession senza parametri.
	 */
	public ExamSession() {
	}

	/**
	 * Ritorna una stringa relativa alla materia dell'appello.
	 * 
	 * @return la materia dell'appello come stringa
	 */
	public String getMatter() {
		return matter;
	}

	/**
	 * Metodo per impostare la materia relativa all'appello
	 * 
	 * @param matter
	 *            stringa che rappresenta la materia dell'appello
	 */
	public void setMatter(String matter) {
		this.matter = matter;
	}

	/**
	 * Ritorna una stringa con la data in cui si svolgera' l'esame. Il formato
	 * utilizzato da Esse3 e' GG/MM/AAAA
	 * 
	 * @return l'anno della sessione dell'appello come stringa
	 */
	public String getSession() {
		return session;
	}

	/**
	 * Metodo per impostare il giorno di svolgimento dell'esame.
	 * 
	 * @param session
	 *            stringa che rappresenta la data dell'appello. Il formato è
	 *            GG/MM/AAAA
	 */
	public void setSession(String session) {
		this.session = session;
	}

	/**
	 * Ritorna una stringa composta da due date, che rappresentano
	 * rispettivamente l'apertura e la chiusura del'iscrizione all'appello
	 * 
	 * @return stringa con il periodo valido per l'iscrizione
	 */
	public String getExpiringDate() {
		return expiringDate;
	}

	/**
	 * Prende in input una stringa che dovrà rappresentare il periodo valido per
	 * potersi iscrivere all'appello. Esse3 utilizza un'unica stringa formata da
	 * due date (ex. 16/10/2014 19/01/2015)
	 * 
	 * @param expiringDate
	 *            Singola stringa che dovrà specificare il giorno di apertura e
	 *            chiusura delle iscrizioni all'appello
	 */
	public void setExpiringDate(String expiringDate) {
		this.expiringDate = expiringDate;
	}

	/**
	 * Ritorna una stringa contenente la descrizione della sessione d'esame
	 * @return stringa contenente la descrizione della sessione d'esame
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Imposta la descrizione della sessione d'esame
	 * @param description La descrizione della sessione d'esame
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Ritorna una stringa che identifica l'anno della sessione dell'appello
	 * 
	 * @return l'anno della sessione dell'appello come stringa
	 */
	public String getSessions() {
		return sessions;
	}

	/**
	 * Metodo per impostare l'anno della sessione dell'appello
	 * 
	 * @param sessions
	 *            stringa che rappresent la sessione dell'appello
	 */
	public void setSessions(String sessions) {
		this.sessions = sessions;
	}

	public String getProfessor() {
		return professor;
	}

	public void setProfessor(String professor) {
		this.professor = professor;
	}

	/**
	 * Ritorna il numero di iscritti all'appello
	 * 
	 * @return una stringa contenente il numero di iscritti all'appello
	 */
	public String getSubscribers() {
		return subscribers;
	}

	/**
	 * Serve per impostare il numero di iscritti all'appello
	 * 
	 * @param subscribers
	 *            stringa contenente il numero di iscritti all'appello
	 *            (decimale)
	 */
	public void setSubscribers(String subscribers) {
		this.subscribers = subscribers;
	}

	public String getCfu() {
		return cfu;
	}

	public void setCfu(String cfu) {
		this.cfu = cfu;
	}

}
