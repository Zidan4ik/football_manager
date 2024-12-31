package org.example.football_manager.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import org.example.football_manager.entity.Team;

@Builder
@Getter
public class PlayerRequestForAdd {
    private Long id;
    @NotBlank(message = "{error.field.empty}")
    @Size(max = 50, message = "{error.field.size.between.characters}")
    private String nickname;
    @Min(value = 18, message = "{error.field.size.min}")
    @Max(value = 30, message = "{error.field.size.max}")
    private Integer age;
    @Min(value = 0,message = "{error.size.min}")
    private Integer experienceMonths;
    private Team team;
}
