package com.upk.diploma.service;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.upk.diploma.config.properties.ExternalServicesProperties;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
public class BasicRestService<T> {

    protected final ExternalServicesProperties externalServicesProperties;
    protected final RestTemplate restTemplate;
    protected final ObjectMapper objectMapper;
    private final Class<T> clazz;

    public T get(String url, HttpHeaders headers){
        HttpEntity requestEntity = new HttpEntity<>(null, headers);

        ResponseEntity<T> response = restTemplate
                .exchange(url,
                        HttpMethod.GET,
                        requestEntity,
                        clazz );

        return response.getBody();
    }

    public List<T> getAll(String url, HttpHeaders headers){
        HttpEntity requestEntity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate
                .exchange(url,
                        HttpMethod.GET,
                        requestEntity,
                        String.class);

        CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, clazz);
        return readValue(response, collectionType);
    }

    public T post(String url, Object body, HttpHeaders headers){
        HttpEntity requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<T> response = restTemplate
                .exchange(url,
                        HttpMethod.POST,
                        requestEntity,
                        clazz);

        return response.getBody();
    }

    public T put(String url, Object body, HttpHeaders headers){
        HttpEntity requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<T> response = restTemplate
                .exchange(url,
                        HttpMethod.PUT,
                        requestEntity,
                        clazz);

        return response.getBody();
    }

    public void delete(String url, HttpHeaders headers){
        HttpEntity requestEntity = new HttpEntity<>(null, headers);

        ResponseEntity<T> response = restTemplate
                .exchange(url,
                        HttpMethod.DELETE,
                        requestEntity,
                        clazz);
    }

    private <T> T readValue(ResponseEntity<String> response, JavaType javaType) {
        T result = null;
        if (response.getStatusCode() == HttpStatus.OK ||
                response.getStatusCode() == HttpStatus.CREATED) {
            try {
                result = objectMapper.readValue(response.getBody(), javaType);
            } catch (IOException e) {
                //LOGGER.info(e.getMessage());
            }
        } else {
            //LOGGER.info("No data found {}", response.getStatusCode());
        }
        return result;
    }
}
