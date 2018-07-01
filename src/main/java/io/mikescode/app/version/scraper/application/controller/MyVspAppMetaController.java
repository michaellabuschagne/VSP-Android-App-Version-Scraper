package io.mikescode.app.version.scraper.application.controller;

import io.mikescode.app.version.scraper.domain.model.AppVersion;
import io.mikescode.app.version.scraper.domain.model.ProviderEnum;
import io.mikescode.app.version.scraper.domain.service.AppVersionProvider;
import io.mikescode.app.version.scraper.domain.service.AppVersionService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class MyVspAppMetaController {
    @Autowired
    AppVersionService appVersionService;

    @RequestMapping(value = "/app/{provider}/version", method = RequestMethod.GET)
    public ResponseEntity<AppVersion> getAppVersion(
            @ApiParam(value = "provider", allowableValues = "google, apple", required = true)
            @PathVariable("provider") String provider) {
        AppVersionProvider versionService = appVersionService.getService(ProviderEnum.getByProviderName(provider));
        Optional<AppVersion> appVersion = versionService.getAppVersion();
        if (appVersion.isPresent()) {
            return new ResponseEntity<>(appVersion.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
