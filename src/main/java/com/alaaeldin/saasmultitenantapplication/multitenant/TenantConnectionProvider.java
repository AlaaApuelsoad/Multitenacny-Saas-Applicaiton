
package com.alaaeldin.saasmultitenantapplication.multitenant;

import lombok.RequiredArgsConstructor;
import org.hibernate.cfg.MultiTenancySettings;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.hibernate.autoconfigure.HibernatePropertiesCustomizer;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class TenantConnectionProvider implements MultiTenantConnectionProvider<String>, HibernatePropertiesCustomizer {

    private final DataSource dataSource;
    private static final String DEFAULT_TENANT_ID = "tenant_1";
    private static final Logger log = LoggerFactory.getLogger(TenantConnectionProvider.class);

    /**
     * Gives Hibernate a connection for a specific tenant's database schema.
     */
    @Override
    public Connection getConnection(String tenantIdentifier) throws SQLException {
        Connection connection = dataSource.getConnection();

        try {
            connection.setSchema(tenantIdentifier);
            log.info("TenantConnectionProvider: selected schema = {}", connection.getSchema());
            return connection;
        } catch (SQLException e) {
            connection.close();
            throw e;
        }
    }

    @Override
    public void releaseConnection(String tenantIdentifier, Connection connection) throws SQLException {
        try (connection) {
            connection.setSchema("public");
        }
        connection.close();
    }

    /**
     * Gives Hibernate a connection when no specific tenant has been selected,
     * such as during bootstrap or metadata access.
     */
    @Override
    public Connection getAnyConnection() throws SQLException {
        log.info("TenantConnectionProvider: getAnyConnection() called");
        return getConnection(DEFAULT_TENANT_ID);
    }

    @Override
    public void releaseAnyConnection(Connection connection) throws SQLException {
        connection.close();
    }

    @Override
    public boolean supportsAggressiveRelease() {
        return false;
    }

    @Override
    public void customize(Map<String, Object> hibernateProperties) {
        hibernateProperties.put(MultiTenancySettings.MULTI_TENANT_CONNECTION_PROVIDER, this);
    }


    @Override
    public boolean isUnwrappableAs(@NonNull Class< ?> unwrapType) {
        return false;
    }

    @Override
    public <T> T unwrap(Class<T> unwrapType) {
        if (unwrapType.isInstance(this)) {
            return unwrapType.cast(this);
        }
        throw new IllegalArgumentException("Cannot unwrap " + getClass().getName() + " to " + unwrapType.getName());
    }

}
