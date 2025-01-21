package com.czak.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductDescRequest {
    @JsonProperty("filename")
    private String filename;

    @JsonProperty("description")
    private String description;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ProductDescRequest{" +
                "filename='" + filename + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
