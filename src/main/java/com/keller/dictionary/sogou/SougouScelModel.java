package com.keller.dictionary.sogou;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class SougouScelModel {
    private Map<String, List<String>> wordMap;

    private String name;

    private String type;

    private String description;

    private String sample;
}
