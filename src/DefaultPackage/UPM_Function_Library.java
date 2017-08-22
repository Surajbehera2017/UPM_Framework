package DefaultPackage;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.joda.time.DateTime;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.LogStatus;

@SuppressWarnings("unused")
public class UPM_Function_Library extends UPM_Page_Library {

	public static WebDriver ObjDriver;
	public static WebElement UPM_Browser_Element;
	public static List<WebElement> UPM_Browser_Elements;
	public static List<WebElement> UPM_Browser_Elements_For_Div;
	public static Actions Element_Action;
	public static WebDriverWait Wait_For_Object;
	public static WebDriverWait Wait_For_Ajax;
	public static File Screen_Shot;
	// @@@@@@@@@@@Singleton Implementation@@@@@@@@@@@@@@@@@
	static UPM_Function_Library Function_Library_Instance;

	UPM_Function_Library() {

	}

	public static UPM_Function_Library Get_UPM_Function_Instance() {
		if (Function_Library_Instance == null) {
			return Function_Library_Instance = new UPM_Function_Library();

		} else {
			return Function_Library_Instance;
		}
	}

	// @@@@@@@@@@@````````````````````````@@@@@@@@@@@@@@@@@@

	public static void Activate_Latest_Browser(String Event, String Desc)
			throws Exception {
		if (!Event.matches("NA")) {
			Activate_Browsers();
			
			
			Element_Action = new Actions(ObjDriver);
			if (!Desc.isEmpty()) {
				UPM_Report_Library.Add_Step(
						UPM_Report_Library.Test_Step_Number, Desc,
						LogStatus.PASS, false);
			}
		}
	}

	public static void Activate_Browsers() {
		Set<String> BrowserList = null;
		Iterator<String> IteratorList = null;
		int Browser_Cnt = 0;
		int i;
		BrowserList = ObjDriver.getWindowHandles();
		Browser_Cnt = BrowserList.size();
		IteratorList = BrowserList.iterator();
		String[] WinList = new String[Browser_Cnt];
		for (i = 0; i < Browser_Cnt; i++) {
			WinList[i] = IteratorList.next();
		}
		ObjDriver.switchTo().window(WinList[i - 1]).manage().timeouts()
				.implicitlyWait(60, TimeUnit.SECONDS);
	}

	public static void Close_All_Active_Browser() {
		Set<String> BrowserList = null;
		Iterator<String> IteratorList = null;
		int Browser_Cnt = 0;
		int i;
		BrowserList = ObjDriver.getWindowHandles();
		Browser_Cnt = BrowserList.size();
		IteratorList = BrowserList.iterator();
		String[] WinList = new String[Browser_Cnt];
		for (i = 0; i < Browser_Cnt; i++) {
			WinList[i] = IteratorList.next();

		}
		for (i = Browser_Cnt - 1; i >= 0; i--) {
			ObjDriver.switchTo().window(WinList[i]).manage().timeouts()
					.implicitlyWait(60, TimeUnit.SECONDS);
			ObjDriver.quit();
		}
	}

	public static int Active_Browsers_Cnt() {
		Set<String> BrowserList = null;
		Iterator<String> IteratorList = null;
		int Browser_Cnt = 0;
		BrowserList = ObjDriver.getWindowHandles();
		Browser_Cnt = BrowserList.size();
		return Browser_Cnt;
	}

	public static void Close_Latest_Active_Browser() {
		Activate_Browsers();
		ObjDriver.quit();
		if (Active_Browsers_Cnt() > 0) {
			Activate_Browsers();
		}

	}

	public static void UPM_Launch_Browser(String Event, String Desc) throws Exception 
	{
		if (!Event.matches("NA")) 
		{
			if(Integer.parseInt(Driver_Script.Str_Version)>45)
			{
				
				File new1  = new File(".");
				String Gecko = new1.getCanonicalPath();
				System.setProperty("webdriver.gecko.driver", Gecko+"\\geckodriver.exe");
				FirefoxBinary FBinary = new FirefoxBinary(new File(Driver_Script.Str_Profile_Path));
				FirefoxProfile FProfile = new FirefoxProfile();
				  
				ObjDriver = new FirefoxDriver(FBinary, FProfile);
				ObjDriver.manage().deleteAllCookies();
				
				ObjDriver.manage().window().maximize();
				UPM_Report_Library.Add_Step(UPM_Report_Library.Test_Step_Number,Desc, LogStatus.PASS, false);

			}
			else
			{
				FirefoxBinary FBinary = new FirefoxBinary(new File(Driver_Script.Str_Profile_Path));
				FirefoxProfile FProfile = new FirefoxProfile();
				FProfile.setPreference("network.proxy.type", 0);
	
				// FProfile.addExtension(new
				// File(UPM_Configuration_Library.GetPath()+
				// "\\SUPPORTING JARS\\"+"selenium-ide.xpi"));
				ObjDriver = new FirefoxDriver(FBinary, FProfile);
				ObjDriver.manage().deleteAllCookies();
	
				ObjDriver.manage().window().maximize();
				UPM_Report_Library.Add_Step(UPM_Report_Library.Test_Step_Number,Desc, LogStatus.PASS, false);
				Wait_For_Object = new WebDriverWait(ObjDriver, 15);
			}
		}
	}

	public static void UPM_NavigateTo(String URL, String Desc) throws Exception {
		if (!URL.matches("NA")) {
			ObjDriver.navigate().to(URL);
			UPM_Report_Library.Add_Step(UPM_Report_Library.Test_Step_Number,
					Desc + ": " + URL, LogStatus.PASS, false);
		}
	}

	public static Boolean Is_Browser_Exist() {
		Boolean Is_Exist = false;
		if (ObjDriver == null) {
			return Is_Exist;
		} else {
			return Is_Exist = true;
		}
	}

	public static WebElement UPM_Get_Element(String Str_Property)
			throws Exception {
		if (!Is_Browser_Exist()) {
			UPM_Launch_Browser("Launch Browser", "yes");
		}
		Get_Locator(Str_Property);
		return UPM_Browser_Element;
	}

	public static List<WebElement> UPM_Get_Elements(String Str_Property)
			throws Exception {
		if (!Is_Browser_Exist()) {
			UPM_Launch_Browser("Launch Browser", "yes");
		}
		Get_Locators(Str_Property);
		return UPM_Browser_Elements;
	}

	public static List<WebElement> UPM_Get_Elements_For_Div(String Str_Property)
			throws Exception {
		if (!Is_Browser_Exist()) {
			UPM_Launch_Browser("Launch Browser", "yes");
		}
		Get_Locators_For_Div(Str_Property);
		return UPM_Browser_Elements_For_Div;
	}

	public static String UPM_Get_WebElement_Value(WebElement Element) {
		return Element.getText();
 
	}

	public static void UPM_Get_Set_WebElement_Value(WebElement Element,
			String Event, String Desc) {
		String Ret;
		Ret = UPM_Get_WebElement_Value(Element);

	}

	public static WebElement Get_Locator_For_Exist(String Str_Property)
			throws Exception {
		WebElement UPM_Browser_Element1 = null;
		String Actual_Property;

		//Wait_For_Div();
		if (Str_Property.startsWith("Attribute=")) {
			Actual_Property = Str_Property.substring(10);
			String[] Actual_Property_Arr = Actual_Property.split(";");
			String TagName = Actual_Property_Arr[0];
			String Attribute = Actual_Property_Arr[1];
			String Attribute_Value = Actual_Property_Arr[2];
			UPM_Browser_Elements = ObjDriver.findElements(By.tagName(TagName));
			for (int i = 0; i <= UPM_Browser_Elements.size() - 1; i++) {
				if (UPM_Browser_Elements.get(i).getAttribute(Attribute)
						.matches(Attribute_Value)) {
					UPM_Browser_Element1 = UPM_Browser_Elements.get(i);
					break;
				}
			}
		}
		if (Str_Property.startsWith("id=")) {
			Actual_Property = Str_Property.substring(3);
			UPM_Browser_Element1 = ObjDriver
					.findElement(By.id(Actual_Property));
			// Wait_For_Object.until(ExpectedConditions.
			// presenceOfElementLocated(By.id(Actual_Property)));
			int i = 10;
			for (i = 0; i <= 15; i++) {
				if (UPM_Browser_Element1.isDisplayed()) {
					Element_Action.moveToElement(UPM_Browser_Element1)
							.perform();
					highLightElement(ObjDriver, UPM_Browser_Element1);
					break;
				}
			}
		}
		if (Str_Property.startsWith("name=")) {
			Actual_Property = Str_Property.substring(5);
			UPM_Browser_Element1 = ObjDriver.findElement(By
					.name(Actual_Property));
			// Wait_For_Object.until(ExpectedConditions.
			// presenceOfElementLocated(By.name(Actual_Property)));
			int i = 10;
			for (i = 0; i <= 15; i++) {
				if (UPM_Browser_Element1.isDisplayed()) {
					Element_Action.moveToElement(UPM_Browser_Element1)
							.perform();
					highLightElement(ObjDriver, UPM_Browser_Element1);
					break;
				}
				Thread.sleep(100);
			}
		}
		if (Str_Property.startsWith("xpath=")) {
			Actual_Property = Str_Property.substring(6);
			UPM_Browser_Element1 = ObjDriver.findElement(By
					.xpath(Actual_Property));
			// Wait_For_Object.until(ExpectedConditions.
			// presenceOfElementLocated(By.xpath(Actual_Property)));
			int i;
			for (i = 0; i <= 15; i++) 
			{
				if (UPM_Browser_Element1.isEnabled()) 
				{
					// Element_Action.moveToElement(UPM_Browser_Element1).perform();
					highLightElement(ObjDriver, UPM_Browser_Element1);
					UPM_Browser_Element1 = ObjDriver.findElement(By
							.xpath(Actual_Property));
					JavascriptExecutor js = (JavascriptExecutor) ObjDriver;
					js.executeScript("arguments[0].scrollIntoView();",
							UPM_Browser_Element1);
					break;
				}
				else 
				{
					return UPM_Browser_Element1;
				}

			}
			
		}
		if (Str_Property.startsWith("link=")) {
			Actual_Property = Str_Property.substring(5);
			UPM_Browser_Element1 = ObjDriver.findElement(By
					.linkText(Actual_Property));
			// Wait_For_Object.until(ExpectedConditions.
			// presenceOfElementLocated(By.linkText(Actual_Property)));
			int i = 10;
			for (i = 0; i <= 15; i++) {
				if (UPM_Browser_Element1.isDisplayed()) {
					Element_Action.moveToElement(UPM_Browser_Element1)
							.perform();
					highLightElement(ObjDriver, UPM_Browser_Element1);
					break;
				}
				Thread.sleep(100);
			}
		}
		if (Str_Property.startsWith("class=")) {
			Actual_Property = Str_Property.substring(6);
			UPM_Browser_Element1 = ObjDriver.findElement(By
					.className(Actual_Property));
			// Wait_For_Object.until(ExpectedConditions.
			// presenceOfElementLocated(By.className(Actual_Property)));
			int i = 10;
			for (i = 0; i <= 15; i++) {
				if (UPM_Browser_Element1.isDisplayed()) {
					Element_Action.moveToElement(UPM_Browser_Element1)
							.perform();
					highLightElement(ObjDriver, UPM_Browser_Element1);
					break;
				}
				Thread.sleep(100);
			}
		}
		if (Str_Property.startsWith("partiallink=")) {
			Actual_Property = Str_Property.substring(12);
			UPM_Browser_Element1 = ObjDriver.findElement(By
					.partialLinkText(Actual_Property));
			// Wait_For_Object.until(ExpectedConditions.
			// presenceOfElementLocated(By.partialLinkText(Actual_Property)));
			int i = 10;
			for (i = 0; i <= 15; i++) {
				if (UPM_Browser_Element1.isDisplayed()) {
					Element_Action.moveToElement(UPM_Browser_Element1)
							.perform();
					highLightElement(ObjDriver, UPM_Browser_Element1);
					UPM_Report_Library.Error_Flag = true;
					break;
				}
				Thread.sleep(100);
			}
		}
		Wait_For_Div();
		return UPM_Browser_Element1;
	}

	public static void Get_Locator(String Str_Property) throws Exception {
		try {
			UPM_Browser_Element = Get_Locator_For_Exist(Str_Property);
		} catch (InvalidSelectorException excp1) {
			UPM_Report_Library.Add_Step(UPM_Report_Library.Test_Step_Number,
					excp1.toString(), LogStatus.FAIL, false);
			Exception_Content();
		} catch (StaleElementReferenceException excp2) {
			ObjDriver.navigate().refresh();
			UPM_Browser_Element = Get_Locator_For_Exist(Str_Property);
		} catch (InvalidElementStateException excp3) {
			UPM_Report_Library.Add_Step(UPM_Report_Library.Test_Step_Number,
					excp3.toString(), LogStatus.FAIL, false);
			Exception_Content();
		} catch (NoSuchElementException excp4) {
			UPM_Report_Library.Add_Step(UPM_Report_Library.Test_Step_Number,
					excp4.toString(), LogStatus.FAIL, false);
			Exception_Content();
		} catch (InvocationTargetException excp5) {
			UPM_Report_Library.Add_Step(UPM_Report_Library.Test_Step_Number,
					excp5.toString(), LogStatus.FAIL, false);
			Exception_Content();
		} catch (Exception excp6) {
			UPM_Report_Library.Add_Step(UPM_Report_Library.Test_Step_Number,
					excp6.toString(), LogStatus.FAIL, false);
			Exception_Content();
		}
	}

	// public static void Get_Locator(String Str_Property) throws Exception
	// {
	// UPM_Browser_Element = null;
	// String Actual_Property;
	//
	// Wait_For_Div();
	// if(Str_Property.startsWith("Attribute="))
	// {
	// try
	// {
	//
	// Actual_Property = Str_Property.substring(10);
	// String[] Actual_Property_Arr = Actual_Property.split(";");
	// String TagName = Actual_Property_Arr[0];
	// String Attribute = Actual_Property_Arr[1];
	// String Attribute_Value = Actual_Property_Arr[2];
	// UPM_Browser_Elements = ObjDriver.findElements(By.tagName(TagName));
	// for(int i=0;i<=UPM_Browser_Elements.size()-1;i++)
	// {
	// if(UPM_Browser_Elements.get(i).getAttribute(Attribute).matches(Attribute_Value))
	// {
	// UPM_Browser_Element = UPM_Browser_Elements.get(i);
	// break;
	// }
	// }
	// }
	// catch(Exception e)
	// {
	// UPM_Configuration_Library.Sound_Beep();
	// e.printStackTrace();
	// Exception_Content();
	// }
	// }
	// if(Str_Property.startsWith("id="))
	// {
	// try
	// {
	// Actual_Property = Str_Property.substring(3);
	//
	// UPM_Browser_Element = ObjDriver.findElement(By.id(Actual_Property));
	// Wait_For_Object.until(ExpectedConditions.
	// presenceOfElementLocated(By.id(Actual_Property)));
	// //Wait_For_Object.until(ExpectedConditions.visibilityOf(UPM_Browser_Element));
	//
	// int i = 10;
	// Boolean Obj_Exist;
	// Obj_Exist = false;
	// for(i=0;i<=15;i++)
	// {
	// if(UPM_Browser_Element.isDisplayed())
	// {
	//
	// Element_Action.moveToElement(UPM_Browser_Element).perform();
	// highLightElement(ObjDriver,UPM_Browser_Element);
	// Obj_Exist = true;
	//
	// break;
	// }
	// Thread.sleep(100);
	// }
	// if(!Obj_Exist)
	// {
	// Exception_Content();
	// }
	// }
	// catch(Exception e)
	// {
	// UPM_Configuration_Library.Sound_Beep();
	// e.printStackTrace();
	// Exception_Content();
	// }
	// }
	// if(Str_Property.startsWith("name="))
	// {
	// try
	// {
	// Actual_Property = Str_Property.substring(5);
	//
	// UPM_Browser_Element = ObjDriver.findElement(By.name(Actual_Property));
	// Wait_For_Object.until(ExpectedConditions.
	// presenceOfElementLocated(By.name(Actual_Property)));
	// int i = 10;
	// Boolean Obj_Exist;
	// Obj_Exist = false;
	// for(i=0;i<=15;i++)
	// {
	// if(UPM_Browser_Element.isDisplayed())
	// {
	// Element_Action.moveToElement(UPM_Browser_Element).perform();
	// highLightElement(ObjDriver,UPM_Browser_Element);
	// Obj_Exist = true;
	// break;
	// }
	// Thread.sleep(100);
	// }
	// if(!Obj_Exist)
	// {
	// Exception_Content();
	// }
	// }
	// catch(Exception e)
	// {
	// UPM_Configuration_Library.Sound_Beep();
	// e.printStackTrace();
	// Exception_Content();
	// }
	// }
	// if(Str_Property.startsWith("xpath="))
	// {
	// try
	// {
	// Actual_Property = Str_Property.substring(6);
	//
	// UPM_Browser_Element = ObjDriver.findElement(By.xpath(Actual_Property));
	// Wait_For_Object.until(ExpectedConditions.
	// presenceOfElementLocated(By.xpath(Actual_Property)));
	// int i = 10;
	// Boolean Obj_Exist;
	// Obj_Exist = false;
	// for(i=0;i<=15;i++)
	// {
	// if(UPM_Browser_Element.isDisplayed())
	// {
	// Element_Action.moveToElement(UPM_Browser_Element).perform();
	// highLightElement(ObjDriver,UPM_Browser_Element);
	// Obj_Exist = true;
	// break;
	// }
	// Thread.sleep(100);
	// //Wait_For_Object.until(ExpectedConditions.visibilityOf(UPM_Browser_Element));
	// }
	// if(!Obj_Exist)
	// {
	// Exception_Content();
	// }
	// }
	// catch(Exception e)
	// {
	// UPM_Configuration_Library.Sound_Beep();
	// e.printStackTrace();
	// Exception_Content();
	// }
	// }
	// if(Str_Property.startsWith("link="))
	// {
	// try
	// {
	// Actual_Property = Str_Property.substring(5);
	//
	// UPM_Browser_Element =
	// ObjDriver.findElement(By.linkText(Actual_Property));
	// Wait_For_Object.until(ExpectedConditions.
	// presenceOfElementLocated(By.linkText(Actual_Property)));
	// int i = 10;
	// Boolean Obj_Exist;
	// Obj_Exist = false;
	// for(i=0;i<=15;i++)
	// {
	// if(UPM_Browser_Element.isDisplayed())
	// {
	// Element_Action.moveToElement(UPM_Browser_Element).perform();
	// highLightElement(ObjDriver,UPM_Browser_Element);
	// Obj_Exist = true;
	// break;
	// }
	// Thread.sleep(100);
	// }
	// if(!Obj_Exist)
	// {
	// Exception_Content();
	// }
	// }
	// catch(Exception e)
	// {
	// UPM_Configuration_Library.Sound_Beep();
	// e.printStackTrace();
	// Exception_Content();
	// }
	// }
	// if(Str_Property.startsWith("class="))
	// {
	// try
	// {
	// Actual_Property = Str_Property.substring(6);
	//
	// UPM_Browser_Element =
	// ObjDriver.findElement(By.className(Actual_Property));
	// Wait_For_Object.until(ExpectedConditions.
	// presenceOfElementLocated(By.className(Actual_Property)));
	// int i = 10;
	// Boolean Obj_Exist;
	// Obj_Exist = false;
	// for(i=0;i<=15;i++)
	// {
	// if(UPM_Browser_Element.isDisplayed())
	// {
	// Element_Action.moveToElement(UPM_Browser_Element).perform();
	// highLightElement(ObjDriver,UPM_Browser_Element);
	// Obj_Exist = true;
	// break;
	// }
	// Thread.sleep(100);
	// }
	// if(!Obj_Exist)
	// {
	// Exception_Content();
	// }
	// }
	// catch(Exception e)
	// {
	// UPM_Configuration_Library.Sound_Beep();
	// e.printStackTrace();
	// Exception_Content();
	// }
	// }
	// if(Str_Property.startsWith("partiallink="))
	// {
	// try
	// {
	// Actual_Property = Str_Property.substring(12);
	//
	// UPM_Browser_Element =
	// ObjDriver.findElement(By.partialLinkText(Actual_Property));
	// Wait_For_Object.until(ExpectedConditions.
	// presenceOfElementLocated(By.partialLinkText(Actual_Property)));
	// int i = 10;
	// Boolean Obj_Exist;
	// Obj_Exist = false;
	// for(i=0;i<=15;i++)
	// {
	// if(UPM_Browser_Element.isDisplayed())
	// {
	// Element_Action.moveToElement(UPM_Browser_Element).perform();
	// highLightElement(ObjDriver,UPM_Browser_Element);
	// Obj_Exist = true;
	// UPM_Report_Library.Error_Flag = true;
	// break;
	// }
	// Thread.sleep(100);
	// }
	// if(!Obj_Exist)
	// {
	// Exception_Content();
	// }
	// }
	// catch(Exception e)
	// {
	// UPM_Configuration_Library.Sound_Beep();
	// e.printStackTrace();
	// Exception_Content();
	// }
	// }
	// Wait_For_Div();
	// }

	public static void Get_Locators(String Str_Property) throws Exception {
		UPM_Browser_Elements = null;
		String Actual_Property;
		//Wait_For_Div();
		if (Str_Property.startsWith("id=")) {
			try {
				Actual_Property = Str_Property.substring(3);

				UPM_Browser_Elements = ObjDriver.findElements(By
						.id(Actual_Property));
				Wait_For_Object
						.until(ExpectedConditions
								.presenceOfAllElementsLocatedBy(By
										.id(Actual_Property)));
				// Wait_For_Object.until(ExpectedConditions.visibilityOfAllElements(UPM_Browser_Elements));

				highLightElement(ObjDriver, UPM_Browser_Elements.get(0));
			} catch (Exception e) {
				UPM_Configuration_Library.Sound_Beep();
				e.printStackTrace();
				Exception_Content();
			}
		}
		if (Str_Property.startsWith("name=")) {
			try {
				Actual_Property = Str_Property.substring(5);

				UPM_Browser_Elements = ObjDriver.findElements(By
						.name(Actual_Property));
				// Wait_For_Object.until(ExpectedConditions.visibilityOfAllElements(UPM_Browser_Elements));
				Wait_For_Object.until(ExpectedConditions
						.presenceOfAllElementsLocatedBy(By
								.name(Actual_Property)));
				int cnt = UPM_Browser_Elements.size() - 1;
				Element_Action.moveToElement(UPM_Browser_Elements.get(cnt))
						.perform();
				highLightElement(ObjDriver, UPM_Browser_Elements.get(cnt));
			} catch (Exception e) {
				UPM_Configuration_Library.Sound_Beep();
				e.printStackTrace();
				Exception_Content();
			}
		}
		if (Str_Property.startsWith("xpath=")) {
			try {
				Actual_Property = Str_Property.substring(6);

				UPM_Browser_Elements = ObjDriver.findElements(By
						.xpath(Actual_Property));
				Wait_For_Object.until(ExpectedConditions
						.presenceOfAllElementsLocatedBy(By
								.xpath(Actual_Property)));
				// Wait_For_Object.until(ExpectedConditions.visibilityOfAllElements(UPM_Browser_Elements));

				int cnt = UPM_Browser_Elements.size() - 1;
				Element_Action.moveToElement(UPM_Browser_Elements.get(cnt))
						.perform();
				highLightElement(ObjDriver, UPM_Browser_Elements.get(cnt));
			} catch (Exception e) {
				UPM_Configuration_Library.Sound_Beep();
				e.printStackTrace();
				Exception_Content();
			}
		}
		if (Str_Property.startsWith("link=")) {
			try {
				Actual_Property = Str_Property.substring(5);

				UPM_Browser_Elements = ObjDriver.findElements(By
						.linkText(Actual_Property));
				Wait_For_Object.until(ExpectedConditions
						.presenceOfAllElementsLocatedBy(By
								.linkText(Actual_Property)));
				// Wait_For_Object.until(ExpectedConditions.visibilityOfAllElements(UPM_Browser_Elements));

				int cnt = UPM_Browser_Elements.size() - 1;
				Element_Action.moveToElement(UPM_Browser_Elements.get(cnt))
						.perform();
				highLightElement(ObjDriver, UPM_Browser_Elements.get(cnt));
			} catch (Exception e) {
				UPM_Configuration_Library.Sound_Beep();
				e.printStackTrace();
				Exception_Content();
			}
		}
		if (Str_Property.startsWith("class=")) {
			try {

				Actual_Property = Str_Property.substring(6);

				UPM_Browser_Elements = ObjDriver.findElements(By
						.className(Actual_Property));
				Wait_For_Object.until(ExpectedConditions
						.presenceOfAllElementsLocatedBy(By
								.className(Actual_Property)));
				// Wait_For_Object.until(ExpectedConditions.visibilityOfAllElements(UPM_Browser_Elements));

				int cnt = UPM_Browser_Elements.size() - 1;
				Element_Action.moveToElement(UPM_Browser_Elements.get(cnt))
						.perform();
				highLightElement(ObjDriver, UPM_Browser_Elements.get(cnt));
			} catch (Exception e) {
				UPM_Configuration_Library.Sound_Beep();
				e.printStackTrace();
				Exception_Content();
			}
		}
		if (Str_Property.startsWith("partiallink=")) {
			try {
				Actual_Property = Str_Property.substring(12);
				Wait_For_Object.until(ExpectedConditions
						.presenceOfAllElementsLocatedBy(By
								.partialLinkText(Actual_Property)));
				UPM_Browser_Elements = ObjDriver.findElements(By
						.partialLinkText(Actual_Property));
				// Wait_For_Object.until(ExpectedConditions.visibilityOfAllElements(UPM_Browser_Elements));

				int cnt = UPM_Browser_Elements.size() - 1;
				Element_Action.moveToElement(UPM_Browser_Elements.get(cnt))
						.perform();
				highLightElement(ObjDriver, UPM_Browser_Elements.get(cnt));
			} catch (Exception e) {
				UPM_Configuration_Library.Sound_Beep();
				e.printStackTrace();
				Exception_Content();
			}
		}
		if (Str_Property.startsWith("tagName=")) {
			try {
				Actual_Property = Str_Property.substring(8);

				UPM_Browser_Elements = ObjDriver.findElements(By
						.tagName(Actual_Property));
				Wait_For_Object.until(ExpectedConditions
						.presenceOfAllElementsLocatedBy(By
								.tagName(Actual_Property)));
				int cnt = UPM_Browser_Elements.size() - 1;
				Element_Action.moveToElement(UPM_Browser_Elements.get(cnt))
						.perform();
				highLightElement(ObjDriver, UPM_Browser_Elements.get(cnt));
			} catch (Exception e) {
				UPM_Configuration_Library.Sound_Beep();
				e.printStackTrace();
				Exception_Content();
			}
		}
		Wait_For_Div();
	}

	public static void Get_Locators_For_Div(String Str_Property)
			throws Exception {
		UPM_Browser_Elements_For_Div = null;
		String Actual_Property;
		if (Str_Property.startsWith("id=")) {
			try {
				Actual_Property = Str_Property.substring(3);
				UPM_Browser_Elements_For_Div = ObjDriver.findElements(By
						.id(Actual_Property));
			} catch (Exception e) {
				UPM_Configuration_Library.Sound_Beep();
				e.printStackTrace();
				Exception_Content();
			}
		}
		if (Str_Property.startsWith("name=")) {
			try {
				Actual_Property = Str_Property.substring(5);
				UPM_Browser_Elements_For_Div = ObjDriver.findElements(By
						.name(Actual_Property));
			} catch (Exception e) {
				UPM_Configuration_Library.Sound_Beep();
				e.printStackTrace();
				Exception_Content();
			}
		}
		if (Str_Property.startsWith("xpath=")) {
			try {
				Actual_Property = Str_Property.substring(6);
				UPM_Browser_Elements_For_Div = ObjDriver.findElements(By
						.xpath(Actual_Property));
			} catch (Exception e) {
				UPM_Configuration_Library.Sound_Beep();
				e.printStackTrace();
				Exception_Content();
			}
		}
		if (Str_Property.startsWith("link=")) {
			try {
				Actual_Property = Str_Property.substring(5);
				UPM_Browser_Elements_For_Div = ObjDriver.findElements(By
						.linkText(Actual_Property));
			} catch (Exception e) {
				UPM_Configuration_Library.Sound_Beep();
				e.printStackTrace();
				Exception_Content();
			}
		}
		if (Str_Property.startsWith("class=")) {
			try {

				Actual_Property = Str_Property.substring(6);
				UPM_Browser_Elements_For_Div = ObjDriver.findElements(By
						.className(Actual_Property));
			} catch (Exception e) {
				UPM_Configuration_Library.Sound_Beep();
				e.printStackTrace();
				Exception_Content();
			}
		}
		if (Str_Property.startsWith("partiallink=")) {
			try {
				Actual_Property = Str_Property.substring(12);
				UPM_Browser_Elements_For_Div = ObjDriver.findElements(By
						.partialLinkText(Actual_Property));
			} catch (Exception e) {
				UPM_Configuration_Library.Sound_Beep();
				e.printStackTrace();
				Exception_Content();
			}
		}
		if (Str_Property.startsWith("tagName=")) {
			try {
				Actual_Property = Str_Property.substring(8);
				UPM_Browser_Elements_For_Div = ObjDriver.findElements(By
						.tagName(Actual_Property));
			} catch (Exception e) {
				UPM_Configuration_Library.Sound_Beep();
				e.printStackTrace();
				Exception_Content();
			}
		}

	}

	public static void Ajax_Wait() throws InterruptedException {
		// try
		// { Thread.sleep(500);
		// int i;
		// WebElement Ajax =
		// ObjDriver.findElement(By.xpath("xpath=//*[contains(@id,'loadingPanelContentTable')]"));
		// if(!(Ajax==null))
		// {
		// for(i=0;i<=40;i++)
		// {
		// if(!Ajax.isDisplayed())
		// {
		// break;
		// }
		// Thread.sleep(500);
		// }
		// }
		// }
		// catch(Exception e)
		// {
		// e.printStackTrace();
		// }
		// By loader =
		// By.xpath("xpath=//*[contains(@id,'loadingPanelContentTable')]");

		// Thread.sleep(500);
		// By loader = By.className("rich-modalpanel Loading");
		// WebDriverWait wait = new WebDriverWait(ObjDriver, 1500);
		// wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));

		ObjDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	}

	public static void waitForAjax2(int s) {
		JavascriptExecutor js = (JavascriptExecutor) ObjDriver;
		// Initially bellow given if condition will check ready state of page.
		if (js.executeScript("return document.readyState").toString()
				.equals("complete")) {
			System.out.println("Page Is loaded.");
			return;
		}
		// This loop will rotate for 25 times to check If page Is ready after
		// every 1 second.
		// You can replace your value with 25 If you wants to Increase or
		// decrease wait time.
		for (int i = 0; i < 25; i++) {
			try {
				Thread.sleep(1000);

			} catch (InterruptedException e) {
			}
			// To check page ready state.
			if (js.executeScript("return document.readyState").toString()
					.equals("complete")) {
				break;
			}
		}
	}

	public static void waitForAjax3(int timeoutInSeconds)
			throws InterruptedException {
		System.out
				.println("Checking active ajax calls by calling jquery.active");
		try {
			if (ObjDriver instanceof JavascriptExecutor) {
				JavascriptExecutor jsDriver = (JavascriptExecutor) ObjDriver;
				for (int i = 0; i < timeoutInSeconds; i++) {
					Object numberOfAjaxConnections = jsDriver
							.executeScript("return jQuery.active");
					// return should be a number
					if (numberOfAjaxConnections instanceof Long) {
						Long n = (Long) numberOfAjaxConnections;
						System.out
								.println("Number of active jquery ajax calls: "
										+ n);
						if (n.longValue() == 0L)
							break;
					}
					Thread.sleep(1000);
				}
			} else {
				System.out.println("Web driver: " + ObjDriver
						+ " cannot execute javascript");
			}
		} catch (InterruptedException e) {
			UPM_Configuration_Library.Sound_Beep();
			System.out.println(e);
		}
	}

	public static void WaitForModalPanel_Disapear() throws InterruptedException {
		Thread.sleep(500);
		int Wait_Iterate;
		for (Wait_Iterate = 0; Wait_Iterate < 16; Wait_Iterate++) {

			// Boolean present =
			// Wait_For_Object.until(ExpectedConditions.not(ExpectedConditions.
			// visibilityOfElementLocated(By.xpath("//*[contains(@id,'loadingPanelContainer')]"))));
			if (!ObjDriver.findElement(
					By.xpath("//*[@id='menu_form_id:loadingPanelDiv']"))
					.isDisplayed()) {
				break;
			} else {
				Thread.sleep(1000);
			}
		}
	}

	public static void Exception_Content() {
		UPM_Browser_Element = null;
		UPM_Report_Library.Error_Flag = true;
		// UPM_Function_Library.Close_All_Active_Browser();
		// UPM_Report_Library.End_Test();
	}

	public static Boolean UPM_MouseOver_Element(String Text_Property)
			throws Exception {
		Boolean ret;
		ret = false;
		// WebElement UPM_Element = null;
		// UPM_Element = UPM_Get_Element(Text_Property);
		// if(UPM_Element != null)
		// {
		// Element_Action.moveToElement(UPM_Element).build().perform();
		// ret = true;
		// }
		return ret;
	}

	public static void UPM_MouseOver(String Element_Property, String Event,
			String Description) throws Exception {
		if (!Event.matches("NA"))
			try {
				{
					Wait_For_Div();

					Boolean ret;
					ret = false;
					WebElement UPM_Element = null;
					String[] Element_PropertyArray = Element_Property
							.split(";");
					Thread.sleep(500);
					for (String Pro : Element_PropertyArray) {
						UPM_Element = UPM_Get_Element(Pro);
						if (UPM_Element != null) {
							if (!Description.isEmpty()) {
								Element_Action.moveToElement(UPM_Element)
										.build().perform();
							}
							ret = true;
						} else {
							if (!Description.isEmpty()) {
								UPM_Report_Library.Add_Step(
										UPM_Report_Library.Test_Step_Number,
										Description, LogStatus.FAIL, true);
								Exception_Content();
							}
							break;
						}
					}
					if (ret) {
						UPM_Report_Library.Add_Step(
								UPM_Report_Library.Test_Step_Number,
								Description, LogStatus.PASS, true);
					}
				}
			} catch (Exception e) {
				if (!Description.isEmpty()) {
					UPM_Report_Library.Add_Step(
							UPM_Report_Library.Test_Step_Number, Description
									+ "\n" + e, LogStatus.FAIL, true);
					Exception_Content();
				}
			}
	}

	public static void UPM_Set_Text(String Text_Property, String Text_Val,
			String Desc) throws Exception {
		if (!Text_Val.matches("NA")) {
			Thread.sleep(500);
			Wait_For_Div();

			WebElement UPM_TextBox = null;

			UPM_TextBox = UPM_Get_Element(Text_Property);

			if (UPM_TextBox == null) {
				if (!Desc.isEmpty()) {
					UPM_Report_Library.Add_Step(
							UPM_Report_Library.Test_Step_Number, Desc + ": "
									+ Text_Val + "\n"
									+ "The Element is not present",
							LogStatus.FAIL, true);
					Exception_Content();

				}
			} else {
				try {

					UPM_TextBox.clear();
					// UPM_TextBox.sendKeys("");
					UPM_TextBox.sendKeys(Text_Val);
					UPM_TextBox.sendKeys(Keys.TAB);
					// WaitForModalPanel_Disapear();
					// Ajax_Wait();
					// waitForAjax(1500);
					if (!Desc.isEmpty()) {
						UPM_Report_Library.Add_Step(
								UPM_Report_Library.Test_Step_Number, Desc
										+ ": " + Text_Val, LogStatus.PASS,
								false);
					}
				} catch (InvalidElementStateException e) {
					UPM_Report_Library.Add_Step(
							UPM_Report_Library.Test_Step_Number, Desc + "\n"
									+ e, LogStatus.FAIL, true);
					Exception_Content();
					UPM_Configuration_Library.Sound_Beep();
					e.printStackTrace();
				}
			}
		}
	}

	// Function used to get the value and store in Excel sheet
	public static void UPM_Get_Text(String Text_Property, String Event,
			String Desc) throws Exception {
		if (!Event.matches("NA")) {
			Thread.sleep(500);
			String Text_Val;
			WebElement UPM_TextBox = null;
			UPM_TextBox = UPM_Get_Element(Text_Property);
			if (UPM_TextBox == null) {
				if (!Desc.isEmpty()) {
					UPM_Report_Library.Add_Step(
							UPM_Report_Library.Test_Step_Number, Desc + "\n"
									+ "The Element is not present",
							LogStatus.FAIL, true);
					Exception_Content();
				}
			} else {
				Text_Val = UPM_TextBox.getAttribute("value");
				UPM_Configuration_Library.Set_Active_Cell_Value_overwrite(1,
						Text_Val); // Note : The cell below the function should
									// be blank
				if (!Desc.isEmpty()) {
					UPM_Report_Library.Add_Step(
							UPM_Report_Library.Test_Step_Number, Desc + ": "
									+ Text_Val, LogStatus.PASS, false);
				}
			}
		}
	}

	public static String UPM_Get_Text_Update(String Text_Property,
			String Event, String Desc) throws Exception {
		String Text_Val = null;
		if (!Event.matches("NA")) {
			Thread.sleep(500);

			WebElement UPM_TextBox = null;
			UPM_TextBox = UPM_Get_Element(Text_Property);
			if (UPM_TextBox == null) {
				if (!Desc.isEmpty()) {
					UPM_Report_Library.Add_Step(
							UPM_Report_Library.Test_Step_Number, Desc,
							LogStatus.FAIL, true);
					Exception_Content();
				}
			} else {
				Text_Val = UPM_TextBox.getText();
				// Note : The cell below the function will be over write if
				// something is there before
				if (!Desc.isEmpty()) {
					UPM_Report_Library.Add_Step(
							UPM_Report_Library.Test_Step_Number, Desc + ": "
									+ Text_Val, LogStatus.PASS, false);
				}

			}
		}
		return Text_Val;
	}

	public static void UPM_Verify_TextBox_Value(String Text_Property,
			String Expected_Value, String Desc) throws Exception {
		if (!Expected_Value.matches("NA")) {
			String Actual_Text_Val;
			WebElement UPM_TextBox = null;
			UPM_TextBox = UPM_Get_Element(Text_Property);
			if (UPM_TextBox == null) {
				if (!Desc.isEmpty()) {
					UPM_Report_Library.Add_Step(
							UPM_Report_Library.Test_Step_Number, Desc,
							LogStatus.FAIL, true);
					Exception_Content();
				}
			} else {
				Actual_Text_Val = UPM_TextBox.getAttribute("value");
				if (Actual_Text_Val.equalsIgnoreCase(Expected_Value)) {
					if (!Desc.isEmpty()) {
						UPM_Report_Library.Add_Step(
								UPM_Report_Library.Test_Step_Number, Desc
										+ ": Actual Value :" + Actual_Text_Val
										+ ", Expected Value : "
										+ Expected_Value, LogStatus.PASS, true);
					}
				} else {
					if (!Desc.isEmpty()) {
						UPM_Report_Library.Add_Step(
								UPM_Report_Library.Test_Step_Number, Desc
										+ ": Actual Value :" + Actual_Text_Val
										+ ", Expected Value : "
										+ Expected_Value, LogStatus.FAIL, true);
						Exception_Content();
					}
				}

			}
		}
	}

	public static void UPM_Verify_TextBox_Contains_Data(String Text_Property,
			String Event1, String Desc) throws Exception {
		if (!Event1.matches("NA")) {
			String Actual_Text_Val;
			WebElement UPM_TextBox = null;
			UPM_TextBox = UPM_Get_Element(Text_Property);
			if (UPM_TextBox == null) {
				if (!Desc.isEmpty()) {
					UPM_Report_Library.Add_Step(
							UPM_Report_Library.Test_Step_Number, Desc,
							LogStatus.FAIL, true);
					Exception_Content();
				}
			} else {
				Actual_Text_Val = UPM_TextBox.getAttribute("value");
				if (!Actual_Text_Val.isEmpty()) {
					if (!Desc.isEmpty()) {
						UPM_Report_Library.Add_Step(
								UPM_Report_Library.Test_Step_Number, Desc
										+ ": Actual Value :" + Actual_Text_Val,
								LogStatus.PASS, true);
					}
				} else {
					if (!Desc.isEmpty()) {
						UPM_Report_Library.Add_Step(
								UPM_Report_Library.Test_Step_Number, Desc
										+ ": Actual Value :" + Actual_Text_Val,
								LogStatus.FAIL, true);
						Exception_Content();
					}
				}

			}
		}
	}

	public static void UPM_Select_DropDownList(String DropDownList_Property,
			String DropDownList_Val, String Desc) throws Exception {
		if (!DropDownList_Val.matches("NA")) {
			Wait_For_Div();
			Thread.sleep(500);
			WebElement Element = null;
			Element = UPM_Get_Element(DropDownList_Property);
			String value = repalceStrings(DropDownList_Val);
			if (Element == null) {
				if (!Desc.isEmpty()) {
					UPM_Report_Library.Add_Step(
							UPM_Report_Library.Test_Step_Number, Desc + ": "
									+ DropDownList_Val, LogStatus.FAIL, true);
					Exception_Content();
				}
			} else {
				Select UPM_DropDownList = new Select(Element);
				UPM_DropDownList.selectByVisibleText(value);
				if (!Desc.isEmpty()) {
					UPM_Report_Library.Add_Step(
							UPM_Report_Library.Test_Step_Number, Desc + ": "
									+ DropDownList_Val, LogStatus.PASS, false);
				}
			}
		}
	}

	public static void UPM_Select_Last_DropDownList(
			String DropDownList_Property, String DropDownList_Val, String Desc)
			throws Exception {
		if (!DropDownList_Val.matches("NA")) {
			Thread.sleep(1000);
			List<WebElement> UPM_Objects = null;
			WebElement UPM_Object = null;
			UPM_Objects = UPM_Get_Elements(DropDownList_Property);
			if (UPM_Objects == null) {
				if (!Desc.isEmpty()) {
					UPM_Report_Library.Add_Step(
							UPM_Report_Library.Test_Step_Number, Desc + " : "
									+ DropDownList_Val, LogStatus.FAIL, true);
					Exception_Content();
				}
			} else {
				int Element_Cnt = UPM_Objects.size();
				UPM_Object = UPM_Objects.get(Element_Cnt - 1);
				Select UPM_DropDownList = new Select(UPM_Object);
				UPM_DropDownList.selectByVisibleText(DropDownList_Val);
				// Ajax_Wait();
				// waitForAjax(1500);
				if (!Desc.isEmpty()) {
					UPM_Report_Library.Add_Step(
							UPM_Report_Library.Test_Step_Number, Desc + " : "
									+ DropDownList_Val, LogStatus.PASS, false);
				}
			}
		}
	}

	public static void UPM_Select_DropDownList_ByValue(
			String DropDownList_Property, String DropDownList_Val, String Desc)
			throws Exception {
		try {
			if (!DropDownList_Val.matches("NA"))

			{
				WebElement Element = null;
				Element = UPM_Get_Element(DropDownList_Property);
				if (Element == null) {
					if (!Desc.isEmpty()) {
						UPM_Report_Library.Add_Step(
								UPM_Report_Library.Test_Step_Number, Desc
										+ ": " + DropDownList_Val,
								LogStatus.FAIL, true);
						Exception_Content();
					}
				} else {
					Select UPM_DropDownList = new Select(Element);
					UPM_DropDownList.selectByValue(DropDownList_Val);
					// Ajax_Wait();
					// waitForAjax(1500);
					if (!Desc.isEmpty()) {
						UPM_Report_Library.Add_Step(
								UPM_Report_Library.Test_Step_Number, Desc
										+ ": " + DropDownList_Val,
								LogStatus.PASS, false);
					}
				}
			}
		} catch (Exception e) {
			if (!Desc.isEmpty()) {
				UPM_Report_Library.Add_Step(
						UPM_Report_Library.Test_Step_Number, Desc + ": "
								+ DropDownList_Val, LogStatus.FAIL, true);
				Exception_Content();
			}

		}
	}

	public static void UPM_Select_DropDownList_ByIndex(
			String DropDownList_Property, String DropDownList_Option_Index,
			String Desc) throws Exception {
		try {

			if (!DropDownList_Option_Index.matches("NA")) {
				WebElement Element = null;
				Element = UPM_Get_Element(DropDownList_Property);
				if (Element == null) {
					if (!Desc.isEmpty()) {
						UPM_Report_Library.Add_Step(
								UPM_Report_Library.Test_Step_Number, Desc
										+ ": " + DropDownList_Option_Index,
								LogStatus.FAIL, true);
						Exception_Content();
					}
				} else {
					Select UPM_DropDownList = new Select(Element);
					UPM_DropDownList.selectByIndex(Integer
							.parseInt(DropDownList_Option_Index));
					if (!Desc.isEmpty()) {
						UPM_Report_Library.Add_Step(
								UPM_Report_Library.Test_Step_Number, Desc
										+ ": " + DropDownList_Option_Index,
								LogStatus.PASS, false);
					}
				}
			}
		} catch (Exception e) {
			if (!Desc.isEmpty()) {
				UPM_Report_Library.Add_Step(
						UPM_Report_Library.Test_Step_Number, Desc + ": "
								+ DropDownList_Option_Index, LogStatus.FAIL,
						true);
				Exception_Content();
			}
		}
	}

	public static void UPM_Verify_DropDownList_Selected_Option(
			String DropDownList_Property, String Expected_Option, String Desc)
			throws Exception {
		try {
			if (!Expected_Option.matches("NA")) {
				String Actual_Option;
				WebElement Element = null;
				Element = UPM_Get_Element(DropDownList_Property);
				if (Element == null) {
					UPM_Report_Library.Add_Step(
							UPM_Report_Library.Test_Step_Number, Desc + ": "
									+ Expected_Option, LogStatus.FAIL, true);
					Exception_Content();
				} else {
					Select UPM_DropDownList = new Select(Element);
					Actual_Option = UPM_DropDownList.getFirstSelectedOption()
							.getText();
					if (Actual_Option.equalsIgnoreCase(Expected_Option)) {
						if (!Desc.isEmpty()) {
							UPM_Report_Library.Add_Step(
									UPM_Report_Library.Test_Step_Number, Desc
											+ ": Expected Option:"
											+ Expected_Option
											+ ", Actual Option: "
											+ Actual_Option, LogStatus.PASS,
									true);
						}
					} else {
						if (!Desc.isEmpty()) {
							UPM_Report_Library.Add_Step(
									UPM_Report_Library.Test_Step_Number, Desc
											+ ": Expected Option:"
											+ Expected_Option
											+ ", Actual Option: "
											+ Actual_Option, LogStatus.FAIL,
									true);
							Exception_Content();
						}
					}
				}
			}
		} catch (Exception e) {
			if (!Desc.isEmpty()) {
				UPM_Report_Library.Add_Step(
						UPM_Report_Library.Test_Step_Number, Desc + ": "
								+ Expected_Option, LogStatus.FAIL, true);
				Exception_Content();
			}
		}
	}

	public static void UPM_Verify_DropDownList_Options(
			String DropDownList_Property, String Expected_Options, String Desc)
			throws Exception {
		if (!Expected_Options.matches("NA")) {
			Thread.sleep(500);
			String Final_OutPut = "";
			List<WebElement> Actual_Option_List;
			String[] Expected_Option_List;
			WebElement Element = null;
			Element = UPM_Get_Element(DropDownList_Property);
			if (Element == null) {
				if (!Desc.isEmpty()) {
					UPM_Report_Library.Add_Step(
							UPM_Report_Library.Test_Step_Number, Desc + ": "
									+ Expected_Options, LogStatus.FAIL, true);
					Exception_Content();
				}
			} else {
				Select UPM_DropDownList = new Select(Element);
				Actual_Option_List = UPM_DropDownList.getOptions();
				Expected_Option_List = Expected_Options.split(";");
				int i;
				int j;
				int Exist;
				for (i = 0; i < Expected_Option_List.length; i++) {
					Exist = 0;
					for (j = 0; j < Actual_Option_List.size(); j++) {
						if (Expected_Option_List[i].toString()
								.equalsIgnoreCase(
										Actual_Option_List.get(j).getText())) {
							Exist = Exist + 1;
							break;
						}
					}
					if (Exist > 0) {
						Final_OutPut = Final_OutPut
								+ Expected_Option_List[i].toString()
								+ ": Exist ,";
					} else {
						Final_OutPut = Final_OutPut
								+ Expected_Option_List[i].toString()
								+ ": Not Exist ,";
					}
				}
				if (!Final_OutPut.contains("Not Exist")) {
					if (!Desc.isEmpty()) {
						UPM_Report_Library.Add_Step(
								UPM_Report_Library.Test_Step_Number, Desc
										+ ": Expected Option:" + Final_OutPut,
								LogStatus.PASS, true);
					}
				} else {
					if (!Desc.isEmpty()) {
						UPM_Report_Library.Add_Step(
								UPM_Report_Library.Test_Step_Number, Desc
										+ ": Expected Option:" + Final_OutPut,
								LogStatus.FAIL, true);
						Exception_Content();
					}
				}
			}
		}
	}

	public static void UPM_Verify_DropDownList_Options_Not_Exist(
			String DropDownList_Property, String Expected_Options, String Desc)
			throws Exception {
		if (!Expected_Options.matches("NA")) {
			Thread.sleep(500);
			String Final_OutPut = "";
			List<WebElement> Actual_Option_List;
			String[] Expected_Option_List;
			WebElement Element = null;
			Element = UPM_Get_Element(DropDownList_Property);
			if (Element == null) {
				if (!Desc.isEmpty()) {
					UPM_Report_Library.Add_Step(
							UPM_Report_Library.Test_Step_Number, Desc + ": "
									+ Expected_Options, LogStatus.FAIL, true);
					Exception_Content();
				}
			} else {
				Select UPM_DropDownList = new Select(Element);
				Actual_Option_List = UPM_DropDownList.getOptions();
				Expected_Option_List = Expected_Options.split(";");
				int i;
				int j;
				int Exist;
				for (i = 0; i < Expected_Option_List.length; i++) {
					Exist = 0;
					for (j = 0; j < Actual_Option_List.size(); j++) {
						if (Expected_Option_List[i].toString()
								.equalsIgnoreCase(
										Actual_Option_List.get(j).getText())) {
							Exist = Exist + 1;
							break;
						}
					}
					if (Exist > 0) {
						Final_OutPut = Final_OutPut
								+ Expected_Option_List[i].toString()
								+ ": Exist ,";
					} else {
						Final_OutPut = Final_OutPut
								+ Expected_Option_List[i].toString()
								+ ": Not Exist ,";
					}
				}
				if (Final_OutPut.contains("Not Exist")) {
					if (!Desc.isEmpty()) {
						UPM_Report_Library.Add_Step(
								UPM_Report_Library.Test_Step_Number, Desc
										+ ": Expected Option:" + Final_OutPut,
								LogStatus.PASS, true);
					}
				} else {
					if (!Desc.isEmpty()) {
						UPM_Report_Library.Add_Step(
								UPM_Report_Library.Test_Step_Number, Desc
										+ ": Expected Option:" + Final_OutPut,
								LogStatus.FAIL, true);
						Exception_Content();
					}
				}
			}
		}
	}

	public static void UPM_Set_CheckBox(String CheckBox_Pro,
			String CheckBox_Val, String Desc) throws Exception {
		Thread.sleep(1000);
		if (!CheckBox_Val.toString().matches("NA")) {
			String IsChecked;
			WebElement UPM_CheckBox = null;
			UPM_CheckBox = UPM_Get_Element(CheckBox_Pro);
			if (UPM_CheckBox == null) {
				UPM_Report_Library.Add_Step(
						UPM_Report_Library.Test_Step_Number, Desc,
						LogStatus.FAIL, true);
				Exception_Content();
			} else {
				IsChecked = UPM_CheckBox.getAttribute("checked");
				if (CheckBox_Val.matches("yes")) {

					if (!(IsChecked == "true")) {
						UPM_CheckBox.click();
						if (!Desc.isEmpty()) {
							UPM_Report_Library.Add_Step(
									UPM_Report_Library.Test_Step_Number, Desc,
									LogStatus.PASS, false);
						}
					}
				} else if (CheckBox_Val.matches("no")) {
					if (!(IsChecked == "true")) {
						Element_Action.click(UPM_CheckBox).build().perform();
						if (!Desc.isEmpty()) {
							UPM_Report_Library.Add_Step(
									UPM_Report_Library.Test_Step_Number, Desc,
									LogStatus.PASS, false);
						}
					}
				}

				else if (CheckBox_Val.matches("yes")) {
					if (IsChecked == null) {
						UPM_CheckBox.click();
						if (!Desc.isEmpty()) {
							UPM_Report_Library.Add_Step(
									UPM_Report_Library.Test_Step_Number, Desc,
									LogStatus.PASS, false);
						}
					}
				}

			}
		}
	}

	@SuppressWarnings("unchecked")
	public static void UPM_Click_RadioButton(String RadioButton_Pro,
			int ButtonPosition, Boolean RadioButton_Val, String Desc)
			throws Exception {
		if (!RadioButton_Val.toString().matches("NA")) {
			List<WebElement> UPM_RadioButton = null;
			UPM_RadioButton = (List<WebElement>) UPM_Get_Element(RadioButton_Pro);
			if (UPM_RadioButton == null) {
				if (!Desc.isEmpty()) {
					UPM_Report_Library.Add_Step(
							UPM_Report_Library.Test_Step_Number, Desc,
							LogStatus.FAIL, true);
					Exception_Content();
				}
			} else {
				UPM_RadioButton.get(ButtonPosition).click();
				if (!Desc.isEmpty()) {
					UPM_Report_Library.Add_Step(
							UPM_Report_Library.Test_Step_Number, Desc,
							LogStatus.PASS, false);
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	public static void UPM_Click_RadioButton_Edited(String RadioButton_Pro,
			String ButtonPosition, String Desc) throws Exception {
		if (ButtonPosition.matches("NA")) {
			List<WebElement> UPM_RadioButton = null;
			UPM_RadioButton = (List<WebElement>) UPM_Get_Element(RadioButton_Pro);
			if (UPM_RadioButton == null) {
				if (!Desc.isEmpty()) {
					UPM_Report_Library.Add_Step(
							UPM_Report_Library.Test_Step_Number, Desc,
							LogStatus.FAIL, true);
					Exception_Content();
				}
			} else {
				UPM_RadioButton.get(Integer.parseInt(ButtonPosition)).click();
				if (!Desc.isEmpty()) {
					UPM_Report_Library.Add_Step(
							UPM_Report_Library.Test_Step_Number, Desc,
							LogStatus.PASS, false);
				}
			}
		}
	}

	public static void UPM_Click_Object(String Object_Name_Pro, String Event,
			String Desc) throws Exception {
		// Robot robot = new Robot();
		// robot.keyPress(KeyEvent.VK_PAGE_DOWN);
		// robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
		if (!Event.matches("NA")) {
			WebElement UPM_Object = null;
			try {
				Thread.sleep(3000);
				Wait_For_Div();
				UPM_Object = UPM_Get_Element(Object_Name_Pro);
				if (UPM_Object == null) {
					if (!Desc.isEmpty()) {
						UPM_Report_Library.Add_Step(
								UPM_Report_Library.Test_Step_Number, Desc,
								LogStatus.FAIL, true);
						Exception_Content();
					}
				} else {
					UPM_Object.click();
					//Wait_For_Div();
					if (!Desc.isEmpty()) {
						UPM_Report_Library.Add_Step(
								UPM_Report_Library.Test_Step_Number, Desc,
								LogStatus.PASS, false);
					}
				}
			} catch (StaleElementReferenceException elementHasDisappeared) {
				UPM_Configuration_Library.Sound_Beep();
				UPM_Object = UPM_Get_Element(Object_Name_Pro);
				if (UPM_Object == null) {
					if (!Desc.isEmpty()) {
						UPM_Report_Library.Add_Step(
								UPM_Report_Library.Test_Step_Number, Desc,
								LogStatus.FAIL, true);
						Exception_Content();
					}
				} else {
					UPM_Object.click();
					Wait_For_Div();
					if (!Desc.isEmpty()) {
						UPM_Report_Library.Add_Step(
								UPM_Report_Library.Test_Step_Number, Desc,
								LogStatus.PASS, false);
					}
				}
			}

		}
	}

	public static void UPM_Click_Object_With_Wait(String Object_Name_Pro,String Wait_Time, String Desc) throws Exception 
	{
		if (!Wait_Time.matches("NA"))
		{
			WebElement UPM_Object = null;
			try {
				Thread.sleep(1000);
				Wait_For_Div();
				UPM_Object = UPM_Get_Element(Object_Name_Pro);
				if (UPM_Object == null) {
					if (!Desc.isEmpty()) {
						UPM_Report_Library.Add_Step(
								UPM_Report_Library.Test_Step_Number, Desc,
								LogStatus.FAIL, true);
						Exception_Content();
					}
				} else {
					UPM_Object.click();
					Thread.sleep(500);

					Thread.sleep(Integer.parseInt(Wait_Time));
					if (!Desc.isEmpty()) {
						UPM_Report_Library.Add_Step(
								UPM_Report_Library.Test_Step_Number, Desc,
								LogStatus.PASS, false);
					}
				}
			}

			catch (InvalidSelectorException excp1) {
				if (!Desc.isEmpty()) {
					UPM_Report_Library.Add_Step(
							UPM_Report_Library.Test_Step_Number, Desc + "\n"
									+ excp1, LogStatus.FAIL, true);
					Exception_Content();
				}
			}

			catch (StaleElementReferenceException elementHasDisappeared) 
			{
				UPM_Object = UPM_Get_Element(Object_Name_Pro);
				if (UPM_Object == null) 
				{
					if (!Desc.isEmpty())
					{
						UPM_Report_Library.Add_Step(
								UPM_Report_Library.Test_Step_Number, Desc,
								LogStatus.FAIL, true);
						Exception_Content();
					}
				} 
				else 
				{
					UPM_Object.click();
					Thread.sleep(Integer.parseInt(Wait_Time));
					if (!Desc.isEmpty()) 
					{
						UPM_Report_Library.Add_Step(
								UPM_Report_Library.Test_Step_Number, Desc,
								LogStatus.PASS, false);
					}
				}
			}

		}
	}

	public static void UPM_DblClick_Object(String Object_Name_Pro,
			String Event, String Desc) throws Exception {
		if (!Event.matches("NA")) {
			WebElement UPM_Object = null;
			UPM_Object = UPM_Get_Element(Object_Name_Pro);
			if (UPM_Object == null) {
				if (!Desc.isEmpty()) {
					UPM_Report_Library.Add_Step(
							UPM_Report_Library.Test_Step_Number, Desc,
							LogStatus.FAIL, true);
					Exception_Content();
				}
			} else {
				Element_Action.doubleClick(UPM_Object).build().perform();
				if (!Desc.isEmpty()) {
					UPM_Report_Library.Add_Step(
							UPM_Report_Library.Test_Step_Number, Desc,
							LogStatus.PASS, false);
				}
			}
		}
	}

	public static void UPM_Click_Tab(String Object_Name_Pro, String Event,
			String Desc) throws Exception {
		if (!Event.matches("NA")) {
			if (Desc.matches("Click on Hierarchie Tab")) {
				WebElement UPM_Object = null;
			}
			WebElement UPM_Object = null;
			try {
				Thread.sleep(500);
				// UPM_Function_Library.Activate_Latest_Browser("yes","Activating New Browser");
				Activate_Browsers();
				UPM_Object = UPM_Get_Element(Object_Name_Pro);
				if (UPM_Object == null) {
					if (!Desc.isEmpty()) {
						UPM_Report_Library.Add_Step(
								UPM_Report_Library.Test_Step_Number, Desc,
								LogStatus.FAIL, true);
						Exception_Content();
					}
				} else {
					if (UPM_Object.isDisplayed()) {
						UPM_Object.click();
						if (!Desc.isEmpty()) {
							UPM_Report_Library.Add_Step(
									UPM_Report_Library.Test_Step_Number, Desc,
									LogStatus.PASS, false);
						}
					}

				}
			} catch (StaleElementReferenceException elementHasDisappeared) {
				UPM_Configuration_Library.Sound_Beep();
				UPM_Object = UPM_Get_Element(Object_Name_Pro);
				if (UPM_Object.isDisplayed()) {
					UPM_Object.click();
					if (!Desc.isEmpty()) {
						UPM_Report_Library.Add_Step(
								UPM_Report_Library.Test_Step_Number, Desc,
								LogStatus.PASS, false);
					}
				}
			}

		}
	}

	public static String UPM_Capture_Screen_Shot(String ScreenShot_Location) {
		Screen_Shot = ((TakesScreenshot) ObjDriver)
				.getScreenshotAs(OutputType.FILE);
		Screen_Shot.renameTo(new File(ScreenShot_Location));
		return ScreenShot_Location;
	}

	public String Get_TimeStamp() {
		Long l = Calendar.getInstance().getTimeInMillis();
		return l.toString();
	}

	public static int UPM_Get_WebTable_Row_Count(WebElement Table_Element)
			throws Exception {
		return Table_Element.findElements(By.tagName("tr")).size();
	}

	public static int UPM_Get_WebTable_Column_Count(WebElement Table_Element)
			throws Exception {
		return Table_Element.findElements(By.tagName("tr")).get(0)
				.findElements(By.tagName("td")).size();
	}

	public static void UPM_Verify_WebTable_Data(String WebElement_Pro,
			String ExpectedText, String Desc) throws Exception {
		if (!ExpectedText.matches("NA")) {
			
			Wait_For_Div();
			
			int Row;
			int Column;
			int iterate;
			int iterate1;
			Boolean ExistFlg = false;
			WebElement Element = null;
			String text = repalceStrings(ExpectedText);
			Element = UPM_Get_Element(WebElement_Pro);

			// Enter the table(WebElement_Pro) having xpath from tbody....

			Row = Element.findElements(By.tagName("tr")).size();
			Column = Element.findElements(By.tagName("tr")).get(0)
					.findElements(By.tagName("td")).size();
			for (iterate = 0; iterate < Row; iterate++) {
				WebElement rowelement = Element.findElements(By.tagName("tr"))
						.get(iterate);

				for (iterate1 = 0; iterate1 < Column; iterate1++) {
					if (rowelement.findElements(By.tagName("td")).get(iterate1) != null) {
						if (text.equalsIgnoreCase(rowelement
								.findElements(By.tagName("td")).get(iterate1)
								.getText())) {
							highLightElement(ObjDriver,
									rowelement.findElements(By.tagName("td"))
											.get(iterate1));
							ExistFlg = true;
							break;
						}
					}
				}
				if (ExistFlg) {
					break;
				}
			}

			if (ExistFlg) {
				if (!Desc.isEmpty()) {
					UPM_Report_Library.Add_Step(
							UPM_Report_Library.Test_Step_Number, Desc + " : "
									+ ExpectedText, LogStatus.PASS, true);
				}
			} else {
				if (!Desc.isEmpty()) {
					UPM_Report_Library.Add_Step(
							UPM_Report_Library.Test_Step_Number, Desc + " : "
									+ ExpectedText, LogStatus.FAIL, true);
					Exception_Content();
				}
			}
		}
	}

	public static void UPM_Verify_WebTable_Data_Not_Exist(
			String WebElement_Pro, String ExpectedText, String Desc)
			throws Exception {
		if (!ExpectedText.matches("NA")) {
			int Row;
			int Column;
			int iterate;
			int iterate1;
			Boolean ExistFlg = false;
			WebElement Element = null;
			Element = UPM_Get_Element(WebElement_Pro);
			Row = Element.findElements(By.tagName("tr")).size();
			Column = Element.findElements(By.tagName("th")).size();// Element.findElements(By.tagName("tr")).get(0).findElements(By.tagName("td")).size();
			for (iterate = 0; iterate < Row; iterate++) {
				WebElement rowelement = Element.findElements(By.tagName("tr"))
						.get(iterate);
				for (iterate1 = 0; iterate1 < Column; iterate1++) {
					if (ExpectedText.equalsIgnoreCase(rowelement
							.findElements(By.tagName("td")).get(iterate1)
							.getText())) {
						highLightElement(
								ObjDriver,
								rowelement.findElements(By.tagName("td")).get(
										iterate1));
						ExistFlg = true;
						break;
					}
				}
				if (ExistFlg) {
					break;
				}
			}

			if (!ExistFlg) {
				if (!Desc.isEmpty()) {
					UPM_Report_Library.Add_Step(
							UPM_Report_Library.Test_Step_Number, Desc + " : "
									+ ExpectedText, LogStatus.PASS, true);
				}
			} else {
				if (!Desc.isEmpty()) {
					UPM_Report_Library.Add_Step(
							UPM_Report_Library.Test_Step_Number, Desc + " : "
									+ ExpectedText, LogStatus.FAIL, true);
					Exception_Content();
				}
			}
		}
	}

	public static void UPM_Verify_WebTable_Headers(String WebElement_Pro,
			String WebTable_Headers, String Desc) throws Exception {
		if (!WebTable_Headers.matches("NA")) {
			int Row;
			int Column;
			int iterate = 0;
			String Actual_Val;
			WebElement Element = null;
			Element = UPM_Get_Element(WebElement_Pro);
			List<WebElement> AllHeaders = Element
					.findElements(By.tagName("th"));
			String[] WebTable_Headers_arr = WebTable_Headers.split(";");
			for (WebElement Header : AllHeaders) {
				Actual_Val = WebTable_Headers_arr[iterate];
				if (Header.getText().equalsIgnoreCase(Actual_Val)) {
					if (!Desc.isEmpty()) {
						UPM_Report_Library.Add_Step(
								UPM_Report_Library.Test_Step_Number, "Header "
										+ Header.getText()
										+ " is present in the table",
								LogStatus.PASS, false);
					}
				} else {
					if (!Desc.isEmpty()) {
						UPM_Report_Library.Add_Step(
								UPM_Report_Library.Test_Step_Number, "Header "
										+ Header.getText()
										+ " is not present in the table",
								LogStatus.FAIL, true);
						Exception_Content();
					}
				}
				iterate++;
			}
		}
	}

	public static void Verify_TextPresent(String Verfy_TextValue, String Desc)
			throws Exception {
		if (!Verfy_TextValue.matches("NA")) {
			Thread.sleep(2000);
		//	Wait_For_Div();
			Thread.sleep(2000);
			Activate_Browsers();
			// ObjDriver.getPageSource()
			String verfytextval2 = repalceStrings(Verfy_TextValue);
			// System.out.println(verfytextval2);
			if (ObjDriver.findElement(By.tagName("body")).getText()
					.contains(verfytextval2)) {
				if (!Desc.isEmpty()) {
					UPM_Report_Library.Add_Step(
							UPM_Report_Library.Test_Step_Number, Desc
									+ " : Expected Value : " + Verfy_TextValue,
							LogStatus.PASS, true);
						}
					} else if (ObjDriver.getPageSource().contains(verfytextval2)) {
				if (!Desc.isEmpty()) 
					{
					UPM_Report_Library.Add_Step(
							UPM_Report_Library.Test_Step_Number, Desc
									+ " : Expected Value : " + Verfy_TextValue,
							LogStatus.PASS, true);
					}
						}
						else 
						{
							if (!Desc.isEmpty()) {
								UPM_Report_Library.Add_Step(
								UPM_Report_Library.Test_Step_Number, Desc
									+ " : Expected Value : " + Verfy_TextValue,
							LogStatus.FAIL, true);
					Exception_Content();
				}
			}
		}
	}

	public static void UPM_Verify_Excel_Cell_Data(String Expected_Value,
			String Desc) throws Exception {
		if (!Expected_Value.matches("NA")) {
			UPM_Configuration_Library.Verify_Excel_Contains_Data(
					Expected_Value, Desc);
		}
	}

	public static void UPM_Get_WebElement_Text(String WebElement_Pro,
			String Event, String Desc) throws Exception {
		String Ret;
		Ret = null;
		if (!Event.matches("NA")) {
			Thread.sleep(1000);
			List<WebElement> Elements = null;
			WebElement Element = null;
			// String Actual_Property = WebElement_Pro.substring(6);
			Elements = UPM_Get_Elements(WebElement_Pro);// UPM_Browser_Elements
														// =
														// ObjDriver.findElements(By.className(Actual_Property));
			if (Elements == null) {
				if (!Desc.isEmpty()) {
					UPM_Report_Library.Add_Step(
							UPM_Report_Library.Test_Step_Number, Desc
									+ " Value : " + Ret, LogStatus.FAIL, true);
					Exception_Content();
				}
			} else {
				int i;
				int Element_Cnt = Elements.size();
				for (i = 0; i <= Element_Cnt; i++) {
					Element = Elements.get(i);
					if (Element.isDisplayed()) {
						Ret = Element.getText();
						UPM_Configuration_Library
								.Set_Active_Cell_Value_overwrite(1, Ret);
						if (!Desc.isEmpty()) {
							UPM_Report_Library.Add_Step(
									UPM_Report_Library.Test_Step_Number, Desc
											+ " Value : " + Ret,
									LogStatus.PASS, true);
						}
					} else {
						if (!Desc.isEmpty()) {
							UPM_Report_Library.Add_Step(
									UPM_Report_Library.Test_Step_Number, Desc
											+ " Value : " + Ret,
									LogStatus.FAIL, true);
							Exception_Content();
						}
					}
					break;
				}
			}
		}
	}

	public static void UPM_Verify_WebElement_Actual(String WebElement_Pro,
			String Expected_Value, String Desc) throws Exception {
		if (!Expected_Value.matches("NA")) {
			try {
				Thread.sleep(1000);
				List<WebElement> Elements = null;
				WebElement Element = null;
				String Expected = repalceStrings(Expected_Value);
				// String Actual_Property = WebElement_Pro.substring(6);
				Elements = UPM_Get_Elements(WebElement_Pro);// UPM_Browser_Elements
															// =
															// ObjDriver.findElements(By.className(Actual_Property));
				if (Elements == null) {
					if (!Desc.isEmpty()) {
						UPM_Report_Library.Add_Step(
								UPM_Report_Library.Test_Step_Number, Desc
										+ "Expected Value : " + Expected,
								LogStatus.FAIL, true);
						Exception_Content();
					}
				} else {
					int i;
					int flag = 0;
					int Element_Cnt = Elements.size();
					for (i = 0; i < Element_Cnt; i++) {
						Element = Elements.get(i);
						if (Element.isDisplayed()) {

							if (Element.getText().equalsIgnoreCase(Expected)) {
								if (!Desc.isEmpty()) {
									UPM_Report_Library
											.Add_Step(
													UPM_Report_Library.Test_Step_Number,
													Desc + "Expected Value : "
															+ Expected,
													LogStatus.PASS, true);
								}
								flag++;
							} else if (Element.getAttribute("value")
									.equalsIgnoreCase(Expected)) {
								if (!Desc.isEmpty()) {
									UPM_Report_Library
											.Add_Step(
													UPM_Report_Library.Test_Step_Number,
													Desc + "Expected Value : "
															+ Expected,
													LogStatus.PASS, true);
								}
								flag++;
							}
							break;
						}
					}
					if (flag == 0) {
						UPM_Report_Library.Add_Step(
								UPM_Report_Library.Test_Step_Number, Desc
										+ "Expected Value : " + Expected,
								LogStatus.FAIL, true);
						Exception_Content();
					}
				}
			}

			catch (Exception e) {
				UPM_Report_Library.Add_Step(
						UPM_Report_Library.Test_Step_Number, Desc
								+ "Expected Value : " + Expected_Value
								+ "; Exception: " + e, LogStatus.FAIL, true);
				Exception_Content();
			}
		}
	}

	public static void UPM_Verify_WebElement_Actual_Contains(
			String WebElement_Pro, String Expected_Value, String Desc)
			throws Exception {
		if (!Expected_Value.matches("NA")) {
			Thread.sleep(1000);
			List<WebElement> Elements = null;
			WebElement Element = null;
			String Actual_Property = WebElement_Pro.substring(6);
			Elements = UPM_Get_Elements(WebElement_Pro);// UPM_Browser_Elements
														// =
														// ObjDriver.findElements(By.className(Actual_Property));
			if (Elements == null) {
				if (!Desc.isEmpty()) {
					UPM_Report_Library.Add_Step(
							UPM_Report_Library.Test_Step_Number, Desc
									+ "Expected Value : " + Expected_Value,
							LogStatus.FAIL, true);
					Exception_Content();
				}
			} else {
				int i;
				int Element_Cnt = Elements.size();
				for (i = 0; i <= Element_Cnt; i++) {
					Element = Elements.get(i);
					if (Element.isDisplayed()) {
						int a = Expected_Value.length();
						int b = Element.getText().length();
						Boolean x;
						if (a >= b) {
							x = Expected_Value.contains(Element.getText());
						} else {
							x = Element.getText().contains(Expected_Value);
						}

						if (x) {
							if (!Desc.isEmpty()) {
								UPM_Report_Library.Add_Step(
										UPM_Report_Library.Test_Step_Number,
										Desc + "Expected Value : "
												+ Expected_Value,
										LogStatus.PASS, true);
							}
						} else {
							if (!Desc.isEmpty()) {
								UPM_Report_Library.Add_Step(
										UPM_Report_Library.Test_Step_Number,
										Desc + "Expected Value : "
												+ Expected_Value,
										LogStatus.FAIL, true);
								Exception_Content();
							}
						}
						break;
					}
				}
			}
		}
	}

	public static void UPM_Verify_WebElement_Enabled_Time(
			String WebElement_Pro, String Expected_Time, String Desc)
			throws Exception {
		long start = System.currentTimeMillis();
		if (!Expected_Time.matches("NA")) {
			WebElement Element = null;

			Element = UPM_Get_Element(WebElement_Pro);
			if (Element == null) {
				if (!Desc.isEmpty()) {
					UPM_Report_Library.Add_Step(
							UPM_Report_Library.Test_Step_Number, Desc,
							LogStatus.FAIL, true);
					Exception_Content();
				}
			} else {
				if (Element.isEnabled()) {
					long finish = System.currentTimeMillis();
					long totalTime = finish - start;
					long ExpectedTime = Long.parseLong(Expected_Time);
					if (totalTime < ExpectedTime) {
						if (!Desc.isEmpty()) {
							UPM_Report_Library.Add_Step(
									UPM_Report_Library.Test_Step_Number, Desc
											+ " : " + "Expected Time :"
											+ Expected_Time + " MS"
											+ ", Actual Time : " + totalTime
											+ " MS", LogStatus.PASS, true);
						}
					} else {
						if (!Desc.isEmpty()) {
							UPM_Report_Library.Add_Step(
									UPM_Report_Library.Test_Step_Number, Desc
											+ " : " + "Expected Time :"
											+ Expected_Time + " MS"
											+ ", Actual Time : " + totalTime
											+ " MS", LogStatus.FAIL, true);
							Exception_Content();
						}
					}

				} else {
					if (!Desc.isEmpty()) {
						UPM_Report_Library.Add_Step(
								UPM_Report_Library.Test_Step_Number, Desc,
								LogStatus.FAIL, true);
						Exception_Content();
					}
				}
			}
		}
	}

	public static void UPM_Verify_WebElement_Disabled(String WebElement_Pro,
			String Event, String Desc) throws Exception {
		if (!Event.matches("NA")) {
			WebElement Element = null;
			Thread.sleep(2000);
			Element = UPM_Get_Element(WebElement_Pro);
			if (Element == null) {
				if (!Desc.isEmpty()) {
					UPM_Report_Library.Add_Step(
							UPM_Report_Library.Test_Step_Number, Desc,
							LogStatus.FAIL, true);
					Exception_Content();
				}
			} else {
				if (!Element.isEnabled()
						|| Element.getAttribute("class").toString()
								.equalsIgnoreCase("InputDisabled")) {
					if (!Desc.isEmpty()) {
						UPM_Report_Library.Add_Step(
								UPM_Report_Library.Test_Step_Number, Desc,
								LogStatus.PASS, true);
					}
				} else {
					if (!Desc.isEmpty()) {
						UPM_Report_Library.Add_Step(
								UPM_Report_Library.Test_Step_Number, Desc,
								LogStatus.FAIL, true);
						Exception_Content();
					}
				}
			}
		}
	}

	public static void UPM_Verify_WebElement_Not_Editable(
			String WebElement_Pro, String Event, String Desc) throws Exception {
		if (!Event.matches("NA")) {
			WebElement Element = null;
			Element = UPM_Get_Element(WebElement_Pro);
			if (Element == null) {
				if (!Desc.isEmpty()) {
					UPM_Report_Library.Add_Step(
							UPM_Report_Library.Test_Step_Number, Desc,
							LogStatus.FAIL, true);
					Exception_Content();
				}
			} else {
				String Attribute = Element.getAttribute("disabled");
				if (!Attribute.isEmpty()) {
					if (Attribute.matches("true")) {
						if (!Desc.isEmpty()) {
							UPM_Report_Library.Add_Step(
									UPM_Report_Library.Test_Step_Number, Desc,
									LogStatus.PASS, true);
						}
					} else {
						if (!Desc.isEmpty()) {
							UPM_Report_Library.Add_Step(
									UPM_Report_Library.Test_Step_Number, Desc,
									LogStatus.FAIL, true);
							Exception_Content();
						}
					}
				} else {
					if (!Desc.isEmpty()) {
						UPM_Report_Library.Add_Step(
								UPM_Report_Library.Test_Step_Number, Desc,
								LogStatus.FAIL, true);
						Exception_Content();
					}
				}
			}
		}
	}

	public static void UPM_Verify_WebElement_Exist_XPATH(String WebElement_Pro,
			String Event, String Desc) throws Exception {
		if (!Event.matches("NA")) {
			Thread.sleep(500);
			WebElement Element = null;
			WebElement_Pro = WebElement_Pro.substring(6);
			if (ObjDriver.findElements(By.xpath(WebElement_Pro)).size() > 0) {
				if (!Desc.isEmpty()) {
					UPM_Report_Library.Add_Step(
							UPM_Report_Library.Test_Step_Number, Desc,
							LogStatus.PASS, true);
				}
			}
			// if(Element == null)
			// {
			// if(!Desc.isEmpty())
			// {
			// UPM_Report_Library.Add_Step(UPM_Report_Library.Test_Step_Number,
			// Desc, LogStatus.FAIL, true);Exception_Content();
			// }
			// }
			else {
				if (!Desc.isEmpty()) {
					UPM_Report_Library.Add_Step(
							UPM_Report_Library.Test_Step_Number, Desc,
							LogStatus.FAIL, true);
					Exception_Content();
				}
			}
		}
	}

	public static void UPM_Verify_WebElement_Not_Exist_XPATH(
			String WebElement_Pro, String Event, String Desc) throws Exception {
		if (!Event.matches("NA")) {
			Thread.sleep(500);
			WebElement Element = null;
			WebElement_Pro = WebElement_Pro.substring(6);
			if (ObjDriver.findElements(By.xpath(WebElement_Pro)).size() > 0) {
				if (!Desc.isEmpty()) {
					UPM_Report_Library.Add_Step(
							UPM_Report_Library.Test_Step_Number, Desc,
							LogStatus.FAIL, true);
					Exception_Content();
				}
			} else {
				if (!Desc.isEmpty()) {
					UPM_Report_Library.Add_Step(
							UPM_Report_Library.Test_Step_Number, Desc,
							LogStatus.PASS, true);
				}
			}
		}
	}

	// public static void UPM_Verify_WebElement_Not_Exist(String
	// WebElement_Pro,String Event,String Desc) throws Exception
	// {
	// if(!Event.matches("NA"))
	// {
	// Thread.sleep(500);
	// WebElement Element = null;
	// Element = Get_Locator_For_Exist(WebElement_Pro);
	// if(Element == null)
	// {
	// if(!Desc.isEmpty())
	// {
	// UPM_Report_Library.Add_Step(UPM_Report_Library.Test_Step_Number, Desc,
	// LogStatus.PASS, true);
	// }
	// }
	// else
	// {
	// if(!Desc.isEmpty())
	// {
	// UPM_Report_Library.Add_Step(UPM_Report_Library.Test_Step_Number, Desc,
	// LogStatus.FAIL, true);Exception_Content();
	// }
	// }
	// }
	// }

	public static void UPM_Verify_WebElement_Enabled(String WebElement_Pro,
			String Event, String Desc) throws Exception {
		if (!Event.matches("NA")) {
			Thread.sleep(500);
			Wait_For_Div();
			WebElement Element = null;
			Element = UPM_Get_Element(WebElement_Pro);
			View_WebElement_Down_Key(WebElement_Pro, "yes", "Move");
			if (Element == null) {
				if (!Desc.isEmpty()) {
					UPM_Report_Library.Add_Step(
							UPM_Report_Library.Test_Step_Number, Desc,
							LogStatus.FAIL, true);
					Exception_Content();
				}
			} else {
				if (Element.isDisplayed()) {
					if (!Desc.isEmpty()) {
						UPM_Report_Library.Add_Step(
								UPM_Report_Library.Test_Step_Number, Desc,
								LogStatus.PASS, true);
					}
				} else {
					if (!Desc.isEmpty()) {
						UPM_Report_Library.Add_Step(
								UPM_Report_Library.Test_Step_Number, Desc,
								LogStatus.FAIL, true);
						Exception_Content();
					}
				}
			}
		}
	}

	public static void UPM_Verify_WebElement_ReadOnly(String WebElement_Pro,
			String Event, String Desc) throws Exception {
		if (!Event.matches("NA")) {
			try {
			//	Wait_For_Div();
				Thread.sleep(3000);
				WebElement Element = null;
				// Element = UPM_Get_Element(WebElement_Pro);
				String Actual_Property = WebElement_Pro.substring(6);
				Element = ObjDriver.findElement(By.xpath(Actual_Property));

				if (Element == null) {
					UPM_Report_Library.Add_Step(
							UPM_Report_Library.Test_Step_Number, Desc,
							LogStatus.FAIL, true);
					Exception_Content();
				} else {
					if (Element.getAttribute("readOnly").equals("true")
							|| Element.getAttribute("readonly")
									.equalsIgnoreCase("")) {
						if (!Desc.isEmpty()) {
							UPM_Report_Library.Add_Step(
									UPM_Report_Library.Test_Step_Number, Desc,
									LogStatus.PASS, true);
						}
					} else {
						if (!Desc.isEmpty()) {
							UPM_Report_Library.Add_Step(
									UPM_Report_Library.Test_Step_Number, Desc,
									LogStatus.FAIL, true);
							Exception_Content();
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void UPM_Verify_Cell_Value_Order_Ascending(
			String Value_Order, String Desc) throws Exception {
		if (!Value_Order.matches("NA")) {

			List<String> value_Order_List = new ArrayList<String>(
					Arrays.asList(Value_Order.split(";")));
			if (value_Order_List.size() == 1) {
				if (!Desc.isEmpty()) {
					UPM_Report_Library.Add_Step(
							UPM_Report_Library.Test_Step_Number,
							"First Value :"
									+ value_Order_List.get(0).toString(),
							LogStatus.PASS, true);
				}
			}
			if (value_Order_List.size() > 1) {
				List<String> value_Order_List_Temp = new ArrayList<String>(
						value_Order_List);
				Collections.sort(value_Order_List_Temp);
				if (value_Order_List_Temp.equals(value_Order_List)) {
					if (!Desc.isEmpty()) {
						UPM_Report_Library.Add_Step(
								UPM_Report_Library.Test_Step_Number, Desc
										+ " :" + Value_Order, LogStatus.PASS,
								true);
					}
				} else {
					if (!Desc.isEmpty()) {
						UPM_Report_Library.Add_Step(
								UPM_Report_Library.Test_Step_Number, Desc
										+ " :" + Value_Order, LogStatus.FAIL,
								true);
						Exception_Content();
					}
				}

			}
		}
	}

	// Element highlighter code
	public static void highLightElement(WebDriver driver, WebElement element)
			throws InterruptedException {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;

			js.executeScript(
					"arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');",
					element);
				//Thread.sleep(100);
			js.executeScript(
					"arguments[0].setAttribute('style','border: solid 2px white')",
					element);
		} catch (WebDriverException e) {
			UPM_Configuration_Library.Sound_Beep();		
			System.out.println(e.getMessage());
		}
	}

	public static void Soft_Wait(Integer MS, String Desc) throws Exception {
		if (!MS.toString().matches("NA")) {
			Thread.sleep(MS);
			if (!Desc.isEmpty()) {
				UPM_Report_Library.Add_Step(
						UPM_Report_Library.Test_Step_Number, Desc,
						LogStatus.PASS, false);
			}
		}
	}

	public static void Soft_Wait(String MS, String Desc) throws Exception {
		if (!MS.matches("NA")) {
			Thread.sleep(Integer.parseInt(MS));
			if (!Desc.isEmpty()) {
				UPM_Report_Library.Add_Step(
						UPM_Report_Library.Test_Step_Number, Desc,
						LogStatus.PASS, false);
			}
		}
	}

	public static void UPM_Click_Last_Pluss_Button(String Object_Name_Pro,
			String Event, String Desc) throws Exception {
		if (!Event.matches("NA")) {
			Thread.sleep(800);
			Wait_For_Div();
			WebElement UPM_Object = null;
			String Actual_Property = Object_Name_Pro.substring(6);
			UPM_Browser_Elements = ObjDriver.findElements(By
					.className(Actual_Property));
			// Wait_For_Object.until(ExpectedConditions.visibilityOfAllElements(UPM_Browser_Elements));
			if (UPM_Browser_Elements == null) {
				if (!Desc.isEmpty()) {
					UPM_Report_Library.Add_Step(
							UPM_Report_Library.Test_Step_Number, Desc,
							LogStatus.FAIL, true);
					Exception_Content();
				}
			} else {
				int Element_Cnt = UPM_Browser_Elements.size();

				UPM_Object = UPM_Browser_Elements.get(Element_Cnt - 1);
				if (UPM_Object.isDisplayed()) {
					UPM_Object.click();
					if (!Desc.isEmpty()) {
						UPM_Report_Library.Add_Step(
								UPM_Report_Library.Test_Step_Number, Desc,
								LogStatus.PASS, false);
					}
				}
			}
		}
	}

	public static void UPM_Click_Pluss_Button_ByIndex(String Object_Name_Pro,
			String Event, String Desc) throws Exception {
		if (!Event.matches("NA")) {
			Wait_For_Div();
			List<WebElement> UPM_Objects = null;
			WebElement UPM_Object = null;
			String Actual_Property = Object_Name_Pro.substring(6);
			UPM_Browser_Elements = ObjDriver.findElements(By
					.className(Actual_Property));
			if (UPM_Browser_Elements == null) {
				if (!Desc.isEmpty()) {
					UPM_Report_Library.Add_Step(
							UPM_Report_Library.Test_Step_Number, Desc,
							LogStatus.FAIL, true);
					Exception_Content();
				}
			} else {

				int Element_Cnt = UPM_Browser_Elements.size();

				UPM_Object = UPM_Browser_Elements.get(Element_Cnt);
				if (UPM_Object.isDisplayed()) {
					UPM_Object.click();
					if (!Desc.isEmpty()) {
						UPM_Report_Library.Add_Step(
								UPM_Report_Library.Test_Step_Number, Desc,
								LogStatus.PASS, false);
					}
				}
			}
		}
	}

	public static void UPM_Click_WebTable_Data(String WebElement_Pro,
			String ExpectedText, String Desc) throws Exception {
		if (!ExpectedText.matches("NA")) {
			int Row;
			int Column;
			int iterate;
			int iterate1;
			WebElement Element = null;
			WebElement Actual_Element = null;
			Element = UPM_Get_Element(WebElement_Pro);
			List<WebElement> element2 = Element.findElements(By.tagName("tr"));
			Row = element2.size();
			System.out.println(Row);
			// Row = Element.findElements(By.tagName("tr")).size();

			// Element.findElements(By.tagName("tr")).get(0).findElements(By.tagName("td")).size();
			/*
			 * List<WebElement> rows=Element.findElements(By.tagName("tr"));
			 * 
			 * for(int rnum=0;rnum<rows.size();rnum++) { List<WebElement>
			 * columns=rows.get(rnum).findElements(By.tagName("td"));
			 * System.out.println("Number of columns:"+columns.size()); for(int
			 * cnum=0;cnum<columns.size();cnum++) {
			 * System.out.println(columns.get(cnum).getText()); } }
			 */

			Boolean Flag = false;
			for (iterate = 0; iterate < Row && Flag == false; iterate++) {
				Column = element2.get(iterate).findElements(By.tagName("td"))
						.size();
				System.out.println(Column);

				for (iterate1 = 0; iterate1 < Column; iterate1++) {
					Actual_Element = Element.findElements(By.tagName("td"))
							.get(iterate1);
					if (Actual_Element.isEnabled()) {
						if (ExpectedText.equalsIgnoreCase(Element
								.findElements(By.tagName("td")).get(iterate1)
								.getText())) {
							highLightElement(
									ObjDriver,
									Element.findElements(By.tagName("td")).get(
											iterate1));

							Actual_Element.click();
							Flag = true;
							break;

						}
					}
				}

			}
			Thread.sleep(2000);
		}
	}

	public static void UPM_Click_Table_Row(String Table_Pro, String Row,
			String Desc) throws Exception {
		if (!Row.matches("NA")) {
			//Wait_For_Div();
			int Row1;
			WebElement Table_Element = null;
			WebElement Table_Row = null;
			try {
				Table_Element = UPM_Get_Element(Table_Pro);

				if (Table_Element == null) {
					if (!Desc.isEmpty()) {
						UPM_Report_Library.Add_Step(
								UPM_Report_Library.Test_Step_Number, Desc,
								LogStatus.FAIL, true);
						Exception_Content();
					}
				} else {
					Table_Row = Table_Element.findElement(By
							.xpath("//tbody/tr[@id='row" + Row + "'" + "]"));
					highLightElement(ObjDriver, Table_Row);
					Element_Action.click(Table_Row).build().perform();
					if (!Desc.isEmpty()) {
						UPM_Report_Library.Add_Step(
								UPM_Report_Library.Test_Step_Number, Desc,
								LogStatus.PASS, false);
					}
				}
			}

			catch (Exception e) {
				if (!Desc.isEmpty()) {
					UPM_Report_Library.Add_Step(
							UPM_Report_Library.Test_Step_Number, Desc + ": "
									+ e, LogStatus.FAIL, true);
					Exception_Content();
				}
			}
		}
	}

	public static void UPM_Dbl_Click_Table_Cell_By_RowCol(String Table_Pro,
			String RowCol, String Desc) throws Exception {
		if (!RowCol.matches("NA")) {
			int Row;
			int Column;
			WebElement Table_Element = null;
			WebElement Table_Row = null;
			WebElement Cell_We_Need = null;

			Table_Element = UPM_Get_Element(Table_Pro);

			if (Table_Element == null) {
				if (!Desc.isEmpty()) {
					UPM_Report_Library.Add_Step(
							UPM_Report_Library.Test_Step_Number, Desc,
							LogStatus.FAIL, true);
					Exception_Content();
				}
			} else {
				String[] RowCol_Val = RowCol.split(":");
				Row = Integer.parseInt(RowCol_Val[0]);
				Column = Integer.parseInt(RowCol_Val[1]);

				Table_Row = Table_Element.findElement(By
						.xpath("//tbody/tr[@id='row" + Row + "'" + "]"));
				// System.out.println(Table_Row.getText());
				highLightElement(ObjDriver, Table_Row);
				Cell_We_Need = Table_Row.findElement(By.xpath("//td[" + Column
						+ "]"));
				// System.out.println(Cell_We_Need.getText());
				// highLightElement(ObjDriver,Cell_We_Need);
				Element_Action.doubleClick(Table_Row).build().perform();
				if (!Desc.isEmpty()) {
					UPM_Report_Library.Add_Step(
							UPM_Report_Library.Test_Step_Number, Desc,
							LogStatus.PASS, false);
				}
			}
		}
	}

	public static void UPM_Click_Image_By_Index(String Image_Property,
			String Image_Index, String Desc) throws Exception {
		if (!Image_Index.matches("NA")) {
			Thread.sleep(800);
			List<WebElement> UPM_Images = null;
			WebElement UPM_Image = null;
			UPM_Images = UPM_Get_Elements(Image_Property);
			if (UPM_Images == null) {
				if (!Desc.isEmpty()) {
					UPM_Report_Library.Add_Step(
							UPM_Report_Library.Test_Step_Number, Desc,
							LogStatus.FAIL, true);
					Exception_Content();
				}
			} else {
				int Element_Cnt = UPM_Images.size();
				UPM_Image = UPM_Images.get(Integer.parseInt(Image_Index));
				Element_Action.moveToElement(UPM_Image).perform();
				UPM_Image.click();
				if (!Desc.isEmpty()) {
					UPM_Report_Library.Add_Step(
							UPM_Report_Library.Test_Step_Number, Desc,
							LogStatus.PASS, false);
				}
			}
		}
	}

	public static void Wait_For_Div() throws Exception {
		try 
		{
				//WebDriverWait wait = new WebDriverWait(ObjDriver, 60);
				//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='menu_form_id:loadingPanelContentTable']/tbody/tr/td/img")));
				
		
		Thread.sleep(1200);
		Activate_Browsers();
		List<WebElement> UPM_Div_Objects = null;
		WebElement UPM_Div_Object = null;
		  
		  
		  for (int i = 0; i <= 60; i++) {
			
				UPM_Div_Objects = UPM_Get_Elements_For_Div("tagName=div");
				int Div_Element_Cnt = UPM_Div_Objects.size();
				if(Div_Element_Cnt>1)
				{
				UPM_Div_Object = UPM_Div_Objects.get(Div_Element_Cnt - 1);
					if ((!UPM_Div_Object.getAttribute("id").contains(
							"loadingPanelCursorDiv"))) {
						break;
					}  
					Thread.sleep(500);
				}
				else{
					UPM_Div_Object = UPM_Div_Objects.get(Div_Element_Cnt);
					if ((!UPM_Div_Object.getAttribute("id").contains(
							"loadingPanelCursorDiv"))) {
						break;
					}  	
					Thread.sleep(700);
				}
			 }

		}

		  catch (StaleElementReferenceException e) {
			UPM_Configuration_Library.Sound_Beep();
			e.printStackTrace();
			Activate_Browsers();
		} catch (Exception e) {
			UPM_Configuration_Library.Sound_Beep();
			e.printStackTrace();
			Activate_Browsers();
		}
	}
	
	public static void wait_for_div_2()
	{
		WebDriverWait wait = new WebDriverWait(ObjDriver, 45);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='menu_form_id:loadingPanelContentTable']/tbody/tr/td/img")));
	}

	public static void UPM_Get_Element_By_Attribute(String Event, String Desc) {

	}

	public static void UPM_Scroll_Down(String Event, String Desc) {
		if (!Event.matches("NA")) {
			((JavascriptExecutor) ObjDriver).executeScript("scroll(0,250);");
		}

	}

	public static void UPM_Scroll_Up(String Event, String Desc) {
		if (!Event.matches("NA")) {
			JavascriptExecutor JsE = (JavascriptExecutor) ObjDriver;
			JsE.executeScript("window.scrollBy(0,-250)", "");
		}
	}

	public static void FluentWait_Div() {
		// Wait<WebDriver> Wait_For_Div = new FluentWait<WebDriver>(ObjDriver)
		// //Wait for the condition
		// .withTimeout(1, TimeUnit.SECONDS)
		// // which to check for the condition with interval of 5 seconds.
		// .pollingEvery(1, TimeUnit.SECONDS)
		// //Which will ignore the NoSuchElementException
		// .ignoring(NoSuchElementException.class);
		// Wait_For_Div.until(ExpectedConditions.invisibilityOfElementLocated(ObjDriver.findElements(By.xpath("//div[contains(@id,'loadingPanelCursorDiv')]")).size()
		// > 0));

	}

	public static void Click_Window_Object(String Event, String Desc)
			throws Exception {
		if (!Event.matches("NA")) {
			Robot Window_Object = new Robot();
			Window_Object.keyPress(KeyEvent.VK_ENTER);
			Window_Object.keyRelease(KeyEvent.VK_ENTER);
			UPM_Report_Library.Add_Step(UPM_Report_Library.Test_Step_Number,
					Desc, LogStatus.PASS, false);
		}
	}

	public static void Click_Window_ALT_S_Key(String Event, String Desc)
			throws Exception {
		if (!Event.matches("NA")) {
			Robot Window_Object = new Robot();
			Window_Object.keyPress(KeyEvent.VK_ALT);
			Window_Object.keyPress(KeyEvent.VK_S);
			Window_Object.keyRelease(KeyEvent.VK_S);
			Window_Object.keyRelease(KeyEvent.VK_ALT);
			if (!Desc.isEmpty()) {
				UPM_Report_Library.Add_Step(
						UPM_Report_Library.Test_Step_Number, Desc,
						LogStatus.PASS, false);

			}
		}
	}

	public static void Click_Window_ALT_S_Key(String Event) throws Exception {
		if (!Event.matches("NA")) {
			Robot Window_Object = new Robot();
			Window_Object.keyPress(KeyEvent.VK_ALT);
			Window_Object.keyPress(KeyEvent.VK_S);
			Window_Object.keyRelease(KeyEvent.VK_S);
			Window_Object.keyRelease(KeyEvent.VK_ALT);
		}
	}

	public static void Click_Window_ALT_Down_Key(String occurrence, String Desc)
			throws Exception {
		if (!occurrence.matches("NA")) {
			Robot Window_Object = new Robot();
			for (int i = 0; i <= Integer.parseInt(occurrence); i++) {
				Window_Object.keyPress(KeyEvent.VK_DOWN);
				Window_Object.keyRelease(KeyEvent.VK_DOWN);
			}
			if (!Desc.isEmpty()) {
				UPM_Report_Library.Add_Step(
						UPM_Report_Library.Test_Step_Number, Desc,
						LogStatus.PASS, false);
			}
		}
	}

	public static void Click_WebElement_Down_Key(String WebElement_Pro,
			String occurrence, String Desc) throws Exception {
		if (!occurrence.matches("NA")) {
			WebElement Element = null;
			Element = UPM_Get_Element(WebElement_Pro);
			Element.sendKeys(Keys.TAB);
			Robot Window_Object = new Robot();
			for (int i = 0; i <= Integer.parseInt(occurrence); i++) {
				Window_Object.keyPress(KeyEvent.VK_DOWN);
				Window_Object.keyRelease(KeyEvent.VK_DOWN);
			}
		}
	}

	public static void Type_WebElement_Key(String WebElement_Pro,
			String KeyVal, String Desc) throws Exception {
		if (!KeyVal.matches("NA")) {
			if (KeyVal.matches("Current Date")) {
				KeyVal = UPM_Get_Current_Date();
			}
			WebElement Element = null;
			Element = UPM_Get_Element(WebElement_Pro);

			Element.click();
			Thread.sleep(1000);
			Element.clear();
			writeKeyboard(KeyVal);
			Element.sendKeys(Keys.TAB);
			UPM_Report_Library.Add_Step(UPM_Report_Library.Test_Step_Number,
					Desc + " : " + KeyVal, LogStatus.PASS, false);
		}
	}

	public static void Click_Window_ALT_Down_Key(String occurrence)
			throws Exception {
		if (!occurrence.matches("NA")) {
			Robot Window_Object = new Robot();
			for (int i = 0; i <= Integer.parseInt(occurrence); i++) {
				Window_Object.keyPress(KeyEvent.VK_DOWN);
				Window_Object.keyRelease(KeyEvent.VK_DOWN);
			}
		}
	}

	public static void writeKeyboard(String st) throws AWTException {
		Robot Window_Object = new Robot();
		char[] arr = st.toCharArray();
		int i = arr.length;
		int j = 0;
		int keycode;
		while (j < i) {
			keycode = arr[j];
			Window_Object.keyPress(keycode);
			Window_Object.keyRelease(keycode);
			j++;
		}
	}

	public static String UPM_Get_Current_Date() {
		DateTime currentDate = new DateTime();
		String D = String.valueOf(currentDate.getDayOfMonth());
		String M = String.valueOf(currentDate.getMonthOfYear());
		String Y = String.valueOf(currentDate.getYear());
		String Date123 = D + "." + M + "." + Y;
		return Date123;
	}

	/*
	 * public static void date_picker(String date_locator,String locator )
	 * throws Exception { WebElement datepick_locator =
	 * UPM_Get_Element(locator); WebElement
	 * Click_date=UPM_Get_Element(date_locator); datepick_locator.click(); }
	 * 
	 * public static void date_select(String date_locator,String locator) throws
	 * Exception { String dd= date.substring(0,1); String
	 * mm=date.substring(3,4); String yy=date.substring(6,7);
	 * 
	 * WebElement datepicker = UPM_Get_Element(locator); datepicker.click();
	 * 
	 * String date2= WebElement date1 = UPM_Get_Element(dd); WebElement month1 =
	 * UPM_Get_Element(mm); WebElement year1 = UPM_Get_Element(yy);
	 * 
	 * date1 }
	 */

	public static void UPM_Login(String Credentials, String Desc)throws Exception 
	{
		String[] Credentials_Details = Credentials.split(";");
		String[] Locator_User = Credentials_Details[1].split("=");
		String[] Locator_Pwd = Credentials_Details[2].split("=");
		// @@@@@@@@@@@@@@@@@@@@@@Variable Declaration@@@@@@@@@@@@@@@@@@@@@@@@@

		UPM_Function_Library.UPM_Launch_Browser("yes", "Launch Browser");
		UPM_Function_Library.UPM_NavigateTo(Credentials_Details[0],
				"Navigate to URL");
		UPM_Function_Library.Activate_Latest_Browser("yes", "");
/*
		UPM_Function_Library.UPM_Set_Text("id=" + Locator_User[0],
				Locator_User[1], "Enter the user id");
		UPM_Function_Library.UPM_Set_Text("id=" + Locator_Pwd[0],
				Locator_Pwd[1], "Enter the password");
		UPM_Function_Library.UPM_Click_Object("id=" + Credentials_Details[3],
				"yes", "Click Login button");
		Thread.sleep(6000);
		
*/
		UPM_Function_Library.ObjDriver.findElement(By.id(Locator_User[0])).sendKeys(Locator_User[1]);
		UPM_Function_Library.ObjDriver.findElement(By.id(Locator_Pwd[0])).sendKeys(Locator_Pwd[1]);
		UPM_Function_Library.ObjDriver.findElement(By.id(Credentials_Details[3])).click();
		
		Thread.sleep(1000);
		}

	// http://172.25.142.46:8080/AS/upm/;j_username=upm_R1;j_password=password;loginbox_submit

	public static void UPM_Date_Select(String Date, String Desc)
			throws Exception {
		if (!Date.matches("NA")) {
			String[] Date_Select = Date.split(";"); // //*[starts-with(@id,'upmstart1:MnyLndg_wB_date_')
													// and
													// contains(@class,'rich-calendar-input
													// inputFieldNotSelected')]
			String[] Date_Locator = Date_Select[0].split("="); // 12.12.2016
			// String[] Calendar_Locator = Date_Select[1].split("=");12.12.2016
			UPM_Function_Library.Type_WebElement_Key(
					"xpath=" + Date_Locator[0], Date_Locator[1],
					"Enter the date");
			// UPM_Function_Library.UPM_Set_Text("xpath="+Date_Locator[0],
			// Date_Locator[1],"Enter the date");
			UPM_Function_Library.UPM_Click_Object("xpath=" + Date_Select[1],
					"yes", "Click Calendar select button"); // //*[starts-with(@id,'upmstart1:MnyLndg_wB_date_')
															// and
															// contains(@class,'rich-calendar-button')]
			Thread.sleep(1000);

			UPM_Function_Library.UPM_Click_Object("xpath=" + Date_Select[2],
					"yes", Desc);
		}

	}

	public static void View_WebElement_Down_Key(String WebElement_Pro,
			String occurrence, String Desc) throws Exception {
		if (!occurrence.matches("NA")) {
			WebElement Element = null;
			Element = UPM_Get_Element(WebElement_Pro);
			JavascriptExecutor js = (JavascriptExecutor) ObjDriver;
			js.executeScript("arguments[0].scrollIntoView();", Element);
		}

	}

	public static int UPM_Get_WebTable_Row_Count_desc(String Table_Element,
			String Event, String Webtablename) throws Exception {
		int row_cnt = 0;
		if (!Event.matches("NA")) {
			WebElement Element = UPM_Get_Element(Table_Element);
			row_cnt = Element.findElements(By.tagName("tr")).size();
			if (row_cnt > 0) {
				UPM_Report_Library.Add_Step(
						UPM_Report_Library.Test_Step_Number,
						"the row count for the webtable " + Webtablename
								+ " is : " + row_cnt, LogStatus.PASS, true);
			}

		}
		return row_cnt;
	}

	public static String repalceStrings(String s1) {
		// return s1.replaceAll(" ", "").replaceAll(" ", "");
		return s1.replaceAll(" ", " ");
	}

	public static void Verify_TextPresent_WebElement(String WebElement_Pro,
			String Verfy_TextValue, String Desc) throws Exception {
		if (!Verfy_TextValue.matches("NA")) {

			try {
				View_WebElement_Down_Key(WebElement_Pro, "yes",
						"Scroll to the elemenet");

				Thread.sleep(2000);
				//Wait_For_Div();
				Activate_Browsers();

				String Actual_Pro = WebElement_Pro.substring(6);
				String ele = ObjDriver.findElement(By.xpath(Actual_Pro))
						.getText();
				String value = ObjDriver.findElement(By.xpath(Actual_Pro))
						.getAttribute("value");

				/*
				 * char[] first = Verfy_TextValue.toLowerCase().toCharArray();
				 * char[] second = ele.toLowerCase().toCharArray();
				 * 
				 * int minValue = Math.min(first.length, second.length);
				 * 
				 * for(int i = 0; i < minValue; i++) { if (first[i] !=
				 * second[i]) { System.out.println(i);
				 * System.out.println("First string ->"+ first[i] );
				 * System.out.println("Second String ->"+ second [i] );
				 * 
				 * } }
				 */

				String Verfy_TextValue1 = repalceStrings(Verfy_TextValue);
				String ele1 = repalceStrings(ele);

				if (Verfy_TextValue1.equalsIgnoreCase(ele1)
						|| Verfy_TextValue1.equalsIgnoreCase(value)) {
					if (!Desc.isEmpty()) {
						UPM_Report_Library
								.Add_Step(UPM_Report_Library.Test_Step_Number,
										Desc + " : Expected Value : "
												+ Verfy_TextValue,
										LogStatus.PASS, true);
					}
				} else {
					if (!Desc.isEmpty()) {
						UPM_Report_Library
								.Add_Step(UPM_Report_Library.Test_Step_Number,
										Desc + " : Expected Value : "
												+ Verfy_TextValue,
										LogStatus.FAIL, true);
						Exception_Content();
					}
				}
			} catch (InvalidSelectorException excp1) {
				if (!Desc.isEmpty()) {
					UPM_Report_Library.Add_Step(
							UPM_Report_Library.Test_Step_Number, Desc + "\n"
									+ excp1, LogStatus.FAIL, true);
					Exception_Content();
				}
			} catch (Exception exception1) {
				if (!Desc.isEmpty()) {
					UPM_Report_Library.Add_Step(
							UPM_Report_Library.Test_Step_Number, Desc + "\n"
									+ exception1, LogStatus.FAIL, true);
					Exception_Content();
				}

			}
		}
	}

	public static void UPM_Submit_Object_With_Wait(String Object_Name_Pro,
			String Wait_Time, String Desc) throws Exception {
		if (!Wait_Time.matches("NA")) {
			//Wait_For_Div();
			WebElement UPM_Object = null;
			try {
				Thread.sleep(1500);
				UPM_Object = UPM_Get_Element(Object_Name_Pro);
				if (UPM_Object == null) {
					if (!Desc.isEmpty()) {
						UPM_Report_Library.Add_Step(
								UPM_Report_Library.Test_Step_Number, Desc,
								LogStatus.FAIL, true);
						Exception_Content();
					}
				} else {
					if (Object_Name_Pro.startsWith("xpath")) {
						String actualProperty = Object_Name_Pro.substring(6);
						ObjDriver.findElement(By.xpath(actualProperty))
								.submit();
						Thread.sleep(Integer.parseInt(Wait_Time));
						if (!Desc.isEmpty()) {
							UPM_Report_Library.Add_Step(
									UPM_Report_Library.Test_Step_Number, Desc,
									LogStatus.PASS, false);
						}
					}
				}
			} catch (StaleElementReferenceException elementHasDisappeared) {
				UPM_Object = UPM_Get_Element(Object_Name_Pro);
				if (UPM_Object == null) {
					if (!Desc.isEmpty()) {
						UPM_Report_Library.Add_Step(
								UPM_Report_Library.Test_Step_Number, Desc,
								LogStatus.FAIL, true);
						Exception_Content();
					}
				} else {
					UPM_Object.submit();
					Thread.sleep(Integer.parseInt(Wait_Time));
					Wait_For_Div();
					if (!Desc.isEmpty()) {
						UPM_Report_Library.Add_Step(
								UPM_Report_Library.Test_Step_Number, Desc,
								LogStatus.PASS, false);
					}
				}
			}
		}
	}

	public static void UPM_Double_Click_Object_With_Wait(
			String Object_Name_Pro, String Wait_Time, String Desc)
			throws Exception {
		if (!Wait_Time.matches("NA")) {
			WebElement UPM_Object = null;
			try {
				Thread.sleep(1500);
				UPM_Object = UPM_Get_Element(Object_Name_Pro);
				if (UPM_Object == null) {
					if (!Desc.isEmpty()) {
						UPM_Report_Library.Add_Step(
								UPM_Report_Library.Test_Step_Number, Desc,
								LogStatus.FAIL, true);
						Exception_Content();
					}
				} else {
					Element_Action = new Actions(ObjDriver);
					Element_Action.doubleClick(UPM_Object);
					UPM_Object.click();
					Thread.sleep(Integer.parseInt(Wait_Time));
					if (!Desc.isEmpty()) {
						UPM_Report_Library.Add_Step(
								UPM_Report_Library.Test_Step_Number, Desc,
								LogStatus.PASS, false);
					}
				}
			}

			catch (InvalidSelectorException excp1) {
				if (!Desc.isEmpty()) {
					UPM_Report_Library.Add_Step(
							UPM_Report_Library.Test_Step_Number, Desc + "\n"
									+ excp1, LogStatus.FAIL, true);
					Exception_Content();
				}
			} catch (StaleElementReferenceException elementHasDisappeared) {
				UPM_Object = UPM_Get_Element(Object_Name_Pro);
				if (UPM_Object == null) {
					if (!Desc.isEmpty()) {
						UPM_Report_Library.Add_Step(
								UPM_Report_Library.Test_Step_Number, Desc,
								LogStatus.FAIL, true);
						Exception_Content();
					}
				} else {
					UPM_Object.submit();
					Thread.sleep(Integer.parseInt(Wait_Time));
				//	Wait_For_Div();
					if (!Desc.isEmpty()) {
						UPM_Report_Library.Add_Step(
								UPM_Report_Library.Test_Step_Number, Desc,
								LogStatus.PASS, false);
					}
				}
			}
		}
	}

	// Direct click without using of return webelement.
	public static void UPM_Direct_Click_Object_With_Wait(String Object_Name_Pro, String Wait_Time, String Desc)
			throws Exception {
		if (!Wait_Time.matches("NA")) 
		{
			Wait_For_Div();
			//Thread.sleep(2000);
			Activate_Browsers();
			WebDriverWait Wait_For_Object = new WebDriverWait(ObjDriver, 20);
			try {

				WebElement UPM_Browser_Element1;
				// UPM_Object = UPM_Get_Element(Object_Name_Pro);

				if (Object_Name_Pro.startsWith("xpath=")) 
				{
					String Actual_Property = Object_Name_Pro.substring(6);
					UPM_Browser_Element1 = ObjDriver.findElement(By.xpath(Actual_Property));
					for (int i = 0; i <= 10; i++) 
					{
						if (UPM_Browser_Element1.isDisplayed())
						{
							View_WebElement_Down_Key(Object_Name_Pro, "yes",Desc);
							Element_Action.moveToElement(UPM_Browser_Element1).perform();
							// highLightElement(ObjDriver,UPM_Browser_Element1);
							break;
						}
						Thread.sleep(100);
					}
					UPM_Browser_Element1.click();
					
					
					/*UPM_Browser_Element1 = ObjDriver.findElement(By
							.xpath(Actual_Property));
					if (UPM_Browser_Element1 != null)
					{
						if (!Desc.isEmpty())
						{
							Element_Action.moveToElement(ObjDriver.findElement(By.xpath(Actual_Property))).click().build().perform();
						}
					}*/

					// Wait_For_Object.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Actual_Property)));
					// ObjDriver.findElement(By.xpath(Actual_Property)).click();

					if (UPM_Browser_Element1 == null) {
						if (!Desc.isEmpty()) {
							UPM_Report_Library.Add_Step(UPM_Report_Library.Test_Step_Number, Desc,LogStatus.FAIL, true);
							Exception_Content();
						}
					} else {
						UPM_Report_Library.Add_Step(
								UPM_Report_Library.Test_Step_Number, Desc,
								LogStatus.PASS, false);
					}
				}
			}

			catch (InvalidSelectorException excp1)
			{
				if (!Desc.isEmpty()) 
				{
					UPM_Report_Library.Add_Step(UPM_Report_Library.Test_Step_Number, Desc + "\n"+ excp1, LogStatus.FAIL, true);
					Exception_Content();
				}
			} 
			catch (StaleElementReferenceException elementHasDisappeared) 
			{
				WebElement UPM_Object = UPM_Get_Element(Object_Name_Pro);

				if (UPM_Object == null) 
				{
					if (!Desc.isEmpty()) 
					{
						UPM_Report_Library.Add_Step(UPM_Report_Library.Test_Step_Number, Desc,LogStatus.FAIL, true);
						Exception_Content();
					}
				} else {
					String Actual_Property = Object_Name_Pro.substring(6);
					Wait_For_Object.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Actual_Property)));
					ObjDriver.findElement(By.xpath(Actual_Property)).click();
					Thread.sleep(Integer.parseInt(Wait_Time));
					Wait_For_Div();
					if (!Desc.isEmpty()) 
					{
						UPM_Report_Library.Add_Step(UPM_Report_Library.Test_Step_Number, Desc,LogStatus.PASS, false);
					}
				}
			} 
			catch (Exception e) 
			{
				if (!Desc.isEmpty()) 
				{
					UPM_Report_Library.Add_Step(UPM_Report_Library.Test_Step_Number, Desc + "\n"+ e, LogStatus.FAIL, true);
					Exception_Content();
				}
			}
		}
	}

	public static void Verify_TextPresent_Select(String WebElement_Pro,
			String Verfy_TextValue, String Desc) throws Exception {
		if (!Verfy_TextValue.matches("NA")) {
			View_WebElement_Down_Key(WebElement_Pro, "yes",
					"Scroll to the elemenet");

			Thread.sleep(2000);
			//Wait_For_Div();
			Activate_Browsers();
			Boolean a = false;
			String Actual_Pro = null;

			Actual_Pro = WebElement_Pro.substring(6);
			String Actual_option = Actual_Pro + "/option";
			List<WebElement> Actual_options = ObjDriver.findElements(By
					.xpath(Actual_option));

			for (WebElement ele : Actual_options) {
				if (ele.getText().equalsIgnoreCase(Verfy_TextValue)) {
					if (!Desc.isEmpty()) {
						UPM_Report_Library
								.Add_Step(UPM_Report_Library.Test_Step_Number,
										Desc + " : Expected Value : "
												+ Verfy_TextValue,
										LogStatus.PASS, true);
					}
					a = true;
				}
			}
			if (a == false && Actual_Pro == null) {
				if (!Desc.isEmpty()) {
					UPM_Report_Library.Add_Step(
							UPM_Report_Library.Test_Step_Number, Desc
									+ " : Expected Value : " + Verfy_TextValue,
							LogStatus.FAIL, true);
					Exception_Content();
				}
			}
		}
	}

	public static void UPM_Verify_TreeStructureData_WebTable_Data(
			String ExpectedText, String Desc) throws Exception {
		if (!ExpectedText.matches("NA")) {
		//	Wait_For_Div();
			int iterate = 1;
			Boolean ExistFlg = false;
			List<WebElement> Element = null;
			String text = repalceStrings(ExpectedText);

			Element = ObjDriver.findElements(By
					.xpath("//span[contains(@id,'col1')]"));

			for (WebElement ele : Element) {

				if (text.equalsIgnoreCase(ele.getText())) {
					highLightElement(ObjDriver, ele);
					ExistFlg = true;
					break;
				}
				++iterate;
			}

			if (ExistFlg) {
				if (!Desc.isEmpty()) {
					UPM_Report_Library.Add_Step(
							UPM_Report_Library.Test_Step_Number, Desc + " : "
									+ ExpectedText + " Found in node: "
									+ iterate, LogStatus.PASS, true);
				}
			} else {
				if (!Desc.isEmpty()) {
					UPM_Report_Library.Add_Step(
							UPM_Report_Library.Test_Step_Number, Desc + " : "
									+ ExpectedText, LogStatus.FAIL, true);
					Exception_Content();
				}
			}
		}
	}

	public static void UPM_Direct_Click_Screenshot_With_Wait(
			String Object_Name_Pro, String Wait_Time, String Desc)
			throws Exception {
		if (!Wait_Time.matches("NA")) {
			Wait_For_Div();
			Thread.sleep(1000);

			try {

				WebElement UPM_Browser_Element1;
				// UPM_Object = UPM_Get_Element(Object_Name_Pro);

				if (Object_Name_Pro.startsWith("xpath=")) 
				{
				
					String Actual_Property = Object_Name_Pro.substring(6);
					UPM_Browser_Element1 = ObjDriver.findElement(By.xpath(Actual_Property));
					for (int i = 0; i <= 10; i++) 
					{
						if (UPM_Browser_Element1.isDisplayed()) 
						{
							View_WebElement_Down_Key(Object_Name_Pro, "yes",Desc);
							Element_Action.moveToElement(UPM_Browser_Element1).perform();
							highLightElement(ObjDriver, UPM_Browser_Element1);
							break;
						}
						Thread.sleep(100);
					}
					UPM_Browser_Element1 = ObjDriver.findElement(By
							.xpath(Actual_Property));
					if (UPM_Browser_Element1 != null) {
						if (!Desc.isEmpty()) 
						{
							Element_Action.moveToElement(ObjDriver.findElement(By.xpath(Actual_Property))).click().build().perform();
							Thread.sleep(1000);
							Wait_For_Div();
						}
					}

					// Wait_For_Object.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Actual_Property)));
					// ObjDriver.findElement(By.xpath(Actual_Property)).click();

					if (UPM_Browser_Element1 == null) {
						if (!Desc.isEmpty()) {
							UPM_Report_Library.Add_Step(
									UPM_Report_Library.Test_Step_Number, Desc,
									LogStatus.FAIL, true);
							Exception_Content();
						}
					} else {
						UPM_Report_Library.Add_Step(
								UPM_Report_Library.Test_Step_Number, Desc,
								LogStatus.PASS, true);
					}
				}
				else
				{
					WebElement UPM_Object = UPM_Get_Element(Object_Name_Pro);
					
							Element_Action.moveToElement(UPM_Object).perform();
							highLightElement(ObjDriver, UPM_Object);
							Element_Action.moveToElement(UPM_Object).click().build().perform();
							Wait_For_Div();
					
					if (UPM_Object == null) 
					{
								if (!Desc.isEmpty()) 
								{
									
									UPM_Report_Library.Add_Step(UPM_Report_Library.Test_Step_Number, Desc,LogStatus.FAIL, true);
									Exception_Content();
									
								}
							}
					else 
							{
								UPM_Report_Library.Add_Step(UPM_Report_Library.Test_Step_Number, Desc,LogStatus.PASS, true);
							}		
							
				}
			}

			
			catch (StaleElementReferenceException elementHasDisappeared) {
				WebElement UPM_Object = UPM_Get_Element(Object_Name_Pro);

				if (UPM_Object == null) {
					if (!Desc.isEmpty()) {
						UPM_Report_Library.Add_Step(
								UPM_Report_Library.Test_Step_Number, Desc,
								LogStatus.FAIL, true);
						Exception_Content();
					}
				} else {
					String Actual_Property = Object_Name_Pro.substring(6);
					Wait_For_Object.until(ExpectedConditions
							.visibilityOfElementLocated(By
									.xpath(Actual_Property)));
					ObjDriver.findElement(By.xpath(Actual_Property)).click();
					Wait_For_Div();
					Thread.sleep(Integer.parseInt(Wait_Time));
					Wait_For_Div();
					if (!Desc.isEmpty()) {
						UPM_Report_Library.Add_Step(
								UPM_Report_Library.Test_Step_Number, Desc,
								LogStatus.PASS, true);
					}
				}
			} catch (Exception e) {
				if (!Desc.isEmpty()) {
					UPM_Report_Library.Add_Step(
							UPM_Report_Library.Test_Step_Number, Desc + "\n"
									+ e, LogStatus.FAIL, true);
					Exception_Content();
				}
			}
		}

	}

	public static void Verify_Selected(String WebElement_Pro,
			String Verfy_TextValue, String Desc) throws Exception {
		if (!Verfy_TextValue.matches("NA")) {
			View_WebElement_Down_Key(WebElement_Pro, "yes",
					"Scroll to the elemenet");

			//Wait_For_Div();
			Activate_Browsers();
			Boolean a = false;
			String Actual_Pro = null;

			String Verify_text = repalceStrings(Verfy_TextValue);

			Actual_Pro = WebElement_Pro.substring(6);

			/*
			 * String Actual_option = Actual_Pro+"/option"; List<WebElement>
			 * Actual_options = ObjDriver.findElements(By.xpath(Actual_option));
			 * 
			 * for(WebElement ele:Actual_options) {
			 * System.out.println(ele.getAttribute("selected"));
			 * if(ele.getAttribute("selected")!=null &&
			 * ele.getAttribute("selected").equalsIgnoreCase("selected")) {
			 * if(ele.getText().equalsIgnoreCase(Verify_text)) {
			 * UPM_Report_Library.Add_Step(UPM_Report_Library.Test_Step_Number,
			 * Desc+" The selected value is: "+ele.getText(), LogStatus.PASS,
			 * true); } a=true; } }
			 */

			String value = repalceStrings(Verfy_TextValue);

			Select selected = new Select(ObjDriver.findElement(By
					.xpath(Actual_Pro)));
			WebElement option = selected.getFirstSelectedOption();

			if (option.getText().equalsIgnoreCase(value)) {
				UPM_Report_Library.Add_Step(
						UPM_Report_Library.Test_Step_Number,
						Desc + "; The selected value is: " + option.getText(),
						LogStatus.PASS, true);
			} else {
				UPM_Report_Library.Add_Step(
						UPM_Report_Library.Test_Step_Number, Desc
								+ " : Expected Value : " + Verfy_TextValue,
						LogStatus.FAIL, true);
				Exception_Content();
			}
		}
	}

	public static void Verify_Webtable_Enabled_data(String WebElement_Pro,
			String Event, String Desc) throws Exception {
		if (!Event.matches("NA")) {
			try

			{

				int Row;
				int Column;
				int iterate;
				int iterate1;
				Boolean ExistFlg = false;
				WebElement Element = null;
				String data = "";
				WebElement inputcoll = null;
				View_WebElement_Down_Key(WebElement_Pro, "yes", "");
				Element = UPM_Get_Element(WebElement_Pro);

				List<WebElement> rowCollection = Element.findElements(By
						.tagName("tr"));

				int i_RowNum = 1;
				for (WebElement rowElement : rowCollection) {
					List<WebElement> colCollection = rowElement.findElements(By
							.xpath("td"));

					int i_ColNum = 1;

					for (WebElement colElement : colCollection) {

						if (!colElement.getText().equalsIgnoreCase("")) {
							inputcoll = colElement.findElement(By
									.tagName("input"));
							if (inputcoll.getAttribute("type").equalsIgnoreCase("checkbox")) {
								if ((inputcoll.getAttribute("disabled") == null)) {

									highLightElement(ObjDriver, colElement);
									data = data + ", " + colElement.getText();
								}
							} else {
								if (!(inputcoll.getAttribute("class")
										.equalsIgnoreCase("InputDisabled"))) {

									highLightElement(ObjDriver, colElement);
									data = data + ", " + colElement.getText();
								}

							}
						}
					}
					i_RowNum = i_RowNum + 1;
				}

				UPM_Report_Library.Add_Step(
						UPM_Report_Library.Test_Step_Number, Desc
								+ "; The available enabled data are : " + data,
						LogStatus.PASS, true);

			} catch (Exception exc) {
				if (!Desc.isEmpty()) {
					UPM_Report_Library.Add_Step(
							UPM_Report_Library.Test_Step_Number, Desc + " : "
									+ exc, LogStatus.FAIL, true);
					Exception_Content();
					System.out.println(exc);
				}

			}
		}
	}

	public static void UPM_Direct_Click_Screenshot(String Object_Name_Pro,
			String Wait_Time, String Desc) throws Exception {
		if (!Wait_Time.matches("NA")) {
			//Wait_For_Div();

			try {

				WebElement UPM_Browser_Element1;
				// UPM_Object = UPM_Get_Element(Object_Name_Pro);

				if (Object_Name_Pro.startsWith("xpath=")) {
					String Actual_Property = Object_Name_Pro.substring(6);
					UPM_Browser_Element1 = ObjDriver.findElement(By
							.xpath(Actual_Property));
					for (int i = 0; i <= 10; i++) {
						if (UPM_Browser_Element1.isDisplayed()) {
							View_WebElement_Down_Key(Object_Name_Pro, "yes",
									Desc);
							Element_Action.moveToElement(UPM_Browser_Element1)
									.perform();
							highLightElement(ObjDriver, UPM_Browser_Element1);
							break;
						}
						Thread.sleep(100);
					}
				//	UPM_Browser_Element1 = ObjDriver.findElement(By
				//			.xpath(Actual_Property));
					if (UPM_Browser_Element1 != null) {
						if (!Desc.isEmpty()) {
							Element_Action
									.moveToElement(
											ObjDriver.findElement(By
													.xpath(Actual_Property)))
									.click().build().perform();
							//Wait_For_Div();
							// Thread.sleep(1000);
						}
					}

					// Wait_For_Object.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Actual_Property)));
					// ObjDriver.findElement(By.xpath(Actual_Property)).click();

					if (UPM_Browser_Element1 == null) {
						if (!Desc.isEmpty()) {
							UPM_Report_Library.Add_Step(
									UPM_Report_Library.Test_Step_Number, Desc,
									LogStatus.FAIL, true);
							Exception_Content();
						}
					} else {
						Wait_For_Div();
						UPM_Report_Library.Add_Step(
								UPM_Report_Library.Test_Step_Number, Desc,
								LogStatus.PASS, true);
					}
				}
			}

			catch (InvalidSelectorException excp1) {
				if (!Desc.isEmpty()) {
					UPM_Report_Library.Add_Step(
							UPM_Report_Library.Test_Step_Number, Desc + "\n"
									+ excp1, LogStatus.FAIL, true);
					Exception_Content();
				}
			} catch (StaleElementReferenceException elementHasDisappeared) {
				WebElement UPM_Object = UPM_Get_Element(Object_Name_Pro);

				if (UPM_Object == null) {
					if (!Desc.isEmpty()) {
						UPM_Report_Library.Add_Step(
								UPM_Report_Library.Test_Step_Number, Desc,
								LogStatus.FAIL, true);
						Exception_Content();
					}
				} else {
					String Actual_Property = Object_Name_Pro.substring(6);
					Wait_For_Object.until(ExpectedConditions
							.visibilityOfElementLocated(By
									.xpath(Actual_Property)));
					ObjDriver.findElement(By.xpath(Actual_Property)).click();
					//Wait_For_Div();
					Thread.sleep(Integer.parseInt(Wait_Time));
					//Wait_For_Div();
					if (!Desc.isEmpty()) {
						UPM_Report_Library.Add_Step(
								UPM_Report_Library.Test_Step_Number, Desc,
								LogStatus.PASS, true);
					}
				}
			} catch (Exception e) {
				if (!Desc.isEmpty()) {
					UPM_Report_Library.Add_Step(
							UPM_Report_Library.Test_Step_Number, Desc + "\n"
									+ e, LogStatus.FAIL, true);
					Exception_Content();
				}
			}
		}

	}

	public void Webtable_Delete_Byindex(String Table_Pro, String index,
			String Description) throws Exception {
		if (!index.matches("NA")) {
			try {
				WebElement ele = UPM_Get_Element(Table_Pro);

				List<WebElement> elements = ele.findElements(By.tagName("tr"));
				int rows = elements.size();

				for (int row1 = 0; row1 < rows; row1++) {
					List<WebElement> elements2 = elements.get(row1)
							.findElements(By.tagName("td"));
					int column = elements2.size();
					int i = Integer.parseInt(index);
					if (row1 == i) {
						elements2.get(column - 1)
								.findElement(By.tagName("div"))
								.findElement(By.tagName("a")).click();
						UPM_Report_Library.Add_Step(
								UPM_Report_Library.Test_Step_Number,
								Description, LogStatus.PASS, true);
						break;
					}
				}

			} catch (Exception exc) {
				System.out.println(exc);
				UPM_Report_Library.Add_Step(
						UPM_Report_Library.Test_Step_Number, Description + "\n"
								+ exc, LogStatus.FAIL, true);
				Exception_Content();

			}

		}

	}

	public void sessionexit(String Event, String Description) throws Exception {
		if (!Event.matches("NA")) {
			try {
				ObjDriver.findElement(
						By.xpath("//td[contains(text(),'Rollen')]")).click();
				UPM_Report_Library.Add_Step(
						UPM_Report_Library.Test_Step_Number,
						"Click on Rollen tab", LogStatus.PASS, true);
				Thread.sleep(1000);
				ObjDriver.findElement(
						By.xpath("//td[contains(text(),'Rating')]")).click();
				Thread.sleep(1000);
				UPM_Report_Library.Add_Step(
						UPM_Report_Library.Test_Step_Number,
						"Click on Rating tab", LogStatus.PASS, true);
				ObjDriver.findElement(
						By.xpath("//td[contains(text(),'Hierarchie')]"))
						.click();
				Thread.sleep(1000);
				UPM_Report_Library.Add_Step(
						UPM_Report_Library.Test_Step_Number,
						"Click on Hierarchie tab", LogStatus.PASS, true);
				ObjDriver.findElement(
						By.xpath("//td[contains(text(),'Engagement')]"))
						.click();
				Thread.sleep(1000);
				UPM_Report_Library.Add_Step(
						UPM_Report_Library.Test_Step_Number,
						"Click on Engagement tab", LogStatus.PASS, true);
				// ObjDriver.findElement(By.xpath("//td[contains(text(),'Historie')]")).click();

				// UPM_Report_Library.Add_Step(UPM_Report_Library.Test_Step_Number,
				// Description+"Click on Historie tab", LogStatus.PASS, true);

			} catch (Exception e) {
				UPM_Report_Library.Add_Step(
						UPM_Report_Library.Test_Step_Number, "Unable to click "
								+ e, LogStatus.FAIL, true);
			}
		}
	}

	public void closealtF4(String Event, String Desc) throws Exception {
		try {
			if (!Event.equalsIgnoreCase("NA")) {
				Wait_For_Div();
				/*
				 * Actions keyaction = new Actions(ObjDriver);
				 * keyaction.keyDown(
				 * Keys.ALT).keyDown(Keys.F4).build().perform();
				 * UPM_Report_Library
				 * .Add_Step(UPM_Report_Library.Test_Step_Number,
				 * Desc+"  Prforming ALT+F4", LogStatus.PASS, true);
				 * 
				 * Robot rbt1 = new Robot(); rbt1.keyPress(KeyEvent.VK_ALT);
				 * rbt1.keyPress(KeyEvent.VK_F4);
				 * 
				 * Actions keyaction = new Actions(ObjDriver);
				 * keyaction.keyDown(Keys.ALT); keyaction.keyDown(Keys.F4);
				 * keyaction.build().perform();
				 */
				ObjDriver.findElement(
						By.xpath("//td[contains(text(),'Rollen')]")).click();
				Robot rbt1 = new Robot();
				rbt1.keyPress(KeyEvent.VK_ALT);
				rbt1.keyPress(KeyEvent.VK_F4);
				rbt1.keyRelease(KeyEvent.VK_ALT);
				rbt1.keyRelease(KeyEvent.VK_F4);
				rbt1.keyPress(KeyEvent.VK_ALT);
				rbt1.keyPress(KeyEvent.VK_F4);
				rbt1.keyRelease(KeyEvent.VK_ALT);
				rbt1.keyRelease(KeyEvent.VK_F4);
				UPM_Report_Library.Add_Step(
						UPM_Report_Library.Test_Step_Number, Desc
								+ "  Prforming ALT+F4", LogStatus.PASS, true);
			}
		} catch (Exception e) {
			UPM_Report_Library.Add_Step(UPM_Report_Library.Test_Step_Number,
					Desc + "Unable to prforming ALT+F4" + e, LogStatus.FAIL,
					true);
			e.printStackTrace();
		}
	}
	
	public static void enter(String Event,String Desc) throws Exception 
	{
		if(Event.equalsIgnoreCase("NA"))	
		{
			
		}
		else
		{
			Thread.sleep(5000);
			Robot rb = new Robot();
			rb.keyPress(KeyEvent.VK_ENTER);
			Thread.sleep(7000);
		}
	}
	
	public static void PDF_Verification_Textbox(String Pro,String Data, String desc) throws Exception
	{
		if(!Data.equalsIgnoreCase("NA"))
		{
			try
			{	
				/*String path = UPM_Configuration_Library.Get_Latest_Xcl_From_Download_Location();
				File downloadpath = new File(path);
				
				UPM_PDF_Verification ob1 = new UPM_PDF_Verification();
				String pdftext = ob1.PDF_READ_2(downloadpath);
				
				
				String[] Pro2 = Pro.split("=");
				
				List <WebElement> ele = ObjDriver.findElements(By.xpath("//input"));
				
				for(WebElement ele2:ele)
				{
					if(ele2.getAttribute("value")!=null && ele2.getAttribute("displaylabel")!=null)
					{
						String presenttext = ele2.getAttribute("value");
						String label = ele2.getAttribute("displaylabel");
						if(pdftext.contains(presenttext))
						{
							UPM_Report_Library.Add_Step(UPM_Report_Library.Test_Step_Number, "The available field name =  "+label+": PDF value =  "+presenttext, LogStatus.PASS, false);
						}
						
					}		
				}
				*/
				Thread.sleep(2000);
				
				String Pro2 = Pro.substring(6);
				WebElement ele = ObjDriver.findElement(By.xpath(Pro2));
				View_WebElement_Down_Key(Pro,"yes", "move");
				
				//Details Partner Test UPM address checking 1
				//String[] Data1 = Data.split(";");
								
				String path = UPM_Configuration_Library.Get_Latest_Xcl_From_Download_Location();
				
				//File downloadpath = new File(path);
								
				String presenttext = ele.getAttribute("value");
				String DisplayLabel = ele.getAttribute("displaylabel");
				
				String pdf = UPM_PDF_Verification.PDF_READ_3(path);
				String[] lines = pdf.split("\\r?\\n", -1);
	        				
	           	int i;
				for(i=0; i< lines.length; i++)
				{
					//System.out.println(lines[i]+i);
					if(lines[i].contains(DisplayLabel))
					{
						break;
					}
				}
				
				if(lines[i].contains(presenttext))
				{
					UPM_Report_Library.Add_Step(UPM_Report_Library.Test_Step_Number, desc+ "The displayed field name =  "+DisplayLabel+": PDF value =  "+presenttext, LogStatus.PASS, true);
				}
				else
				{
					UPM_Report_Library.Add_Step(UPM_Report_Library.Test_Step_Number,desc + "  Actual: Unable to find the related text: "+Data+" in the particular field", LogStatus.FAIL,true);
				}
				
			}
			catch(Exception exc)
			{
				UPM_Report_Library.Add_Step(UPM_Report_Library.Test_Step_Number,desc + "Unable to perform; exception:" + exc, LogStatus.FAIL,true);
				System.out.println(exc);
			}
		}
	}
	
	public static void PDF_Verification_Select(String Pro,String Value, String Desc) throws Exception
	{
		if(!Value.equalsIgnoreCase("NA"))
		{
		try
			{
			View_WebElement_Down_Key(Pro,"yes", "move");
			
			WebElement ele =  UPM_Get_Element(Pro);
			Select sel = new Select(ele);
			
			WebElement selected = sel.getFirstSelectedOption();
			String displayedtext = selected.getText();
			
			String path = UPM_Configuration_Library.Get_Latest_Xcl_From_Download_Location();
			
			UPM_PDF_Verification ob1 = new UPM_PDF_Verification();
			
			String pdf = UPM_PDF_Verification.PDF_READ_3(path);
			String[] lines = pdf.split("\\r?\\n", -1);
        	
			String label = ele.getAttribute("displaylabel");
           	int i;
			for(i=0; i< lines.length; i++)
			{
				//System.out.println(lines[i]+i);
				if(lines[i].contains(label))
				{
					break;
				}
			}
			
			if(lines[i].contains(displayedtext))
			{		
				UPM_Report_Library.Add_Step(UPM_Report_Library.Test_Step_Number, Desc+ " : The selected field having label: "+label+"  PDF value= "+displayedtext, LogStatus.PASS, true);
			}
			else
			{
				UPM_Report_Library.Add_Step(UPM_Report_Library.Test_Step_Number,Desc + " : The Selected value is not available in the PDF file", LogStatus.FAIL,true);
			}
			}
		catch(Exception exc)
			{
			UPM_Report_Library.Add_Step(UPM_Report_Library.Test_Step_Number,Desc + " : Unable to perform; exception:" + exc, LogStatus.FAIL,true);
			System.out.println(exc);
			}
		}
		
	}
	
	public static void PDF_Verification_Checkbox(String Pro,String Value,String Desc) throws Exception
	{
		if(!(Value.equalsIgnoreCase("NA")))
		{
			try
			{
				
			//Property should be set up to the td path of the xpath
				
			WebElement ele = UPM_Get_Element(Pro);
			
			String path = UPM_Configuration_Library.Get_Latest_Xcl_From_Download_Location();
			
			UPM_PDF_Verification ob1 = new UPM_PDF_Verification();
			String pdftext = ob1.PDF_READ_3(path);
			
			if(pdftext.contains(ele.getText()))
				{
					//String label = ele.getAttribute("label");
					UPM_Report_Library.Add_Step(UPM_Report_Library.Test_Step_Number, Desc+ " : The selected field having label: "+ele.getText()+" ;   PDF value= "+ele.getText(), LogStatus.PASS, true);
				}
			else
				{
					UPM_Report_Library.Add_Step(UPM_Report_Library.Test_Step_Number,Desc + " : The Selected value is not available in the PDF file", LogStatus.FAIL,true);
				}
			}
			catch(Exception exc)
			{
				UPM_Report_Library.Add_Step(UPM_Report_Library.Test_Step_Number,Desc + " : Unable to perform; exception:" + exc, LogStatus.FAIL,true);
				System.out.println(exc);
			}
		}		
	}
	
	
	public static void PDF_Verification_WebtableElement(String Pro,String Value,String Desc) throws Exception
	{
		if(!(Value.equalsIgnoreCase("NA")))
		{
			try
			{
				
			//Property should be set up to the span of the table path of the xpath
				
			WebElement ele = UPM_Get_Element(Pro);
			
			String path = UPM_Configuration_Library.Get_Latest_Xcl_From_Download_Location();
		
			UPM_PDF_Verification ob1 = new UPM_PDF_Verification();
			String pdftext = ob1.PDF_READ_3(path);
			
			if(pdftext.contains(ele.getText()))
				{
					//String label = ele.getAttribute("label");
					UPM_Report_Library.Add_Step(UPM_Report_Library.Test_Step_Number, Desc+ " : The Particular Webtable element having label: "+ele.getText()+" ;   PDF value= "+ele.getText(), LogStatus.PASS, true);
				}
			else
				{
					UPM_Report_Library.Add_Step(UPM_Report_Library.Test_Step_Number,Desc + " : The Selected value is not available in the PDF file", LogStatus.FAIL,true);
				}
			}
			catch(Exception exc)
			{
				UPM_Report_Library.Add_Step(UPM_Report_Library.Test_Step_Number,Desc + " : Unable to perform; exception:" + exc, LogStatus.FAIL,true);
				System.out.println(exc);
			}
		}		
	}
	
	public static void PDF_Verification_Label(String Pro,String Value, String Desc) throws Exception
	{
		if(!Value.equalsIgnoreCase("NA"))
		{
		try
			{
			View_WebElement_Down_Key(Pro,"yes", "move");
			
			WebElement ele =  UPM_Get_Element(Pro);
			
			String text = ele.getText();
			
			String path = UPM_Configuration_Library.Get_Latest_Xcl_From_Download_Location();
			
			
			UPM_PDF_Verification ob1 = new UPM_PDF_Verification();
			String pdftext = ob1.PDF_READ_3(path);
			
			
			if(pdftext.contains(text))
			{
				
				UPM_Report_Library.Add_Step(UPM_Report_Library.Test_Step_Number, Desc+ " : The selected field having label: "+text+"  PDF value= "+text, LogStatus.PASS, true);
			}
			else
			{
				UPM_Report_Library.Add_Step(UPM_Report_Library.Test_Step_Number,Desc + " : The Selected value is not available in the PDF file", LogStatus.FAIL,true);
			}
			}
		catch(Exception exc)
			{
			UPM_Report_Library.Add_Step(UPM_Report_Library.Test_Step_Number,Desc + " : Unable to perform; exception:" + exc, LogStatus.FAIL,true);
			System.out.println(exc);
			}
		}
		
	}
	
	public static void ErrorMessagevarification(String event) throws Exception {

		try {
			WebElement error = ObjDriver.findElement(By
					.xpath("//div[@class='ErrorMessage']"));
			if (error.getText() != null) {
				UPM_Report_Library.Add_Step(
						UPM_Report_Library.Test_Step_Number,
						"Getting error pop-up: " + error.getText(),
						LogStatus.INFO, true);
			}
		} catch (NullPointerException exc) {

		}

		catch (NoSuchElementException e) {

		} catch (Exception e) {

		}

	}

}