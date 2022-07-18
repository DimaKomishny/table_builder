package com.dmytro.komyshnyi.table.builder;

import com.dmytro.komyshnyi.table.data.Cell;
import com.dmytro.komyshnyi.table.data.Row;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
public class PlainTextTableBuilder implements TableBuilder {

    private static final String BORDER_VERTICAL = "|";
    private static final String BORDER_HORIZONTAL = "-";
    private static final String BORDER_CROSS = "+";
    private String cellSeparator = ",";

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
        return stringBuilder.
                insert(0, buildBorder(rows.get(0)))
                .toString();
    }

    private String buildRow(Row row) {
        StringBuilder stringBuilder = new StringBuilder("\n" + BORDER_VERTICAL);
        for (Cell cell : row.getCells()) {
            stringBuilder.append(cellToString(cell));
        }
        return stringBuilder
                .append(buildBorder(row))
                .toString();
    }

    private String buildBorder(Row row) {
        StringBuilder stringBuilder = new StringBuilder("\n" + BORDER_CROSS);
        for (Cell cell : row.getCells()) {
            stringBuilder.append(addSign(cell.getMinSize() , BORDER_HORIZONTAL)).append(BORDER_CROSS);
        }
        return stringBuilder.toString();
    }

    private String cellToString(Cell cell) {
        return cell.getValue()
                + addSign(cell.getMinSize() - cell.getValue().length(), " ")
                + BORDER_VERTICAL;
    }

    private List<String[]> parseText(String text) {
        String[] rowStr = text.split("\n");
        return Arrays.stream(rowStr)
                .map(row -> row.split(cellSeparator))
                .collect(Collectors.toList());
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
        int size = findLongestCellInColumn(table, column);
        table.stream()
                .map(row -> row.getCells().get(column))
                .forEach(cell -> cell.setMinSize(size));
    }

    private int findLongestCellInColumn(List<Row> table, int column) {
        return table.stream()
                .map(row -> row.getCells().get(column))
                .max(Comparator.comparingInt(c -> c.getValue().length()))
                .orElseThrow(RuntimeException::new)
                .getValue()
                .length();
    }

    private static String addSign(int number, String sign) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i <= number; i++) {
            stringBuilder.append(sign);
        }
        return stringBuilder.toString();
    }
}
