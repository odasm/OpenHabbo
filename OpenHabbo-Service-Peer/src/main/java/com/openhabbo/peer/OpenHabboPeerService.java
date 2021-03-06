package com.openhabbo.peer;

import com.openhabbo.api.services.OpenHabboService;
import com.openhabbo.commons.web.WebClient;
import com.openhabbo.communication.services.ServiceCommunicationManager;
import com.openhabbo.config.OpenHabboPeerServiceConfiguration;
import com.openhabbo.config.OpenHabboServiceConfiguration;
import com.openhabbo.peer.networking.GameNetworkService;
import com.openhabbo.peer.web.PeerWebService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class OpenHabboPeerService {
    private final Logger log = LogManager.getLogger(OpenHabboPeerService.class);

    private static OpenHabboPeerService peerServiceInstance;

    private String[] runtimeArguments;

    private OpenHabboServiceConfiguration serviceConfiguration;
    private OpenHabboPeerServiceConfiguration peerServiceConfiguration;

    private GameNetworkService gameNetworkService;

    private String serviceAlias = "peerservice-1";

    public OpenHabboPeerService() {
    }

    public void initialize(final String[] runtimeArguments) {
        this.runtimeArguments = runtimeArguments;

        if (this.runtimeArguments.length != 0) {
            this.serviceAlias = this.runtimeArguments[0].replace("--alias:", "");
        }

        OpenHabboService.SERVICE_ALIAS = this.serviceAlias;
        OpenHabboService.SERVICE_TYPE = "peer";

        this.log.info("Initializing OpenHabbo Peer Service ({})", this.serviceAlias);

        // load configuration.
        this.serviceConfiguration = OpenHabboServiceConfiguration.loadConfiguration();
        this.peerServiceConfiguration = OpenHabboPeerServiceConfiguration.loadConfiguration();

        // initialize game network service
        this.log.info("Initializing game network service");
        this.gameNetworkService = new GameNetworkService(this.peerServiceConfiguration);
        this.gameNetworkService.initialize();

        // initialize web service
        this.log.info("Initializing web service");
        WebClient.getInstance().initialize(this.serviceConfiguration);
        PeerWebService.getInstance().initialize();

        // initialize master service connection
        log.info("Initializing master service connection");
        ServiceCommunicationManager.getInstance().initialize();
    }

    public OpenHabboServiceConfiguration getServiceConfiguration() {
        return serviceConfiguration;
    }

    public OpenHabboPeerServiceConfiguration getPeerServiceConfiguration() {
        return peerServiceConfiguration;
    }

    public static void main(String[] args) {
        OpenHabboPeerService.getInstance().initialize(args);
    }

    public static OpenHabboPeerService getInstance() {
        if (peerServiceInstance == null) {
            peerServiceInstance = new OpenHabboPeerService();
        }

        return peerServiceInstance;
    }
}
