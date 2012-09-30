package scriptParsers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SaasConnectivityTrace {
	public String userTag;
	public String traceType;
	public String SaasFd;
	public Date traceTime;
	public int agentID;
	public int SaasPID;
	public List<SaasConnectivityMessage> messages;
	public List<SaasConnectionsTrace> connections;
	
	public SaasConnectivityTrace() {
		this.messages = new ArrayList<SaasConnectivityMessage>();
		this.connections = new ArrayList<SaasConnectionsTrace>();
	}
}
