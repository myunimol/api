package it.unimol.my.examsession;

public class ExamSessionEnrollementId {

	private String cdsEsaId;
	private String attDidEsaId;
	private String appId;
	private String adSceId;
	private String attDidId;
	private String tipoIscr;

	private static final String DELIMITER = "#";

	public String buildEnrollementId() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(this.cdsEsaId);
		buffer.append(DELIMITER);
		buffer.append(this.attDidEsaId);
		buffer.append(DELIMITER);
		buffer.append(this.appId);
		buffer.append(DELIMITER);
		buffer.append(this.adSceId);
		buffer.append(DELIMITER);
		buffer.append(this.attDidId);
		buffer.append(DELIMITER);
		buffer.append(this.tipoIscr);
		return buffer.toString();
	}

	/**
	 * @return the cdsEsaId
	 */
	public String getCdsEsaId() {
		return cdsEsaId;
	}

	/**
	 * @param cdsEsaId
	 *            the cdsEsaId to set
	 */
	public void setCdsEsaId(String cdsEsaId) {
		this.cdsEsaId = cdsEsaId;
	}

	/**
	 * @return the attDidEsaId
	 */
	public String getAttDidEsaId() {
		return attDidEsaId;
	}

	/**
	 * @param attDidEsaId
	 *            the attDidEsaId to set
	 */
	public void setAttDidEsaId(String attDidEsaId) {
		this.attDidEsaId = attDidEsaId;
	}

	/**
	 * @return the appId
	 */
	public String getAppId() {
		return appId;
	}

	/**
	 * @param appId
	 *            the appId to set
	 */
	public void setAppId(String appId) {
		this.appId = appId;
	}

	/**
	 * @return the adSceId
	 */
	public String getAdSceId() {
		return adSceId;
	}

	/**
	 * @param adSceId
	 *            the adSceId to set
	 */
	public void setAdSceId(String adSceId) {
		this.adSceId = adSceId;
	}

	/**
	 * @return the attDidId
	 */
	public String getAttDidId() {
		return attDidId;
	}

	/**
	 * @param attDidId
	 *            the attDidId to set
	 */
	public void setAttDidId(String attDidId) {
		this.attDidId = attDidId;
	}

	/**
	 * @return the tipoIscr
	 */
	public String getTipoIscr() {
		return tipoIscr;
	}

	/**
	 * @param tipoIscr
	 *            the tipoIscr to set
	 */
	public void setTipoIscr(String tipoIscr) {
		this.tipoIscr = tipoIscr;
	}

	public ExamSessionEnrollementId(String cdsEsaId, String attDidEsaId,
			String appId, String adSceId, String attDidId, String tipoIscr) {
		this.cdsEsaId = cdsEsaId;
		this.attDidEsaId = attDidEsaId;
		this.appId = appId;
		this.adSceId = adSceId;
		this.attDidId = attDidId;
		this.tipoIscr = tipoIscr;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ExamSessionEnrollementId [cdsEsaId=" + cdsEsaId
				+ ", attDidEsaId=" + attDidEsaId + ", appId=" + appId
				+ ", adSceId=" + adSceId + ", attDidId=" + attDidId
				+ ", tipoIscr=" + tipoIscr + "]";
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
		result = prime * result + ((adSceId == null) ? 0 : adSceId.hashCode());
		result = prime * result + ((appId == null) ? 0 : appId.hashCode());
		result = prime * result
				+ ((attDidEsaId == null) ? 0 : attDidEsaId.hashCode());
		result = prime * result
				+ ((attDidId == null) ? 0 : attDidId.hashCode());
		result = prime * result
				+ ((cdsEsaId == null) ? 0 : cdsEsaId.hashCode());
		result = prime * result
				+ ((tipoIscr == null) ? 0 : tipoIscr.hashCode());
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
		ExamSessionEnrollementId other = (ExamSessionEnrollementId) obj;
		if (adSceId == null) {
			if (other.adSceId != null)
				return false;
		} else if (!adSceId.equals(other.adSceId))
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
		if (attDidId == null) {
			if (other.attDidId != null)
				return false;
		} else if (!attDidId.equals(other.attDidId))
			return false;
		if (cdsEsaId == null) {
			if (other.cdsEsaId != null)
				return false;
		} else if (!cdsEsaId.equals(other.cdsEsaId))
			return false;
		if (tipoIscr == null) {
			if (other.tipoIscr != null)
				return false;
		} else if (!tipoIscr.equals(other.tipoIscr))
			return false;
		return true;
	}

}
