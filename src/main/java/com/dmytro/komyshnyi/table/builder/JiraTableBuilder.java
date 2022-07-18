package com.dmytro.komyshnyi.table.builder;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
@NoArgsConstructor
public class JiraTableBuilder implements TableBuilder {

    private static final String BORDER = "|";
    private static final String HEADER_BORDER = "||";
    private String cellSeparator = ",";

    @Override
    public String build(String text) {
        String[] rows = text.split("\n");
        StringBuilder stringBuilder = new StringBuilder(processHeader(rows[0]) + '\n');
        Arrays.stream(rows)
                .skip(1)
                .map(this::processRow)
                .forEach(r ->
                        stringBuilder.append(r).append('\n'));
        return stringBuilder.toString();

    }

    private String processRow(String row) {
        return BORDER + row.replace(cellSeparator, BORDER) + BORDER;
    }

    private String processHeader(String row) {
        return HEADER_BORDER + row.replace(cellSeparator, HEADER_BORDER) + HEADER_BORDER;
    }
}
