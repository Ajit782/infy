package executor;

import javax.swing.JOptionPane;

import utils.ConfigFunctions;
import utils.LogFunctions;
import utils.ORFunctions;
import utils.PropertiesAndConstants;
import utils.SuiteFunctions;
import utils.UtilityFunctions;

public class ExecutionFunctions {
	
	public static void main(String args[]) throws Exception{
	 		try{			
				System.out.println("Execution begins :");
				//ExcelHandler.CloseAllExcelFiles();
				//UtilityFunctions.CloseFireFoxBrowsers();
				System.out.println("closed Open Excel and Firefox browsers");
				UtilityFunctions.SetDefaultValues();
				System.out.println("Default Values Set");
				
				ConfigFunctions.populateEnvDictionary(PropertiesAndConstants.EvnFilePath);
				System.out.println("Env Directory Populated");
				
				ConfigFunctions.SetupEnvValues();
				System.out.println("Environment Values Set1");
				
				System.out.println("PropertiesAndConstants.TempSuitPathsflag==="+PropertiesAndConstants.TempSuitPathsflag);	
				if(PropertiesAndConstants.TempSuitPathsflag == 0)
				{
					System.out.println("inside if ==0");
					for(;PropertiesAndConstants.TempSuitePathsCount != PropertiesAndConstants.TempSuitPathsflag;)
					{	
						System.out.println("inside for ==0");
						if(PropertiesAndConstants.TempSuitPathsflag > 0)
						{
							System.out.println("inside if > 0");
							ConfigFunctions.SetupEnvValues();
							System.out.println("Environment Values Set");
						}
						ORFunctions.PopulateObjRepositoryDictionary(PropertiesAndConstants.Repository);
						
						LogFunctions.LogEntry("Test Execution was started...", false);		
					
						SuiteFunctions.ProcessSuiteFile();
						
						System.out.println(PropertiesAndConstants.TempSuitePathsCount);
						
						System.out.println(PropertiesAndConstants.TempSuitPathsflag);
						
						PropertiesAndConstants.TempSuitPathsflag++;
						
						System.out.println(PropertiesAndConstants.TempSuitPathsflag);
					}//End of for loop
				}//End of if(PropertiesAndConstants.TempSuitPathsflag == 0)
				else
				{
					ConfigFunctions.SetupEnvValues();
					System.out.println("Environment Values Set2");
					ORFunctions.PopulateObjRepositoryDictionary(PropertiesAndConstants.Repository);
					LogFunctions.LogEntry("Test Execution was started...", false);
					SuiteFunctions.ProcessSuiteFile();
				}
				//SeleniumHandler.SetupTest();
			}//End of try block
			catch (Exception startExecutionException) 
			{	
				LogFunctions.LogEntry("Error while starting the batch run.", false);
				LogFunctions.LogEntry("Reason: " + startExecutionException.getMessage(), false);
			}	
		 	finally
		 	{
		 		LogFunctions.LogEntry("***********Execution Completed Successfully**********", false);
		 		System.out.println("\nend of suite execution.");
		            JOptionPane.showMessageDialog(null, "Run Completed", "InfoBox: ",
		                JOptionPane.INFORMATION_MESSAGE);
		 	}
	 	}
}//End of class Execution Function
	 	
