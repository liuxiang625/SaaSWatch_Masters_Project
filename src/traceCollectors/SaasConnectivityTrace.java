package traceCollectors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class SaasConnectivityTrace implements Serializable {
	public String userTag;
	public String traceType;
	public String SaasFd;
	public Date traceTime;
	public int agentID;
	public int SaasPID;
	public List<SaasConnectivityMessage> messages;
	public List<SaasConnectionsTrace> connections;
	
	public SaasConnectivityTrace(String userTag, int agentID, int SaasPID) {
		//this.messages = new ArrayList<SaasConnectivityMessage>();
		this.connections = new ArrayList<SaasConnectionsTrace>();
		this.userTag = userTag;
		this.agentID = agentID;
		this.SaasPID = SaasPID;
	}
}
