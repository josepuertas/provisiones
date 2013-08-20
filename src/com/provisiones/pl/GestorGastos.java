package com.provisiones.pl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.provisiones.ll.CLActivos;
import com.provisiones.ll.CLCuotas;
import com.provisiones.ll.CLGastos;
import com.provisiones.ll.CLImpuestos;
import com.provisiones.ll.CLProvisiones;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.ActivoTabla;
import com.provisiones.types.CuotaTabla;
import com.provisiones.types.ImpuestoRecursoTabla;
import com.provisiones.types.MovimientoGasto;

public class GestorGastos implements Serializable 
{
	private static final long serialVersionUID = 476229907564908389L;
	
	static String sClassName = GestorGastos.class.getName();

	private String sCOACES = "";
	private String sCOGRUG = "";
	private String sCOTPGA = "";
	private String sCOSBGA = "";
	private String sPTPAGO = "";
	private String sFEDEVE = "";
	private String sFFGTVP = "";
	private boolean bFFGTVP = true;
	private String sFEPAGA = "";
	private String sFELIPG = "";
	private String sCOSIGA = "";
	private String sFEEESI = "";
	private boolean bFEEESI = true;
	private String sFEECOI = "";
	private boolean bFEECOI = true;
	private String sFEEAUI = "00000000";
	private String sFEEPAI = "00000000";

	private String sIMNGAS = "";
	private String sYCOS02 = "";
	private String sIMRGAS = "";
	private String sYCOS04 = "";
	private String sIMDGAS = "";
	private String sYCOS06 = "";
	private String sIMCOST = "";
	private String sYCOS08 = "";
	private String sIMOGAS = "";
	private String sYCOS10 = "";
	
	private String sIMDTGA = "";
	private String sCOUNMO = "";
	private String sIMIMGA = "";
	private String sCOIMPT = "";
	
	private String sCOTNEG = ValoresDefecto.DEF_COTNEG;
	
	private String sCOENCX = ValoresDefecto.DEF_COENCX;
	private String sCOOFCX = ValoresDefecto.DEF_COOFCX;
	private String sNUCONE = ValoresDefecto.DEF_NUCONE;
	private String sNUPROF = "";
	private String sFEAGTO = "";
	private String sCOMONA = ValoresDefecto.DEF_COMONA;
	private String sBIAUTO = ValoresDefecto.DEF_BIAUTO;
	private String sFEAUFA = ValoresDefecto.DEF_FEAUFA;
	private String sCOTERR = ValoresDefecto.DEF_COTERR;
	private String sFMPAGN = ValoresDefecto.DEF_FMPAGN;
	private String sFEPGPR = ValoresDefecto.DEF_FEPGPR;
	
	private String sFEAPLI = ValoresDefecto.DEF_FEAPLI;
	
	private String sCOAPII = ValoresDefecto.DEF_COAPII;
	private String sCOSPII = ValoresDefecto.DEF_COSPII;
	private String sNUCLII = ValoresDefecto.DEF_NUCLII;

	//recuperar cuotas
	private String sCOSBAC = "";
	private String sIMCUCO = "";
	
	//filtro de activos
	private String sCOPOIN = "";
	private String sNOMUIN = "";
	private String sNOPRAC = "";
	private String sNOVIAS = "";
	private String sNUPIAC = "";
	private String sNUPOAC = "";
	private String sNUPUAC = "";
	
	
	private ActivoTabla activoseleccionado = null;
	private ArrayList<ActivoTabla> tablaactivos = null;

	private CuotaTabla cuotaseleccionada = null;
	private ArrayList<CuotaTabla> tablacuotas = null;
	
	private ImpuestoRecursoTabla devolucionseleccionada = null;
	private ArrayList<ImpuestoRecursoTabla> tabladevoluciones = null;
	
	private Map<String,String> tiposcotpgaHM = new LinkedHashMap<String, String>();
	private Map<String,String> tiposcosbgaHM = new LinkedHashMap<String, String>();
	
	private Map<String,String> tiposcotpga_g1HM = new LinkedHashMap<String, String>();
	private Map<String,String> tiposcotpga_g2HM = new LinkedHashMap<String, String>();
	private Map<String,String> tiposcotpga_g3HM = new LinkedHashMap<String, String>();
	
	private Map<String,String> tiposcosbga_t11HM = new LinkedHashMap<String, String>();
	private Map<String,String> tiposcosbga_t12HM = new LinkedHashMap<String, String>();
	private Map<String,String> tiposcosbga_t21HM = new LinkedHashMap<String, String>();
	private Map<String,String> tiposcosbga_t22HM = new LinkedHashMap<String, String>();
	private Map<String,String> tiposcosbga_t23HM = new LinkedHashMap<String, String>();
	private Map<String,String> tiposcosbga_t32HM = new LinkedHashMap<String, String>();
	private Map<String,String> tiposcosbga_t33HM = new LinkedHashMap<String, String>();

	private Map<String,String> tiposcosigaHM = new LinkedHashMap<String, String>();

	public GestorGastos()
	{
		Utils.standardIO2File("");//Salida por fichero de texto
		
		tiposcotpga_g1HM.put("Plusvalia", "1");
		tiposcotpga_g1HM.put("Notaria",   "2");

		tiposcotpga_g2HM.put("Tasas-Impuestos", "1");
		tiposcotpga_g2HM.put("Comunidades",     "2");
		tiposcotpga_g2HM.put("Suministros",     "3");
		
		tiposcotpga_g3HM.put("Honorarios","2");
		tiposcotpga_g3HM.put("Licencias", "3");
		
		
		
		tiposcosbga_t11HM.put("Plusvalia", "0");
		//tiposcosbga_t11HM.put("Devolucion Plusvalia", "50");
		tiposcosbga_t12HM.put("Notaria",   "1");
		//tiposcosbga_t12HM.put("Devolucion Notaria",     "51");

		tiposcosbga_t21HM.put("Impuestos e IBIS",                     "0");
		tiposcosbga_t21HM.put("IBIS",                                 "1");
		tiposcosbga_t21HM.put("Tasas basura",                         "2");
		tiposcosbga_t21HM.put("Tasas alcantarillado",                 "3");
		tiposcosbga_t21HM.put("Tasas agua",                           "4");
		tiposcosbga_t21HM.put("Contribuciones especiales",            "5");
		tiposcosbga_t21HM.put("Otras tasas",                          "6");
		/*tiposcosbga_t21HM.put("Devolución Impuestos e IBIS",         "50");
		tiposcosbga_t21HM.put("Devolución IBIS",                     "51");
		tiposcosbga_t21HM.put("Devolución Tasas basura",             "52");
		tiposcosbga_t21HM.put("Devolución Tasas alcantarillado",     "53");
		tiposcosbga_t21HM.put("Devolución Tasas agua",               "54");
		tiposcosbga_t21HM.put("Devolución Contribuciones especiales","55");
		tiposcosbga_t21HM.put("Devolución Otras tasas",              "56");*/
		
		tiposcosbga_t22HM.put("Comunidad",	                   	"0");  
		tiposcosbga_t22HM.put("Ordinaria",                     	"1");  
		tiposcosbga_t22HM.put("Extras Comunidad",              	"2");  
		tiposcosbga_t22HM.put("Mancomunidad",                  	"3");  
		tiposcosbga_t22HM.put("Extras Mancomunidad",           	"4");  
		tiposcosbga_t22HM.put("Obras comunidad",               	"5");  
		/*tiposcosbga_t22HM.put("Devolucion Comunidades",       "50"); 
		tiposcosbga_t22HM.put("Devolucion Ordinaria",          	"51"); 
		tiposcosbga_t22HM.put("Devolucion Extras Comunidad",   	"52"); 
		tiposcosbga_t22HM.put("Devolucion Mancomunidad",       	"53"); 
		tiposcosbga_t22HM.put("Devolucion Extras Mancomunidad",	"54"); 
		tiposcosbga_t22HM.put("Devolucion Obras comunidad",   	"55");*/
		
		
		tiposcosbga_t23HM.put("Suministros",               "0");
		tiposcosbga_t23HM.put("Suministro luz",            "1");
		tiposcosbga_t23HM.put("Suministro agua",           "2");
		tiposcosbga_t23HM.put("Suministro gas",            "3");
		/*tiposcosbga_t23HM.put("Devolucion Suministros",  "50");
		tiposcosbga_t23HM.put("Devolucion Suministro luz", "51");
		tiposcosbga_t23HM.put("Devolucion Suministro agua","52");
		tiposcosbga_t23HM.put("Devolución Suministro gas", "53");*/
		
		tiposcosbga_t32HM.put("Honorarios Colaboradores","0");  
		tiposcosbga_t32HM.put("Prescripcion",            "1");  
		tiposcosbga_t32HM.put("Colaboracion",            "2");  
		tiposcosbga_t32HM.put("Otros honorarios",        "3");  
		tiposcosbga_t32HM.put("Servicios varios",        "4");
		
		tiposcosbga_t33HM.put("Obtencion de Licencias", "0");
		
		tiposcosigaHM.put("ESTIMADO",            "1");
		tiposcosigaHM.put("CONOCIDO",            "2");
		
	}
	public void borrarPlantillaGasto()
	{
		this.sCOGRUG = "";
		this.sCOTPGA = "";
		this.sCOSBGA = "";
		this.sPTPAGO = "";

		this.sFEDEVE = "";
		this.sFFGTVP = "";
		this.bFFGTVP = true;
		this.sFEPAGA = "";
		this.sFELIPG = "";

		this.sCOSIGA = "";
		this.sFEEESI = "";
		this.bFEEESI = true;
		this.sFEECOI = "";
		this.bFEECOI = true;
		this.sFEEAUI = "00000000";
		this.sFEEPAI = "00000000";

		this.sIMNGAS = "";
		this.sYCOS02 = "";
		this.sIMRGAS = "";
		this.sYCOS04 = "";
		this.sIMDGAS = "";
		this.sYCOS06 = "";
		this.sIMCOST = "";
		this.sYCOS08 = "";
		this.sIMOGAS = "";
		this.sYCOS10 = "";
		this.sIMDTGA = "";
		this.sCOUNMO = "";
		this.sIMIMGA = "";
		this.sCOIMPT = "";

		this.sCOTNEG = ValoresDefecto.DEF_COTNEG;

		this.sCOENCX = ValoresDefecto.DEF_COENCX;
		this.sCOOFCX = ValoresDefecto.DEF_COOFCX;
		this.sNUCONE = ValoresDefecto.DEF_NUCONE;

		this.sNUPROF = "";

		this.sFEAGTO = "";

		this.sCOMONA = ValoresDefecto.DEF_COMONA;
		this.sBIAUTO = ValoresDefecto.DEF_BIAUTO;
		this.sFEAUFA = ValoresDefecto.DEF_FEAUFA;
		this.sCOTERR = ValoresDefecto.DEF_COTERR;

		this.sFMPAGN = ValoresDefecto.DEF_FMPAGN;
		this.sFEPGPR = ValoresDefecto.DEF_FEPGPR;
		
		this.sFEAPLI = ValoresDefecto.DEF_FEAPLI;
		
		this.sCOAPII = ValoresDefecto.DEF_COAPII;
		this.sCOSPII = ValoresDefecto.DEF_COSPII;
		this.sNUCLII = ValoresDefecto.DEF_NUCLII;
		
		this.sCOSBAC = "";
		this.sIMCUCO = "";
		
	}
	
    public void limpiarPlantilla(ActionEvent actionEvent) 
    {  
    	borrarPlantillaGasto();
    	borrarPlantillaActivo();
    	
		this.activoseleccionado = null;
		this.tablaactivos = null;

		this.cuotaseleccionada = null;
		this.tablacuotas = null;
    }
	
	public void borrarPlantillaActivo()
	{
    	this.sCOACES = "";

    	this.sCOPOIN = "";
    	this.sNOMUIN = "";
    	this.sNOPRAC = "";
    	this.sNOVIAS = "";
    	this.sNUPIAC = "";
    	this.sNUPOAC = "";
    	this.sNUPUAC = "";
	}
	
    public void limpiarPlantillaActivo(ActionEvent actionEvent) 
    {  
    	borrarPlantillaActivo();
    	
    	this.activoseleccionado = null;
    	this.tablaactivos = null;
   	
    }
	
	public void cambiaGrupo()
	{
		tiposcotpgaHM = new LinkedHashMap<String, String>();
		tiposcosbgaHM = new LinkedHashMap<String, String>();
	}
	
	public void cambiaTipo()
	{
		String sMethod = "cambiaTipo";
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "sCOGRUG:|"+sCOGRUG+"|");
		if (sCOGRUG !=null && !sCOGRUG.equals(""))
		{
			switch (Integer.parseInt(sCOGRUG)) 
			{
				case 1:
					tiposcotpgaHM = tiposcotpga_g1HM;
					break;
				case 2:
					tiposcotpgaHM = tiposcotpga_g2HM;
					break;
				case 3:
					tiposcotpgaHM = tiposcotpga_g3HM;
					break;
				default:
					tiposcotpgaHM = new LinkedHashMap<String, String>();
					break;
			}
			tiposcosbgaHM = new LinkedHashMap<String, String>();
			sCOTPGA = "";
			sCOSBGA = "";
		}
	}
	
	public void cambiaSubtipo()
	{
		String sMethod = "cambiaTipo";
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "sCOTPGA:|"+sCOGRUG+"| sCOTPGA:|"+sCOTPGA+"|");
		
		if (sCOTPGA !=null && !sCOTPGA.equals(""))
		{
			switch (Integer.parseInt(sCOGRUG+sCOTPGA)) 
			{
				case 11:
					tiposcosbgaHM = tiposcosbga_t11HM;
					break;
				case 12:
					tiposcosbgaHM = tiposcosbga_t12HM;
					break;
				case 21:
					tiposcosbgaHM = tiposcosbga_t21HM;
					break;
				case 22:
					tiposcosbgaHM = tiposcosbga_t22HM;
					break;
				case 23:
					tiposcosbgaHM = tiposcosbga_t23HM;
					break;
				case 32:
					tiposcosbgaHM = tiposcosbga_t32HM;
					break;
				case 33:
					tiposcosbgaHM = tiposcosbga_t33HM;
					break;
				default:
					tiposcosbgaHM = new LinkedHashMap<String, String>();
					break;
			}
			sCOSBGA = "";
		}
	}
	
	public void cambiaFechaPorSituacion()
	{

		if (sCOSIGA !=null && !sCOSIGA.equals(""))
		{
			switch (Integer.parseInt(sCOSIGA)) 
			{
				case 1:
					this.bFEEESI = false;
					this.bFEECOI = true;
					//this.sFEEESI = "";
					this.sFEECOI = "";
					break;
				case 2:
					this.bFEEESI = true;
					this.bFEECOI = false;
					this.sFEEESI = "";
					//this.sFEECOI = "";
					break;
				default:
					this.bFEEESI = true;
					this.bFEECOI = true;
					this.sFEEESI = "";
					this.sFEECOI = "";
					break;
			}

		}
	}
	
	public void cambiaFechaFinPeriodo()
	{

		if (sPTPAGO !=null && !sPTPAGO.equals(""))
		{
			switch (Integer.parseInt(sPTPAGO)) 
			{
				case 8:
					this.bFFGTVP = false;
					break;
				default:
					this.bFFGTVP = true;
					this.sFFGTVP = "";
					break;
			}

		}
	}
	
	
	public void hoyFEDEVE (ActionEvent actionEvent)
	{
		String sMethod = "hoyFEDEVE";
		this.setsFEDEVE(Utils.fechaDeHoy(true));
		Utils.debugTrace(true, sClassName, sMethod, "sFEDEVE:|"+sFEDEVE+"|");
	}

	public void hoyFFGTVP (ActionEvent actionEvent)
	{
		String sMethod = "hoyFFGTVP";
		this.setsFFGTVP(Utils.fechaDeHoy(true));
		Utils.debugTrace(true, sClassName, sMethod, "sFFGTVP:|"+sFFGTVP+"|");
	}

	public void hoyFEPAGA (ActionEvent actionEvent)
	{
		String sMethod = "hoyFEPAGA";
		this.setsFEPAGA(Utils.fechaDeHoy(true));
		Utils.debugTrace(true, sClassName, sMethod, "sFEPAGA:|"+sFEPAGA+"|");
	}

	public void hoyFELIPG (ActionEvent actionEvent)
	{
		String sMethod = "hoyFELIPG";
		this.setsFELIPG(Utils.fechaDeHoy(true));
		Utils.debugTrace(true, sClassName, sMethod, "sFELIPG:|"+sFELIPG+"|");
	}

	public void hoyFEEESI (ActionEvent actionEvent)
	{
		String sMethod = "hoyFEEESI";
		this.setsFEEESI(Utils.fechaDeHoy(true));
		Utils.debugTrace(true, sClassName, sMethod, "sFEEESI:|"+sFEEESI+"|");
	}

	public void hoyFEECOI (ActionEvent actionEvent)
	{
		String sMethod = "hoyFEECOI";
		this.setsFEECOI(Utils.fechaDeHoy(true));
		Utils.debugTrace(true, sClassName, sMethod, "sFEECOI:|"+sFEECOI+"|");
	}

	
	public void buscaActivos (ActionEvent actionEvent)
	{
		
		String sMethod = "buscaActivos";
		
		
		FacesMessage msg;
		
		ActivoTabla buscaactivos = new ActivoTabla(
				sCOACES.toUpperCase(), sCOPOIN.toUpperCase(), sNOMUIN.toUpperCase(),
				sNOPRAC.toUpperCase(), sNOVIAS.toUpperCase(), sNUPIAC.toUpperCase(), 
				sNUPOAC.toUpperCase(), sNUPUAC.toUpperCase(), "");
		
		Utils.debugTrace(true, sClassName, sMethod, "Buscando Activos...");
		
		this.setTablaactivos(CLCuotas.buscarActivosConCuotas(buscaactivos));
		
		Utils.debugTrace(true, sClassName, sMethod, "Encontrados "+getTablaactivos().size()+" activos relacionados.");

		msg = new FacesMessage("Encontrados "+getTablaactivos().size()+" activos relacionados.");
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
	}
	
	public void seleccionarActivo(ActionEvent actionEvent) 
    {  
    	
    	String sMethod = "seleccionarActivo";

    	FacesMessage msg;
    	
    	
    	
    	//this.sCOACESBuscado = activoseleccionado.getCOACES();
    	
    	this.sCOACES  = activoseleccionado.getCOACES();
    	
    	String sCOSPAT = CLActivos.sociedadPatrimonialAsociada(sCOACES); 
    	
    	this.sNUPROF = CLProvisiones.ultimaProvisionAbierta(sCOSPAT);
    			 
    	
    	msg = new FacesMessage("Activo "+ sCOACES +" Seleccionado.");
    	
    	Utils.debugTrace(true, sClassName, sMethod, "Activo seleccionado: |"+sCOACES+"|");
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
		//return "listacomunidadesactivos.xhtml";
    }
	
	public void cargarOperaciones(ActionEvent actionEvent)
	{
		String sMethod = "cargarCuotas";
		
		FacesMessage msg;
		
		String sMsg = "";
		
		Utils.debugTrace(true, sClassName, sMethod, "Buscando cuotas...");
		
		this.tablacuotas = CLCuotas.buscarCuotasActivo(sCOACES.toUpperCase());
		
		sMsg = "Encontradas '"+getTablacuotas().size()+"' cuotas pendientes.";
		Utils.debugTrace(true, sClassName, sMethod, sMsg);
		msg = new FacesMessage(sMsg);
		
		FacesContext.getCurrentInstance().addMessage(null, msg);

		Utils.debugTrace(true, sClassName, sMethod, "Buscando devoluciones...");
		
		this.tabladevoluciones = CLImpuestos.buscarDevolucionesDelActivo(sCOACES.toUpperCase());
		
		sMsg = "Encontradas '"+getTabladevoluciones().size()+"' devoluciones pendientes.";
		Utils.debugTrace(true, sClassName, sMethod, sMsg);
		msg = new FacesMessage(sMsg);
		
		FacesContext.getCurrentInstance().addMessage(null, msg);

		
	}
	
	public void seleccionarCuota(ActionEvent actionEvent) 
    {  
    	
    	String sMethod = "seleccionarCuota";

    	FacesMessage msg;
    	
    	String sMsg = "";

    	this.sCOGRUG = ValoresDefecto.DEF_COGRUG_E2;
    	this.sCOTPGA = ValoresDefecto.DEF_COTACA_E2;
    	this.sCOSBGA = cuotaseleccionada.getCOSBAC();
    	this.sPTPAGO = cuotaseleccionada.getPTPAGO();
    	
    	this.sIMNGAS = cuotaseleccionada.getIMCUCO();

    	
    	tiposcotpgaHM = tiposcotpga_g2HM;
    	tiposcosbgaHM = tiposcosbga_t22HM;
    	

    	//comprobar
    	
    	sMsg = "Cuota de '"+ cuotaseleccionada.getDCOSBAC() +"' Seleccionada.";
    	
    	msg = new FacesMessage(sMsg);
    	
    	Utils.debugTrace(true, sClassName, sMethod, sMsg);
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
    }
	
	public void seleccionarDevolucion(ActionEvent actionEvent) 
    {  
    	
    	String sMethod = "seleccionarDevolucion";

    	FacesMessage msg;
    	
    	String sMsg = "";

    	this.sCOGRUG = ValoresDefecto.DEF_COGRUG_E4;
    	this.sCOTPGA = ValoresDefecto.DEF_COTACA_E4;
    	this.sCOSBGA = devolucionseleccionada.getCOSBAC();
    	this.sPTPAGO = "1";
    	
    	//this.sIMNGAS = "";

    	
    	tiposcotpgaHM = tiposcotpga_g2HM;
    	tiposcosbgaHM = tiposcosbga_t21HM;
    	

    	//comprobar
    	
    	sMsg = "Devolucion de '"+ devolucionseleccionada.getDCOSBAC() +"' Seleccionada.";
    	
    	msg = new FacesMessage(sMsg);
    	
    	Utils.debugTrace(true, sClassName, sMethod, sMsg);
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
    }
	
    public void registraGasto(ActionEvent actionEvent) 
    {  
    	borrarPlantillaGasto();
    	borrarPlantillaActivo();
    	
		this.activoseleccionado = null;
		this.tablaactivos = null;

		this.cuotaseleccionada = null;
		this.tablacuotas = null;
    }

	public void realizaAlta(ActionEvent actionEvent)
	{
		String sMethod = "registraMovimiento";
		
		Utils.standardIO2File("");//Salida por fichero de texto
		
		//MovimientoComunidad movimiento = new MovimientoComunidad (sCODTRN.toUpperCase(), sCOTDOR.toUpperCase(), sIDPROV.toUpperCase(), sCOACCI.toUpperCase(), sCOENGP.toUpperCase(), sCOCLDO.toUpperCase(), sNUDCOM.toUpperCase(), sBITC10.toUpperCase(), sCOACES.toUpperCase(), sBITC01.toUpperCase(), sNOMCOC.toUpperCase(), sBITC02.toUpperCase(), sNODCCO.toUpperCase(), sBITC03.toUpperCase(), sNOMPRC.toUpperCase(), sBITC04.toUpperCase(), sNUTPRC.toUpperCase(), sBITC05.toUpperCase(), sNOMADC.toUpperCase(), sBITC06.toUpperCase(), sNUTADC.toUpperCase(), sBITC07.toUpperCase(), sNODCAD.toUpperCase(), sBITC08.toUpperCase(), sNUCCEN.toUpperCase(), sNUCCOF.toUpperCase(), sNUCCDI.toUpperCase(), sNUCCNT.toUpperCase(), sBITC09.toUpperCase(), sOBTEXC.toUpperCase(), sOBDEER.toUpperCase());
		MovimientoGasto movimiento = new MovimientoGasto (
				sCOACES.toUpperCase(),
				sCOGRUG.toUpperCase(),
				sCOTPGA.toUpperCase(),
				sCOSBGA.toUpperCase(),
				sPTPAGO.toUpperCase(),
				sFEDEVE.toUpperCase(),
				sFFGTVP.toUpperCase(),
				sFEPAGA.toUpperCase(),
				sFELIPG.toUpperCase(),
				sCOSIGA.toUpperCase(),
				sFEEESI.toUpperCase(),
				sFEECOI.toUpperCase(),
				sFEEAUI.toUpperCase(),
				sFEEPAI.toUpperCase(),
				sIMNGAS.toUpperCase(),
				sYCOS02.toUpperCase(),
				sIMRGAS.toUpperCase(),
				sYCOS04.toUpperCase(),
				sIMDGAS.toUpperCase(),
				sYCOS06.toUpperCase(),
				sIMCOST.toUpperCase(),
				sYCOS08.toUpperCase(),
				sIMOGAS.toUpperCase(),
				sYCOS10.toUpperCase(),
				sIMDTGA.toUpperCase(),
				sCOUNMO.toUpperCase(),
				sIMIMGA.toUpperCase(),
				sCOIMPT.toUpperCase(),
				sCOTNEG.toUpperCase(),
				sCOENCX.toUpperCase(),
				sCOOFCX.toUpperCase(),
				sNUCONE.toUpperCase(),
				sNUPROF.toUpperCase(),
				sFEAGTO.toUpperCase(),
				sCOMONA.toUpperCase(),
				sBIAUTO.toUpperCase(),
				sFEAUFA.toUpperCase(),
				sCOTERR.toUpperCase(),
				sFMPAGN.toUpperCase(),
				sFEPGPR.toUpperCase(),
				sFEAPLI.toUpperCase(),
				sCOAPII.toUpperCase(),
				sCOSPII.toUpperCase(),
				sNUCLII.toUpperCase());
		
		FacesMessage msg;
		
		String sMsg = "";
		
		int iSalida = CLGastos.registraMovimiento(movimiento);
		
		Utils.debugTrace(true, sClassName, sMethod, "Codigo de salida:"+iSalida);
		
		switch (iSalida) 
		{
		case 0: //Sin errores
			sMsg = "La cuota se ha creado correctamente.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(sMsg,null);
			break;

		case -1: //Error 001 - CODIGO DE ACCION DEBE SER A,M o B
			sMsg = "ERROR:001 - No se ha elegido una acccion correcta. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;

		case -3: //Error 003 - NO EXISTE EL ACTIVO
			sMsg = "ERROR:003 - El activo elegido no esta registrado en el sistema. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;


		case -4: //Error 004 - CIF DE LA COMUNIDAD NO PUEDE SER BLANCO O NULO
			sMsg = "ERROR:004 - No se ha informado el numero de documento. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;


		case -33: //Error 033 - LA FECHA DE PRIMER PAGO DEBE SER LOGICA Y OBLIGATORIA
			sMsg = "ERROR:033 - La fecha del primer pago es obligatoria. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;


		case -34: //Error 034 - LA FECHA DE ULTIMO PAGO DEBE SER LOGICA Y OBLIGATORIA
			sMsg = "ERROR:034 - La fecha del ultimo pago es obligatoria. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;


		case -35: //Error 035 - LA FECHA DE ULTIMO PAGO NO DEBE DE SER MENOR QUE LA FECHA DE PRIMER PAGO
			sMsg = "ERROR:35 - La fecha del ultimo pago no puede ser menor que la del primero.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;


		case -36: //Error 036 - IMPORTE DE CUOTA TIENE QUE SER MAYOR DE CERO
			sMsg = "ERROR:036 - El importe de la cuota tiene ser mayor que cero. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;


		case -41: //Error 041 - LA COMUNIDAD NO EXISTE EN LA TABLA DE COMUNIDADES GMAE10
			sMsg = "ERROR:041 - La comunidad propocionada no esta registrada en el sistema. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;


		case -42: //Error 042 - LA RELACION ACTIVO-COMUNIDAD YA EXISTE EN GMAE12. NO SE PUEDE REALIZAR EL ALTA
			sMsg = "ERROR:042 - El activo proporcionado esta asociado a otra comunidad. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;

		case -43: //Error 043 - LA RELACION ACTIVO-COMUNIDAD NO EXISTE EN GMAE12. NO SE PUEDE REALIZAR LA MODIFICACION
			sMsg = "ERROR:043 - El activo prorcionado no pertenece a la comunidad. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;

		case -44: //Error 044 - NO EXISTE PERIOCIDAD DE PAGO
			sMsg = "ERROR:044 - La periodicidad de pago es obligatoria. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;

		case -45: //Error 045 - LA RELACION ACTIVO-COMUNIDAD NO EXISTE EN GMAE12. NO SE PUEDE REALIZAR LA BAJA
			sMsg = "ERROR:045 - El activo prorcionado no pertenece a la comunidad. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;
			
		case -46: //Error 046 - LA FECHA DEL ACTA DEBE SER LOGICA Y OBLIGATORIA 
			sMsg = "ERROR:046 - La fecha de acta es obligatoria. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;
			
		case -801: //Error 801 - alta de una cuota en alta
			sMsg = "ERROR:801 - La cuota ya esta dada de alta. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;

		case -802: //Error 802 - cuota de baja no puede recibir mas movimientos
			sMsg = "ERROR:802 - La cuota esta baja y no puede recibir mas movimientos. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;
			
		case -803: //Error 803 - estado no disponible
			sMsg = "ERROR:803 - El estado de la cuota informada no esta disponible. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;

		case -804: //Error 804 - modificacion sin cambios
			sMsg = "ERROR:804 - No hay modificaciones que realizar. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;

		case -805: //Error 805 - error en importe
			sMsg = "ERROR:805 - El campo importe no se ha informado correctamente. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;
			
		case -900: //Error 900 - al crear un movimiento
			sMsg = "ERROR:900 - Se ha producido un error al registrar el movimiento. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, sMsg,null);
			break;

		case -901: //Error 901 - error y rollback - error al crear la cuota
			sMsg = "ERROR:901 - Se ha producido un error al registrar la cuota. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, sMsg,null);
			break;
			
		case -902: //Error 902 - error y rollback - error al registrar la relaccion
			sMsg = "ERROR:902 - Se ha producido un error al registrar la relacion. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, sMsg,null);
			break;

		case -903: //Error 903 - error y rollback - error al cambiar el estado
			sMsg = "ERROR:903 - Se ha producido un error al cambiar el estado de la cuota. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, sMsg,null);
			break;

		case -904: //Error 904 - error y rollback - error al modificar la cuota
			sMsg = "ERROR:904 - Se ha producido un error al modificar la cuota. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, sMsg,null);
			break;

		default: //error generico
			sMsg = "ERROR:"+iSalida+" - La operacion solicitada ha producido un error desconocido. Por favor, revise los datos."; 
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, sMsg,null);
			break;
		}
		
		
		Utils.debugTrace(true, sClassName, sMethod, "Finalizadas las comprobaciones.");
		FacesContext.getCurrentInstance().addMessage(null, msg);

	}
    
	public String getsCOACES() {
		return sCOACES;
	}

	public void setsCOACES(String sCOACES) {
		this.sCOACES = sCOACES;
	}

	public String getsCOGRUG() {
		return sCOGRUG;
	}

	public void setsCOGRUG(String sCOGRUG) {
		this.sCOGRUG = sCOGRUG;
	}

	public String getsCOTPGA() {
		return sCOTPGA;
	}

	public void setsCOTPGA(String sCOTPGA) {
		this.sCOTPGA = sCOTPGA;
	}

	public String getsCOSBGA() {
		return sCOSBGA;
	}

	public void setsCOSBGA(String sCOSBGA) {
		this.sCOSBGA = sCOSBGA;
	}

	public String getsPTPAGO() {
		return sPTPAGO;
	}

	public void setsPTPAGO(String sPTPAGO) {
		this.sPTPAGO = sPTPAGO;
	}

	public String getsFEDEVE() {
		return sFEDEVE;
	}

	public void setsFEDEVE(String sFEDEVE) {
		this.sFEDEVE = sFEDEVE;
	}

	public String getsFFGTVP() {
		return sFFGTVP;
	}

	public void setsFFGTVP(String sFFGTVP) {
		this.sFFGTVP = sFFGTVP;
	}

	public String getsFEPAGA() {
		return sFEPAGA;
	}

	public void setsFEPAGA(String sFEPAGA) {
		this.sFEPAGA = sFEPAGA;
	}

	public String getsFELIPG() {
		return sFELIPG;
	}

	public void setsFELIPG(String sFELIPG) {
		this.sFELIPG = sFELIPG;
	}

	public String getsCOSIGA() {
		return sCOSIGA;
	}

	public void setsCOSIGA(String sCOSIGA) {
		this.sCOSIGA = sCOSIGA;
	}

	public String getsFEEESI() {
		return sFEEESI;
	}

	public void setsFEEESI(String sFEEESI) {
		this.sFEEESI = sFEEESI;
	}

	public String getsFEECOI() {
		return sFEECOI;
	}

	public void setsFEECOI(String sFEECOI) {
		this.sFEECOI = sFEECOI;
	}

	public String getsFEEAUI() {
		return sFEEAUI;
	}

	public void setsFEEAUI(String sFEEAUI) {
		this.sFEEAUI = sFEEAUI;
	}

	public String getsFEEPAI() {
		return sFEEPAI;
	}

	public void setsFEEPAI(String sFEEPAI) {
		this.sFEEPAI = sFEEPAI;
	}

	public String getsIMNGAS() {
		return sIMNGAS;
	}

	public void setsIMNGAS(String sIMNGAS) {
		this.sIMNGAS = sIMNGAS;
	}

	public String getsYCOS02() {
		return sYCOS02;
	}

	public void setsYCOS02(String sYCOS02) {
		this.sYCOS02 = sYCOS02;
	}

	public String getsIMRGAS() {
		return sIMRGAS;
	}

	public void setsIMRGAS(String sIMRGAS) {
		this.sIMRGAS = sIMRGAS;
	}

	public String getsYCOS04() {
		return sYCOS04;
	}

	public void setsYCOS04(String sYCOS04) {
		this.sYCOS04 = sYCOS04;
	}

	public String getsIMDGAS() {
		return sIMDGAS;
	}

	public void setsIMDGAS(String sIMDGAS) {
		this.sIMDGAS = sIMDGAS;
	}

	public String getsYCOS06() {
		return sYCOS06;
	}

	public void setsYCOS06(String sYCOS06) {
		this.sYCOS06 = sYCOS06;
	}

	public String getsIMCOST() {
		return sIMCOST;
	}

	public void setsIMCOST(String sIMCOST) {
		this.sIMCOST = sIMCOST;
	}

	public String getsYCOS08() {
		return sYCOS08;
	}

	public void setsYCOS08(String sYCOS08) {
		this.sYCOS08 = sYCOS08;
	}

	public String getsIMOGAS() {
		return sIMOGAS;
	}

	public void setsIMOGAS(String sIMOGAS) {
		this.sIMOGAS = sIMOGAS;
	}

	public String getsYCOS10() {
		return sYCOS10;
	}

	public void setsYCOS10(String sYCOS10) {
		this.sYCOS10 = sYCOS10;
	}

	public String getsIMDTGA() {
		return sIMDTGA;
	}

	public void setsIMDTGA(String sIMDTGA) {
		this.sIMDTGA = sIMDTGA;
	}

	public String getsCOUNMO() {
		return sCOUNMO;
	}

	public void setsCOUNMO(String sCOUNMO) {
		this.sCOUNMO = sCOUNMO;
	}

	public String getsIMIMGA() {
		return sIMIMGA;
	}

	public void setsIMIMGA(String sIMIMGA) {
		this.sIMIMGA = sIMIMGA;
	}

	public String getsCOIMPT() {
		return sCOIMPT;
	}

	public void setsCOIMPT(String sCOIMPT) {
		this.sCOIMPT = sCOIMPT;
	}

	public String getsCOTNEG() {
		return sCOTNEG;
	}

	public void setsCOTNEG(String sCOTNEG) {
		this.sCOTNEG = sCOTNEG;
	}

	public String getsCOENCX() {
		return sCOENCX;
	}

	public void setsCOENCX(String sCOENCX) {
		this.sCOENCX = sCOENCX;
	}

	public String getsCOOFCX() {
		return sCOOFCX;
	}

	public void setsCOOFCX(String sCOOFCX) {
		this.sCOOFCX = sCOOFCX;
	}

	public String getsNUCONE() {
		return sNUCONE;
	}

	public void setsNUCONE(String sNUCONE) {
		this.sNUCONE = sNUCONE;
	}

	public String getsNUPROF() {
		return sNUPROF;
	}

	public void setsNUPROF(String sNUPROF) {
		this.sNUPROF = sNUPROF;
	}

	public String getsFEAGTO() {
		return sFEAGTO;
	}

	public void setsFEAGTO(String sFEAGTO) {
		this.sFEAGTO = sFEAGTO;
	}

	public String getsCOMONA() {
		return sCOMONA;
	}

	public void setsCOMONA(String sCOMONA) {
		this.sCOMONA = sCOMONA;
	}

	public String getsBIAUTO() {
		return sBIAUTO;
	}

	public void setsBIAUTO(String sBIAUTO) {
		this.sBIAUTO = sBIAUTO;
	}

	public String getsFEAUFA() {
		return sFEAUFA;
	}

	public void setsFEAUFA(String sFEAUFA) {
		this.sFEAUFA = sFEAUFA;
	}

	public String getsCOTERR() {
		return sCOTERR;
	}

	public void setsCOTERR(String sCOTERR) {
		this.sCOTERR = sCOTERR;
	}

	public String getsFMPAGN() {
		return sFMPAGN;
	}

	public void setsFMPAGN(String sFMPAGN) {
		this.sFMPAGN = sFMPAGN;
	}

	public String getsFEPGPR() {
		return sFEPGPR;
	}

	public void setsFEPGPR(String sFEPGPR) {
		this.sFEPGPR = sFEPGPR;
	}

	public String getsFEAPLI() {
		return sFEAPLI;
	}

	public void setsFEAPLI(String sFEAPLI) {
		this.sFEAPLI = sFEAPLI;
	}

	public String getsCOAPII() {
		return sCOAPII;
	}

	public void setsCOAPII(String sCOAPII) {
		this.sCOAPII = sCOAPII;
	}

	public String getsCOSPII() {
		return sCOSPII;
	}

	public void setsCOSPII(String sCOSPII) {
		this.sCOSPII = sCOSPII;
	}

	public String getsNUCLII() {
		return sNUCLII;
	}

	public void setsNUCLII(String sNUCLII) {
		this.sNUCLII = sNUCLII;
	}

	public Map<String, String> getTiposcotpgaHM() {
		return tiposcotpgaHM;
	}

	public void setTiposcotpgaHM(Map<String, String> tiposcotpgaHM) {
		this.tiposcotpgaHM = tiposcotpgaHM;
	}

	public Map<String, String> getTiposcosbgaHM() {
		return tiposcosbgaHM;
	}

	public void setTiposcosbgaHM(Map<String, String> tiposcosbgaHM) {
		this.tiposcosbgaHM = tiposcosbgaHM;
	}

	public Map<String, String> getTiposcotpga_g1HM() {
		return tiposcotpga_g1HM;
	}

	public void setTiposcotpga_g1HM(Map<String, String> tiposcotpga_g1HM) {
		this.tiposcotpga_g1HM = tiposcotpga_g1HM;
	}

	public Map<String, String> getTiposcotpga_g2HM() {
		return tiposcotpga_g2HM;
	}

	public void setTiposcotpga_g2HM(Map<String, String> tiposcotpga_g2HM) {
		this.tiposcotpga_g2HM = tiposcotpga_g2HM;
	}

	public Map<String, String> getTiposcotpga_g3HM() {
		return tiposcotpga_g3HM;
	}

	public void setTiposcotpga_g3HM(Map<String, String> tiposcotpga_g3HM) {
		this.tiposcotpga_g3HM = tiposcotpga_g3HM;
	}

	public Map<String, String> getTiposcosbga_t11HM() {
		return tiposcosbga_t11HM;
	}

	public void setTiposcosbga_t11HM(Map<String, String> tiposcosbga_t11HM) {
		this.tiposcosbga_t11HM = tiposcosbga_t11HM;
	}

	public Map<String,String> getTiposcosbga_t12HM() {
		return tiposcosbga_t12HM;
	}
	public void setTiposcosbga_t12HM(Map<String,String> tiposcosbga_t12HM) {
		this.tiposcosbga_t12HM = tiposcosbga_t12HM;
	}
	
	public Map<String, String> getTiposcosbga_t21HM() {
		return tiposcosbga_t21HM;
	}

	public void setTiposcosbga_t21HM(Map<String, String> tiposcosbga_t21HM) {
		this.tiposcosbga_t21HM = tiposcosbga_t21HM;
	}

	public Map<String, String> getTiposcosbga_t22HM() {
		return tiposcosbga_t22HM;
	}

	public void setTiposcosbga_t22HM(Map<String, String> tiposcosbga_t22HM) {
		this.tiposcosbga_t22HM = tiposcosbga_t22HM;
	}

	public Map<String, String> getTiposcosbga_t23HM() {
		return tiposcosbga_t23HM;
	}

	public void setTiposcosbga_t23HM(Map<String, String> tiposcosbga_t23HM) {
		this.tiposcosbga_t23HM = tiposcosbga_t23HM;
	}

	public Map<String, String> getTiposcosbga_t32HM() {
		return tiposcosbga_t32HM;
	}

	public void setTiposcosbga_t32HM(Map<String, String> tiposcosbga_t32HM) {
		this.tiposcosbga_t32HM = tiposcosbga_t32HM;
	}
	
	public Map<String, String> getTiposcosbga_t33HM() {
		return tiposcosbga_t33HM;
	}
	public void setTiposcosbga_t33HM(Map<String, String> tiposcosbga_t33HM) {
		this.tiposcosbga_t33HM = tiposcosbga_t33HM;
	}
	

	public ActivoTabla getActivoseleccionado() {
		return activoseleccionado;
	}
	public void setActivoseleccionado(ActivoTabla activoseleccionado) {
		this.activoseleccionado = activoseleccionado;
	}
	public ArrayList<ActivoTabla> getTablaactivos() {
		return tablaactivos;
	}
	public void setTablaactivos(ArrayList<ActivoTabla> tablaactivos) {
		this.tablaactivos = tablaactivos;
	}
	public CuotaTabla getCuotaseleccionada() {
		return cuotaseleccionada;
	}
	public void setCuotaseleccionada(CuotaTabla cuotaseleccionada) {
		this.cuotaseleccionada = cuotaseleccionada;
	}
	public ArrayList<CuotaTabla> getTablacuotas() {
		return tablacuotas;
	}
	public void setTablacuotas(ArrayList<CuotaTabla> tablacuotas) {
		this.tablacuotas = tablacuotas;
	}
	public String getsCOSBAC() {
		return sCOSBAC;
	}
	public void setsCOSBAC(String sCOSBAC) {
		this.sCOSBAC = sCOSBAC;
	}

	public String getsIMCUCO() {
		return sIMCUCO;
	}
	public void setsIMCUCO(String sIMCUCO) {
		this.sIMCUCO = sIMCUCO;
	}

	public String getsCOPOIN() {
		return sCOPOIN;
	}
	public void setsCOPOIN(String sCOPOIN) {
		this.sCOPOIN = sCOPOIN;
	}
	public String getsNOMUIN() {
		return sNOMUIN;
	}
	public void setsNOMUIN(String sNOMUIN) {
		this.sNOMUIN = sNOMUIN;
	}
	public String getsNOPRAC() {
		return sNOPRAC;
	}
	public void setsNOPRAC(String sNOPRAC) {
		this.sNOPRAC = sNOPRAC;
	}
	public String getsNOVIAS() {
		return sNOVIAS;
	}
	public void setsNOVIAS(String sNOVIAS) {
		this.sNOVIAS = sNOVIAS;
	}
	public String getsNUPIAC() {
		return sNUPIAC;
	}
	public void setsNUPIAC(String sNUPIAC) {
		this.sNUPIAC = sNUPIAC;
	}
	public String getsNUPOAC() {
		return sNUPOAC;
	}
	public void setsNUPOAC(String sNUPOAC) {
		this.sNUPOAC = sNUPOAC;
	}
	public String getsNUPUAC() {
		return sNUPUAC;
	}
	public void setsNUPUAC(String sNUPUAC) {
		this.sNUPUAC = sNUPUAC;
	}
	public boolean isbFEEESI() {
		return bFEEESI;
	}
	public void setbFEEESI(boolean bFEEESI) {
		this.bFEEESI = bFEEESI;
	}
	public boolean isbFEECOI() {
		return bFEECOI;
	}
	public void setbFEECOI(boolean bFEECOI) {
		this.bFEECOI = bFEECOI;
	}
	public boolean isbFFGTVP() {
		return bFFGTVP;
	}
	public void setbFFGTVP(boolean bFFGTVP) {
		this.bFFGTVP = bFFGTVP;
	}
	public ImpuestoRecursoTabla getDevolucionseleccionada() {
		return devolucionseleccionada;
	}
	public void setDevolucionseleccionada(ImpuestoRecursoTabla devolucionseleccionada) {
		this.devolucionseleccionada = devolucionseleccionada;
	}
	public ArrayList<ImpuestoRecursoTabla> getTabladevoluciones() {
		return tabladevoluciones;
	}
	public void setTabladevoluciones(ArrayList<ImpuestoRecursoTabla> tabladevoluciones) {
		this.tabladevoluciones = tabladevoluciones;
	}
	public Map<String, String> getTiposcosigaHM() {
		return tiposcosigaHM;
	}
	public void setTiposcosigaHM(Map<String, String> tiposcosigaHM) {
		this.tiposcosigaHM = tiposcosigaHM;
	}




}
