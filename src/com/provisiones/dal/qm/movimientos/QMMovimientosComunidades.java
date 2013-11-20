package com.provisiones.dal.qm.movimientos;

import com.provisiones.misc.Utils;
import com.provisiones.types.movimientos.MovimientoComunidad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class QMMovimientosComunidades
{
	private static Logger logger = LoggerFactory.getLogger(QMMovimientosComunidades.class.getName());

	public static final String TABLA = "pp001_e1_movimientos_tbl";

	public static final String CAMPO1 = "e1_movimiento_id";
	
	public static final String CAMPO2 = "cod_codtrn";
	public static final String CAMPO3 = "cod_cotdor";
	public static final String CAMPO4 = "idprov";
	public static final String CAMPO5 = "cod_coacci";
	public static final String CAMPO6 = "coengp";
	public static final String CAMPO7 = "cod_cocldo";
	public static final String CAMPO8 = "cod_nudcom";
	public static final String CAMPO9 = "cod_bitc10";
	public static final String CAMPO10 = "cod_coaces";
	public static final String CAMPO11 = "cod_bitc01";
	public static final String CAMPO12 = "nomcoc";
	public static final String CAMPO13 = "cod_bitc02";
	public static final String CAMPO14 = "nodcco";
	public static final String CAMPO15 = "cod_bitc03";
	public static final String CAMPO16 = "nomprc";
	public static final String CAMPO17 = "cod_bitc04";
	public static final String CAMPO18 = "nutprc";
	public static final String CAMPO19 = "cod_bitc05";
	public static final String CAMPO20 = "nomadc";
	public static final String CAMPO21 = "cod_bitc06";
	public static final String CAMPO22 = "nutadc";
	public static final String CAMPO23 = "cod_bitc07";
	public static final String CAMPO24 = "nodcad";
	public static final String CAMPO25 = "cod_bitc08";
	public static final String CAMPO26 = "nuccen";
	public static final String CAMPO27 = "nuccof";
	public static final String CAMPO28 = "nuccdi";
	public static final String CAMPO29 = "nuccnt";
	public static final String CAMPO30 = "cod_bitc09";
	public static final String CAMPO31 = "obtexc";
	public static final String CAMPO32 = "obdeer";         

	private QMMovimientosComunidades(){}
	
	public static int addMovimientoComunidad(Connection conexion, MovimientoComunidad NuevoMovimientoComunidad)
	{
		int iCodigo = 0;

		if (conexion != null)
		{
			Statement stmt = null;
			ResultSet resulset = null;
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "INSERT INTO " 
					   + TABLA + 
					   " ("
				       + CAMPO2  + ","              
				       + CAMPO3  + ","              
				       + CAMPO4  + ","              
				       + CAMPO5  + ","              
				       + CAMPO6  + ","              
				       + CAMPO7  + ","              
				       + CAMPO8  + ","              
				       + CAMPO9  + ","              
				       + CAMPO10 + ","              
				       + CAMPO11 + ","              
				       + CAMPO12 + ","              
				       + CAMPO13 + ","              
				       + CAMPO14 + ","              
				       + CAMPO15 + ","              
				       + CAMPO16 + ","              
				       + CAMPO17 + ","              
				       + CAMPO18 + ","              
				       + CAMPO19 + ","              
				       + CAMPO20 + ","              
				       + CAMPO21 + ","              
				       + CAMPO22 + ","              
				       + CAMPO23 + ","              
				       + CAMPO24 + ","              
				       + CAMPO25 + ","              
				       + CAMPO26 + ","              
				       + CAMPO27 + ","              
				       + CAMPO28 + ","              
				       + CAMPO29 + ","
				       + CAMPO30 + ","
				       + CAMPO31 + ","
				       + CAMPO32 +               
				       ") VALUES ('" 
				       + NuevoMovimientoComunidad.getCODTRN() + "','" 
				       + NuevoMovimientoComunidad.getCOTDOR() + "','"
				       + NuevoMovimientoComunidad.getIDPROV() + "','"
				       + NuevoMovimientoComunidad.getCOACCI() + "','"
				       + NuevoMovimientoComunidad.getCOENGP() + "','"
				       + NuevoMovimientoComunidad.getCOCLDO() + "','"
				       + NuevoMovimientoComunidad.getNUDCOM() + "','"
				       + NuevoMovimientoComunidad.getBITC10() + "','"
				       + NuevoMovimientoComunidad.getCOACES() + "','"
				       + NuevoMovimientoComunidad.getBITC01() + "','"
				       + NuevoMovimientoComunidad.getNOMCOC() + "','"
				       + NuevoMovimientoComunidad.getBITC02() + "','"
				       + NuevoMovimientoComunidad.getNODCCO() + "','"
				       + NuevoMovimientoComunidad.getBITC03() + "','"
				       + NuevoMovimientoComunidad.getNOMPRC() + "','"
				       + NuevoMovimientoComunidad.getBITC04() + "','"
				       + NuevoMovimientoComunidad.getNUTPRC() + "','"
				       + NuevoMovimientoComunidad.getBITC05() + "','"
				       + NuevoMovimientoComunidad.getNOMADC() + "','"
				       + NuevoMovimientoComunidad.getBITC06() + "','"
				       + NuevoMovimientoComunidad.getNUTADC() + "','"
				       + NuevoMovimientoComunidad.getBITC07() + "','"
				       + NuevoMovimientoComunidad.getNODCAD() + "','"
				       + NuevoMovimientoComunidad.getBITC08() + "','"
				       + NuevoMovimientoComunidad.getNUCCEN() + "','"
				       + NuevoMovimientoComunidad.getNUCCOF() + "','"
				       + NuevoMovimientoComunidad.getNUCCDI() + "','"
				       + NuevoMovimientoComunidad.getNUCCNT() + "','"
				       + NuevoMovimientoComunidad.getBITC09() + "','"
				       + NuevoMovimientoComunidad.getOBTEXC() + "','"
				       + NuevoMovimientoComunidad.getOBDEER() + 
				       "' )";
			
			logger.debug(sQuery);

			try 
			{

				stmt = conexion.createStatement();
				stmt.executeUpdate(sQuery, Statement.RETURN_GENERATED_KEYS);
				
				resulset = stmt.getGeneratedKeys();
				
				logger.debug("Ejecutada con exito!");
				
				if (resulset.next()) 
				{
					iCodigo= resulset.getInt(1);
				} 
			} 
			catch (SQLException ex) 
			{
				iCodigo = 0;

				logger.error("ERROR COCLDO:|"+NuevoMovimientoComunidad.getCOCLDO()+"|");
				logger.error("ERROR NUDCOM:|"+NuevoMovimientoComunidad.getNUDCOM()+"|");
				logger.error("ERROR COACES:|"+NuevoMovimientoComunidad.getCOACES()+"|");
				
				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally
			{
				Utils.closeStatement(stmt);
				Utils.closeResultSet(resulset);
			}	
		}

		logger.error("iCodigo: |" + iCodigo +"|");

		return iCodigo;
	}
	public static boolean modMovimientoComunidad(Connection conexion, MovimientoComunidad NuevoMovimientoComunidad, String sMovimientoComunidadID)
	{
		boolean bSalida = false;
		
		if (conexion != null)
		{
			Statement stmt = null;
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "UPDATE " 
					+ TABLA + 
					" SET " 
					+ CAMPO2  + " = '"+ NuevoMovimientoComunidad.getCODTRN() + "', " 
					+ CAMPO3  + " = '"+ NuevoMovimientoComunidad.getCOTDOR() + "', " 
					+ CAMPO4  + " = '"+ NuevoMovimientoComunidad.getIDPROV() + "', " 
					+ CAMPO5  + " = '"+ NuevoMovimientoComunidad.getCOACCI() + "', " 
					+ CAMPO6  + " = '"+ NuevoMovimientoComunidad.getCOENGP() + "', " 
					+ CAMPO7  + " = '"+ NuevoMovimientoComunidad.getCOCLDO() + "', " 
					+ CAMPO8  + " = '"+ NuevoMovimientoComunidad.getNUDCOM() + "', " 
					+ CAMPO9  + " = '"+ NuevoMovimientoComunidad.getBITC10() + "', " 
					+ CAMPO10 + " = '"+ NuevoMovimientoComunidad.getCOACES() + "', " 
					+ CAMPO11 + " = '"+ NuevoMovimientoComunidad.getBITC01() + "', " 
					+ CAMPO12 + " = '"+ NuevoMovimientoComunidad.getNOMCOC() + "', " 
					+ CAMPO13 + " = '"+ NuevoMovimientoComunidad.getBITC02() + "', " 
					+ CAMPO14 + " = '"+ NuevoMovimientoComunidad.getNODCCO() + "', " 
					+ CAMPO15 + " = '"+ NuevoMovimientoComunidad.getBITC03() + "', " 
					+ CAMPO16 + " = '"+ NuevoMovimientoComunidad.getNOMPRC() + "', " 
					+ CAMPO17 + " = '"+ NuevoMovimientoComunidad.getBITC04() + "', " 
					+ CAMPO18 + " = '"+ NuevoMovimientoComunidad.getNUTPRC() + "', " 
					+ CAMPO19 + " = '"+ NuevoMovimientoComunidad.getBITC05() + "', " 
					+ CAMPO20 + " = '"+ NuevoMovimientoComunidad.getNOMADC() + "', " 
					+ CAMPO21 + " = '"+ NuevoMovimientoComunidad.getBITC06() + "', " 
					+ CAMPO22 + " = '"+ NuevoMovimientoComunidad.getNUTADC() + "', " 
					+ CAMPO23 + " = '"+ NuevoMovimientoComunidad.getBITC07() + "', " 
					+ CAMPO24 + " = '"+ NuevoMovimientoComunidad.getNODCAD() + "', " 
					+ CAMPO25 + " = '"+ NuevoMovimientoComunidad.getBITC08() + "', " 
					+ CAMPO26 + " = '"+ NuevoMovimientoComunidad.getNUCCEN() + "', " 
					+ CAMPO27 + " = '"+ NuevoMovimientoComunidad.getNUCCOF() + "', " 
					+ CAMPO28 + " = '"+ NuevoMovimientoComunidad.getNUCCDI() + "', " 
					+ CAMPO29 + " = '"+ NuevoMovimientoComunidad.getNUCCNT() + "', " 
					+ CAMPO30 + " = '"+ NuevoMovimientoComunidad.getBITC09() + "', " 
					+ CAMPO31 + " = '"+ NuevoMovimientoComunidad.getOBTEXC() + "', " 
					+ CAMPO32 + " = '"+ NuevoMovimientoComunidad.getOBDEER() +
					"' "+
					" WHERE "
					+ CAMPO1 + " = '"+ sMovimientoComunidadID +"'";
			
			logger.debug(sQuery);
			
			try 
			{
				stmt = conexion.createStatement();
				stmt.executeUpdate(sQuery);
				
				logger.debug("Ejecutada con exito!");
				
				bSalida = true;
			} 
			catch (SQLException ex) 
			{
				bSalida = false;
				
				logger.error("ERROR MovimientoComunidadID:|"+sMovimientoComunidadID+"|");
				
				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}	
		}

		return bSalida;
	}

	public static boolean delMovimientoComunidad(Connection conexion, String sMovimientoComunidadID)
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			Statement stmt = null;
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "DELETE FROM " 
					+ TABLA + 
					" WHERE "
					+ CAMPO1 + " = '" + sMovimientoComunidadID + "'";
			
			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();
				stmt.executeUpdate(sQuery);
				
				logger.debug("Ejecutada con exito!");
				
				bSalida = true;
			} 
			catch (SQLException ex) 
			{
				bSalida = false;

				logger.error("ERROR MovimientoComunidadID:|"+sMovimientoComunidadID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}
	
	public static MovimientoComunidad getMovimientoComunidad(Connection conexion, String sMovimientoComunidadID)
	{
		String sCODTRN = "";
		String sCOTDOR = "";
		String sIDPROV = "";
		String sCOACCI = "";
		String sCOENGP = "";
		String sCOCLDO = "";
		String sNUDCOM = "";
		String sBITC10 = "";
		String sCOACES = "";
		String sBITC01 = "";
		String sNOMCOC = "";
		String sBITC02 = "";
		String sNODCCO = "";
		String sBITC03 = "";
		String sNOMPRC = "";
		String sBITC04 = "";
		String sNUTPRC = "";
		String sBITC05 = "";
		String sNOMADC = "";
		String sBITC06 = "";
		String sNUTADC = "";
		String sBITC07 = "";
		String sNODCAD = "";
		String sBITC08 = "";
		String sNUCCEN = "";
		String sNUCCOF = "";
		String sNUCCDI = "";
		String sNUCCNT = "";
		String sBITC09 = "";
		String sOBTEXC = "";
		String sOBDEER = "";

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			boolean bEncontrado = false;
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "
				       + CAMPO2  + ","
				       + CAMPO3  + ","              
				       + CAMPO4  + ","              
				       + CAMPO5  + ","              
				       + CAMPO6  + ","              
				       + CAMPO7  + ","              
				       + CAMPO8  + ","              
				       + CAMPO9  + ","              
				       + CAMPO10 + ","              
				       + CAMPO11 + ","              
				       + CAMPO12 + ","              
				       + CAMPO13 + ","              
				       + CAMPO14 + ","              
				       + CAMPO15 + ","              
				       + CAMPO16 + ","              
				       + CAMPO17 + ","              
				       + CAMPO18 + ","              
				       + CAMPO19 + ","              
				       + CAMPO20 + ","              
				       + CAMPO21 + ","              
				       + CAMPO22 + ","              
				       + CAMPO23 + ","              
				       + CAMPO24 + ","              
				       + CAMPO25 + ","              
				       + CAMPO26 + ","              
				       + CAMPO27 + ","              
				       + CAMPO28 + ","              
				       + CAMPO29 + ","
				       + CAMPO30 + ","
				       + CAMPO31 + ","
				       + CAMPO32 +               
				       " FROM " 
				       + TABLA + 
				       " WHERE " 
				       + CAMPO1 + " = '" + sMovimientoComunidadID + "'";
			
			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();

				pstmt = conexion.prepareStatement(sQuery);
				rs = pstmt.executeQuery();
				
				logger.debug("Ejecutada con exito!");

				if (rs != null) 
				{
					while (rs.next()) 
					{
						bEncontrado = true;

						sCODTRN = rs.getString(CAMPO2);  
						sCOTDOR = rs.getString(CAMPO3);  
						sIDPROV = rs.getString(CAMPO4);  
						sCOACCI = rs.getString(CAMPO5);  
						sCOENGP = rs.getString(CAMPO6);  
						sCOCLDO = rs.getString(CAMPO7);  
						sNUDCOM = rs.getString(CAMPO8);  
						sBITC10 = rs.getString(CAMPO9);  
						sCOACES = rs.getString(CAMPO10); 
						sBITC01 = rs.getString(CAMPO11); 
						sNOMCOC = rs.getString(CAMPO12); 
						sBITC02 = rs.getString(CAMPO13); 
						sNODCCO = rs.getString(CAMPO14); 
						sBITC03 = rs.getString(CAMPO15); 
						sNOMPRC = rs.getString(CAMPO16); 
						sBITC04 = rs.getString(CAMPO17); 
						sNUTPRC = rs.getString(CAMPO18); 
						sBITC05 = rs.getString(CAMPO19); 
						sNOMADC = rs.getString(CAMPO20); 
						sBITC06 = rs.getString(CAMPO21); 
						sNUTADC = rs.getString(CAMPO22); 
						sBITC07 = rs.getString(CAMPO23); 
						sNODCAD = rs.getString(CAMPO24); 
						sBITC08 = rs.getString(CAMPO25); 
						sNUCCEN = rs.getString(CAMPO26); 
						sNUCCOF = rs.getString(CAMPO27); 
						sNUCCDI = rs.getString(CAMPO28); 
						sNUCCNT = rs.getString(CAMPO29); 
						sBITC09 = rs.getString(CAMPO30); 
						sOBTEXC = rs.getString(CAMPO31); 
						sOBDEER = rs.getString(CAMPO32); 
						
						logger.debug("Encontrado el registro!");

						logger.debug(CAMPO1+":|"+sMovimientoComunidadID+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}
			} 
			catch (SQLException ex) 
			{
				sCODTRN = "";
				sCOTDOR = "";
				sIDPROV = "";
				sCOACCI = "";
				sCOENGP = "";
				sCOCLDO = "";
				sNUDCOM = "";
				sBITC10 = "";
				sCOACES = "";
				sBITC01 = "";
				sNOMCOC = "";
				sBITC02 = "";
				sNODCCO = "";
				sBITC03 = "";
				sNOMPRC = "";
				sBITC04 = "";
				sNUTPRC = "";
				sBITC05 = "";
				sNOMADC = "";
				sBITC06 = "";
				sNUTADC = "";
				sBITC07 = "";
				sNODCAD = "";
				sBITC08 = "";
				sNUCCEN = "";
				sNUCCOF = "";
				sNUCCDI = "";
				sNUCCNT = "";
				sBITC09 = "";
				sOBTEXC = "";
				sOBDEER = "";
				
				logger.error("ERROR MovimientoComunidadID:|"+sMovimientoComunidadID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return new MovimientoComunidad(sCODTRN, sCOTDOR, sIDPROV, sCOACCI, sCOENGP,
				sCOCLDO, sNUDCOM, sBITC10, sCOACES, sBITC01, sNOMCOC, sBITC02,
				sNODCCO, sBITC03, sNOMPRC, sBITC04, sNUTPRC, sBITC05, sNOMADC,
				sBITC06, sNUTADC, sBITC07, sNODCAD, sBITC08, sNUCCEN, sNUCCOF,
				sNUCCDI, sNUCCNT, sBITC09, sOBTEXC, sOBDEER);
	}
	
	public static String getMovimientoComunidadID(Connection conexion, MovimientoComunidad comunidad)
	{
		String sMovimientoComunidadID = "";
		
		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "
					+ CAMPO1 + 
					" FROM " 
					+ TABLA + 
					" WHERE ("
					+ CAMPO2  +" = '" + comunidad.getCODTRN() + "' AND "
					+ CAMPO4  +" = '" + comunidad.getIDPROV() + "' AND "
					+ CAMPO5  +" = '" + comunidad.getCOACCI() + "' AND "
					+ CAMPO6  +" = '" + comunidad.getCOENGP() + "' AND "
					+ CAMPO7  +" = '" + comunidad.getCOCLDO() + "' AND "
					+ CAMPO8  +" = '" + comunidad.getNUDCOM() + "' AND "
					+ CAMPO9  +" = '" + comunidad.getBITC10() + "' AND "
					+ CAMPO10 +" = '" + comunidad.getCOACES() + "' AND "
					+ CAMPO11 +" = '" + comunidad.getBITC01() + "' AND "
					+ CAMPO12 +" = '" + comunidad.getNOMCOC() + "' AND "
					+ CAMPO13 +" = '" + comunidad.getBITC02() + "' AND "
					+ CAMPO14 +" = '" + comunidad.getNODCCO() + "' AND "
					+ CAMPO15 +" = '" + comunidad.getBITC03() + "' AND "
					+ CAMPO16 +" = '" + comunidad.getNOMPRC() + "' AND "
					+ CAMPO17 +" = '" + comunidad.getBITC04() + "' AND "
					+ CAMPO18 +" = '" + comunidad.getNUTPRC() + "' AND "
					+ CAMPO19 +" = '" + comunidad.getBITC05() + "' AND "
					+ CAMPO20 +" = '" + comunidad.getNOMADC() + "' AND "
					+ CAMPO21 +" = '" + comunidad.getBITC06() + "' AND "
					+ CAMPO22 +" = '" + comunidad.getNUTADC() + "' AND "
					+ CAMPO23 +" = '" + comunidad.getBITC07() + "' AND "
					+ CAMPO24 +" = '" + comunidad.getNODCAD() + "' AND "
					+ CAMPO25 +" = '" + comunidad.getBITC08() + "' AND "
					+ CAMPO26 +" = '" + comunidad.getNUCCEN() + "' AND "
					+ CAMPO27 +" = '" + comunidad.getNUCCOF() + "' AND "
					+ CAMPO28 +" = '" + comunidad.getNUCCDI() + "' AND "
					+ CAMPO29 +" = '" + comunidad.getNUCCNT() + "' AND "
					+ CAMPO30 +" = '" + comunidad.getBITC09() + "' AND "
					+ CAMPO31 +" = '" + comunidad.getOBTEXC() + 
					"')";
			
			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();

				pstmt = conexion.prepareStatement(sQuery); 
				rs = pstmt.executeQuery();

				logger.debug("Ejecutada con exito!");

				if (rs != null) 
				{
					while (rs.next()) 
					{
						bEncontrado = true;

						sMovimientoComunidadID = rs.getString(CAMPO1);
						
						logger.debug("Encontrado el registro!");

						logger.debug(CAMPO1+":|"+sMovimientoComunidadID+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontró la información.");
				}			
			} 
			catch (SQLException ex) 
			{
				sMovimientoComunidadID = "";
				
				logger.error("ERROR COCLDO:|"+comunidad.getCOCLDO()+"|");
				logger.error("ERROR NUDCOM:|"+comunidad.getNUDCOM()+"|");
				logger.error("ERROR COACES:|"+comunidad.getCOACES()+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return sMovimientoComunidadID;
	}
	
	public static boolean existeMovimientoComunidad(Connection conexion, String sMovimientoComunidadID)
	{
		boolean bEncontrado = false;
		
		Statement stmt = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT " 
				+ CAMPO1 + 
				" FROM " 
				+ TABLA + 
				" WHERE " 
				+ CAMPO1 + " = '" + sMovimientoComunidadID + "'";
		
		logger.debug(sQuery);

		try 
		{
			stmt = conexion.createStatement();

			pstmt = conexion.prepareStatement(sQuery);
			
			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");
			
			if (rs != null) 
			{
				while (rs.next()) 
				{
					bEncontrado = true;

					logger.debug("Encontrado el registro!");
					logger.debug(CAMPO1+":|"+rs.getString(CAMPO1)+"|");
				}
			}
			if (!bEncontrado) 
			{
				logger.debug("No se encontro la información.");
			}
		} 
		catch (SQLException ex) 
		{
			bEncontrado = false;

			logger.error("ERROR MovimientoComunidadID:|"+sMovimientoComunidadID+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally 
		{
			Utils.closeStatement(stmt);
		}

		return bEncontrado;
	}
}