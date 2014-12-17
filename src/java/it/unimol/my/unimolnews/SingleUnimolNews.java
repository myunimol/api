package it.unimol.my.unimolnews;

/**
 * Questa classe rappresenta una singola news estratta dai feed RSS della
 * homepage. Ogni elemento memorizza l'autore, il link, il titolo, il contenuto
 * e la data di pubblicazione.
 *
 * @author Giuseppe Bianco
 */
public class SingleUnimolNews {

	private String title;
	private String link;
	private String description;
	private String author;
	private String publishedDate;

	/**
	 * Costruttore vuoto che non prende parametri.
	 */
	public SingleUnimolNews() {
	}

	/**
	 * Costruttore per una singola news.
	 *
	 * @param pAuthor
	 *            Autore della news
	 * @param pLink
	 *            Link della news
	 * @param pTitle
	 *            Titolo della news
	 * @param pContent
	 *            Contenuto della news
	 * @param pPublishedDate
	 *            Data di pubblicazione della news
	 */
	public SingleUnimolNews(String pAuthor, String pLink, String pTitle,
			String pContent, String pPublishedDate) {
		this.title = pTitle;
		this.link = pLink;
		this.author = pAuthor;
		this.publishedDate = pPublishedDate;
		this.description = pContent;
	}

	/**
	 * Costruttore per una singola news.
	 *
	 * @param pTitle
	 *            Titolo della news
	 * @param pLink
	 *            Link della news
	 * @param pContenuto
	 *            Contenuto della news
	 */
	public SingleUnimolNews(String pTitle, String pLink, String pContenuto) {
		this.title = pTitle;
		this.link = pLink;
		this.description = pContenuto;
	}

	/**
	 * Ritorna il titolo della news come stringa
	 *
	 * @return stringa con il titolo della news
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Ritorna il titolo della news come stringa
	 *
	 * @param title
	 *            stringa con il titolo della news
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(String publishedDate) {
		this.publishedDate = publishedDate;
	}

	public String getContent() {
		return description;
	}

	public void setContent(String content) {
		this.description = content;
	}

}
