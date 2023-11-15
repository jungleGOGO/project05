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
    private Integer productId;
    private String productTable;
    private String buyerId;
    private String buyerName;
    private Integer buyerActive;
    private String sellerId;
    private String sellerName;
    private Integer sellerActive;
    private String regDate;

}