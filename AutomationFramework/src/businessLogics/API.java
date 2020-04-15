package businessLogics;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.annotation.JsonAppend.Prop;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSerializer;

import com.ssts.pcloudy.Connector;
import com.ssts.pcloudy.dto.access.UserDetailResult;
import com.ssts.pcloudy.dto.appium.booking.BookingDtoDevice;
import com.ssts.pcloudy.dto.device.MobileDevice;
import com.ssts.pcloudy.dto.file.PDriveFileDTO;
import com.ssts.pcloudy.dto.generic.BookingDtoResult;
import com.ssts.pcloudy.dto.screenshot.CaptureDeviceScreenshotDto.CaptureDeviceScreenshotResultDto;
import com.ssts.pcloudy.exception.ConnectError;

import utils.PropertiesAndConstants;
import utils.UtilityFunctions;

public class API {
	
	
	public static String activateAPI(String number){	
		try {
		System.out.println(number);
		//String number = "7507173961";		
		//Genetare  token
		String tokenArray =  GenerateToken();    
		JSONObject tokenArrayObject = new JSONObject(tokenArray); 
		String token = tokenArrayObject.getString("access_token").toString();
		System.out.println("Token is:");
		System.out.println(token);

		//Generate Otp	
		String otpArray = EnterNumber(token,number);		
		JSONObject otpArrayObject = new JSONObject(otpArray); 
		String otp = otpArrayObject.getJSONObject("data").getJSONObject("otp_details").getString("otp").toString();
		System.out.println("OTP is:");
		System.out.println(otp);    
		return otp;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		
	
		/*
		//Get Otp Response
		String otpResponse = EnterOTP(token, number, otp);	
		System.out.println(otpResponse);
		JSONObject otpResponseObject = new JSONObject(otpResponse); 
		String brandName = otpResponseObject.getJSONObject("data").getJSONObject("number_details").getString("brandName").toString();    	    	  	    			    	    	    
		String circle = otpResponseObject.getJSONObject("data").getJSONObject("number_details").getString("circle").toString();    	    	  	    				    	    
		String subscriberType = otpResponseObject.getJSONObject("data").getJSONObject("number_details").getString("subscriberType").toString();    	    	  	    			
		String access_token = otpResponseObject.getJSONObject("data").getJSONObject("token_details").getString("access_token").toString();    	    	  	    	
				
				
		//Get Dashboard respnse
		String DashboardResponse =  DashboardCard(number, circle, brandName, subscriberType, token);
		//String DashboardResponse = "{\"data\":{\"user_details\":{\"persona\":\"\",\"fullName\":\"Swaraj \",\"emailId\":\"\",\"accountId\":\"2125301668\",\"firstName\":\"Swaraj\",\"email_isVerified\":false,\"plan_title\":\"Your RED Box\",\"altPhoneNo\":\"413104\",\"msisdn\":\"7768981584\",\"activationDate\":\"20/08/2013\",\"emailVerificationDate\":\"\",\"alternativeContactNumber\":\"413104\",\"plan_description\":\"Free benefits of worth ₹4498 on your RED Entertainment +\"},\"bucket_details\":{\"core_balance_expiry\":\"Service Valid for: 5 days (till 28th Sep 2019)\",\"total_data\":\"\",\"additional_packs\":\"\",\"active_packs\":\"\",\"expiredDaysText\":\"\",\"data_left\":\"\",\"highest_pack_validity\":\"\",\"pack_expiry_date\":\"28th Sep 19\",\"core_balance\":0.0,\"data_left_text\":\"\",\"calls\":\"\",\"data_left_percent\":\"\",\"leftExpiryDays\":5.0}},\"isSuccessful\":true,\"message\":\"\",\"status\":\"SUCCESS\"}";
		System.out.println(DashboardResponse);	
		JSONObject DashboardResponseObject = new JSONObject(DashboardResponse);
		Object test =  DashboardResponseObject.getJSONObject("data").getJSONObject("bucket_details").get("highest_pack_validity");
		System.out.println("before test...");
		if(test.equals(""))
		{
			System.out.println("testing...");
			PropertiesAndConstants.highest_pack_validity = 0;
		}
		else {
		PropertiesAndConstants.highest_pack_validity = DashboardResponseObject.getJSONObject("data").getJSONObject("bucket_details").getInt("highest_pack_validity");
		}
		Object coreBalance =  DashboardResponseObject.getJSONObject("data").getJSONObject("bucket_details").get("core_balance");
		System.out.println("before test...");
		if(coreBalance.equals(0.0))
		{
			System.out.println("testing2...");
			PropertiesAndConstants.core_balance = "0.0";
		}		
		else {
		PropertiesAndConstants.core_balance = DashboardResponseObject.getJSONObject("data").getJSONObject("bucket_details").getString("core_balance").toString();
		}
		PropertiesAndConstants.calls = DashboardResponseObject.getJSONObject("data").getJSONObject("bucket_details").getString("calls").toString();						
		PropertiesAndConstants.pack_expiry = DashboardResponseObject.getJSONObject("data").getJSONObject("bucket_details").getString("core_balance_expiry").toString();				
		PropertiesAndConstants.total_data = DashboardResponseObject.getJSONObject("data").getJSONObject("bucket_details").getString("total_data").toString();						
		PropertiesAndConstants.data_left_percent = DashboardResponseObject.getJSONObject("data").getJSONObject("bucket_details").getString("data_left_percent").toString();						
		PropertiesAndConstants.additional_packs = DashboardResponseObject.getJSONObject("data").getJSONObject("bucket_details").getString("additional_packs").toString();							
		PropertiesAndConstants.balanceLeft = DashboardResponseObject.getJSONObject("data").getJSONObject("bucket_details").getJSONArray("active_packs").getJSONObject(0).getString("balanceLeft").toString();						
		PropertiesAndConstants.serviceExpiryDays = DashboardResponseObject.getJSONObject("data").getJSONObject("bucket_details").getInt("leftExpiryDays");								   		
		PropertiesAndConstants.data_left = DashboardResponseObject.getJSONObject("data").getJSONObject("bucket_details").getString("data_left");
		System.out.println("Dashboard Data retrival completed..");
		*/
	}//End of main

	// HTTP POST request
	private static String GenerateToken() throws Exception {
		String url = "https://mapsit.ideacellular.com:8104/SelfCareApp/api/az/v1/token";
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
		//add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("Authorization", "Basic U2VsZkNhcmVBZG1pbjpTZWxmQ2FyZUFkbWlu");
		con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		String urlParameters = "Scope=DEFAULT_SCOPE&grant_type=client_credentials";
		String gettoken = postRequest(con, urlParameters);
		return gettoken;
	}//Eod of GenerateToken()

	// HTTP POST request
	private static String EnterNumber(String token,String number) throws Exception {
		String url = "https://mapsit.ideacellular.com:8104/SelfCareApp/api/adapters/LoginAdapter/verifyNumberAndSendOtp";
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
		String str = "Bearer "+token;		
		//add reuqest header
		con.setRequestMethod("POST"); 
		con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		con.setRequestProperty("Authorization",str);	 		
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");		
		String CreateArray[] = {number};
  		String parametrArrayWithQuotes = createParameterArray(CreateArray);		
  		String encodedValue = encodeValue(parametrArrayWithQuotes);
		String urlParameters = "params="+encodedValue; 		
		String getOtp = postRequest(con, urlParameters);	 		
		return getOtp;	 
	}//EOD EnterNumber

	// HTTP POST request
	private static String EnterOTP(String token, String number, String otp ) throws Exception {
		String url = "https://mapsit.ideacellular.com:8104/SelfCareApp/api/adapters/LoginAdapter/verifyOtpAndGetDetails";
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
		String str = "Bearer "+token;  		
		con.setRequestMethod("POST"); 
		con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		con.setRequestProperty("Authorization",str);	  		
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");		
		String CreateArray[] = {number,otp};
  		String parametrArrayWithQuotes = createParameterArray(CreateArray);		
  		String encodedValue = encodeValue(parametrArrayWithQuotes);
		String urlParameters = "params="+encodedValue;
		String getOtpResponse = postRequest(con, urlParameters);
		return getOtpResponse;	  		
	}//EOD EnterOTP

	 // HTTP POST request
  	private static String DashboardCard(String number, String circle, String brandName, String subscriberType,String token ) throws Exception {		
  		String url = "https://mapsit.ideacellular.com:8104/SelfCareApp/api/adapters/DashboardAdapter/getDashboardCardData";
  		URL obj = new URL(url);
  		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
  		String str = "Bearer "+token;  		
  		con.setRequestMethod("POST"); 
  		con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
  		con.setRequestProperty("Authorization",str);
  		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
  		String CreateArray[] = {number, circle, brandName, subscriberType};
  		String parametrArrayWithQuotes = createParameterArray(CreateArray);  		  		  	      		  		
  		String encodedValue = encodeValue(parametrArrayWithQuotes);  		 		 		
 		String urlParameters = "params="+encodedValue;
 		String getDashboardResponse = postRequest(con, urlParameters);
		return getDashboardResponse;	  		
  	}
		
	private static String createParameterArray(String [] listOfStrings) {		  
		 String output = "";

	    for (int i = 0; i < listOfStrings.length; i++) {
	        output += "\"" + listOfStrings[i] + "\"";
	        if (i != listOfStrings.length - 1) {
	            output += ",";
	        }
	    }
	  output = "["+output+"]";	    	 
	return output;
	}//EOD createParameterArray
	
	public static String postRequest(HttpsURLConnection con,String urlParameters) throws Exception {
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		//System.out.println("\nSending 'POST' request to URL : " + url);
		//System.out.println("Post parameters : " + urlParameters);
		//System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
				new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		//System.out.println(response.toString());
		return response.toString();
	}


	private static String encodeValue(String value) {
		try {
			return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());

		} catch (UnsupportedEncodingException ex) {
			throw new RuntimeException(ex.getCause());
		}
	}
	
	/*public static void getPrivateDevicesAndroid() {
	Connector con = new Connector("https://qkm1vil.qualitykiosk.com");
	try {	
			String authToken = con.authenticateUser(UtilityFunctions.getEnvKeyValue("USERNAME"), UtilityFunctions.getEnvKeyValue("ACCESSKEY"));
		//String authToken = con.authenticateUser("rohitv@iprogrammer.com", "crysw3jb36jvdymyz7zq3gzk");
			System.out.println(authToken);		
			//For single device selection:
			MobileDevice selectedDevice = con.chooseSingleDevice(authToken, "Android");		
			System.out.println(selectedDevice.full_name);	
			 PropertiesAndConstants.PrivateFarmAndroid = selectedDevice.full_name;			 
			 PDriveFileDTO[] apps = con.getAvailableApps(authToken);
			 System.out.println(apps.length);
			 System.out.println(apps[0].file);
			PropertiesAndConstants.PrivateFarmUploadedApps = apps[0].file; 			
	    } catch (IOException e) {		
	    	e.printStackTrace();
	   } catch (ConnectError e) {		
		   e.printStackTrace();
	   }	
	}*/
	
	
	public static void getPrivateDevicesIos() {
		Connector con = new Connector("https://qkm1vil.qualitykiosk.com");
		try {	
				String authToken = con.authenticateUser(UtilityFunctions.getEnvKeyValue("USERNAME"), UtilityFunctions.getEnvKeyValue("ACCESSKEY"));
			//String authToken = con.authenticateUser("rohitv@iprogrammer.com", "crysw3jb36jvdymyz7zq3gzk");
				System.out.println(authToken);	
				
				
				//For single device selection:
				MobileDevice selectedDevice = con.chooseSingleDevice(authToken, "ios");		
				System.out.println(selectedDevice.full_name);	
				
				PropertiesAndConstants.PlatformVersion = selectedDevice.getVersion();
				System.out.println(PropertiesAndConstants.PlatformVersion);
				
				 PropertiesAndConstants.PrivateFarmios = selectedDevice.full_name;			 
				 PDriveFileDTO[] apps = con.getAvailableApps(authToken);
				 
				 System.out.println(apps.length);
				 System.out.println(apps[1].file);
				PropertiesAndConstants.PrivateFarmUploadedApps = apps[1].file; 			
		    } catch (IOException e) {	
		    	//System.out.println("exception");
		    	e.printStackTrace();
		   } catch (ConnectError e) {		
			   e.printStackTrace();
			   //System.out.println("conn error");
		   }	
		}
		
		public static void getDevice_on_private()
		{
			if(PropertiesAndConstants.envDictionary.get("APP_TYPE").equals("PrivateFarm_Android"))
			{
				//API.getPrivateDevicesAndroid();
				System.out.println("info: Selected "+PropertiesAndConstants.PrivateFarmAndroid +" Device ");
				Connector con = new Connector("https://qkm1vil.qualitykiosk.com");
				try {	
						String authToken = con.authenticateUser(UtilityFunctions.getEnvKeyValue("USERNAME"), UtilityFunctions.getEnvKeyValue("ACCESSKEY"));
					//String authToken = con.authenticateUser("rohitv@iprogrammer.com", "crysw3jb36jvdymyz7zq3gzk");
						System.out.println(authToken);		
						//For single device selection:
						MobileDevice selectedDevice = con.chooseSingleDevice(authToken, "Android");		
						System.out.println(selectedDevice.full_name);	
						 PropertiesAndConstants.PrivateFarmAndroid = selectedDevice.full_name;			 
						 PDriveFileDTO[] apps = con.getAvailableApps(authToken);
						 System.out.println(apps.length);
						 System.out.println(apps[0].file);
						PropertiesAndConstants.PrivateFarmUploadedApps = apps[0].file; 			
				    } catch (IOException e) {		
				    	e.printStackTrace();
				   } catch (ConnectError e) {		
					   e.printStackTrace();
				   }	
				
				
			}
			else if(PropertiesAndConstants.envDictionary.get("APP_TYPE").equals("PrivateFarm_ios"))
			{
				//API.getPrivateDevicesIos();
				System.out.println("info: Selected "+PropertiesAndConstants.PrivateFarmios +" Device ");
				Connector con = new Connector("https://qkm1vil.qualitykiosk.com");
				try {	
						String authToken = con.authenticateUser(UtilityFunctions.getEnvKeyValue("USERNAME"), UtilityFunctions.getEnvKeyValue("ACCESSKEY"));
					//String authToken = con.authenticateUser("rohitv@iprogrammer.com", "crysw3jb36jvdymyz7zq3gzk");
						System.out.println(authToken);	
						
						
						//For single device selection:
						MobileDevice selectedDevice = con.chooseSingleDevice(authToken, "ios");		
						System.out.println(selectedDevice.full_name);	
						
						PropertiesAndConstants.PlatformVersion = selectedDevice.getVersion();
						System.out.println(PropertiesAndConstants.PlatformVersion);
						
						 PropertiesAndConstants.PrivateFarmios = selectedDevice.full_name;			 
						 PDriveFileDTO[] apps = con.getAvailableApps(authToken);
						 
						 System.out.println(apps.length);
						 System.out.println(apps[1].file);
						PropertiesAndConstants.PrivateFarmUploadedApps = apps[1].file; 			
				    } catch (IOException e) {	
				    	//System.out.println("exception");
				    	e.printStackTrace();
				   } catch (ConnectError e) {		
					   e.printStackTrace();
					   //System.out.println("conn error");
				   }	
				
				
			}
		}
	//public static void main(String [] args ) throws Exception {
		//getPrivateDevicesAndroid();
	//}

}