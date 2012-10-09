package scriptParsers;

import java.io.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;


public class ProcessbuilderForNgrep {
	public static void main(String[] args) throws Exception
	{
		List<String> command = new ArrayList<String>();
		command.add("/bin/sh");
		command.add("-c");
		command.add("lsof -p 512 |grep TCP");

		ProcessBuilder pb = new ProcessBuilder("/bin/bash","/home/xiang/workspaceEE/SaasWatch_SJSU/src/scriptParsers/testScript/NgrepScriptForLinux.sh");
		Process p;
		try {
			p = pb.start();
			String output = loadStream(p.getInputStream());
			String error  = loadStream(p.getErrorStream());
			int rc = p.waitFor();
			System.out.println("Process ended with rc=" + rc);
			System.out.println("\nStandard Output:\n");
			System.out.println(output);
			System.out.println("\nStandard Error:\n");
			System.out.println(error);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	private static String loadStream(InputStream s) throws Exception
	{
		//List<SaasConnectivityTrace> tracesArray = new ArrayList<SaasConnectivityTrace>();
		BufferedReader br = new BufferedReader(new InputStreamReader(s));
		StringBuilder sb = new StringBuilder();
		String line;
		//SaasConnectivityTrace trace = new SaasConnectivityTrace();
		while((line=br.readLine()) != null){
//			Matcher matchedResult = RegexParsers.test(line);
//			if(trace.traceTime == null) {
//				trace.traceTime = new Date();
//				trace.agentID = 1;
//				trace.SaasFd = matchedResult.group(1);
//				trace.SaasPID = Integer.parseInt(matchedResult.group(2));
//				trace.traceType = matchedResult.group(3);
//			}
//			SaasConnectionsTrace connection = new SaasConnectionsTrace(matchedResult.group(4),matchedResult.group(5),matchedResult.group(6),matchedResult.group(7));
//			trace.connections.add(connection);
			if((line.toString()) != null & line.toString().indexOf('#') != -1) {
				sb.append(line).append("\n");
			}
			else {
				sb.append(line);
			}
//			System.out.println("Matched: "+ connection.connectionType);
		}
		return sb.toString();
	}	
}

