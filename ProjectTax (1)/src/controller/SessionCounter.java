package controller;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionCounter implements HttpSessionListener {

	 private static int totalActiveSessions;
	 
	  public static int getTotalActiveSession(){
		return totalActiveSessions;

	}

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		synchronized(this){
		totalActiveSessions++;}
		System.out.println("Session created. Total active sessions:  "+totalActiveSessions);

		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		synchronized(this){
		totalActiveSessions--;}
		System.out.println("Session destroyed. Total active sessions:  "+totalActiveSessions);

		
	}
}
