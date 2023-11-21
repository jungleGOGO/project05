package com.tsherpa.team35.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.aop.framework.AopInfrastructureBean;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomVO {

    private Long roomId;
    private int productId;
    private String productTable;
    private String productName = "";
    private String buyerId;
    private String buyerName;
    private int buyerActive;
    private String regDate;

}