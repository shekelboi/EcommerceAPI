package com.ecommerce.API.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BooleanResponse {
    private boolean success;

    public BooleanResponse(boolean success) {
        this.success = success;
    }
}
