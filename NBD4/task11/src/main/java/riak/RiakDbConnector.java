package riak;

import com.basho.riak.client.core.RiakCluster;
import com.basho.riak.client.core.RiakNode;

public class RiakDbConnector {

    private static final String REMOTE_ADDRESS = "127.0.0.1";
    private static final int PORT = 8087;

    public RiakCluster setUpCluster() {
        RiakNode node = new RiakNode.Builder()
                .withRemoteAddress(REMOTE_ADDRESS)
                .withRemotePort(PORT)
                .build();

        RiakCluster cluster = new RiakCluster.Builder(node)
                .build();
        cluster.start();

        return cluster;
    }

}
