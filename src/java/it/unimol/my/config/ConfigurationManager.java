package it.unimol.my.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ConfigurationManager {

    private JsonObject config;
    private static ConfigurationManager INSTANCE;

    public static ConfigurationManager getInstance() {
        if (ConfigurationManager.INSTANCE == null) {
            ConfigurationManager.INSTANCE = new ConfigurationManager();
        }
        return ConfigurationManager.INSTANCE;
    }

    private String readFile() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(
                ConfigurationManager.class.getResourceAsStream("config.json")));
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

    private ConfigurationManager() {
        try {
            String json = this.readFile();
            this.config = new JsonParser().parse(json).getAsJsonObject();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public String getMessage(String key) {
        String msg = this.config.getAsJsonObject("messages")
                .get(key).getAsString();
        if (msg == null || msg.equals("")) {
            msg = "No message found for key \"" + key + "\"";
        }
        return msg;
    }

    public JsonObject getUniversity() {
        return this.config.getAsJsonObject("university");
    }

    public JsonObject getEsse3Links() {
        return this.getUniversity().getAsJsonObject("links")
                .getAsJsonObject("esse3");
    }

    public String getEsse3Base(){
        return this.getEsse3Links().get("base").getAsString();
    }
    
    public JsonObject getAddressBookLinks() {
        return this.getUniversity().getAsJsonObject("links")
                .getAsJsonObject("addressBook");
    }

    public JsonObject getNewsLinks() {
        return this.getUniversity().getAsJsonObject("links")
                .getAsJsonObject("news");
    }

    public JsonObject getDepartment() {
        return this.getNewsLinks().getAsJsonObject("department");
    }

    public JsonObject getCourseLinks() {
        return this.getNewsLinks().getAsJsonObject("course");
    }

    public String getLogonUrl() {
        return this.getEsse3Links().get("logon").getAsString();
    }
    
    public String getCareerChangeUrl() {
        return this.getEsse3Links().get("careerChange").getAsString();
    }
    
    public String getCareerListUrl() {
        return this.getEsse3Links().get("careerList").getAsString();
    }
    
    public String getHomeUrl() {
        return this.getEsse3Links().get("logon").getAsString();
    }

    public String getLogoutUrl() {
        return this.getEsse3Links().get("logout").getAsString();
    }

    public String getRecordBookUrl() {
        return this.getEsse3Links().get("recordBook").getAsString();
    }

    public String getExamDetailUrl() {
        return this.getEsse3Links().get("examDetail").getAsString();
    }

    public String getExamSessionsUrl() {
        return this.getEsse3Links().get("examSessions").getAsString();
    }

    public String getEnrolledExamSessionsUrl() {
        return this.getEsse3Links().get("enrolledExamSessions").getAsString();
    }

    public String getExamSessionDetailUrl() {
        return this.getEsse3Links().get("examSessionDetail").getAsString();
    }

    public String getTaxesUrl() {
        return this.getEsse3Links().get("taxes").getAsString();
    }

    public String getUniversityName() {
        return this.getUniversity().get("name").getAsString();
    }

    public String getUniversityShortName() {
        return this.getUniversity().get("shortName").getAsString();
    }

    public String getAddressBookMainUrl() {
        return this.getAddressBookLinks().get("main").getAsString();

    }

    public String getAddressBookSearchUrl() {
        return this.getAddressBookLinks().get("search").getAsString();
    }

    public String getUniversityNewsUrl() {
        return this.getNewsLinks().get("main").getAsString();
    }

    public String getAgricolturaNewsUrl() {
        return this.getDepartment().get("agricolturaAmbienteAlimenti").getAsString();
    }

    public String getBioscienzeTerritorioNewsUrl() {
        return this.getDepartment().get("bioscienzeTerritorio").getAsString();
    }

    public String getEconomiaNewsUrl() {
        return this.getDepartment().get("economiaGestioneSocietaIstituzioni").getAsString();
    }

    public String getGiuridicoNewsUrl() {
        return this.getDepartment().get("giuridico").getAsString();
    }

    public String getMedicinaNewsUrl() {
        return this.getDepartment().get("medicinaScienzeSalute").getAsString();
    }

    public String getUmanisticheNewsUrl() {
        return this.getDepartment().get("scienzeUmanisticheSocialiFormazione").getAsString();
    }
    
    public String getMedicinaNewsBoardUrl1() {
    	return this.getCourseLinks().get("medicina1").getAsString();
    }
    
    public String getMedicinaNewsBoardUrl2() {
    	return this.getCourseLinks().get("medicina2").getAsString();
    }

    public String getInformaticaNewsBoardUrl() {
        return this.getCourseLinks().get("informatica").getAsString();
    }

    public String getScienzeBiologicheNewsBoardUrl() {
        return this.getCourseLinks().get("scienzeBiologiche").getAsString();
    }
    
    public String getEconomiaNewsBoardUrl() {
    	return this.getCourseLinks().get("economia").getAsString();
	}
    
    public String getScienzePoliticheNewsBoardUrl() {
    	return this.getCourseLinks().get("scienzePolitiche").getAsString();
	}
    
    public String getScienzeServizioSocialeNewsBoardUrl() {
    	return this.getCourseLinks().get("scienzeServizioSociale").getAsString();
	}
    
    public String getScienzePoliticheIstituzioniEuropeeNewsBoardUrl() {
    	return this.getCourseLinks().get("scienzePoliticheIstituzioniEuropee").getAsString();
	}
    
    public String getServizioSocialePoliticheSocialiNewsBoardUrl() {
    	return this.getCourseLinks().get("servizioSocialePoliticheSociali").getAsString();
	}
    
    public String getGiurisprudenzaNewsBoardUrl() {
    	return this.getCourseLinks().get("giurisprudenza").getAsString();
	}
    
    public String getScienzeTecnologieAlimentariNewsBoardUrl() {
    	return this.getCourseLinks().get("scienzeTecnologieAlimentari").getAsString();
	}
    
    public String getAgrariaNewsBoardUrl() {
    	return this.getCourseLinks().get("agraria").getAsString();
	}
    
    public String getTecnologieForestaliAmbientaliNewsBoardUrl() {
    	return this.getCourseLinks().get("tecnologieForestaliAmbientali").getAsString();
	}
    
    public String getLettereNewsBoardUrl() {
    	return this.getCourseLinks().get("lettere").getAsString();
	}
    
    public String getMerendeNewsBoardUrl() {
    	return this.getCourseLinks().get("comunicazione").getAsString();
	}
    
    public String getScienzeFormazionePrimariaNewsBoardUrl() {
    	return this.getCourseLinks().get("scienzeFormazionePrimaria").getAsString();
	}
    
    public String getIngegneriaEdileNewsBoardUrl() {
    	return this.getCourseLinks().get("ingEdile").getAsString();
	}
    
    public String getIngegneriaCivileNewsBoardUrl() {
    	return this.getCourseLinks().get("ingCivile").getAsString();
	}
    
    public String getIngegneriaCivileMasterNewsBoardUrl() {
    	return this.getCourseLinks().get("ingCivileMaster").getAsString();
	}
    
    public String getScienzeTuristicheNewsBoardUrl() {
    	return this.getCourseLinks().get("scienzeTuristiche").getAsString();
	}
    
    public String getScienzeBiologicheMasterNewsBoardUrl() {
    	return this.getCourseLinks().get("scienzeBiologicheMaster").getAsString();
	}
    
    public String getTurismoBeniCulturaliNewsBoardUrl() {
    	return this.getCourseLinks().get("turismoBeniCulturali").getAsString();
	}
    
    public JsonObject getFeedLinks() {
        return this.getUniversity().getAsJsonObject("links")
                .getAsJsonObject("feed");
    }

    public JsonObject getDepartmentFeedLinks() {
        return this.getFeedLinks().getAsJsonObject("department");
    }

    public String getUnimolFeedUrl() {
        return this.getFeedLinks().get("unimol").getAsString();
    }

    public String getBioscienzeTerritorioFeedLink() {
        return this.getDepartmentFeedLinks().get("bioscienzeTerritorio")
                .getAsString();
    }

}
