package com.andyadc.samples.domain;

import javax.validation.constraints.NotNull;

public class UserAddress {

    @NotNull
    private String countryCode;
    @NotNull
    private String provinceCode;

    public UserAddress() {
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }
}
