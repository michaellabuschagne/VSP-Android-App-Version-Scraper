package io.mikescode.app.version.scraper.domain.service;

import io.mikescode.app.version.scraper.domain.model.ProviderEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class AppVersionService {
    @Autowired
    private ApplicationContext applicationContext;

    public AppVersionProvider getService(ProviderEnum provider) {
        switch (provider) {
            case APPLE:
                return (AppVersionProvider) applicationContext.getBean("appleAppVersionService");
            case GOOGLE:
                return (AppVersionProvider) applicationContext.getBean("googleAppVersionService");
        }
        throw new IllegalArgumentException("Unsupported provider [" + provider + "]");
    }
}
