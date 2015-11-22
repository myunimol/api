package it.unimol.my.examsession;

import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Giuseppe Bianco
 */
public class ExamSessionInfo {

    private String action;
    private String APP_ID;
    private String CDS_ESA_ID;
    private String ATT_DID_ESA_ID;
    private String ADSCE_ID;
    private String AA_OFF_ID;
    private String CDS_ID;
    private String PDS_ID;
    private String AA_ORD_ID;
    private String ISCR_APERTA;
    private String TIPO_ATTIVITA;
    private String TIPO_APP_COD;

    private static final String DELIMITER = "#";

    public ExamSessionInfo() {
    }

    public ExamSessionInfo(String pAction, String pAppId, String pCdsEsaId,
            String pAttDidEsaId, String pAdsceId, String pAaOffId,
            String pCdsId, String pPdsId, String pAaOrdId, String pIscrAperta,
            String pTipoAttivita, String pTipoAppCod) {
        this.action = pAction;
        this.APP_ID = pAppId;
        this.CDS_ESA_ID = pCdsEsaId;
        this.ATT_DID_ESA_ID = pAttDidEsaId;
        this.ADSCE_ID = pAdsceId;
        this.AA_OFF_ID = pAaOffId;
        this.CDS_ID = pCdsId;
        this.PDS_ID = pPdsId;
        this.AA_ORD_ID = pAaOrdId;
        this.ISCR_APERTA = pIscrAperta;
        this.TIPO_ATTIVITA = pTipoAttivita;
        this.TIPO_APP_COD = pTipoAppCod;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getAppId() {
        return APP_ID;
    }

    public void setAppId(String appId) {
        this.APP_ID = appId;
    }

    public String getCdsEsaId() {
        return CDS_ESA_ID;
    }

    public void setCdsEsaId(String cdsEsaId) {
        this.CDS_ESA_ID = cdsEsaId;
    }

    public String getAttDidEsaId() {
        return ATT_DID_ESA_ID;
    }

    public void setAttDidEsaId(String attDidEsaId) {
        this.ATT_DID_ESA_ID = attDidEsaId;
    }

    public String getAdsceId() {
        return ADSCE_ID;
    }

    public void setAdsceId(String adsceId) {
        this.ADSCE_ID = adsceId;
    }

    public String getAaOffId() {
        return AA_OFF_ID;
    }

    public void setAaOffId(String aaOffId) {
        this.AA_OFF_ID = aaOffId;
    }

    public String getCdsId() {
        return CDS_ID;
    }

    public void setCdsId(String cdsId) {
        this.CDS_ID = cdsId;
    }

    public String getPdsId() {
        return PDS_ID;
    }

    public void setPdsId(String pdsId) {
        this.PDS_ID = pdsId;
    }

    public String getAaOrdId() {
        return AA_ORD_ID;
    }

    public void setAaOrdId(String aaOrdId) {
        this.AA_ORD_ID = aaOrdId;
    }

    public String getIscrAperta() {
        return ISCR_APERTA;
    }

    public void setIscrAperta(String iscrAperta) {
        this.ISCR_APERTA = iscrAperta;
    }

    public String getTipoAttivita() {
        return TIPO_ATTIVITA;
    }

    public void setTipoAttivita(String tipoAttivita) {
        this.TIPO_ATTIVITA = tipoAttivita;
    }

    public String getTipoAppCod() {
        return TIPO_APP_COD;
    }

    public void setTipoAppCod(String pTipoAppCod) {
        this.TIPO_APP_COD = pTipoAppCod;
    }

    /*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ExamSessionInfo [action=" + action + ", appId=" + APP_ID
                + ", cdsEsaId=" + CDS_ESA_ID + ", attDidEsaId=" + ATT_DID_ESA_ID
                + ", adsceId=" + ADSCE_ID + ", aaOffId=" + AA_OFF_ID + ", cdsId="
                + CDS_ID + ", pdsId=" + PDS_ID + ", aaOrdId=" + AA_ORD_ID
                + ", iscrAperta=" + ISCR_APERTA + ", tipoAttivita="
                + TIPO_ATTIVITA + ", tipoAppCod=" + TIPO_APP_COD + "]";
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
        result = prime * result + ((AA_OFF_ID == null) ? 0 : AA_OFF_ID.hashCode());
        result = prime * result + ((AA_ORD_ID == null) ? 0 : AA_ORD_ID.hashCode());
        result = prime * result + ((action == null) ? 0 : action.hashCode());
        result = prime * result + ((ADSCE_ID == null) ? 0 : ADSCE_ID.hashCode());
        result = prime * result + ((APP_ID == null) ? 0 : APP_ID.hashCode());
        result = prime * result
                + ((ATT_DID_ESA_ID == null) ? 0 : ATT_DID_ESA_ID.hashCode());
        result = prime * result
                + ((CDS_ESA_ID == null) ? 0 : CDS_ESA_ID.hashCode());
        result = prime * result + ((CDS_ID == null) ? 0 : CDS_ID.hashCode());
        result = prime * result
                + ((ISCR_APERTA == null) ? 0 : ISCR_APERTA.hashCode());
        result = prime * result + ((PDS_ID == null) ? 0 : PDS_ID.hashCode());
        result = prime * result
                + ((TIPO_ATTIVITA == null) ? 0 : TIPO_ATTIVITA.hashCode());
        result = prime * result + ((TIPO_APP_COD == null) ? 0 : TIPO_APP_COD.hashCode());
        return result;
    }

    /*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ExamSessionInfo other = (ExamSessionInfo) obj;
        if (AA_OFF_ID == null) {
            if (other.AA_OFF_ID != null) {
                return false;
            }
        } else if (!AA_OFF_ID.equals(other.AA_OFF_ID)) {
            return false;
        }
        if (AA_ORD_ID == null) {
            if (other.AA_ORD_ID != null) {
                return false;
            }
        } else if (!AA_ORD_ID.equals(other.AA_ORD_ID)) {
            return false;
        }
        if (action == null) {
            if (other.action != null) {
                return false;
            }
        } else if (!action.equals(other.action)) {
            return false;
        }
        if (ADSCE_ID == null) {
            if (other.ADSCE_ID != null) {
                return false;
            }
        } else if (!ADSCE_ID.equals(other.ADSCE_ID)) {
            return false;
        }
        if (APP_ID == null) {
            if (other.APP_ID != null) {
                return false;
            }
        } else if (!APP_ID.equals(other.APP_ID)) {
            return false;
        }
        if (ATT_DID_ESA_ID == null) {
            if (other.ATT_DID_ESA_ID != null) {
                return false;
            }
        } else if (!ATT_DID_ESA_ID.equals(other.ATT_DID_ESA_ID)) {
            return false;
        }
        if (CDS_ESA_ID == null) {
            if (other.CDS_ESA_ID != null) {
                return false;
            }
        } else if (!CDS_ESA_ID.equals(other.CDS_ESA_ID)) {
            return false;
        }
        if (CDS_ID == null) {
            if (other.CDS_ID != null) {
                return false;
            }
        } else if (!CDS_ID.equals(other.CDS_ID)) {
            return false;
        }
        if (ISCR_APERTA == null) {
            if (other.ISCR_APERTA != null) {
                return false;
            }
        } else if (!ISCR_APERTA.equals(other.ISCR_APERTA)) {
            return false;
        }
        if (PDS_ID == null) {
            if (other.PDS_ID != null) {
                return false;
            }
        } else if (!PDS_ID.equals(other.PDS_ID)) {
            return false;
        }
        if (TIPO_ATTIVITA == null) {
            if (other.TIPO_ATTIVITA != null) {
                return false;
            }
        } else if (!TIPO_ATTIVITA.equals(other.TIPO_ATTIVITA)) {
            return false;
        } else if (!TIPO_APP_COD.equals(other.TIPO_APP_COD)) {
            return false;
        }
        return true;
    }

    public Map toHashMap() {
        Map<String, String> result = new HashMap<>();
        result.put("APP_ID", APP_ID);
        result.put("CDS_ESA_ID", CDS_ESA_ID);
        result.put("ATT_DID_ESA_ID", ATT_DID_ESA_ID);
        result.put("ADSCE_ID", ADSCE_ID);
        result.put("AA_OFF_ID", AA_OFF_ID);
        result.put("CDS_ID", CDS_ID);
        result.put("PDS_ID", PDS_ID);
        result.put("AA_ORD_ID", AA_ORD_ID);
        result.put("ISCR_APERTA", ISCR_APERTA);
        result.put("TIPO_ATTIVITA", TIPO_ATTIVITA);
        result.put("TIPO_APP_COD", TIPO_APP_COD);

        return result;

    }
    
    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
