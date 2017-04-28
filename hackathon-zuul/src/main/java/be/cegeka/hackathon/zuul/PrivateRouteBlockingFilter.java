package be.cegeka.hackathon.zuul;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.cloud.netflix.zuul.util.ZuulRuntimeException;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

public class PrivateRouteBlockingFilter extends ZuulFilter {
    private static final String METADATA_KEY_PRIVATE = "private";

    private final DiscoveryClient discoveryClient;
    private final RouteLocator routeLocator;

    PrivateRouteBlockingFilter(DiscoveryClient discoveryClient, RouteLocator routeLocator) {
        this.discoveryClient = discoveryClient;
        this.routeLocator = routeLocator;
    }

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        Route route = findMatchingRoute();
        if (route == null) {
            return null;
        }

        ServiceInstance serviceInstance = serviceInstance(route);
        if (serviceInstance == null) {
            return null;
        }

        Map<String, String> metadata = serviceInstance.getMetadata();

        if (Boolean.valueOf(metadata.getOrDefault(METADATA_KEY_PRIVATE, Boolean.FALSE.toString()))) {
            throw new ZuulRuntimeException(new ZuulException("Forbidden route", HttpStatus.NOT_FOUND.value(), "Not found"));
        }

        return null;
    }

    private ServiceInstance serviceInstance(Route route) {
        List<ServiceInstance> instances = discoveryClient.getInstances(route.getId());
        if (instances.isEmpty()) {
            return null;
        }

        return instances.get(0);
    }

    private Route findMatchingRoute() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        String requestURI = currentContext.getRequest().getRequestURI();

        return routeLocator.getMatchingRoute(requestURI);
    }
}
