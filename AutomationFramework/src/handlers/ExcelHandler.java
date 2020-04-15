package handlers;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFHyperlink;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javafx.scene.control.Hyperlink;
import utils.LogFunctions;
import utils.UtilityFunctions;


public class ExcelHandler {
	
	/// <summary>
	/// Description       : Closing All Excel Files If Already Opened
	/// </summary>
	
	public static void CloseAllExcelFiles() throws IOException, Exception
	{
		if (UtilityFunctions.isProcessRunning("EXCEL.EXE"))
		{
			Runtime.getRuntime().exec("taskkill /IM EXCEL.EXE");
		}
	}
	
	/// <summary>
	/// Description       : Creating Excel Workbook
	/// </summary>
	
	public static Workbook ExcelOpenWorkbook(String path) throws BiffException, IOException
    {
        Workbook excelWorkBook;
        File excelwoorbookfile=new File(path);
        excelWorkBook=Workbook.getWorkbook(excelwoorbookfile);
        return excelWorkBook;
    }
	
	
	/// <summary>
	/// Description       : Opening Excel Workbook
	/// </summary>
	
	public static Workbook OpenExcelToRead(String FilePath) throws IOException, BiffException
	{
		File inputWorkbook = new File(FilePath);
		if(inputWorkbook.exists())
        {
			return Workbook.getWorkbook(inputWorkbook);
			
        }
		else
		{
			LogFunctions.LogEntry("File does not exist...", false);
			return null;
		}
	}
	
	/// <summary>
	/// Description       : Reading Data From Excel Workbook
	/// </summary>
	
	public static String ReadExcelCell(Workbook WorkbookToRead,int SheetIndex, int Row,int Column) throws IOException, BiffException
	{
		jxl.Sheet sheet = WorkbookToRead.getSheet(SheetIndex); 
		String cellValue =((jxl.Sheet) sheet).getCell(Column, Row).getContents().toString(); 
		return cellValue;
	}
	
	/// <summary>
	/// Description       : Closing Excel Workbook
	/// </summary>
	
	public static void CloseExcel(Workbook WorkbookToClose) throws IOException, BiffException
	{
		WorkbookToClose.close();
	}
	
	
	/// <summary>
	/// Description       : Fetchinng Cell Value From Excel Workbook 
	/// </summary>
	
	public static String FetchCellValue(String Sheetpath,int SheetIndex, int Row,int Column) throws Exception
	{
		Workbook workbook = Workbook.getWorkbook(new File(Sheetpath)); 
		jxl.Sheet sheet = workbook.getSheet(SheetIndex); 
		String cellValue =((jxl.Sheet) sheet).getCell(Column, Row).getContents().toString(); 
		workbook.close();
		return (cellValue);
	}
	
	/*
	public static String FetchCellValue(String Sheetpath,int SheetIndex, int Row,int Column) throws Exception
	{
		File inputWorkbook = new File(Sheetpath);
		Workbook workbook = null ;
		jxl.Sheet sheet;
		String cellvalue="";
		if(inputWorkbook.exists())
        {
			workbook = Workbook.getWorkbook(inputWorkbook);
			sheet = workbook.getSheet(0);
			 cellvalue= sheet.getCell(Column, Row).getContents().toString();
			 workbook.close();
			 return (cellvalue);
        }
		else
			 LogFunctions.LogEntry("File does not exist...", false);
		return "";
	}
	
	*/
	/// <summary>
	/// Description       : Writing String Data in Excel Sheet Cell
	/// </summary>
	public static void WriteToSheet(String SheetPath, int SheetIndex,int Row,int Column,String Value) throws Exception
	{
		File WritableFile=new File(SheetPath);
		FileInputStream FileToWrite = new FileInputStream(WritableFile);                
		HSSFWorkbook ReportWorkbook = new HSSFWorkbook(FileToWrite);
		HSSFSheet Reportsheet = ReportWorkbook.getSheetAt(SheetIndex);
		HSSFCell cell=Reportsheet.getRow(Row).getCell(Column);
	    cell.setCellValue(Value);
		FileOutputStream CloseFile=new FileOutputStream(WritableFile);
		ReportWorkbook.write(CloseFile);
		CloseFile.close();
	}
	/// <summary>
	/// Description       : Writing Link in Excel Sheet Cell
	/// </summary>
	public static void WriteLinkToSheet(String SheetPath, int SheetIndex,int Row,int Column,String Value, String LinkValue) throws Exception
	{
		File WritableFile=new File(SheetPath);
		FileInputStream FileToWrite = new FileInputStream(WritableFile);  
		HSSFWorkbook ReportWorkbook = new HSSFWorkbook(FileToWrite);
		HSSFSheet Reportsheet = ReportWorkbook.getSheetAt(SheetIndex);
		//HSSFHyperlink Link=new HSSFHyperlink(HSSFHyperlink(link_type));
		HSSFHyperlink Link=new HSSFHyperlink(HSSFHyperlink.LINK_URL);
		Link.setAddress(LinkValue);
		HSSFCell cell=Reportsheet.getRow(Row).getCell(Column);
	    cell.setCellValue(Value);
	    cell.setHyperlink(Link);
		FileOutputStream CloseFile=new FileOutputStream(WritableFile);
		ReportWorkbook.write(CloseFile);
		CloseFile.close();
	}
	/// <summary
	/// Description       : Reading Excel Sheet
	/// </summary>
	
	public static void ReadSheet(String FilePath) throws BiffException, IOException
	{
		File ReadableFile = new File(FilePath);
		Workbook SuiteWorkbook=Workbook.getWorkbook(ReadableFile);              
		jxl.Sheet SuiteSheet =  SuiteWorkbook.getSheet(0);
	}//End of method ReadSheet
}//End of class 