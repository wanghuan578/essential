package com.spirit.essential.common.web.response.pojo;

import com.spirit.essential.rpc.protocol.thrift.RouteInfo;

import java.util.List;

public class ApplicationInfo {

    private String applicationName;
    private List<RouteInfo> routeInfoList;

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public List<RouteInfo> getRouteInfoList() {
        return routeInfoList;
    }

    public void setRouteInfoList(List<RouteInfo> routeInfoList) {
        this.routeInfoList = routeInfoList;
    }
}
