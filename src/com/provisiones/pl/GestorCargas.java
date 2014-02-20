package com.provisiones.pl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFPicture;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.util.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.provisiones.dal.ConnectionManager;
import com.provisiones.ll.FileManager;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.ResultadoCarga;
import com.provisiones.types.tablas.ResultadosTabla;

public class GestorCargas implements Serializable
{
	private static final long serialVersionUID = 942487732660619012L;

	private static Logger logger = LoggerFactory.getLogger(GestorCargas.class.getName());
	
	private transient ArrayList<ResultadosTabla> tablamensajes = new ArrayList<ResultadosTabla>();

	private String sArchivo = "resultado";
	private String sDuracion = "";
	private String sRegistrosProcesados = "";
	private String sRegistrosErroneos = "";
	
	private int iContador = 0;
	
	private boolean bPoll = true;
	
	public GestorCargas ()
	{
		if (ConnectionManager.comprobarConexion())
		{
			logger.debug("Iniciando GestorCargas...");
		}
	}

	public void borrarResultadosCarga()
	{
    	this.tablamensajes = new ArrayList<ResultadosTabla>();
    	this.sArchivo = "resultado";
    	this.sDuracion = "";
    	this.sRegistrosProcesados = "";
    	this.sRegistrosErroneos = "";
    	this.iContador = 0;
	}
	
    public void limpiarPlantilla(ActionEvent actionEvent) 
    {  
    	borrarResultadosCarga();
    }
    
    public void cuenta() 
    {  
        iContador=iContador+1;
        logger.debug("iContador:"+iContador);
    } 
    
	public void cargaArchivo(FileUploadEvent event) 
    {
		borrarResultadosCarga();
		
		if (ConnectionManager.comprobarConexion())
		{
			this.bPoll = false;
			
			FacesMessage msg;
			
			logger.debug("Iniciando carga...");
			
			boolean bRecibido = false; 
			
			ResultadoCarga resultado = FileManager.splitter(FileManager.guardarFichero(event,bRecibido),bRecibido);
			
			int iCodigoError = resultado.getiCodigo();

			this.sArchivo = resultado.getsArchivo()+"-resultado";

			this.sRegistrosProcesados = Long.toString(resultado.getLiRegistrosProcesados());
			this.sRegistrosErroneos = Long.toString(resultado.getLiRegistrosProcesados()-resultado.getLiRegistrosCorrectos());

			this.sDuracion = resultado.getsDuracion();
			
			logger.debug("iCodigoError:|{}|",iCodigoError);
			
			if (resultado.getAlCarga().size() > 0)
			{
				this.tablamensajes.addAll(resultado.getAlCarga());
			}
			
			logger.debug("tablamensajes.size():|{}|",tablamensajes.size());
			
			String sMsg = "";
			
			switch (iCodigoError) 
			{
			case 0:
				sMsg = "'"+event.getFile().getFileName() +"' ha subido correctamente.";
				msg = Utils.pfmsgInfo(sMsg);
				logger.info(sMsg);
				break;
			case 1:
				sMsg = "El archivo de Activos debe de ser cargado por recepción.";
				msg = Utils.pfmsgWarning(sMsg);
				logger.warn(sMsg);
				break;
			case 2:
				sMsg = "El archivo de Rechazados debe de ser cargado por recepción.";
				msg = Utils.pfmsgWarning(sMsg);
				logger.warn(sMsg);
				break;
			case 3:
				sMsg = "El archivo de Autorizados debe de ser cargado por recepción.";
				msg = Utils.pfmsgWarning(sMsg);
				logger.warn(sMsg);
				break;
			case 4:
				sMsg = "El archivo de Gastos debe de ser validado por el banco.";
				msg = Utils.pfmsgWarning(sMsg);
				logger.warn(sMsg);
				break;
			case 10:
				sMsg = "ERROR: El archivo '"+event.getFile().getFileName() +"' no tiene un nombre reconocible. Por favor, reviselo.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
				break;
			default:
				sMsg = "ERROR: Se encontraron problemas al procesar el archivo '"+event.getFile().getFileName() +"', contiene registros inconsistentes con el sistema. Por favor, reviselo.";
				msg = Utils.pfmsgFatal(sMsg);
				logger.error(sMsg);
				break;
			}
			
			this.bPoll = true;
			
			FacesContext.getCurrentInstance().addMessage(null, msg);
			
			logger.debug("Carga completada!");
		}

	}

	//Obtener datos de la tabla
	public String[] ObtenerCabeceraDelDocumento(){
		//Este código ha de ser reemplazado por el pertinente que obtenga los datos de forma dinámica.
		String valores[] = new String[7];
		
		valores[0] ="GUTIERREZ LABRADOR"; //Constante
		valores[1] ="SOCIEDAD PATRIMONIAL: " + "SAREB 2013 BANCAJA HABITAT"; //COSPAT
		valores[2] ="PETICIÓN PROVISIONAL DE FONDOS"; //Constante
		valores[3] ="ALQUILADO"; //TAS
		valores[4] ="COMUNIDAD"; //AUTO
		valores[5] ="PROVISIÓN Nº " + "401304082"; //NUPROF
		valores[6] ="FECHA: " + "27/11/2013"; //FEPFON
		
		return valores;
	}

	//preProceso en XML
	public void preProcessXLS(Object documento) { 
	    //Creando los objetos de Excel
		HSSFWorkbook wb = (HSSFWorkbook) documento;
		HSSFSheet sheet = wb.getSheetAt(0);
		
		//Creamos las filas en blanco antes del proceso ya que si lo hacemos después perderemos filas de la tabla exportada
	    //Crearemos 10 filas de 0 a 10
	    for (int i = 0; i < 11; i++) {
			sheet.createRow(i);
		}
	}//Fin del PreProceso en XML
	
	
	//PostProceso en XML
	public void postProcessXLS(Object documento) {  	
	    	//Creando los objetos de Excel
		    HSSFWorkbook wb = (HSSFWorkbook) documento;  //Tomamos el libro del documento
		    HSSFSheet sheet = wb.getSheetAt(0);  //Tomamos la primera pestaña del libro
		    HSSFRow header = sheet.getRow(0); //Tomamos la primera fila de la pestaña
		    HSSFRow row = sheet.createRow(10);	//Tomamos la fila de destino
		    HSSFCell headerCell = null;	//Reservamos una variable para la celda de la cabecera
		    HSSFCell cell = null; //Reservamos una variable para recorrer celdas de destino
		    
		    //Variable con el grosor de la tabla que exportamos
		    int anchoTabla = header.getPhysicalNumberOfCells();
		    
		    //Creando la fuentes
			    //Fuente para el encabezado del documento
			    HSSFFont fontEncabezado = wb.createFont(); //Creamos una fuente para el texto
			    fontEncabezado.setFontHeightInPoints((short) 12); //Indicamos el tamaño de la fuente
			    fontEncabezado.setBoldweight((short) HSSFFont.BOLDWEIGHT_BOLD); //Marcamos que sea negrita
			    
			    //Fuente para la cabecera de la tabla
			    HSSFFont fontTabla = wb.createFont();
			    fontTabla.setBoldweight((short) HSSFFont.BOLDWEIGHT_BOLD);
		    
			//Creando los estilos de la celda
			    //Creando el estilo de la cabecera de la tabla
			    HSSFCellStyle cellStyleTabla = wb.createCellStyle(); //Creamos un estilo a aplicar en las celdas
			    cellStyleTabla.setFont(fontTabla); //Aplicamos nuestra fuente al estilo
			    cellStyleTabla.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index); //Modificar el relleno de la celda  
			    cellStyleTabla.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			    cellStyleTabla.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			    cellStyleTabla.setBottomBorderColor(HSSFColor.BLACK.index);
			    
			    //Creando el estilo de la cabecera del documento
			    HSSFCellStyle cellStyleEncabezado = wb.createCellStyle();
			    cellStyleEncabezado.setFont(fontEncabezado); //Aplicamos nuestra fuente al estilo
			    		    
			    //Creando estilo para la fila 1
			    HSSFCellStyle cellStyleFila0 = wb.createCellStyle();
			    cellStyleFila0.setFillForegroundColor(HSSFColor.BROWN.index);
			    cellStyleFila0.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			    
			    //Creando estilo para la fila 2
			    HSSFCellStyle cellStyleFila1 = wb.createCellStyle();
			    cellStyleFila1.setFillForegroundColor(HSSFColor.GREEN.index);
			    cellStyleFila1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			    
			    //Personalizar Colores
			    	//Marrón personalizado
			    	HSSFPalette palette = wb.getCustomPalette();
			    	palette.setColorAtIndex(HSSFColor.BROWN.index,
			                (byte) 51,  //RGB red (0-255)
			                (byte) 51,    //RGB green
			                (byte) 0     //RGB blue
			        );
			    	
			    	//Verde personalizado
			    	palette.setColorAtIndex(HSSFColor.GREEN.index,(byte) 0, (byte) 204, (byte) 51);
		    	
		    //*** Aplicando modificaciones al documento ***
			    //1º Moveremos la primera fila que es la cabecera de la tabla a la última fila que añadimos en el PreProcessXLS
			    for(int i=0; i < anchoTabla;i++) { //Recorremos la fila de origen
			    	headerCell = header.getCell(i); //Tomamos la celda de origen
			    	cell = row.createCell(i); //Creamos una fila(Al crear filas reemplaza la fila existente por la que creamos)
			    	cell.setCellValue(headerCell.getStringCellValue()); //getStringCellValue() sólo es válido si la celda contiene texto, consultar "http://poi.apache.org/spreadsheet/quick-guide.html#CreateCells" el apartado "Getting the cell contents"
					headerCell.setCellValue(""); //Borramos el origen
			    }
			    
			    //2º Aplicamos el estilo a la fila que hemos seleccionado como cabecera de la tabla exportada que hemos movido de la fila 0 a la i
			    for(int i=0; i < anchoTabla;i++) {  
			        cell = row.getCell(i); //Seleccionamos la celda  
			        cell.setCellStyle(cellStyleTabla);  //Aplicamos el formato
			    }
			    
			    //3º Dar estilo a las filas 1 y 2
				    //Modificar el alto y el color de las filas
			    		//Procesamos la primera fila
			    		row = sheet.getRow(0); //Seleccionamos la fila0
				    	row.setHeightInPoints((short)60); //Modificamos la altura de la fila0
				    	cell = row.createCell(0); //Seleccionamos la celda 0
				    	cell.setCellStyle(cellStyleFila0); //Aplicamos el estilo de Fila 1 a la celda 0
				    	
				    	//Repetimos el proceso para la segunda fila
				    	row = sheet.getRow(1);
				    	row.setHeightInPoints((short)10);
				    	cell = row.createCell(0);
				    	cell.setCellStyle(cellStyleFila1);
				    	
				    //Fusionar todas las celdas de la fila(Hay que fusionar las celdas después de dar formato a la celda0, de lo contrario no aplica ningún formato)
				    	//sheet.addMergedRegion(new CellRangeAddress(first row (0-based),  last row  (0-based),  first column (0-based),  last column  (0-based) ));
				    	//Fusionar la Fila1 con el ancho de la tabla que exportamos
				    	sheet.addMergedRegion(new CellRangeAddress(0,0,0,(anchoTabla-1)));
				    	//Fusionar la fila 2 con el ancho de la tabla que exportamos
				    	sheet.addMergedRegion(new CellRangeAddress(1,1,0,(anchoTabla-1)));

				    //4º Insertar los datos en el resto del encabezado del documento
						/*
						 Cabecera del documento 
						___________________
						| valor0 | valor1  |
						| valor2 | valor3  |
						| valor4 |		   |
						| valor5 | valor6  |
						|__________________|
						
						*/
						//En estas variables podemos meter los datos que saquemos de las SQL
						String valor[] = ObtenerCabeceraDelDocumento();
				    
				    	//Fila 3
				    	row = sheet.getRow(3); 	cell = row.createCell(1); cell.setCellValue(valor[0]);
				    	row = sheet.getRow(3); 	cell = row.createCell(3); cell.setCellValue(valor[1]);

				    	//Fila 5
				    	row = sheet.getRow(5); 	cell = row.createCell(1); cell.setCellValue(valor[2]);
				    	row = sheet.getRow(5); 	cell = row.createCell(3); cell.setCellValue(valor[3]);
				    	
				    	//Fila 6
				    	row = sheet.getRow(6); 	cell = row.createCell(1); cell.setCellValue(valor[4]);
				    	
				    	//Fila 8
				    	row = sheet.getRow(8); 	cell = row.createCell(1); cell.setCellValue(valor[5]);
				    	row = sheet.getRow(8); 	cell = row.createCell(3); cell.setCellValue(valor[6]);
				    	
				//5º Ajustar columnas 1 y 3 al contenido de las celdas 
				    	sheet.autoSizeColumn(1);
				    	sheet.autoSizeColumn(3);
				
				//6º Añadir la imagen
				    	//Definimos la ruta donde se encuentran nuestro recursos de imagen
				    	String rutaImagen = ValoresDefecto.DEF_RESOURCES_PATH + "favicon.jpeg";
				    	try {
				    		//Creación del InputStream
				    		InputStream is = new FileInputStream(rutaImagen);
				    		//InputStream is = new FileInputStream("C:\\Users\\Portatil\\git\\provisiones\\src\\Bankia.JPG");
				    	    byte[] bytes = IOUtils.toByteArray(is);
				    	    int pictureIdx = wb.addPicture(bytes, HSSFWorkbook.PICTURE_TYPE_JPEG);
				    	    is.close();
				    	    
				    	    /* Create the drawing container */
				    	    HSSFPatriarch drawing = sheet.createDrawingPatriarch();
				    	    
				    	    /* Create an anchor point */
				    	    ClientAnchor anchor = new HSSFClientAnchor();
				    	    
				    	    /* Define top left corner, and we can resize picture suitable from there */
				    	    anchor.setCol1(0);
				    	    anchor.setRow1(0);
				    	    
				    	    
				    	    /* Invoke createPicture and pass the anchor point and ID */
			                HSSFPicture  picture = drawing.createPicture(anchor, pictureIdx);
			                /* Call resize method, which resizes the image */
				    	    picture.resize();
				    	} catch (FileNotFoundException e){
				    		logger.debug("ERROR IMAGEN FileNotFoundException: " + e.getMessage() + " - Traza: " + e.getStackTrace() + " - Causa: " + e.getCause());
				    		logger.debug("RUTA ERROR: " + rutaImagen);

						} catch (Exception e) {
							
							// TODO: handle exception
							logger.debug("ERROR IMAGEN exception: " + e.getMessage() + " - Traza: " + e.getStackTrace() + " - Causa: " + e.getCause());
						}
	}//Fin del PostProceso en XML
	
	
	//PreProceso del PDF
	public void preProcessPDF(Object documento) {
		Document pdf = (Document) documento;
	    pdf.open();  
	    pdf.setPageSize(PageSize.A4);  
	    //ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
	    String rutaImagen = ValoresDefecto.DEF_RESOURCES_PATH + "favicon.jpeg";
	    //String logo = servletContext.getRealPath("") + File.separator + "images" + File.separator + "prime_logo.png";  
	  
	    try {
			pdf.add(Image.getInstance(rutaImagen));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			logger.debug("ERROR IMAGEN MalformedURLException: " + e.getMessage() + " - Traza: " + e.getStackTrace() + " - Causa: " + e.getCause());
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			logger.debug("ERROR IMAGEN DocumentException: " + e.getMessage() + " - Traza: " + e.getStackTrace() + " - Causa: " + e.getCause());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.debug("ERROR IMAGEN IOException: " + e.getMessage() + " - Traza: " + e.getStackTrace() + " - Causa: " + e.getCause());
		} catch (Exception e){
			logger.debug("ERROR IMAGEN Exception: " + e.getMessage() + " - Traza: " + e.getStackTrace() + " - Causa: " + e.getCause());
		}  
	}//Fin del PreProceso en PDF
	
	
	
	//PosProcesoen PDF
	public void postProcessPDF(Object documento) {
		//Código del post proceso
	}//Fin del PosProceso en PDF
	
	public void preProcessPDF2(Object documento){
	//En el preproceso del PDF podemos crear páginas de portada y definir el formato que tendrá nuestro PDF, por ejemplo "apaisado" o determinadas fuentes de texto etc...
	//En el preproceso NO se puede cerrar el documento, ya que no se podrían añadir los datos del "proceso" y el "postproceso" en caso de que existiese este segundo.
		Document pdf = (Document) documento;
		pdf.open();
		//pdf.setPageSize(PageSize.A4); //Tamaño A4 vertical 
		//pdf.setPageSize(PageSize.LETTER.rotate()); //Landscape o Apaisado(No afecta a la primera página, sólo al proceso de los datos)
		pdf.setPageSize(PageSize.A4.rotate()); //Tamaño A4 horizontal
		
		//Creamos una serie de fuentes para los diferentes apartados
		Font tituloFont = FontFactory.getFont(FontFactory.TIMES_ROMAN , 22);
		Font cuerpoFont = FontFactory.getFont(FontFactory.COURIER, 12);
		Font pieFont = FontFactory.getFont(FontFactory.COURIER_BOLD, 8);
		
		//*****************************TRABAJANDO CON ITEXT**************************************-/

		//Añadir Metadata
		    pdf.addTitle("Mis primeros trabajos en PDF");
		    pdf.addSubject("Usando iText");
		    pdf.addKeywords("Java, PDF, iText");
		    pdf.addAuthor("GLSL DevTeam");
		    pdf.addCreator("GLSL Portales");

		//
		    Paragraph preface = new Paragraph();
		    // We add one empty line
		    //Llamamos a un método que inserta tantas líneas en blanco como le pasamos por parámetro.
		    addEmptyLine(preface, 1);
		    
		    // Lets write a big header
		    //Aplicamos la fuente "tituloFont" que hemos creado al principio de este procedimiento.
		    preface.add(new Paragraph("Título del documento", tituloFont));

		    addEmptyLine(preface, 1);
		    
		    // Will create: Report generated by: _name, _date
		    preface.add(new Paragraph("Reporte generado por: " + System.getProperty("user.name") + ", " + new Date(), cuerpoFont));//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		   
		    addEmptyLine(preface, 3);
		    preface.add(new Paragraph("Este documento describe algo que es muy importante ", cuerpoFont));

		    addEmptyLine(preface, 8);

		    preface.add(new Paragraph("Este documento es una versión preliminar y no está sujeta a ninguna autorización de licencia.", pieFont));

		    try {
				pdf.add((Element) preface);
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error("Error iText al añadir el elemento 'preface' al documento PDF.");
			}
		    // Start a new page
		    pdf.newPage();
			
		
		//********************************FIN DEL TRABAJO CON ITEXT******************************-/
	}//Fin de preProcessPDF2
	
	private static void addEmptyLine(Paragraph paragraph, int number) {
	    for (int i = 0; i < number; i++) {
	      paragraph.add(new Paragraph(" "));
	    }
	}
	
	//PosProceso2 en PDF
	public void postProcessPDF2(Object documento) {
		
	}//Fin del PosProceso2 en PDF
	
	
	public ArrayList<ResultadosTabla> getTablamensajes() {
		return tablamensajes;
	}


	public void setTablamensajes(ArrayList<ResultadosTabla> tablamensajes) {
		this.tablamensajes = tablamensajes;
	}

	public String getsArchivo() {
		return sArchivo;
	}

	public void setsArchivo(String sArchivo) {
		this.sArchivo = sArchivo;
	}

	public String getsDuracion() {
		return sDuracion;
	}

	public void setsDuracion(String sDuracion) {
		this.sDuracion = sDuracion;
	}

	public String getsRegistrosProcesados() {
		return sRegistrosProcesados;
	}

	public void setsRegistrosProcesados(String sRegistrosProcesados) {
		this.sRegistrosProcesados = sRegistrosProcesados;
	}

	public String getsRegistrosErroneos() {
		return sRegistrosErroneos;
	}

	public void setsRegistrosErroneos(String sRegistrosErroneos) {
		this.sRegistrosErroneos = sRegistrosErroneos;
	}

	public int getiContador() {
		return iContador;
	}

	public void setiContador(int iContador) {
		this.iContador = iContador;
	}

	public boolean isbPoll() {
		return bPoll;
	}

	public void setbPoll(boolean bPoll) {
		this.bPoll = bPoll;
	}  
}
