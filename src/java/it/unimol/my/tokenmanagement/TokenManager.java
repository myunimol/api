package it.unimol.my.tokenmanagement;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Classe (SINGLETON) che gestisce l'accesso ai token presenti nel file
 * tokens.json
 * 
 * TODO metodi per modifica, cancellazione ed inserimento tokens
 *
 * @author Ivan Di Rienzo
 */
public class TokenManager {

	private static TokenManager INSTANCE;

	private List<Token> tokenList;
	/**
	 * come variabile di istanza per non ricrearlo ogni volta che viene fatto
	 * reload
	 */
	private Gson gson;

	/**
	 * Design pattern singleton, costruttore privato
	 * 
	 * Inizializza variabile di istanza e carica per la prima volta i token
	 * all'interno della lista
	 */
	private TokenManager() {
		gson = new Gson();
		// carica il file all'interno della lista per la prima
		// volta
		this.reload();
	}

	/**
	 * Restituisce l'istanza di TokenManager se non esiste la crea
	 * 
	 * @return l'istanza di TokenManager
	 */
	public static TokenManager getInstance() {
		if (TokenManager.INSTANCE == null) {
			TokenManager.INSTANCE = new TokenManager();
		}
		return TokenManager.INSTANCE;
	}

	/**
	 * Funzione che legge il file dei token "tokens.json"
	 * 
	 * @return restituisce il contenuto del file
	 * @throws IOException
	 *             Errore nell'accesso al file
	 */
	private String fileReader() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(
				TokenManager.class.getResourceAsStream("tokens.json")));
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append("\n");
				line = br.readLine();
			}
			return sb.toString();
		} finally {
			br.close();
		}
	}

	/**
	 * Ricarica i token presenti nel file "tokens.json" all'interno della lista
	 */
	public void reload() {
		try {
			Type listType = new TypeToken<ArrayList<Token>>() {
			}.getType(); // Ottengo il tipo necessario per gson

			this.tokenList = gson.fromJson(this.fileReader(), listType);

		} catch (IOException ex) {
			ex.printStackTrace();
			// TODO gestire eccezione
		}
	}

	/**
	 * Controlla se un token esiste ed &egrave; valido
	 * 
	 * @param token
	 *            il token da controllare
	 * @return TRUE se il token esiste ed &egrave; valido FALSE altrimenti
	 */
	public boolean tokenExists(String token) {

		Iterator<Token> listIterator = this.tokenList.iterator();
		Token tk;

		while (listIterator.hasNext()) {
			tk = listIterator.next();
			if (tk.getToken().equals(token) && tk.isValid()) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Permette di ottenere l'istanza di Token corrispondente (se esiste)
	 * 
	 * @param token
	 *            il token
	 * @return l'istanza della classe bean corrispondente al token, NULL se il
	 *         token non &egrave; valido
	 */

	public Token getToken(String token) {
		Iterator<Token> listIterator = this.tokenList.iterator();
		Token tk;

		while (listIterator.hasNext()) {
			tk = listIterator.next();
			if (tk.getToken().equals(token)) {
				return tk;
			}
		}

		return null;
	}

}
