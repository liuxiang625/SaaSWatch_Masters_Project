package traceCollectors;

import saasWatchAgent.TraceReceiver;

public class ConnectivityTraceCollector {
	public static void main(String[] args) throws Exception {
		SaasConnectivityTrace traceMsg = new SaasConnectivityTrace("test msg",1,1);	
		//thread(new TraceSender(traceMsg), false);
        //thread(new TraceReceiver(), false);
//        Thread sendThread = new Thread(new TraceSender(traceMsg));
//        sendThread.start();
		thread(new TraceSender(traceMsg), false);
		thread(new TraceSender(traceMsg), false);
		thread(new TraceSender(traceMsg), false);
		thread(new TraceSender(traceMsg), false);
        Thread recvThread = new Thread(new TraceReceiver());
        recvThread.start();
	}
	public static void thread(Runnable runnable, boolean daemon) {
		System.out.println("Thread Started" + Thread.currentThread().getName());
        Thread brokerThread = new Thread(runnable);
        brokerThread.setDaemon(daemon);
        brokerThread.start();
        
    }

}
