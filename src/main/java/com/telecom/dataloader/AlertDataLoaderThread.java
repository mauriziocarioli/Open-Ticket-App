package com.telecom.dataloader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.telecom.cep.AlertEvent;
import com.telecom.cep.AlertEventProcessingPseudotime;
import com.telecom.cep.LinkListStack;

public class AlertDataLoaderThread extends Thread {

	static final File file = new File("./input/0520_21_1300.csv");
	private static final char SEPARATOR = ',';

	public AlertDataLoaderThread() {
	}

	static synchronized AlertEvent getData(AlertEvent alertEvent, List<CSVRecord> rows, int r) {

		CSVRecord record = rows.get(r);
		alertEvent.set_time(Instant.parse(record.get(60).substring(0,19)+".0z").toEpochMilli());
		alertEvent.setAknowledged(record.get(1).equals("1"));
		alertEvent.setAgent(record.get(3));
		alertEvent.setAlertGroup(record.get(5));
		alertEvent.setAlertKey(record.get(6));
		alertEvent.setCircuit(record.get(12));
		alertEvent.setNeName(record.get(27));		
		alertEvent.setServerSerial(Long.parseLong(record.get(42)));
		alertEvent.setSeverity(Integer.parseInt(record.get(47)));
		alertEvent.setSeverityName(record.get(48));
		alertEvent.setSummary(record.get(50));
		alertEvent.setTtNumber(record.get(51));
		alertEvent.setNcFunction(record.get(66));

		return alertEvent;

	}

	public static LinkListStack alerts = new LinkListStack();

	public void run() {

		CSVParser parser;
		List<CSVRecord> rows;
		
		try {
			
			parser = new CSVParser(new FileReader(file), CSVFormat.DEFAULT.withDelimiter(SEPARATOR));
			rows = parser.getRecords();
			parser.close();
			
			AlertEventProcessingPseudotime droolsCEPService = AlertEventProcessingPseudotime.getInstance();
			
			while (true) {
				
				Long pt = new Long(0);
				
				for (int r = rows.size()-1; r > 0; r--) {

					AlertEvent alertEvent = new AlertEvent();
					alertEvent = getData(alertEvent, rows, r);
					/*
					System.out.println("record: "+r+" =====================================================");
					*/
					Long t = alertEvent.get_time();
					System.out.println("_time: "+alertEvent.get_time());
					/*
					System.out.println("acknowledged: "+alertEvent.getAknowledged());
					System.out.println("agent: "+alertEvent.getAgent());
					System.out.println("alert group: "+alertEvent.getAlertGroup());
					System.out.println("alert key: "+alertEvent.getAlertKey());
					System.out.println("circuit: "+alertEvent.getCircuit());
					System.out.println("ne name: "+alertEvent.getNeName());
					System.out.println("owner uid: "+alertEvent.getOwnerUid());
					System.out.println("server serial: "+alertEvent.getServerSerial());
					System.out.println("severity: "+alertEvent.getSeverity());
					System.out.println("severity name: "+alertEvent.getSeverityName());
					System.out.println("summary: "+alertEvent.getSummary());
					System.out.println("tt number: "+alertEvent.getTtNumber());
					System.out.println("nc function: "+alertEvent.getNcFunction());
					*/
					if (alertEvent.getNeName().equals("bdhlmbch")) 
						System.out.println("###### USE CASE #######");
					alerts.push(alertEvent);
					droolsCEPService.execute(alertEvent,pt);
					pt = t;

				}
				
				Thread.sleep(60000);
				
			}
			
		} catch (IOException | InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
}
