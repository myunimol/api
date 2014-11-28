/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.unimol.my.elencoappelli;

import java.util.List;

/**
 *
 * @author Giuseppe
 */
public interface ExamSessionsExtractorInterface {

    /**
     * Metodo che permette di fare parsing di una pagina protetta da login
     * @param targetURL URL della pagina su cui eseguire l'estrazione
     * @param username 
     * @param password
     * @return
     */
    public List<ExamSession> getExamSessions(String targetURL, String username, String password);
    
}
