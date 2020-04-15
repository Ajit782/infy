package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.sun.org.apache.regexp.internal.recompile;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableHyperlink;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class UtilityFunctions {
	
	/// <summary>
	/// Author            :  Thakur Ajit
	/// Description       : Checking Weather a Process is Running Or Not
	/// </summary>
	
	public static boolean isProcessRunning(String serviceName) throws Exception 
	{
		Process p = Runtime.getRuntime().exec(System.getenv("windir") + "\\system32\\" + "tasklist.exe");
		BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line;
		while ((line = reader.readLine()) != null) {
			if (line.contains(serviceName)) {
				return true;
			}
		}    
		return false;
	}
	/// <summary>
	/// Author            :  Thakur Ajit
	/// Description       : Setting Default Values to Current Directory and Environment File Path
	/// </summary>
	public static void SetDefaultValues()
	{
		PropertiesAndConstants.CurrentDirectory= System.getProperty("user.dir").substring(0, 33);
		//PropertiesAndConstants.CurrentDirectory= System.getProperty("user.dir");
		System.out.println("PropertiesAndConstants.CurrentDirectory"+PropertiesAndConstants.CurrentDirectory);
		PropertiesAndConstants.EvnFilePath = PropertiesAndConstants.CurrentDirectory+"/Config/Env.xml";
		
		System.out.println("PropertiesAndConstants.EvnFilePath"+PropertiesAndConstants.EvnFilePath);
	}
	
	
	/// <summary>
	/// Author            :  Thakur Ajit
	/// Description       : Checking Weather a String is Null or Not
	/// </summary>
	
	public static boolean IsNullOrEmpty(String StringTocheck)
	{
		if (StringTocheck == null||StringTocheck.trim().length() == 0)
			return true;
		else
			return false;
	}

	
	/// <summary>
	/// Author            :  Thakur Ajit
	/// Description       : Checking Weather a String is Null or Having White Spaces
	/// </summary>
	public static boolean IsNullOrWhiteSpace(String StringTocheck)
	{
		
		return IsNullOrEmpty(StringTocheck);
	}
	
	
	/// <summary>
	/// Author            :  Thakur Ajit
	/// Description       : Checking Weather a File is Open or Not
	/// </summary>
	public static boolean isFileOpened(File file) throws IOException

	{
			boolean res = false;
			
			FileChannel channel = new RandomAccessFile(file, "rw").getChannel();
			FileLock lock = channel.lock();
			
			try {
			      //The file is not already opened 
			      lock = channel.tryLock();
			   } 
			 catch (Exception e)
		        {   
		            e.printStackTrace();   
		            res = true;
		            
		        }  

			return res;
		
	}
	
	
	/// <summary>
	/// Author            :  Thakur Ajit
	/// Description       : Closing Firefox Browser
	/// </summary>
	
	public static void CloseFireFoxBrowsers() throws IOException, Exception
	{
		while (isProcessRunning("chromedriver.exe")||isProcessRunning("firefox.exe"))
			//while (isProcessRunning("chromedriver.exe")||isProcessRunning("chrome.exe"))
			{
				if(isProcessRunning("chromedriver.exe")==true)
				{
					System.out.println("Killing----- Chrome driver");
					Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
				}
				if(isProcessRunning("firefox.exe")==true)
				{
					System.out.println("Killing----- mozila");
					Runtime.getRuntime().exec("taskkill /IM firefox.exe");
				}
				if(isProcessRunning("chrome.exe")==true)
				{
					System.out.println("Killing----- Chrome");
					Runtime.getRuntime().exec("taskkill /IM chrome.exe");
				}
			}//End of while Loop

	}
	/// <summary>
	/// Author            :  Thakur Ajit
	/// Description       : Executing the Script
	/// </summary>
	
	public static void ExecuteScript(String Argument) throws ScriptException
	{
		ScriptEngineManager factory = new ScriptEngineManager();
		ScriptEngine engine = factory.getEngineByName("JavaScript");
		engine.eval(Argument);
	}

	public static void CaptureExecutionResults(Workbook testScript,Sheet testScriptSheet, int rowID, boolean result, String resultsToExcel) throws IOException, WriteException
	{		        
        WritableWorkbook testscriptcopy = null;
        WritableSheet testscriptsheetcopy = null;
        testscriptcopy=Workbook.createWorkbook(new File("c:\\Assingment\\result"+".xls"),testScript);
		testscriptsheetcopy=testscriptcopy.getSheet(0);
		PropertiesAndConstants.isCreated=true;
        
		testscriptsheetcopy.addCell(new Label(5, rowID, ""));
		testscriptsheetcopy.addCell(new Label(6, rowID, ""));
		testscriptsheetcopy.addCell(new Label(7, rowID, ""));

		if (resultsToExcel.toUpperCase() == "FAIL")
		{
			if (!result)
			{
				/*testScriptSheet.Cells[rowID, 6] = "";
                testScriptSheet.Cells[rowID, 6] = "FAIL";
                testScriptSheet.Cells[rowID, 7] = "";
                testScriptSheet.Cells[rowID, 7] = GlobalClass.ResultString;
                testScriptSheet.Cells[rowID, 8] = "";*/

				testscriptsheetcopy.addCell(new Label(5, rowID, ""));
				testscriptsheetcopy.addCell(new Label(6, rowID, "FAIL"));
				testscriptsheetcopy.addCell(new Label(6, rowID, PropertiesAndConstants.ResultString));
				testscriptsheetcopy.addCell(new Label(7, rowID, ""));

				if (!UtilityFunctions.IsNullOrEmpty(PropertiesAndConstants.TempScreenshotPath))
				{
					File screenshotFile = new File(PropertiesAndConstants.TempScreenshotPath);
				
					//testScriptSheet.Hyperlinks.Add(testScriptSheet.Cells[rowID, 8], GlobalClass.TempScreenshotPath, Type.Missing, Type.Missing, "ScreenShot");
					
					WritableHyperlink ExcelHyperLink = new WritableHyperlink(7, rowID, screenshotFile, "ScreenShot");
					testscriptsheetcopy.addHyperlink(ExcelHyperLink);
				}

				if (!UtilityFunctions.IsNullOrWhiteSpace(PropertiesAndConstants.TempPageSourcePath))
				{
					File screenshotFile = new File(PropertiesAndConstants.TempPageSourcePath);
					WritableHyperlink ExcelHyperLink = new WritableHyperlink(7, rowID, screenshotFile, "PageSource");
					testscriptsheetcopy.addHyperlink(ExcelHyperLink);
					//testScriptSheet.addHyperlink(ExcelHyperLink);
				}
				
			
				PropertiesAndConstants.FailStepsPerScriptCounter++;
			}
			testscriptcopy.write();
		}
		if (!UtilityFunctions.IsNullOrEmpty(resultsToExcel))
		{
			if (!result)
			{
				/*testScriptSheet.Cells[rowID, 6] = "";
				testScriptSheet.Cells[rowID, 6] = "FAIL";
				testScriptSheet.Cells[rowID, 8] = "";*/
				testscriptsheetcopy.addCell(new Label(5,rowID,""));
				testscriptsheetcopy.addCell(new Label(5,rowID,"FAIL"));
				testscriptsheetcopy.addCell(new Label(7,rowID,""));
				if (!UtilityFunctions.IsNullOrEmpty(PropertiesAndConstants.TempScreenshotPath))
				{
					File ScreenshotFile = new File(PropertiesAndConstants.TempScreenshotPath);
					WritableHyperlink ExcelHyperLink = new WritableHyperlink(7, rowID, ScreenshotFile, "ScreenShot");
					testscriptsheetcopy.addHyperlink(ExcelHyperLink);
				}
					
					//testScriptSheet.Hyperlinks.Add(testScriptSheet.Cells[rowID, 8], Properties.TempScreenshotPath, Type.Missing, Type.Missing, "ScreenShot");
				if (!UtilityFunctions.IsNullOrWhiteSpace(PropertiesAndConstants.TempPageSourcePath))
				{
					File ScreenshotFile = new File(PropertiesAndConstants.TempPageSourcePath);
					WritableHyperlink ExcelHyperLink = new WritableHyperlink(7, rowID, ScreenshotFile, "ScreenShot");
					testscriptsheetcopy.addHyperlink(ExcelHyperLink);
				}
					//testScriptSheet.Hyperlinks.Add(testScriptSheet.Cells[rowID, 8], Properties.TempPageSourcePath, Type.Missing, Type.Missing, "PageSource");
		
				PropertiesAndConstants.FailStepsPerScriptCounter++;
			}
			if (result && !PropertiesAndConstants.IsStepSkip)
			{ 
				testscriptsheetcopy.addCell(new Label(5,rowID,"PASS"));
				testscriptcopy.write();
				//testScriptSheet.Cells[rowID, 6] = "PASS";
			}

			if (result && PropertiesAndConstants.IsStepSkip)
			{
				testscriptsheetcopy.addCell(new Label(5,rowID,"SKIP"));
			
				//testScriptSheet.Cells[rowID, 6] = "SKIP";
			}

			/*testScriptSheet.Cells[rowID, 7] = "";
			testScriptSheet.Cells[rowID, 7] = Properties.ResultString;
			testScript.Save();*/
			testscriptsheetcopy.addCell(new Label(7,rowID,""));
		   
		}
		
	    testscriptcopy.close();
		PropertiesAndConstants.ScreenShotFullPath = "";
	}


	public static String getEnvKeyValue(String keyName) {
		try
		{
			String value;
			value = PropertiesAndConstants.envDictionary.get(keyName);
			return value;
		}
		catch (Exception e) {
			System.out.println("Not get keyName");
			return "";
		}
	}
	
	 
	
//	public static void SendResultMail() throws IOException
//	{
//	      String[] to = PropertiesAndConstants.EmailToList.split(";");
//	   
//	      String from = PropertiesAndConstants.EmailFrom;
//
//	      String host = PropertiesAndConstants.EmailHost;
//	      String port = PropertiesAndConstants.EmailPort;
//	      
//	      // Get system properties
//	      Properties properties = System.getProperties();
//	
//	      
//	      properties.put("mail.smtp.auth", true);
//	      properties.put("mail.smtp.starttls.enable", true);
//	      properties.put("mail.smtp.host", host);
//	      properties.put("mail.smtp.port", port);
//	      properties.put("mail.smtp.user", PropertiesAndConstants.EmailUser);
//	      properties.put("mail.smtp.password", PropertiesAndConstants.EmailPassword);
//	     System.out.println("setting done");
//	     try{
//	  // creates a new session with an authenticator
//	        Authenticator auth = new Authenticator() {
//	            public PasswordAuthentication getPasswordAuthentication() {
//	                return new PasswordAuthentication(PropertiesAndConstants.EmailUser, PropertiesAndConstants.EmailPassword);
//	            }
//	        };
//	        Session session = Session.getInstance(properties, auth);
//	 
//	        // creates a new e-mail message
//	        Message msg = new MimeMessage(session);
//	 
//	        msg.setFrom(new InternetAddress(from));
//	        InternetAddress[] toAddressList = new InternetAddress[to.length];
//
//	        // To get the array of addresses
//	        for( int i = 0; i < to.length; i++ ) {
//	        	toAddressList[i] = new InternetAddress(to[i]);
//	        }
//
//	        for( int i = 0; i < toAddressList.length; i++) {
//	        	msg.addRecipient(Message.RecipientType.TO, toAddressList[i]);
//	        }
//	        msg.setSubject("Test Execution Results");
//	         
//	        // creates message part
//	        MimeBodyPart messageBodyPart = new MimeBodyPart();
//	        messageBodyPart.setContent("Please find attached Batch execution summary and Detailed log.", "text/html");
//	 
//	        // creates multi-part
//	        Multipart multipart = new MimeMultipart();
//	        multipart.addBodyPart(messageBodyPart);
//	 
//	        
//	     // Handle attachment 1
//            MimeBodyPart messageBodyPart1 = new MimeBodyPart();
//            messageBodyPart1.attachFile(PropertiesAndConstants.ExcelReportFile);
//
//            // Handle attachment 2
//            MimeBodyPart messageBodyPart2 = new MimeBodyPart();
//            messageBodyPart2.attachFile(PropertiesAndConstants.LogFile);
//	        /*
//	        // adds attachments
//	        if (attachFiles != null && attachFiles.length > 0) {
//	            for (String filePath : attachFiles) {
//	                MimeBodyPart attachPart = new MimeBodyPart();
//	 
//	                try {
//	                    attachPart.attachFile(filePath);
//	                } catch (IOException ex) {
//	                    ex.printStackTrace();
//	                }
//	 
//	                multipart.addBodyPart(attachPart);
//	            }
//	        }
//	        */
//	        multipart.addBodyPart(messageBodyPart1);
//            multipart.addBodyPart(messageBodyPart2);
//	 
//	        // sets the multi-part as e-mail's content
//	        msg.setContent(multipart);
//	 
//	        // sends the e-mail
//	        Transport.send(msg);
///*
//	      // Get the default Session object.
//	     Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
//
//	    	    protected PasswordAuthentication getPasswordAuthentication() {
//	    	      return new PasswordAuthentication(PropertiesAndConstants.EmailUser, PropertiesAndConstants.EmailPassword);
//	    	    }
//	     });
//	        MimeMessage message = new MimeMessage(session);
//
//	        try {
//	            message.setFrom(new InternetAddress(from));
//	            InternetAddress[] toAddress = new InternetAddress[to.length];
//
//	            // To get the array of addresses
//	            for( int i = 0; i < to.length; i++ ) {
//	                toAddress[i] = new InternetAddress(to[i]);
//	            }
//
//	            for( int i = 0; i < toAddress.length; i++) {
//	                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
//	            }
//	            
//	            // Handle attachment 1
//	            MimeBodyPart messageBodyPart1 = new MimeBodyPart();
//	            messageBodyPart1.attachFile(PropertiesAndConstants.ExcelReportFile);
//
//	            // Handle attachment 2
//	            MimeBodyPart messageBodyPart2 = new MimeBodyPart();
//	            messageBodyPart2.attachFile(PropertiesAndConstants.LogFile);
//
//	            MimeMultipart multipart = new MimeMultipart("related");
//
//	            multipart.addBodyPart(messageBodyPart1);
//	            multipart.addBodyPart(messageBodyPart2);
//
//	            message.setContent(multipart);
//	            
//	            message.setSubject("Test Execution Results");
//	            message.setText("Please find attached Batch execution summary and Detailed log.");
//	            
//	            Transport transport = session.getTransport("smtp");
//	            transport.connect(host, from, PropertiesAndConstants.EmailPassword);
//	            transport.sendMessage(message, message.getAllRecipients());
//	            transport.close();
//	            */
//	     
//	
//	      }catch (MessagingException mex) {
//	         mex.printStackTrace();
//	      }
//	   }//End of method
	
	

}
