

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class Main {
	public static HashMap<Integer, ServiceCall> serviceCalls = new HashMap<Integer, ServiceCall>();
	public static HashMap<String, Result> results = new HashMap<String, Result>();
	public static File logfile;

	public static void main(String args[]) {
		Scanner scanner = null;

		System.out.println("Opening log File...");
		try {
			logfile = new File("/usr/src/qa_case_study/test.log");
			scanner = new Scanner(logfile);

		} catch (FileNotFoundException e) {
			System.out.println("Error reading file.");
			e.printStackTrace();
			System.exit(1);
		}
		System.out.println("Parsing log File...");
		while (scanner.hasNextLine()) {
			PrasedLogLine logLine = null;
			try {
				logLine = new PrasedLogLine(scanner.nextLine().toString().trim());
			} catch (Exception e) {

				e.printStackTrace();
				return;
			}
			if (serviceCalls.containsKey(logLine.serviceId) && logLine.type.matches("exit")) {
				serviceCalls.get(logLine.serviceId).SetExitTime(logLine.time);
			} else {
				serviceCalls.put(logLine.serviceId,
						new ServiceCall(logLine.serviceId, logLine.serviceName, logLine.time));
			}
		}
		scanner.close();
		System.out.println("Log file parsed.");
		ProcessServiceCalls();
		PrintResults();
	}

	public static PrasedLogLine parseLine(String logLine) {
		return null;

	}

	public static void ProcessServiceCalls() {
		Iterator iter = serviceCalls.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			ServiceCall serviceCall = (ServiceCall) entry.getValue();
			if (results.containsKey(serviceCall.serviceName)) {
				results.get(serviceCall.serviceName).incrementRequest(serviceCall.getTimeDetla());
			} else {
				results.put(serviceCall.serviceName, new Result(serviceCall));
			}
		}
	}

	public static void PrintResults() {
		Iterator iter = results.entrySet().iterator();
		System.out.println("  Name of Service \t| Amount of request to service \t| Max time of request execution");
		System.out.println("========================================================================================");
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			Result result = (Result) entry.getValue();
			String maxTime = (new SimpleDateFormat("hh:mm:ss,SSS")).format(new Date(result.maxTime));
			System.out.format("%17s\t| %28d\t| %29s\n", result.serviceName, result.numOfRequests, maxTime);
		}
	}
}