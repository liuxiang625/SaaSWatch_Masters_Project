package traceCollectors;

import static java.lang.System.out;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;

import com.jezhumble.javasysmon.JavaSysMon;

public class MetricRecorder {
	private static JavaSysMon monitor =   new JavaSysMon();
	private static String osName =        monitor.osName();
	private static String workingDirectory = new File("").getAbsolutePath();
	private static int currentPID = monitor.currentPid() ;
	private static String connectionsCollector = workingDirectory+ "/src/scriptParsers/testScript/lsofScript.sh " + 312; 
	private static String messageCollector = workingDirectory+ "/src/scriptParsers/testScript/NgrepScriptForLinux.sh";
	public static void main(String[] args) throws Exception {
		//String[] stringArray = getProcessString(connectionsCollector).split("  ");
		String messages = getProcessString(messageCollector + " " + getPrimaryInternetInterName() + " " + 80);
		System.out.println(messages);
	}
	
//	public static void getMessageMetric() throws Exception, SocketException {
//		String primaryInterfaceName;
//		Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
//		for (NetworkInterface netint : Collections.list(nets)) {
//			primaryInterfaceName = getPrimaryInternetInterName(netint);
//			out.printf("Primary Newwork interface: %s\n", primaryInterfaceName);
//			if(primaryInterfaceName != null) break;
//		}
//		
//	}
	
	static String getPrimaryInternetInterName() throws Exception, SocketException {
		String primaryInterfaceName = null;
		Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
		InetAddress thisIp = InetAddress.getLocalHost();
		String thisIpAddress = thisIp.getHostAddress().toString();
		
		for (NetworkInterface netint : Collections.list(nets)) {
			if(netint.isUp() & !netint.isLoopback() & !netint.isVirtual() & !netint.isPointToPoint() ) {
				Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
				for (InetAddress inetAddress : Collections.list(inetAddresses)) {
					if(inetAddress.toString().indexOf(thisIpAddress) != -1) {
						primaryInterfaceName =  netint.getDisplayName();
					}
				}
			}
			if(primaryInterfaceName != null) break;
		}
		return primaryInterfaceName;
	}
	
	public static String getProcessString(String processString) {
	       Process p = null;
	       BufferedReader br = null;
	       String retVal = null;
	        try {
	            ProcessBuilder pb = new ProcessBuilder("/bin/bash", processString);
	            pb.redirectErrorStream(true);
	            p = pb.start();
	            br = new BufferedReader(new InputStreamReader(p.getInputStream()));
	            //ArrayList<String> lines = new ArrayList<String>();
	            String line;
	            while ((line = br.readLine()) != null) {
	                //lines.add(line);
	            	//System.out.println(line);
	                retVal += line + ", ";
	            }
	            //retVal = lines.get(0);
	        }
	        catch (Exception x) {
	            x.printStackTrace();
	        }
	        finally {
	            if (br != null) {try {br.close();} catch (IOException ignoreMe) {}}
	            if (p != null) {p.destroy();}
	        }
			//System.out.println(retVal);
	        return retVal;
	}
}

