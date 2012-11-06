package traceCollectors;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.net.*;
import java.util.*;
import static java.lang.System.out;

import com.jezhumble.javasysmon.JavaSysMon;


public class ProcessbuilderForNgrep {

	public static void main(String[] args) throws Exception, SocketException
	{
		String primaryInterfaceName;
		Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
		for (NetworkInterface netint : Collections.list(nets)) {
			primaryInterfaceName = getPrimaryInternetInterName(netint);
			out.printf("Primary Newwork interface: %s\n", primaryInterfaceName);
			if(primaryInterfaceName != null) break;
		}

		JavaSysMon monitor =   new JavaSysMon();
		String osName =        monitor.osName();
		String workingDirectory = new File("").getAbsolutePath();
		int currentPID = monitor.currentPid() ;
		System.out.println("OS name: " + osName + "  PID: " + currentPID + "  Working directory: " + workingDirectory);

		//		List<String> command = new ArrayList<String>();
		//		command.add("/bin/sh");
		//		command.add("-c");
		//		command.add("lsof -p 512 |grep TCP");
		//
		ProcessBuilder pb = new ProcessBuilder("/bin/bash",workingDirectory+ "/src/scriptParsers/testScript/NgrepScriptForLinux.sh");
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

	static String getPrimaryInternetInterName(NetworkInterface netint) throws SocketException {

		try
		{	
			InetAddress thisIp = InetAddress.getLocalHost();
			String thisIpAddress = thisIp.getHostAddress().toString();
			//out.printf("Public Ip: %s\n", thisIpAddress);

			if(netint.isUp() & !netint.isLoopback() & !netint.isVirtual() & !netint.isPointToPoint() ) {
				//out.printf("Display name: %s\n", netint.getDisplayName());
				//out.printf("Name: %s\n", netint.getName());
				//byte[] macArray = netint.getHardwareAddress();
				//out.printf("MAC address: ");
				//		        for (int k=0; k < macArray.length; k++) {
				//		        	out.printf("%s", netint.getHardwareAddress()[k]);
				//		        }
				Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
				for (InetAddress inetAddress : Collections.list(inetAddresses)) {
					//out.printf("\nInetAddress: %s\n", inetAddress.toString());
					if(inetAddress.toString().indexOf(thisIpAddress) != -1) {
						return netint.getDisplayName();
					}
				}
				//out.printf("\n");
			}
		}
		catch(Exception e){}
		return null;
	}
	public static SaasConnectivityMessage messageCollector(int agentID, String userTag, Date requestTime) throws Exception {
		
		List<SaasConnectivityMessage> messagesArray = new ArrayList<SaasConnectivityMessage>();
		String primaryInterfaceName;
		Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
		for (NetworkInterface netint : Collections.list(nets)) {
			primaryInterfaceName = getPrimaryInternetInterName(netint);
			out.printf("Primary Newwork interface: %s\n", primaryInterfaceName);
			if(primaryInterfaceName != null) break;
		}

		JavaSysMon monitor =   new JavaSysMon();
		String osName =        monitor.osName();
		String workingDirectory = new File("").getAbsolutePath();
		int currentPID = monitor.currentPid() ;
		System.out.println("OS name: " + osName + "  PID: " + currentPID + "  Working directory: " + workingDirectory);

		ProcessBuilder pb = new ProcessBuilder("/bin/bash",workingDirectory+ "/src/scriptParsers/testScript/NgrepScriptForLinux.sh");
		Process p;
		p = pb.start();
		BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
		StringBuilder sb = new StringBuilder();
		String line;
		SaasConnectivityMessage trace = new SaasConnectivityMessage(userTag,agentID,currentPID);
		while((line=br.readLine()) != null){
			if((line.toString()) != null & line.toString().indexOf('#') != -1) {
				sb.append(line).append("\n");
			}
			else {
				sb.append(line);
			}
		}
		
		return null;

	}
}


