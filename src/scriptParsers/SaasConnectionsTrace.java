package scriptParsers;

public class SaasConnectionsTrace {
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
