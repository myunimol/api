package it.unimol.my.examsession;

/**
 *
 * @author Giuseppe Bianco
 */
public class ExamSessionInfo {

	private String action;
	private String appId;
	private String cdsEsaId;
	private String attDidEsaId;
	private String adsceId;
	private String aaOffId;
	private String cdsId;
	private String pdsId;
	private String aaOrdId;
	private String iscrAperta;
	private String tipoAttivita;

	private static final String DELIMITER = "#";

	public ExamSessionInfo() {
	}

	public ExamSessionInfo(String pAction, String pAppId, String pCdsEsaId,
			String pAttDidEsaId, String pAdsceId, String pAaOffId,
			String pCdsId, String pPdsId, String pAaOrdId, String pIscrAperta,
			String pTipoAttivita) {
		this.action = pAction;
		this.appId = pAppId;
		this.cdsEsaId = pCdsEsaId;
		this.attDidEsaId = pAttDidEsaId;
		this.adsceId = pAdsceId;
		this.aaOffId = pAaOffId;
		this.cdsId = pCdsId;
		this.pdsId = pPdsId;
		this.aaOrdId = pAaOrdId;
		this.iscrAperta = pIscrAperta;
		this.tipoAttivita = pTipoAttivita;
	}

	// public String buildExamSessionId() {
	// StringBuffer id = new StringBuffer();
	// id.append(this.cdsEsaId);
	// id.append(DELIMITER);
	// id.append(this.attDidEsaId);
	// id.append(DELIMITER);
	// id.append(this.adsceId);
	// id.append(DELIMITER);
	// id.append(aaOffId);
	// id.append(DELIMITER);
	// id.append(cdsId);
	// id.append(DELIMITER);
	// id.append(this.pdsId);
	// id.append(DELIMITER);
	// id.append(this.aaOrdId);
	// id.append(DELIMITER);
	// id.append(this.iscrAperta);
	// id.append(DELIMITER);
	// id.append(this.tipoAttivita);
	// id.append(DELIMITER);
	// id.append(this.tipoAppCod);
	// return id.toString();
	// }

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getCdsEsaId() {
		return cdsEsaId;
	}

	public void setCdsEsaId(String cdsEsaId) {
		this.cdsEsaId = cdsEsaId;
	}

	public String getAttDidEsaId() {
		return attDidEsaId;
	}

	public void setAttDidEsaId(String attDidEsaId) {
		this.attDidEsaId = attDidEsaId;
	}

	public String getAdsceId() {
		return adsceId;
	}

	public void setAdsceId(String adsceId) {
		this.adsceId = adsceId;
	}

	public String getAaOffId() {
		return aaOffId;
	}

	public void setAaOffId(String aaOffId) {
		this.aaOffId = aaOffId;
	}

	public String getCdsId() {
		return cdsId;
	}

	public void setCdsId(String cdsId) {
		this.cdsId = cdsId;
	}

	public String getPdsId() {
		return pdsId;
	}

	public void setPdsId(String pdsId) {
		this.pdsId = pdsId;
	}

	public String getAaOrdId() {
		return aaOrdId;
	}

	public void setAaOrdId(String aaOrdId) {
		this.aaOrdId = aaOrdId;
	}

	public String getIscrAperta() {
		return iscrAperta;
	}

	public void setIscrAperta(String iscrAperta) {
		this.iscrAperta = iscrAperta;
	}

	public String getTipoAttivita() {
		return tipoAttivita;
	}

	public void setTipoAttivita(String tipoAttivita) {
		this.tipoAttivita = tipoAttivita;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ExamSessionInfo [action=" + action + ", appId=" + appId
				+ ", cdsEsaId=" + cdsEsaId + ", attDidEsaId=" + attDidEsaId
				+ ", adsceId=" + adsceId + ", aaOffId=" + aaOffId + ", cdsId="
				+ cdsId + ", pdsId=" + pdsId + ", aaOrdId=" + aaOrdId
				+ ", iscrAperta=" + iscrAperta + ", tipoAttivita="
				+ tipoAttivita + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aaOffId == null) ? 0 : aaOffId.hashCode());
		result = prime * result + ((aaOrdId == null) ? 0 : aaOrdId.hashCode());
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		result = prime * result + ((adsceId == null) ? 0 : adsceId.hashCode());
		result = prime * result + ((appId == null) ? 0 : appId.hashCode());
		result = prime * result
				+ ((attDidEsaId == null) ? 0 : attDidEsaId.hashCode());
		result = prime * result
				+ ((cdsEsaId == null) ? 0 : cdsEsaId.hashCode());
		result = prime * result + ((cdsId == null) ? 0 : cdsId.hashCode());
		result = prime * result
				+ ((iscrAperta == null) ? 0 : iscrAperta.hashCode());
		result = prime * result + ((pdsId == null) ? 0 : pdsId.hashCode());
		result = prime * result
				+ ((tipoAttivita == null) ? 0 : tipoAttivita.hashCode());
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
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExamSessionInfo other = (ExamSessionInfo) obj;
		if (aaOffId == null) {
			if (other.aaOffId != null)
				return false;
		} else if (!aaOffId.equals(other.aaOffId))
			return false;
		if (aaOrdId == null) {
			if (other.aaOrdId != null)
				return false;
		} else if (!aaOrdId.equals(other.aaOrdId))
			return false;
		if (action == null) {
			if (other.action != null)
				return false;
		} else if (!action.equals(other.action))
			return false;
		if (adsceId == null) {
			if (other.adsceId != null)
				return false;
		} else if (!adsceId.equals(other.adsceId))
			return false;
		if (appId == null) {
			if (other.appId != null)
				return false;
		} else if (!appId.equals(other.appId))
			return false;
		if (attDidEsaId == null) {
			if (other.attDidEsaId != null)
				return false;
		} else if (!attDidEsaId.equals(other.attDidEsaId))
			return false;
		if (cdsEsaId == null) {
			if (other.cdsEsaId != null)
				return false;
		} else if (!cdsEsaId.equals(other.cdsEsaId))
			return false;
		if (cdsId == null) {
			if (other.cdsId != null)
				return false;
		} else if (!cdsId.equals(other.cdsId))
			return false;
		if (iscrAperta == null) {
			if (other.iscrAperta != null)
				return false;
		} else if (!iscrAperta.equals(other.iscrAperta))
			return false;
		if (pdsId == null) {
			if (other.pdsId != null)
				return false;
		} else if (!pdsId.equals(other.pdsId))
			return false;
		if (tipoAttivita == null) {
			if (other.tipoAttivita != null)
				return false;
		} else if (!tipoAttivita.equals(other.tipoAttivita))
			return false;
		return true;
	}

}
