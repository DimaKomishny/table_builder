package com.dmytro.komyshnyi.table.data;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Row {

    private List<Cell> cells;
}
