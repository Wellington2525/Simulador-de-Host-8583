package org.jpos;

import org.jpos.IsoServer;
import org.jpos.RPCAdapterServer;


public class NonBatchEnabler {

	
	public static void main(String[] args) {
		RPCAdapterServer rpc = new RPCAdapterServer();
		rpc.main(args);
		HttpAdapterServer http = new HttpAdapterServer();
		http.main(args);
		IsoServer iso = new IsoServer();
		iso.main(args);
	}

}
