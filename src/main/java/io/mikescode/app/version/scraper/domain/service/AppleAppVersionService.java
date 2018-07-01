package io.mikescode.app.version.scraper.domain.service;

import io.mikescode.app.version.scraper.domain.model.ProviderEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppleAppVersionService extends AppVersionProvider {
    @Value("${service.props.app.apple.url}")
    private String appleUrl;

    @Value("${service.props.app.apple.regex.version.string}")
    private String versionStringRegex;
    @Value("${service.props.app.apple.regex.version.multi-match-fail}")
    private boolean versionStringRegexMultiMatchFail;


    @Value("${service.props.app.apple.regex.decimal.string}")
    private String decimalStringRegex;
    @Value("${service.props.app.apple.regex.decimal.multi-match-fail}")
    private boolean decimalStringRegexMultiMatchFail;

    @Override
    public String getAppStoreUrl() {
        return appleUrl;
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
        return ProviderEnum.APPLE;
    }
}
