package com.upk.diploma.service;

import com.upk.diploma.config.properties.ExternalServicesProperties;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@AllArgsConstructor
public class BasicRestService<T> {

    protected final ExternalServicesProperties externalServicesProperties;
    protected final RestTemplate restTemplate;

    public T get(String url, HttpHeaders headers){
        HttpEntity requestEntity = new HttpEntity<>(null, headers);

        ResponseEntity<T> response = restTemplate
                .exchange(url,
                        HttpMethod.GET,
                        requestEntity,
                        new ParameterizedTypeReference<>() {
                        });

        return response.getBody();
    }

    public List<T> getAll(String url, HttpHeaders headers){
        HttpEntity requestEntity = new HttpEntity<>(null, headers);

        ResponseEntity<List<T>> response = restTemplate
                .exchange(url,
                        HttpMethod.GET,
                        requestEntity,
                        new ParameterizedTypeReference<>() {
                        });

        return response.getBody();
    }

    public T post(String url, Object body, HttpHeaders headers){
        HttpEntity requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<T> response = restTemplate
                .exchange(url,
                        HttpMethod.POST,
                        requestEntity,
                        new ParameterizedTypeReference<>() {
                        });

        return response.getBody();
    }

    public void delete(String url, HttpHeaders headers){
        HttpEntity requestEntity = new HttpEntity<>(null, headers);

        ResponseEntity<T> response = restTemplate
                .exchange(url,
                        HttpMethod.DELETE,
                        requestEntity,
                        new ParameterizedTypeReference<>() {
                        });
    }
}
