package com.andyadc.ssm.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author andy.an
 * @since 2018/9/29
 */
@Getter
@Setter
public class DemoDTO {

    private Long id;

    private String name;

    private int status;

    private Date createdTime;

    private Date updatedTime;


}
