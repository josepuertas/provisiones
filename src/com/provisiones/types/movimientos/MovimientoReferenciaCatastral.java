package com.provisiones.types.movimientos;

public class MovimientoReferenciaCatastral 
{

	private String CODTRN = "";
	private String COTDOR = "";
	private String IDPROV = "";
	private String COACCI = "";
	private String COENGP = "";
	private String COACES = "";
	private String NURCAT = "";
	private String BITC16 = "";
	private String TIRCAT = "";
	private String BITC17 = "";
	private String ENEMIS = "";
	private String COTEXA = "";
	private String BITC09 = "";
	private String OBTEXC = "";
	private String OBDEER = "";

	//Ampliacion de valor catastral
	private String BITC23 = "";
	private String IMVSUE = "";
	private String BITC24 = "";
	private String IMCATA = "";
	private String BITC25 = "";
	private String FERECA = "";

	
	private String FILLER = "                                                                                     ";

	//Constructor de clase

	public MovimientoReferenciaCatastral(String cODTRN, String cOTDOR,
			String iDPROV, String cOACCI, String cOENGP, String cOACES,
			String nURCAT, String bITC16, String tIRCAT, String bITC17,
			String eNEMIS, String cOTEXA, String bITC09, String oBTEXC,
			String oBDEER, String bITC23, String iMVSUE, String bITC24,
			String iMCATA, String bITC25, String fERECA) {
		super();
		CODTRN = cODTRN;
		COTDOR = cOTDOR;
		IDPROV = iDPROV;
		COACCI = cOACCI;
		COENGP = cOENGP;
		COACES = cOACES;
		NURCAT = nURCAT;
		BITC16 = bITC16;
		TIRCAT = tIRCAT;
		BITC17 = bITC17;
		ENEMIS = eNEMIS;
		COTEXA = cOTEXA;
		BITC09 = bITC09;
		OBTEXC = oBTEXC;
		OBDEER = oBDEER;
		BITC23 = bITC23;
		IMVSUE = iMVSUE;
		BITC24 = bITC24;
		IMCATA = iMCATA;
		BITC25 = bITC25;
		FERECA = fERECA;
	}	
	
	//M�todos de acceso

	public String getCODTRN() {
		return CODTRN;
	}

	public void setCODTRN(String cODTRN) {
		CODTRN = cODTRN;
	}

	public String getCOTDOR() {
		return COTDOR;
	}

	public void setCOTDOR(String cOTDOR) {
		COTDOR = cOTDOR;
	}

	public String getIDPROV() {
		return IDPROV;
	}

	public void setIDPROV(String iDPROV) {
		IDPROV = iDPROV;
	}

	public String getCOACCI() {
		return COACCI;
	}

	public void setCOACCI(String cOACCI) {
		COACCI = cOACCI;
	}

	public String getCOENGP() {
		return COENGP;
	}

	public void setCOENGP(String cOENGP) {
		COENGP = cOENGP;
	}

	public String getCOACES() {
		return COACES;
	}

	public void setCOACES(String cOACES) {
		COACES = cOACES;
	}

	public String getNURCAT() {
		return NURCAT;
	}

	public void setNURCAT(String nURCAT) {
		NURCAT = nURCAT;
	}

	public String getBITC16() {
		return BITC16;
	}

	public void setBITC16(String bITC16) {
		BITC16 = bITC16;
	}

	public String getTIRCAT() {
		return TIRCAT;
	}

	public void setTIRCAT(String tIRCAT) {
		TIRCAT = tIRCAT;
	}

	public String getBITC17() {
		return BITC17;
	}

	public void setBITC17(String bITC17) {
		BITC17 = bITC17;
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

	public String getBITC09() {
		return BITC09;
	}

	public void setBITC09(String bITC09) {
		BITC09 = bITC09;
	}

	public String getOBTEXC() {
		return OBTEXC;
	}

	public void setOBTEXC(String oBTEXC) {
		OBTEXC = oBTEXC;
	}

	public String getOBDEER() {
		return OBDEER;
	}

	public void setOBDEER(String oBDEER) {
		OBDEER = oBDEER;
	}

	public String getBITC23() {
		return BITC23;
	}

	public void setBITC23(String bITC23) {
		BITC23 = bITC23;
	}

	public String getIMVSUE() {
		return IMVSUE;
	}

	public void setIMVSUE(String iMVSUE) {
		IMVSUE = iMVSUE;
	}

	public String getBITC24() {
		return BITC24;
	}

	public void setBITC24(String bITC24) {
		BITC24 = bITC24;
	}

	public String getIMCATA() {
		return IMCATA;
	}

	public void setIMCATA(String iMCATA) {
		IMCATA = iMCATA;
	}

	public String getBITC25() {
		return BITC25;
	}

	public void setBITC25(String bITC25) {
		BITC25 = bITC25;
	}

	public String getFERECA() {
		return FERECA;
	}

	public void setFERECA(String fERECA) {
		FERECA = fERECA;
	}

	public String getFILLER() {
		return FILLER;
	}

	public String logMovimientoReferenciaCatastral()
	{
		return String.format("(MOVIMIENTO REFERENCIA CATASTRAL)\nCODTRN:|%s|\nCOTDOR:|%s|\nIDPROV:|%s|\nCOACCI:|%s|\nCOENGP:|%s|\nCOACES:|%s|\nNURCAT:|%s|\nBITC16:|%s|\nTIRCAT:|%s|\nBITC17:|%s|\nENEMIS:|%s|\nCOTEXA:|%s|\nBITC09:|%s|\nOBTEXC:|%s|\nOBDEER:|%s|\nBITC23:|%s|\nIMVSUE:|%s|\nBITC24:|%s|\nIMCATA:|%s|\nBITC25:|%s|\nFERECA:|%s|",CODTRN,COTDOR,IDPROV,COACCI,COENGP,COACES,NURCAT,BITC16,TIRCAT,BITC17,ENEMIS,COTEXA,BITC09,OBTEXC,OBDEER,BITC23,IMVSUE,BITC24,IMCATA,BITC25,FERECA);
	}


}
