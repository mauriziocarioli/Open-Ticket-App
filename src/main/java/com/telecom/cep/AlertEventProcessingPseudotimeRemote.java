/**
 * 
 */
package com.telecom.cep;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
//import java.util.LinkedList;

import org.drools.core.command.runtime.AdvanceSessionTimeCommand;
import org.kie.api.KieServices;
import org.kie.api.command.BatchExecutionCommand;
import org.kie.api.command.Command;
import org.kie.api.command.KieCommands;
import org.kie.api.runtime.ExecutionResults;
import org.kie.api.runtime.KieContainer;
import org.kie.server.api.marshalling.MarshallingFormat;
import org.kie.server.api.model.ServiceResponse;
import org.kie.server.client.KieServicesClient;
import org.kie.server.client.KieServicesConfiguration;
import org.kie.server.client.KieServicesFactory;
import org.kie.server.client.RuleServicesClient;

import com.telecom.Issue;
import com.telecom.Issues;
import com.telecom.TicketCreated;

public class AlertEventProcessingPseudotimeRemote {

	/**
	 *
	 */
	private static final String protocol = "http";
	private static final String hostname = "localhost";
	private static final String port = "8080";
	private static final String containerName = "Open-Ticket_1.0.0-SNAPSHOT";

	private static final String PSEUDOTIME_STATEFUL_KIE_SESSION = "telecom-pseudotime-ksession";
	private static final String groupId = "com.telecom";
	private static final String artifactId = "Open-Ticket";
	private static final String version = "1.0.0-SNAPSHOT";

	private static AlertEventProcessingPseudotimeRemote cepService = null;

	// Drools Fusion Runtime Configuration
	private KieServices kServices;
	private KieContainer kContainer;
	private KieServicesClient ksClient;
	private KieCommands kCommands;
	private RuleServicesClient ruleClient;
	private ServiceResponse<ExecutionResults> executeResponse;

	// memory sizing and reporting activities
	static long totalFactCount = 0;

	// public static LinkListStack issues = new LinkListStack();

	public Issues issues = new Issues();

	public static AlertEventProcessingPseudotimeRemote getInstance() {

		if (cepService == null) {
			cepService = new AlertEventProcessingPseudotimeRemote();
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

		AlertEventProcessingPseudotimeRemote droolsCEPService = AlertEventProcessingPseudotimeRemote.getInstance();

		long t = 0L;
		long pt = t;

		AlertEvent alertEvent = new AlertEvent();
		alertEvent.set_time(t);
		System.out.println("_time: " + alertEvent.get_time());
		alertEvent.setNeName("bdhlmbch");
		alertEvent.setAlertGroup("RECT");
		alertEvent.setServerSerial(1738805008L);
		alertEvent.setSummary("BRANDON HILLS REMOTE RECTIFIER FAIL MJR - C3E35");
		alertEvent.setCircuit("RECT - power");
		alertEvent.setSeverity(4);
		alertEvent.setNcFunction("INSERT");
		droolsCEPService.execute(alertEvent, pt);

		pt = t;
		t = t + 1000L;

		alertEvent = new AlertEvent();
		alertEvent.set_time(t);
		System.out.println("_time: " + alertEvent.get_time());
		alertEvent.setNeName("bdhlmbch");
		alertEvent.setAlertGroup("AC");
		alertEvent.setServerSerial(1738805010L);
		alertEvent.setSummary("BRANDON HILLS REMOTE AC FAIL MJ - C3E38");
		alertEvent.setCircuit("AC - power");
		alertEvent.setSeverity(0);
		alertEvent.setNcFunction("INSERT");
		droolsCEPService.execute(alertEvent, pt);

		pt = t;
		t = t + 1000L;

		alertEvent = new AlertEvent();
		alertEvent.set_time(t);
		System.out.println("_time: " + alertEvent.get_time());
		alertEvent.setNeName("bdhlmbch");
		alertEvent.setAlertGroup("AC");
		alertEvent.setServerSerial(1738805010L);
		alertEvent.setSummary("BRANDON HILLS REMOTE AC FAIL MJ - C3E38");
		alertEvent.setCircuit("AC - power");
		alertEvent.setSeverity(0);
		alertEvent.setNcFunction("DELETE");
		droolsCEPService.execute(alertEvent, pt);

		while (true) {

			pt = t;
			t = t + 1000L;

			alertEvent = new AlertEvent();
			alertEvent.set_time(t);
			System.out.println("_time: " + alertEvent.get_time());
			alertEvent.setNeName("dummynename");
			alertEvent.setAlertGroup("WFOWF");
			alertEvent.setServerSerial(1738805008L);
			alertEvent.setSummary("DUMMY SUMMARY");
			alertEvent.setCircuit("DUMMY CIRCUIT");
			alertEvent.setSeverity(0);
			alertEvent.setNcFunction("XYZ");
			droolsCEPService.execute(alertEvent, pt);

		}

	}

	public void init() {

		try {

			System.out.println("==========================================================");
			System.out.println("INITIALIZING KIE RUNTIME FOR DROOLS FUSION");
			System.out.println("==========================================================");

			kServices = KieServices.Factory.get();
			kContainer = kServices.newKieContainer(kServices.newReleaseId(groupId, artifactId, version));

			Set<Class<?>> allClasses = new HashSet<Class<?>>();
			allClasses.add(AlertEvent.class);
			allClasses.add(Issue.class);
			allClasses.add(Issues.class);
			allClasses.add(TicketCreated.class);
			//allClasses.add(AdvanceSessionTimeCommand.class);
			String serverUrl = protocol + "://" + hostname + ":" + port + "/kie-server/services/rest/server";
			String username = "kieserver";
			String password = "kieserver1!";
			KieServicesConfiguration ksConfig = KieServicesFactory.newRestConfiguration(serverUrl, username, password);
			ksConfig.setMarshallingFormat(MarshallingFormat.JAXB);
			ksConfig.addExtraClasses(allClasses);

			ksClient = KieServicesFactory.newKieServicesClient(ksConfig);
			kCommands = KieServices.Factory.get().getCommands();

			List<Command> commandList = new ArrayList<Command>();
			issues.setList(new ArrayList<Issue>());
			issues.setLastIssueId(-1);
			commandList.add(kCommands.newSetGlobal("issues", issues, "issues"));
			BatchExecutionCommand batch = kCommands.newBatchExecution(commandList, PSEUDOTIME_STATEFUL_KIE_SESSION);
			ruleClient = ksClient.getServicesClient(RuleServicesClient.class);
			executeResponse = ruleClient.executeCommandsWithResults(containerName, batch);

			// kSession.setGlobal("issuesMap", new HashMap<Long,
			// AlertEventCorrelation>());
			System.out.println("initialized the kie runtime for drools fusion...");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static long prevTime = 0, currTime = 0;

	public void execute(AlertEvent event, Long previousTime) {

		// try {
		System.out.println(">>>>>> RECEIVED AN ALERT");

		// anything to with event object
		totalFactCount++;
		System.out.println(">>>>>> RUNNING THROUGH THE RULES..." + totalFactCount);
		List<Command> commandList = new ArrayList<Command>();
		commandList.add(kCommands.newAdvanceSessionTime(event.get_time().longValue() - previousTime.longValue(),
				TimeUnit.MILLISECONDS));
		commandList.add(kCommands.newInsert(event));
		commandList.add(kCommands.newFireAllRules());
		commandList.add(kCommands.newGetGlobal("issues"));
		BatchExecutionCommand batch = kCommands.newBatchExecution(commandList, PSEUDOTIME_STATEFUL_KIE_SESSION);
		// ruleClient = ksClient.getServicesClient(RuleServicesClient.class);
		executeResponse = ruleClient.executeCommandsWithResults(containerName, batch);

		// HashMap issueM = (HashMap) kSession.getGlobal("issueMap");

		// if (executeResponse.getResult() == null) {
		// System.out.println(">>>>>>>>>>>>> Response Result is null");
		// } else {
		if (executeResponse.getResult() != null) {
			Collection<String> identifiers = executeResponse.getResult().getIdentifiers();
			Iterator<String> i = identifiers.iterator();
			while (i.hasNext()) {
				String identifier = i.next();
				if (identifier.equals("issues")) {
					Issues issues = (Issues) executeResponse.getResult().getValue("issues");
					System.out.println(">>>>>>>>> Issues is there.");
					if (issues.getList() != null) {
						if (!issues.getList().isEmpty()) {
							Issue issue = issues.getList().get(issues.getLastIssueId());
							System.out.println(">>>>>>>> Found Issue " + issue.getIssueId());
							TicketCreated ticketWasCreated = new TicketCreated();
							ticketWasCreated.setIssueId(issue.getIssueId());
							commandList = new ArrayList<Command>();
							commandList.add(kCommands.newInsert(ticketWasCreated));
							commandList.add(kCommands.newFireAllRules());
							batch = kCommands.newBatchExecution(commandList, PSEUDOTIME_STATEFUL_KIE_SESSION);
							executeResponse = ruleClient.executeCommandsWithResults(containerName, batch);
						}
					}
				}
			}
		} else {
			System.out.println(">>>>>>>>>>>>>>>>> null result from rule execution.");
		}
		// }

		// LinkedList list = new LinkedList();
		// list.addAll(issueM.values());
		// issues.pushAll(list);
		System.out.println(">>>>>> FINISHED RUNNING THROUGH THE RULES");

	}

}
