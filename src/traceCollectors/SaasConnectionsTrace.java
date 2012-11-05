package traceCollectors;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SaasConnectionsTrace implements Serializable {
		public String connectionType;
		public String connectionSource;
		public String connectionDestination;
		public String conenctionState;
		public SaasConnectionsTrace(String source, String dest, String type, String state) {
			this.connectionType = type;
			this.connectionSource = source;
			this.connectionDestination = dest;
			this.conenctionState = state;
		}
}
