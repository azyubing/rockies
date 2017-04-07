package com.rockies.webservice.interceptors;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;


public class LoggingInterceptor implements ClientHttpRequestInterceptor
{
	final static Logger LOG = LoggerFactory.getLogger(LoggingInterceptor.class);

	@Override
	public ClientHttpResponse intercept(final HttpRequest request, final byte[] body, final ClientHttpRequestExecution execution)
			throws IOException
	{
		traceRequest(request, body);
		final ClientHttpResponse response = execution.execute(request, body);
		traceResponse(response);
		return response;
	}

	private void traceRequest(final HttpRequest request, final byte[] body) throws IOException
	{
		LOG.debug("===========================request begin================================================");
		LOG.debug("URI         : {}", request.getURI());
		LOG.debug("Method      : {}", request.getMethod());
		LOG.debug("Headers     : {}", request.getHeaders());
		LOG.debug("Request body: {}", new String(body, "UTF-8"));
		LOG.debug("==========================request end================================================");
	}

	private void traceResponse(final ClientHttpResponse response) throws IOException
	{
		LOG.debug("============================response begin==========================================");
		LOG.debug("Status code  : {}", response.getStatusCode());
		LOG.debug("Status text  : {}", response.getStatusText());
		LOG.debug("Headers      : {}", response.getHeaders());
		LOG.debug("Response body: {}", IOUtils.toString(response.getBody()));
		LOG.debug("=======================response end=================================================");
	}
}
