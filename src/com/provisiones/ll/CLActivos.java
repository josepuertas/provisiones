package com.provisiones.ll;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.dal.qm.QMActivos;

import com.provisiones.misc.Parser;
import com.provisiones.types.Activo;
import com.provisiones.types.tablas.ActivoTabla;


public class CLActivos 
{
	private static Logger logger = LoggerFactory.getLogger(CLActivos.class.getName());
	
	public static int actualizaActivoLeido(String linea)
	{
		int iCodigo = 0;
		
		Activo activo = Parser.leerActivo(linea);
		
		String sCodActivo =  activo.getCOACES();
		
		logger.debug("sCodActivo:|{}|",sCodActivo);
		
		if (!QMActivos.existeActivo(sCodActivo))
		{
			if (QMActivos.addActivo(activo))
			{
				logger.info("Nuevo Activo registrado.");
			}
			else
			{
				iCodigo = -2;
			}
		}
		else
		{
			logger.warn("El siguiente registro ya se encuentre en el sistema:");
			logger.warn("|{}|",linea);
			iCodigo = -1;
		}
				
		return iCodigo;
	}	

	public static ArrayList<ActivoTabla> buscarActivos (ActivoTabla activobuscado)
	{
			
		return QMActivos.buscaActivos(activobuscado);
	}

	public static boolean compruebaActivo (String sCodCOACES)
	{
			
		return QMActivos.existeActivo(sCodCOACES);
	}
	
	public static String compruebaTipoActivoSAREB (String sCodCOACES)
	{

		String sTipo = "#";
		
		logger.debug("sCodCOACES:|{}|",sCodCOACES);

		if (QMActivos.getSociedadPatrimonial(sCodCOACES).equals("9999"))
		{
			if (QMActivos.getCOTSINActivo(sCodCOACES).startsWith("SU"))
			{
				sTipo = "S"; //SUELOS Y OBRA EN CURSO 
			}
			else if (QMActivos.getBIARREActivo(sCodCOACES).equals("S"))
			{
				sTipo = "A"; //ARRENDAMIENTOS
			}
			else
			{
				sTipo = "T"; //PRODUCTO TERMINADO
			}
		}
		logger.debug("sTipo:|{}|",sTipo);
		return sTipo;
	}
	
	public static String sociedadPatrimonialAsociada (String sCodCOACES)
	{
			
		return QMActivos.getSociedadPatrimonial(sCodCOACES);
	}
}
