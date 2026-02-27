package com.dotacp.counterpicker.infrastructure;

import lombok.Data;

import java.util.List;

@Data
public class OpenDotaHero {
    private Long id;
    private String localized_name;
    private String name;
    private String primary_attr;
    private String attack_type;
    private List<String> roles;
}
