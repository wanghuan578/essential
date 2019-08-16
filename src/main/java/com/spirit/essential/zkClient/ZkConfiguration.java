package com.spirit.essential.zkClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZkConfiguration {

    @Value("${zookeeper.baseSleepTimeMs}")
    private Integer baseSleepTimeMs;

    @Value("${zookeeper.maxRetries}")
    private Integer maxRetries;

    @Value("${zookeeper.sessionTimeoutMs}")
    private Integer sessionTimeoutMs;

    @Value("${zookeeper.connectionTimeoutMs}")
    private Integer connectionTimeoutMs;

    @Value("${zookeeper.namespace}")
    private String namespace;

    @Value("${zookeeper.digest}")
    private String digest;

    @Value("${zookeeper.server}")
    private String serverAddr;

    @Bean(initMethod = "init")
    public ZkClient zkClient() {
        ZkClient client = new ZkClient(serverAddr, baseSleepTimeMs, maxRetries,
                sessionTimeoutMs, connectionTimeoutMs,
                namespace, digest);
        return client;
    }

}
