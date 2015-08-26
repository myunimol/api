package it.unimol.my.news;

import java.security.InvalidParameterException;

import it.unimol.my.news.extractors.AgrariaNewsExtractor;
import it.unimol.my.news.extractors.BiologiaMasterNewsExtractor;
import it.unimol.my.news.extractors.BiologiaNewsExtractor;
import it.unimol.my.news.extractors.EconomiaNewsExtractor;
import it.unimol.my.news.extractors.GiurisprudenzaNewsExtractor;
import it.unimol.my.news.extractors.InformaticaNewsExtractor;
import it.unimol.my.news.extractors.IngegneriaCivileMasterNewsExtractor;
import it.unimol.my.news.extractors.IngegneriaCivileNewsExtractor;
import it.unimol.my.news.extractors.IngegneriaEdileNewsExtractor;
import it.unimol.my.news.extractors.LettereNewsExtractor;
import it.unimol.my.news.extractors.MedicinaNewsExtractor;
import it.unimol.my.news.extractors.MerendeNewsExtractor;
import it.unimol.my.news.extractors.ScienzeFormazionePrimariaNewsExtractor;
import it.unimol.my.news.extractors.ScienzePoliticheIstituzioniEuropeeNewsExtractor;
import it.unimol.my.news.extractors.ScienzePoliticheNewsExtractor;
import it.unimol.my.news.extractors.ScienzeServizioSocialeNewsExtractor;
import it.unimol.my.news.extractors.ScienzeTecnologieAlimentariNewsExtractor;
import it.unimol.my.news.extractors.ScienzeTuristicheNewsExtractor;
import it.unimol.my.news.extractors.ServizioSocialePoliticheSocialiNewsExtractor;
import it.unimol.my.news.extractors.TecnologieForestaliAmbientaliNewsExtractor;
import it.unimol.my.news.extractors.TurismoBeniCulturaliNewsExtractor;

public class NewsBoardBridge {
	public static NewsBoardBridge bridge;
	
	public static NewsBoardBridge getInstance() {
		if (bridge == null)
			bridge = new NewsBoardBridge();
		
		return bridge;
	}
	
	private NewsBoardBridge() {
	}
	
	public NewsExtractorInterface getNewsExtractor(String newsPage) throws InvalidParameterException {
		
		switch (newsPage) {
		case "informatica":
			return new InformaticaNewsExtractor();
		case "scienzeBiologiche":
			return new BiologiaNewsExtractor();
		case "medicina":
			return new MedicinaNewsExtractor();
		case "economia":
			return new EconomiaNewsExtractor();
		case "scienzePolitiche":
			return new ScienzePoliticheNewsExtractor();
		case "scienzeServizioSociale":
			return new ScienzeServizioSocialeNewsExtractor();
		case "scienzePoliticheIstituzioniEuropee":
			return new ScienzePoliticheIstituzioniEuropeeNewsExtractor();
		case "servizioSocialePoliticheSociali":
			return new ServizioSocialePoliticheSocialiNewsExtractor();
		case "giurisprudenza":
			return new GiurisprudenzaNewsExtractor();
		case "scienzeTecnologieAlimentari":
			return new ScienzeTecnologieAlimentariNewsExtractor();
		case "agraria":
			return new AgrariaNewsExtractor();
		case "tecnologieForestaliAmbientali":
			return new TecnologieForestaliAmbientaliNewsExtractor();
		case "lettere":
			return new LettereNewsExtractor();
		case "comunicazione":
			return new MerendeNewsExtractor();
		case "scienzeFormazionePrimaria":
			return new ScienzeFormazionePrimariaNewsExtractor();
		case "ingEdile":
			return new IngegneriaEdileNewsExtractor();
		case "ingCivile":
			return new IngegneriaCivileNewsExtractor();
		case "scienzeTuristiche":
			return new ScienzeTuristicheNewsExtractor();
		case "scienzeBiologicheMaster":
			return new BiologiaMasterNewsExtractor();
		case "ingCivileMaster":
			return new IngegneriaCivileMasterNewsExtractor();
		case "turismoBeniCulturali":
			return new TurismoBeniCulturaliNewsExtractor();

		default:
			throw new InvalidParameterException(newsPage + " unrecognized");
		}
	}
}
