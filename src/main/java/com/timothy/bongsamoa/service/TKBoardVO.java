package com.timothy.bongsamoa.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@AllArgsConstructor
@ToString
@Getter
public class TKBoardVO {
    private int id;
    private String title;
    private String writer;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date creationDate;
    private String content;
}
