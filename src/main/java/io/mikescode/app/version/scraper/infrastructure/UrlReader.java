package io.mikescode.app.version.scraper.infrastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

@Component
public class UrlReader {
    private static final Logger log = LoggerFactory.getLogger(UrlReader.class);

    public Optional<String> retrieveRawPageData(String playStoreUrl) {
        log.info("Retrieving data from URL[" + playStoreUrl + "]");
        try {
            URL url = new URL(playStoreUrl);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuilder stringBuilder = new StringBuilder(2700);
            String inputLine;
            while ((inputLine = bufferedReader.readLine()) != null) {
                stringBuilder.append(inputLine);
            }
            return Optional.of(stringBuilder.toString());
        } catch (MalformedURLException e) {
            log.error("Something is wrong with the URL[" + playStoreUrl + "]", e);
        } catch (IOException e) {
            log.error("IO error occurred while retrieving response body for URL[" + playStoreUrl + "]", e);
        }
        return Optional.empty();
    }
}
