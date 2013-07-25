package com.provisiones.dal.qm;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.misc.Utils;
import com.provisiones.types.DatosActivo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QMDatosActivos
{
	static String sClassName = QMDatosActivos.class.getName();

	static String sTable = "ac_datos_tbl";

	static String sField1 = "ac_datos_id";

	static String sField2  = "nuinmu";
	static String sField3  = "cod_cosopa";
	static String sField4  = "cod_coenae";
	static String sField5  = "cod_coesen";
	static String sField6  = "novias";
	static String sField7  = "nupoac";
	static String sField8  = "nuesac";
	static String sField9  = "nupiac";
	static String sField10 = "nupuac";
	static String sField11 = "nomuin";
	static String sField12 = "cod_coprae";
	static String sField13 = "noprac";
	static String sField14 = "copoin";
	static String sField15 = "fereap";
	static String sField16 = "cod_coreae";
	static String sField17 = "feinau";
	static String sField18 = "fesopo";
	static String sField19 = "fesepo";
	static String sField20 = "ferepo";
	static String sField21 = "feadac";
	static String sField22 = "cod_codiju";
	static String sField23 = "cod_cosjup";
	static String sField24 = "cod_costli";
	static String sField25 = "cod_coscar";
	static String sField26 = "cod_coesve";
	static String sField27 = "cod_cotsin";
	static String sField28 = "nufire";
	static String sField29 = "nuregp";
	static String sField30 = "nomui0";
	static String sField31 = "nulibe";
	static String sField32 = "nutome";
	static String sField33 = "nufole";
	static String sField34 = "nuinsr";
	static String sField35 = "cod_cosocu";
	static String sField36 = "cod_coxpro";
	static String sField37 = "fesola";
	static String sField38 = "fesela";
	static String sField39 = "ferela";
	static String sField40 = "ferlla";
	static String sField41 = "caspre";
	static String sField42 = "casutr";
	static String sField43 = "casutc";
	static String sField44 = "casutg";
	static String sField45 = "cod_biarre";
	static String sField46 = "cadorm";
	static String sField47 = "cabano";
	static String sField48 = "cod_bigapa";
	static String sField49 = "cagapa";
	static String sField50 = "casute";
	static String sField51 = "cod_bilipo";
	static String sField52 = "cod_biliac";
	static String sField53 = "cod_bilius";
	static String sField54 = "cod_biboin";
	static String sField55 = "cod_bicefi";
	static String sField56 = "casucb";
	static String sField57 = "casucs";
	static String sField58 = "feacon";
	static String sField59 = "idauto";
	static String sField60 = "fedema";
	static String sField61 = "ynocur";
	static String sField62 = "obreco";
	static String sField63 = "ynolec";
	static String sField64 = "nolojz";
	static String sField65 = "ferede";
	static String sField66 = "poprop";
	static String sField67 = "cod_cograp";
	static String sField68 = "fepreg";
	static String sField69 = "fephac";
	static String sField70 = "fefoac";
	static String sField71 = "fevact";
	static String sField72 = "imvact";
	static String sField73 = "nufipr";
	static String sField74 = "cod_cotpet";
	static String sField75 = "feempt";
	static String sField76 = "fesorc";
	static String sField77 = "fesode";
	static String sField78 = "fereac";
	static String sField79 = "cod_coxsia";
	static String sField80 = "nujuzd";
	static String sField81 = "nurcat";
	static String sField82 = "nomprc";
	static String sField83 = "nutprc";
	static String sField84 = "nomadc";
	static String sField85 = "nutadc";
	static String sField86 = "impcoo";
	static String sField87 = "coenor";
	static String sField88 = "cod_cospat";
	static String sField89 = "cod_cospas";
	static String sField90 = "idcol3";
	static String sField91 = "cod_biobnu";
	static String sField92 = "pobrar";

	public static boolean addDatosActivo (DatosActivo NuevoActivo) 
	 
	{
		String sMethod = "addActivo";
		Statement stmt = null;
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();
		
		try 
		{
			
			stmt = conn.createStatement();
			stmt.executeUpdate("INSERT INTO " + sTable + " ("
				       + sField2  + ","              
				       + sField3  + ","              
				       + sField4  + ","              
				       + sField5  + ","              
				       + sField6  + ","              
				       + sField7  + ","              
				       + sField8  + ","              
				       + sField9  + ","              
				       + sField10 + ","              
				       + sField11 + ","              
				       + sField12 + ","              
				       + sField13 + ","              
				       + sField14 + ","              
				       + sField15 + ","              
				       + sField16 + ","              
				       + sField17 + ","              
				       + sField18 + ","              
				       + sField19 + ","              
				       + sField20 + ","              
				       + sField21 + ","              
				       + sField22 + ","              
				       + sField23 + ","              
				       + sField24 + ","              
				       + sField25 + ","              
				       + sField26 + ","              
				       + sField27 + ","              
				       + sField28 + ","              
				       + sField29 + ","              
				       + sField30 + ","              
				       + sField31 + ","              
				       + sField32 + ","              
				       + sField33 + ","              
				       + sField34 + ","              
				       + sField35 + ","              
				       + sField36 + ","              
				       + sField37 + ","              
				       + sField38 + ","              
				       + sField39 + ","              
				       + sField40 + ","              
				       + sField41 + ","              
				       + sField42 + ","              
				       + sField43 + ","              
				       + sField44 + ","              
				       + sField45 + ","              
				       + sField46 + ","              
				       + sField47 + ","              
				       + sField48 + ","              
				       + sField49 + ","              
				       + sField50 + ","              
				       + sField51 + ","              
				       + sField52 + ","              
				       + sField53 + ","              
				       + sField54 + ","              
				       + sField55 + ","              
				       + sField56 + ","              
				       + sField57 + ","              
				       + sField58 + ","              
				       + sField59 + ","              
				       + sField60 + ","              
				       + sField61 + ","              
				       + sField62 + ","              
				       + sField63 + ","              
				       + sField64 + ","              
				       + sField65 + ","              
				       + sField66 + ","              
				       + sField67 + ","              
				       + sField68 + ","              
				       + sField69 + ","              
				       + sField70 + ","              
				       + sField71 + ","              
				       + sField72 + ","              
				       + sField73 + ","              
				       + sField74 + ","              
				       + sField75 + ","              
				       + sField76 + ","              
				       + sField77 + ","              
				       + sField78 + ","              
				       + sField79 + ","              
				       + sField80 + ","              
				       + sField81 + ","              
				       + sField82 + ","              
				       + sField83 + ","              
				       + sField84 + ","              
				       + sField85 + ","              
				       + sField86 + ","              
				       + sField87 + ","              
				       + sField88 + ","              
				       + sField89 + ","              
				       + sField90 + ","              
				       + sField91 + ","              
				       + sField92 +                  
				       					") VALUES ('"        
				       + NuevoActivo.getNUINMU() + "','"  
				       + NuevoActivo.getCOSOPA() + "','"  
				       + NuevoActivo.getCOENAE() + "','"  
				       + NuevoActivo.getCOESEN() + "','"  
				       + NuevoActivo.getNOVIAS() + "','"  
				       + NuevoActivo.getNUPOAC() + "','"  
				       + NuevoActivo.getNUESAC() + "','"  
				       + NuevoActivo.getNUPIAC() + "','"  
				       + NuevoActivo.getNUPUAC() + "','"  
				       + NuevoActivo.getNOMUIN() + "','"  
				       + NuevoActivo.getCOPRAE() + "','"  
				       + NuevoActivo.getNOPRAC() + "','"  
				       + NuevoActivo.getCOPOIN() + "','"  
				       + NuevoActivo.getFEREAP() + "','"  
				       + NuevoActivo.getCOREAE() + "','"  
				       + NuevoActivo.getFEINAU() + "','"  
				       + NuevoActivo.getFESOPO() + "','"  
				       + NuevoActivo.getFESEPO() + "','"  
				       + NuevoActivo.getFEREPO() + "','"  
				       + NuevoActivo.getFEADAC() + "','"  
				       + NuevoActivo.getCODIJU() + "','"  
				       + NuevoActivo.getCOSJUP() + "','"  
				       + NuevoActivo.getCOSTLI() + "','"  
				       + NuevoActivo.getCOSCAR() + "','"  
				       + NuevoActivo.getCOESVE() + "','"  
				       + NuevoActivo.getCOTSIN() + "','"  
				       + NuevoActivo.getNUFIRE() + "','"  
				       + NuevoActivo.getNUREGP() + "','"  
				       + NuevoActivo.getNOMUI0() + "','"  
				       + NuevoActivo.getNULIBE() + "','"  
				       + NuevoActivo.getNUTOME() + "','"  
				       + NuevoActivo.getNUFOLE() + "','"  
				       + NuevoActivo.getNUINSR() + "','"  
				       + NuevoActivo.getCOSOCU() + "','"  
				       + NuevoActivo.getCOXPRO() + "','"  
				       + NuevoActivo.getFESOLA() + "','"  
				       + NuevoActivo.getFESELA() + "','"  
				       + NuevoActivo.getFERELA() + "','"  
				       + NuevoActivo.getFERLLA() + "','"  
				       + NuevoActivo.getCASPRE() + "','"  
				       + NuevoActivo.getCASUTR() + "','"  
				       + NuevoActivo.getCASUTC() + "','"  
				       + NuevoActivo.getCASUTG() + "','"  
				       + NuevoActivo.getBIARRE() + "','"  
				       + NuevoActivo.getCADORM() + "','"  
				       + NuevoActivo.getCABANO() + "','"  
				       + NuevoActivo.getBIGAPA() + "','"  
				       + NuevoActivo.getCAGAPA() + "','"  
				       + NuevoActivo.getCASUTE() + "','"  
				       + NuevoActivo.getBILIPO() + "','"  
				       + NuevoActivo.getBILIAC() + "','"  
				       + NuevoActivo.getBILIUS() + "','"  
				       + NuevoActivo.getBIBOIN() + "','"  
				       + NuevoActivo.getBICEFI() + "','"  
				       + NuevoActivo.getCASUCB() + "','"  
				       + NuevoActivo.getCASUCS() + "','"  
				       + NuevoActivo.getFEACON() + "','"  
				       + NuevoActivo.getIDAUTO() + "','"  
				       + NuevoActivo.getFEDEMA() + "','"  
				       + NuevoActivo.getYNOCUR() + "','"  
				       + NuevoActivo.getOBRECO() + "','"  
				       + NuevoActivo.getYNOLEC() + "','"  
				       + NuevoActivo.getNOLOJZ() + "','"  
				       + NuevoActivo.getFEREDE() + "','"  
				       + NuevoActivo.getPOPROP() + "','"  
				       + NuevoActivo.getCOGRAP() + "','"  
				       + NuevoActivo.getFEPREG() + "','"  
				       + NuevoActivo.getFEPHAC() + "','"  
				       + NuevoActivo.getFEFOAC() + "','"  
				       + NuevoActivo.getFEVACT() + "','"  
				       + NuevoActivo.getIMVACT() + "','"  
				       + NuevoActivo.getNUFIPR() + "','"  
				       + NuevoActivo.getCOTPET() + "','"  
				       + NuevoActivo.getFEEMPT() + "','"  
				       + NuevoActivo.getFESORC() + "','"  
				       + NuevoActivo.getFESODE() + "','"  
				       + NuevoActivo.getFEREAC() + "','"  
				       + NuevoActivo.getCOXSIA() + "','"  
				       + NuevoActivo.getNUJUZD() + "','"  
				       + NuevoActivo.getNURCAT() + "','"  
				       + NuevoActivo.getNOMPRC() + "','"  
				       + NuevoActivo.getNUTPRC() + "','"  
				       + NuevoActivo.getNOMADC() + "','"  
				       + NuevoActivo.getNUTADC() + "','"  
				       + NuevoActivo.getIMPCOO() + "','"  
				       + NuevoActivo.getCOENOR() + "','"  
				       + NuevoActivo.getCOSPAT() + "','"  
				       + NuevoActivo.getCOSPAS() + "','"  
				       + NuevoActivo.getIDCOL3() + "','"  
				       + NuevoActivo.getBIOBNU() + "','"  
				       + NuevoActivo.getPOBRAR() + "' )");
		} 
		catch (SQLException ex) 
		{

			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COSJUP: " + NuevoActivo.getCOSJUP());
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COTSIN: " + NuevoActivo.getCOTSIN());
			//System.out.println("["+sClassName+"."+sMethod+"] ERROR: COGRAP: " + NuevoActivo.getCOGRAP());
			
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: COACES: " + NuevoActivo.getCOACES());
			
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: SQLException: " + ex.getMessage());
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: SQLState: " + ex.getSQLState());
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: VendorError: " + ex.getErrorCode());			
		} 
		finally 
		{

			Utils.closeStatement(stmt, sClassName, sMethod);
		}
		ConnectionManager.CloseDBConnection(conn);
		return true;
	}
	public static boolean modDatosActivo(DatosActivo NuevoActivo, String sActivoID)
	{
		String sMethod = "modActivo";
		Statement stmt = null;
		boolean bExit = false;
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();
		
		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("UPDATE " + sTable + 
					" SET " 
					+ sField2  + " = '"+ NuevoActivo.getNUINMU() + "','"
					+ sField3  + " = '"+ NuevoActivo.getCOSOPA() + "','"
					+ sField4  + " = '"+ NuevoActivo.getCOENAE() + "','"
					+ sField5  + " = '"+ NuevoActivo.getCOESEN() + "','"
					+ sField6  + " = '"+ NuevoActivo.getNOVIAS() + "','"
					+ sField7  + " = '"+ NuevoActivo.getNUPOAC() + "','"
					+ sField8  + " = '"+ NuevoActivo.getNUESAC() + "','"
					+ sField9  + " = '"+ NuevoActivo.getNUPIAC() + "','"
					+ sField10 + " = '"+ NuevoActivo.getNUPUAC() + "','"
					+ sField11 + " = '"+ NuevoActivo.getNOMUIN() + "','"
					+ sField12 + " = '"+ NuevoActivo.getCOPRAE() + "','"
					+ sField13 + " = '"+ NuevoActivo.getNOPRAC() + "','"
					+ sField14 + " = '"+ NuevoActivo.getCOPOIN() + "','"
					+ sField15 + " = '"+ NuevoActivo.getFEREAP() + "','"
					+ sField16 + " = '"+ NuevoActivo.getCOREAE() + "','"
					+ sField17 + " = '"+ NuevoActivo.getFEINAU() + "','"
					+ sField18 + " = '"+ NuevoActivo.getFESOPO() + "','"
					+ sField19 + " = '"+ NuevoActivo.getFESEPO() + "','"
					+ sField20 + " = '"+ NuevoActivo.getFEREPO() + "','"
					+ sField21 + " = '"+ NuevoActivo.getFEADAC() + "','"
					+ sField22 + " = '"+ NuevoActivo.getCODIJU() + "','"
					+ sField23 + " = '"+ NuevoActivo.getCOSJUP() + "','"
					+ sField24 + " = '"+ NuevoActivo.getCOSTLI() + "','"
					+ sField25 + " = '"+ NuevoActivo.getCOSCAR() + "','"
					+ sField26 + " = '"+ NuevoActivo.getCOESVE() + "','"
					+ sField27 + " = '"+ NuevoActivo.getCOTSIN() + "','"
					+ sField28 + " = '"+ NuevoActivo.getNUFIRE() + "','"
					+ sField29 + " = '"+ NuevoActivo.getNUREGP() + "','"
					+ sField30 + " = '"+ NuevoActivo.getNOMUI0() + "','"
					+ sField31 + " = '"+ NuevoActivo.getNULIBE() + "','"
					+ sField32 + " = '"+ NuevoActivo.getNUTOME() + "','"
					+ sField33 + " = '"+ NuevoActivo.getNUFOLE() + "','"
					+ sField34 + " = '"+ NuevoActivo.getNUINSR() + "','"
					+ sField35 + " = '"+ NuevoActivo.getCOSOCU() + "','"
					+ sField36 + " = '"+ NuevoActivo.getCOXPRO() + "','"
					+ sField37 + " = '"+ NuevoActivo.getFESOLA() + "','"
					+ sField38 + " = '"+ NuevoActivo.getFESELA() + "','"
					+ sField39 + " = '"+ NuevoActivo.getFERELA() + "','"
					+ sField40 + " = '"+ NuevoActivo.getFERLLA() + "','"
					+ sField41 + " = '"+ NuevoActivo.getCASPRE() + "','"
					+ sField42 + " = '"+ NuevoActivo.getCASUTR() + "','"
					+ sField43 + " = '"+ NuevoActivo.getCASUTC() + "','"
					+ sField44 + " = '"+ NuevoActivo.getCASUTG() + "','"
					+ sField45 + " = '"+ NuevoActivo.getBIARRE() + "','"
					+ sField46 + " = '"+ NuevoActivo.getCADORM() + "','"
					+ sField47 + " = '"+ NuevoActivo.getCABANO() + "','"
					+ sField48 + " = '"+ NuevoActivo.getBIGAPA() + "','"
					+ sField49 + " = '"+ NuevoActivo.getCAGAPA() + "','"
					+ sField50 + " = '"+ NuevoActivo.getCASUTE() + "','"
					+ sField51 + " = '"+ NuevoActivo.getBILIPO() + "','"
					+ sField52 + " = '"+ NuevoActivo.getBILIAC() + "','"
					+ sField53 + " = '"+ NuevoActivo.getBILIUS() + "','"
					+ sField54 + " = '"+ NuevoActivo.getBIBOIN() + "','"
					+ sField55 + " = '"+ NuevoActivo.getBICEFI() + "','"
					+ sField56 + " = '"+ NuevoActivo.getCASUCB() + "','"
					+ sField57 + " = '"+ NuevoActivo.getCASUCS() + "','"
					+ sField58 + " = '"+ NuevoActivo.getFEACON() + "','"
					+ sField59 + " = '"+ NuevoActivo.getIDAUTO() + "','"
					+ sField60 + " = '"+ NuevoActivo.getFEDEMA() + "','"
					+ sField61 + " = '"+ NuevoActivo.getYNOCUR() + "','"
					+ sField62 + " = '"+ NuevoActivo.getOBRECO() + "','"
					+ sField63 + " = '"+ NuevoActivo.getYNOLEC() + "','"
					+ sField64 + " = '"+ NuevoActivo.getNOLOJZ() + "','"
					+ sField65 + " = '"+ NuevoActivo.getFEREDE() + "','"
					+ sField66 + " = '"+ NuevoActivo.getPOPROP() + "','"
					+ sField67 + " = '"+ NuevoActivo.getCOGRAP() + "','"
					+ sField68 + " = '"+ NuevoActivo.getFEPREG() + "','"
					+ sField69 + " = '"+ NuevoActivo.getFEPHAC() + "','"
					+ sField70 + " = '"+ NuevoActivo.getFEFOAC() + "','"
					+ sField71 + " = '"+ NuevoActivo.getFEVACT() + "','"
					+ sField72 + " = '"+ NuevoActivo.getIMVACT() + "','"
					+ sField73 + " = '"+ NuevoActivo.getNUFIPR() + "','"
					+ sField74 + " = '"+ NuevoActivo.getCOTPET() + "','"
					+ sField75 + " = '"+ NuevoActivo.getFEEMPT() + "','"
					+ sField76 + " = '"+ NuevoActivo.getFESORC() + "','"
					+ sField77 + " = '"+ NuevoActivo.getFESODE() + "','"
					+ sField78 + " = '"+ NuevoActivo.getFEREAC() + "','"
					+ sField79 + " = '"+ NuevoActivo.getCOXSIA() + "','"
					+ sField80 + " = '"+ NuevoActivo.getNUJUZD() + "','"
					+ sField81 + " = '"+ NuevoActivo.getNURCAT() + "','"
					+ sField82 + " = '"+ NuevoActivo.getNOMPRC() + "','"
					+ sField83 + " = '"+ NuevoActivo.getNUTPRC() + "','"
					+ sField84 + " = '"+ NuevoActivo.getNOMADC() + "','"
					+ sField85 + " = '"+ NuevoActivo.getNUTADC() + "','"
					+ sField86 + " = '"+ NuevoActivo.getIMPCOO() + "','"
					+ sField87 + " = '"+ NuevoActivo.getCOENOR() + "','"
					+ sField88 + " = '"+ NuevoActivo.getCOSPAT() + "','"
					+ sField89 + " = '"+ NuevoActivo.getCOSPAS() + "','"
					+ sField90 + " = '"+ NuevoActivo.getIDCOL3() + "','"
					+ sField91 + " = '"+ NuevoActivo.getBIOBNU() + "','"
					+ sField92 + " = '"+ NuevoActivo.getPOBRAR() + "' "+
					" WHERE "
					+ sField1 + " = '"+ sActivoID +"'");
			
		} 
		catch (SQLException ex) 
		{

			System.out.println("["+sClassName+"."+sMethod+"] ERROR: SQLException: " + ex.getMessage());
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: SQLState: " + ex.getSQLState());
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: VendorError: " + ex.getErrorCode());
		} 
		finally 
		{

			Utils.closeStatement(stmt, sClassName, sMethod);
			bExit = true;
		}
		ConnectionManager.CloseDBConnection(conn);
		return bExit;
	}

	public static boolean delDatosActivo(String sActivoID)
	{
		String sMethod = "delActivo";
		Statement stmt = null;
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();

		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("DELETE FROM " + sTable + 
					" WHERE (" + sField1 + " = '" + sActivoID + "' )");
		} 
		catch (SQLException ex) 
		{

			System.out.println("["+sClassName+"."+sMethod+"] ERROR: SQLException: " + ex.getMessage());
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: SQLState: " + ex.getSQLState());
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: VendorError: " + ex.getErrorCode());
		} 
		finally 
		{

			Utils.closeStatement(stmt, sClassName, sMethod);
		}
		ConnectionManager.CloseDBConnection(conn);
		return true;
	}

	public static DatosActivo getDatosActivo(String sActivoID)
	{//pendiente de coaces, de la tabla activos
		
		String sMethod = "getActivo";

		Statement stmt = null;
		ResultSet rs = null;

		String sCOACES = "";
		String sNUINMU = "";
		String sCOSOPA = "";
		String sCOENAE = "";
		String sCOESEN = "";
		String sNOVIAS = "";
		String sNUPOAC = "";
		String sNUESAC = "";
		String sNUPIAC = "";
		String sNUPUAC = "";
		String sNOMUIN = "";
		String sCOPRAE = "";
		String sNOPRAC = "";
		String sCOPOIN = "";
		String sFEREAP = "";
		String sCOREAE = "";
		String sFEINAU = "";
		String sFESOPO = "";
		String sFESEPO = "";
		String sFEREPO = "";
		String sFEADAC = "";
		String sCODIJU = "";
		String sCOSJUP = "";
		String sCOSTLI = "";
		String sCOSCAR = "";
		String sCOESVE = "";
		String sCOTSIN = "";
		String sNUFIRE = "";
		String sNUREGP = "";
		String sNOMUI0 = "";
		String sNULIBE = "";
		String sNUTOME = "";
		String sNUFOLE = "";
		String sNUINSR = "";
		String sCOSOCU = "";
		String sCOXPRO = "";
		String sFESOLA = "";
		String sFESELA = "";
		String sFERELA = "";
		String sFERLLA = "";
		String sCASPRE = "";
		String sCASUTR = "";
		String sCASUTC = "";
		String sCASUTG = "";
		String sBIARRE = "";
		String sCADORM = "";
		String sCABANO = "";
		String sBIGAPA = "";
		String sCAGAPA = "";
		String sCASUTE = "";
		String sBILIPO = "";
		String sBILIAC = "";
		String sBILIUS = "";
		String sBIBOIN = "";
		String sBICEFI = "";
		String sCASUCB = "";
		String sCASUCS = "";
		String sFEACON = "";
		String sIDAUTO = "";
		String sFEDEMA = "";
		String sYNOCUR = "";
		String sOBRECO = "";
		String sYNOLEC = "";
		String sNOLOJZ = "";
		String sFEREDE = "";
		String sPOPROP = "";
		String sCOGRAP = "";
		String sFEPREG = "";
		String sFEPHAC = "";
		String sFEFOAC = "";
		String sFEVACT = "";
		String sIMVACT = "";
		String sNUFIPR = "";
		String sCOTPET = "";
		String sFEEMPT = "";
		String sFESORC = "";
		String sFESODE = "";
		String sFEREAC = "";
		String sCOXSIA = "";
		String sNUJUZD = "";
		String sNURCAT = "";
		String sNOMPRC = "";
		String sNUTPRC = "";
		String sNOMADC = "";
		String sNUTADC = "";
		String sIMPCOO = "";
		String sCOENOR = "";
		String sCOSPAT = "";
		String sCOSPAS = "";
		String sIDCOL3 = "";
		String sBIOBNU = "";
		String sPOBRAR = "";

		PreparedStatement pstmt = null;
		boolean found = false;
		
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();

		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement("SELECT "
				       + sField2  + ","              
				       + sField3  + ","              
				       + sField4  + ","              
				       + sField5  + ","              
				       + sField6  + ","              
				       + sField7  + ","              
				       + sField8  + ","              
				       + sField9  + ","              
				       + sField10 + ","              
				       + sField11 + ","              
				       + sField12 + ","              
				       + sField13 + ","              
				       + sField14 + ","              
				       + sField15 + ","              
				       + sField16 + ","              
				       + sField17 + ","              
				       + sField18 + ","              
				       + sField19 + ","              
				       + sField20 + ","              
				       + sField21 + ","              
				       + sField22 + ","              
				       + sField23 + ","              
				       + sField24 + ","              
				       + sField25 + ","              
				       + sField26 + ","              
				       + sField27 + ","              
				       + sField28 + ","              
				       + sField29 + ","              
				       + sField30 + ","              
				       + sField31 + ","              
				       + sField32 + ","              
				       + sField33 + ","              
				       + sField34 + ","              
				       + sField35 + ","              
				       + sField36 + ","              
				       + sField37 + ","              
				       + sField38 + ","              
				       + sField39 + ","              
				       + sField40 + ","              
				       + sField41 + ","              
				       + sField42 + ","              
				       + sField43 + ","              
				       + sField44 + ","              
				       + sField45 + ","              
				       + sField46 + ","              
				       + sField47 + ","              
				       + sField48 + ","              
				       + sField49 + ","              
				       + sField50 + ","              
				       + sField51 + ","              
				       + sField52 + ","              
				       + sField53 + ","              
				       + sField54 + ","              
				       + sField55 + ","              
				       + sField56 + ","              
				       + sField57 + ","              
				       + sField58 + ","              
				       + sField59 + ","              
				       + sField60 + ","              
				       + sField61 + ","              
				       + sField62 + ","              
				       + sField63 + ","              
				       + sField64 + ","              
				       + sField65 + ","              
				       + sField66 + ","              
				       + sField67 + ","              
				       + sField68 + ","              
				       + sField69 + ","              
				       + sField70 + ","              
				       + sField71 + ","              
				       + sField72 + ","              
				       + sField73 + ","              
				       + sField74 + ","              
				       + sField75 + ","              
				       + sField76 + ","              
				       + sField77 + ","              
				       + sField78 + ","              
				       + sField79 + ","              
				       + sField80 + ","              
				       + sField81 + ","              
				       + sField82 + ","              
				       + sField83 + ","              
				       + sField84 + ","              
				       + sField85 + ","              
				       + sField86 + ","              
				       + sField87 + ","              
				       + sField88 + ","              
				       + sField89 + ","              
				       + sField90 + ","              
				       + sField91 + ","              
				       + sField92 +        
			"  FROM " + sTable + 
					" WHERE (" + sField1 + " = '" + sActivoID	+ "')");

			rs = pstmt.executeQuery();

			System.out.println("===================================================");
			System.out.println(sField1 + ": " + sActivoID);

			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;

					sCOACES = "";
					sNUINMU = rs.getString(sField2);
					sCOSOPA = rs.getString(sField3);
					sCOENAE = rs.getString(sField4);
					sCOESEN = rs.getString(sField5);
					sNOVIAS = rs.getString(sField6);
					sNUPOAC = rs.getString(sField7);
					sNUESAC = rs.getString(sField8);
					sNUPIAC = rs.getString(sField9);
					sNUPUAC = rs.getString(sField10);
					sNOMUIN = rs.getString(sField11);
					sCOPRAE = rs.getString(sField12);
					sNOPRAC = rs.getString(sField13);
					sCOPOIN = rs.getString(sField14);
					sFEREAP = rs.getString(sField15);
					sCOREAE = rs.getString(sField16);
					sFEINAU = rs.getString(sField17);
					sFESOPO = rs.getString(sField18);
					sFESEPO = rs.getString(sField19);
					sFEREPO = rs.getString(sField20);
					sFEADAC = rs.getString(sField21);
					sCODIJU = rs.getString(sField22);
					sCOSJUP = rs.getString(sField23);
					sCOSTLI = rs.getString(sField24);
					sCOSCAR = rs.getString(sField25);
					sCOESVE = rs.getString(sField26);
					sCOTSIN = rs.getString(sField27);
					sNUFIRE = rs.getString(sField28);
					sNUREGP = rs.getString(sField29);
					sNOMUI0 = rs.getString(sField30);
					sNULIBE = rs.getString(sField31);
					sNUTOME = rs.getString(sField32);
					sNUFOLE = rs.getString(sField33);
					sNUINSR = rs.getString(sField34);
					sCOSOCU = rs.getString(sField35);
					sCOXPRO = rs.getString(sField36);
					sFESOLA = rs.getString(sField37);
					sFESELA = rs.getString(sField38);
					sFERELA = rs.getString(sField39);
					sFERLLA = rs.getString(sField40);
					sCASPRE = rs.getString(sField41);
					sCASUTR = rs.getString(sField42);
					sCASUTC = rs.getString(sField43);
					sCASUTG = rs.getString(sField44);
					sBIARRE = rs.getString(sField45);
					sCADORM = rs.getString(sField46);
					sCABANO = rs.getString(sField47);
					sBIGAPA = rs.getString(sField48);
					sCAGAPA = rs.getString(sField49);
					sCASUTE = rs.getString(sField50);
					sBILIPO = rs.getString(sField51);
					sBILIAC = rs.getString(sField52);
					sBILIUS = rs.getString(sField53);
					sBIBOIN = rs.getString(sField54);
					sBICEFI = rs.getString(sField55);
					sCASUCB = rs.getString(sField56);
					sCASUCS = rs.getString(sField57);
					sFEACON = rs.getString(sField58);
					sIDAUTO = rs.getString(sField59);
					sFEDEMA = rs.getString(sField60);
					sYNOCUR = rs.getString(sField61);
					sOBRECO = rs.getString(sField62);
					sYNOLEC = rs.getString(sField63);
					sNOLOJZ = rs.getString(sField64);
					sFEREDE = rs.getString(sField65);
					sPOPROP = rs.getString(sField66);
					sCOGRAP = rs.getString(sField67);
					sFEPREG = rs.getString(sField68);
					sFEPHAC = rs.getString(sField69);
					sFEFOAC = rs.getString(sField70);
					sFEVACT = rs.getString(sField71);
					sIMVACT = rs.getString(sField72);
					sNUFIPR = rs.getString(sField73);
					sCOTPET = rs.getString(sField74);
					sFEEMPT = rs.getString(sField75);
					sFESORC = rs.getString(sField76);
					sFESODE = rs.getString(sField77);
					sFEREAC = rs.getString(sField78);
					sCOXSIA = rs.getString(sField79);
					sNUJUZD = rs.getString(sField80);
					sNURCAT = rs.getString(sField81);
					sNOMPRC = rs.getString(sField82);
					sNUTPRC = rs.getString(sField83);
					sNOMADC = rs.getString(sField84);
					sNUTADC = rs.getString(sField85);
					sIMPCOO = rs.getString(sField86);
					sCOENOR = rs.getString(sField87);
					sCOSPAT = rs.getString(sField88);
					sCOSPAS = rs.getString(sField89);
					sIDCOL3 = rs.getString(sField90);
					sBIOBNU = rs.getString(sField91);
					sPOBRAR = rs.getString(sField92);


					//System.out.println(sField2 + ": " + sApplication);
					//System.out.println(sField3 + ": " + sContactCode);
					//System.out.println(sField4 + ": " + sProjectCode);
					//System.out.println("===================================================");

				}
			}
			if (found == false) 
			{
				System.out.println("No Information Found");
			}

		} 
		catch (SQLException ex) 
		{

			System.out.println("["+sClassName+"."+sMethod+"] ERROR: SQLException: " + ex.getMessage());
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: SQLState: " + ex.getSQLState());
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: VendorError: " + ex.getErrorCode());
		} 
		finally 
		{
			Utils.closeResultSet(rs,sClassName,sMethod);
			Utils.closeStatement(stmt, sClassName, sMethod);
		}
		ConnectionManager.CloseDBConnection(conn);
		return new DatosActivo(sCOACES, sNUINMU, sCOSOPA, sCOENAE, sCOESEN, sNOVIAS,
				sNUPOAC, sNUESAC, sNUPIAC, sNUPUAC, sNOMUIN, sCOPRAE, sNOPRAC,
				sCOPOIN, sFEREAP, sCOREAE, sFEINAU, sFESOPO, sFESEPO, sFEREPO,
				sFEADAC, sCODIJU, sCOSJUP, sCOSTLI, sCOSCAR, sCOESVE, sCOTSIN,
				sNUFIRE, sNUREGP, sNOMUI0, sNULIBE, sNUTOME, sNUFOLE, sNUINSR,
				sCOSOCU, sCOXPRO, sFESOLA, sFESELA, sFERELA, sFERLLA, sCASPRE,
				sCASUTR, sCASUTC, sCASUTG, sBIARRE, sCADORM, sCABANO, sBIGAPA,
				sCAGAPA, sCASUTE, sBILIPO, sBILIAC, sBILIUS, sBIBOIN, sBICEFI,
				sCASUCB, sCASUCS, sFEACON, sIDAUTO, sFEDEMA, sYNOCUR, sOBRECO,
				sYNOLEC, sNOLOJZ, sFEREDE, sPOPROP, sCOGRAP, sFEPREG, sFEPHAC,
				sFEFOAC, sFEVACT, sIMVACT, sNUFIPR, sCOTPET, sFEEMPT, sFESORC,
				sFESODE, sFEREAC, sCOXSIA, sNUJUZD, sNURCAT, sNOMPRC, sNUTPRC,
				sNOMADC, sNUTADC, sIMPCOO, sCOENOR, sCOSPAT, sCOSPAS, sIDCOL3,
				sBIOBNU, sPOBRAR);
	}

}