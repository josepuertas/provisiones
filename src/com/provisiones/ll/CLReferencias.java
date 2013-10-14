package com.provisiones.ll;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.dal.qm.QMActivos;
import com.provisiones.dal.qm.QMImpuestos;
import com.provisiones.dal.qm.QMReferencias;
import com.provisiones.dal.qm.listas.QMListaReferencias;
import com.provisiones.dal.qm.listas.errores.QMListaErroresReferencias;
import com.provisiones.dal.qm.movimientos.QMMovimientosReferencias;

import com.provisiones.misc.Parser;
import com.provisiones.misc.ValoresDefecto;

import com.provisiones.types.ActivoTabla;
import com.provisiones.types.MovimientoReferenciaCatastral;
import com.provisiones.types.ReferenciaCatastral;
import com.provisiones.types.ReferenciaTabla;


public class CLReferencias 
{
	private static Logger logger = LoggerFactory.getLogger(CLReferencias.class.getName());
	
	public static int actualizaReferenciaLeida(String linea)
	{
		int iCodigo = 0;
		
		MovimientoReferenciaCatastral referencia = Parser.leerReferenciaCatastral(linea);
		
		logger.debug(referencia.logMovimientoReferenciaCatastral());

		String sCodMovimiento = QMMovimientosReferencias.getMovimientoReferenciaCatastralID(referencia);
		
		logger.debug("sCodMovimiento|"+sCodMovimiento+"|");
		
		if (!(sCodMovimiento.equals("")))
		{

			
			String sEstado = QMListaReferencias.getValidado(sCodMovimiento);;
			
			if (sEstado.equals("P"))
			{
				iCodigo = -11;
			}
			else if (sEstado.equals("X") || sEstado.equals("V") || sEstado.equals("R"))
			{
				iCodigo = -12;
			}
			else if (sEstado.equals("E"))
			{
				String sValidado = "";
				
				logger.debug("referencia.getCOTDOR()|{}|",referencia.getCOTDOR());
				logger.debug("ValoresDefecto.DEF_COTDOR|{}|",ValoresDefecto.DEF_COTDOR);

				if (referencia.getCOTDOR().equals(ValoresDefecto.DEF_COTDOR))
				{
					sValidado = "V";
				}
				else
				{
					sValidado = "X";
				}
				
				logger.debug("sValidado|{}|",sValidado);
				
				logger.debug("referencia.getCOACCI()|{}|",referencia.getCOACCI());

				ValoresDefecto.TIPOSACCIONES COACCI = ValoresDefecto.TIPOSACCIONES.valueOf(referencia.getCOACCI());

				switch (COACCI)
				{
				case A: case M: case B:
					if (QMListaReferencias.existeRelacionReferencia(referencia.getNURCAT(),referencia.getCOACES(), sCodMovimiento))
					{
						if(QMListaReferencias.setValidado(sCodMovimiento, sValidado))
						{
							if (sValidado.equals("X"))
							{
								//recibido error
								if (QMListaErroresReferencias.addErrorReferencia(sCodMovimiento, referencia.getCOTDOR()))
								{
									iCodigo = 1;
								}
								else
								{
									QMListaReferencias.setValidado(sCodMovimiento, "E");
									iCodigo = -4;
								}
							}
							else
							{
								//recibido OK
								logger.info("Movimiento validado.");
							}
						}
						else
						{
							iCodigo = -3;
						}
					}
					else
					{
						iCodigo = -2;
					}
					break;
					
				default:
					logger.error("Se ha recibido un movimiento con acci�n desconocida:|{}|.",referencia.getCOACCI());
					iCodigo = -9;
					break;
				
				}
				
				//bSalida = QMMovimientosReferencias.modMovimientoReferencia(referencia, sCodMovimiento);
				//nos ahorramos modificar el movimiento y posteriormente en el bean de gestion de errores
				//recuperaremos el codigo de error de la tabla pertinente.
			}
			else
			{
				iCodigo = -10;
			}
				
		}
		else 
		{
			logger.error("El siguiente registro no se encuentra en el sistema:");
			logger.error("|{}|",linea);
			iCodigo = -1;
		}
		
		logger.error("iCodigo:|{}|",iCodigo);
		
		return iCodigo;
	}
	
	public static MovimientoReferenciaCatastral convierteCuotaenMovimiento(ReferenciaCatastral referencia, String sCodCOACES, String sCodCOACCI)
	{
		logger.debug("Convirtiendo...");
		
		return new MovimientoReferenciaCatastral(
				ValoresDefecto.DEF_E3_CODTRN,
				ValoresDefecto.DEF_COTDOR,
				ValoresDefecto.DEF_IDPROV,
				sCodCOACCI,
				ValoresDefecto.DEF_COENGP,
				sCodCOACES,
				referencia.getNURCAT(),
				"",
				referencia.getTIRCAT(),
				"",
				referencia.getENEMIS(),
				referencia.getCOTEXA(),
				"",
				referencia.getOBTEXC(),
				"",
				//Ampliacion de valor catastral
				"",
				referencia.getIMVSUE(),
				"",
				referencia.getIMCATA(),
				"",
				referencia.getFERECA());
		
	}
	public static ReferenciaCatastral convierteMovimientoenReferencia(MovimientoReferenciaCatastral movimiento)
	{
		logger.debug("Convirtiendo...");
		
		return new ReferenciaCatastral(
				movimiento.getNURCAT(),
				movimiento.getTIRCAT(),
				movimiento.getENEMIS(),
				movimiento.getCOTEXA(),
				movimiento.getOBTEXC(),
				
				//Ampliacion de valor catastral
				movimiento.getIMVSUE(),
				movimiento.getIMCATA(),
				movimiento.getFERECA());
	}
	
	public static boolean existeReferenciaCatastral (String sCodNURCAT)
	{

		return QMReferencias.existeReferenciaCatastral(sCodNURCAT);
	}
	
	public static boolean comprobarRelacion(String sCodNURCAT, String sCodCOACES)
	{
		return QMListaReferencias.compruebaRelacionReferenciaActivo(sCodNURCAT, sCodCOACES);
	}
	
	
	public static String referenciaCatastralActivo(String sCodCOACES)
	{
		String sReferencia = QMActivos.getReferenciaCatastral(sCodCOACES);
		
		if (!sReferencia.equals("") && QMReferencias.getEstado(sReferencia).equals(ValoresDefecto.DEF_ALTA))
		{
			sReferencia = "";
		}
		return sReferencia;
	}
	
	public static String referenciaCatastralAsociada(String sCodCOACES)
	{
		return QMListaReferencias.referenciaAsociada(sCodCOACES);
	}
	
	public static ArrayList<ActivoTabla> buscarActivosSinReferencias (ActivoTabla activo)
	{
		return QMListaReferencias.buscaActivosNoAsociados(activo);
	}
	
	public static ArrayList<ActivoTabla> buscarListaActivosReferencia (ActivoTabla activo)
	{
		return QMListaReferencias.buscaListaActivosReferencias(activo);
	}
	
	public static ArrayList<ActivoTabla> buscarActivosConReferencias (ActivoTabla activo)
	{
		return QMListaReferencias.buscaActivosAsociados(activo);
	}
	
	public static MovimientoReferenciaCatastral buscarMovimientoReferenciaCatastral (String sCodMovimiento)
	{

		return QMMovimientosReferencias.getMovimientoReferenciaCatastral(sCodMovimiento);
	}
	
	public static boolean existeMovimientoReferenciaCatastral (String sCodMovimiento)
	{

		return QMMovimientosReferencias.existeMovimientoReferenciaCatastral(sCodMovimiento);
	}
	
	public static long buscarNumeroMovimientosReferenciasPendientes()
	{
		return (QMListaReferencias.buscaCantidadValidado(ValoresDefecto.DEF_MOVIMIENTO_PENDIENTE));
	}

	public static boolean estaAsociado(String sCodCOACES)
	{
		return QMListaReferencias.activoAsociado(sCodCOACES);
	}

	public static String estadoReferencia(String sCodNURCAT)
	{
		return QMReferencias.getEstado(sCodNURCAT);
	}
	
	public static ReferenciaCatastral buscaReferencia (String sCodNURCAT)
	{
		return QMReferencias.getReferenciaCatastral(sCodNURCAT);
	}
	
	public static ArrayList<ReferenciaTabla> buscarReferenciasActivo(String sCodCOACES)
	{
		return QMListaReferencias.buscaReferenciasActivo(sCodCOACES);
	}
	
	public static boolean estaDeBaja(String sCodNURCAT)
	{
		return QMReferencias.getEstado(sCodNURCAT).equals(ValoresDefecto.DEF_BAJA);
	}
	
	public static MovimientoReferenciaCatastral revisaCodigosControl(MovimientoReferenciaCatastral movimiento)
	{
		ReferenciaCatastral referencia = QMReferencias.getReferenciaCatastral(movimiento.getNURCAT());
		
		
		logger.debug(referencia.logReferenciaCatastral());
		
		logger.debug(movimiento.logMovimientoReferenciaCatastral());
		
		MovimientoReferenciaCatastral movimiento_revisado = new MovimientoReferenciaCatastral("","0","0","","0","0","","","","","","0","","","","","0","","0","","0");
		
		logger.debug("Revisando Accion:|{}|",movimiento.getCOACCI());
		
		movimiento_revisado.setCODTRN(movimiento.getCODTRN());
		movimiento_revisado.setCOTDOR(movimiento.getCOTDOR());
		movimiento_revisado.setIDPROV(movimiento.getIDPROV());
		movimiento_revisado.setCOACCI(movimiento.getCOACCI());
		movimiento_revisado.setCOENGP(movimiento.getCOENGP());
		movimiento_revisado.setCOACES(movimiento.getCOACES());
		movimiento_revisado.setNURCAT(movimiento.getNURCAT());
		
		movimiento_revisado.setCOTEXA(movimiento.getCOTEXA());
		
		movimiento_revisado.setOBDEER(movimiento.getOBDEER());
		
				
		
			if (movimiento.getCOACCI().equals(ValoresDefecto.DEF_ALTA))
			{
				
				if (movimiento.getTIRCAT().equals(""))
				{
					movimiento_revisado.setBITC16("#");
				}
				else
				{
					movimiento_revisado.setBITC16("S");
					movimiento_revisado.setTIRCAT(movimiento.getTIRCAT());
				}

				if (movimiento.getENEMIS().equals("0"))
				{
					movimiento_revisado.setBITC17("#");
				}
				else
				{
					movimiento_revisado.setBITC17("S");
					movimiento_revisado.setENEMIS(movimiento.getENEMIS());
				}

				if (movimiento.getOBTEXC().equals(""))
				{
					movimiento_revisado.setBITC09("#");
				}
				else
				{
					movimiento_revisado.setBITC09(ValoresDefecto.DEF_ALTA);
					movimiento_revisado.setOBTEXC(movimiento.getOBTEXC());
				}
				
				//Ampliacion de valor catastral
				if (movimiento.getIMVSUE().equals(""))
				{
					movimiento_revisado.setBITC23("#");
				}
				else
				{
					movimiento_revisado.setBITC23("S");
					movimiento_revisado.setIMVSUE(movimiento.getIMVSUE());
				}
				
				if (movimiento.getIMCATA().equals(""))
				{
					movimiento_revisado.setBITC24("#");
				}
				else
				{
					movimiento_revisado.setBITC24("S");
					movimiento_revisado.setIMCATA(movimiento.getIMCATA());
				}
				
				if (movimiento.getFERECA().equals(""))
				{
					movimiento_revisado.setBITC25("#");
				}
				else
				{
					movimiento_revisado.setBITC25("S");
					movimiento_revisado.setFERECA(movimiento.getFERECA());
				}
				

			}
			else if (movimiento.getCOACCI().equals("M"))
			{
				boolean bCambio = false;
				
				if (movimiento.getTIRCAT().equals(referencia.getTIRCAT()))
				{
					movimiento_revisado.setBITC16("#");
				}
				else
				{
					movimiento_revisado.setBITC16("S");
					movimiento_revisado.setTIRCAT(movimiento.getTIRCAT());
					bCambio = true;
				}

				if (movimiento.getENEMIS().equals(referencia.getENEMIS()))
				{
					movimiento_revisado.setBITC17("#");
				}
				else
				{
					movimiento_revisado.setBITC17("S");
					movimiento_revisado.setENEMIS(movimiento.getENEMIS());
					bCambio = true;
				}


				
				if (movimiento.getOBTEXC().equals(referencia.getOBTEXC()))
				{
					movimiento_revisado.setBITC09("#");
				}
				else if (movimiento.getOBTEXC().equals("") && !referencia.getOBTEXC().equals(""))
				{
					movimiento_revisado.setBITC09(ValoresDefecto.DEF_BAJA);
					movimiento_revisado.setOBTEXC("");
					bCambio = true;
				}
				else if (!movimiento.getOBTEXC().equals("") &&  referencia.getOBTEXC().equals(""))
				{
					movimiento_revisado.setBITC09(ValoresDefecto.DEF_ALTA);
					movimiento_revisado.setOBTEXC(movimiento.getOBTEXC());
					bCambio = true;
				}
				else 
				{
					movimiento_revisado.setBITC09("M");
					movimiento_revisado.setOBTEXC(movimiento.getOBTEXC());
					bCambio = true;
				}
				
				//Ampliacion de valor catastral
				if (movimiento.getIMVSUE().equals(referencia.getIMVSUE()))
				{
					movimiento_revisado.setBITC23("#");
				}
				else
				{
					movimiento_revisado.setBITC23("S");
					movimiento_revisado.setIMVSUE(movimiento.getIMVSUE());
					bCambio = true;
				}
				
				if (movimiento.getIMCATA().equals(referencia.getIMCATA()))
				{
					movimiento_revisado.setBITC24("#");
				}
				else
				{
					movimiento_revisado.setBITC24("S");
					movimiento_revisado.setIMCATA(movimiento.getIMCATA());
					bCambio = true;
				}
				
				if (movimiento.getFERECA().equals(referencia.getFERECA()))
				{
					movimiento_revisado.setBITC25("#");
				}
				else
				{
					movimiento_revisado.setBITC25("S");
					movimiento_revisado.setFERECA(movimiento.getFERECA());
					bCambio = true;
				}
				
				if (!bCambio)
					movimiento_revisado.setCOACCI("#");
				
			}
			else if (movimiento.getCOACCI().equals(ValoresDefecto.DEF_BAJA))
			{
				movimiento_revisado.setBITC16("#");
				movimiento_revisado.setBITC17("#");
				movimiento_revisado.setBITC09("#");
				
				//Ampliacion de valor catastral
				movimiento_revisado.setBITC23("#");
				movimiento_revisado.setBITC24("#");
				movimiento_revisado.setBITC25("#");
			}
			else
				movimiento_revisado.setCOACCI("");


		

		logger.debug("Revisado! Nuevo movimiento:");

		logger.debug(movimiento_revisado.logMovimientoReferenciaCatastral());
		
		return movimiento_revisado;

	}
	
	public static int revisaMovimiento(MovimientoReferenciaCatastral movimiento)
	{
		int iCodigo = 0;
		
		logger.debug("Comprobando estado...");
		
		String sEstado = QMReferencias.getEstado(movimiento.getNURCAT());
		
		logger.debug("Estado:|{}|",sEstado);
		logger.debug("Acci�n:|{}|",movimiento.getCOACCI());
		
		if (movimiento.getCOACCI().equals(""))
		{
			//Error 001 - CODIGO DE ACCION DEBE SER A,M o B
			iCodigo = -1;
		}
		else if (movimiento.getCOACES().equals("") || !QMActivos.existeActivo(movimiento.getCOACES()))
		{
			//Error 003 - NO EXISTE EL ACTIVO
			iCodigo = -3;
		}
		else if (movimiento.getNURCAT().equals(""))
		{
			//Error 054 - LA REFERENCIA CATASTRAL ES OBLIGATORIA
			iCodigo = -54;
		}
		else if (movimiento.getCOACCI().equals(ValoresDefecto.DEF_ALTA) && movimiento.getTIRCAT().equals(""))
		{
			//Error 052 - TITULAR CATASTRAL OBLIGATORIO. NO SE PUEDE DAR DE ALTA
			iCodigo = -52;
		}
		
		else if (Double.parseDouble(movimiento.getIMVSUE()) <= 0)
		{
			//Error 082 - EL VALOR DEL SUELO TIENE QUE SER MAYOR DE CERO
			iCodigo = -82;
		}
		else if (movimiento.getIMVSUE().equals("#"))
		{
			//Error 701 - valor del suelo incorrecto
			iCodigo = -701;
		}

		else if (Double.parseDouble(movimiento.getIMCATA()) <= 0)
		{
			//Error 083 - EL VALOR CATASTRAL TIENE QUE SER MAYOR DE CERO
			iCodigo = -83;
		}
		else if (movimiento.getIMCATA().equals("#"))
		{
			//Error 702 - valor catastral incorrecto
			iCodigo = -702;
		}
		
		else if (movimiento.getFERECA().equals("#"))// || movimiento.getFERECA().equals("0"))
		{
			//Error 085 - FECHA REVISION DEL VALOR CATASTRAL NO TRAE UN VALOR LOGICO
			iCodigo = -85;
		}
		
		else if (!movimiento.getCOACCI().equals(ValoresDefecto.DEF_ALTA) &&  !comprobarRelacion(movimiento.getNURCAT(),movimiento.getCOACES()))
		{
			//error no existe relaccion con ese activo
			iCodigo = -700;
		}
		else if (movimiento.getCOACCI().equals(ValoresDefecto.DEF_ALTA) && QMReferencias.existeReferenciaCatastral(movimiento.getNURCAT()) && !estaDeBaja(movimiento.getNURCAT()))
		{
			//Error 049 - LA REFERENCIA CATASTRAL YA EXISTE NO SE PUEDE DAR DE ALTA
			iCodigo = -49;
		}
		else if (movimiento.getCOACCI().equals("M") && !QMReferencias.existeReferenciaCatastral(movimiento.getNURCAT()))
		{
			//Error 050 - LA REFERENCIA CATASTRAL NO EXISTE NO SE PUEDE MODIFICAR
			iCodigo = -50;
		}
		else if (movimiento.getCOACCI().equals(ValoresDefecto.DEF_BAJA) && !QMReferencias.existeReferenciaCatastral(movimiento.getNURCAT()))
		{
			//Error 051 - LA REFERENCIA CATASTRAL NO EXISTE NO SE PUEDE DAR DE BAJA
			iCodigo = -51;
		}
		else if (movimiento.getCOACCI().equals(ValoresDefecto.DEF_BAJA) && QMImpuestos.tieneImpuestoRecurso(movimiento.getNURCAT()))
		{
			//Error 053 - EXISTEN DATOS EN GMAE57. NO SE PUEDE REALIZAR LA BAJA
			iCodigo = -53;
		}
		
		else if (sEstado.equals(ValoresDefecto.DEF_ALTA) && movimiento.getCOACCI().equals(ValoresDefecto.DEF_ALTA))
		{
			//error alta de una referencia en alta
			iCodigo = -801;
		}
		else if (sEstado.equals(ValoresDefecto.DEF_BAJA) && !movimiento.getCOACCI().equals(ValoresDefecto.DEF_ALTA))
		{
			//error referencia de baja, solo puede recibir altas
			iCodigo = -802;
		}
		else if (sEstado.equals("") && !movimiento.getCOACCI().equals(ValoresDefecto.DEF_ALTA))
		{
			//error estado no disponible
			iCodigo = -803;
		}
		
		return iCodigo;
	}
	
	public static int registraMovimiento(MovimientoReferenciaCatastral movimiento)
	{
		int iCodigo = revisaMovimiento(movimiento);

		if (iCodigo == 0)
		{
			MovimientoReferenciaCatastral movimiento_revisado = revisaCodigosControl(movimiento);
			if (movimiento_revisado.getCOACCI().equals("#"))
			{	
				//error modificacion sin cambios
				iCodigo = -804;
			}
			else
			{
				int indice = QMMovimientosReferencias.addMovimientoReferenciaCatastral(movimiento_revisado);
				
				if (indice == 0)
				{
					//error al crear un movimiento
					iCodigo = -900;
				}
				else
				{	
					ValoresDefecto.TIPOSACCIONES COACCES = ValoresDefecto.TIPOSACCIONES.valueOf(movimiento.getCOACCI());
					
					switch (COACCES) 
					{
						case A:
							ReferenciaCatastral referenciadealta = convierteMovimientoenReferencia(movimiento_revisado);

							logger.debug("Dando de alta la referencia...");

							logger.debug(referenciadealta.logReferenciaCatastral());
						
							if (estaDeBaja(movimiento_revisado.getNURCAT()))
							{
								if (QMListaReferencias.addRelacionReferencia(movimiento_revisado.getNURCAT(), movimiento_revisado.getCOACES(), Integer.toString(indice)))
								{
									//OK 
									if (QMReferencias.setEstado(movimiento_revisado.getNURCAT(), ValoresDefecto.DEF_ALTA))
									{
										//Se cambian los valores de la antigua referencia
										if(QMReferencias.modReferenciaCatastral(convierteMovimientoenReferencia(movimiento), movimiento_revisado.getNURCAT()))
										{
											//OK 
											iCodigo = 0;
										}
										else
										{
											QMMovimientosReferencias.delMovimientoReferenciaCatastral(Integer.toString(indice));
											QMListaReferencias.delRelacionReferencia(Integer.toString(indice));
											QMReferencias.setEstado(movimiento_revisado.getNURCAT(), ValoresDefecto.DEF_BAJA);
											iCodigo = -904;						
										}
									}
									else
									{
										//error estado no establecido - Rollback
										QMMovimientosReferencias.delMovimientoReferenciaCatastral(Integer.toString(indice));
										QMListaReferencias.delRelacionReferencia(Integer.toString(indice));
										iCodigo = -903;
									}
								}
								else
								{
									//error relacion referencia no creada - Rollback
									QMMovimientosReferencias.delMovimientoReferenciaCatastral(Integer.toString(indice));
									iCodigo = -902;
								}
								

							}
							else
							{
								if (QMReferencias.addReferenciaCatastral(referenciadealta))
								{
									//OK - referencia creada
									logger.debug("Hecho!");
									if (QMListaReferencias.addRelacionReferencia(movimiento_revisado.getNURCAT(), movimiento_revisado.getCOACES(), Integer.toString(indice)))
									{
										//OK 
										iCodigo = 0;
									}
									else
									{
										//error relacion referencia no creada - Rollback
										QMReferencias.delReferenciaCatastral(movimiento_revisado.getNURCAT());
										QMMovimientosReferencias.delMovimientoReferenciaCatastral(Integer.toString(indice));
										iCodigo = -902;
									}
								}
								else
								{
									//error referencia no creada - Rollback
									QMMovimientosReferencias.delMovimientoReferenciaCatastral(Integer.toString(indice));
									iCodigo = -901;
								}
							}
							
							break;
						case B:
							if (QMListaReferencias.addRelacionReferencia(movimiento_revisado.getNURCAT(), movimiento_revisado.getCOACES(), Integer.toString(indice)))
							{
							
								if (QMReferencias.setEstado(movimiento_revisado.getNURCAT(), ValoresDefecto.DEF_BAJA))
								{
									//OK 
									iCodigo = 0; 
								}
								else
								{
									//ReferenciaCatastral referenciadebaja = convierteMovimientoenReferencia(movimiento);
									//error estado no establecido - Rollback
									//QMReferencias.addReferenciaCatastral(referenciadebaja);
									QMMovimientosReferencias.delMovimientoReferenciaCatastral(Integer.toString(indice));
									QMListaReferencias.delRelacionReferencia(Integer.toString(indice));
									iCodigo = -903;
								}
	
							}
							else
							{
								//error relacion referencia no creada - Rollback
								QMMovimientosReferencias.delMovimientoReferenciaCatastral(Integer.toString(indice));
								iCodigo = -902;
							}
							break;
						case M:
							if (QMListaReferencias.addRelacionReferencia(movimiento_revisado.getNURCAT(), movimiento_revisado.getCOACES(), Integer.toString(indice)))
							{
								//ReferenciaCatastral referenciamodificada = QMReferencias.getReferenciaCatastral( movimiento_revisado.getNURCAT());
								if(QMReferencias.modReferenciaCatastral(convierteMovimientoenReferencia(movimiento), movimiento_revisado.getNURCAT()))
								{
									//OK 
									iCodigo = 0;
								}
								else
								{
									QMMovimientosReferencias.delMovimientoReferenciaCatastral(Integer.toString(indice));
									QMListaReferencias.delRelacionReferencia(Integer.toString(indice));
									iCodigo = -904;						
								}

							}
							else
							{
								//error relacion referencia no creada - Rollback
								QMMovimientosReferencias.delMovimientoReferenciaCatastral(Integer.toString(indice));
								iCodigo = -902;
							}
							break;
						default:
							break;
					}
				}
			}
			
		}
		logger.debug("iCodigo:|{}|",iCodigo);
		
		return iCodigo;
	}
}
