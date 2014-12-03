package it.unimol.my.token;

/**
 * Classe bean che rappresenta un token
 * 
 * @author Ivan Di Rienzo
 */
public class Token {
	/**
	 * Il token
	 */
	private String token;
	/**
	 * a chi corrisponde il token
	 */
	private String owner;
	/**
	 * se il token &egrave; attualmente valido o meno
	 */
	private boolean valid;

	/**
	 * Costruttore che modella un token, imposta di default la validit&agrave; a
	 * true
	 * 
	 * @param token
	 *            il token da utilizzare
	 * @param owner
	 *            il proprietario del token
	 * 
	 */
	public Token(String token, String owner) {
		this.token = token;
		this.owner = owner;
		this.valid = true;
	}

	// GETTER E SETTER
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

}
