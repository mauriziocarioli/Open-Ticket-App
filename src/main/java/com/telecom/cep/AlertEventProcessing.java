/**
 * 
 */
package com.telecom.cep;

import java.util.ArrayList;
import java.util.Date;
//import java.util.HashMap;
//import java.util.LinkedList;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.telecom.Issue;
import com.telecom.Issues;
import com.telecom.TicketCreated;

import org.kie.api.event.rule.DebugAgendaEventListener;
import org.kie.api.event.rule.DebugRuleRuntimeEventListener;

public class AlertEventProcessing {

	/**
	 *
	 */

	private static final String DEFAULT_STATEFUL_KIE_SESSION = "telecom-realtime-ksession";
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

	//public static LinkListStack issues = new LinkListStack();
	
	public Issues issues = new Issues();

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

		System.out.println("===========================================================");
		System.out.println("| COMPLEX EVENT PROCESSING WITH DROOLS - STAND ALONE TEST |");
		System.out.println("==========================================================+");

		AlertEventProcessing droolsCEPService = AlertEventProcessing.getInstance();

		AlertEvent alertEvent = new AlertEvent();
		alertEvent.set_time(new Date().getTime());
		System.out.println("_time: " + alertEvent.get_time());
		alertEvent.setNeName("bdhlmbch");
		alertEvent.setAlertGroup("RECT");
		alertEvent.setServerSerial(1738805008L);
		alertEvent.setSummary("BRANDON HILLS REMOTE RECTIFIER FAIL MJR - C3E35");
		alertEvent.setCircuit("RECT - power");
		alertEvent.setSeverity(4);
		alertEvent.setNcFunction("INSERT");
		droolsCEPService.execute(alertEvent);

		Thread.sleep(1000);

		alertEvent = new AlertEvent();
		alertEvent.set_time(new Date().getTime());
		System.out.println("_time: " + alertEvent.get_time());
		alertEvent.setNeName("bdhlmbch");
		alertEvent.setAlertGroup("AC");
		alertEvent.setServerSerial(1738805010L);
		alertEvent.setSummary("BRANDON HILLS REMOTE AC FAIL MJ - C3E38");
		alertEvent.setCircuit("AC - power");
		alertEvent.setSeverity(0);
		alertEvent.setNcFunction("INSERT");
		droolsCEPService.execute(alertEvent);

		Thread.sleep(1000);

		alertEvent = new AlertEvent();
		alertEvent.set_time(new Date().getTime());
		System.out.println("_time: " + alertEvent.get_time());
		alertEvent.setNeName("bdhlmbch");
		alertEvent.setAlertGroup("AC");
		alertEvent.setServerSerial(1738805010L);
		alertEvent.setSummary("BRANDON HILLS REMOTE AC FAIL MJ - C3E38");
		alertEvent.setCircuit("AC - power");
		alertEvent.setSeverity(0);
		alertEvent.setNcFunction("DELETE");
		droolsCEPService.execute(alertEvent);

		Thread.sleep(1000);

		while (true) {

			alertEvent = new AlertEvent();
			alertEvent.set_time(new Date().getTime());
			System.out.println("_time: " + alertEvent.get_time());
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
			System.out.println("   INITIALIZING KIE RUNTIME FOR DROOLS FUSION");
			System.out.println("==========================================================");
			
			kServices = KieServices.Factory.get();
			kContainer = kServices.newKieContainer(kServices.newReleaseId(groupId, artifactId, version));
			kSession = kContainer.newKieSession(DEFAULT_STATEFUL_KIE_SESSION);
			//kSession.addEventListener(new DebugAgendaEventListener());
			//kSession.addEventListener(new DebugRuleRuntimeEventListener());
			//kServices.getLoggers().newConsoleLogger(kSession);
			//kServices.getLoggers().newFileLogger(kSession, "./target/drools");
			
			issues.setLastIssueId(-1);
			issues.setList(new ArrayList<Issue>());
			kSession.setGlobal("issues", issues);

			//kSession.setGlobal("issuesMap", new HashMap<Long, AlertEventCorrelation>());
			System.out.println("Initialized the KIE runtime for Drools Fusion...");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void execute(AlertEvent event) {

		// try {
		System.out.println(">>>>>> RECEIVED AN ALERT");

		// anything to with event object
		totalFactCount++;
		System.out.println(">>>>>> RUNNING THROUGH THE RULES..." + totalFactCount);
		kSession.insert(event);
		kSession.fireAllRules();

		//HashMap issueM = (HashMap) kSession.getGlobal("issueMap");
		issues = (Issues) kSession.getGlobal("issues");
		
		for (int i = 0; i < issues.getList().size(); i++) {
			Issue issue = issues.getList().get(i);
			System.out.println(">>>>>>>> Found Issue # "+issue.getIssueId());
			System.out.println(">>>>>>>> 	ne name: "+issue.getNeName());
			System.out.println(">>>>>>>> 	summary: "+issue.getSummary());
			System.out.println(">>>>>>>> 	time of incident:     "+new Date(issue.getTimeOfIncident()));
			System.out.println(">>>>>>>> 	time when AC went up: "+new Date(issue.getTimeAcBackUp()));
			TicketCreated ticketWasCreated = new TicketCreated();
			ticketWasCreated.setIssueId(issue.getIssueId());
			System.out.println(">>>>>>>> Ticket is created");
			kSession.insert(ticketWasCreated);
			kSession.fireAllRules();
		}

		//LinkedList list = new LinkedList();
		//list.addAll(issueM.values());
		//issues.pushAll(list);
		System.out.println(">>>>>> FINISHED RUNNING THROUGH THE RULES");

	}

}
