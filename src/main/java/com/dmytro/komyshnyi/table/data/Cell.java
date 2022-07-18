package com.dmytro.komyshnyi.table.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Cell {

    private String value;
    private int minSize;

    public Cell(String value) {
        this.value = value;
    }
}
