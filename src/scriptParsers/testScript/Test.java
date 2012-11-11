package scriptParsers.testScript;

import java.util.Date;

import saasWatchAgent.TraceReceiver;
import traceCollectors.ConnectivityTraceCollector;
import traceCollectors.ConnectionsWatcher;

public class Test {
	public static void main(String[] args) throws Exception {
		System.out.println(ConnectivityTraceCollector.getCollectorThreadId());
		System.out.println(ConnectivityTraceCollector.getCollectorThreadId());
	}
}
