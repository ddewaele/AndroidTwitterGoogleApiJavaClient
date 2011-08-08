package com.ecs.sample.util;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class QueryStringParser {
    private final String queryString;
    
    private int paramBegin;
    private int paramEnd = -1;
    private int paramNameEnd;
    private String paramName;
    private String paramValue;

    private Map<String, String> paramPairs = new HashMap<String, String>();
    
    public QueryStringParser(String queryString) {
        this.queryString = queryString;
        while(next()) {
        	paramPairs.put(getName(),getValue());
        }
    }

    public String getQueryParamValue(String name) {
    	return paramPairs.get(name);
    }
    
    private boolean next() {
        int len = queryString.length();
        while (true) {
            if (paramEnd == len) {
                return false;
            }
            paramBegin = paramEnd == -1 ? 0 : paramEnd+1;
            int idx = queryString.indexOf('&', paramBegin);
            paramEnd = idx == -1 ? len : idx;
            if (paramEnd > paramBegin) {
                idx = queryString.indexOf('=', paramBegin);
                paramNameEnd = idx == -1 || idx > paramEnd ? paramEnd : idx;
                paramName = null;
                paramValue = null;
                return true;
            }
        }
    }
    
    private String getName() {
        if (paramName == null) {
            paramName = queryString.substring(paramBegin, paramNameEnd);
        }
        return paramName;
    }
    
    private String getValue() {
        if (paramValue == null) {
            if (paramNameEnd == paramEnd) {
                return null;
            }
            try {
                paramValue = URLDecoder.decode(queryString.substring(paramNameEnd+1, paramEnd));
            } catch (Exception ex) {
                throw new Error(ex);
            }
        }
        return paramValue;
    }
}