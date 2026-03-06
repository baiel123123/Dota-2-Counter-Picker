package com.dotacp.counterpicker.infrastructure;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpenDotaHero {
    private Long id;
    private String localized_name;
    private String name;
    private String primary_attr;
    private String attack_type;
    private List<String> roles;
}
