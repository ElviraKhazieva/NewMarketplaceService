package com.technokratos.newmarketplaceservice.enums;

public enum MarketplaceServiceUrl {

    PRODUCTS("http://localhost:1234/products"), ORDERS("http://localhost:1234/orders");

    private String url;

    MarketplaceServiceUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

}
