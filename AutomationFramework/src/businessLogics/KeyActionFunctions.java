package businessLogics;

import handlers.SeleniumHandler;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;

import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.PressesKey;

import java.awt.Robot;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Driver;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import jxl.Sheet;
import jxl.Workbook;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.ranges.RangeException;

import utils.ConfigFunctions;
import utils.LogFunctions;
import utils.ORFunctions;
import utils.PropertiesAndConstants;
import utils.UtilityFunctions;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableMap;

public class KeyActionFunctions 
{
	public boolean _result = false;
	Map<String, String> _links = new HashMap<String, String>();
	Map<String, String> _linksCheckResult = new HashMap<String, String>();
	Map<String, String> _dynamicVariables = new HashMap<String, String>();
	
	//String str1;
	String mailOtpText;
public enum KeyAction
{
	DisableFlash,
	NavigateToURL,
	Click,
	ClickSafeIfPresent,
	JavaScriptClick,
	CheckBoxCheck,
	CheckBoxUnCheck,
	DoubleClickElement,
	RightClickElement,
	TypeText,
	TypeTextInRichTextEditor,
	InsertEmptyStringInRichTextEditor,
	SelectElementByText,
	OpenNewTab,
	OpenNewTabOrWindowByJavaScript,
	CloseAdditionalTab,
	CloseMainTab,
	SwitchToMainTab,
	SwitchToAdditionalTab,
	ReturnToPreviousPage,
	RefreshPage,
	AlertAccept,
	ElementMouseOver,
	MoveToElement,
	MoveMouseToElement,
	VerifyIsElementPresentAndDisplayed,
	VerifyIsElementNotDisplayed,
	VerifyIsTextPresent,
	VerifyIsTextNotPresent,
	VerifyIsElementContainsText,
	VerifyIsURLContainsText,
	VerifyIsURLNotContainsText,
	VerifyIsTitleContainsText,
	VerifyIsTitleNotContainsText,
	VerifyIsAlertPresent,
	VerifyIsImageDisplayed,
	VerifyElementAttribute,
	VerifyIsElementNotContainsAttribute,
	VerifyAllLinksOnThePage,
	VerifyAllLinksButtonsInElement,
	VerifyBackgroundImageForElement,
	VerifyAllBackgroundImagesOnThePage,
	AddCookie,
	ClearAllCookies,
	ShowHiddenElement,
	RestartBrowser, 
	SwitchToNewWindow,
	GetRandomValue,
	VerifyIsSameImageDisplayedAsSet,
	GetAttributeValue,
	WindowResize,
	CompareRandomValuesNotEqual,
	HardWait,
	StoreMultipleRandomValue,
	compareMultipleRandomValues,
	ScrollToBottom,
	scrollToTop,
	TypeTextFromConfig,
	KeyboardAction,
	ClearFiled,
	EnterTexts,
	//preriqosite,
	verifyprerequistites,
}//End Of Enum

     /// <summary>
	/// Description       :  To retrive the text from element.
	/// </summary>	
/*
Description       : Restarting Browser with current URL if input URL is Null
*/	
public Boolean KeyboardAction(String name, String locator) throws Exception
{		
	System.out.println(locator+" locatoeemmmdd.....");
//if (locator.equals("")||locator.equals(null))
{
	//WebElement webElement = WaitForElementPresent(name, locator);
	//WebElement webElement = PropertiesAndConstants.Selenium.findElement("By");
	System.out.println("inside function tab");
	//WebElement element = (WebElement) PropertiesAndConstants.Selenium.findElements(By.cssSelector(".mat-primary.ng-star-inserted.mat-select-panel-done-animating"));
	WebElement element = FindElement(".mat-primary.ng-star-inserted.mat-select-panel-done-animating");
	element.sendKeys(Keys.TAB);
	System.out.println(" Tab Done");
	/*AndroidDriver driver = null;
	
	driver.pressKey(new KeyEvent(AndroidKey.BACK));
*/

//	driver.pressKey
//	driver.pressKeyCode(AndroidKeyCode.BACK);
}
return true;
}//End OF Meathod RestartBrowser

/*
	 Description       : Restarting Browser with current URL if input URL is Null
*/	
public Boolean restartBrowser(String inputData) throws Exception
{		
	if (inputData.equals("")||inputData.equals(null))
	{
		String currentURL = PropertiesAndConstants.Selenium.getCurrentUrl();
		SeleniumHandler.CloseSeleniumDriver();						
		NavigateToUrl(currentURL);
		return true;
	}
	else
	{			
		NavigateToUrl(inputData);
		
		return true;
	}
}//End OF Meathod RestartBrowser
		
/*
	 Description       :Used to scroll down 
*/
public boolean scrollToBottom(String name, String locator) throws Exception 
{
	//copy from ==> machine
	 try
	 {
			 //((JavascriptExecutor) PropertiesAndConstants.Selenium).executeScript("window.scrollTo(0, document.body.scrollHeight)");
			 	WebElement webElement = WaitForElementPresent(name, locator);
		        JavascriptExecutor js = (JavascriptExecutor) PropertiesAndConstants.Selenium;
		        HashMap<String, String> scrollObject = new HashMap<String, String>();
		       // scrollObject.put("direction", "down");
		        scrollObject.put("direction", "down");
		      //scrollObject.put("element", ((RemoteWebElement) webElement).getId());
		        js.executeScript("mobile: scroll", scrollObject);
		        LogFunctions.LogEntry("Pass -  to scroll at the bottom", true);
		        PropertiesAndConstants.scriptExecutionResult = "Pass";
	 return true;
	 }
	 catch(Exception e)
	 {
	       LogFunctions.LogEntry("Fail to scroll at the bottom", false);
	       PropertiesAndConstants.scriptExecutionResult = "Fail";
	       return false;
	 }
}//End OF Meathod scrollToBottom	


public boolean scrollToTop(String name, String locator) throws Exception 
{
	//copy from ==> machine
	 try
	 {
		 		System.out.println("before scroll");
		 		//PropertiesAndConstants.Selenium.scrollToExact("Recharge Amount");
		 		//PropertiesAndConstants.Selenium.
		 		////System.out.println("after recharge");
		 	//	Thread.sleep(5000);
			 //((JavascriptExecutor) PropertiesAndConstants.Selenium).executeScript("window.scrollTo(0, document.body.scrollHeight)");
			 	WebElement webElement = WaitForElementPresent(name, locator);
		        JavascriptExecutor js = (JavascriptExecutor) PropertiesAndConstants.Selenium;
		        HashMap<String, String> scrollObject = new HashMap<String, String>();
		       // scrollObject.put("direction", "down");
		        scrollObject.put("direction", "down");
		      //scrollObject.put("element", ((RemoteWebElement) webElement).getId());
		        js.executeScript("mobile: scroll", scrollObject);
		        LogFunctions.LogEntry("Pass -  to scroll at the bottom", true);
		        PropertiesAndConstants.scriptExecutionResult = "Pass";
	 return true;
	 }
	 catch(Exception e)
	 {
		 e.printStackTrace();
	       LogFunctions.LogEntry("Fail to scroll at the bottom", false);
	       PropertiesAndConstants.scriptExecutionResult = "Fail";
	       return false;
	 }
}//End OF Meathod scrollToBottom	
/* 
 	Description       : Checking Weather Flash is Disabled or Not			
*/	
public boolean disableFlash()throws Exception
{
	if (PropertiesAndConstants.IsFlashDisabled)
	{
		LogFunctions.LogEntry("Flash Disabled - Pass", false);
		return true;
	}
	else
	{
		LogFunctions.LogEntry("Cannot Disable Flash - Fail", false);
		return false;
	}
}//End OF Meathod DisableFlash
/*
	 Author            :  Ajit Thakur
	 Description       : Clear browser cookies	
*/	
public boolean clearAllCookies() throws Exception
 {
		try {
			PropertiesAndConstants.Selenium.manage().deleteAllCookies();
			LogFunctions.LogEntry("Clear all Browser Cookies - Pass", false);
			return true;
		} catch (Exception e) {
			LogFunctions.LogEntry("Cannot Clear Browser Cookies - Fail", false);
			return false;
		}
	}//End of method clearAllCookies
	
/* 
  	Author            :  Ajit Thakur
	Description       : add Cookie to browser
*/  
public boolean addCookie(String Cookie) throws Exception
{
	try{
		String [] cookieKeyValue = Cookie.split("="); 
		Cookie cookie = new Cookie(cookieKeyValue[0], cookieKeyValue[1]);
		PropertiesAndConstants.Selenium.manage().addCookie(cookie);
		LogFunctions.LogEntry("Cookies successfully added- Pass", false);
		return true;}
	catch (Exception e) {
		LogFunctions.LogEntry("Cannot add Cookies to Browser - Fail", false);
		return false;}
}//End of method addCookie
/*
   Author            :  Ajit Thakur
   Description       : Navigate to URL which is given as a Parameter
*/
public  Boolean NavigateToUrl(String url) throws Exception
{
	try
	{
	    url = PropertiesAndConstants.envDictionary.get(url); 
	    System.out.println("url       : "+url);
	if (url.contains("http://") || url.contains("https://"))
	{
	   // Should be added try/finally // Wait WebDriver Exception / Lost Connection.....
	    try{
	    	 Thread.sleep(3000);
              PropertiesAndConstants.Selenium.get(url);
              WaitForReadyStateComplete();                                                                                                                                                                           
	    }//End of try block
	    catch (Exception e)
	    {	        
	        LogFunctions.LogEntry("Exception in process of Navigate to URL...", false);
	        //SeleniumHandler.CloseSeleniumDriver();	                                                               
	    }                                    
	    if(!UtilityFunctions.IsNullOrWhiteSpace(PropertiesAndConstants.Selenium.getCurrentUrl()))
	    {
	        LogFunctions.LogEntry("Navigate to URL"+url+"- Pass" , false);
	        return true;
	    }
	    else
	    {
	        LogFunctions.LogEntry("Navigate to URL"+url+" - Fail" , false);
	        LogFunctions.LogEntry("Current browser URL: Actual -"+PropertiesAndConstants.Selenium.getCurrentUrl()+" Expected - "+url, false);
	        if (PropertiesAndConstants.scriptExecutionResult == "Fail")
	        { PropertiesAndConstants.scriptExecutionResult = "Fail"; }
	        else { PropertiesAndConstants.scriptExecutionResult = "Pass"; }
	                return false;
	    }
	}//End of  Url contains IF
	else
	{
		System.out.println("in else of navigateurl");
	    // Should be added try/finally
	    if (!PropertiesAndConstants.Url.endsWith("/") && !url.startsWith("/"))
	    {
	        url = "/" + url;
	    }
	    if (PropertiesAndConstants.Url.endsWith("/") && url.startsWith("/"))
	    {
	        url = url.replaceFirst("/", "");
	    }
	    String urlToNavigate = PropertiesAndConstants.Url + url;
	    // Should be added try/finally // Wait WebDriver Exception / Lost Connection...
	    try
	    {
	        PropertiesAndConstants.Selenium.navigate().to(urlToNavigate);
	        WaitForReadyStateComplete();
	    }
	    catch (WebDriverException e)
	    {
	            // Restart Driver Application
	        LogFunctions.LogEntry("Exception in process of Navigate to URL...", false);
	        //SeleniumHandler.CloseSeleniumDriver();
	        LogFunctions.LogEntry("Restatrt WebDriver Application...", false);
	        //PropertiesAndConstants.SwitchDriver();
	        //LogFunctions.LogEntry("Try to Navigate to URL again...", false);
	        //NavigateToUrl(url);
	    }
	    if (!(PropertiesAndConstants.Selenium.getCurrentUrl().equals(null)||PropertiesAndConstants.Selenium.getCurrentUrl().equals("")))
		{
	        LogFunctions.LogEntry(("Navigate to URL"+urlToNavigate+" - Pass" ), false);
	        return true;
	    }
	    else
	    {
	        LogFunctions.LogEntry("Navigate to URL"+url+" - Fail" ,false);
	        LogFunctions.LogEntry("Current browser URL: Actual -"+PropertiesAndConstants.Selenium.getCurrentUrl()+"; Expected -"+url, false);
	        if (PropertiesAndConstants.scriptExecutionResult == "Fail")
	        { PropertiesAndConstants.scriptExecutionResult = "Fail"; }
	        else { PropertiesAndConstants.scriptExecutionResult = "Pass"; }
	        return false;
	    }
	                                
}//End of Else of contains(http) if 
	}//eND oF FIRST tRY  bLOCK
	catch(Exception e)
	{
		LogFunctions.LogEntry("Navigate to URL"+url+" - Fail" ,false);
        LogFunctions.LogEntry("Current browser URL: Actual -"+PropertiesAndConstants.Selenium.getCurrentUrl()+"; Expected -"+url, false);
        if (PropertiesAndConstants.scriptExecutionResult == "Fail")
        { PropertiesAndConstants.scriptExecutionResult = "Fail"; }
        else { PropertiesAndConstants.scriptExecutionResult = "Pass"; }
        return false;
	}
}//End of Meathod navigate
	/// <summary>
	/// Author            :  Ajit Thakur
	/// Description       :  Click element without Frame 
	/// </summary>
	private boolean Click(String name, String locator) throws Exception
	{
		if (locator.equals(null)||locator.equals(""))
		{
			
			LogFunctions.LogEntry("Click on :"+name+" - Fail - Incorrect Locator :"+locator, false);
			PropertiesAndConstants.scriptExecutionResult = "Fail";
			return false;
		}

		WebElement element = WaitForElementPresent(name, locator);

		if (element != null && element.isDisplayed()||element.isEnabled())
		{
			   //JavascriptExecutor
			   ((JavascriptExecutor)PropertiesAndConstants.Selenium).executeScript("arguments[0].click();", element);
			   System.out.println("click are apply");
			   //element.click();
			   
			   
			   if (ConfigFunctions.getEnvKeyValue("ALERTAUTOACCEPT").toUpperCase() == "YES") ProcessUnexpectedAlert();
			   LogFunctions.LogEntry("Click on :"+name+" - Pass",false);
			   if (PropertiesAndConstants.scriptExecutionResult == "Fail") PropertiesAndConstants.scriptExecutionResult = "Fail";
			   else PropertiesAndConstants.scriptExecutionResult = "Pass";
			   // ***** For Processing UnExpected Alert
			   //SwitchToDefaultContent(frames);
			   return true;
		}
		else
		{
			LogFunctions.LogEntry("Click on :"+name+" - Fail", false);
			return false;
		}
	}

	
/* 
	   Author            :  Ajit Thakur
	   Description       : Click element in Frame
*/
public boolean clickInFrame(String name, String locator, String frames) throws Exception
{
	try{
	if (locator.equals(null) || locator.equals("")){
		LogFunctions.LogEntry("Click on :"+name+" - Fail - Incorrect Locator :"+locator, false);
		PropertiesAndConstants.scriptExecutionResult = "Fail";
		return false;}

	if (!(frames.equals("")||frames.equals(null))){
		 SwitchToFrames(frames);}
	WebElement element = WaitForElementPresent(name, locator);
	if (!element.equals(null) && element.isDisplayed())
	{		
		element.click();
		// ***** For Processing UnExpected Alert
		if (ConfigFunctions.getEnvKeyValue("ALERTAUTOACCEPT").toUpperCase() == "YES") ProcessUnexpectedAlert();
		LogFunctions.LogEntry("Click on :"+name+" - Pass",false);
		if (PropertiesAndConstants.scriptExecutionResult == "Fail") 
			PropertiesAndConstants.scriptExecutionResult = "Fail";
		else 
		    PropertiesAndConstants.scriptExecutionResult = "Pass";
		
		//SwitchToDefaultContent(frames);
		return true;
	}//End oF if not null
	else
	{
		LogFunctions.LogEntry("Click on :"+name+" - Fail",false);
		PropertiesAndConstants.scriptExecutionResult = "Fail";
		//SwitchToDefaultContent(frames);
		return false;}//End oF else not null
	}//End OF Try Block
	catch(Exception e)
	{
		LogFunctions.LogEntry("Click on :"+name+" - Fail",false);
		PropertiesAndConstants.scriptExecutionResult = "Fail";
		//SwitchToDefaultContent(frames);
		return false;
	} 
}//End of method clickInFrame

/*
	Author            :  Ajit Thakur
 	Description       :Switch to Frame for Working with elements in this frame
	for working with <frame>elements</frame> OR <iframe>elements</iframe>
*/
@SuppressWarnings("null")
private void SwitchToFrames(String frames) throws Exception
{
	WebDriverWait framewait = new WebDriverWait(PropertiesAndConstants.Selenium, 120000);
	SwitchToDefaultContent(frames);
	String[] framesArray = null;
	if (frames.contains("||"))
	{
		framesArray = frames.split("\\|\\|");
		PropertiesAndConstants.Selenium.switchTo().defaultContent();
		for (int i = 0; i < framesArray.length; i++)
		{
			if (framesArray[i] != null)
			{
				WebElement frameElement = FindElement(framesArray[i]);
				if (frameElement != null){
					LogFunctions.LogEntry("Frame Element with locator {0} FOUND! "+ framesArray[i], false);
	
					PropertiesAndConstants.Selenium.switchTo().frame(frameElement);
					WaitForReadyStateComplete();
					Thread.sleep(1000);}
				else { 
					LogFunctions.LogEntry(String.format("Cannot Find Frame Element with locator {0}! ", framesArray[i]), false); }
			}//End Of If framesArray[i] != null
			else{
				LogFunctions.LogEntry(String.format("Incorrect Frame Element locator {0} ! ", framesArray[i]), false);}//End Of Else framesArray[i] != null
		}//End Of For Loop
	}//End Of if frames.contains("||")
	else{
		WebElement frameElement = FindElement(frames);
		if (frameElement != null){
			LogFunctions.LogEntry("Frame Element with locator {0} FOUND! "+ frames, false);
			SwitchToDefaultContent();
			//PropertiesAndConstants.Selenium.switchTo().defaultContent();
			//PropertiesAndConstants.Selenium.switchTo().defaultContent();
			PropertiesAndConstants.Selenium.switchTo().frame(frameElement);
			WaitForReadyStateComplete();
			Thread.sleep(1000);}//End OF If frameElement != null
		else { 
			LogFunctions.LogEntry(String.format("Cannot Find Frame Element with locator {0}! ", frames), false); }	//End OF Else frameElement != null		
	}//End Of Else frames.contains("||")
}//End Of Method SwitchToFrames
	
/* 
  	Author            :  Ajit Thakur
 	Description       : Meathod to resize the window according to provided cordinates
*/				 		
private boolean windowResize(String Coordinates) throws Exception
{
	String[] aCoordinates = null;		
	if (Coordinates == ""){
		LogFunctions.LogEntry("No coordinate passed", false);
		return false;}
	aCoordinates = Coordinates.split(":");	
	int xCoordinates =Integer.parseInt(aCoordinates[0]);
	int yCoordinates =Integer.parseInt(aCoordinates[1]);	
	try 
	{			
		Dimension dCoordinates = new Dimension(xCoordinates, yCoordinates);
		PropertiesAndConstants.Selenium.manage().window().setSize(dCoordinates);
		PropertiesAndConstants.Selenium.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
	}
	catch (NoSuchElementException e) 
	{
			System.out.println("Exception is...... "+e);
	}
	catch (InvalidElementStateException e) 
	{
		System.out.println("Exception is...... "+e);
	} 		
	return true;
}//End of meathod windowResize
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++	
	/// <summary>
	/// Author            :  Ajit Thakur
	/// Description       : Switch WebDriver in a Top Content of Document without Frame
	/// </summary>
	private void SwitchToDefaultContent() throws Exception
	{
		try
		{
			Thread.sleep(250); // Templorary Workaround for No Responce Exception
			PropertiesAndConstants.Selenium.switchTo().defaultContent();
			Thread.sleep(250); // Templorary Workaround for No Responce Exception
		}
		catch(Exception exc)
		{
			LogFunctions.LogEntry("Cannot Switch to Default content - Fail", false);
			LogFunctions.LogEntry("Reason: " + exc.getMessage(),false);
			LogFunctions.LogEntry("Details: " + exc,false);
		}
	}
	/// <summary>
	/// Author            :  Ajit Thakur
	/// Description       : Switch WebDriver in a Top Content of Document with Frame
	/// </summary>
	/// </summary>
	private void SwitchToDefaultContent(String frames) throws Exception
	{
		// if (isAlertPresent()) return;
		if (!(frames.equals("")||frames.equals(null)))
		{
			try
			{
				Thread.sleep(500); // Temporary Workaround for No Responce Exception
				PropertiesAndConstants.Selenium.switchTo().defaultContent();
				Thread.sleep(500); // Temporary Workaround for No Responce Exception
			}
			catch (Exception frameExc)
			{
				LogFunctions.LogEntry("Cannot Switch to Default content - Fail", false);
				LogFunctions.LogEntry("Details: " + frameExc,false);
			}
		}
	}
	/// <summary>
	/// Author            :  Ajit Thakur
	/// Description       : Method Wait until element presence in DOM 
	/// </summary>
		
	WebElement WaitForElementPresent(String name, String locator) throws IOException, Exception
	{
		if (locator.equals("")||locator.equals(null))
		{	
			LogFunctions.LogEntry("Incorrect Locator :" + locator, false);
			PropertiesAndConstants.scriptExecutionResult = "Fail";
			return null;
		}
		try
		{
			WebDriverWait waitAppear = new WebDriverWait(PropertiesAndConstants.Selenium,45);
			WebElement element = FindElement(locator);
			   if ((element.isDisplayed()||element.isEnabled()))
			   {
				   return element;
			   
			   }
			   element = waitAppear.until(ExpectedConditions.visibilityOf(FindElement(locator)));
			   // element = waitAppear.until(ExpectedConditions.visibilityOf(FindElement(locator)));
			  
			  
			   return element;
			  }
			  catch (Exception e)
			  { 
				e.printStackTrace();
			    LogFunctions.LogEntry("Wait for element"+name+" with locator"+locator+" present (TimeOut) - Fail", false);
			    return null;
			  }
	}
	/// <summary>
	/// Author            :  Ajit Thakur
	/// Description       : Web Driver Find element in the Top Document
	/// </summary>
	private WebElement FindElement(String locator) throws Exception
	{
		//WebElement element = null;
		WebElement element;

		if (locator.equals("")||locator.equals(null))
		{
			LogFunctions.LogEntry("Incorrect Locator :"+ locator, false);
			return null;
		}

		else if ((locator.contains("#") || locator.contains(".") || locator.contains(">") || locator.contains("~") ||
				locator.contains("+") || locator.contains("*") || locator.contains("$") ||
				locator.contains("^") || locator.contains(" ") || locator.contains("[")) && (!locator.contains("/") && !locator.contains("@")))
		{
			try // by CSS
			{
				PropertiesAndConstants.Selenium.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
				element = PropertiesAndConstants.Selenium.findElement(By.cssSelector(locator));
				PropertiesAndConstants.Selenium.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
				return element;
			}
			catch (NoSuchElementException e) {}//{ LogFunctions.LogEntry("FindByCSS - Fail", false); } //{ LogFunctions.LogEntry("Find Element By CSS - Fail. Reason: " + noElementExc , false); }
			//finally (IllegalLocatorException e) { } // { LogFunctions.LogEntry("Find Element By CSS - Fail. Reason: " + illegalLocatorExc, false); }
			catch (InvalidElementStateException e) { } // { LogFunctions.LogEntry("Find Element By CSS - Fail. Reason: " + invalidElementStateExc, false); }
		}

		else if ((locator.contains("//")||locator.contains("/") || locator.contains("contains") || locator.contains("text()") || locator.contains("@") || locator.contains("*") || locator.contains("////") ||
				locator.contains("////") || locator.contains("[") || locator.contains("]")) && (!locator.contains("#") && !locator.contains(">")))
		{
			try // by XPATH
			{
				PropertiesAndConstants.Selenium.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
				element = PropertiesAndConstants.Selenium.findElement(By.xpath(locator));
				PropertiesAndConstants.Selenium.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
				return element;
			}
			catch (NoSuchElementException e)
			{
				e.printStackTrace();
			}// { LogFunctions.LogEntry("FindByXPATH - Fail", false); }//{ LogFunctions.LogEntry("Find Element By XPATH - Fail. Reason: " + noElementExc, false); }
			//finally (IllegalLocatorException) { } //{ LogFunctions.LogEntry("Find Element By XPATH - Fail. Reason: " + illegalLocatorExc, false); }
			//finally (InvalidSelectorException) { } //{ LogFunctions.LogEntry("Find Element By XPATH - Fail. Reason: " + invalidSelectorExc, false); }
			//finally (InvalidElementStateException) { } //{ LogFunctions.LogEntry("Find Element By XPATH - Fail. Reason: " + invalidElementStateExc, false); }
		}

		else if (!locator.contains("#") && !locator.contains(".") && !locator.contains(">") && !locator.contains("(") &&
				!locator.contains(")") && !locator.contains("/") && !locator.contains("@") && !locator.contains("*") &&
				!locator.contains("$") && !locator.contains("^") && !locator.contains(" ") && !locator.contains("+"))
		{
			
			try // by Id
			{
				PropertiesAndConstants.Selenium.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
				return element = PropertiesAndConstants.Selenium.findElement(By.id(locator));
			}
			catch (Exception e){}{ LogFunctions.LogEntry("FindById - Fail", false); }
			//finally (IllegalLocatorException) { }
			//finally (InvalidSelectorException) { }
			//finally (InvalidElementStateException) { }

			try // by ClassName
			{
				PropertiesAndConstants.Selenium.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
				element = PropertiesAndConstants.Selenium.findElement(By.className(locator));
				return element;
			}
			catch (Exception e){}
			{ LogFunctions.LogEntry("FindByClassName - Fail", false); }
			// finally (IllegalLocatorException) { }
			//finally (InvalidSelectorException) { }
			//finally (InvalidElementStateException) { }
		}

		else if (!locator.contains("#") && !locator.contains(">") && !locator.contains(".") && !locator.contains("(") &&
				!locator.contains(")") && !locator.contains("/") && !locator.contains("@") && !locator.contains("*") &&
				!locator.contains("$") && !locator.contains("^") || locator.contains(" ") )
		{
			try // by LinkText
			{
				PropertiesAndConstants.Selenium.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
				element = PropertiesAndConstants.Selenium.findElement(By.linkText(locator));
				//PropertiesAndConstants.Selenium.manage().timeouts().implicitlyWait(120000,TimeUnit.SECONDS);
				return element;
			}
			catch (NoSuchElementException e) {}//{ LogFunctions.LogEntry("FindByLinkText - Fail", false); }
			// finally (IllegalLocatorException) { }
			// finally (InvalidSelectorException) { }
			//  finally (InvalidElementStateException) { }
		}
		// ***** Need to Add Fail code for Test Result
		return null;
}


	/// <summary>
	/// Author            :  Ajit Thakur
	/// Description       : Wait Until browser property readyState = 4 (complete)
	/// </summary>
	
	private void WaitForReadyStateComplete() throws Exception
	{
		try
		{
			try
			{
				String readystateproperty ="";
				//String readystateproperty = ((JavaScriptExecutor)PropertiesAndConstants.Selenium).executeAsyncScript("return document.readyState").toString();
				if (readystateproperty == "complete")
					return;
				else
					return;
			}
			catch (Exception exc)
			{
				LogFunctions.LogEntry("Fail for wait ReadyState property to complete - Fail ", false);
				LogFunctions.LogEntry("Reason: " + exc, false);
				return;
			}       
		}
		catch (Exception e)
		{
			LogFunctions.LogEntry("Wait ReadyState property to complete (Timeout) - Fail ", false);
			//   LogFunctions.TakeScreenshoot("ReadyStateProperty_Fail"); // Templorary
		}
	}

	
	/// <summary>
	/// Author            :  Ajit Thakur
	/// Description       : Wait Until browser property readyState = 4 (complete)
	/// </summary>
	
	private void ProcessUnexpectedAlert()throws Exception

	{
		try
		{
			String alerttext = PropertiesAndConstants.Selenium.switchTo().alert().getText();
			try { //   LogFunctions.TakeScreenshoot("UnexpectedAlert");
			}
			catch(Exception e) { }
			PropertiesAndConstants.Selenium.switchTo().alert().accept();
			LogFunctions.LogEntry("Accept Alert", false);
			LogFunctions.LogEntry("Alert text: " + alerttext, false);
		}
		catch(Exception e)
		{
			LogFunctions.LogEntry("Alert not Present", false);
		}
	}	
	/// <summary>
	/// Author            :  Ajit Thakur
	/// Description       : 
	/// </summary>
	
	public boolean JavaScriptClick(String name, String locator, String frames) throws Exception
	{
		if (UtilityFunctions.IsNullOrWhiteSpace(locator))
		{
			LogFunctions.LogEntry("Click on :"+name+" - Fail - Incorrect Locator :"+ locator,false);
			PropertiesAndConstants.scriptExecutionResult = "Fail";
			return false;
		}

		if (!UtilityFunctions.IsNullOrWhiteSpace(frames)) SwitchToFrames(frames);

		WebElement element = WaitForElementPresent(name, locator);

		if (element != null)
		{
			//  ((IJavaScriptExecutor)PropertiesAndConstants.GetDriver).ExecuteScript("arguments[0].click();", element);
			if (ConfigFunctions.getEnvKeyValue("ALERTAUTOACCEPT").toUpperCase()== "YES") ProcessUnexpectedAlert();
			LogFunctions.LogEntry("JavaScript Click on :"+name+" - Pass",false);
			if (PropertiesAndConstants.scriptExecutionResult == "Fail") PropertiesAndConstants.scriptExecutionResult = "Fail";
			else PropertiesAndConstants.scriptExecutionResult = "Pass";
			// ***** For Processing UnExpected Alert
			SwitchToDefaultContent(frames);
			return true;
		}
		else
		{
			LogFunctions.LogEntry("JavaScript Click on :"+name+" - Fail",false);
			PropertiesAndConstants.scriptExecutionResult = "Fail";
			SwitchToDefaultContent(frames);
			return false;
		}
	}
	/// <summary>
	/// Author            :  Ajit Thakur
	/// Description       : Check Weather a Checkbox is Selected or not without Frame
	/// </summary>
	
	private boolean CheckBoxCheck(String name, String locator) throws Exception
	{
		if (UtilityFunctions.IsNullOrWhiteSpace(locator))
		{
			LogFunctions.LogEntry("CheckBox Check"+ name+" - Fail - Incorrect Locator :"+locator,false);
			PropertiesAndConstants.scriptExecutionResult = "Fail";
			return false;
		}

		WebElement element = WaitForElementPresent(name, locator);

		if (element != null && element.isDisplayed())
		{
			if (!element.isSelected())
			{
				element.click();
				LogFunctions.LogEntry("CheckBox Check :"+name+" - Pass" ,false);
			}
			else
			{
				LogFunctions.LogEntry("CheckBox was Checked yet :"+name+"- Pass" ,false);
			}
			return true;
		}
		else
		{
			LogFunctions.LogEntry("CheckBox Check :"+ name+" - Fail",false);
			return false;
		}
	}
	/// <summary>
	/// Author            :  Ajit Thakur
	/// Description       :  Check Weather a Checkbox is check or not with Frame
	/// </summary>
	
	public boolean CheckBoxCheck(String name, String locator, String frames) throws Exception
	{
		if (UtilityFunctions.IsNullOrWhiteSpace(locator))
		{
			LogFunctions.LogEntry("CheckBox Check"+name+" - Fail - Incorrect Locator :"+ locator,false);
			PropertiesAndConstants.scriptExecutionResult = "Fail";
			return false;
		}

		if (!UtilityFunctions.IsNullOrWhiteSpace(frames)) SwitchToFrames(frames);
		WebElement element = WaitForElementPresent(name, locator);

		if (element != null && element.isDisplayed())
		{
			if (!element.isSelected())
			{
				element.click();
				LogFunctions.LogEntry("CheckBox Check :"+name+" - Pass" ,false);
				if (PropertiesAndConstants.scriptExecutionResult == "Fail") PropertiesAndConstants.scriptExecutionResult = "Fail";
				else PropertiesAndConstants.scriptExecutionResult = "Pass";
			}
			else
			{
				LogFunctions.LogEntry("CheckBox was Checked yet :"+name+" - Pass" ,false);
				if (PropertiesAndConstants.scriptExecutionResult == "Fail") PropertiesAndConstants.scriptExecutionResult = "Fail";
				else PropertiesAndConstants.scriptExecutionResult = "Pass";
			}
			SwitchToDefaultContent(frames);
			return true;
		}
		else
		{
			LogFunctions.LogEntry("CheckBox Check :"+name+"- Fail" ,false);
			PropertiesAndConstants.scriptExecutionResult = "Fail";
			SwitchToDefaultContent(frames);
			return false;
		}
	}
	/// <summary>
	/// Author            :  Ajit Thakur
	/// Description       : Verify that page source contains text
	/// </summary>
	
	public boolean VerifyIsTextPresent(String inputData) throws Exception
	{
		if (UtilityFunctions.IsNullOrWhiteSpace(inputData))
		{
			LogFunctions.LogEntry("Incorrect Input Data :"+inputData,false);
			PropertiesAndConstants.scriptExecutionResult = "Fail";
			return false;
		}

		if (PropertiesAndConstants.Selenium.getPageSource().contains(inputData))
		{
			LogFunctions.LogEntry("Text :"+inputData+" : Present : - Pass" ,false);
			if (PropertiesAndConstants.scriptExecutionResult == "Fail")
			{ PropertiesAndConstants.scriptExecutionResult = "Fail"; }
			else PropertiesAndConstants.scriptExecutionResult = "Pass";
			return true;
		}
		else
		{
			LogFunctions.LogEntry("Text :"+inputData+" : NOT Present  - Fail" ,false);
			PropertiesAndConstants.scriptExecutionResult = "Fail";
			return false;
		}
	}
	/// <summary>
	/// Author            :  Ajit Thakur
	/// Description       : Click Link / Button if present without fail
	/// </summary>
	
		public boolean ClickSafeIfPresent(String name, String locator, String frames) throws Exception
	{
		if (UtilityFunctions.IsNullOrWhiteSpace(locator))
		{
			LogFunctions.LogEntry("Click on :"+name+" - Fail - Incorrect Locator :"+ locator,false);
			PropertiesAndConstants.scriptExecutionResult = "Fail";
			return false;
		}

		if (!UtilityFunctions.IsNullOrWhiteSpace(frames)) SwitchToFrames(frames);
		WebElement element = WaitForElementPresent(name, locator);

		if (element != null && element.isDisplayed())
		{
			element.click();
			if (   ConfigFunctions.getEnvKeyValue("ALERTAUTOACCEPT").toUpperCase() == "YES") ProcessUnexpectedAlert();
			LogFunctions.LogEntry("Click on :"+name+" - Pass",false);
			if (PropertiesAndConstants.scriptExecutionResult == "Fail") PropertiesAndConstants.scriptExecutionResult = "Fail";
			else PropertiesAndConstants.scriptExecutionResult = "Pass";
			SwitchToDefaultContent(frames);
			return true;
		}
		else
		{
			LogFunctions.LogEntry("Cannot Click on :"+name+" - Element not Present/Displayed",false);
			SwitchToDefaultContent(frames);
			return true;
		}
	}
//		/// <summary>
//		/// Author            :  Ajit Thakur
//		/// Description       : Check Box UnCheck or Not without Frame
//		/// </summary>	
//
//	private boolean CheckBoxUnCheck(String name, String locator) throws Exception
//	{
//		if (UtilityFunctions.IsNullOrWhiteSpace(locator))
//		{
//			LogFunctions.LogEntry("CheckBox UnCheck"+name+" - Fail - Incorrect Locator :"+ locator,false);
//			PropertiesAndConstants.scriptExecutionResult = "Fail";
//			return false;
//		}
//
//		WebElement element = WaitForElementPresent(name, locator);
//
//		if (element != null && element.isDisplayed())
//		{
//			if (element.isSelected())
//			{
//				element.click();
//				LogFunctions.LogEntry("CheckBox UnCheck :"+name+ "- Pass",false);
//			}
//			else LogFunctions.LogEntry("CheckBox was Unchecked yet :"+name+"- Pass",false);
//
//			return true;
//		}
//		else
//		{
//			LogFunctions.LogEntry("CheckBox UnCheck :"+name+" - Fail",false);
//			return false;
//		}
//	}
	/// <summary>
	/// Author            :  Ajit Thakur
	/// Description       : Check Box UnCheck or Not with Frames
	/// </summary>	
	public boolean CheckBoxUnCheck(String name, String locator, String frames) throws Exception
	{
		if (UtilityFunctions.IsNullOrWhiteSpace(locator))
		{
			LogFunctions.LogEntry("CheckBox UnCheck"+name+"- Fail - Incorrect Locator :"+ locator,false);
			PropertiesAndConstants.scriptExecutionResult = "Fail";
			return false;
		}

		if (!UtilityFunctions.IsNullOrWhiteSpace(frames)) SwitchToFrames(frames);
		WebElement element = WaitForElementPresent(name, locator);

		if (element != null && element.isDisplayed())
		{
			if (element.isSelected())
			{
				element.click();
				LogFunctions.LogEntry("CheckBox UnCheck :"+name+" - Pass",false);
			}
			else    LogFunctions.LogEntry("CheckBox was Unchecked yet :"+name+" - Pass",false);

			if (PropertiesAndConstants.scriptExecutionResult == "Fail") PropertiesAndConstants.scriptExecutionResult = "Fail";
			else PropertiesAndConstants.scriptExecutionResult = "Pass";
			SwitchToDefaultContent(frames);
			return true;
		}
		else
		{
			LogFunctions.LogEntry("CheckBox UnCheck :"+name+" - Fail",false);
			PropertiesAndConstants.scriptExecutionResult = "Fail";
			SwitchToDefaultContent(frames);
			return false;
		}
	}
	/// <summary>
	/// Author            :  Ajit Thakur
	/// Description       : Check Double Click Element Without Frame
	/// </summary>	
	private boolean DoubleClickElement(String name, String locator)throws Exception
	{
		if (UtilityFunctions.IsNullOrWhiteSpace(locator))
		{
			LogFunctions.LogEntry("Double Click button :"+name+" - Fail - Incorrect Locator :"+ locator,false);
			PropertiesAndConstants.scriptExecutionResult = "Fail";
			return false;
		}

		WebElement element = WaitForElementPresent(name, locator);
		if (element != null)
		{
			
			Actions action = new Actions(PropertiesAndConstants.Selenium);
			action.doubleClick(element).perform();
			Thread.sleep(250);
			LogFunctions.LogEntry("Double Click button :"+name+" - Pass",false);
			return true;
		}
		else
		{
			LogFunctions.LogEntry("Double Click button :"+name+" : Cannot Find element - Fail",false);
			return false;
		}
	}
	
	/// <summary>
	/// Author            :  Ajit Thakur
	/// Description       : Check Double Click Element With Frame
	/// </summary>	
	public boolean DoubleClickElement(String name, String locator, String frames) throws Exception
	{
		if (UtilityFunctions.IsNullOrWhiteSpace(locator))
		{
			LogFunctions.LogEntry(String.format("Double Click button : \"{0}\" - Fail - Incorrect Locator : \"{1}\"", name, locator),false);
			PropertiesAndConstants.scriptExecutionResult = "Fail";
			return false;
		}

		if (!UtilityFunctions.IsNullOrWhiteSpace(frames)) SwitchToFrames(frames);
		WebElement element = WaitForElementPresent(name, locator);

		if (element != null)
		{
			Actions action = new Actions(PropertiesAndConstants.Selenium);
			action.doubleClick(element).perform();
			Thread.sleep(300);
			LogFunctions.LogEntry(String.format("Double Click button : \"{0}\" - Pass", name),false);
			if (PropertiesAndConstants.scriptExecutionResult == "Fail") PropertiesAndConstants.scriptExecutionResult = "Fail";
			else PropertiesAndConstants.scriptExecutionResult = "Pass";
			SwitchToDefaultContent(frames);
			return true;
		}
		else
		{
			LogFunctions.LogEntry(String.format("Double Click button : \"{0}\" : Cannot Find element - Fail", name),false);
			PropertiesAndConstants.scriptExecutionResult = "Fail";
			SwitchToDefaultContent(frames);
			return false;
		}
	}
	/// <summary>
	/// Author            :  Ajit Thakur
	/// Description       : Context Click (Right Button Mouse Click)
	/// </summary>	
	
	public boolean RightClickElement(String name, String locator, String frames)throws Exception
	{
		if (UtilityFunctions.IsNullOrWhiteSpace(locator))
		{
			LogFunctions.LogEntry("Right Click button :"+name+" - Fail - Incorrect Locator :"+ locator,false);
			PropertiesAndConstants.scriptExecutionResult = "Fail";
			return false;
		}

		if (!UtilityFunctions.IsNullOrWhiteSpace(frames)) SwitchToFrames(frames);
		WebElement element = WaitForElementPresent(name, locator);

		if (element != null)
		{
			Actions actionRightClick = new Actions(PropertiesAndConstants.Selenium);
			Actions actionMove = new Actions(PropertiesAndConstants.Selenium);
			actionMove.moveToElement(element).perform();
			actionRightClick.contextClick(element).perform();
			SwitchToDefaultContent(frames);
			LogFunctions.LogEntry("Right Click button :"+name+"- Pass",false);
			return true;
		}
		else
		{
			LogFunctions.LogEntry("Cannot find element for Right Click - Fail",false);
			SwitchToDefaultContent(frames);
			return false;
		}

	}
	/// <summary>
	/// Author            :  Ajit Thakur
	/// Description       : Type text into element (Example: TextBox) without Frame
	/// </summary>	
	boolean ClearElements(String name, String locator, String frames, String text)throws Exception
	{
		if (UtilityFunctions.IsNullOrWhiteSpace(locator))
		{
			LogFunctions.LogEntry("Type text into :"+name+" - Fail - Incorrect Locator :"+ locator,false);
			PropertiesAndConstants.scriptExecutionResult = "Fail";
			return false;
		}
		if (!UtilityFunctions.IsNullOrWhiteSpace(frames)) 
			{
			  SwitchToFrames(frames);
			}
		WebElement element = WaitForElementPresent(name, locator);

		if (element != null && element.isDisplayed())
		{
			if (UtilityFunctions.IsNullOrWhiteSpace(frames))  // Clear() cannot working with Frames	
			
		    element.clear(); 
		    Thread.sleep(550);
			System.out.println("locator"+locator);
			
			element.click();
			System.out.println("click field--");
			
			System.out.println("Clear the OTP Field===");
			LogFunctions.LogEntry("Enter text into :"+name+" - Pass" ,false);
			if (PropertiesAndConstants.scriptExecutionResult == "Fail")
			{ PropertiesAndConstants.scriptExecutionResult = "Fail"; }
			else PropertiesAndConstants.scriptExecutionResult = "Pass";
			SwitchToDefaultContent(frames);
			return true;
		}
		else
		{
			LogFunctions.LogEntry("Enter text into :"+name+" - Fail",false);
			PropertiesAndConstants.scriptExecutionResult = "Fail";
			SwitchToDefaultContent(frames);
			return false;
		}
	}	
	//Type mobile Number in to Mobile field

	boolean InputTextElement(String name, String locator, String text)throws Exception
	{
		if (UtilityFunctions.IsNullOrWhiteSpace(locator))
		{
			LogFunctions.LogEntry("Type text into :"+name+" - Fail - Incorrect Locator :"+ locator,false);
			return false;
		}
		WebElement element = WaitForElementPresent(name, locator);
		System.out.println(element+".......element");
		if (element != null && element.isDisplayed())
		
		//if (element != null)
		{
			element.clear();
			Thread.sleep(350);
			System.out.println(text);
			
			element.sendKeys(text);
			
			System.out.println("Send keys");
			//Thread.sleep(250);
			LogFunctions.LogEntry("Enter text into :"+name+" - Pass" ,false);
			return true;
		}
		else
		{
			LogFunctions.LogEntry("Enter text into :"+ name+" - Fail",false);
			return false;
		}
	}
	public boolean verifyprerequistites(String name, String locator,String text)throws Exception
	{
		if (UtilityFunctions.IsNullOrWhiteSpace(locator))
		{
			LogFunctions.LogEntry("Type text into :"+locator+" - Fail - Incorrect Locator :"+ locator,false);
			return false;
		}
		WebElement element = WaitForElementPresent(name, locator);
		
		System.out.println(element+".......element");
		
		if (element != null && element.isDisplayed())
		
		//if (element != null)
		{
			Thread.sleep(350);
			element.click();
			
			LogFunctions.LogEntry("Enter text into :"+name+" - Pass" ,false);
			return true;
		}
		else
		{
			LogFunctions.LogEntry("Enter text into :"+ name+" - Fail",false);
			return false;
		}
	}

	/// <summary>
	/// Description       : Type text into element (Example: TextBox) with Frame
	/// </summary>	
	
	public boolean InputTextElement(String name, String locator, String frames, String text)throws Exception
	{
		if (UtilityFunctions.IsNullOrWhiteSpace(locator))
		{
			LogFunctions.LogEntry("Type text into :"+name+" - Fail - Incorrect Locator :"+ locator,false);
			PropertiesAndConstants.scriptExecutionResult = "Fail";
			return false;
		}
		if (!UtilityFunctions.IsNullOrWhiteSpace(frames)) 
			{
			  SwitchToFrames(frames);
			}
		WebElement element = WaitForElementPresent(name, locator);

		if (element != null && element.isDisplayed())
		{
			if (UtilityFunctions.IsNullOrWhiteSpace(frames)) element.clear(); // Clear() cannot working with Frames	
			
		    Thread.sleep(7500);
		    
			System.out.println("text"+text);
			
		    element.sendKeys(text);
		    
			System.out.println("sending number");
			
			LogFunctions.LogEntry("Enter text into :"+name+" - Pass" ,false);
			if (PropertiesAndConstants.scriptExecutionResult == "Fail")
			{ PropertiesAndConstants.scriptExecutionResult = "Fail"; }
			else PropertiesAndConstants.scriptExecutionResult = "Pass";
			SwitchToDefaultContent(frames);
			return true;
		}
		else
		{
			LogFunctions.LogEntry("Enter text into :"+name+" - Fail",false);
			PropertiesAndConstants.scriptExecutionResult = "Fail";
			SwitchToDefaultContent(frames);
			return false;
		}
	}	
	
	public boolean InputTextElement1(String name, String locator, String frames, String text)throws Exception
	{
		if (UtilityFunctions.IsNullOrWhiteSpace(locator))
		{
			LogFunctions.LogEntry("Type text into :"+name+" - Fail - Incorrect Locator :"+ locator,false);
			PropertiesAndConstants.scriptExecutionResult = "Fail";
			return false;
		}
		if (!UtilityFunctions.IsNullOrWhiteSpace(frames)) 
			{
			  SwitchToFrames(frames);
			}
		WebElement element = WaitForElementPresent(name, locator);

		if (element != null && element.isDisplayed())
		{
			if (UtilityFunctions.IsNullOrWhiteSpace(frames)) element.clear(); // Clear() cannot working with Frames	
			
		    //element.click(); 
		    Thread.sleep(750);
			System.out.println("text"+text);
		    element.sendKeys(text);
	//	    element.pressKeyCode(AndroidKeyCode.BACK);
		  
			System.out.println("sending number");
			
			LogFunctions.LogEntry("Enter text into :"+name+" - Pass" ,false);
			if (PropertiesAndConstants.scriptExecutionResult == "Fail")
			{ PropertiesAndConstants.scriptExecutionResult = "Fail"; }
			else PropertiesAndConstants.scriptExecutionResult = "Pass";
			SwitchToDefaultContent(frames);
			return true;
		}
		else
		{
			LogFunctions.LogEntry("Enter text into :"+name+" - Fail",false);
			PropertiesAndConstants.scriptExecutionResult = "Fail";
			SwitchToDefaultContent(frames);
			return false;
		}
	}	
	/// <summary>
	/// Author            :  Ajit Thakur
	/// Description       :  Verify that WebElement Contain Text
	/// </summary>	
	public boolean VerifyIsElementContainsText(String name, String locator, String frames, String inputData)throws Exception
	{
		try{


		if (!UtilityFunctions.IsNullOrWhiteSpace(frames))
			SwitchToFrames(frames);

		WaitForElementPresent(name, locator);
		WebElement element = WaitForElementPresent(name, locator);

		if (element != null)
		{
			System.out.println("inside if ");
			for (int i = 0; i <= 10; i++)
			{ 
				System.out.println("inside for if");
				if (!element.isDisplayed()) 
					Thread.sleep(500);
				else break;
			}
				String elementattrtext = element.getText();
				elementattrtext = elementattrtext.replace(" ", "");
				inputData =inputData.replace(" ",  "");
				System.out.println(elementattrtext+"...elementattrtext");
			//if (elementattrtext != null && (elementattrtext.trim().toLowerCase().contains(inputData.toLowerCase()) ||
			//		elementattrtext.trim().toLowerCase().equals(inputData.toLowerCase())))
				if(elementattrtext != null && elementattrtext.equals(inputData))
				{
					LogFunctions.LogEntry("Element :"+name+" Contains text"+inputData+" : - Pass",false);
				if (PropertiesAndConstants.scriptExecutionResult == "Fail")
				{ 
					PropertiesAndConstants.scriptExecutionResult = "Fail";
				}
				else 
					PropertiesAndConstants.scriptExecutionResult = "Pass";
					//SwitchToDefaultContent(frames);
				return true;
			}
			else
			{
				LogFunctions.LogEntry("Element :"+name+"not Contains text"+inputData+". Actual:"+elementattrtext+" : - Fail",false);
				PropertiesAndConstants.scriptExecutionResult = "Fail";
				SwitchToDefaultContent(frames);
				return false;
			}
		}

		else
		{
			LogFunctions.LogEntry("Cannot find Element :"+name+": - Fail",false);
			PropertiesAndConstants.scriptExecutionResult = "Fail";
			PropertiesAndConstants.Selenium.switchTo().defaultContent();
			return false;
		}
	}//try block
	catch(Exception e)
	{
		e.printStackTrace();
		return false;
	}	
}


	/// <summary>
	/// Author            :  Ajit Thakur
	/// Description       :  Select Element from select node using Text
	/// </summary>	
	
	public boolean SelectElementByText(String name, String locator, String frames, String text)throws Exception
	 {
	  if (UtilityFunctions.IsNullOrWhiteSpace(locator))
	  {
	   LogFunctions.LogEntry("Select item from :"+name+" - Fail - Incorrect Locator :"+ locator,false);
	   PropertiesAndConstants.scriptExecutionResult = "Fail";
	   return false;
	  }

	  if (UtilityFunctions.IsNullOrWhiteSpace(text))
	  {
	   LogFunctions.LogEntry("Select item from"+name+"- Fail - Incorrect/Null Value for Select",false);
	   PropertiesAndConstants.scriptExecutionResult = "Fail";
	   return false;
	  }

	  if (!UtilityFunctions.IsNullOrWhiteSpace(frames)) 
	   SwitchToFrames(frames);
	  WebElement selectnode = WaitForElementPresent(name, locator);

	  if (selectnode != null)
	  {
	   Select select = new Select(selectnode);
	        List <WebElement> allOptions = selectnode.findElements(By
	            .tagName("option"));
	        Iterator <WebElement> i = allOptions.iterator();
	        while (i.hasNext())
	        {
	          WebElement row = i.next();
	          if (row.getText().contains(text.trim()))
	          {
	            select.selectByVisibleText(row.getText());
	            break;
	          }
	          Thread.sleep(2000);
	        }	   
	   LogFunctions.LogEntry("Select element from:"+name+" - Pass",false);
	   if (PropertiesAndConstants.scriptExecutionResult == "Fail")
	   { PropertiesAndConstants.scriptExecutionResult = "Fail"; }
	   else PropertiesAndConstants.scriptExecutionResult = "Pass";
	   SwitchToDefaultContent(frames);
	   return true;
	  }
	  else
	  {
	   LogFunctions.LogEntry("Cannot select element from :"+name+" - Fail",false);
	   PropertiesAndConstants.scriptExecutionResult = "Fail";
	   SwitchToDefaultContent(frames);
	   return false;
	  }
	 }
	
	/// <summary>
	/// Author            :  Ajit Thakur
	/// Description       : Click Element with atttr target='_blank' and open new Tab or Window
	/// </summary>	
	
	public boolean OpenNewTabOrWindow(String name, String locator, String frames)throws Exception
	{
		if (UtilityFunctions.IsNullOrWhiteSpace(locator))
		{
			LogFunctions.LogEntry("Incorrect Locator :"+locator,false);
			PropertiesAndConstants.scriptExecutionResult = "Fail";
			return false;
		}

		if (!UtilityFunctions.IsNullOrWhiteSpace(frames)) SwitchToFrames(frames);

		WebElement element = WaitForElementPresent(name, locator);

		if (element != null && element.isDisplayed())
		{
			try
			{
				element.click();
				Thread.sleep(1000);
				//PropertiesAndConstants.AdditionalDriverWindowHandle = GlobalClass.GetDriver.WindowHandles[1];
				PropertiesAndConstants.AdditionalDriverWindowHandle=PropertiesAndConstants.Selenium.getWindowHandle();
				//GlobalClass.GetDriver.SwitchTo().Window(GlobalClass.AdditionalDriverWindowHandle);
				
				for(String Winhadle : PropertiesAndConstants.Selenium.getWindowHandles()){
					PropertiesAndConstants.Selenium.switchTo().window(Winhadle);
				}
					
				LogFunctions.LogEntry("Open new tab - Pass",false);
				if (PropertiesAndConstants.scriptExecutionResult == "Fail")
				{ PropertiesAndConstants.scriptExecutionResult = "Fail"; }
				else PropertiesAndConstants.scriptExecutionResult = "Pass";
				SwitchToDefaultContent(frames);
				//PropertiesAndConstants.Selenium.switchTo().window(PropertiesAndConstants.AdditionalDriverWindowHandle);
				WaitForReadyStateComplete();
				return true;
			}
			catch (IllegalArgumentException outofrangeexc) 
			// catch (ArgumentOutOfRangeException )
			{
				LogFunctions.LogEntry("Cannot open new tab - Fail",false);
				LogFunctions.LogEntry("Reason: " + outofrangeexc.getMessage(), false);
				LogFunctions.LogEntry("Details: " + outofrangeexc, false);
				SwitchToDefaultContent(frames);
				return false;
			}
			catch (Exception exc)
			{

				//  Console.ForegroundColor = ConsoleColor.Red;
				LogFunctions.LogEntry("Cannot open new tab - Fail",false);
				LogFunctions.LogEntry("Reason: " + exc.getMessage(), false);
				LogFunctions.LogEntry("Details: " + exc, false);
				PropertiesAndConstants.scriptExecutionResult = "Fail";
				// Console.ResetColor();
				SwitchToDefaultContent(frames);
				return false;
			}   
		}
		else
		{
			LogFunctions.LogEntry("Cannot open new tab (Element for Click not Found) - Fail",false);
			return false;
		}
	}
	
	
	
	/// <summary>
	/// Author            :  Ajit Thakur
	/// Description       : Switch to the new window opened.
	/// </summary>	
	public boolean SwitchToNewWindow() throws IOException
	{
		try
		{
		String parentWindow = PropertiesAndConstants.Selenium.getWindowHandle();
		Set<String> handles =  PropertiesAndConstants.Selenium.getWindowHandles();
		   for(String windowHandle  : handles)
		       {
		       if(!windowHandle.equals(parentWindow))
		          {
		    	   PropertiesAndConstants.Selenium.switchTo().window(windowHandle);
		         
		          }
		       }
		   LogFunctions.LogEntry("Switch to new window - Pass",false);
		   return true;
			
	}catch(Exception exc)
	{
		LogFunctions.LogEntry("Cannot open new tab - Fail",false);
		LogFunctions.LogEntry("Reason: " + exc.getMessage(), false);
		LogFunctions.LogEntry("Details: " + exc, false);
		return false;
	}
	}

	
	/// <summary>
	/// Author            :  Ajit Thakur
	/// Description       : Click Element with atttr target='_blank' and open new Tab or Window using JavaScript (onClick action)
	/// </summary>	
	
	public boolean OpenNewTabOrWindowByJavaScript(String name, String locator, String frames)throws Exception
	{
		if (UtilityFunctions.IsNullOrWhiteSpace(locator))
		{
			LogFunctions.LogEntry("Incorrect Locator :"+ locator,false);
			PropertiesAndConstants.scriptExecutionResult = "Fail";
			return false;
		}

		if (!UtilityFunctions.IsNullOrWhiteSpace(frames)) SwitchToFrames(frames);

		WebElement element = WaitForElementPresent(name, locator);

		if (element != null)
		{
			try
			{
				// ((JavaScriptExecutor)PropertiesAndConstants.Selenium).ExecuteScript("arguments[0].click();", element);
				Thread.sleep(1000);
				PropertiesAndConstants.AdditionalDriverWindowHandle = PropertiesAndConstants.Selenium.getWindowHandle();
				LogFunctions.LogEntry("Open new tab by JavaScript - Pass",true);
				if (PropertiesAndConstants.scriptExecutionResult == "Fail")
				{ PropertiesAndConstants.scriptExecutionResult = "Fail"; }
				else PropertiesAndConstants.scriptExecutionResult = "Pass";
				SwitchToDefaultContent(frames);
				PropertiesAndConstants.Selenium.switchTo().window(PropertiesAndConstants.AdditionalDriverWindowHandle);
				WaitForReadyStateComplete();
				return true;
			}
			catch (RangeException outofrangeexc)
			{
				LogFunctions.LogEntry("Cannot open new tab by JavaScript - Fail",false);
				LogFunctions.LogEntry("Reason: " + outofrangeexc.getMessage(), false);
				LogFunctions.LogEntry("Details: " + outofrangeexc, false);
				SwitchToDefaultContent(frames);
				return false;
			}
			catch (Exception exc)
			{
				// Console.ForegroundColor = ConsoleColor.Red;
				LogFunctions.LogEntry("Cannot open new tab - Fail",false);
				LogFunctions.LogEntry("Reason: " + exc.getMessage(), false);
				LogFunctions.LogEntry("Details: " + exc, false); 
				PropertiesAndConstants.scriptExecutionResult = "Fail";
				// Console.ResetColor();
				SwitchToDefaultContent(frames);
				return false;
			}
		}
		else
		{
			LogFunctions.LogEntry("Cannot open new tab (Element for Click not Found) - Fail",false);
			return false;
		}
	}
	
	
	/// <summary>
	/// Author            :  Ajit Thakur
	/// Description       : Close Additional Tab or Window (which opened earlier)
	/// </summary>	
	
	public boolean CloseAdditionalTabOrWindow()throws Exception
	{
		//String main = PropertiesAndConstants.Selenium.getWindowHandle();
		//Set<String> whs = PropertiesAndConstants.Selenium.getWindowHandles();
		
		ArrayList<String> main = new ArrayList<String> (PropertiesAndConstants.Selenium.getWindowHandles());
		
		if(main.size() > 1)
		{
		PropertiesAndConstants.Selenium.switchTo().window(main.get(1));
		PropertiesAndConstants.Selenium.close();
		PropertiesAndConstants.Selenium.switchTo().window(main.get(0));
		LogFunctions.LogEntry("Close tab - Pass",false);
		
		if (PropertiesAndConstants.scriptExecutionResult == "Fail")
		{ PropertiesAndConstants.scriptExecutionResult = "Fail"; }
		else PropertiesAndConstants.scriptExecutionResult = "Pass";
		return true;
		}
		else
		{
			LogFunctions.LogEntry("Cannot close tab - Fail",false);
			return false;
		} 
		//return true;
	}

	/// <summary>
	/// Description       : Close Main Tab or Window
	/// </summary>	
	
	public boolean CloseMainTabOrWindow()throws Exception
	{
		Set<String> whs = PropertiesAndConstants.Selenium.getWindowHandles();
				
		if (whs.size() > 1)
		{
			PropertiesAndConstants.Selenium.switchTo().window(PropertiesAndConstants.MainDriverWindowHandle).close();
			PropertiesAndConstants.Selenium.switchTo().window(PropertiesAndConstants.AdditionalDriverWindowHandle);
			PropertiesAndConstants.MainDriverWindowHandle = PropertiesAndConstants.Selenium.getWindowHandle();
			LogFunctions.LogEntry("Close tab - Pass",false);
			if (PropertiesAndConstants.scriptExecutionResult == "Fail")
			{ PropertiesAndConstants.scriptExecutionResult = "Fail"; }
			else PropertiesAndConstants.scriptExecutionResult = "Pass";
			return true;
		}
		else
		{
			LogFunctions.LogEntry("Cannot close tab - Fail",false);
			return false;
		}
	}

	
	/// <summary>
	/// Author            :  Ajit Thakur
	/// Description       : Instructs the driver to Switch a Main Tab or window.
	/// </summary>	
	
	public boolean SwitchToMainTab()throws Exception
	{
		if (PropertiesAndConstants.MainDriverWindowHandle != null)
		{
			try
			{
				PropertiesAndConstants.Selenium.switchTo().window(PropertiesAndConstants.MainDriverWindowHandle);
				Thread.sleep(500);
				return true;
			}
			catch (Exception switchTabExc)
			{
				LogFunctions.LogEntry("Cannot Switch in a Main Tab - Fail",false);
				LogFunctions.LogEntry("Reason: " + switchTabExc.getMessage(), false);
				LogFunctions.LogEntry("Details: " + switchTabExc, false);
				return false;
			}
		}
		else
		{
			LogFunctions.LogEntry("Cannot Switch in a Main Tab (WindowHandle is Null) - Fail",false);
			return false;
		}
	}

	/// <summary>
	/// Author            :  Ajit Thakur
	/// Description       : Instructs the driver to Switch a Additional (opened later) Tab or Window
	/// </summary>	
	
	public boolean SwitchToAdditionalTab()throws Exception
	{
		if (PropertiesAndConstants.AdditionalDriverWindowHandle != null)
		{
			try
			{
				PropertiesAndConstants.Selenium.switchTo().window(PropertiesAndConstants.AdditionalDriverWindowHandle);
				Thread.sleep(500);
				return true;
			}
			catch (Exception switchTabExc)
			{
				LogFunctions.LogEntry("Cannot Switch in an Additional Tab - Fail",false);
				LogFunctions.LogEntry("Reason: " + switchTabExc.getMessage(), false);
				LogFunctions.LogEntry("Details: " + switchTabExc, false);
				return false;
			}
		}
		else
		{
			LogFunctions.LogEntry("Cannot Switch in a Additional Tab (WindowHandle is Null) - Fail",false);
			return false;
		}
	}
	
	
	/// <summary>
	/// Author            :  Ajit Thakur
	/// Description       : Browser Back (Return)
	/// </summary>	
	
	public boolean ReturnToPreviousPage()throws Exception
	{
		try
		{
			PropertiesAndConstants.Selenium.navigate().back();
			Thread.sleep(250);
			WaitForReadyStateComplete();
			LogFunctions.LogEntry("Return to previous page - Pass",false);
			if (PropertiesAndConstants.scriptExecutionResult == "Fail")
			{ PropertiesAndConstants.scriptExecutionResult = "Fail"; }
			else PropertiesAndConstants.scriptExecutionResult = "Pass";
			return true;
		}
		catch(Exception exc)
		{
			LogFunctions.LogEntry("Cannot Return to previous page - Fail",false);
			return false;
		}
	}

	/// <summary>
	/// Author            :  Ajit Thakur
	/// Description       : Browser Refresh (Refresh)
	/// </summary>	
	
	public boolean RefreshPage() throws Exception
	{
		try
		{
			PropertiesAndConstants.Selenium.navigate().refresh();
			WaitForReadyStateComplete();
			LogFunctions.LogEntry("Refresh page - Pass",false);
			if (PropertiesAndConstants.scriptExecutionResult == "Fail")
			{ PropertiesAndConstants.scriptExecutionResult = "Fail"; }
			else PropertiesAndConstants.scriptExecutionResult = "Pass";
			return true;
		}
		catch (WebDriverException wdExc)
		{
			LogFunctions.LogEntry("Cannot Refresh page (WebDriverException) - Fail",false);
			LogFunctions.LogEntry("WebDriverException Details " + wdExc.getMessage(),false);
			return false;
		}
	}
	
	
	/// <summary>
	/// Author            :  Ajit Thakur
	/// Description       : Accept Alert if We expected that Alert Appear
	/// </summary>	
	
	public boolean AlertAccept() throws Exception
    {
          Alert alert = null;
          
          Thread.sleep(2000);
          int i=0;
             while(i++<5)
          //alertWait.until(d =>
       {
           try
           {
        	  // PropertiesAndConstants.Selenium.switchTo().alert().accept();
               alert = PropertiesAndConstants.Selenium.switchTo().alert();
               //System.out.println(alert+"dum maro dum");
               if (alert != null) break;
               
           }
           catch (Exception exc)
           {
               LogFunctions.LogEntry("Cannot Switch to Alert - Fail ", false);
               LogFunctions.LogEntry("Reason: " + exc, false);
               return false;
           }
       }

          if (alert != null)
          {
                Thread.sleep(500);
                PropertiesAndConstants.Selenium.switchTo().alert().accept();
                Thread.sleep(500);
                LogFunctions.LogEntry("Alert was Accepted - Pass",false);
                SwitchToDefaultContent();
                return true;
          }
          else
          {
                LogFunctions.LogEntry("Cannot Accept alert - Fail",false);
                SwitchToDefaultContent();
                return false;
          }
    }

	
	
	/// <summary>
	/// Author            :  Ajit Thakur
	
	
	/// Description       : WebDriver Action Focus Element
	/// </summary>	
	
	public boolean MoveToElement(String name, String locator, String frames) throws Exception
	{
		if (UtilityFunctions.IsNullOrWhiteSpace(locator))
		{
			LogFunctions.LogEntry("Focus on element :"+name+" - Fail - Incorrect Locator :"+locator,false);
			PropertiesAndConstants.scriptExecutionResult = "Fail";
			return false;
		}

		if (!UtilityFunctions.IsNullOrWhiteSpace(frames))
		{
			
			SwitchToDefaultContent(frames);
			String[] framesArray=frames.split("||");
			int x=0;
			LinkedList<String> list=new LinkedList<String>();
			String[] frameArray1=new String[list.size()];
			for(int index=0;index<framesArray.length;index++)
			{
				if(framesArray[index].length()!=0 )
				{
					list.add(framesArray[index]);
					x++;

				}
			}
			for(int j=0;j<list.size();j++)
			{
				frameArray1[j]=list.get(j); 
			}
			//  String  framesArray = frames.Split(new String[] { "||" }, StringSplitOptions.RemoveEmptyEntries);
			for (int i = 0; i < framesArray.length; i++)
			{
				if (FindElement(frameArray1[i]) != null)
				{
					LogFunctions.LogEntry("Frame Element with locator"+frameArray1[i]+" FOUND!!! ", false);
					PropertiesAndConstants.Selenium.switchTo().frame(FindElement(framesArray[i]));

					//  framewait.Until<WebElement>(((d) => PropertiesAndConstants.GetDriver.FindElement(By.TagName("body"))));
				}
				else
				{
					LogFunctions.LogEntry("CANNOT FOUND Frame Element with locator"+frameArray1[i]+" !!! " , false);
					return false;
				}
			}
		}

		WebElement element = WaitForElementPresent(name, locator);

		if (element != null)
		{
			Actions action = new Actions(PropertiesAndConstants.Selenium);
			action.moveToElement(element).build().perform();
			LogFunctions.LogEntry("Focus on element :"+name+" - Pass",false);
			if (PropertiesAndConstants.scriptExecutionResult == "Fail")
			{ PropertiesAndConstants.scriptExecutionResult = "Fail"; }
			else PropertiesAndConstants.scriptExecutionResult = "Pass";
			return true;
		}
		else
		{
			LogFunctions.LogEntry("Cannot find element :"+name+"- Fail",false);
			PropertiesAndConstants.scriptExecutionResult = "Fail";
			return false;
		}
	}

	
	
	/// <summary>         
	/// Author            :Swapnil Mane  
	/// Description       : WebDriver Action Focus Element
	/// </summary>	
	
	public boolean MoveMouseToElement(String name, String locator, String frames)
			throws Exception {
		if (UtilityFunctions.IsNullOrWhiteSpace(locator)) {
			LogFunctions.LogEntry("Focus on element :" + name
					+ " - Fail - Incorrect Locator :" + locator, false);
			PropertiesAndConstants.scriptExecutionResult = "Fail";
			return false;
		}

		if (!UtilityFunctions.IsNullOrWhiteSpace(frames)) {

			SwitchToDefaultContent(frames);
			String[] framesArray = frames.split("||");
			int x = 0;
			LinkedList<String> list = new LinkedList<String>();
			String[] frameArray1 = new String[list.size()];
			for (int index = 0; index < framesArray.length; index++) {
				if (framesArray[index].length() != 0) {
					list.add(framesArray[index]);
					x++;
				}
			}
			for (int j = 0; j < list.size(); j++) {
				frameArray1[j] = list.get(j);
			}
				for (int i = 0; i < frameArray1.length; i++) {
				if (FindElement(framesArray[i]) != null) {
					LogFunctions.LogEntry("Frame Element with locator"
							+ framesArray[i] + " FOUND!!! ", false);
					PropertiesAndConstants.Selenium.switchTo().frame(
							FindElement(framesArray[i]));

				} else {
					LogFunctions.LogEntry(
							"CANNOT FOUND Frame Element with locator"
									+ framesArray[i] + "!!! ", false);
					return false;
				}
			}
		}
		WebElement element = WaitForElementPresent(name, locator);
		if (element != null) {

			Actions a1 = new Actions(PropertiesAndConstants.Selenium);
			a1.moveToElement(
					PropertiesAndConstants.Selenium.findElement(By
							.xpath(locator))).build().perform();
			System.out.println("Mouse Over:" + locator);
			Thread.sleep(60000);
			LogFunctions.LogEntry("Move Mouse to element :" + name + " - Pass",
					false);
			if (PropertiesAndConstants.scriptExecutionResult == "Fail") {
				PropertiesAndConstants.scriptExecutionResult = "Fail";
			} else
				PropertiesAndConstants.scriptExecutionResult = "Pass";

			return true;
		} else {
			LogFunctions.LogEntry("Cannot find element :" + name + " - Fail",
					false);
			PropertiesAndConstants.scriptExecutionResult = "Fail";
			return false;
		}
	}
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
/// <summary>
/// Author            :  Ajit Thakur
/// Description       : HardWait  To wait for particular time being
//	Parameter 		  :	Wait time in minutes
/// </summary>	
	
public void  hardWait(String timeInMinutes) throws Exception 
{
	
	if (UtilityFunctions.IsNullOrWhiteSpace(timeInMinutes))
	{
		
			LogFunctions.LogEntry("Please Enter proper time :"+timeInMinutes+"  - Fail",false);
	
		PropertiesAndConstants.scriptExecutionResult = "Fail";
		//return false;
	}
	timeInMinutes = timeInMinutes.trim();
	int time = Integer.parseInt(timeInMinutes);
	time = time*60;
	time = time*100;
	System.out.print(time+" time.....................");
	Thread.sleep(time);
}	
	
	/// <summary>
	/// Author            :  Ajit Thakur
	/// Description       :  Verify that element present and displayed on the page
	/// </summary>	
		
	public boolean VerifyIsElementPresentAndDisplayed(String name, String locator, String frames) throws Exception
	{
		
		if (UtilityFunctions.IsNullOrWhiteSpace(locator))
		{
			LogFunctions.LogEntry("Incorrect Locator :"+ locator,false);
			PropertiesAndConstants.scriptExecutionResult = "Fail";
			return false;
		}
		
		if (!UtilityFunctions.IsNullOrWhiteSpace(frames)) 
			SwitchToFrames(frames);
		
		
		WebElement element = WaitForElementPresent(name, locator);
				
		if (element != null)
		{
			for (int i = 0; i <= 10; i++)
			{
				if (!element.isDisplayed()) 
					Thread.sleep(500);
				else break;
			}		
			//if (element.isDisplayed() || element.isEnabled() || element.isSelected())
			//Thread.sleep(3000);
			if (element.isDisplayed()|| element.isEnabled())	
			{
				LogFunctions.LogEntry("Element :"+ name+"present - Pass",false);
				if (PropertiesAndConstants.scriptExecutionResult == "Fail")
				{ 
					PropertiesAndConstants.scriptExecutionResult = "Fail"; 
				}
				else 
					PropertiesAndConstants.scriptExecutionResult = "Pass";
					SwitchToDefaultContent(frames);
				return true;
			}
			else
			{	//SeleniumHandler.CloseSeleniumDriver();	
				LogFunctions.LogEntry("Element :"+name+" not Displayed - Fail",false);
				PropertiesAndConstants.scriptExecutionResult = "Fail";
				SwitchToDefaultContent(frames);
				
				return false;
			}
		}
		else
		{	
			if(name.contains("Jackpots_Section")){
			LogFunctions.LogEntry("Element :"+name+" not found - Fail",false);
			PropertiesAndConstants.scriptExecutionResult = "Pass";
			SwitchToDefaultContent(frames);
			return true;
			}
		
			LogFunctions.LogEntry("Element :"+name+" not found - Fail",false);
			PropertiesAndConstants.scriptExecutionResult = "Fail";
			SwitchToDefaultContent(frames);
			return false;
		}
	}

	
	/// <summary>
	/// Author            :  Ajit Thakur
	/// Description       :  Verify that element DOESN'T displayed on the page
	/// </summary>	
	
	public boolean VerifyIsElementNotDisplayed(String name, String locator, String frames) throws Exception
	{
		if (UtilityFunctions.IsNullOrWhiteSpace(locator))
		{
			LogFunctions.LogEntry("Incorrect Locator :"+locator,false);
			PropertiesAndConstants.scriptExecutionResult = "Fail";
			return false;
		}

		if (!UtilityFunctions.IsNullOrWhiteSpace(frames)) SwitchToFrames(frames);
		Thread.sleep(2000); // Wait 2 Second Until element Appear
		WebElement element = FindElement(locator);

		if (element != null)
		{
			if (!element.isDisplayed())
			{
				LogFunctions.LogEntry("Element :"+name+" not displayed - Pass",false);
				if (PropertiesAndConstants.scriptExecutionResult == "Fail")
				{ PropertiesAndConstants.scriptExecutionResult = "Fail"; }
				else PropertiesAndConstants.scriptExecutionResult = "Pass";
				return true;
			}
			else
			{
				LogFunctions.LogEntry("Element :"+name+" displayed - Fail",false);
				PropertiesAndConstants.scriptExecutionResult = "Fail";
				return false;
			}
		}
		else
		{
			LogFunctions.LogEntry("Element :"+name+" not present - Pass",false);
			if (PropertiesAndConstants.scriptExecutionResult == "Fail")
			{ PropertiesAndConstants.scriptExecutionResult = "Fail"; }
			else PropertiesAndConstants.scriptExecutionResult = "Pass";
			return true;
		}
	}
	
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	/// <summary>
		/// Author            : Ajit Thakur
		/// Description       : Verify image Name is same as set attribute for a <img></img> tag in DOM
		/// </summary>	
		
public boolean VerifyIsSameImageDisplayedAsSet(String name, String locator,String imgName) throws Exception
{
	
	if (UtilityFunctions.IsNullOrWhiteSpace(imgName))
	{
		LogFunctions.LogEntry("Image Name not Provided :"+ imgName,false);
		PropertiesAndConstants.scriptExecutionResult = "Fail";
		return false;
	}
	
	if (UtilityFunctions.IsNullOrWhiteSpace(locator))
	{
		LogFunctions.LogEntry("Incorrect Locator :"+ locator,false);
		PropertiesAndConstants.scriptExecutionResult = "Fail";
		return false;
	}
	WebElement element = WaitForElementPresent(name, locator);
	if (element != null)
	{
		if (element.getTagName().equals("img"))
		{
			System.out.println("in side img get");
			String srcAttribute = element.getAttribute("src");
		
			if (srcAttribute != null)
			{ 
				System.out.println("in side srcattr get");
				PropertiesAndConstants.Selenium.navigate().to(srcAttribute);
				String driverTitle = PropertiesAndConstants.Selenium.getTitle();						
					driverTitle = driverTitle.replace(".", "~");
					System.out.println("replaced str : "+driverTitle);
					String[] driverTitle1 = driverTitle.split("~");	
					driverTitle1[0] =driverTitle1[0].toLowerCase();
					imgName =imgName.toLowerCase();
				PropertiesAndConstants.Selenium.navigate().back();
				if (!driverTitle.contains("404") && !driverTitle.contains("not found") && !driverTitle.contains("Runtime Error")
						&& !driverTitle.contains("Problem loading") && driverTitle1[0].contains(imgName))
				{
					System.out.println("in !condition");
					LogFunctions.LogEntry("Image Matched..."+srcAttribute+".... is Valid - Pass" ,false);
					if (PropertiesAndConstants.scriptExecutionResult == "Fail")
					{ PropertiesAndConstants.scriptExecutionResult = "Fail"; }
					else PropertiesAndConstants.scriptExecutionResult = "Pass";
					return true;
				}
				else
				{
					LogFunctions.LogEntry("Image src attribute Incorrect -Image"+srcAttribute+" Not Found - Fail ",false);
					PropertiesAndConstants.scriptExecutionResult = "Fail";
					return false;
				}
			}//End of srcAttribute != null iF
			else
			{
				LogFunctions.LogEntry("Image src attribute:"+srcAttribute+" Actual = Null - Fail ",false);
				PropertiesAndConstants.scriptExecutionResult = "Fail";
				return false;
			}
		}//End of tag name img iF
		else
		{
			LogFunctions.LogEntry("Current WebElement is NOT an IMAGE <img> - Fail"+ locator,false);
			LogFunctions.LogEntry("Current element tag is"+ element.getTagName(), false);
			PropertiesAndConstants.scriptExecutionResult = "Fail";
			return false;
		}
	}//End of Element iF
	else
	{
		LogFunctions.LogEntry("Cannot Find element with locator :"+locator+" - Fail",false);
		PropertiesAndConstants.scriptExecutionResult = "Fail";
		return false;
	}
}//End of function 

     /// <summary>
	/// Description       :  To retrive the text from element.
	/// </summary>	
public boolean GetAttributeValue(String name, String locator, String frames)throws Exception
{ 
	try
	{
	 if (UtilityFunctions.IsNullOrWhiteSpace(locator))
	 {	
	  LogFunctions.LogEntry("Type text into :"+name+" - Fail - Incorrect Locator :"+ locator,false);
	  PropertiesAndConstants.scriptExecutionResult = "Fail";
	  return false;
	 }
	 if (!UtilityFunctions.IsNullOrWhiteSpace(frames)) 
	  {
	    SwitchToFrames(frames);
	  }
	 
	 WebElement element = WaitForElementPresent(name, locator);
	
	 if (element.getText() != null && element.isDisplayed())
	 {	
	  String SaveValue = element.getText();  
	  System.out.println(SaveValue+"SaveValue");
	  
	//  if(SaveValue != null)
	//  { 	   
	   LogFunctions.LogEntry("element Value displayed as:"+SaveValue+" - Pass",false); 
	 //  return true;
	  //}
	  return true;
	 }
	 else
	 {
	  LogFunctions.LogEntry("Enter text into :"+name+" - Fail",false);
	  PropertiesAndConstants.scriptExecutionResult = "Fail";
	  SwitchToDefaultContent(frames);
	  return false;
	 }
	}
	catch(Exception e)
	{
			LogFunctions.LogEntry("Enter text into :"+name+" - Fail",false);
		  PropertiesAndConstants.scriptExecutionResult = "Fail";
		  SwitchToDefaultContent(frames);
		  return false;
	}
}//End of Function

	/// <summary>
	/// Author            :  Ajit Thakur
	/// Description       :  Verify that Current page URL contains input data
	/// </summary>	
	
	public boolean VerifyIsPageURLContainsText(String inputData) throws Exception
	{
		if (UtilityFunctions.IsNullOrWhiteSpace(inputData))
		{
			LogFunctions.LogEntry("Incorrect Input data :"+ inputData,false);
			PropertiesAndConstants.scriptExecutionResult = "Fail";
			return false;
		}
		if (PropertiesAndConstants.Selenium.getCurrentUrl().toLowerCase().contains(inputData.toLowerCase()))
		{
			LogFunctions.LogEntry("Current browser URL Contains: -"+inputData+" - Pass " ,false);
			if (PropertiesAndConstants.scriptExecutionResult == "Fail")
			{ PropertiesAndConstants.scriptExecutionResult = "Fail"; }
			else PropertiesAndConstants.scriptExecutionResult = "Pass";
			return true;
		}
		else
		{
			LogFunctions.LogEntry("Current browser URL: Actual -"+PropertiesAndConstants.Selenium.getCurrentUrl()+"; Expected Contains: -"+inputData+" - Fail " ,false);
			PropertiesAndConstants.scriptExecutionResult = "Fail";
			return false;
		}
	}
	
	/// <summary>
	/// Author            :  Ajit Thakur
	/// Description       :  Verify that Current page URL DOES'N contain input data
	/// </summary>	
	
	public boolean VerifyIsPageURLNotContainsText(String inputData) throws Exception
	{
		if (UtilityFunctions.IsNullOrWhiteSpace(inputData))
		{
			LogFunctions.LogEntry("Incorrect Input data :"+ inputData,false);
			PropertiesAndConstants.scriptExecutionResult = "Fail";
			return false;
		}
		if (!PropertiesAndConstants.Selenium.getCurrentUrl().toLowerCase().contains(inputData.toLowerCase()))
		{
			LogFunctions.LogEntry("Current browser URL NOT Contains: -"+ inputData+" - Pass ",false);
			if (PropertiesAndConstants.scriptExecutionResult == "Fail")
			{ PropertiesAndConstants.scriptExecutionResult = "Fail"; }
			else PropertiesAndConstants.scriptExecutionResult = "Pass";
			return true;
		}
		else
		{
			LogFunctions.LogEntry("Current browser URL: Actual "+ PropertiesAndConstants.Selenium.getCurrentUrl()+"; Expected Not Contains: -"+inputData+" - Fail ",false);
			PropertiesAndConstants.scriptExecutionResult = "Fail";
			return false;
		}
	}
	
	
	/// <summary>
	/// Author            :  Ajit Thakur
	/// Description       : Verify that Current Browser Title DOESN'T contain input data
	/// </summary>	
	
	public boolean VerifyIsTitleNotContainsText(String inputData) throws Exception
	{
		if (UtilityFunctions.IsNullOrWhiteSpace(inputData))
		{
			LogFunctions.LogEntry("Incorrect Input data :"+inputData,false);
			PropertiesAndConstants.scriptExecutionResult = "Fail";
			return false;
		}

		if (!PropertiesAndConstants.Selenium.getTitle().toLowerCase().contains(inputData.toLowerCase()))
		{
			LogFunctions.LogEntry("Current browser Title Doesn't Contain: -"+inputData+" - Pass ",false);
			if (PropertiesAndConstants.scriptExecutionResult == "Fail")
			{ PropertiesAndConstants.scriptExecutionResult = "Fail"; }
			else PropertiesAndConstants.scriptExecutionResult = "Pass";
			return true;
		}
		else
		{
			LogFunctions.LogEntry("Current browser Title: Actual -"+ PropertiesAndConstants.Selenium.getTitle()+"; Expected Doesn't Contain: -"+inputData+"- Fail ",false);
			PropertiesAndConstants.scriptExecutionResult = "Fail";
			return false;
		}
	}

	

	/// <summary>
	/// Author            :  Ajit Thakur
	/// Description       : Verify is Alert Present (if we Expected)
	/// </summary>	
	
	public boolean VerifyIsAlertPresent() throws Exception
	{
		try
		{
			Alert alert = PropertiesAndConstants.Selenium.switchTo().alert();
			String alertText = alert.getText();
			LogFunctions.LogEntry("Alert with Text :"+alertText+" present - Pass",false);
			return true;
		}
		catch(Exception exc)
		{
			LogFunctions.LogEntry("Alert doesn't present - Fail",false);
			return false;
		}
	}
	
	
	/// <summary>
	/// Author            :  Ajit Thakur
	/// Description       : Verify scr attribute for a <img></img> tag in DOM
	/// </summary>	
	
	public boolean VerifyIsImageDisplayed(String name, String locator) throws Exception
	{
		
		if (UtilityFunctions.IsNullOrWhiteSpace(locator))
		{
			LogFunctions.LogEntry("Incorrect Locator :"+ locator,false);
			PropertiesAndConstants.scriptExecutionResult = "Fail";
			return false;
		}

		WebElement element = WaitForElementPresent(name, locator);

		if (element != null)
		{
			if (element.getTagName().equals("img"))
			{
				String srcAttribute = element.getAttribute("src");

				if (srcAttribute != null)
				{ 
					PropertiesAndConstants.Selenium.navigate().to(srcAttribute);
					String driverTitle = PropertiesAndConstants.Selenium.getTitle();
					PropertiesAndConstants.Selenium.navigate().back();

					if (!driverTitle.contains("404") && !driverTitle.contains("not found") && !driverTitle.contains("Runtime Error")
							&& !driverTitle.contains("Problem loading"))
					{
						
						LogFunctions.LogEntry("Image src attribute..."+srcAttribute+".... is Valid - Pass" ,false);
						if (PropertiesAndConstants.scriptExecutionResult == "Fail")
						{ PropertiesAndConstants.scriptExecutionResult = "Fail"; }
						else PropertiesAndConstants.scriptExecutionResult = "Pass";
						return true;
					}
					else
					{
						LogFunctions.LogEntry("Image src attribute Incorrect -Image"+srcAttribute+" Not Found - Fail ",false);
						PropertiesAndConstants.scriptExecutionResult = "Fail";
						return false;
					}
				}
				else
				{
					LogFunctions.LogEntry("Image src attribute:"+srcAttribute+" Actual = Null - Fail ",false);
					PropertiesAndConstants.scriptExecutionResult = "Fail";
					return false;
				}
			}
			else
			{
				LogFunctions.LogEntry("Current WebElement is NOT an IMAGE <img> - Fail"+ locator,false);
				LogFunctions.LogEntry("Current element tag is"+ element.getTagName(), false);
				PropertiesAndConstants.scriptExecutionResult = "Fail";
				return false;
			}
		}
		else
		{
			LogFunctions.LogEntry("Cannot Find element with locator :"+locator+" - Fail",false);
			PropertiesAndConstants.scriptExecutionResult = "Fail";
			return false;
		}
	}
	/// <summary>
	/// Author            :  Ajit Thakur
	/// Description       : Verify that Current Browser Title contains input data
	/// </summary>
	
	public boolean VerifyIsTitleContainsText(String inputData) throws IOException
	{
		if (UtilityFunctions.IsNullOrWhiteSpace(inputData))
		{
			LogFunctions.LogEntry("Incorrect Input data :"+ inputData,false);
			PropertiesAndConstants.scriptExecutionResult = "Fail";
			return false;
		}

		if (PropertiesAndConstants.Selenium.getTitle().toLowerCase().contains(inputData.toLowerCase()))
		{
			LogFunctions.LogEntry("Current browser Title Contains: -"+inputData+ "- Pass ",false);
			if (PropertiesAndConstants.scriptExecutionResult == "Fail")
			{ PropertiesAndConstants.scriptExecutionResult = "Fail"; }
			else PropertiesAndConstants.scriptExecutionResult = "Pass";
			return true;
		}
		else
		{
			LogFunctions.LogEntry("Current browser Title: Actual -"+PropertiesAndConstants.Selenium.getTitle()+"; Expected Contains: -"+inputData+" - Fail ",false);
			PropertiesAndConstants.scriptExecutionResult = "Fail";
			return false;
		}
	}
	/// <summary>
	/// Author            :  Ajit Thakur
	/// Description       :  Check that attribute for element contains a Value (format -> attrName=value)
	/// </summary>
	
	public boolean VerifyElementAttribute(String name, String locator, String inputData) throws Exception
	{
		if (UtilityFunctions.IsNullOrWhiteSpace(locator))
		{
			LogFunctions.LogEntry("Incorrect Locator :"+ locator,false);
			PropertiesAndConstants.scriptExecutionResult = "Fail";
			return false;
		}

		if (UtilityFunctions.IsNullOrWhiteSpace(inputData) || !inputData.contains("="))
		{
			LogFunctions.LogEntry("Incorrect Input Data :"+ inputData,false);
			PropertiesAndConstants.scriptExecutionResult = "Fail";
			return false;
		}

		inputData = inputData.trim().replace(" =", "=");
		String[] dataArray=inputData.split("=");
		// var dataArray = inputData.Split(new String[] { "=" }, StringSplitOptions.None);
		String attributeName = dataArray[0];
		String attributeValue = dataArray[1];

		WebElement element = WaitForElementPresent(name, locator);

		if (element != null)
		{
			String currentAttributeValue = element.getAttribute(attributeName);

			if (currentAttributeValue == null)
			{
				LogFunctions.LogEntry("Element"+name+" Doesn't Contain attribute"+attributeName+ "- Fail",false);
				PropertiesAndConstants.scriptExecutionResult = "Fail";
				return false;
			}

			if (currentAttributeValue.contains(attributeValue) ||
					currentAttributeValue.equals(attributeValue))
			{
				LogFunctions.LogEntry("Element"+name+" attribute"+attributeName+" Contains Value"+attributeValue+" - Pass",false);
				if (PropertiesAndConstants.scriptExecutionResult == "Fail")
				{ PropertiesAndConstants.scriptExecutionResult = "Fail"; }
				else PropertiesAndConstants.scriptExecutionResult = "Pass";
				return true;
			}
			else
			{
				LogFunctions.LogEntry("Element"+name+ "attribute"+attributeName+" Doesn't Contain Value"+attributeValue+" - Fail",false);
				PropertiesAndConstants.scriptExecutionResult = "Fail";
				return false;
			}
		}
		else
		{
			LogFunctions.LogEntry("Cannot Find element with locator :"+locator+" - Fail",false);
			PropertiesAndConstants.scriptExecutionResult = "Fail";
			return false;
		}
	}
	
	
	/// <summary>
	/// Author            :  Ajit Thakur
	/// Description       :  Check that attribute for element DOESN'T contain a Value (format -> attrName=value)
	/// </summary>
	
	public boolean VerifyIsElementNotContainsAttribute(String name, String locator, String inputData) throws Exception
	{
		if (UtilityFunctions.IsNullOrWhiteSpace(locator))
		{
			LogFunctions.LogEntry("Incorrect Locator :"+ locator,false);
			PropertiesAndConstants.scriptExecutionResult = "Fail";
			return false;
		}

		if (UtilityFunctions.IsNullOrWhiteSpace(inputData))
		{
			LogFunctions.LogEntry("Incorrect Input Data :"+ inputData,false);
			PropertiesAndConstants.scriptExecutionResult = "Fail";
			return false;
		}

		String attributeName = inputData.trim();

		WebElement element = WaitForElementPresent(name, locator);

		if (element != null)
		{

			if (UtilityFunctions.IsNullOrWhiteSpace(element.getAttribute(attributeName)))
			{
				LogFunctions.LogEntry("Element"+name+" doesn't Contain attribute"+attributeName+"  - Pass",false);
				if (PropertiesAndConstants.scriptExecutionResult == "Fail")
				{ PropertiesAndConstants.scriptExecutionResult = "Fail"; }
				else PropertiesAndConstants.scriptExecutionResult = "Pass";
				return true;
			}
			else
			{
				LogFunctions.LogEntry("Element"+name+" Contains attribute"+attributeName+"  - Fail",false);
				PropertiesAndConstants.scriptExecutionResult = "Fail";
				return false;
			}
		}
		else
		{
			LogFunctions.LogEntry("Cannot Find element with locator :"+locator+" - Fail",false);
			PropertiesAndConstants.scriptExecutionResult = "Fail";
			return false;
		}
	}
	
	
	
	/// <summary>
	/// Author            :  Ajit Thakur
	/// Description       : 
	/// </summary>
		
	public boolean VerifyAllLinksOnThePage() throws Exception
	{
		_links.clear();
		_linksCheckResult.clear();

		if (!UtilityFunctions.IsNullOrWhiteSpace(PropertiesAndConstants.Selenium.getPageSource()))
		{
			String pageUrl = PropertiesAndConstants.Selenium.getCurrentUrl();
			List<WebElement> linkElements = PropertiesAndConstants.Selenium.findElements(By.tagName("a"));
			int counter = 1;
			boolean isStepStatusPass = true;
			String driverTitle;

			if (linkElements != null)
			{
				for (WebElement element : linkElements)
				{
					String hrefValue = element.getAttribute("href");
					String locationKey = element.getLocation().toString().replace("{", "Link_").replace("}", "Number_" + counter).replace('=', '_');
					if (!UtilityFunctions.IsNullOrWhiteSpace(hrefValue) && hrefValue != "#") _links.put(locationKey, hrefValue);
					counter++;
				}

				counter = 1;

				for(String link : _links.keySet())
				{
					if (counter % 10 == 0)
					{
						Thread.sleep(3000);
					}

					String key = link;
					String valueHrefUrl = _links.get(link);

					try
					{
						PropertiesAndConstants.Selenium.navigate().to(valueHrefUrl);
						try
						{
							WaitForElementPresent("BodyElement", "html>body");
						}
						catch (WebDriverException wExc)
						{
							LogFunctions.LogEntry("Webdriver Timeout Exception: Wait for Body Element - " + wExc, false);
						}
						Thread.sleep(500);
						driverTitle = PropertiesAndConstants.Selenium.getTitle();
					}
					catch (WebDriverException exc)
					{
						LogFunctions.LogEntry("Exception in process of navigate to URL: " + valueHrefUrl, false);
						driverTitle = "404";
						SeleniumHandler.CloseSeleniumDriver();
						Thread.sleep(1500);
						SeleniumHandler.SwitchDriver();
						continue;
					}
					catch (Exception exc)
					{
						LogFunctions.LogEntry("Unexpected exception in process of navigate to URL: " + valueHrefUrl, false);
						driverTitle = "404";
						SeleniumHandler.CloseSeleniumDriver();
						Thread.sleep(1500);
						SeleniumHandler.SwitchDriver();
						continue;
					}

					if (PropertiesAndConstants.Selenium.getWindowHandles().size() > 1)
					{
						for (String openedBrouserWindowHandles : PropertiesAndConstants.Selenium.getWindowHandles())
						{
							if (openedBrouserWindowHandles != PropertiesAndConstants.MainDriverWindowHandle)
							{
								PropertiesAndConstants.Selenium.switchTo().window(openedBrouserWindowHandles).close();
								Thread.sleep(250);
							}
						}
						PropertiesAndConstants.Selenium.switchTo().window(PropertiesAndConstants.MainDriverWindowHandle);
					}

					if (!driverTitle.contains("404") && !driverTitle.contains("not found") && !driverTitle.contains("Runtime Error")
							&& !driverTitle.contains("Problem loading") && !driverTitle.contains("Whoops") && !driverTitle.contains("Google Search"))
					{
						LogFunctions.LogEntry("Link href attribute"+valueHrefUrl+" for element location"+key+" is Valid - Pass", false);
						if (PropertiesAndConstants.scriptExecutionResult == "Fail")
						{ PropertiesAndConstants.scriptExecutionResult = "Fail"; }
						else PropertiesAndConstants.scriptExecutionResult = "Pass";
						counter++;
						continue;
					}
					else
					{
						LogFunctions.LogEntry("Link Href attribute Incorrect - Source"+valueHrefUrl+" with location"+key+ "Not Found - Fail ", false);
						LogFunctions.LogEntry("Actual Title:"+ driverTitle, false);
						PropertiesAndConstants.scriptExecutionResult = "Fail";
						isStepStatusPass = false;
						counter++;
						continue;
					}
				}

				if (!isStepStatusPass)
				{
					NavigateToUrl(pageUrl);
					PropertiesAndConstants.scriptExecutionResult = "Fail";
					LogFunctions.LogEntry("Page Contains Incorrect Links! - Fail ",false);
					return false;
				}
				else
				{
					NavigateToUrl(pageUrl);
					LogFunctions.LogEntry("All Links are Valid - Pass ",false);
					return true;
				}
			}
			else
			{
				LogFunctions.LogEntry("Cannot find any element with tag a - Fail ",false);
				PropertiesAndConstants.scriptExecutionResult = "Fail";
				return false;
			}
		}
		else
		{
			LogFunctions.LogEntry("Page Source is Empty  - Fail ",false);
			PropertiesAndConstants.scriptExecutionResult = "Fail";
			return false;
		}
	}		
		/// <summary>
		/// Author            :  Ajit Thakur
		/// Description       :  Web Driver Find elements if element not Unique
		/// </summary>	
	
	private List<WebElement> FindElements(String locator) throws Exception
	{
		List<WebElement> elements = null;

		if (UtilityFunctions.IsNullOrWhiteSpace(locator))
		{
			LogFunctions.LogEntry("Incorrect Locator :"+ locator, false);
			return null;
		}

		if ((locator.contains("#") || locator.contains(".") || locator.contains(">") || locator.contains("~") ||
				locator.contains("+") || locator.contains("*") || locator.contains("$") ||
				locator.contains("^") || locator.contains(" ") || locator.contains("[")) && (!locator.contains("/") && !locator.contains("@")))
		{
			try // by CSS
			{
				PropertiesAndConstants.Selenium.manage().timeouts().implicitlyWait(10000,TimeUnit.MILLISECONDS);
				elements = PropertiesAndConstants.Selenium.findElements(By.cssSelector(locator));
				PropertiesAndConstants.Selenium.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
				return elements;
			}
			catch (NoSuchElementException exc) { LogFunctions.LogEntry("FindByCSS - Fail", false); } //{ Utilities.LogEntry("Find Element By CSS - Fail. Reason: " + noElementExc , false); }
			// catch (IllegalLocatorException exc) { } // { Utilities.LogEntry("Find Element By CSS - Fail. Reason: " + illegalLocatorExc, false); }
			//  catch (InvalidSelectorException exc) { } //{ Utilities.LogEntry("Find Element By CSS - Fail. Reason: " + invalidSelectorExc, false); }
			//  catch (InvalidElementStateException) { } // { Utilities.LogEntry("Find Element By CSS - Fail. Reason: " + invalidElementStateExc, false); }
		}

		if ((locator.contains("/") || locator.contains("contains") || locator.contains("text()") || locator.contains("@") || locator.contains("*") || locator.contains("////") ||
				locator.contains("////") || locator.contains("[") || locator.contains("]")) && (!locator.contains("#") && !locator.contains(">")))
		{
			try // by XPATH
			{
				PropertiesAndConstants.Selenium.manage().timeouts().implicitlyWait(10000,TimeUnit.MILLISECONDS);
				elements = PropertiesAndConstants.Selenium.findElements(By.xpath(locator));
				PropertiesAndConstants.Selenium.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
				return elements;
			}
			catch (NoSuchElementException exc) { LogFunctions.LogEntry("FindByXPATH - Fail", false); }//{ Utilities.LogEntry("Find Element By XPATH - Fail. Reason: " + noElementExc, false); }
			// catch (IllegalLocatorException) { } //{ Utilities.LogEntry("Find Element By XPATH - Fail. Reason: " + illegalLocatorExc, false); }
			// catch (InvalidSelectorException) { } //{ Utilities.LogEntry("Find Element By XPATH - Fail. Reason: " + invalidSelectorExc, false); }
			// catch (InvalidElementStateException) { } //{ Utilities.LogEntry("Find Element By XPATH - Fail. Reason: " + invalidElementStateExc, false); }
		}

		if (!locator.contains("#") && !locator.contains(".") && !locator.contains(">") && !locator.contains("(") &&
				!locator.contains(")") && !locator.contains("/") && !locator.contains("@") && !locator.contains("*") &&
				!locator.contains("$") && !locator.contains("^") && !locator.contains(" ") && !locator.contains("+"))
		{
			try // by Id
			{
				PropertiesAndConstants.Selenium.manage().timeouts().implicitlyWait(3000,TimeUnit.MILLISECONDS);
				elements = PropertiesAndConstants.Selenium.findElements(By.id(locator));
				PropertiesAndConstants.Selenium.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
				return elements;
			}
			catch (NoSuchElementException exc) { LogFunctions.LogEntry("FindById - Fail", false); }
			//  catch (IllegalLocatorException) { }
			// catch (InvalidSelectorException) { }
			//  catch (InvalidElementStateException) { }

			try // by ClassName
			{
				PropertiesAndConstants.Selenium.manage().timeouts().implicitlyWait(3000,TimeUnit.MILLISECONDS);
				elements = PropertiesAndConstants.Selenium.findElements(By.className(locator));
				PropertiesAndConstants.Selenium.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
				return elements;
			}
			catch (NoSuchElementException exc) { LogFunctions.LogEntry("FindByClassName - Fail", false); }
			//  catch (IllegalLocatorException) { }
			//   catch (InvalidSelectorException) { }
			//  catch (InvalidElementStateException) { }
		}

		if (!locator.contains("#") && !locator.contains(">") && !locator.contains(".") && !locator.contains("(") &&
				!locator.contains(")") && !locator.contains("/") && !locator.contains("@") && !locator.contains("*") &&
				!locator.contains("$") && !locator.contains("^") || locator.contains(" "))
		{
			try // by LinkText
			{
				PropertiesAndConstants.Selenium.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
				elements = PropertiesAndConstants.Selenium.findElements(By.linkText(locator));
				
				return elements;
			}
			catch (Exception exc) { LogFunctions.LogEntry("FindByLinkText - Fail", false); }
			// catch (IllegalLocatorException) { }
			// catch (InvalidSelectorException) { }
			// catch (InvalidElementStateException) { }
		}
		// ***** Need to Add Fail code for Test Result
		return null;
	}
	/// <summary>
	/// Author            :  Ajit Thakur
	/// Description       : Select Element from select node using Text (without Frames)
	/// </summary>	
	
	private boolean SelectElementByText(String name, String locator, String text) throws Exception
	{
		if (UtilityFunctions.IsNullOrWhiteSpace(locator))
		{
			LogFunctions.LogEntry("Select item from :"+name+" - Fail - Incorrect Locator :"+ locator,false);
			return false;
		}

		if (UtilityFunctions.IsNullOrWhiteSpace(text))
		{
			LogFunctions.LogEntry("Select item from \"" +name+ "\"- Fail - Incorrect/Null Value for Select",false);
			return false;
		}
		
		

		WebElement selectnode = WaitForElementPresent(name, locator);

		if (selectnode != null)
		{
			Select select=new Select(selectnode);
			select.selectByVisibleText(text);
			LogFunctions.LogEntry("Select element from: \""+name+"\" - Pass",false);
			return true;
		}
		else
		{
			LogFunctions.LogEntry("Cannot select element from : \""+name+"\" - Fail",false);
			return false;
		}
	}

	
	/// <summary>
	/// Author            :  Ajit Thakur
	/// Description       : Select Element from select node using Text (without Frames)
	/// </summary>	
	
	public boolean VerifyBackgroundImageForElement(String name, String locator) throws Exception
	{
		String backgroundImageAttribute = "EMPTY";
		if (UtilityFunctions.IsNullOrWhiteSpace(locator))
		{
			LogFunctions.LogEntry("Incorrect Locator - Fail",false);
			return false;
		}

		WebElement element = WaitForElementPresent(name, locator);

		if (element != null)
		{
			backgroundImageAttribute = element.getCssValue("background-image");
			if (!UtilityFunctions.IsNullOrEmpty(backgroundImageAttribute))
			{
				backgroundImageAttribute = backgroundImageAttribute.replace("url(\"", " ").replace("\")", " ").trim();
				PropertiesAndConstants.Selenium.navigate().to(backgroundImageAttribute);
				String driverTitle = PropertiesAndConstants.Selenium.getTitle();
				PropertiesAndConstants.Selenium.navigate().back();

				if (!driverTitle.contains("404") && !driverTitle.contains("not found") && !driverTitle.contains("Runtime Error")
						&& !driverTitle.contains("Problem loading"))
				{
					LogFunctions.LogEntry("Background Image css attribute"+backgroundImageAttribute+" is Valid - Pass",false);
					if (PropertiesAndConstants.scriptExecutionResult == "Fail")
					{ PropertiesAndConstants.scriptExecutionResult = "Fail"; }
					else PropertiesAndConstants.scriptExecutionResult = "Pass";
					return true;
				}
				else
				{
					LogFunctions.LogEntry("Background Image css attribute Incorrect -Image"+backgroundImageAttribute+" Not Found - Fail ",false);
					PropertiesAndConstants.scriptExecutionResult = "Fail";
					return false;
				}
			}
			else
			{
				LogFunctions.LogEntry("Background Image css attribute"+backgroundImageAttribute+" is Null or Empty - Fail",false);
				PropertiesAndConstants.scriptExecutionResult = "Fail";
				return false;
			}
		}
		else
		{
			LogFunctions.LogEntry("Cannot get Css attribute"+backgroundImageAttribute+ "for element"+name+" with locator"+locator+" - Fail",false);
			PropertiesAndConstants.scriptExecutionResult = "Fail";
			return false;
		}
	}	
	/// <summary>
	/// Author            :  Ajit Thakur
	/// Description       : Set Css property display:block for Element
	/// </summary>	
		
	public boolean ShowHiddenElement(String name, String locator, String frames)throws Exception
	{
		if (UtilityFunctions.IsNullOrWhiteSpace(locator))
		{
			LogFunctions.LogEntry("Incorrect Locator : \""+locator+"\"",false);
			PropertiesAndConstants.scriptExecutionResult = "Fail";
			return false;
		}

		if (!UtilityFunctions.IsNullOrWhiteSpace(frames))
		{
			WebDriverWait framewait = new WebDriverWait(PropertiesAndConstants.Selenium,30);
			SwitchToDefaultContent(frames);
			String[] framesArray = frames.split("||"); 
			int x=0;
			LinkedList<String> list=new LinkedList<String>();
			String[] frameArray1=new String[list.size()];
			for(int index=0;index<framesArray.length;index++)
			{
				if(framesArray[index].length()!=0 )
				{
					list.add(framesArray[index]);
					x++;

				}
			}
			for(int j=0;j<list.size();j++)
			{
				frameArray1[j]=list.get(j); 
			}

			// var framesArray = frames.Split(new String[] { "||" }, StringSplitOptions.RemoveEmptyEntries);
			for (int i = 0; i < frameArray1.length; i++)
			{
				if (FindElement(framesArray[i]) != null)
				{
					LogFunctions.LogEntry("Frame Element with locator"+framesArray[i]+" FOUND!!! ", false);
					PropertiesAndConstants.Selenium.switchTo().frame(FindElement(framesArray[i]));
					//  framewait.Until<WebElement>( PropertiesAndConstants.Selenium.FindElement(By.tagName("body"))));
					framewait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
				}
				else
				{
					LogFunctions.LogEntry("CANNOT FOUND Frame Element with locator"+framesArray[i]+" !!! ", false);
					return false;
				}
			}
		}
		
		
		WebElement element = WaitForElementPresent(name, locator);
		
		if (element != null)
		{
			UtilityFunctions.ExecuteScript(String.format("arguments[0].style.display='block';", element));
			LogFunctions.LogEntry("Set 'display' property as 'block' - Pass",false);
			return true;
		}
		else
		{
			LogFunctions.LogEntry("Cannot set 'display' property - Fail",false);
			return false;
		}
	}		
	/// <summary>
	/// Author            :  Ajit Thakur
	/// Description       : Context Click (Right Button Mouse Click without entering in Frames)
	/// </summary>	
	
	private boolean RightClickElement(String name, String locator) throws Exception
	{
		if (UtilityFunctions.IsNullOrWhiteSpace(locator))
		{
			LogFunctions.LogEntry("Right Click button : \""+name+"\" - Fail - Incorrect Locator : \""+locator+"\"",false);
			return false;
		}

		WebElement element = WaitForElementPresent(name, locator);

		if (element != null)
		{
			Actions actionRightClick = new Actions(PropertiesAndConstants.Selenium);
			Actions actionMove = new Actions(PropertiesAndConstants.Selenium);
			actionMove.moveToElement(element).perform();
			actionRightClick.contextClick(element).perform();
			LogFunctions.LogEntry("Right Click button : \""+name+"\" - Pass",false);
			return true;
		}
		else
		{
			LogFunctions.LogEntry("Cannot find element for Right Click - Fail",false);
			return false;
		}

	}
	/// <summary>
	/// Author            :  Ajit Thakur
	/// Description       : Verify that page source DOESN'T contain text
	/// </summary>	
	
    public boolean VerifyIsTextNotPresent(String inputData) throws Exception
    {
    	if (UtilityFunctions.IsNullOrWhiteSpace(inputData))
        {
            LogFunctions.LogEntry("Incorrect Input Data : \""+inputData+"\"",false);
            PropertiesAndConstants.scriptExecutionResult = "Fail";
            return false;
        }
        if (!PropertiesAndConstants.Selenium.getPageSource().contains(inputData))
        {
            LogFunctions.LogEntry("Text :"+inputData+" NOT Present : - Pass",false);
            if (PropertiesAndConstants.scriptExecutionResult == "Fail")
            { PropertiesAndConstants.scriptExecutionResult = "Fail"; }
            else PropertiesAndConstants.scriptExecutionResult = "Pass";
            return true;
        }
        else
        {
            LogFunctions.LogEntry("Text :"+inputData+" Present : - Fail",false);
            PropertiesAndConstants.scriptExecutionResult = "Fail";
            return false;
        }
    }			
	public static void SetVariableValue(String keyName, String value) throws IOException
    {
        if (!PropertiesAndConstants.varDataDictionary.containsKey(keyName))
        {
       	 PropertiesAndConstants.varDataDictionary.put(keyName, value);

            LogFunctions.LogEntry("Pass - Variable - set with the value - "+keyName+"=> STEP ID : "+value+" ", false);
        }
        else
       	 LogFunctions.LogEntry("Fail - Variable already exist in the list - "+keyName, false);
    }

	public static String GetVariableValue(String keyName) throws IOException
    {
		System.out.println(keyName);
		if (PropertiesAndConstants.varDataDictionary.get(keyName).isEmpty())
		{
        	LogFunctions.LogEntry("Fail - Variable value for "+keyName+ " not found", false);
        	return "";
		}
		else
        {
        	LogFunctions.LogEntry("Pass - Variable value for "+keyName+ " found", false);
        	return PropertiesAndConstants.varDataDictionary.get(keyName);
        }
    }
	
	public <E extends Enum<E>> boolean isInEnum(String value, Class<E> enumClass) {
		  for (E e : enumClass.getEnumConstants()) {
		    if(e.name().equals(value)) { return true; }
		  }
		  return false;
		}
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	 /// <summary>
	   /// Author            :  Ajit Thakur
	   /// Description       : meathod used to store value or attribute.
	   ///String name    : (mandatory) name of object 
	   ///String locator   : (mandatory) real object
	   ///String VAR_Name    : (Mandatory) Nmae of the Variable user defined
	   public boolean storeMultipleRandomValue(String name, String locator, String frames, String value)throws Exception
	   { 
		   System.out.println("inside function");
	    if (UtilityFunctions.IsNullOrWhiteSpace(locator))
	    { 
	    	System.out.println("inside null if");
	     LogFunctions.LogEntry("Type text into :"+name+" - Fail - Incorrect Locator :"+ locator,false);
	     PropertiesAndConstants.scriptExecutionResult = "Fail";
	     return false;
	    }
	    if (!UtilityFunctions.IsNullOrWhiteSpace(frames)) 
	    {
	    	 System.out.println("inside frames");
	     SwitchToFrames(frames);
	     System.out.println("inside frames");
	    }
	    
	    WebElement element = WaitForElementPresent(name, locator);
	    if (element != null && element.isDisplayed())
	    { 
	    	System.out.println("ele displayed");
	     _dynamicVariables.put(value, element.getAttribute("value"));
	      LogFunctions.LogEntry("Value stored in variable "+value+" : "+_dynamicVariables.get(value)+" - Pass",false);
	      SwitchToDefaultContent(frames);
	     return true;
	      
	    }//End of if element
	    else
	    {
	    	System.out.println("insisde IF");
	       LogFunctions.LogEntry("Store values :"+" - Fail",false);
	       PropertiesAndConstants.scriptExecutionResult = "Fail";
	       SwitchToDefaultContent(frames);
	       return false;
	    } 
	   }//End of Function
	   
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++	   
	   
	    /// Author            : Ajit Thakur 
	    /// Description       : meathod used to store value or attribute.
	    ///String name    : (mandatory) name of object 
	    ///String locator   : (mandatory) real object
	    ///String VAR_Name    : (Mandatory) Nmae of the Variable user defined
	   public boolean compareMultipleRandomValues(String name, String locator, String frames, String value)throws Exception
	   {
	      if (UtilityFunctions.IsNullOrWhiteSpace(locator))
	      { 
	       LogFunctions.LogEntry("Type text into :"+name+" - Fail - Incorrect Locator :"+ locator,false);
	       PropertiesAndConstants.scriptExecutionResult = "Fail";
	       return false;
	      }
	      if (!UtilityFunctions.IsNullOrWhiteSpace(frames)) 
	      {
	       SwitchToFrames(frames);
	      }
	      
	      WebElement element = WaitForElementPresent(name, locator);
	      if (element != null && element.isDisplayed())
	      { 
	     if(_dynamicVariables.get(value).equals(element.getText()))
	     {
	      LogFunctions.LogEntry("Dynamic text: "+_dynamicVariables.get(value) +" = "+"Runtime text: "+element.getText()+" - Pass",true);
	      SwitchToDefaultContent(frames);
	      return true;
	     }
	     else
	     {
	      LogFunctions.LogEntry("Strings value do not match"+" - Fail",false);
	         PropertiesAndConstants.scriptExecutionResult = "Fail";
	         SwitchToDefaultContent(frames);
	         return false;
	     }
	       
	       //return true;
	       
	       
	      }//End of if element   
	      else
	      {
	        LogFunctions.LogEntry("Strings value do not match"+" - Fail",false);
	        PropertiesAndConstants.scriptExecutionResult = "Fail";
	        return false;
	      }
	     
	   }//End of function
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++	
	 //Ajit Thakur
	 public boolean compareRandomValuesNotEqual(String name, String locator, String frames)throws Exception
	 {
		 String variable1 = "getValue";
		 String variable2;
		  String var1 = _dynamicVariables.get(variable1);
		  var1 = var1.trim();
			 
			  if (UtilityFunctions.IsNullOrWhiteSpace(locator))
			  { 
			   LogFunctions.LogEntry("Type text into :"+name+" - Fail - Incorrect Locator :"+ locator,false);
			   PropertiesAndConstants.scriptExecutionResult = "Fail";
			   return false;
			  }
			  if (!UtilityFunctions.IsNullOrWhiteSpace(frames)) 
			  {
			   SwitchToFrames(frames);
			  }
			  
			  WebElement element = WaitForElementPresent(name, locator);
			  if (element != null && element.isDisplayed())
			  { 
				  variable2 = element.getText();
				//if(_dynamicVariables.get(variable1).equals(variable2))
				  if(!_dynamicVariables.get(variable1).equals(variable2))
				{
					LogFunctions.LogEntry("Strings value do not match  - Pass",false);
					SwitchToDefaultContent(frames);
					return true;
				}
				else
				{
					LogFunctions.LogEntry("Strings value same as before"+" - Fail",false);
				    PropertiesAndConstants.scriptExecutionResult = "Fail";
				    SwitchToDefaultContent(frames);
				    return false;
				}
			   //return true;
			  }//End of if element	  
			  else
			  {
			    LogFunctions.LogEntry("Strings value do not match"+" - Fail",false);
			    PropertiesAndConstants.scriptExecutionResult = "Fail";
			    return false;
			  }
		  
	 }//End of function

//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ 
/// <summary>
/// Author            :  Ajit Thakur
/// Description       : Select Element from select node using Text (without Frames)
/// </summary>
	
	@SuppressWarnings("finally")
	public void ProcessScriptTemplate(Workbook testScriptPath) throws Exception, IllegalArgumentException 
	{
		_result=false;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date now = new Date(); 
		Sheet testScriptSheet=testScriptPath.getSheet(0);
		String resultsToExcel = ConfigFunctions.getEnvKeyValue("RESULTSTOEXCEL");
		int startRow = 0;
		int rowcount_script = testScriptSheet.getRows();
		do{	startRow++;}
		while (!((String)(testScriptSheet.getCell(0,startRow).getContents()) ).equals("StepId") );
		startRow++;
    	int rowcount =startRow;
	    for(int row=startRow;row<=rowcount_script;row++)
	    {
	    	boolean result=((String)(testScriptSheet.getCell(0,row).getContents())).contains("Step");
	    	if(result==true)
	    	{
	    		 rowcount++;
	    	}
	    	else
	    	{
	    		break;
	    	}
	    }
		for (int rowID = startRow; rowID <= rowcount-1; rowID++)
		{
			if (PropertiesAndConstants.FailStepsPerScriptCounter > PropertiesAndConstants.FailStepsCountLimit) break; // Should be Configurable
			
			String keyActionName = (String)(testScriptSheet.getCell(2,rowID).getContents());
			PropertiesAndConstants.ResultString = "No log report";
			if (keyActionName.equals(null)&&keyActionName.equals(""))
			{
				LogFunctions.LogEntry("Processing test script was completed...", false);

				break;
			}
            String stepID = (String)(testScriptSheet.getCell(0,rowID).getContents());
            System.out.println("                      ");
            System.out.println("Step      : "+stepID);
            String StepDescription =(String)(testScriptSheet.getCell(1,rowID).getContents());
            System.out.println("Action    : "+StepDescription);
			String objName = (String)(testScriptSheet.getCell(3,rowID).getContents());
			System.out.println("objName   : "+objName);
			System.out.println("keyAction : "+keyActionName);
			String inputData = (String)(testScriptSheet.getCell(4,rowID).getContents());
			System.out.println("Result    : "+PropertiesAndConstants.scriptExecutionResult);
//			if(PropertiesAndConstants.isTestDataFileExist)
//			{
//				System.out.println("inside if 88888888888");
//				inputData = PropertiesAndConstants.testDataDictionary.get(inputData);
//			}
//			if(inputData.contains("VAR_") && !(keyActionName.trim().equals("GetRandomValue"))&&!(keyActionName.trim().equals("CreateUmbracoNode")))
//			{
//				System.out.println("inside if 9999999999");
//				inputData = GetVariableValue(inputData);
//			}
		//	String additionalParameters = (String)(testScriptSheet.getCell(rowID, 9).getContents()); // Currently not used
			// ***************************************************
			String usrObjName = objName;
	        String actObjName = ORFunctions.GetObjRepositoryKeyValue(usrObjName).trim();
			String frameObj =   ORFunctions.GetObjFrameValue(usrObjName).trim();
			// ***** Additional Verify for Start Page
			PropertiesAndConstants.IsStepSkip = false;
			PropertiesAndConstants.TempScreenshotPath = null;
			PropertiesAndConstants.TempPageSourcePath = null;
		    LogFunctions.LogEntry("Step ID :--------- "+ stepID,false);
			if (isInEnum(keyActionName, KeyAction.class)) {
				KeyAction keyActionname = KeyAction.valueOf(keyActionName);
				try // Switch Wrapper
				{
					switch (keyActionname) {
		case DisableFlash:
				{
					_result = disableFlash();
					if (!_result)
					{
						PropertiesAndConstants.IsTestExecutionPassed = false;
						
					}
					break;
				}
				
		case scrollToTop:
        {
             Thread.sleep(10000);
             _result= scrollToTop(usrObjName, actObjName);
             if (!_result)
                 {
                      PropertiesAndConstants.IsTestExecutionPassed = false;
                      try
                      { 
                           PropertiesAndConstants.TempScreenshotPath =  LogFunctions.TakeScreenshot(keyActionName); 
                      }
                      finally 
                      {break;}
                 }
                 break;
        }
				case ScrollToBottom:
                {
                     Thread.sleep(10000);
                     _result= scrollToBottom(usrObjName, actObjName);
                     if (!_result)
                         {
                              PropertiesAndConstants.IsTestExecutionPassed = false;
                              try
                              { 
                                   PropertiesAndConstants.TempScreenshotPath =  LogFunctions.TakeScreenshot(keyActionName); 
                              }
                              finally 
                              {break;}
                         }
                         break;
                }
				case NavigateToURL:
				{
					_result = NavigateToUrl(inputData);
					if (!_result)
					{
						PropertiesAndConstants.IsTestExecutionPassed = false;
						try { PropertiesAndConstants.TempScreenshotPath = LogFunctions.TakeScreenshot(keyActionName); }
						finally { break; }
					}
					break;
				}
				
				case Click:
				{
					_result = clickInFrame(usrObjName, actObjName, frameObj);
					Thread.sleep(250); // Templorary Parameter
					if (!_result)
					{
						PropertiesAndConstants.IsTestExecutionPassed = false;
						try { PropertiesAndConstants.TempScreenshotPath =LogFunctions.TakeScreenshot(keyActionName); }
						finally { break; }
					}
					break;
				}
											
				case ClickSafeIfPresent:
				{
					_result = ClickSafeIfPresent(usrObjName, actObjName, frameObj);
					Thread.sleep(250); // Templorary Parameter
					if (!_result)
					{
						PropertiesAndConstants.IsTestExecutionPassed = false;
						try { PropertiesAndConstants.TempScreenshotPath =  LogFunctions.TakeScreenshot(keyActionName); }
						finally { break; }
					}
					break;
				}
				case JavaScriptClick:
				{
					_result = JavaScriptClick(usrObjName, actObjName, frameObj);
					Thread.sleep(250); // Templorary Parameter
					if (!_result)
					{
						PropertiesAndConstants.IsTestExecutionPassed = false;
						try { PropertiesAndConstants.TempScreenshotPath =  LogFunctions.TakeScreenshot(keyActionName); }
						finally { break; }
					}
					break;
				}
				
				case CheckBoxUnCheck:

                {

                        _result = CheckBoxUnCheck(usrObjName, actObjName, frameObj);

                        if (!_result)

                        {

                            PropertiesAndConstants.IsTestExecutionPassed = false;

                            try { PropertiesAndConstants.TempScreenshotPath =   LogFunctions.TakeScreenshot(keyActionName); }

                            finally { break; }
                        }

                        break;

                }

				case CheckBoxCheck:
				{
					_result = CheckBoxCheck(usrObjName, actObjName, frameObj);
					if (!_result)
					{
						PropertiesAndConstants.IsTestExecutionPassed = false;
						try { PropertiesAndConstants.TempScreenshotPath =   LogFunctions.TakeScreenshot(keyActionName); }
						finally { break; }
					}
					break;
				}

				case DoubleClickElement:
				{
					_result = DoubleClickElement(usrObjName, actObjName, frameObj);
					if (!_result)
					{
						PropertiesAndConstants.IsTestExecutionPassed = false;
						try { PropertiesAndConstants.TempScreenshotPath =    LogFunctions.TakeScreenshot(keyActionName); }
						finally { break; }
					}
					break;
				}
				case RightClickElement:
				{
					_result = RightClickElement(usrObjName, actObjName, frameObj);
					if (!_result)
					{
						PropertiesAndConstants.IsTestExecutionPassed = false;
						try { PropertiesAndConstants.TempScreenshotPath =    LogFunctions.TakeScreenshot(keyActionName); }
						finally { break; }
					}
					break;
				}
				case TypeText:
				{
					_result = InputTextElement(usrObjName, actObjName, frameObj, inputData);
					//Thread.Sleep(250);
					if (!_result)
						
					{
						System.out.println("Ajit Thakur................");
						PropertiesAndConstants.IsTestExecutionPassed = false;
						try { 
							PropertiesAndConstants.TempScreenshotPath = LogFunctions.TakeScreenshot(keyActionName);
							
							
							
						}
						finally { 
							break; }
					}
					break;
				}
				case verifyprerequistites:
				{
					_result =verifyprerequistites(objName, actObjName,inputData);
					//Thread.Sleep(250);
					if (!_result)
					{
						PropertiesAndConstants.IsTestExecutionPassed = false;
						try { 
							PropertiesAndConstants.TempScreenshotPath = LogFunctions.TakeScreenshot(keyActionName);
								
						}
						finally { 
							break; }
					}
					break;
				}
				case EnterTexts:
				{
					_result = InputTextElement1(usrObjName, actObjName, frameObj, inputData);
					//Thread.Sleep(250);
					if (!_result)
					{
						PropertiesAndConstants.IsTestExecutionPassed = false;
						try { 
							PropertiesAndConstants.TempScreenshotPath = LogFunctions.TakeScreenshot(keyActionName);
							
							
							
						}
						finally { 
							break; }
					}
					break;
					
				}
				case SelectElementByText:
				{
					_result=SelectElementByText(usrObjName, actObjName, frameObj, inputData);
					if(_result)
					{
						PropertiesAndConstants.IsTestExecutionPassed = false;
						try { PropertiesAndConstants.TempScreenshotPath = LogFunctions.TakeScreenshot(keyActionName); }
						finally { break; }
					}
					break;
				}    

				case OpenNewTab:
				{
					_result = OpenNewTabOrWindow(usrObjName, actObjName, frameObj);
					if (!_result)
					{
						PropertiesAndConstants.IsTestExecutionPassed = false;
						try { PropertiesAndConstants.TempScreenshotPath =LogFunctions.TakeScreenshot(keyActionName); }
						finally { break; }
					}
					break;
				}
				case SwitchToNewWindow:
				{
					_result = SwitchToNewWindow();
					if (!_result)
					{
						PropertiesAndConstants.IsTestExecutionPassed = false;
						try { PropertiesAndConstants.TempScreenshotPath =LogFunctions.TakeScreenshot(keyActionName); }
						finally { break; }
					}
					break;
				}
			
				case OpenNewTabOrWindowByJavaScript:
				{
					_result = OpenNewTabOrWindowByJavaScript(usrObjName, actObjName, frameObj);
					if (_result)
					{
						PropertiesAndConstants.IsTestExecutionPassed = false;
						try { PropertiesAndConstants.TempScreenshotPath =    LogFunctions.TakeScreenshot(keyActionName); }
						finally { break; }
					}
					break;
				}
				case CloseAdditionalTab:
				{
					_result = CloseAdditionalTabOrWindow();
					if (!_result)
					{
						PropertiesAndConstants.IsTestExecutionPassed = false;
						try { PropertiesAndConstants.TempScreenshotPath =   LogFunctions.TakeScreenshot(keyActionName); }
						finally { break; }
					}
					break;
				}
				case CloseMainTab:
				{
					_result = CloseMainTabOrWindow();
					if (!_result)
					{
						PropertiesAndConstants.IsTestExecutionPassed = false;
						try { PropertiesAndConstants.TempScreenshotPath =    LogFunctions.TakeScreenshot(keyActionName); }
						finally { break; }
					}
					break;
				}
				case SwitchToMainTab:
				{
					_result = SwitchToMainTab();
					if (!_result)
					{
						PropertiesAndConstants.IsTestExecutionPassed = false;
						try { PropertiesAndConstants.TempScreenshotPath =   LogFunctions.TakeScreenshot(keyActionName); }
						finally { break; }
					}
					break;
				}
				case SwitchToAdditionalTab:
				{
					_result = SwitchToAdditionalTab();
					if (!_result)
					{
						PropertiesAndConstants.IsTestExecutionPassed = false;
						try { PropertiesAndConstants.TempScreenshotPath =    LogFunctions.TakeScreenshot(keyActionName); }
						finally { break; }
					}
					break;
				}
				case ReturnToPreviousPage:
				{
					_result = ReturnToPreviousPage();
					if (!_result)
					{
						PropertiesAndConstants.IsTestExecutionPassed = false;
						try { PropertiesAndConstants.TempScreenshotPath =    LogFunctions.TakeScreenshot(keyActionName); }
						finally { break; }
					}
					break;

				}
				case RefreshPage:
				{
					_result = RefreshPage();
					if (!_result)
					{
						PropertiesAndConstants.IsTestExecutionPassed = false;
						try { PropertiesAndConstants.TempScreenshotPath = LogFunctions.TakeScreenshot(keyActionName); }
						finally { break; }
					}
					break;
				}
				case AlertAccept:
				{
					_result = AlertAccept();
					if (!_result)
					{
						PropertiesAndConstants.IsTestExecutionPassed = false;
						try { PropertiesAndConstants.TempScreenshotPath =  LogFunctions.TakeScreenshot(keyActionName); }
						finally { break; }
					}
					break;
				}
				case ElementMouseOver:
				{
					//result = driveractions.
							if (!_result)
							{
								PropertiesAndConstants.IsTestExecutionPassed = false;
								try { PropertiesAndConstants.TempScreenshotPath =    LogFunctions.TakeScreenshot(keyActionName); }
								finally { break; }
							}
					break;
				}
				case MoveToElement:
				{
					_result =MoveToElement(usrObjName, actObjName, frameObj);
					if (!_result)
					{
						PropertiesAndConstants.IsTestExecutionPassed = false;
						try { PropertiesAndConstants.TempScreenshotPath =  LogFunctions.TakeScreenshot(keyActionName); }
						finally { break; }
					}
					break;
				}
				case MoveMouseToElement:
				{
					_result=MoveMouseToElement(usrObjName, actObjName, frameObj);
					if (!_result)
					{
						PropertiesAndConstants.IsTestExecutionPassed = false;
						try { PropertiesAndConstants.TempScreenshotPath = LogFunctions.TakeScreenshot(keyActionName); }
						finally { break; }
					}
					break;
				}
				case VerifyIsElementPresentAndDisplayed:
				{
					_result=VerifyIsElementPresentAndDisplayed(usrObjName, actObjName, frameObj);
					if (!_result)
					{
						PropertiesAndConstants.IsTestExecutionPassed = false;
						try { PropertiesAndConstants.TempScreenshotPath =LogFunctions.TakeScreenshot(keyActionName); }
						finally { break; }
					}
					break;
				}

				case VerifyIsURLContainsText:
				{
					_result = VerifyIsPageURLContainsText(inputData);
					if (!_result)
					{
						PropertiesAndConstants.IsTestExecutionPassed = false;
					}
					break;
				}
				case VerifyIsURLNotContainsText:
				{
					_result=VerifyIsPageURLNotContainsText(inputData);
					if (!_result)
					{
						PropertiesAndConstants.IsTestExecutionPassed = false;
					}
					break;
				}				
				case KeyboardAction:
				{
					_result=KeyboardAction(usrObjName, actObjName);
					if (!_result)
					{
						PropertiesAndConstants.IsTestExecutionPassed = false;
					}
					break;
				}				
				case VerifyIsTitleContainsText:
				{

					if (!_result)
					{
						PropertiesAndConstants.IsTestExecutionPassed = false;
					}
					break;
				}
				case VerifyIsTitleNotContainsText:
				{
					VerifyIsTitleNotContainsText(inputData);
					if (!_result)
					{
						PropertiesAndConstants.IsTestExecutionPassed = false;
					}
					break;
				}
				case TypeTextFromConfig:
		        {
		          String inputdataConfig=ConfigFunctions.getEnvKeyValue(inputData);
		          _result = InputTextElement(usrObjName, actObjName, frameObj, inputdataConfig);
		          //Thread.Sleep(250);
		          if (!_result)
		          {
		            PropertiesAndConstants.IsTestExecutionPassed = false;
		            try { PropertiesAndConstants.TempScreenshotPath =   LogFunctions.TakeScreenshot(keyActionName); }
		            finally {break; }
		          }
		          break;
		        }
				case VerifyIsAlertPresent:
				{
					_result=VerifyIsAlertPresent();
					{
						if (!_result)
						{
							PropertiesAndConstants.IsTestExecutionPassed = false;
							try { PropertiesAndConstants.TempScreenshotPath =  LogFunctions.TakeScreenshot(keyActionName); }
							catch(Exception exc)
							{ break; }
						}
						break;
					}
				}
				case WindowResize:
				{
					_result=windowResize(inputData);
					if (!_result)
					{
						PropertiesAndConstants.IsTestExecutionPassed = false;
						try { PropertiesAndConstants.TempScreenshotPath =LogFunctions.TakeScreenshot(keyActionName); }
						finally { break; }
					}
					break;
				}
				case ClearFiled:
				{
					_result=ClearElements(objName, actObjName, frameObj,frameObj);
					if (!_result)
					{
						PropertiesAndConstants.IsTestExecutionPassed = false;
						try { PropertiesAndConstants.TempScreenshotPath =LogFunctions.TakeScreenshot(keyActionName); }
						finally { break; }
					}
					break;
				}
				case VerifyElementAttribute:
				{
					_result=VerifyElementAttribute(objName, actObjName, frameObj);
					if (!_result)
					{
						PropertiesAndConstants.IsTestExecutionPassed = false;
					}
					break;
				}
				case VerifyIsElementNotContainsAttribute:
				{
					_result=VerifyIsElementNotContainsAttribute(objName, actObjName, inputData);
					if (!_result)
					{
						PropertiesAndConstants.IsTestExecutionPassed = false;
					}
					break;
				}

				case VerifyAllLinksOnThePage:
				{
					{
						_result=VerifyAllLinksOnThePage();
						LogFunctions.LogEntry("Start Checking All Links on the Page...", false);
						if (ConfigFunctions.getEnvKeyValue("BROWSER") == "IE")
						{
							_result = true;
							PropertiesAndConstants.IsStepSkip = true;
							PropertiesAndConstants.ResultString = "Not supported for IE - Pass";
									break;
						}

						if (!_result)
						{
							PropertiesAndConstants.IsTestExecutionPassed = false;
						}
						LogFunctions.LogEntry("Checking All Links on the Page - Completed", false);
						break;
					}

				}
				case VerifyAllLinksButtonsInElement:
				{
					LogFunctions.LogEntry("Start Checking Links/Buttons Collection...", false);
					if (ConfigFunctions.getEnvKeyValue("BROWSER") == "IE")
					{
						_result = true;
						PropertiesAndConstants.IsStepSkip = true;
						PropertiesAndConstants.ResultString = "Not supported for IE - Pass";
						break;
					}

					if (!_result)
					{
						PropertiesAndConstants.IsTestExecutionPassed = false;
					}
					LogFunctions.LogEntry("Checking Links/Buttons Collection - Completed", false);
					break;
				}
				case VerifyBackgroundImageForElement:
				{
					_result=VerifyBackgroundImageForElement(objName, actObjName);
					break;
				}
				case VerifyIsElementNotDisplayed:
				{

					_result = VerifyIsElementNotDisplayed(usrObjName, actObjName, frameObj);
					if (!_result)
					{
						PropertiesAndConstants.IsTestExecutionPassed = false;
						try { PropertiesAndConstants.TempScreenshotPath =    LogFunctions.TakeScreenshot(keyActionName); }
						catch(Exception exc) 
						{  

							break;
						}
					}
					break;

				}
				case VerifyIsTextPresent:
				{ 
					_result=VerifyIsTextPresent(inputData);
					if(_result)
					{
						PropertiesAndConstants.IsTestExecutionPassed = false;
						try 
						{             		   
							String source = PropertiesAndConstants.Selenium.getPageSource();
							

							PropertiesAndConstants.TempPageSourcePath = PropertiesAndConstants.TempDirectoryPath + String.format("PageSource_" + "{0}_{1:HH-mm-ss}",
							PropertiesAndConstants.TestScriptName, dateFormat.format(now)) + ".txt";

							//StreamWriter sourceFile = new StreamWriter(PropertiesAndConstants.TempPageSourcePath, true);
							PrintWriter sourceFile = new PrintWriter(new FileOutputStream(new File(PropertiesAndConstants.TempPageSourcePath), true));
							  
                                  sourceFile.write(source.toString());
                                  sourceFile.close();
                              //    sourceFile.Dispose();
                             break;

						}
						catch (Exception exc) 
						{
							LogFunctions.LogEntry("Cannot create a PageSourse text file.", false);
							LogFunctions.LogEntry("Reason: " + exc.getMessage(), false);
							LogFunctions.LogEntry("Details: " + exc, false);
							break;
						}
					}
					break;
				}
				  case VerifyIsTextNotPresent:
				  {
					 _result=VerifyIsTextNotPresent(inputData);
                      if (!_result)
                      {
                          PropertiesAndConstants.IsTestExecutionPassed = false;
                          try
                          {
                              String source = PropertiesAndConstants.Selenium.getPageSource();
                              PropertiesAndConstants.TempPageSourcePath = PropertiesAndConstants.TempDirectoryPath + String.format("PageSource_" + "{0}_{1:HH-mm-ss}",
                              PropertiesAndConstants.TestScriptName,dateFormat.format(now) ) + ".txt";
                             // using (StreamWriter sourceFile = new StreamWriter(PropertiesAndConstants.TempPageSourcePath, true))
                          	 PrintWriter sourceFile = new PrintWriter(new FileOutputStream(new File(PropertiesAndConstants.TempPageSourcePath), true));
                                  sourceFile.write(source.toString());
                                  sourceFile.close();
                               //   sourceFile.Dispose();
                              break;
                          }                                              
                          catch (Exception exc)
                          {
                              LogFunctions.LogEntry("Cannot create a PageSourse text file.", false);
                              LogFunctions.LogEntry("Reason: " + exc.getMessage(), false);
                              LogFunctions.LogEntry("Details: " + exc, false);
                              break;
                          }

                      }
                      break;
				  }
				  				                    
				  case HardWait:
					{
						hardWait(inputData);
						break;
					}				      
				// ***** Verification Methods: *****
				//  #endregion ***** Verification Methods: *****

				case AddCookie:
				{
					_result = addCookie(inputData);
					if (!_result)
					{
						PropertiesAndConstants.IsTestExecutionPassed = false;
					}
					break;
				}

				case ClearAllCookies:
				{
					_result = clearAllCookies();
					if (!_result)
					{
						PropertiesAndConstants.IsTestExecutionPassed = false;
					}
					break;
				}
				case ShowHiddenElement:
				{
					_result = ShowHiddenElement(objName, actObjName, frameObj);
					if (!_result)
					{
						PropertiesAndConstants.IsTestExecutionPassed = false;
						try { PropertiesAndConstants.TempScreenshotPath = LogFunctions.TakeScreenshot(keyActionName); }
						finally { break; }
					}
					break;
				}
			    case compareMultipleRandomValues:
				{
					_result = compareMultipleRandomValues(usrObjName, actObjName, frameObj, inputData);
					Thread.sleep(250);
					if (!_result)
					{
						PropertiesAndConstants.IsTestExecutionPassed = false;
						try { PropertiesAndConstants.TempScreenshotPath =    LogFunctions.TakeScreenshot(keyActionName); }
						finally { break; }
					}
					break;
				}
				case StoreMultipleRandomValue:
				{
					_result = storeMultipleRandomValue(usrObjName, actObjName, frameObj, inputData);
					Thread.sleep(250);
					if (!_result)
					{
						PropertiesAndConstants.IsTestExecutionPassed = false;
						try { PropertiesAndConstants.TempScreenshotPath =    LogFunctions.TakeScreenshot(keyActionName); }
						finally { break; }
					}
					break;
				}				
				case RestartBrowser:
				{
					_result = restartBrowser(inputData);
					if (!_result)
					{
						PropertiesAndConstants.IsTestExecutionPassed = false;
					}
					break;
				}
				case VerifyIsElementContainsText:
				{
					_result = VerifyIsElementContainsText(objName, actObjName, frameObj, inputData);
					if (!_result)
					{
						PropertiesAndConstants.IsTestExecutionPassed = false;
						try { PropertiesAndConstants.TempScreenshotPath =   LogFunctions.TakeScreenshot(keyActionName); }
						finally { break; }
					}
					break;
				}
				case VerifyIsImageDisplayed:
					
				{
					_result = VerifyIsImageDisplayed(objName, actObjName);
					if (!_result)
					{
						PropertiesAndConstants.IsTestExecutionPassed = false;
						try { PropertiesAndConstants.TempScreenshotPath =   LogFunctions.TakeScreenshot(keyActionName); }
						finally { break; }
					}
					break;
				}		
				
				case VerifyIsSameImageDisplayedAsSet:
				{
					_result = VerifyIsSameImageDisplayedAsSet(objName, actObjName, inputData);
					if (!_result)
					{
						PropertiesAndConstants.IsTestExecutionPassed = false;
						try { PropertiesAndConstants.TempScreenshotPath =   LogFunctions.TakeScreenshot(keyActionName); }
						finally { break; }
					}
					break;
				}				
				//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++				  				  
				case CompareRandomValuesNotEqual:
				{
					_result = compareRandomValuesNotEqual(objName, actObjName, frameObj);
					if (!_result)
					{
						PropertiesAndConstants.IsTestExecutionPassed = false;
						try { PropertiesAndConstants.TempScreenshotPath =   LogFunctions.TakeScreenshot(keyActionName); }
						finally { break; }
					}
					break;
				}
				
				case  GetAttributeValue:
				  {
					  //Thread.sleep(10000);
					  _result=GetAttributeValue(usrObjName, actObjName, frameObj);
					  if (!_result)
						{
							PropertiesAndConstants.IsTestExecutionPassed = false;
							try { 
								PropertiesAndConstants.TempScreenshotPath =  LogFunctions.TakeScreenshot(keyActionName); 
							}
							finally 
							{break;}
						}
						break;					  
				  }							  																	  			  
			  default:
				{
					_result = false;
					LogFunctions.LogEntry("Provide the right KeyAction and re-execute the script. The given KeyAciton \""+keyActionName+"\", doesn't exists. Refere the help for more information",false);
							PropertiesAndConstants.IsTestExecutionPassed = false;
							break;
				}
		}
					
				
			              
			          if(_result==false)
			          {
			           break; 
			          }
			}								
			catch(TimeoutException wdtexc)
			{
				//  Console.ForegroundColor = ConsoleColor.Red;
				LogFunctions.LogEntry("Exception from KeyAction (WebDriverTimeoutException)",false);
				LogFunctions.LogEntry("Reason: " + wdtexc.getMessage(), false);
				LogFunctions.LogEntry("Details: " + wdtexc, false);
				//  Console.ResetColor();
				_result = false;
				PropertiesAndConstants.IsTestExecutionPassed = false;
				PropertiesAndConstants.scriptExecutionResult = "Fail";
			}
			catch (WebDriverException wdexc)
			{
				// Console.ForegroundColor = ConsoleColor.Red;
				LogFunctions.LogEntry("Exception from KeyAction (WebDriverException)",false);
				LogFunctions.LogEntry("Reason: " + wdexc.getMessage(), false);
				LogFunctions.LogEntry("Details: " + wdexc, false);
				//Console.ResetColor();
				_result = false;
				PropertiesAndConstants.IsTestExecutionPassed = false;
				PropertiesAndConstants.scriptExecutionResult = "Fail";
			}
			catch(IllegalArgumentException excep)
			{
				LogFunctions.LogEntry("Exception from KeyAction "+ keyActionName,false);
				LogFunctions.LogEntry("Reason: " + excep.getMessage(), false);
				LogFunctions.LogEntry("Details: " + excep, false);
				//  Console.ResetColor();
				_result = false;
				PropertiesAndConstants.IsTestExecutionPassed = false;
				PropertiesAndConstants.scriptExecutionResult = "Fail";
			}
			//  #endregion
		    }
		    else
		    {
		    	System.out.println("stepid:"+stepID);
		    	System.out.println("objName:"+objName);
		    	System.out.println("dontknow:"+(String)(testScriptSheet.getCell(0,rowID).getContents()));
		    	System.out.println("inputdata:"+inputData);
		    	LogFunctions.LogEntry("Exception from KeyAction: "+ keyActionName,false);
	
				//  Console.ResetColor();
				_result = false;
				PropertiesAndConstants.IsTestExecutionPassed = false;
				PropertiesAndConstants.scriptExecutionResult = "Fail";
		    }

			//PropertiesAndConstants.GetDriver.SwitchTo().DefaultContent();

			if (!_result && !keyActionName.contains("Verify") && !keyActionName.contains("Cookie") &&
					!keyActionName.contains("SafeLink")) // Add for Verify
			{
				PropertiesAndConstants.IsTestExecutionPassed = false;
				PropertiesAndConstants.scriptExecutionResult = "Fail";
				UtilityFunctions.CaptureExecutionResults(testScriptPath, testScriptSheet, rowID, _result, resultsToExcel);

				break;
			}
			    else   UtilityFunctions.CaptureExecutionResults(testScriptPath, testScriptSheet, rowID, _result, resultsToExcel);
		   }
	  }		
}//End of class KeyActionFuction
