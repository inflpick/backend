package com.leesh.inflpick.common;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class MockHttpServletRequestFactory {

    public static MockHttpServletRequest createMockHttpServletRequest(String apiPath, String httpMethod) {
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        mockHttpServletRequest.setRequestURI(apiPath);
        mockHttpServletRequest.setMethod(httpMethod);
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(mockHttpServletRequest));
        return mockHttpServletRequest;
    }

}
