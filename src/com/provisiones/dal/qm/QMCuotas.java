package com.provisiones.dal.qm;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.Cuota;
import com.provisiones.types.tablas.CuotaTabla;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QMCuotas
{
	private static Logger logger = LoggerFactory.getLogger(QMCuotas.class.getName());
	
	public static final String TABLA = "pp001_e2_cuotas_tbl";

	//Primary Key
	public static final String CAMPO1  = "e2_cuota_id";
	
	//Unique Key - comunidad
	public static final String CAMPO2  = "cod_coaces";
	public static final String CAMPO3  = "cod_cocldo";
	public static final String CAMPO4  = "cod_nudcom";
	public static final String CAMPO5  = "cod_cosbac";
	
	//Campos secundarios
	public static final String CAMPO6  = "fipago";    
	public static final String CAMPO7  = "ffpago";    
	public static final String CAMPO8  = "imcuco";    
	public static final String CAMPO9  = "faacta";    
	public static final String CAMPO10 = "cod_ptpago";
	public static final String CAMPO11 = "obtexc";
	
	//Campos de control
	public static final String CAMPO12 = "cod_estado";

	public static long addCuota(Cuota NuevaCuota)

	{
		Connection conn = null;

		Statement stmt = null;
		ResultSet resulset = null;
		
		conn = ConnectionManager.getDBConnection();

		long liCodigo = 0;

		logger.debug("Ejecutando Query...");
		
		String sQuery = "INSERT INTO " + TABLA + " ("
			       + CAMPO2  + ","              
			       + CAMPO3  + ","              
			       + CAMPO4  + ","              
			       + CAMPO5  + ","              
			       + CAMPO6  + ","              
			       + CAMPO7  + ","              
			       + CAMPO8  + ","
			       + CAMPO9  + ","
			       + CAMPO10  + ","
				   + CAMPO11  + ","  
			       + CAMPO12  + 
			       ") VALUES ('"
			       + NuevaCuota.getCOACES() + "','"
			       + NuevaCuota.getCOCLDO() + "','"
			       + NuevaCuota.getNUDCOM() + "','"
			       + NuevaCuota.getCOSBAC() + "','"
			       + NuevaCuota.getFIPAGO() + "','"
			       + NuevaCuota.getFFPAGO() + "','"
			       + NuevaCuota.getIMCUCO() + "','"
			       + NuevaCuota.getFAACTA() + "','"
			       + NuevaCuota.getPTPAGO() + "','"
			       + NuevaCuota.getOBTEXC() + "','" 
			       + ValoresDefecto.DEF_ALTA + "' )";

		logger.debug(sQuery);

		try 
		{

			stmt = conn.createStatement();
			stmt.executeUpdate(sQuery, Statement.RETURN_GENERATED_KEYS);
			
			resulset = stmt.getGeneratedKeys();
			
			if (resulset.next()) 
			{
				liCodigo= resulset.getLong(1);
			} 

			logger.debug("Ejecutada con exito!");
			
			logger.debug("Ejecutada con exito!");
		}
		catch (SQLException ex)
		{
			liCodigo = 0;

			logger.error("ERROR COACES:|"+NuevaCuota.getCOACES()+"|");
			logger.error("ERROR COCLDO:|"+NuevaCuota.getCOCLDO()+"|");
			logger.error("ERROR NUDCOM:|"+NuevaCuota.getNUDCOM()+"|");
			logger.error("ERROR COSBAC:|"+NuevaCuota.getCOSBAC()+"|");
			
			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally
		{

			Utils.closeStatement(stmt);
		}
		//ConnectionManager.CloseDBConnection(conn);
		return liCodigo;
	}
	public static boolean modCuota(Cuota NuevaCuota, String sCodCuota)
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();

		Statement stmt = null;

		boolean bSalida = true;
		
		logger.debug("Ejecutando Query...");
		
		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("UPDATE " 
					+ TABLA + 
					" SET " 
					+ CAMPO6  + " = '"+ NuevaCuota.getFIPAGO() + "', "
					+ CAMPO7  + " = '"+ NuevaCuota.getFFPAGO() + "', "
					+ CAMPO8  + " = '"+ NuevaCuota.getIMCUCO() + "', "
					+ CAMPO9  + " = '"+ NuevaCuota.getFAACTA() + "', "
					+ CAMPO10  + " = '"+ NuevaCuota.getPTPAGO() + "', "
					+ CAMPO11 + " = '"+ NuevaCuota.getOBTEXC() + "' "+
					" WHERE " 
					+ CAMPO1  + " = '"+ sCodCuota +"'");
			
			logger.debug("Ejecutada con exito!");
			
		} 
		catch (SQLException ex) 
		{
			bSalida = false;

			logger.error("ERROR COACES:|"+NuevaCuota.getCOACES()+"|");
			logger.error("ERROR COCLDO:|"+NuevaCuota.getCOCLDO()+"|");
			logger.error("ERROR NUDCOM:|"+NuevaCuota.getNUDCOM()+"|");
			logger.error("ERROR COSBAC:|"+NuevaCuota.getCOSBAC()+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally 
		{
			Utils.closeStatement(stmt);
		}
		//ConnectionManager.CloseDBConnection(conn);
		return bSalida;
	}

	public static boolean delCuota(String sCodCuota)
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();

		Statement stmt = null;

		boolean bSalida = true;
		
		logger.debug("Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("DELETE FROM " 
					+ TABLA + 
					" WHERE "
					+ CAMPO1  + " = '"+ sCodCuota +"'");
			
			logger.debug("Ejecutada con exito!");
			
		} 
		catch (SQLException ex) 
		{
			bSalida = false;

			logger.error("ERROR Cuota:|"+sCodCuota+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally 
		{

			Utils.closeStatement(stmt);
		}
		//ConnectionManager.CloseDBConnection(conn);
		return bSalida;
	}
	
	public static boolean existeCuota(String sCodCuota)
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();
		
		Statement stmt = null;

		ResultSet rs = null;
		PreparedStatement pstmt = null;

		boolean bEncontrado = false;

		logger.debug("Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();


			pstmt = conn.prepareStatement("SELECT " 
					+ CAMPO1 + 
					" FROM " 
					+ TABLA + 
					" WHERE " 
					+ CAMPO1  + " = '"+ sCodCuota +"'");

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");
			
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					bEncontrado = true;

					logger.debug("Encontrado el registro!");
				}
			}
			if (bEncontrado == false) 
			{
 
				logger.debug("No se encontr� la informaci�n.");
			}

		} 
		catch (SQLException ex) 
		{
			bEncontrado = false;

			logger.error("ERROR Cuota:|"+sCodCuota+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}

		//ConnectionManager.CloseDBConnection(conn);
		return bEncontrado;
	}

	public static Cuota getCuota(String sCodCuota)
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();

		Statement stmt = null;

		ResultSet rs = null;
		PreparedStatement pstmt = null;

		String sCOACES = "";
		String sCOCLDO = "";
		String sNUDCOM = "";
		String sCOSBAC = "";
		String sFIPAGO = "";
		String sFFPAGO = "";
		String sIMCUCO = "";
		String sFAACTA = "";
		String sPTPAGO = "";
		String sOBTEXC = "";

		boolean bEncontrado = false;

		logger.debug("Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement("SELECT "
					   + CAMPO2  + ","              
				       + CAMPO3  + ","              
				       + CAMPO4  + ","              
				       + CAMPO5  + ","              
				       + CAMPO6  + ","              
				       + CAMPO7  + ","              
				       + CAMPO8  + ","
				       + CAMPO9  + ","
					   + CAMPO10  + ","
				       + CAMPO11  +       
				       " FROM " 
				       + TABLA + 
				       " WHERE " 
				       + CAMPO1  + " = '"+ sCodCuota +"'");

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");

			if (rs != null) 
			{

				while (rs.next()) 
				{
					bEncontrado = true;

					sCOACES = rs.getString(CAMPO2); 
					sCOCLDO = rs.getString(CAMPO3); 
					sNUDCOM = rs.getString(CAMPO4); 
					sCOSBAC = rs.getString(CAMPO5); 
					sFIPAGO = rs.getString(CAMPO6); 
					sFFPAGO = rs.getString(CAMPO7); 
					sIMCUCO = rs.getString(CAMPO8); 
					sFAACTA = rs.getString(CAMPO9); 
					sPTPAGO = rs.getString(CAMPO10);
					sOBTEXC = rs.getString(CAMPO11);
					
					logger.debug("Encontrado el registro!");
					
				}
			}
			if (bEncontrado == false) 
			{
				logger.debug("No se encontr� la informaci�n.");
			}

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR Cuota:|"+sCodCuota+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}
		//ConnectionManager.CloseDBConnection(conn);
		return new Cuota(sCOACES, sCOCLDO, sNUDCOM, sCOSBAC, sFIPAGO, sFFPAGO, sIMCUCO, sFAACTA, sPTPAGO, sOBTEXC);
	}
	
	public static String getCuotaID(String sCodCOACES, String sCodCOCLDO, String sCodNUDCOM, String sCodCOSBAC)
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();

		Statement stmt = null;

		ResultSet rs = null;
		PreparedStatement pstmt = null;

		String sCuotaID = "";

		boolean bEncontrado = false;

		logger.debug("Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement("SELECT "
					   + CAMPO1 +       
				       " FROM " 
					   + TABLA + 
					   " WHERE ("	
					   + CAMPO2  + " = '"+ sCodCOACES +"' AND " 
					   + CAMPO3  + " = '"+ sCodCOCLDO +"' AND " 
					   + CAMPO4  + " = '"+ sCodNUDCOM +"' AND " 
					   + CAMPO5  + " = '"+ sCodCOSBAC + "')");

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");

			if (rs != null) 
			{

				while (rs.next()) 
				{
					bEncontrado = true;

					sCuotaID = rs.getString(CAMPO1);
					
					logger.debug("Encontrado el registro!");
					
				}
			}
			if (bEncontrado == false) 
			{
				logger.debug("No se encontr� la informaci�n.");
			}

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR COACES:|"+sCodCOACES+"|");
			logger.error("ERROR COCLDO:|"+sCodCOCLDO+"|");
			logger.error("ERROR NUDCOM:|"+sCodNUDCOM+"|");
			logger.error("ERROR COSBAC:|"+sCodCOSBAC+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}
		//ConnectionManager.CloseDBConnection(conn);
		return sCuotaID;
	}
	
	public static boolean tieneCuotas(String sCodCOACES, String sCodCOCLDO, String sCodNUDCOM)
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();

		Statement stmt = null;

		ResultSet rs = null;
		PreparedStatement pstmt = null;

		boolean bEncontrado = false;

		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT "              
			       + CAMPO3  +       
			       " FROM " 
			       + TABLA + 
			       " WHERE ("
			       + CAMPO2  + " = '"+ sCodCOACES +"' AND "
			       + CAMPO3  + " = '"+ sCodCOCLDO +"' AND "
			       + CAMPO4  + " = '"+ sCodNUDCOM +"' AND " 
			       + CAMPO12  + " <> 'B')";
		
		logger.debug(sQuery);

		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement(sQuery);

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");


			if (rs != null) 
			{

				while (rs.next()) 
				{
					bEncontrado = true;

					logger.debug("Encontrado el registro!");

				}
			}
			if (bEncontrado == false) 
			{
				logger.debug("No se encontr� la informaci�n.");
			}


		} 
		catch (SQLException ex) 
		{
			bEncontrado = false;

			logger.error("ERROR COACES:|"+sCodCOACES+"|");
			logger.error("ERROR COCLDO:|"+sCodCOCLDO+"|");
			logger.error("ERROR NUDCOM:|"+sCodNUDCOM+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}
		//ConnectionManager.CloseDBConnection(conn);
		return bEncontrado;
	}
	
	public static ArrayList<CuotaTabla> buscaCuotasActivo(String sCodCOACES)
	{
		Statement stmt = null;
		ResultSet rs = null;

		String sCOCLDO = "";
		String sDesCOCLDO = "";
		String sNUDCOM = "";
		String sCOSBAC = "";
		String sDesCOSBAC = "";
		String sFIPAGO = "";
		String sFFPAGO = "";
		String sIMCUCO = "";
		String sFAACTA = "";
		String sPTPAGO = "";
		String sDesPTPAGO = "";
		String sOBTEXC = "";
		
		ArrayList<CuotaTabla> result = new ArrayList<CuotaTabla>();
		

		PreparedStatement pstmt = null;
		boolean found = false;
		
		Connection conn = null;
		
		conn = ConnectionManager.getDBConnection();
		
		logger.debug("Ejecutando Query...");
		
		String sQuery = "SELECT "
					   + CAMPO3 + ","
					   + CAMPO4 + ","
					   + CAMPO5 + ","
					   + CAMPO6 + ","
					   + CAMPO7 + ","
					   + CAMPO8 + ","
					   + CAMPO9 + ","
					   + CAMPO10 + "," 
					   + CAMPO11 + ","
					   + CAMPO12 +
					   " FROM " 
					   + TABLA + 
					   " WHERE ("
					   + CAMPO12 + " = '" + ValoresDefecto.DEF_ALTA + "' AND "
					   + CAMPO1 + " = '" + sCodCOACES	+ "'))";
		
		logger.debug(sQuery);

		try 
		{
			stmt = conn.createStatement();
			
			pstmt = conn.prepareStatement(sQuery);

			


			

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con �xito!");

			

			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;
					
					sCOCLDO     = rs.getString(CAMPO3);
					sDesCOCLDO  = QMCodigosControl.getDesCampo(QMCodigosControl.TCOCLDO, QMCodigosControl.ICOCLDO, sCOCLDO);
					sNUDCOM     = rs.getString(CAMPO4);
					sCOSBAC     = rs.getString(CAMPO5);
					sDesCOSBAC  = QMCodigosControl.getDesCampo(QMCodigosControl.TCOSBGAT22,QMCodigosControl.ICOSBGAT22,sCOSBAC);
					sFIPAGO     = Utils.recuperaFecha(rs.getString(CAMPO6));
					sFFPAGO     = Utils.recuperaFecha(rs.getString(CAMPO7));
					sIMCUCO     = Utils.recuperaImporte(false,rs.getString(CAMPO8));
					sFAACTA     = Utils.recuperaFecha(rs.getString(CAMPO9));
					sPTPAGO     = rs.getString(CAMPO10);
					sDesPTPAGO  = QMCodigosControl.getDesCampo(QMCodigosControl.TPTPAGO,QMCodigosControl.IPTPAGO,sPTPAGO);
					sOBTEXC     = rs.getString(CAMPO11);  

					
					CuotaTabla cuotaencontrada = new CuotaTabla(
							sCOCLDO,
							sDesCOCLDO,
							sNUDCOM,
							sCOSBAC,
							sDesCOSBAC,
							sFIPAGO,
							sFFPAGO,
							sIMCUCO,
							sFAACTA,
							sPTPAGO,
							sDesPTPAGO,
							sOBTEXC);
					
					result.add(cuotaencontrada);
					
					logger.debug("Encontrado el registro!");

					logger.debug(CAMPO1+":|"+sCodCOACES+"|");
				}
			}
			if (found == false) 
			{
				logger.debug("No se encontr� la informaci�n.");
			}

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}
		//ConnectionManager.CloseDBConnection(conn);
		return result;
	}
	
	public static boolean setEstado(String sCodCuota, String sEstado)
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();
		
		Statement stmt = null;

		boolean bSalida = true;

		logger.debug("Ejecutando Query...");
		
		String sQuery = "UPDATE " 
				+ TABLA + 
				" SET " 
				+ CAMPO12 + " = '"+ sEstado +"' "+
				" WHERE "
				+ CAMPO1  + " = '"+ sCodCuota +"'";
		
		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate(sQuery);
			
			logger.debug("Ejecutada con exito!");
			
		} 
		catch (SQLException ex) 
		{
			bSalida = false;

			logger.error("ERROR Cuota:|"+sCodCuota+"|");

			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally 
		{

			Utils.closeStatement(stmt);
		}
		//ConnectionManager.CloseDBConnection(conn);
		return bSalida;
	}
	
	public static String getEstado(String sCodCuota)
	{
		Connection conn = null;
		conn = ConnectionManager.getDBConnection();

		Statement stmt = null;

		ResultSet rs = null;
		PreparedStatement pstmt = null;

		String sEstado = "";

		boolean bEncontrado = false;

		logger.debug("Ejecutando Query...");

		try 
		{
			stmt = conn.createStatement();


			pstmt = conn.prepareStatement("SELECT " 
					+ CAMPO12 + 
					" FROM " 
					+ TABLA + 
					" WHERE "
					+ CAMPO1  + " = '"+ sCodCuota +"'");

			rs = pstmt.executeQuery();
			
			logger.debug("Ejecutada con exito!");
			
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					bEncontrado = true;

					sEstado = rs.getString(CAMPO12);
					
					logger.debug("Encontrado el registro!");
					
					logger.debug(CAMPO12+":|"+sEstado+"|");


				}
			}
			if (bEncontrado == false) 
			{
 
				logger.debug("No se encontr� la informaci�n.");
			}

		} 
		catch (SQLException ex) 
		{
			logger.error("ERROR Cuota:|"+sCodCuota+"|");
			
			logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
		} 
		finally 
		{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}

		//ConnectionManager.CloseDBConnection(conn);
		return sEstado;
	}
	
}
