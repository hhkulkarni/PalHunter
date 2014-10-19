package com.usc.csci587;

import java.util.ArrayList;
import java.util.List;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Sender;

public class GCMSender {
	
	// The SENDER_ID here is the "Browser Key" that was generated when I
		// created the API keys for my Google APIs project.
		private final String SENDER_ID = "AIzaSyDrsepPza-SfweaJqMGa-4YfTNYmHp_65E";
		
		// This is a *cheat*  It is a hard-coded registration ID from an Android device
		// that registered itself with GCM using the same project id shown above.
	//	private  String ANDROID_DEVICE = "APA91bFS0UK_Selbg_WEPoizF09v0RapY6q_-IpzfmaeUDIle934YwikFjM_tbuMW0EjKD-Mx9OepmCeEUErBvELp8AuzqzUQRoVl9Nq1hIZQrJhH8asY5SSgwv6U-zzEZEr_mln3kUU6yxa6EINZJAvpSjeIARq5P1_Vo7_4XZYWpymdvjPCYk";
			
		// This array will hold all the registration ids used to broadcast a message.
		// for this demo, it will only have the ANDROID_DEVICE id that was captured 
		// when we ran the Android client app through Eclipse.
		private static List<String> androidTargets = new ArrayList<String>();
	       
		public static void main(String[] args) {
			
			new GCMSender("APA91bEsqoSFbSRzGdBftpfBIRGpc323cG5sB6oYAudarZjCrBwnE56cmljDKS_7kVv3PNNPs9A5_eq9SBIn5ixyVABNGZcVPw_n49yTHV3m-9gWllt0wDECEfjMLZvid83QIZdT7EJJMCYFQ6PuTrvOp_ZyVsGP4lhARGzitSmbgdsLLDwirS8").sendMessage("Hi dear5671235!!");
		}
		
		public GCMSender(String regId){
	//		ANDROID_DEVICE=regId;
			 androidTargets.add(regId);
		}

	public void sendMessage(String msg) {
		// TODO Auto-generated method stub
		
		
		
		// We'll collect the "CollapseKey" and "Message" values from our JSP page
				String collapseKey = "";
				String userMessage = "";
				
				try {
					 userMessage =msg;
					collapseKey = "msg";
				} catch (Exception e) {
					e.printStackTrace();
					return;
				}

				// Instance of com.android.gcm.server.Sender, that does the
				// transmission of a Message to the Google Cloud Messaging service.
				Sender sender = new Sender(SENDER_ID);
				
				// This Message object will hold the data that is being transmitted
				// to the Android client devices.  For this demo, it is a simple text
				// string, but could certainly be a JSON object.
				Message message = new Message.Builder()
				
				// If multiple messages are sent using the same .collapseKey()
				// the android target device, if it was offline during earlier message
				// transmissions, will only receive the latest message for that key when
				// it goes back on-line.
				.collapseKey(collapseKey)
				.timeToLive(30)
				.delayWhileIdle(true)
				.addData("message", userMessage)
				.build();
				
				try {
					// use this for multicast messages.  The second parameter
					// of sender.send() will need to be an array of register ids.
					MulticastResult result = sender.send(message, androidTargets, 1);
					
					if (result.getResults() != null) {
						int canonicalRegId = result.getCanonicalIds();
						if (canonicalRegId != 0) {
							
						}
					} else {
						int error = result.getFailure();
						System.out.println("Broadcast failure: " + error);
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}

				// We'll pass the CollapseKey and Message values back to index.jsp, only so
				// we can display it in our form again.
				/*request.setAttribute("CollapseKey", collapseKey);
				request.setAttribute("Message", userMessage);
				
				request.getRequestDispatcher("index.jsp").forward(request, response);*/
						
			}
	}

