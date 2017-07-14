package com.abnamro.cognitiveservices.FaceAPIDemo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class FaceAPIController {

	@Value("${subscription.key}")
	private String subscriptionKey;
	@Value("${sample.url}")
	private String sample;

	@RequestMapping("/check")
	public String check() {

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Ocp-Apim-Subscription-Key", subscriptionKey);

		String body = "{ \"url\": \"" + sample + "\" }";

		HttpEntity<String> entity = new HttpEntity<String>(body, headers);

		UriComponents uriComponents = UriComponentsBuilder.newInstance().scheme("https")
				.host("westcentralus.api.cognitive.microsoft.com").path("/face/v1.0/detect")
				.query("returnFaceAttributes={keyword}").buildAndExpand("age,emotion");

		ResponseEntity<String> result = restTemplate.exchange(uriComponents.toUriString(), HttpMethod.POST, entity,
				String.class);

		return result.getBody();
	}

}
