package com.dmytro.komyshnyi.table;

import com.dmytro.komyshnyi.table.builder.TableBuilder;

public class Main {

    public static void main(String[] args) {
        TableBuilder tableBuilder = new TableBuilder();
        String table = "";

        System.out.println(tableBuilder.build(""));
    }

}
