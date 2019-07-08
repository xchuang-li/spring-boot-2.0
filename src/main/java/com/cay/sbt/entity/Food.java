package com.cay.sbt.entity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class Food {
    private Integer id;
    private String name;
    private String proTime;
    private String expDate;
}
