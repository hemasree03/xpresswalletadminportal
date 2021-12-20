package com.ceva.unionbank.channel;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.ceva.fbn.txn.main.UnitTest;
import com.ceva.wallet.core.service.ServiceImpl;
import com.ceva.xpress.bvn.service.impl.BvnService;
import com.ceva.xpress.fcmb.wallet.agent.trans.services.AgentWalletFcmbServiceCaller;
import com.ceva.xpress.paybill.bank.service.impl.PaybillServiceCall;
import com.ceva.xpress.paybill.wallet.service.call.PaybillAirtimeServiceCall;
import com.ceva.xpress.wallet.agent.emailsms.services.AgentWalletSMSEmailServiceCaller;
import com.ceva.xpress.wallet.agent.fundtrans.services.AgentWalletNipServiceCaller;
import com.ceva.xpress.wallet.agent.paybill.services.AgentWalletPayBillServiceCaller;

public class ExternalServices {
	
private static Logger logger = Logger.getLogger(ExternalServices.class);
	
	public static void main(String args[]) {
		//bvndetails();
		//System.out.println("SMS Response ::" + agentbonus("2349502188246","KKILLj9900","1000"));
		billers();
	}
	
	
	public static void sendSMS(String str,String username)
	{

		try{

			//String username = "2349502188246";
			JSONObject respJson=null;
			JSONObject request = new JSONObject();
			request.put("username", username );
			request.put("reqtype", "SEND_SMS" );
			request.put("channel", "MOBILE");
			
			//String str = "{'recipients': ['08167864124'],'sender': 'Xpress','message': 'test message'}";

			JSONObject bankReqObj = new JSONObject(str);
			
			
			request.put("bankrequest", bankReqObj );
			
			System.out.println("SMS Request Message ::" + str);
			System.out.println("SMS Request ::" + request);

			respJson = AgentWalletSMSEmailServiceCaller.route(request);

			System.out.println("SMS Response ::" + respJson);
			
/*			{"respdesc":"SUCCESS","data":{"sms":{"createdAt":"2020-06-30 08:51:53.376","sender":"Xpress","recipient":["2348167864124"],"messageId":[null],"message":"test message","status":["SENT"]}},"servrespcode":"00","respcode":"00","servrespdesc":"SUCCESS"}
*/

		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		}
		
		
	}
	
	public static void createTemplate()
	{

		try{

			String username = "2349502188246";

			JSONObject request = new JSONObject();
			request.put("username", username );
			request.put("reqtype", "CREATE_TEMPLATE" );
			request.put("channel", "MOBILE");
			
			String str = "{\"templateId\": \"cevatest8\",\"sourceCode\":\"<h2> test email </h2> <br> <div style='line-height: 20px;'> "
					+ "Dear {firstName}, Below are the details: <ul align='left'> <li>{dateTime} </li> <li>Name of User {userFirstName} {lastLastName} </li> <li>Role Name - {role} </li> </ul> </br> </div> \"}";
			
			
			JSONObject bankReqObj = new JSONObject(str);
			
			request.put("bankrequest", bankReqObj );
			
			System.out.println("Request ::" + request);

			JSONObject respJson = AgentWalletSMSEmailServiceCaller.route(request);

			logger.debug("Response ::" + respJson);


		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		}
	}
		public static void sendEmail()
		{

			try{

				String username = "2349502188246";

				JSONObject request = new JSONObject();
				request.put("username", username );
				request.put("reqtype", "SEND_EMAIL" );
				request.put("channel", "MOBILE");
				
				String str = "{\"recipients\":[\"sravan@cevaltd.com\"],\"sender\":\"test@xpress.co\",\"senderName\":\"abdussamad\","
						+ "\"subject\":\"testing\",\"message\":\"postmantest\",\"parameters\":{\"firstName\":\"ade\",\"firstname\":\"ade\",\"lastname\":"
						+ "\"ogbeni\",\"userFirstName\":\"ade\",\"lastLastName\":\"ogbeni\",\"role\":\"manager\","
						+ "\"dateTime\":\"23/04/2020\"},\"templateName\":\"cevatest2\"}";
				
				
				JSONObject bankReqObj = new JSONObject(str);
				
				request.put("bankrequest", bankReqObj );
				
				System.out.println("Request ::" + request);

				JSONObject respJson = AgentWalletSMSEmailServiceCaller.route(request);

				System.out.println("Response ::" + respJson);


			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();

			}
		}
		public static String billers()
		{
			JSONObject respJson=null;
			String result=null;
			try{


				String username = "2349502188246";

				JSONObject request = new JSONObject();
				request.put("username", username );
				request.put("reqtype", "BILLERS" );
				request.put("channel", "WEB");

				System.out.println("Request ::" + request);

				
				//respJson = PaybillServiceCall.getBillers(request);
				//result=respJson.toString();
				result="{\"respdesc\":\"SUCCESS\",\"bankresp\":[{\"lookUp\":false,\"baseURL\":\"\",\"visibleStatus\":0,\"updateFeature\":false,\"enableBulk\":true,\"editMode\":false,\"visibleOnReseller\":1,\"id\":6113,\"billerName\":\"9MOBILE AIRTIME\",\"visibleOnResellerApi\":1,\"logoUrl\":\"./billerlogos/billerLogob17ea0d5ddb94f14ac93d64ead107156.jpg\",\"billerCode\":\"9MOBILEAIRTIME\"},{\"lookUp\":false,\"baseURL\":\"\",\"visibleStatus\":0,\"updateFeature\":false,\"enableBulk\":false,\"editMode\":false,\"visibleOnReseller\":1,\"id\":6115,\"billerName\":\"9mobiledata 1\",\"visibleOnResellerApi\":1,\"logoUrl\":\"./billerlogos/billerLogo0e33dc1d59464769b36385ffc8f8f471.jpg\",\"billerCode\":\"9mobiledata1\"},{\"lookUp\":false,\"baseURL\":\"\",\"visibleStatus\":0,\"updateFeature\":false,\"enableBulk\":false,\"editMode\":false,\"visibleOnReseller\":1,\"id\":6096,\"billerName\":\"ABUJA POSTPAID\",\"visibleOnResellerApi\":1,\"logoUrl\":\"./billerlogos/billerLogod440655366894d7ebb50a54518785343.jpg\",\"billerCode\":\"ABJ_POSTPAID\"},{\"lookUp\":false,\"baseURL\":\"\",\"visibleStatus\":0,\"updateFeature\":false,\"enableBulk\":false,\"editMode\":false,\"visibleOnReseller\":1,\"id\":6097,\"billerName\":\"ABUJA PREPAID\",\"visibleOnResellerApi\":1,\"logoUrl\":\"./billerlogos/billerLogod2af189f8bd64739b4930f962eb4245a.jpg\",\"billerCode\":\"ABJ_PREPAID\"},{\"lookUp\":false,\"enableBulk\":true,\"visibleOnReseller\":1,\"billerName\":\"Airtel\",\"visibleOnResellerApi\":1,\"logoUrl\":\"./billerlogos/billerLogoeef631e1a9264abcb38f6c4c898958e0.jpg\",\"billerCode\":\"airtel\",\"baseURL\":\"\",\"visibleStatus\":0,\"updateFeature\":false,\"resellerTop\":1,\"editMode\":false,\"id\":6003},{\"lookUp\":false,\"baseURL\":\"\",\"visibleStatus\":0,\"updateFeature\":false,\"enableBulk\":false,\"editMode\":false,\"visibleOnReseller\":1,\"id\":6004,\"billerName\":\"Airtel Data\",\"visibleOnResellerApi\":1,\"logoUrl\":\"./billerlogos/billerLogo64517376224342e9b5682f6798cfdb69.jpg\",\"billerCode\":\"airtel-data\"},{\"lookUp\":false,\"baseURL\":\"\",\"visibleStatus\":0,\"updateFeature\":false,\"enableBulk\":false,\"editMode\":false,\"visibleOnReseller\":1,\"id\":6102,\"billerName\":\"ARM\",\"visibleOnResellerApi\":1,\"logoUrl\":\"./billerlogos/billerLogo0aa712f5c3284458970673ccaa137365.jpg\",\"billerCode\":\"ARM\"},{\"lookUp\":false,\"enableBulk\":false,\"visibleOnReseller\":1,\"billerName\":\"DSTV2\",\"visibleOnResellerApi\":1,\"logoUrl\":\"./billerlogos/billerLogo51bd90b6d9904d5eb97f8a08172f08aa.jpg\",\"billerCode\":\"DSTV2\",\"baseURL\":\"\",\"visibleStatus\":0,\"updateFeature\":false,\"resellerTop\":1,\"editMode\":false,\"id\":6091},{\"lookUp\":false,\"baseURL\":\"\",\"visibleStatus\":0,\"updateFeature\":false,\"enableBulk\":false,\"editMode\":false,\"visibleOnReseller\":1,\"id\":6069,\"billerName\":\"EKO electric Postpaid\",\"visibleOnResellerApi\":1,\"logoUrl\":\"./billerlogos/billerLogo5e8183fa7a43402d85cb2792b3b0b3cc.jpg\",\"billerCode\":\"ekdc postpaid\"},{\"lookUp\":false,\"baseURL\":\"\",\"visibleStatus\":0,\"updateFeature\":false,\"enableBulk\":false,\"editMode\":false,\"visibleOnReseller\":1,\"id\":6068,\"billerName\":\"EKO electric prepaid\",\"visibleOnResellerApi\":1,\"logoUrl\":\"./billerlogos/billerLogoea7903cc5fe24bd2ad43d7d3679262fe.jpg\",\"billerCode\":\"ekdc prepaid\"},{\"lookUp\":false,\"baseURL\":\"\",\"updateFeature\":false,\"enableBulk\":false,\"editMode\":false,\"visibleOnReseller\":1,\"id\":6067,\"billerName\":\"Enugu Electric Postpaid\",\"visibleOnResellerApi\":1,\"logoUrl\":\"./billerlogos/billerLogoa1c2b79a70664da38cb1e1deb070174f.jpg\",\"billerCode\":\"eedc postpaid\"},{\"lookUp\":false,\"baseURL\":\"\",\"updateFeature\":false,\"enableBulk\":false,\"editMode\":false,\"visibleOnReseller\":1,\"id\":6066,\"billerName\":\"Enugu Electric Prepaid\",\"visibleOnResellerApi\":1,\"logoUrl\":\"./billerlogos/billerLogo11f04d796fdc48afb20b282fb049830b.jpg\",\"billerCode\":\"eedc prepaid\"},{\"lookUp\":false,\"baseURL\":\"\",\"visibleStatus\":0,\"updateFeature\":false,\"enableBulk\":false,\"editMode\":false,\"visibleOnReseller\":1,\"id\":6104,\"billerName\":\"FIDELITY\",\"visibleOnResellerApi\":1,\"logoUrl\":\"./billerlogos/billerLogo8c7c60332e634e77a7dbe9724901632e.jpg\",\"billerCode\":\"FIDELITY\"},{\"lookUp\":false,\"enableBulk\":true,\"visibleOnReseller\":1,\"billerName\":\"Glo\",\"visibleOnResellerApi\":1,\"logoUrl\":\"./billerlogos/billerLogof9b771016102443d8167b94d6b8d26bd.jpg\",\"billerCode\":\"glo\",\"baseURL\":\"\",\"visibleStatus\":0,\"updateFeature\":false,\"resellerTop\":1,\"editMode\":false,\"id\":3002},{\"lookUp\":false,\"baseURL\":\"\",\"visibleStatus\":0,\"updateFeature\":false,\"enableBulk\":false,\"editMode\":false,\"visibleOnReseller\":1,\"id\":6002,\"billerName\":\"Glo Data\",\"visibleOnResellerApi\":1,\"logoUrl\":\"./billerlogos/billerLogo01042de11514458d948bd55b0ef2fa6b.jpg\",\"billerCode\":\"glo-data\"},{\"lookUp\":false,\"enableBulk\":false,\"visibleOnReseller\":1,\"billerName\":\"GOTV2\",\"visibleOnResellerApi\":1,\"logoUrl\":\"./billerlogos/billerLogo8835edaf1d804f45a9949be5e9b0f1cb.jpg\",\"billerCode\":\"GOTV2\",\"baseURL\":\"\",\"visibleStatus\":0,\"updateFeature\":false,\"resellerTop\":1,\"editMode\":false,\"id\":6092},{\"lookUp\":false,\"baseURL\":\"\",\"updateFeature\":false,\"enableBulk\":false,\"editMode\":false,\"visibleOnReseller\":1,\"id\":6065,\"billerName\":\"ibadan electric postpaid\",\"visibleOnResellerApi\":1,\"logoUrl\":\"./billerlogos/billerLogo699ed759e6a34bafb41fe7a6367772f5.jpg\",\"billerCode\":\"ibedc postpaid\"},{\"lookUp\":false,\"baseURL\":\"\",\"updateFeature\":false,\"enableBulk\":false,\"editMode\":false,\"visibleOnReseller\":1,\"id\":6064,\"billerName\":\"Ibandan electric prepaid\",\"visibleOnResellerApi\":1,\"logoUrl\":\"./billerlogos/billerLogo4f923a0b509f4cc4b30aa6c3498d3509.jpg\",\"billerCode\":\"ibedc prepaid\"},{\"lookUp\":false,\"baseURL\":\"\",\"updateFeature\":false,\"enableBulk\":false,\"editMode\":false,\"visibleOnReseller\":1,\"id\":6063,\"billerName\":\"Ikeja Electric PostPaid\",\"visibleOnResellerApi\":1,\"logoUrl\":\"./billerlogos/billerLogo0429880c79af474295829eb2c6ba942a.jpg\",\"billerCode\":\"iedc postpaid\"},{\"lookUp\":false,\"baseURL\":\"\",\"updateFeature\":false,\"enableBulk\":false,\"editMode\":false,\"visibleOnReseller\":1,\"id\":2,\"billerName\":\"Ikeja Electric Prepaid\",\"visibleOnResellerApi\":1,\"logoUrl\":\"./billerlogos/billerLogoe7fbb5c48e3a4feb8da830ba7005c486.jpg\",\"billerCode\":\"iedc\"},{\"lookUp\":false,\"baseURL\":\"\",\"visibleStatus\":0,\"updateFeature\":false,\"enableBulk\":false,\"editMode\":false,\"visibleOnReseller\":1,\"id\":6100,\"billerName\":\"Jos Electric Postpaid\",\"visibleOnResellerApi\":1,\"logoUrl\":\"./billerlogos/billerLogo21899655aab94add8a9510acbe540112.jpg\",\"billerCode\":\"JOS_POSTPAID\"},{\"lookUp\":false,\"baseURL\":\"\",\"visibleStatus\":0,\"updateFeature\":false,\"enableBulk\":false,\"editMode\":false,\"visibleOnReseller\":1,\"id\":6101,\"billerName\":\"Jos Electric Prepaid\",\"visibleOnResellerApi\":1,\"logoUrl\":\"./billerlogos/billerLogoe3c45f3adcd34ce8a1a8a38d9566745b.jpg\",\"billerCode\":\"JOS_PREPAID\"},{\"lookUp\":false,\"baseURL\":\"\",\"visibleStatus\":0,\"updateFeature\":false,\"enableBulk\":false,\"editMode\":false,\"visibleOnReseller\":1,\"id\":6098,\"billerName\":\"Kaduna Electric Postpaid\",\"visibleOnResellerApi\":1,\"logoUrl\":\"./billerlogos/billerLogoecdd60b2e85d4271b30bdc92363d038e.jpg\",\"billerCode\":\"KADUNA_POSTPAID\"},{\"lookUp\":false,\"baseURL\":\"\",\"visibleStatus\":0,\"updateFeature\":false,\"enableBulk\":false,\"editMode\":false,\"visibleOnReseller\":1,\"id\":6099,\"billerName\":\"Kaduna Electric Prepaid\",\"visibleOnResellerApi\":1,\"logoUrl\":\"./billerlogos/billerLogoefa23d2e21944f07b156aaa719c8821f.jpg\",\"billerCode\":\"KADUNA_PREPAID\"},{\"lookUp\":false,\"baseURL\":\"\",\"visibleStatus\":0,\"updateFeature\":false,\"enableBulk\":false,\"editMode\":false,\"visibleOnReseller\":1,\"id\":6094,\"billerName\":\"KANO POSTPAID\",\"visibleOnResellerApi\":1,\"logoUrl\":\"./billerlogos/billerLogo3a997103ec2e472bb0152c085041c651.jpg\",\"billerCode\":\"KANO_POSTPAID\"},{\"lookUp\":false,\"baseURL\":\"\",\"visibleStatus\":0,\"updateFeature\":false,\"enableBulk\":false,\"editMode\":false,\"visibleOnReseller\":1,\"id\":6095,\"billerName\":\"KANO PREPAID\",\"visibleOnResellerApi\":1,\"logoUrl\":\"./billerlogos/billerLogo319f7821a5484f9abd34581b4a00561b.jpg\",\"billerCode\":\"KANO_PREPAID\"},{\"lookUp\":false,\"baseURL\":\"\",\"visibleStatus\":0,\"updateFeature\":false,\"enableBulk\":false,\"editMode\":false,\"visibleOnReseller\":1,\"id\":6083,\"billerName\":\"Law Union and rock\",\"visibleOnResellerApi\":1,\"logoUrl\":\"./billerlogos/billerLogoc88779f65153450c8478c874d88eed34.jpg\",\"billerCode\":\"law union and rock\"},{\"lookUp\":false,\"baseURL\":\"\",\"visibleStatus\":0,\"updateFeature\":false,\"enableBulk\":false,\"editMode\":false,\"visibleOnReseller\":1,\"id\":6129,\"billerName\":\"Leadway\",\"visibleOnResellerApi\":1,\"logoUrl\":\"./billerlogos/billerLogofec7d7c3f79d4625bfaf60b16c27fd84.png\",\"billerCode\":\"Leadway\"},{\"lookUp\":false,\"enableBulk\":true,\"visibleOnReseller\":1,\"billerName\":\"MTN\",\"visibleOnResellerApi\":1,\"logoUrl\":\"./billerlogos/billerLogod2960d2e03aa4b96be28e33845872d1a.jpg\",\"billerCode\":\"mtn\",\"baseURL\":\"\",\"visibleStatus\":0,\"updateFeature\":false,\"resellerTop\":1,\"editMode\":false,\"id\":1002},{\"lookUp\":false,\"baseURL\":\"\",\"visibleStatus\":0,\"updateFeature\":false,\"enableBulk\":false,\"editMode\":false,\"visibleOnReseller\":1,\"id\":6061,\"billerName\":\"MTN DATA\",\"visibleOnResellerApi\":1,\"logoUrl\":\"./billerlogos/billerLogo91270737a8904c48b4ca25719df30833.jpg\",\"billerCode\":\"data\"},{\"lookUp\":false,\"baseURL\":\"\",\"visibleStatus\":0,\"updateFeature\":false,\"enableBulk\":true,\"editMode\":false,\"visibleOnReseller\":1,\"id\":4002,\"billerName\":\"NTEL\",\"visibleOnResellerApi\":1,\"logoUrl\":\"./billerlogos/billerLogodfb4231fdcf3494d985405249ac356e4.jpg\",\"billerCode\":\"NTEL\"},{\"lookUp\":false,\"baseURL\":\"\",\"visibleStatus\":0,\"updateFeature\":false,\"enableBulk\":false,\"editMode\":false,\"visibleOnReseller\":1,\"id\":5002,\"billerName\":\"NTELBundle\",\"visibleOnResellerApi\":1,\"logoUrl\":\"./billerlogos/billerLogod769024f442d4bd192535fba37c5ab3f.jpg\",\"billerCode\":\"NTELBundle\"},{\"lookUp\":false,\"baseURL\":\"\",\"updateFeature\":false,\"enableBulk\":false,\"editMode\":false,\"visibleOnReseller\":1,\"id\":6088,\"billerName\":\"OGUN STATE\",\"visibleOnResellerApi\":1,\"logoUrl\":\"./billerlogos/billerLogo31bfbdd2aee9433abc6cba3bc589244a.jpg\",\"billerCode\":\"OGUN\"},{\"lookUp\":false,\"baseURL\":\"\",\"updateFeature\":false,\"enableBulk\":false,\"editMode\":false,\"visibleOnReseller\":1,\"id\":6087,\"billerName\":\"OYO STATE\",\"visibleOnResellerApi\":1,\"logoUrl\":\"./billerlogos/billerLogo2b096fbab6db483b974ca54cde66f4ff.jpg\",\"billerCode\":\"OYO\"},{\"lookUp\":false,\"enableBulk\":false,\"visibleOnReseller\":1,\"billerName\":\"PHED2\",\"visibleOnResellerApi\":1,\"logoUrl\":\"./billerlogos/billerLogo5f907e81932e4a30a85158ec233f94a3.jpg\",\"billerCode\":\"PHEDDIR2\",\"baseURL\":\"\",\"visibleStatus\":0,\"updateFeature\":false,\"resellerTop\":1,\"editMode\":false,\"id\":6090},{\"lookUp\":false,\"baseURL\":\"\",\"updateFeature\":false,\"enableBulk\":false,\"editMode\":false,\"visibleOnReseller\":1,\"id\":6079,\"billerName\":\"SMILE\",\"visibleOnResellerApi\":1,\"logoUrl\":\"./billerlogos/billerLogod7a019ff12aa47ac8fb4fd924375a74a.jpg\",\"billerCode\":\"SMILE\"},{\"lookUp\":false,\"baseURL\":\"\",\"updateFeature\":false,\"enableBulk\":false,\"editMode\":false,\"visibleOnReseller\":1,\"id\":6093,\"billerName\":\"SPECTRANET\",\"visibleOnResellerApi\":1,\"logoUrl\":\"./billerlogos/billerLogod30ccf1e55e8436d8b868825830f4cb8.png\",\"billerCode\":\"SPECTRANET\"},{\"lookUp\":false,\"baseURL\":\"\",\"updateFeature\":false,\"enableBulk\":false,\"editMode\":false,\"visibleOnReseller\":1,\"id\":6007,\"billerName\":\"Startimes\",\"visibleOnResellerApi\":1,\"logoUrl\":\"./billerlogos/billerLogob01fff3668a3431b876b6a8966274531.jpg\",\"billerCode\":\"startimes\"},{\"lookUp\":false,\"baseURL\":\"\",\"visibleStatus\":0,\"updateFeature\":false,\"enableBulk\":false,\"editMode\":false,\"visibleOnReseller\":1,\"id\":6105,\"billerName\":\"VERITASREG\",\"visibleOnResellerApi\":1,\"billerCode\":\"VERITASREG\"},{\"lookUp\":false,\"baseURL\":\"\",\"visibleStatus\":0,\"updateFeature\":false,\"enableBulk\":false,\"editMode\":false,\"visibleOnReseller\":1,\"id\":6084,\"billerName\":\"WAEC CHECK\",\"visibleOnResellerApi\":1,\"logoUrl\":\"./billerlogos/billerLogocceeb9af24944c7e88419ce01a99883e.jpg\",\"billerCode\":\"WAECCHECK\"},{\"lookUp\":false,\"enableBulk\":false,\"visibleOnReseller\":1,\"billerName\":\"WAECREGISTER\",\"visibleOnResellerApi\":1,\"logoUrl\":\"./billerlogos/billerLogo8e05de42db5d4522b7f8a53a3436cfee.jpg\",\"billerCode\":\"WAECREGISTER\",\"baseURL\":\"\",\"visibleStatus\":0,\"updateFeature\":false,\"resellerTop\":1,\"editMode\":false,\"id\":6085}],\"servrespcode\":\"00\",\"respcode\":\"00\",\"servrespdesc\":\"SUCCESS\"}" ; 
					

				logger.debug("Billers Response ::" + result);


			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();

			}
			return result;
		}


		public static void billersAndProducts()
		{

			try{


				String username = "2349502188246";

				JSONObject request = new JSONObject();
				request.put("username", username );
				request.put("reqtype", "BILLERS_AND_PRD" );
				request.put("channel", "MOBILE");

				System.out.println("Request ::" + request);

				JSONObject respJson = AgentWalletPayBillServiceCaller.route(request);

				System.out.println("Response ::" + respJson);


			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();

			}
			
		}

		
		public static void billersandproductandfields()
		{

			try{


				String username = "2349502188246";

				JSONObject request = new JSONObject();
				request.put("username", username );
				request.put("reqtype", "BILLERS_AND_PRD" );
				request.put("channel", "MOBILE");

				System.out.println("Request ::" + request);

				JSONObject respJson = AgentWalletPayBillServiceCaller.route(request);

				System.out.println("Response ::" + respJson);


			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();

			}
			
		}
		
		public static void productfields()
		{

			try{


				String username = "2349502188246";

				JSONObject request = new JSONObject();
				request.put("username", username );
				request.put("reqtype", "PRDFIELDS" );
				request.put("channel", "MOBILE");

				System.out.println("Request ::" + request);

				JSONObject respJson = AgentWalletPayBillServiceCaller.route(request);

				System.out.println("Response ::" + respJson);


			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();

			}
			
		}
		
		public static String accountOpen(String str,String username)
		{
			JSONObject respJson=null;
			String result=null;
			try{


				//String username = "2349502188246";
				
				JSONObject request = new JSONObject();
				request.put("reqtype", "FCMB_AGENT_ACCOUNT_OPEN" );
				request.put("channel", "MOBILE");
				request.put("userid", username );
				String pwd= "1234";
				request.put("pin", pwd);
				System.out.println("Request accountOpen input ::" +str);
				
				/*String str = "{'MaritalStatus':'Single','Email':'chigozie.mike@gmail.com','RequestID':'1592295992577',"
						+ "'FirstName':'OLUWASEUN1','DoB':'12/22/1983','PhoneNumber':'08091364729',"
						+ "'LastName':'APATA1','Gender':'M','MiddleName':'OLUWADAMILOLA','BVNNumber':'22146021767'}";*/
				
				JSONObject bankReqObj = new JSONObject(str);
				bankReqObj.put("RequestID", System.currentTimeMillis()+"");
				
				request.put("bankrequest", bankReqObj );
				
				
				System.out.println("Request accountOpen ::" + request);
				logger.debug("Request accountOpen ::" + request);

				respJson = AgentWalletFcmbServiceCaller.route(request);
				result=respJson.toString();
				//result="{\"respdesc\":\"Successful\",\"ResponseCode\":\"00\",\"RequestID\":\"1592296367711\",\"ResponseMessage\":\"Successful\",\"servrespcode\":\"00\",\"respcode\":\"00\",\"servrespdesc\":\"Successful\",\"AccountNumber\":\"6663893013\"}";

				System.out.println("Response accountOpen ::" + result);
				logger.debug("Response accountOpen ::" + result);


			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();

			}
			return result;
		}
		
		public static String accountEnq(String accountnumber,String username)
		{

			String result=null;
			try{


				/*String accountnumber = "1774691015";
				String username = "2349502188246";*/

				JSONObject request = new JSONObject();
				request.put("reqtype", "ACC_ENQ_BY_ACC");
				request.put("acctnumber", accountnumber );
				request.put("username", username);
				request.put("channel", "MOBILE");
				

				System.out.println("Request accountEnq ::" + request);
				logger.debug("Request accountEnq ::" + request);

				JSONObject respJson = AgentWalletFcmbServiceCaller.route(request);
				result=respJson.toString();
				
				//result="{\"respdesc\":\"Successful\",\"ResponseCode\":\"00\",\"RequestID\":\"AFM1593519003342\",\"ResponseMessage\":\"Successful\",\"servrespcode\":\"00\",\"respcode\":\"00\",\"servrespdesc\":\"Successful\",\"AccountName\":\"THADDEUS UGOCHUKWU ALAWUBA\"}";
				
				System.out.println("Response accountEnq ::" + result);
				logger.debug("Response accountEnq ::" + result);
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();

			}
			return result;
		}
		
		
		public static String bvndetails(String request)
		{

			String result=null;
			try{
				
				//String str = "{'bankCode':'058','bankVerificationNumber':'221498320901'}";
				System.out.println("Request bvndetails ::" + request);
				logger.debug("Request bvndetails ::" + request);
				JSONObject bankReqObj = new JSONObject(request);
				//System.out.println("Channel Response ::" + BvnService.bvnVal(bankReqObj) );
				JSONObject respJson =BvnService.bvnVal(bankReqObj); 
				result=respJson.toString();
				//result="{\"data\":{\"details\":{\"data\":{\"lastName\":\"OLADOJA\",\"watchListed\":\"0\",\"gender\":\"Male\",\"enrollmentBranch\":null,\"stateOfOrigin\":\"Kwara State\",\"enrollmentDate\":null,\"dateOfBirth\":\"26-Jun-1981\",\"stateOfResidence\":\"Oyo State\",\"title\":\"N/A\",\"levelOfAccount\":\"Level 1- Low Level Accounts\",\"firstName\":\"BOLAJI\",\"lgaOfResidence\":\"Ibadan South-West\",\"phoneNumber\":\"\",\"nationality\":\"Nigeria\",\"nameOnCard\":\"OLADOJA SAHEED BOLAJI\",\"base64Image\":\"f5ddb83bd7f064fdb904fbc915912c7372e4b094df67210bc221b506f7eeca26\",\"nationalIdentityNumber\":\"80019567212\",\"residentialAddress\":\"HOUSE 3, K-CLOSE, 14TH AVENUE, OLUYOLE ESTATE, IBADAN\",\"registrationDate\":\"\",\"middleName\":\"\",\"bvn\":\"22149832090\",\"email\":\"bolaji.s.oladoja@gmail.com\",\"maritalStatus\":\"Married\"},\"statusMessage\":\"Success\",\"status\":\"00\"}},\"status\":\"SUCCESS\"}";
				
				System.out.println("Response bvndetails ::" + result);
				logger.debug("Response bvndetails ::" + result);

			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return result;
		}
		
		public static String loadBanks(String username)
		{
			String result=null;
			try{
				
				JSONObject request = new JSONObject();
				request.put("username", username );
				request.put("channel", "MOBILE");
				request.put("reqtype", "LOAD_BANKS" );

				System.out.println("Request loadBanks ::" + request);
				logger.debug("Request loadBanks ::" + request);

				JSONObject respJson = AgentWalletNipServiceCaller.route(request);
				result=respJson.toString();
				
				System.out.println("Response loadBanks ::" + result);
				logger.debug("Response loadBanks ::" + result);
				
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return result;
		}
		
		public static String cashoutAccountEnq(String username,String accountnumber,String destinationbankcode)
		{
			String result=null;
			try{
				
				/*String accountnumber = "0018056244";
				String username = "2349502188246";
				String destinationbankcode = "044";*/

				JSONObject request = new JSONObject();
				request.put("username", username );
				request.put("reqtype", "NIP_ENQUIRY" );
				request.put("accountnumber", accountnumber );
				request.put("destinationbankcode", destinationbankcode );
				request.put("channel", "MOBILE");

				System.out.println("Request ::" + request);
				logger.debug("Request ::" + request);
				
				JSONObject respJson = AgentWalletNipServiceCaller.route(request);
				result=respJson.toString();
				
				//result="{\"respdesc\":\"Approved or completed successfully\",\"data\":{\"accounts\":{\"bankCode\":\"011\",\"otherNames\":\"NNACHRIS VENTURES\",\"success\":true,\"rcNumber\":null,\"surname\":\"NNACHRIS VENTURES\",\"accountType\":null,\"name\":\"NNACHRIS VENTURES\",\"bvn\":\"22237109677\",\"nameEnquirySessionId\":\"090201201202000209001022306330\",\"accountNumber\":\"2032888706\",\"statusMessage\":\"Approved or completed successfully \",\"kyclevel\":\"3\"}},\"servrespcode\":\"00\",\"respcode\":\"00\",\"servrespdesc\":\"Approved or completed successfully\"}";
				System.out.println("Response cashoutAccountEnq ::" + result);
				logger.debug("Response cashoutAccountEnq ::" + result);
				
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return result;
		}
		
		public static String billersAndProductsFieldsByBillerCode(String username,String billercode)
		{
			String result=null;
			try{
				
				

				JSONObject jrequest = new JSONObject();

				JSONObject requestHeader = new JSONObject();
				requestHeader.put("reqtype", "BILLER_PRD_BY_BILLERCODE" );
				requestHeader.put("flowid", System.currentTimeMillis()+"" );
				requestHeader.put("channel", "MOBILE" );
				requestHeader.put("transdate", System.currentTimeMillis()+"" );
				requestHeader.put("servicetype", "AIRTIME_PAYBILL");
				requestHeader.put("userid", username );  /*"2348032022832"*/

				jrequest.put("requestheader", requestHeader);

				JSONObject requestBody = new JSONObject();

				JSONObject bankDataObj = new JSONObject();
				JSONObject extradataObj = new JSONObject();
				extradataObj.put( "billercode" , billercode); /*"9MOBILE" */

				requestBody.put("bankdata" , bankDataObj );
				requestBody.put("extradata", extradataObj );

				jrequest.put("requestbody", requestBody);
				System.out.println("Request ::" + jrequest);
				logger.debug("Request ::" + jrequest);
				
				JSONObject respJson = PaybillAirtimeServiceCall.route(jrequest);
				result=respJson.toString();
				
				/*result="{ \"billerName\": \"Etisalat\", \"billerCode\": \"etisalat\", \"productfields\": [ { \"productId\": 9, \"productName\": \"Etisalat\", \"billerCode\": \"etisalat\", \"commission\": 0.0000, \"categoryCode\": \"002\", \"serviceChargeId\": 0, \"charge\": 0.00, \"chargeType\": \"FIXED\", \"productType\": \"xpress-product\", \"listId\": 10061, \"productFields\": [ { \"FieldId\": \"47\", \"FieldName\": \"Amount\", \"ControlType\": \"TEXTBOX\", \"MaxAmount\": \"0.00\", \"Required\": \"True\", \"Size\": \"255\", \"AmountField\": \"True\", \"LookupListId\": \"0\", \"PaymentInputKey\": \"amount\", \"CustomerLookupInputKey\": \"\", \"InputSource\": \"user\", \"InputSourceKey\": \"\" }, { \"FieldId\": \"48\", \"FieldName\": \"Email\", \"ControlType\": \"TEXTBOX\", \"MaxAmount\": \"0.00\", \"Required\": \"True\", \"Size\": \"255\", \"AmountField\": \"False\", \"LookupListId\": \"0\", \"PaymentInputKey\": \"\", \"CustomerLookupInputKey\": \"\", \"InputSource\": \"user\", \"InputSourceKey\": \"\" }, { \"FieldId\": \"49\", \"FieldName\": \"Phone Number\", \"ControlType\": \"TEXTBOX\", \"MaxAmount\": \"0.00\", \"Required\": \"True\", \"Size\": \"255\", \"AmountField\": \"False\", \"LookupListId\": \"0\", \"PaymentInputKey\": \"customerAccountNumber\", \"CustomerLookupInputKey\": \"\", \"InputSource\": \"user\", \"InputSourceKey\": \"\" }, { \"FieldId\": \"50\", \"FieldName\": \"Product\", \"ControlType\": \"LOOKUP\", \"MaxAmount\": \"0.00\", \"Required\": \"True\", \"Size\": \"0\", \"AmountField\": \"False\", \"LookupListId\": \"10060\", \"PaymentInputKey\": \"productCode\", \"CustomerLookupInputKey\": \"\", \"InputSource\": \"user\", \"InputSourceKey\": \"\", \"ListItems\": [ { \"ItemType\": \"9Mobile-VTU\", \"ItemName\": \"9Mobile-VTU\", \"Amount\": \"0\", \"ItemDesc\": \"-\" } ] } ], \"lookUpRequired\": false, \"lookUpFields\": [], \"paymentFields\": [ { \"fieldName\": \"Amount\", \"required\": true }, { \"fieldName\": \"Phone Number\", \"required\": true }, { \"fieldName\": \"Product\", \"required\": true } ] } ]}";
				System.out.println("Response billersAndProductsFieldsByBillerCode ::" + result);*/
				logger.debug("Response billersAndProductsFieldsByBillerCode ::" + result);
				
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return result;
		}
		
		/*public static String agentBonus(String userid,String externalid,String amount)
		{
			String result=null;
			try{
				
				System.out.println("userid ::" + userid);
				System.out.println("externalid ::" + externalid);
				System.out.println("amount ::" + amount);
				
				JSONObject respJson = UnitTest.agentbonus(userid,externalid,amount);
				result=respJson.toString();
				
				//result="{\"respdesc\":\"Approved or completed successfully\",\"respcode\":\"00\",\"servrespdesc\":\"Approved or completed successfully\"}";
				System.out.println("Agent Bonus Response ::" + result);
				
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return result;
		}*/
		
		public static String agentbonus(String userid, String externalid, String amount)
		{
			JSONObject jrequest = new JSONObject();
			String result=null;
			try {
				JSONObject jheader = new JSONObject();
				jheader.put("userid", userid);
				jheader.put("flowid", ""+System.currentTimeMillis());
				jheader.put("channel", "WEB");
				jheader.put("requesttype", "TRANSAC");
				jheader.put("nanotime", ""+System.nanoTime());

				JSONObject jbody = new JSONObject();
				String txnrefno=""+System.currentTimeMillis();
				jbody.put("txnrefno", txnrefno);
				jbody.put("txnamount", amount);
				jbody.put("curreny", "NAIRA");
				jbody.put("txntype", "C"); // A - Agent Initiated || C - Customer Initiated
				jbody.put("servicecode", "AGENTBONUS");
				jbody.put("exttxnrefno", externalid);
				jbody.put("narration", "narration");
				jbody.put("txnexactcode", "800002");
				jbody.put("txnnano", ""+System.nanoTime());
				jrequest.put("jheader", jheader);
				jrequest.put("jbody", jbody);
				System.out.println("jrequest->"+jrequest);
				logger.debug("jrequest->"+jrequest);
				
				result=new ServiceImpl().walletTransaction(jrequest).toString();
				
				System.out.println("Agent Bonus Response ::" + result);
				logger.debug("Agent Bonus Response ::" + result);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}
		

}
