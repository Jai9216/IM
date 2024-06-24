package com.im.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class GeolocationService {

    @Value("${google.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public GeocodeResponse.Result getLocationByPincode(String pincode) {
        String url = UriComponentsBuilder.fromHttpUrl("https://maps.googleapis.com/maps/api/geocode/json")
                .queryParam("address", pincode)
                .queryParam("key", apiKey)
                .toUriString();
        System.out.println(url);
        GeocodeResponse response = restTemplate.getForObject(url, GeocodeResponse.class);
        System.out.println(response);
        if (response != null && !response.getResults().isEmpty()) {
            return response.getResults().get(0);
        }

        return null;
    }


    public String getCityFromResponse(GeocodeResponse.Result result) {
        if (result != null && result.getAddressComponents() != null) {
            for (GeocodeResponse.AddressComponent component : result.getAddressComponents()) {
                if (component.getTypes() != null && component.getTypes().contains("locality")) {
                    System.out.println(component.getLongName());
                    return component.getLongName(); // This is the city name
                }
            }
        }
        return null; // City not found
    }

    public String getCountryFromResponse(GeocodeResponse.Result result) {
        if (result != null && result.getAddressComponents() != null) {
            for (GeocodeResponse.AddressComponent component : result.getAddressComponents()) {
                if (component.getTypes() != null && component.getTypes().contains("country")) {
                    System.out.println(component.getLongName());
                    return component.getLongName(); // This is the country name
                }
            }
        }
        return null; // Country not found
    }
}
