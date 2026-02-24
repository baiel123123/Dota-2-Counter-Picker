package com.dotacp.counterpicker.application;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CounterHeroDTO {
    private String name;
    private String hero_name;
    private String role;
    private Double winrate;
    private Integer games_played;
}
