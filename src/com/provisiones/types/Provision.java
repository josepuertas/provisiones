package com.provisiones.types;

public class Provision 
{

	private String sNUPROF = "";
	private String sCOSPAT = "";
	private String sTAS = "";
	private String sValorTolal = "";
	private String sNumGastos = "";	
	private String sValorAutorizado = "";
	private String sGastosAutorizados = "";	
	private String sFEPFON = "";
	private String sFechaValidacion = "";
	private String sCodEstado = "";


	//Constructor de clase

	public Provision(String sNUPROF, String sCOSPAT, String sTAS,
			String sValorTolal, String sNumGastos, String sValorAutorizado,
			String sGastosAutorizados, String sFEPFON, String sFechaValidacion,
			String sCodEstado) {
		super();
		this.sNUPROF = sNUPROF;
		this.sCOSPAT = sCOSPAT;
		this.sTAS = sTAS;
		this.sValorTolal = sValorTolal;
		this.sNumGastos = sNumGastos;
		this.sValorAutorizado = sValorAutorizado;
		this.sGastosAutorizados = sGastosAutorizados;
		this.sFEPFON = sFEPFON;
		this.sFechaValidacion = sFechaValidacion;
		this.sCodEstado = sCodEstado;
	}

	
	//M�todos de acceso

	public String getsNUPROF() {
		return sNUPROF;
	}


	public void setsNUPROF(String sNUPROF) {
		this.sNUPROF = sNUPROF;
	}


	public String getsCOSPAT() {
		return sCOSPAT;
	}


	public void setsCOSPAT(String sCOSPAT) {
		this.sCOSPAT = sCOSPAT;
	}


	public String getsTAS() {
		return sTAS;
	}


	public void setsTAS(String sTAS) {
		this.sTAS = sTAS;
	}


	public String getsValorTolal() {
		return sValorTolal;
	}


	public void setsValorTolal(String sValorTolal) {
		this.sValorTolal = sValorTolal;
	}


	public String getsNumGastos() {
		return sNumGastos;
	}


	public void setsNumGastos(String sNumGastos) {
		this.sNumGastos = sNumGastos;
	}


	public String getsValorAutorizado() {
		return sValorAutorizado;
	}


	public void setsValorAutorizado(String sValorAutorizado) {
		this.sValorAutorizado = sValorAutorizado;
	}


	public String getsGastosAutorizados() {
		return sGastosAutorizados;
	}


	public void setsGastosAutorizados(String sGastosAutorizados) {
		this.sGastosAutorizados = sGastosAutorizados;
	}


	public String getsFEPFON() {
		return sFEPFON;
	}


	public void setsFEPFON(String sFEPFON) {
		this.sFEPFON = sFEPFON;
	}


	public String getsFechaValidacion() {
		return sFechaValidacion;
	}


	public void setsFechaValidacion(String sFechaValidacion) {
		this.sFechaValidacion = sFechaValidacion;
	}


	public String getsCodEstado() {
		return sCodEstado;
	}


	public void setsCodEstado(String sCodEstado) {
		this.sCodEstado = sCodEstado;
	}

	public String logProvision()
	{
		return String.format("(PROVISION)\nsNUPROF         :|"+sNUPROF+"|\nsCOSPAT         :|"+sCOSPAT+"|\nsTAS            :|"+sTAS+"|\nsFEPFON         :|"+sFEPFON+"|\nsFechaValidacion:|"+sFechaValidacion+"|\nsCodEstado      :|"+sCodEstado+"|\nsValorTolal     :|"+sValorTolal+"|\nsNumGastos      :|"+sNumGastos+"|");
	}


}
