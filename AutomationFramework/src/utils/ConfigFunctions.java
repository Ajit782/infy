package utils;


import handlers.ExcelHandler;
import handlers.XMLHandler;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

import org.w3c.dom.Document;

import utils.PropertiesAndConstants;

public class ConfigFunctions 
{
	/// <summary>
	/// Author            :  Thakur
	/// Description       : Opening XML File and Calling a Function to Store All the Objects of Environment File In a HashMap
	/// </summary>
	
	public static void populateEnvDictionary(String envFilePath) 
    {
		try
		{
			Document doc = XMLHandler.OpenXML(envFilePath);
			PropertiesAndConstants.envDictionary = XMLHandler.populateXMLDictionary(doc);
			//XMLHandler.CloseXML(envFilePath);
		}
		catch (Exception e) {
			System.out.println("Failed to Populate Environment Details "+e.getMessage());
		}
     }
	
	/// <summary>
	/// Author            :  Thakur
	/// Description       : Getting the value of Objects in Environment File which are stored in HashMap
	/// </summary>
	
	public static String getEnvKeyValue(String keyName)
	{
		try
		{
			String value;
			value = PropertiesAndConstants.envDictionary.get(keyName);
			return value;
		}
		catch (Exception e) {
			System.out.println("Failed to Fetch the Environemnt Value for Key:"+keyName+e.getMessage());
			return "";
		}	
	}
	/// <summary>
	/// Author            : Thakur
	/// Description       : Setting Up Default Values of All The Objects in Environment File 
	/// </summary>
	 
	public static void SetupEnvValues() throws Exception
	 {
		 PropertiesAndConstants.TestType =getEnvKeyValue("TESTTYPE"); 
		
		 PropertiesAndConstants.TemplatePath=getEnvKeyValue("TEMPLATEPATH");
		
		 PropertiesAndConstants.Application = getEnvKeyValue("APP").toUpperCase();
		
		 String TempTestSuitePath=getEnvKeyValue("SUITE");
		 
		 	if(PropertiesAndConstants.TempSuitPathsflag > -1)
		 	{
		 		System.out.println("inside if> -1");
		 		PropertiesAndConstants.TestType = PropertiesAndConstants.TempSuitePaths.get(PropertiesAndConstants.TempSuitPathsflag);
					 
			 } 
		 	else if(PropertiesAndConstants.TestType.contains(",")){
		 		System.out.println("inside elseif > -1");
			 PropertiesAndConstants.TempSuitePaths = Arrays.asList(PropertiesAndConstants.TestType.split(","));
			 PropertiesAndConstants.TempSuitePathsCount = PropertiesAndConstants.TempSuitePaths.size();		 
			 PropertiesAndConstants.TempSuitPathsflag++;
			 PropertiesAndConstants.TestType = PropertiesAndConstants.TempSuitePaths.get(PropertiesAndConstants.TempSuitPathsflag);
			 }		 
		 
		 
		 PropertiesAndConstants.TempTestSuitePath =PropertiesAndConstants.CurrentDirectory+TempTestSuitePath+PropertiesAndConstants.Application+"_"+PropertiesAndConstants.TestType+"_Suite.xls";
 
		 PropertiesAndConstants.TestSuitePath=PropertiesAndConstants.CurrentDirectory+TempTestSuitePath+PropertiesAndConstants.Application+"/"+PropertiesAndConstants.TestType+"/";
		
		 String dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss-a").format(Calendar.getInstance().getTime());
		 
		 String currentRunReportFolder = PropertiesAndConstants.CurrentDirectory + getEnvKeyValue("EXEREPPATH") + dateFormat + "_" + PropertiesAndConstants.TestType;
		 
		 PropertiesAndConstants.CurrentRunReportFolder =  currentRunReportFolder;
         
		 PropertiesAndConstants.TempDirectoryPath =  currentRunReportFolder + "/Temp/";
		 
		 PropertiesAndConstants.ScreenshotDirectoryPath=currentRunReportFolder+"/Screenshots/";
        
         String logFileName = PropertiesAndConstants.CurrentDirectory + getEnvKeyValue("LOGPATH") + "Log_" + dateFormat + ".log";
         PropertiesAndConstants.LogFile=logFileName;
         
         String tempTestLogPath = PropertiesAndConstants.TempDirectoryPath + "TestExecutionLog.txt";
         PropertiesAndConstants.TempTestLogPath =tempTestLogPath;
          
          //url  commented for test
          String url = getEnvKeyValue(PropertiesAndConstants.Application + "_" + getEnvKeyValue("ENVIRONMENT"));
          System.out.println(url+"   url");
          
          PropertiesAndConstants.Environment = getEnvKeyValue("ENVIRONMENT");
          
          //PropertiesAndConstants.Url=url;
         // PropertiesAndConstants.Browser = getEnvKeyValue("BROWSER");
          //System.out.println(PropertiesAndConstants.Browser+"555555555555555");
          //rohit comment
          
          String repository = PropertiesAndConstants.CurrentDirectory+getEnvKeyValue("OBJREPOSITORY")+PropertiesAndConstants.Application+"_OR.xls";
          PropertiesAndConstants.Repository=repository;
          System.out.println(PropertiesAndConstants.Repository+"555555555555555");
          
          
          String suiteFile = PropertiesAndConstants.CurrentDirectory + getEnvKeyValue("SUITE") + PropertiesAndConstants.Application + "_" + PropertiesAndConstants.TestType + "_Suite.xls";
          PropertiesAndConstants.SuiteFile = suiteFile;
          
          System.out.println(PropertiesAndConstants.SuiteFile+"66666666");
          
          String testscriptsdirectory = PropertiesAndConstants.CurrentDirectory + getEnvKeyValue("SCRIPTS") + PropertiesAndConstants.Application + "/" + PropertiesAndConstants.TestType+"/";
          
          PropertiesAndConstants.TempTestScriptPath=testscriptsdirectory;
         
          System.out.println(PropertiesAndConstants.TempTestScriptPath+"888888888");
                    
          PropertiesAndConstants.ReusableScriptPath=PropertiesAndConstants.CurrentDirectory+getEnvKeyValue("SCRIPTS");
          
          System.out.println(PropertiesAndConstants.ReusableScriptPath+"77777777");
          
          
          // ***** Create a Temporary Directory          
          LogFunctions.CreateReportFolder();
          System.out.println("Aftre create Repo");
          LogFunctions.SetLogFileHeader();
          System.out.println("Aftre set log file");
          
          // Write to Excel Report
          String updateResult = PropertiesAndConstants.ExcelReportFile;
         
          ExcelHandler.WriteToSheet(updateResult, 0, 2, 2, PropertiesAndConstants.Application);
          ExcelHandler.WriteLinkToSheet(updateResult, 0, 3, 2, url, url);
          ExcelHandler.WriteToSheet(updateResult, 0, 4, 2, PropertiesAndConstants.TestType);
          ExcelHandler.WriteToSheet(updateResult, 0, 6, 3, PropertiesAndConstants.date);
          ExcelHandler.WriteToSheet(updateResult, 0, 15, 2, PropertiesAndConstants.Browser);
          ExcelHandler.WriteLinkToSheet(updateResult, 0, 16, 2, "Test Log", logFileName);
          ExcelHandler.WriteLinkToSheet(updateResult, 0, 17, 2, "Test Suite", suiteFile);
	 }
	
	
}
