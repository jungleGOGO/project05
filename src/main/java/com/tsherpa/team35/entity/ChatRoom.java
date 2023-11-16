package com.tsherpa.team35.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoom {

    private Long roomId;
    private Integer productId;
    private String productTable;
    private String buyerId;
    private String regDate;

}