package it.unimol.my.utils;

public class StringUtils {

    /**
     * In questo metodo, una stringa contenente non breaking space (che è
     * diverso dal whitespace) viene trimmata all'inizio e alla fine.
     *
     * @param untrimmed stringa non trimmata
     * @return restituisce la stringa trimmata
     */
    public static String realTrim(String untrimmed) {
        String trimmed = untrimmed.replace(String.valueOf((char) 160), " ").trim();
        return trimmed;
    }

}
