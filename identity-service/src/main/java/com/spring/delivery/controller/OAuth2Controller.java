package com.spring.delivery.controller;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpHeaders;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class OAuth2Controller {
    ClientRegistrationRepository clientRegistrationRepository;

    @GetMapping("/auth/google")
    public ResponseEntity<URI> getGoogleAuthUrl() throws UnsupportedEncodingException {
        ClientRegistration clientRegistration = clientRegistrationRepository.findByRegistrationId("google");
        String authorizationUri = clientRegistration.getProviderDetails().getAuthorizationUri();
        String clientId = clientRegistration.getClientId();

        // Redirect URI to handle response
        String redirectUri = "http://localhost:8081/login/oauth2/code/google";
        String scope = URLEncoder.encode("openid profile email", StandardCharsets.UTF_8.toString());

        // Construct URL for the client to redirect to
        String authUrl = String.format(
                "%s?client_id=%s&redirect_uri=%s&response_type=code&scope=%s",
                authorizationUri, clientId, redirectUri, scope
        );

        // Return this URL to the React Native client
        URI uri = URI.create(authUrl);

        // Create custom headers to return along with the URL
        HttpHeaders headers = new HttpHeaders();
        headers.add("User-Agent", "Mozilla/5.0 (Linux; Android 10; Mobile)");
        // Return the URL with custom headers
        return ResponseEntity.ok()
                .headers(headers)
                .body(uri);
    }
}
