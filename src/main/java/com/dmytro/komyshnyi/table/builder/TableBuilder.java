package com.dmytro.komyshnyi.table.builder;

import com.dmytro.komyshnyi.table.data.Cell;
import com.dmytro.komyshnyi.table.data.Row;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TableBuilder {

    public String build(String text) {
        List<String[]> cells = parseText(text);
        List<Row> rows = createRows(cells);
        computeColumnSize(rows);
        return buildTable(rows);
    }

    private String buildTable(List<Row> rows) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Row row : rows) {
            stringBuilder.append(buildRow(row));
        }
        return stringBuilder.toString();
    }

    private String buildRow(Row row) {
        StringBuilder stringBuilder = new StringBuilder("\n|");
        for (Cell cell : row.getCells()) {
            stringBuilder.append(cellToString(cell));
        }
        return stringBuilder
                .append(buildBorder(row))
                .toString();
    }

    private String buildBorder(Row row) {
        StringBuilder stringBuilder = new StringBuilder("\n+");
        for (Cell cell : row.getCells()) {
            stringBuilder.append(addSign(cell.getMinSize() , '-')).append("+");
        }
        return stringBuilder.toString();
    }

    private String cellToString(Cell cell) {
        return cell.getValue()
                + addSign(cell.getMinSize() - cell.getValue().length(), ' ')
                + '|';
    }

    private List<String[]> parseText(String text) {
        String[] rowStr = text.split("\n");
        List<String[]> cellStr = Arrays.stream(rowStr)
                .map(row -> row.split(","))
                .collect(Collectors.toList());
        return cellStr;
    }

    private List<Row> createRows(List<String[]> cellValues) {
        return cellValues.stream()
                .map(this::createCells)
                .map(Row::new)
                .collect(Collectors.toList());
    }

    private List<Cell> createCells(String[] values) {
        return Arrays.stream(values).map(Cell::new).collect(Collectors.toList());
    }

    private void computeColumnSize(List<Row> table) {
        int numberOfColumn = table.get(0).getCells().size();
        for (int i = 0; i < numberOfColumn; i++) {
            setColumnSize(table, i);
        }
    }

    private void setColumnSize(List<Row> table, int column) {
        int size = table.stream()
                .map(row -> row.getCells().get(column))
                .max(Comparator.comparingInt(c -> c.getValue().length()))
                .orElseThrow(RuntimeException::new)
                .getValue()
                .length();
        table.stream()
                .map(row -> row.getCells().get(column))
                .forEach(cell -> cell.setMinSize(size));
    }

    private static String addSign(int number, char sign) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i <= number; i++) {
            stringBuilder.append(sign);
        }
        return stringBuilder.toString();
    }
}
