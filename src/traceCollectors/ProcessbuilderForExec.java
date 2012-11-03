package traceCollectors;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import com.jezhumble.javasysmon.JavaSysMon;
import com.jezhumble.javasysmon.ProcessInfo;

public class ProcessbuilderForExec {
	public static void main(String[] args) throws Exception
	{
		JavaSysMon monitor =   new JavaSysMon();
	    String osName =        monitor.osName();
	    String workingDirectory = new File("").getAbsolutePath();
	    int currentPID = monitor.currentPid() ;
	    		
	    System.out.println("OS name: " + osName + ". PID: " + currentPID + "Working directory: " + workingDirectory);
		List<String> command = new ArrayList<String>();
		//command.add("lsof");
		//command.add("-i");
		//command.add("TCP");
		//command.add("-p");
		//command.add("512");
		command.add("/bin/sh");
		command.add("-c");
		command.add("lsof -p "+ currentPID +" |grep TCP");

		ProcessBuilder pb = new ProcessBuilder("/bin/bash",workingDirectory+ "/src/scriptParsers/testScript/lsofScript.sh",""+ 323);///home/xiang/workspaceEE/SaasWatch_SJSU/src/scriptParsers/testScript/lsofScript.sh
		Process p = pb.start();
		String output = loadStream(p.getInputStream());
		String error  = loadStream(p.getErrorStream());
		int rc = p.waitFor();
		System.out.println("Process ended with rc=" + rc);
		System.out.println("\nStandard Output:\n");
		System.out.println(output);
		System.out.println("\nStandard Error:\n");
		System.out.println(error);
	}

	private static String loadStream(InputStream s) throws Exception
	{
		//List<SaasConnectivityTrace> tracesArray = new ArrayList<SaasConnectivityTrace>();
		BufferedReader br = new BufferedReader(new InputStreamReader(s));
		StringBuilder sb = new StringBuilder();
		String line;
		SaasConnectivityTrace trace = new SaasConnectivityTrace("Test",1,1);
		while((line=br.readLine()) != null){
			Matcher matchedResult = RegexParsers.lsofParser(line);
			if(trace.traceTime == null) {
				trace.traceTime = new Date();
			}
			if(matchedResult.find()) {
				trace.agentID = 1;
				trace.SaasFd = matchedResult.group(1);
				trace.SaasPID = Integer.parseInt(matchedResult.group(2));
				trace.traceType = matchedResult.group(3);
				SaasConnectionsTrace connection = new SaasConnectionsTrace(matchedResult.group(4),matchedResult.group(5),matchedResult.group(6),matchedResult.group(7));
				System.out.println(connection);
				trace.connections.add(connection);
				sb.append(line).append("\n");
				System.out.println("Matched: "+ connection.connectionType);
			}
		}
		return sb.toString();
	}	
}
