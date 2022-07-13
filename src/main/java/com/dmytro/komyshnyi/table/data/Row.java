package com.dmytro.komyshnyi.table.data;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Row {

    List<Cell> cells;
}
