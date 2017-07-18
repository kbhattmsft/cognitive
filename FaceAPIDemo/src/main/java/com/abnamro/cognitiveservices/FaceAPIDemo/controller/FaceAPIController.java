package com.abnamro.cognitiveservices.FaceAPIDemo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.abnamro.cognitiveservices.FaceAPIDemo.model.FaceAPIModel;

@RestController
public class FaceAPIController {

	@Value("${subscription.key}")
	private String subscriptionKey;
	@Value("${sample.url}")
	private String sampleUrl;

	@RequestMapping("/detect")
	public String check(@ModelAttribute FaceAPIModel model) {

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		// Form values take precendence
		String key = model.getKey() != null ? model.getKey() : subscriptionKey;
		String url = model.getUrl() != null ? model.getUrl() : sampleUrl;

		headers.set("Ocp-Apim-Subscription-Key", key);

		String body = "{ \"url\": \"" + url + "\" }";

		HttpEntity<String> entity = new HttpEntity<String>(body, headers);

		UriComponents uriComponents = UriComponentsBuilder.newInstance().scheme("https")
				.host("westcentralus.api.cognitive.microsoft.com").path("/face/v1.0/detect")
				.query("returnFaceAttributes={keyword}").buildAndExpand("age,emotion");

		ResponseEntity<String> result = restTemplate.exchange(uriComponents.toUriString(), HttpMethod.POST, entity,
				String.class);

		return result.getBody();

	}

}
