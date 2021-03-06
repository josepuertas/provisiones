package com.provisiones.types;

public class ReferenciaCatastral 
{

	private String NURCAT = "";
	private String TIRCAT = "";
	private String ENEMIS = "";
	private String COTEXA = "";
	private String OBTEXC = "";
	
	//Ampliacion de valor catastral
	private String IMVSUE = "";
	private String IMCATA = "";
	private String FERECA = "";

	//Constructor de clase

	public ReferenciaCatastral(String nURCAT, String tIRCAT, String eNEMIS,
			String cOTEXA, String oBTEXC, String iMVSUE, String iMCATA,
			String fERECA) {
		super();
		NURCAT = nURCAT;
		TIRCAT = tIRCAT;
		ENEMIS = eNEMIS;
		COTEXA = cOTEXA;
		OBTEXC = oBTEXC;
		IMVSUE = iMVSUE;
		IMCATA = iMCATA;
		FERECA = fERECA;
	}	

	//M�todos de acceso

	public String getNURCAT() {
		return NURCAT;
	}

	public void setNURCAT(String nURCAT) {
		NURCAT = nURCAT;
	}

	public String getTIRCAT() {
		return TIRCAT;
	}

	public void setTIRCAT(String tIRCAT) {
		TIRCAT = tIRCAT;
	}

	public String getENEMIS() {
		return ENEMIS;
	}

	public void setENEMIS(String eNEMIS) {
		ENEMIS = eNEMIS;
	}

	public String getCOTEXA() {
		return COTEXA;
	}

	public void setCOTEXA(String cOTEXA) {
		COTEXA = cOTEXA;
	}

	public String getOBTEXC() {
		return OBTEXC;
	}

	public void setOBTEXC(String oBTEXC) {
		OBTEXC = oBTEXC;
	}

	public String getIMVSUE() {
		return IMVSUE;
	}

	public void setIMVSUE(String iMVSUE) {
		IMVSUE = iMVSUE;
	}

	public String getIMCATA() {
		return IMCATA;
	}

	public void setIMCATA(String iMCATA) {
		IMCATA = iMCATA;
	}

	public String getFERECA() {
		return FERECA;
	}

	public void setFERECA(String fERECA) {
		FERECA = fERECA;
	}

	public String logReferenciaCatastral()
	{
		return String.format("(REFERENCIA CATASTRAL)\nNURCAT:|%s|\nTIRCAT:|%s|\nENEMIS:|%s|\nCOTEXA:|%s|\nOBTEXC:|%s|\nIMVSUE:|%s|\nIMCATA:|%s|\nFERECA:|%s|",NURCAT,TIRCAT,ENEMIS,COTEXA,OBTEXC,IMVSUE,IMCATA,FERECA);
	}


}
