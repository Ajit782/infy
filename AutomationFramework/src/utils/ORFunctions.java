package utils;

import handlers.ExcelHandler;

import java.io.File;
import java.io.IOException;

import utils.PropertiesAndConstants;
import utils.UtilityFunctions;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ORFunctions {
	
	
	/// <summary>
	/// Author            : Thakur
	/// Description       : Getting Object Values From OR File
	/// </summary>
	
	public static String GetObjRepositoryKeyValue(String keyName)
	{
		String objValue=PropertiesAndConstants.objRepo.get(keyName);
		return objValue+"";
	}
	
	
	/// <summary>
	/// Author            : Thakur
	/// Description       : Getting Object's Frame Value From OR File
	/// </summary>
	
	public static  String GetObjFrameValue(String keyName)
	{
		
		String objFramesValue=PropertiesAndConstants.objFrames.get(keyName);
		if (UtilityFunctions.IsNullOrEmpty(objFramesValue))
				return "";
		else
			return objFramesValue;
		
		//if ((PropertiesAndConstants.objFrames.size() > 0) && (UtilityFunctions.IsNullOrEmpty(PropertiesAndConstants.objFrames.get(objFramesValue))))
		//	return objFramesValue;

		//return "";
	}
	
	
	/// <summary>
	/// Author            : Thakur
	/// Description       : Populating HashMap With All Objects in OR File
	/// </summary>
	
	public static void PopulateObjRepositoryDictionary(String fileName) throws IOException, BiffException
    {
        PropertiesAndConstants.objRepo.clear();
        PropertiesAndConstants.objFrames.clear();
        
        File file = new File(fileName);
        Workbook Workbook = ExcelHandler.OpenExcelToRead(fileName);
        
        try 
   	    {
        if (file.exists())
        {
           LogFunctions.LogEntry("Start Processing OR for " + PropertiesAndConstants.Application + " App...", false);
           /*
           Workbook testDataWBook;
	   	      testDataWBook = Workbook.getWorkbook(file);
	   	      sheet = testDataWBook.getSheet(0);
	        */	
	            int startRow = 1;
	            boolean hasContent = false;
	            int row = startRow;
	
	            do
	            {	/*
	            	String keyObj = ExcelHandler.FetchCellValue(fileName,0,row,0);//   sheet.getCell(0, row).getContents();
	            	String valueLocator = ExcelHandler.FetchCellValue(fileName,0,row,1);//sheet.getCell(1, row).getContents();
	            	String valueFrames = ExcelHandler.FetchCellValue(fileName,0,row,2);//sheet.getCell(2, row).getContents();
	            	*/
	            	
	            	String keyObj = ExcelHandler.ReadExcelCell(Workbook, 0,row, 0);// (fileName,0,row,0);//   sheet.getCell(0, row).getContents();
	            	String valueLocator = ExcelHandler.ReadExcelCell(Workbook, 0,row, 1);//sheet.getCell(1, row).getContents();
	            	String valueFrames = ExcelHandler.ReadExcelCell(Workbook, 0,row, 2);//sheet.getCell(2, row).getContents();
                
	            	hasContent = false;
	                if ((keyObj.equals(null)||keyObj.equals("")) && !(valueLocator.equals(null)||valueLocator.equals("")))
	                {
	
	                   LogFunctions.LogEntry("Processing element in row : " + row + " was skipped", false);
	                   LogFunctions.LogEntry("Element name is empty for locator : "+ valueLocator, false);
	                   continue;
	                }
	               
	                if (!(keyObj.equals(null)||keyObj.equals("")) && (valueLocator.equals(null)||valueLocator.equals("")))
	                {
	                	LogFunctions.LogEntry("Processing element in row : " + row + " was skipped", false);
	                	LogFunctions.LogEntry("Locator value is empty for element : "+ keyObj, false);
	                  continue;
	                }
	                if (!(keyObj.equals(null)||keyObj.equals("")) && !(valueFrames.equals(null)||valueFrames.equals("")))
	                {
	                    PropertiesAndConstants.objFrames.put(keyObj, valueFrames);
	                }
	                if (!(keyObj.equals(null)||keyObj.equals("")) && !(valueLocator.equals(null)||valueLocator.equals("")))
	                {
	                    hasContent = true;
	                    row++;
	                    if ((!PropertiesAndConstants.objRepo.containsKey(keyObj)) && (!(keyObj.equals(null)||keyObj.equals("")) && (!(valueLocator.equals(null)||valueLocator.equals("")))))
	                        PropertiesAndConstants.objRepo.put(keyObj, valueLocator);
	                    else LogFunctions.LogEntry("Object Repository contain the same element "+ keyObj +" : "+ valueLocator, false);
	                }
	                else LogFunctions.LogEntry("Processing OR for " + PropertiesAndConstants.Application + " App has been completed in row : "+ row, false);
	            }
	            while (hasContent);
	            
	            //testDataWBook.close();
	            
	            ExcelHandler.CloseExcel(Workbook);
	   	    }
	        else
	        {
	        	LogFunctions.LogEntry("Cannot find Object Repository file for " + PropertiesAndConstants.Application, false);
	        }
        }
        catch (Exception e) {
        	LogFunctions.LogEntry(e.getMessage(), false);
		}
    }
}//End oF Class ORFunctions
