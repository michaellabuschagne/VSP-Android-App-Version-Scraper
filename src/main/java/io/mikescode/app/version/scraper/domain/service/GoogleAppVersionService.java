package io.mikescode.app.version.scraper.domain.service;

import io.mikescode.app.version.scraper.domain.model.ProviderEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GoogleAppVersionService extends AppVersionProvider {
    @Value("${service.props.app.google.url}")
    private String googleUrl;

    @Value("${service.props.app.google.regex.version.string}")
    private String versionStringRegex;
    @Value("${service.props.app.google.regex.version.multi-match-fail}")
    private boolean versionStringRegexMultiMatchFail;


    @Value("${service.props.app.google.regex.decimal.string}")
    private String decimalStringRegex;
    @Value("${service.props.app.google.regex.decimal.multi-match-fail}")
    private boolean decimalStringRegexMultiMatchFail;

    @Override
    public String getAppStoreUrl() {
        return googleUrl;
    }

    @Override
    public String getVersionTextRegex() {
        return versionStringRegex;
    }

    @Override
    public boolean getVersionTextRegexFailMultiMatch() {
        return versionStringRegexMultiMatchFail;
    }

    @Override
    public String getVersionOnlyRegex() {
        return decimalStringRegex;
    }

    @Override
    public boolean getVersionOnlyRegexFailMultiMatch() {
        return decimalStringRegexMultiMatchFail;
    }

    @Override
    public ProviderEnum getProvider() {
        return ProviderEnum.GOOGLE;
    }
}
