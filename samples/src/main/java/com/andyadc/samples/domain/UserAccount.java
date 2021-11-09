package com.andyadc.samples.domain;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserAccount {

    @NotBlank(groups = {AdvanceInfo.class})
    private String name;

    @NotNull(groups = {BasicInfo.class})
    @Size(min = 6, max = 16, groups = {BasicInfo.class})
    private String password;

    @NotBlank(groups = {AdvanceInfo.class})
    private String phone;

    @Min(value = 18, message = "Age should not be less than 18")
    private Integer age;

    @Valid
    @NotNull(groups = {AdvanceInfo.class})
    private UserAddress userAddress;

    public UserAccount() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public UserAddress getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(UserAddress userAddress) {
        this.userAddress = userAddress;
    }
}
