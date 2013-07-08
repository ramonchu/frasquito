/**
 *
 */
package com.rarnau.fastquickproto.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Ramón Arnau Gómez, 2013
 *
 */
public class UrlRewriteFilter implements Filter {

	private static final String[] staticPrefixResources = new String[] { "/css", "/img", "/js", "/html", "/app" };
	private static final String[] staticPostfixResources = new String[] { ".jsp" };

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String uri = StringUtils.removeStart(httpRequest.getRequestURI(), httpRequest.getContextPath());
		if (StringUtils.startsWithAny(uri, staticPrefixResources) || StringUtils.endsWithAny(uri, staticPostfixResources)) {
			chain.doFilter(httpRequest, response);
		} else {
			request.getRequestDispatcher("/app" + uri).forward(httpRequest, response);
		}
	}
}
