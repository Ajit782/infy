package utils;

import handlers.ExcelHandler;
import handlers.XMLHandler;

import java.awt.Toolkit;
import java.io.File;
import java.util.ArrayList;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.w3c.dom.Document;

import utils.PropertiesAndConstants;
import utils.UtilityFunctions;

public class ScriptFunctions 
{
	
	/// <summary>
	/// Author            : Thakur
	/// Description       : Adding Common Script Files in Main Script File  
	/// </summary>
	
	
	public static void AddCommonActionsInTempScript(String scriptpath) throws Exception
	{
		System.out.println(scriptpath+"     ------scriptpath");
		//excel.Application excelAppScript = new Excel.Application();
				//excelAppScript.DisplayAlerts = false;
				//excelAppScript.AskToUpdateLinks = false;
				//excelAppScript.Application.EnableEvents = false; // For COMException (0x800A03EC)
				//excelAppscriptpathScript.AutomationSecurity = Microsoft.Office.Core.MsoAutomationSecurity.msoAutomationSecurityForceDisable;
				File scriptFile = new File(scriptpath);
				Workbook excelWorkBook;
				 excelWorkBook= Workbook.getWorkbook(scriptFile);
				String Tempfile=PropertiesAndConstants.TempDirectoryPath+ PropertiesAndConstants.TestScriptName + ".xls";
		        System.out.println(Tempfile+"  ---Tempfile");
				WritableWorkbook Scriptfilecopy=Workbook.createWorkbook(new File(Tempfile),excelWorkBook);
		        WritableSheet testscriptsheet  = Scriptfilecopy.getSheet(0); 
		       
		        
		        testscriptsheet.removeSharedDataValidation(testscriptsheet.getWritableCell(2, 7));
		        testscriptsheet.removeSharedDataValidation(testscriptsheet.getWritableCell(3, 7));
		        
		        
		        //WritableCell a = testscriptsheet.getWritableCell(1, 1);
		        //a.getCellFeatures().removeDataValidation()	
		        
				//.Open(scriptpath, 0, false, 5, "", "", true,"" , "\t", true, false, 0,
				//   true, true, false);
				//excelWorkBook.CheckCompatibility = false;

		      //  Workbook SriptToRead = ExcelHandler.OpenExcelToRead(scriptpath);
		        //jxl.Sheet sheet = SriptToRead.getSheet(0); 
				
		        
		        
				// Add Activate + Select
				int rowCountScript = testscriptsheet.getRows();
				int testscriptstartRow = 0;
				//int rowCountScript = testscriptsheet.getRows();
				do 
					{
					  testscriptstartRow++;
					}
				while (!testscriptsheet.getWritableCell(0, testscriptstartRow).getContents().equals("StepId"));
				testscriptstartRow++;
			
				// Processing TestScript Template Until String(keyaction) is not Empty.
				for (int startRow = testscriptstartRow; startRow <= rowCountScript; startRow++)
				{
					//String keyactions =((jxl.Sheet) sheet).getCell(2, startRow).getContents().toString(); 
					 String keyactions = testscriptsheet.getCell(2, startRow).getContents().toString();
					 //System.out.println(testscriptsheet.getCell(2, startRow).getContents().toString());
					if (keyactions.trim() =="")
					{
							break;
					}
					if ((keyactions.equals(null)||keyactions.equals("")))
					{
						LogFunctions.LogEntry("Stop the process of addiing common steps in row: " + startRow, false);
						LogFunctions.LogEntry("If this row not last please check for blank lines in test scripts.", false);
						break;
					}

					if (keyactions.toLowerCase().contains("disableflash"))
					{
						LogFunctions.LogEntry("- Flash Disabled for this Script", false);
						PropertiesAndConstants.IsFlashDisabled = true; // For Disabling Flash
					}

					String valuepath = testscriptsheet.getWritableCell(4,startRow).getContents().toString();
					
					if(valuepath.toUpperCase().contains("REUSABLE"))
					{  
						if (PropertiesAndConstants.ReusableScriptPath.toUpperCase().contains("REUSABLE"))
						{	
							PropertiesAndConstants.ReusableScriptPath = PropertiesAndConstants.CurrentDirectory+ConfigFunctions.getEnvKeyValue("SCRIPTS");
							
						}
						System.out.println(PropertiesAndConstants.ReusableScriptPath);

						PropertiesAndConstants.finalScriptPath=PropertiesAndConstants.ReusableScriptPath + valuepath;
						PropertiesAndConstants.ReusableScriptPath=PropertiesAndConstants.finalScriptPath;
					}
					
					else
					{
					PropertiesAndConstants.finalScriptPath=PropertiesAndConstants.TempTestScriptPath + valuepath;
					System.out.println(PropertiesAndConstants.finalScriptPath);
					
					}
					File file=new File(PropertiesAndConstants.finalScriptPath);
					//File file=new File(PropertiesAndConstants.TempTestScriptPath + valuepath);
				
					if (keyactions.contains("Import")&&!file.exists())
					{
						
						LogFunctions.LogEntry("Incorrect filename in Import action. Please verify that the Script file " +PropertiesAndConstants.finalScriptPath + " was exist", false);
						//testscriptsheet.addCell(new Label(startRow, 7, "< -- File for import steps not found"));
						testscriptsheet.addCell(new Label(6, startRow, "< -- File for import steps not found"));
						//(testscriptsheet.(startRow,7) .toString()) = "< -- File for import steps not found";
						
					}
		           // File DPfile=new File(Properties.TempDirectoryPath + valuepath) ;
					if (keyactions.contains("Import") && file.exists())
					{
						LogFunctions.LogEntry("Find Import Action in file " + scriptpath + " in row "+startRow, false);
						/*testscriptsheet.getCell(startRow, 2) = "IMPORT Action - See Steps Below...";
						testscriptsheet.getCell(startRow, 2).getContents(). .get_Characters().Font.Bold = true;
						((Excel.Range)testscriptsheet.Cells[startRow, 2]).get_Characters().Font.ColorIndex = 5;*/
						//testscriptsheet.addCell(new Label(startRow,2,"IMPORT Action - See Steps Below..."));
					/*	WritableCell cell;
						Label l = new Label(6, startRow, "  IMPORT Action - See Steps Below...");
						cell = (WritableCell) l;
						testscriptsheet.addCell(cell);*/
					
						 
					    testscriptsheet.addCell(new Label(6, startRow, "  IMPORT Action - See Steps Below..."));
					  
						Workbook commonscriptbook = ExcelHandler.ExcelOpenWorkbook(PropertiesAndConstants.finalScriptPath);
						Sheet commonscriptsheet = (Sheet)commonscriptbook.getSheet("Sheet1");
						// ***** To be define... Replace Rows From File
						int commonscriptstartRow = 0;
						int insertrowcount = 0;
						//int commonscriptrowcount = commonscriptsheet.UsedRange.Rows.Count;
						// ***** Find a Start Row (The first test script step)
						
						do 
						{
							commonscriptstartRow++;
						}
						while (!commonscriptsheet.getCell(0,commonscriptstartRow).getContents().equals("StepId"));
						//while ((String)((Excel.Range)commonscriptsheet.Cells[commonscriptstartRow, 1]).Value2 != "StepId");
						commonscriptstartRow++;
						// *****

						// ***** Find a Count of Insert Row (until Stepid not Empty)
						do insertrowcount++;
						while (!UtilityFunctions.IsNullOrWhiteSpace((String)(commonscriptsheet.getCell(1,(commonscriptstartRow + insertrowcount)).getContents())));
						insertrowcount--;

						LogFunctions.LogEntry("Insert Row count: " + (insertrowcount + 1), false);
						int startRowforpaste = startRow + 1; // For Insert Row After "Import" Action

						// ***** Templorary Start
						try 
						{ 
							Toolkit.getDefaultToolkit().getSystemClipboard().setContents(null, null);
																					
						}
						catch (Exception exc)
						{	
							LogFunctions.LogEntry("Cannot clear clipboard....", false);
							LogFunctions.LogEntry("Reason: " + exc.getMessage(), false);
							LogFunctions.LogEntry("Details: " + exc, false);
						}
						/*org.apache.poi.ss.util.CellRangeAddress ranges=  CellRangeAddress.valueOf("A" + commonscriptstartRow +":"+  "E" + (commonscriptstartRow + insertrowcount)); 
						
						Range r_a;
						r_a = New commonscriptsheet.getm
								commonscriptsheet
						Range r_a = new Range(commonscriptsheet, 1, commonscriptstartRow, 5,(commonscriptstartRow + insertrowcount));
					//	Range sourcerows = commonscriptsheet.get_range("A" + commonscriptstartRow, "E" + (commonscriptstartRow + insertrowcount));
						sourcerows.Select();
						var specCells = sourcerows.SpecialCells(Microsoft.Office.Interop.Excel.XlCellType.xlCellTypeVisible, Type.Missing);
						specCells.Copy(Type.Missing);

						Excel.Range destinrowsinsert = testscriptsheet.get_Range("A" + startRowforpaste, "E" + (startRowforpaste + insertrowcount));
						destinrowsinsert.Insert(Excel.XlInsertShiftDirection.xlShiftDown, Type.Missing);
						Excel.Range destinrowspaste = testscriptsheet.get_Range("A" + startRowforpaste, "E" + (startRowforpaste + insertrowcount));

						try // Wait for COM Exception...System.Runtime.InteropServices.COMException (0x800A03EC)
						{
							destinrowspaste.PasteSpecial(Excel.XlPasteType.xlPasteValues);
						}
						catch (System.Runtime.InteropServices.COMException comexc)
						{
							LogEntry(String.format("Cannot Insert Common Step."),false);
							LogEntry(String.format("Reason: " + comexc.Message), false);
							LogEntry(String.format("Details: " + comexc), false);
							Properties.IsTestExecutionPassed = false;
							Properties.scriptExecutionResult = "Fail";
						}*/
						int columns=testscriptsheet.getColumns();
						int shiftrow=startRowforpaste;
						for(int shiftindex=0;shiftindex<=insertrowcount;shiftindex++)
						{
						  
						  testscriptsheet.insertRow(shiftrow);
						  shiftrow++;
						}
						
					    ArrayList<String> arr=new ArrayList();
					     int count=-1;
					     do
					     {
		                   for(int col = 0;col < columns;col++)
				             {
				                 String RowcellValue = commonscriptsheet.getCell(col, commonscriptstartRow).getContents();
				                 testscriptsheet.addCell(new Label(col, startRowforpaste, RowcellValue.trim()));
				                //  testscriptsheet.getRow(startRowforpaste).getCell(col).setCellValue(RowcellValue);
				                 arr.add(RowcellValue);
				                
				                 
				             }
		                 startRowforpaste++; 
		                 commonscriptstartRow++;
		                 count++;
					     }while(count!=insertrowcount);
					//	rowCountScript = rowCountScript + insertrowcount + 1;
					
						
						try { Toolkit.getDefaultToolkit().getSystemClipboard().setContents(null, null); }
						catch (Exception exc)
						{
							LogFunctions.LogEntry("Cannot clear clipboard....", false);
							LogFunctions.LogEntry("Reason: " + exc.getMessage(), false);
							LogFunctions.LogEntry("Details: " + exc, false);
						}

						commonscriptbook.close();
					/*	System.Runtime.InteropServices.Marshal.ReleaseComObject(commonscriptsheet);
						System.Runtime.InteropServices.Marshal.ReleaseComObject(commonscriptbook);*/
					}
				}
				Scriptfilecopy.write();
				Scriptfilecopy.close();
				excelWorkBook.close();
				/*System.Runtime.InteropServices.Marshal.ReleaseComObject(testscriptsheet);
				System.Runtime.InteropServices.Marshal.ReleaseComObject(excelWorkBook);
				System.Runtime.InteropServices.Marshal.ReleaseComObject(excelAppScript);*/
	}
	public static void validateTestDataExist(String filepath) throws Exception
	{
		System.out.println(filepath+" ---------------------------filepath");
		Workbook workbook = ExcelHandler.OpenExcelToRead(filepath);
		String cellValue = ExcelHandler.ReadExcelCell(workbook, 0, 1, 6);
		if(cellValue.contains(".xml"))
		{
			PropertiesAndConstants.isTestDataFileExist = true;
			Document doc = XMLHandler.OpenXML(cellValue);
			PropertiesAndConstants.testDataDictionary = XMLHandler.populateXMLDictionary(doc);
		}
	}

	}


