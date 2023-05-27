package com.upk.diploma.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrderResponse {
    private Long id;
    private Date dateOrdered;
    private Date dateReceived;
    private OrderStatusResponse orderStatus;
    private Long customerProfileId;
}
