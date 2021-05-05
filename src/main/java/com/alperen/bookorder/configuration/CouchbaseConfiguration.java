package com.alperen.bookorder.configuration;

import com.alperen.bookorder.model.Customer;
import com.alperen.bookorder.model.Order;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.env.CouchbaseEnvironment;
import com.couchbase.client.java.env.DefaultCouchbaseEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.config.BeanNames;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.data.couchbase.core.WriteResultChecking;
import org.springframework.data.couchbase.repository.auditing.EnableCouchbaseAuditing;
import org.springframework.data.couchbase.repository.config.RepositoryOperationsMapping;

import java.util.List;

@Configuration
@EnableCouchbaseAuditing
public class CouchbaseConfiguration extends AbstractCouchbaseConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(CouchbaseConfiguration.class);

    private final CouchbaseProperties couchbaseProperties;

    public CouchbaseConfiguration(CouchbaseProperties couchbaseProperties) {
        this.couchbaseProperties = couchbaseProperties;
    }

    @Override
    @Bean(name = BeanNames.COUCHBASE_TEMPLATE)
    public CouchbaseTemplate couchbaseTemplate() throws Exception {
        CouchbaseTemplate couchbaseTemplate = super.couchbaseTemplate();
        couchbaseTemplate.setWriteResultChecking(WriteResultChecking.EXCEPTION);
        return couchbaseTemplate;
    }

    @Override
    protected List<String> getBootstrapHosts() {
        return couchbaseProperties.getBootstrapHosts();
    }

    @Override
    protected String getBucketName() {
        return couchbaseProperties.getBucket().getName();
    }

    @Override
    protected String getBucketPassword() {
        return couchbaseProperties.getBucket().getPassword();
    }

    @Override
    protected CouchbaseEnvironment getEnvironment() {
        return DefaultCouchbaseEnvironment.builder()
                .connectTimeout(couchbaseProperties.getEnv().getTimeouts().getConnect().toMillis())
                .build();
    }

    private Bucket customersBucket() throws Exception {
        return couchbaseCluster().openBucket("customers", "customers");
    }

    private Bucket ordersBucket() throws Exception {
        return couchbaseCluster().openBucket("orders", "orders");
    }

    @Bean
    public CouchbaseTemplate ordersTemplate() throws Exception {
        CouchbaseTemplate template = new CouchbaseTemplate(
                couchbaseClusterInfo(), ordersBucket(),
                mappingCouchbaseConverter(), translationService());
        template.setDefaultConsistency(getDefaultConsistency());
        return template;
    }

    @Bean
    public CouchbaseTemplate customersTemplate() throws Exception {
        CouchbaseTemplate template = new CouchbaseTemplate(
                couchbaseClusterInfo(), customersBucket(),
                mappingCouchbaseConverter(), translationService());
        template.setDefaultConsistency(getDefaultConsistency());
        return template;
    }

    @Override
    public void configureRepositoryOperationsMapping(RepositoryOperationsMapping baseMapping) {
        try {
            baseMapping.mapEntity(Order.class, ordersTemplate());
            baseMapping.mapEntity(Customer.class, customersTemplate());
        } catch (Exception e) {
            LOGGER.error("Error creating mapping for {0} bucket", e);
        }
    }

}
