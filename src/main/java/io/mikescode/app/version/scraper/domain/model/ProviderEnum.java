package io.mikescode.app.version.scraper.domain.model;

public enum ProviderEnum {
    APPLE("apple"), GOOGLE("google");

    private String providerName;

    ProviderEnum(String providerName) {
        this.providerName = providerName;
    }

    public String getProviderName() {
        return providerName;
    }

    public static ProviderEnum getByProviderName(String searchString) {
        for (ProviderEnum providerEnum: ProviderEnum.values()) {
            if (providerEnum.getProviderName().equalsIgnoreCase(searchString)) {
                return providerEnum;
            }
        }
        throw new IllegalArgumentException("Unknown provider name [" + searchString + "]");
    }
}
