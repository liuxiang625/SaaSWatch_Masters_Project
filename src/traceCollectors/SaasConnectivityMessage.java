package traceCollectors;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SaasConnectivityMessage implements Serializable {
	public String messageBody;
	public int agentID;
	public int SaasPID;
	public String userTag;
	
	public SaasConnectivityMessage(String userTag, int agentID, int SaasPID) {
		//this.messages = new ArrayList<SaasConnectivityMessage>();
		this.userTag = userTag;
		this.agentID = agentID;
		this.SaasPID = SaasPID;
	}
	}
