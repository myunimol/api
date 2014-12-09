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

    public JsonObject getAddressBookLinks() {
        return this.getUniversity().getAsJsonObject("links")
                .getAsJsonObject("addressBook");
    }

    public String getLogonUrl() {
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
}
