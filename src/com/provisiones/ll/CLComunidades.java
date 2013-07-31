package com.provisiones.ll;

import com.provisiones.dal.qm.QMComunidades;
import com.provisiones.dal.qm.QMListaComunidades;
import com.provisiones.dal.qm.QMMovimientosComunidades;
import com.provisiones.misc.Parser;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.Comunidad;
import com.provisiones.types.MovimientoComunidad;


public class CLComunidades 
{
	static String sClassName = CLComunidades.class.getName();
	
	public static boolean actualizaComunidadLeida(String linea)
	{
		String sMethod = "actualizaComunidadLeida";

		boolean bSalida = false;
		
		MovimientoComunidad comunidad = Parser.leerComunidad(linea);
		
		String sBKCOTDOR = ValoresDefecto.DEF_COTDOR;
		String sBKOBDEER = ValoresDefecto.DEF_OBDEER.trim();
				
		String sValidado = "";
		
		if (comunidad.getCOTDOR().equals(ValoresDefecto.DEF_COTDOR))
		{
			sValidado = "V";
			sBKOBDEER = comunidad.getOBDEER();
		}
		else
		{
			sValidado = "X";
			sBKCOTDOR = comunidad.getCOTDOR();
			sBKOBDEER = comunidad.getOBDEER();
			comunidad.setCOTDOR(ValoresDefecto.DEF_COTDOR);

		}
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "sValidado|"+sValidado+"|");
		
		//comunidad.setOBDEER(ValoresDefecto.DEF_OBDEER.trim());
		
		String sCodMovimiento = QMMovimientosComunidades.getMovimientoComunidadID(comunidad);
		
		com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "sCodMovimiento|"+sCodMovimiento+"|");
		
		bSalida = !(sCodMovimiento.equals(""));
		
		if (bSalida)
		{
			//String sAccion = comunidad.getCOACCI();
			
			//Accion	Estado	Validado
			/*
			
			if (sAccion.equals("A") && sValidado.equals("X"))
			{
				
			}
			if (sAccion.equals("A") && sValidado.equals("V"))
			{
				
			}
			if (sAccion.equals("A") && sValidado.equals("X"))
			{
				
			}*/
			
			comunidad.setCOTDOR(sBKCOTDOR);
			comunidad.setOBDEER(sBKOBDEER);
			
			comunidad.setBITC01("S");
			
			comunidad.pintaMovimientoComunidad();
			
			bSalida = QMMovimientosComunidades.modMovimientoComunidad(comunidad, sCodMovimiento);
			
			if (QMListaComunidades.existeRelacionComunidad(comunidad.getNUDCOM(), comunidad.getCOACES(), sCodMovimiento))
				QMListaComunidades.setValidado(comunidad.getNUDCOM(), comunidad.getCOACES(), sCodMovimiento, sValidado);
			else
				System.out.println("No Existe relacion.");
		}
		else 
		{

			
			
			/*Comunidad NuevaComunidad = new Comunidad(comunidad.getCOCLDO(),
					comunidad.getNUDCOM(), comunidad.getNOMCOC(),
					comunidad.getNODCCO(), comunidad.getNOMPRC(),
					comunidad.getNUTPRC(), comunidad.getNOMADC(),
					comunidad.getNUTADC(), comunidad.getNODCAD(),
					comunidad.getNUCCEN(), comunidad.getNUCCOF(),
					comunidad.getNUCCDI(), comunidad.getNUCCNT(),
					comunidad.getOBTEXC());
			
			QMComunidades.addComunidad(NuevaComunidad);*/
			
			//comunidad.pintaMovimientoComunidad();
			
			//comunidad.setBITC01("#");
			
			//QMMovimientosComunidades.addMovimientoComunidad(comunidad);
			
			//QMListaComunidades.
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "El siguiente registro no se encuentre en el sistema:");
			com.provisiones.misc.Utils.debugTrace(true, sClassName, sMethod, "|"+linea+"|");
			System.out.println("No Information Found");
		}
		
		return bSalida;
	}
}