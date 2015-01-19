package it.unimol.my.exam;

/**
 * Inserire descrizione classe
 *
 * @author Matteo Ianno
 */
public class Details {

	/**
	 * Descrizione variabile
	 */
	private String name;

	/**
	 * Descrizione variabile
	 */
	private int cfu;

	/**
	 * Descrizione variabile
	 */
	private int hours;

	/**
	 * Descrizione variabile
	 */
	private String area;

	/**
	 * Descrizione costruttore
	 *
	 * @param moduleName
	 * @param cfu
	 * @param hours
	 * @param area
	 */
	public Details(String moduleName, int cfu, int hours, String area) {
		this.name = moduleName;
		this.cfu = cfu;
		this.hours = hours;
		this.area = area;
	}

	/**
	 * Descrizione costruttore
	 */
	public Details() {

	}

	/**
	 * @return the module's name
	 */
	public String getModuleName() {
		return name;
	}

	/**
	 * @param moduleName
	 *            the module's name to set
	 */
	public void setModuleName(String moduleName) {
		this.name = moduleName;
	}

	/**
	 * @return the cfu
	 */
	public int getCfu() {
		return cfu;
	}

	/**
	 * @param cfu
	 *            the cfu to set
	 */
	public void setCfu(int cfu) {
		this.cfu = cfu;
	}

	/**
	 * @return the hours
	 */
	public int getHours() {
		return hours;
	}

	/**
	 * @param hours
	 *            the hours to set
	 */
	public void setHours(int hours) {
		this.hours = hours;
	}

	/**
	 * @return the area
	 */
	public String getArea() {
		return area;
	}

	/**
	 * @param area
	 *            the area to set
	 */
	public void setArea(String area) {
		this.area = area;
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
		result = prime * result + ((area == null) ? 0 : area.hashCode());
		result = prime * result + cfu;
		result = prime * result + hours;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Details other = (Details) obj;
		if (area == null) {
			if (other.area != null)
				return false;
		} else if (!area.equals(other.area))
			return false;
		if (cfu != other.cfu)
			return false;
		if (hours != other.hours)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Details{" + "moduleName=" + name + ", cfu=" + cfu + ", hours="
				+ hours + ", area=" + area + '}';
	}

}
