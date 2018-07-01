package io.mikescode.app.version.scraper.domain.service;

import io.mikescode.app.version.scraper.domain.model.AppPageRetrievalRegexException;
import io.mikescode.app.version.scraper.domain.model.AppVersion;
import io.mikescode.app.version.scraper.domain.model.ProviderEnum;
import io.mikescode.app.version.scraper.infrastructure.UrlReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public abstract class AppVersionProvider {
    @Autowired
    UrlReader urlReader;

    private final Logger log = LoggerFactory.getLogger(this.getClass().getName());

    public abstract String getAppStoreUrl();

    public abstract String getVersionTextRegex();
    public abstract boolean getVersionTextRegexFailMultiMatch();

    public abstract String getVersionOnlyRegex();
    public abstract boolean getVersionOnlyRegexFailMultiMatch();

    public abstract ProviderEnum getProvider();

    public Optional<AppVersion> getAppVersion() {
        Optional<String> pageData = Optional.empty();
        try {
            pageData = urlReader.retrieveRawPageData(getAppStoreUrl());

        } catch (Exception e) {
            log.error("An unexpected error occurred while retrieving the MyVodacomApp page data", e);
        }
        try {
            if (pageData.isPresent()) {
                return Optional.of(new AppVersion(parsePageDataAndRetrieveVersion(pageData.get()), getProvider(), new Date()));
            }
        } catch (Exception e) {
            log.error("An unexpected error occurred while parsing the MyVodacomApp page data", e);
        }
        return Optional.empty();
    }

    public String parsePageDataAndRetrieveVersion(String pageData) {
        String fullVersionText = performPatterMatch(pageData, getVersionTextRegex(), getVersionTextRegexFailMultiMatch());
        return performPatterMatch(fullVersionText, getVersionOnlyRegex(), getVersionOnlyRegexFailMultiMatch());
    }

    private String performPatterMatch(String inputString, String regex, boolean failMultiMatch) {
        Pattern patter = Pattern.compile(regex, Pattern.MULTILINE);
        Matcher matcher = patter.matcher(inputString);
        boolean foundMatch = false;
        String matchedString = null;
        while (matcher.find()) {
            log.debug("Full match: " + matcher.group(0));
            matchedString = matcher.group(0);
            if (foundMatch) {
                throw new AppPageRetrievalRegexException("More than one match found [" + matcher.group(0) + "]");
            }
            foundMatch = true;
            if (!failMultiMatch) {
                return matchedString;
            }
        }
        return matchedString;
    }
}
