package io.mikescode.app.version.scraper.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Data
public class AppVersion {
    private String version;
    private ProviderEnum provider;
    private Date timestamp;
}
