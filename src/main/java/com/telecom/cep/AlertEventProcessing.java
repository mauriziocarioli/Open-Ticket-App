/**
 * 
 */
package com.telecom.cep;

import java.util.Date;
import java.util.HashMap;
//import java.util.LinkedList;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import org.kie.api.event.rule.DebugAgendaEventListener;
import org.kie.api.event.rule.DebugRuleRuntimeEventListener;

public class AlertEventProcessing {

	private static final String groupId = "com.telecom";
	private static final String artifactId = "Open-Ticket-App";
	private static final String version = "1.0.0-SNAPSHOT";

	private static AlertEventProcessing cepService = null;

	// Drools Fusion Runtime Configuration
	private KieServices kServices;
	private KieContainer kContainer;
	private KieSession kSession;

	// memory sizing and reporting activities
	static long totalFactCount = 0;

	public static LinkListStack issues = new LinkListStack();

	public static AlertEventProcessing getInstance() {

		if (cepService == null) {
			cepService = new AlertEventProcessing();
			cepService.init();
		}
		return cepService;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {

		System.out.println("==========================================================");
		System.out.println("COMPLEX EVENT PROCESSING WITH DROOLS - STAND ALONE TEST");
		System.out.println("==========================================================");

		AlertEventProcessing droolsCEPService = AlertEventProcessing.getInstance();
		
		AlertEvent alertEvent = new AlertEvent();
		alertEvent.set_time(new Date().getTime());
		alertEvent.setNeName("bdhlmbch");
		alertEvent.setAlertGroup("RECT");
		alertEvent.setServerSerial(1738805008L);
		alertEvent.setSummary("BRANDON HILLS REMOTE RECTIFIER FAIL MJR - C3E35");
		alertEvent.setCircuit("RECT - power");
		alertEvent.setSeverity(4);
		alertEvent.setNcFunction("INSERT");			
		droolsCEPService.execute(alertEvent);
		
		Thread.sleep(1000);
		
		while (true) {
			
			alertEvent = new AlertEvent();
			alertEvent.set_time(new Date().getTime());
			alertEvent.setNeName("dummynename");
			alertEvent.setAlertGroup("WFOWF");
			alertEvent.setServerSerial(1738805008L);
			alertEvent.setSummary("DUMMY SUMMARY");
			alertEvent.setCircuit("DUMMY CIRCUIT");
			alertEvent.setSeverity(0);
			alertEvent.setNcFunction("XYZ");			
			droolsCEPService.execute(alertEvent);
			
			Thread.sleep(1000);
			
		}
		
	}

	public void init() {

		try {

			System.out.println("==========================================================");
			System.out.println("INITIALIZING KIE RUNTIME FOR DROOLS FUSION");
			System.out.println("==========================================================");

			kServices = KieServices.Factory.get();
			kContainer = kServices.newKieContainer(kServices.newReleaseId(groupId, artifactId, version));
			kSession = kContainer.newKieSession("default-stateful-kie-session");
			kSession.addEventListener(new DebugAgendaEventListener());
			kSession.addEventListener(new DebugRuleRuntimeEventListener());
			kServices.getLoggers().newConsoleLogger(kSession);
			kServices.getLoggers().newFileLogger(kSession, "./target/drools.log");

			kSession.setGlobal("issuesMap", new HashMap<Long, AlertEventCorrelation>());
			kSession.setGlobal("startTime", new Date().getTime());
			kSession.setGlobal("startMemory", Runtime.getRuntime().freeMemory());
			kSession.setGlobal("totalFactCount", totalFactCount);
			System.out.println("initialized the kie runtime for drools fusion...");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static long prevTime = 0, currTime = 0;

	public void execute(AlertEvent event) {

		// try {
		System.out.println(">>>>>> RECEIVED AN ALERT");

		// anything to with event object
		kSession.setGlobal("totalFactCount", totalFactCount++);
		System.out.println(">>>>>> RUNNING THROUGH THE RULES..." + totalFactCount);
		kSession.insert(event);
		kSession.fireAllRules();

		//HashMap issueM = (HashMap) kSession.getGlobal("issueMap");

		//LinkedList list = new LinkedList();
		//list.addAll(issueM.values());
		//issues.pushAll(list);
		System.out.println(">>>>>> FINISHED RUNNING THROUGH THE RULES");

		if (prevTime == 0)
			prevTime = Long.parseLong(kSession.getGlobal("startTime").toString());
		currTime = new Date().getTime();
	}

}
