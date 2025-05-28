package ng.com.ninepsb.nip_outward.clients.impl;

import ng.com.ninepsb.nip_outward.clients.NIPClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.client.core.WebServiceTemplate;

@Component
class DefaultNIPClient implements NIPClient {

    private final WebServiceTemplate webServiceTemplate;

    DefaultNIPClient(WebServiceTemplate webServiceTemplate) {
        this.webServiceTemplate = webServiceTemplate;
    }

    @Override
    public String performCallToNibss(String encryptedData) {

        try {
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.valueOf("text/xml; charset=utf-8"));
            headers.set("SOAPAction", "");
            headers.set("User-Agent", "YourBank-NIP-Client/1.0");

            HttpEntity<String> entity = new HttpEntity<>(encryptedData, headers);

            ResponseEntity<String> response = restTemplate.postForEntity(
                    webServiceTemplate.getDefaultUri(), entity, String.class);

            return response.getBody();

        } catch (Exception e) {
            throw new RuntimeException("Web service call failed", e);
        }
    }
}
