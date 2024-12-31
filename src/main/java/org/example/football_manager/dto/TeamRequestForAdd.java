package org.example.football_manager.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import org.example.football_manager.validator.SizeValidation;

@Builder
@Getter
public class TeamRequestForAdd {
    private Long id;
    @NotBlank(message = "{error.field.empty}")
    private String name;
    @SizeValidation(message = "{error.field.size.commission}")
    private Integer commission;
    @Min(value = 0,message = "{error.field.size.min}")
    private int balance;
}