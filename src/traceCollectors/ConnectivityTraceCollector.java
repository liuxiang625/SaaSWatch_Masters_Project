package traceCollectors;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.Date;

import saasWatchAgent.TraceReceiver;

public class ConnectivityTraceCollector {
	ThreadGroup rootThreadGroup = null;
	static long collectorThreadId;
	public static void main(String[] args) throws Exception {
		thread(new TraceReceiver(), false);
		collectorThreadId = SaasConnectivityTraceCollector. connectionListCollector(1,"Test",new Date());
		System.out.println(collectorThreadId);
	}
	
	public static long getCollectorThreadId() {
		return collectorThreadId;
	}
	
	private static void thread(Runnable runnable, boolean daemon) {
		System.out.println("Thread Started: " + Thread.currentThread().getName());
        Thread brokerThread = new Thread(runnable);
        brokerThread.setDaemon(daemon);
        brokerThread.start();
        
    }
	
	 
	private ThreadGroup getRootThreadGroup( ) {
	    if ( rootThreadGroup != null )
	        return rootThreadGroup;
	    ThreadGroup tg = Thread.currentThread( ).getThreadGroup( );
	    ThreadGroup ptg;
	    while ( (ptg = tg.getParent( )) != null )
	        tg = ptg;
	    return tg;
	}
	
	private Thread[] getAllThreads( ) {
	    final ThreadGroup root = getRootThreadGroup( );
	    final ThreadMXBean thbean = ManagementFactory.getThreadMXBean( );
	    int nAlloc = thbean.getThreadCount( );
	    int n = 0;
	    Thread[] threads;
	    do {
	        nAlloc *= 2;
	        threads = new Thread[ nAlloc ];
	        n = root.enumerate( threads, true );
	    } while ( n == nAlloc );
	    return java.util.Arrays.copyOf( threads, n );
	}
	
	private Thread getThread( final long id ) {
	    final Thread[] threads = getAllThreads( );
	    for ( Thread thread : threads )
	        if ( thread.getId( ) == id )
	            return thread;
	    return null;
	}
}
