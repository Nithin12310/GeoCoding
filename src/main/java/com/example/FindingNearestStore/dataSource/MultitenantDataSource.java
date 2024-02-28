package com.example.FindingNearestStore.dataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class MultitenantDataSource extends AbstractRoutingDataSource {
    @Override
    public String determineCurrentLookupKey() {
        return TenantContext.getCurrentTenant();
    }
}
