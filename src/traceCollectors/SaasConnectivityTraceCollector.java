package traceCollectors;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import com.jezhumble.javasysmon.JavaSysMon;

public class SaasConnectivityTraceCollector {

	public static long connectionListCollector(int agentID, String userTag, Date requestTime) throws Exception
	{
		JavaSysMon monitor =   new JavaSysMon();
	    String osName =        monitor.osName();
	    String workingDirectory = new File("").getAbsolutePath();
	    int currentPID = monitor.currentPid() ;
	    System.out.println("OS name: " + osName + "  PID: " + currentPID + "  Working directory: " + workingDirectory);

		ProcessBuilder pb = new ProcessBuilder("/bin/bash",workingDirectory+ "/src/scriptParsers/testScript/lsofScript.sh",""+ 458);///home/xiang/workspaceEE/SaasWatch_SJSU/src/scriptParsers/testScript/lsofScript.sh
		Process p = pb.start();
		int rc = p.waitFor();
		System.out.println("Process ended with rc=" + rc);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
		StringBuilder sb = new StringBuilder();
		String line;
		SaasConnectivityTrace trace = new SaasConnectivityTrace(userTag,agentID,currentPID);
		while((line=br.readLine()) != null){
			Matcher matchedResult = RegexParsers.lsofParser(line);
			if(requestTime == null) {
				trace.traceTime = new Date();
			}
			else {
				trace.traceTime = requestTime;
			}
			if(matchedResult.find()) {
				trace.agentID = 1;
				trace.SaasFd = matchedResult.group(1);
				trace.SaasPID = Integer.parseInt(matchedResult.group(2));
				trace.traceType = matchedResult.group(3);
				SaasConnectionsTrace connection = new SaasConnectionsTrace(matchedResult.group(4),matchedResult.group(5),matchedResult.group(6),matchedResult.group(7));
				trace.connections.add(connection);
				sb.append(line).append("\n");
			}
		}
		
		Thread sendThread = new Thread(new TraceSender(trace));
		sendThread.start();
		System.out.println("Thread Started: " + Thread.currentThread().getName());
		if (sendThread.isAlive())
			return sendThread.getId();
		return 0;
	}	
	
}
