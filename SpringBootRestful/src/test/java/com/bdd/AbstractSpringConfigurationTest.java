package com.bdd;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.SpringBootRestfulApplication;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest(classes = SpringBootRestfulApplication.class, webEnvironment = WebEnvironment.DEFINED_PORT)
@ContextConfiguration
@DirtiesContext
public abstract class AbstractSpringConfigurationTest {

	@Autowired(required = false)
	private TestRestTemplate testRestTemplate;
	
	protected RestTemplate restTemplate = new RestTemplate();
	protected String statusCode;
	
	protected ObjectMapper mapper = new ObjectMapper();
	protected static final String HOST = "localhost";
	protected static final String PORT = "8080";

	public TestRestTemplate getTestRestTemplate() {

		return testRestTemplate != null ? testRestTemplate : new TestRestTemplate();
	}

	public void setTestRestTemplate(TestRestTemplate testRestTemplate) {
		this.testRestTemplate = testRestTemplate;
	}

	public ResponseEntity<String> invokeRESTCall(String url, HttpMethod method, HttpEntity<?> requestEntity) {

		return getTestRestTemplate().exchange(url, method, requestEntity, String.class);
	}

	public HttpHeaders getDefaultHttpHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		return headers;
	}

	public String buildUrl(String host, String port, String path, Map<String, String> uriVariables,
			MultiValueMap<String, String> queryParams) {

		UriComponentsBuilder builder = UriComponentsBuilder.fromPath(path).host(host).port(port).scheme("http");
		if (queryParams != null && !queryParams.isEmpty())
			builder.queryParams(queryParams);
		UriComponents uriComponent = uriVariables != null && !uriVariables.isEmpty()
				? builder.buildAndExpand(uriVariables)
				: builder.build();

		return uriComponent.toUri().toString();
	}

	public String buildUrl(String host, String port, String path) {

		return buildUrl(host, port, path, null, null);
	}

	public String buildUrl(String host, String port, String path, Map<String, String> uriVariables) {

		return buildUrl(host, port, path, uriVariables, null);
	}
}
