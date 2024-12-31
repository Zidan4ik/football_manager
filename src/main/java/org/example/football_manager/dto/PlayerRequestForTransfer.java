package org.example.football_manager.dto;

import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PlayerRequestForTransfer {
    @Min(value = 0,message = "{error.field.size.min}")
    private Long playerId;
    @Min(value = 0,message = "{error.field.size.min}")
    private Long teamBuyingId;
}
