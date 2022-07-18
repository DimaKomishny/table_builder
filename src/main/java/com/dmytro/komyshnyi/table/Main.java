package com.dmytro.komyshnyi.table;

import com.dmytro.komyshnyi.table.builder.TableBuilderPlainText;

public class Main {

    public static void main(String[] args) {
        TableBuilderPlainText tableBuilder = new TableBuilderPlainText();
        String table = "row1cell1,row1cell2,row1cell3\n" +
                "row2cell1,row2cell2,row2cell3";

        System.out.println(tableBuilder.build(table));
    }

}
