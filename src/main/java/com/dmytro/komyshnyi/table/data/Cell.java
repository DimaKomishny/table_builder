package com.dmytro.komyshnyi.table.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Cell {

    private String value = "";
    private int minSize = 0;

    public Cell(String value) {
        this.value = value;
    }
}
