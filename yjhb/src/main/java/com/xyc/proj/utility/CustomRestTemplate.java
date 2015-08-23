/**
 * All rights, including trade secret rights, reserved.
 */
package com.xyc.proj.utility;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomRestTemplate extends RestTemplate {
	public CustomRestTemplate() {
		super();
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(
				DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mappingJackson2HttpMessageConverter.setObjectMapper(objectMapper);
		List<MediaType> supportedMediaTypes = new ArrayList<MediaType>();
		supportedMediaTypes.add(new MediaType("text", "plain"));
		supportedMediaTypes.add(new MediaType("application", "json"));
		mappingJackson2HttpMessageConverter
				.setSupportedMediaTypes(supportedMediaTypes);
		messageConverters.add(mappingJackson2HttpMessageConverter);
		FormHttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
		messageConverters.add(formHttpMessageConverter);
		StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
		messageConverters.add(stringHttpMessageConverter);
		setMessageConverters(messageConverters);
	}

}
