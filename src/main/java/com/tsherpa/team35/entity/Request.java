package com.tsherpa.team35.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Request {
    private int req_no;
    private String title;
    private int price;
    private String content;
    private int user_id;
    private String active;
    private String regdate;
    private String addr1;
    private String addr2;
    private String postcode;
}


