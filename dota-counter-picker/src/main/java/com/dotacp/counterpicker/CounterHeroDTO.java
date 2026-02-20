package com.dotacp.counterpicker;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CounterHeroDTO {
    private String name;
    private String role;
    private Double winrate;
}
