package com.openhabbo.peer;

import com.openhabbo.config.OpenHabboPeerServiceConfiguration;
import com.openhabbo.config.OpenHabboServiceConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OpenHabboPeerService {
    private final Logger log = LogManager.getLogger(OpenHabboPeerService.class);

    private static OpenHabboPeerService peerServiceInstance;

    private String[] runtimeArguments;

    private OpenHabboServiceConfiguration serviceConfiguration;
    private OpenHabboPeerServiceConfiguration peerServiceConfiguration;

    public OpenHabboPeerService() {

    }

    public void initialize(final String[] runtimeArguments) {
        this.runtimeArguments = runtimeArguments;

        this.log.info("Initializing OpenHabbo Peer Service");

        // load configuration.
        this.serviceConfiguration = OpenHabboServiceConfiguration.loadConfiguration();
        this.peerServiceConfiguration = OpenHabboPeerServiceConfiguration.loadConfiguration();
    }

    public String[] getRuntimeArguments() {
        return runtimeArguments;
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
