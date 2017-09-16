package ua.nure.pashneva.SummaryTask4.web.filter;

import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EncodingFilterTest extends Mockito {

    @Test
    public void destroy() throws Exception {
        new EncodingFilter().destroy();
    }

    @Test
    public void doFilter() throws Exception {
        ServletRequest request = mock(HttpServletRequest.class);
        ServletResponse response = mock(HttpServletResponse.class);
        FilterChain chain = mock(FilterChain.class);
        new EncodingFilter().doFilter(request, response, chain);
        verify(chain, atLeast(1)).doFilter(request, response);
    }

    @Test
    public void init() throws Exception {
        FilterConfig fConfig = mock(FilterConfig.class);
        new EncodingFilter().init(fConfig);
        verify(fConfig, atLeast(1)).getInitParameter("encoding");
    }

}