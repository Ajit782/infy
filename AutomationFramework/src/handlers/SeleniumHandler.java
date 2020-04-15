package handlers;
import org.openqa.selenium.firefox.*;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import java.io.*;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import utils.ConfigFunctions;
import utils.LogFunctions;
import utils.PropertiesAndConstants;
import utils.UtilityFunctions;

public class SeleniumHandler
{
	//public static IOSDriver driver;	 
	public static WebDriver driver;
	//Rohit Vyas
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
	/// Description       : Closing Selenium Driver
	/// </summary>
	public static void CloseSeleniumDriver() throws Exception
    {	
		try
        {	
        	//PropertiesAndConstants.Selenium.close();
        	
           PropertiesAndConstants.Selenium.quit();
            //selenium.Dispose();
            //wait(300);
//        	while (isProcessRunning("firefox.exe"))
//    		{
//    			Runtime.getRuntime().exec("taskkill /IM firefox.exe");
//    		}
        	/*while (isProcessRunning("chromedriver.exe")||isProcessRunning("firefox.exe")||isProcessRunning("chrome.exe"))
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
*/            LogFunctions.LogEntry("Close Webdriver Process - Completed",false);
           
        }
        catch (Exception exc) 
        { 
        	System.out.println(exc+"Errorrrr");
        	LogFunctions.LogEntry("driver Close Exception " + exc.getMessage(), false); 
        }
        PropertiesAndConstants.Selenium = null;
        
      }
	 
	/// <summary>
	/// Description       : Switching From one Browser To Other
	/// </summary>
	 public static WebDriver SwitchDriver() throws Exception
	    {  
	        EventFiringWebDriver driverHandler;
	        
	        if (driver == null)
	        { 
	            //Firefox, InternetExplorer, Chrome
	        	System.out.println(ConfigFunctions.getEnvKeyValue("APP_TYPE"));
	            switch(ConfigFunctions.getEnvKeyValue("APP_TYPE"))
	            {
	                case "FIREFOX":
	                    {
	                    	FirefoxProfile profile = null;	                    	
	                        profile = new FirefoxProfile();	                       
	                        profile.setAcceptUntrustedCertificates(true);
	                        profile.setPreference("app.update.auto", false);
	                        profile.setPreference("app.update.enabled", false);
	                        profile.setPreference("app.update.silent", true);
	                        if (ConfigFunctions.getEnvKeyValue("VERIFYJAVASCRIPTERRORS").toUpperCase().equals( "YES"))
	                        {
	                        	File f = new File(PropertiesAndConstants.CurrentDirectory + "/Automation/Drivers/JSErrorCollector.xpi");
	                            if (f.exists())
	                            {
	                            	LogFunctions.LogEntry("Set Up JavaScript Error collector...", false);
	                            	//profile.AddExtension(Properties.AppHomeDrive + "TestAutomation\\TAF\\Drivers\\JSErrorCollector.xpi");
	                            	LogFunctions.LogEntry("Set Up JavaScript Error collector - Completed", false);
	                            }
	                            else LogFunctions.LogEntry("Cannot Find FireFox extension for JavaScript Error collector file", false);
	                        }

	                        try 
	                        { 
	                        	System.setProperty("webdriver.gecko.driver","/Automation/Drivers/geckodriver");
	                        	driver = new FirefoxDriver();
	                        	driver.manage().window().maximize();
	                        }
	                        catch (WebDriverException webDriverExc)
	                        {
	                        	LogFunctions.LogEntry("Exception in process of Start WebDriver.", false);
	                        	LogFunctions.LogEntry("Reason: " + webDriverExc.getMessage(), false);
	                        	LogFunctions.LogEntry("Details: " + webDriverExc, false);
	                            //profilePort++;
	                        	LogFunctions.LogEntry("Retry to Launch Webdriver Browser...",false);
	                            SwitchDriver();
	                        }
	                        
	                        driver.manage().window().maximize();
	                      
	                       // SetDefaultDriverConfig();
	                        driverHandler = new EventFiringWebDriver(driver);
	                        PropertiesAndConstants.Selenium = driver;
	                        return driver;
	                    }
	                case "INTERET EXPLORER":
	                    {	  
	                    	File file = new File(PropertiesAndConstants.CurrentDirectory + "Automation/Drivers/IEDriverServer.exe");
	                    	
	                    	System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
	                    	driver = new InternetExplorerDriver();	                    	                     
	                        driver.manage().window().maximize();
	                        driver.manage().deleteAllCookies();
	                        SetDefaultDriverConfig();
	                        driverHandler = new EventFiringWebDriver(driver);
	        
	                        PropertiesAndConstants.Selenium = driver;
	                        return driver;
	                    }
	                case "CHROME":
                    {
                    	File file = new File(PropertiesAndConstants.CurrentDirectory + "/Automation/Drivers/chromedriver");
                    	System.setProperty("webdriver.chrome.driver",file.getAbsolutePath());
            	        ChromeOptions options = new ChromeOptions();
            	        options.addArguments("--test-type");
            	        WebDriver driver = new ChromeDriver(options);                                       
                        driver.manage().window().maximize(); 
                        driver.get("http://www.google.com/");                        
                        driverHandler = new EventFiringWebDriver(driver);                        
                        PropertiesAndConstants.Selenium = driver;
                        return driver;
                    }
	                case "PrivateFarm_Android":
	                {
	                	System.out.println("Kiosk2_Device private..");	    
	                    //API.getPrivateDevicesAndroid();
	                	DesiredCapabilities capabilities = new DesiredCapabilities();
	                	capabilities.setCapability("Capability_Username","ujshitol@in.ibm.com");
	                	capabilities.setCapability("Capability_ApiKey","br5zwvfk2xp6xv75dwc3wmxd");
	                	capabilities.setCapability("Capability_ApplicationName", "BAUR67_BASEBUILD_2012019_1PM.apk");
	                	capabilities.setCapability("Capability_DurationInMinutes", 60);
	                	capabilities.setCapability("Capability_DeviceFullName","Huawei_Honor8X_Android_9.0.0");
	                	capabilities.setCapability("autoGrantPermissions",true);
	                	capabilities.setCapability("autoAcceptAlerts",true);	                		                		                		                		                	                		                		                		                	
            	        capabilities.setCapability("browserName", "");
            	        capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
            	        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 90);
            	        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"UiAutomator2");
            	        capabilities.setCapability("appWaitActivity", "com.amazon.mShop.home.web.MShopWebGatewayActivity");
            	        capabilities.setCapability("appWaitDuration", "15000");
            	        System.out.println("In Device Farm Capabilities");
            		    System.out.println("Please wait:- Connecting to Mobile...");     
            		    PropertiesAndConstants.Appium = new AndroidDriver<MobileElement>(new URL("https://qkm1vil.qualitykiosk.com/appiumcloud/wd/hub"),
        	                	capabilities);
            		    System.out.println("Driver initialised on the desired device");  
            		    //PropertiesAndConstants.Appium = new AppiumDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
                        PropertiesAndConstants.Selenium = (WebDriver)PropertiesAndConstants.Appium;	                       
                        Thread.sleep(9000);	                        
                         return driver;
	                }//End of case
	                case "MOBILE ANDROID NATIVE":
                    {	           
                    	System.out.println("inside android native...");
                    	DesiredCapabilities capabilities = new DesiredCapabilities();
            			capabilities.setCapability(CapabilityType.PLATFORM_NAME,"Android");
            			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"b28e6aea");
            			//capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"emulator-5554");
            			capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,"9");
            			capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"UiAutomator2");
            			capabilities.setCapability(MobileCapabilityType.APP, "C:\\Assingment\\Drivers\\Amazon_shopping.apk");
            			capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "520");
            	        //capabilities.setCapability("browserName", "");
            	        //capabilities.setCapability("noReset", "true");
            			capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
            	        capabilities.setCapability(MobileCapabilityType.NO_RESET, "true");
            	        //capabilities.setCapability("deviceReadyTimeout", "300");
            	        capabilities.setCapability("appWaitActivity", "com.amazon.mShop.home.web.MShopWebGatewayActivity");
            	        capabilities.setCapability("appWaitDuration", "15000");	            	        
            		    System.out.println("Please wait:- Connecting to Mobile...");
            		    PropertiesAndConstants.Appium = new AppiumDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
                        PropertiesAndConstants.Selenium = PropertiesAndConstants.Appium;	                                                                     
                         return driver;
                    }	           
	                case "SAFARI":
	                    {
	                        // ***** To be define
	                        //return new SafariDriver();
	                        driverHandler = new EventFiringWebDriver(driver);
	                       // if (ConfigFunctions.getEnvKeyValue("STARTFROMHOMEPAGE").toUpperCase() == "YES") driverAction.navigateToHomePage();
	                        return null;
	                    }
	                case "MOBILE ANDROID WEB":
	                	{
		                	DesiredCapabilities capabilities = new DesiredCapabilities();
		        			capabilities.setCapability(MobileCapabilityType.BROWSER_NAME,"Chrome");
		                	capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,"5.0.2");
		                	capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
		                	capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"Android");
		                	capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"Appium");
	                        System.out.println("Connecting to Mobile");
		        			AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);                      
	                        driverHandler = new EventFiringWebDriver(driver);                        
	                        PropertiesAndConstants.Selenium = driver;
	                        return driver;
	                	}
	                case "MOBILE IOS WEB":
	                {
	                	//IOSDriver driver;
	                	 DesiredCapabilities capabilities = new DesiredCapabilities();
		                    capabilities.setCapability("platformName", "iOS");
		                    capabilities.setCapability("deviceName", "iOS");
		                    capabilities.setCapability("platformVersion", "8.3"); 	
		                    capabilities.setCapability("browser", "safari");
		                    capabilities.setCapability("udid", "4a1eed0fcc7a6ecd94ef9b97d341a732c7c9aea7");
		                    capabilities.setCapability("autoAcceptAlerts",true);
		                    capabilities.setCapability("autoWebview", true);
		                    capabilities.setCapability("safariAllowPopups", true);
		                    capabilities.setCapability("autoDismissAlerts", true);
		                    capabilities.setCapability("showIOSLog", true);
		                    System.out.println("Connecting.....");
		                    PropertiesAndConstants.Selenium  = new IOSDriver(new URL("http://192.168.157.60:4723/wd/hub"), capabilities);
		                    System.out.println("Appium Setup successfully ");
		                   Thread.sleep(8000);
	                }//End of case	
	                case "MOBILE IOS NATIVE":
	                {
	                	//IOSDriver driver;
	                    DesiredCapabilities capabilities = new DesiredCapabilities();
	                    capabilities.setCapability("platformName", "iOS");
	                    capabilities.setCapability("deviceName", "iOS");
	                    capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"Appium");
	                    System.out.println("Connecting.....");
	                    IOSDriver driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	                    System.out.println("god is great");
	                    Thread.sleep(2000);
	                    PropertiesAndConstants.Selenium = driver;
	                    return driver;
	                }//End of case
	                	
	            }
	        }
	        else
	        {
	      
	            PropertiesAndConstants.MainDriverWindowHandle = driver.getWindowHandle();
	            return driver;
	        }
			return driver;
	    }
	
	 public static void SetDefaultDriverConfig() throws IOException
	    {
	        LogFunctions.LogEntry("Set Default Config...", false);
	        String pageLoadTimeout = null;
	        String javaScriptExecuteTimeout;
	        String elementWaitTimeout;
	        String failStepsCountForStopScript;
	        try
	        {
	            pageLoadTimeout=(ConfigFunctions.getEnvKeyValue("PAGELOADTIMEOUT"));
	            if (!pageLoadTimeout.equals(null)&&!pageLoadTimeout.equals(""))
	            {
	               // driver.manage().timeouts().SetPageLoadTimeout(TimeSpan.FromSeconds(pageLoadTimeout));
	            	driver.manage().timeouts().implicitlyWait(Integer.parseInt(pageLoadTimeout), TimeUnit.MILLISECONDS);
	            }
	            else driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	        }
	        catch (WebDriverException wdsetexc)
	        {
	            LogFunctions.LogEntry("Cannot set PageLoadTimeout", false);
	            LogFunctions.LogEntry("Reason: " + wdsetexc.getMessage(),false);
	        }
	        try
	        {
	        	javaScriptExecuteTimeout=ConfigFunctions.getEnvKeyValue("JAVASCRIPTTIMEOUT");
	        	if(!javaScriptExecuteTimeout.equals(null)&&!javaScriptExecuteTimeout.equals(""))
	            	driver.manage().timeouts().implicitlyWait(Integer.parseInt(javaScriptExecuteTimeout), TimeUnit.MILLISECONDS);
	            else  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	        }
	        catch (WebDriverException wdsetexc)
	        {
	            LogFunctions.LogEntry("Cannot set ScriptTimeout", false);
	            LogFunctions.LogEntry("Reason: " + wdsetexc.getMessage(), false);
	        }

	        try
	        {
	        	elementWaitTimeout=ConfigFunctions.getEnvKeyValue("ELEMENTWAITTIMEOUT");
	        	if(!elementWaitTimeout.equals(null)&&!elementWaitTimeout.equals(""))
	            	driver.manage().timeouts().implicitlyWait(Integer.parseInt(elementWaitTimeout), TimeUnit.MILLISECONDS);
	            else driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
	        }
	        catch (WebDriverException wdsetexc)
	        {
	            LogFunctions.LogEntry("Wait for Element Timeout", false);
	            LogFunctions.LogEntry("Reason: " + wdsetexc.getMessage(), false);
	        }

	        try
	        {
	        	failStepsCountForStopScript=ConfigFunctions.getEnvKeyValue("STOPIFFAILSTEPSCOUNT");
	        	if(!failStepsCountForStopScript.equals(null)&&!failStepsCountForStopScript.equals(""))
	            {
	                PropertiesAndConstants.FailStepsCountLimit = Integer.parseInt(failStepsCountForStopScript);
	                LogFunctions.LogEntry("Stop Script when Fail steps Count limit will be" + failStepsCountForStopScript, false);
	            }
	            else
	            {
	                PropertiesAndConstants.FailStepsCountLimit = Integer.MAX_VALUE;
	                LogFunctions.LogEntry("Stop Script when Fail steps Count limit - Disabled", false);
	            }
	        }
	        catch (Exception e)
	        {
	            LogFunctions.LogEntry("Cannot Set Stop Script when Fail steps Count limit will be achieved parameter", false);
	            LogFunctions.LogEntry("Reason: " + e.getMessage(), false);
	        }
	     
	        PropertiesAndConstants.MainDriverWindowHandle = driver.getWindowHandle();
	        LogFunctions.LogEntry("Set Default Config - Completed", false);
	    }
}//End of class Selenium Handler

