package com.dmytro.komyshnyi.table;

import com.dmytro.komyshnyi.table.builder.JiraTableBuilder;
import com.dmytro.komyshnyi.table.builder.TableBuilder;
import com.dmytro.komyshnyi.table.builder.PlainTextTableBuilder;

public class Main {

    public static void main(String[] args) {
        TableBuilder plainTextTableBuilder = new PlainTextTableBuilder();
        String table = "row1cell1,row1cell2,row1cell3\n" +
                "row2cell1,row2cell2,row2cell3";
        TableBuilder jiraTableBuilder = new JiraTableBuilder();

        System.out.println("plainText\n" + plainTextTableBuilder.build(table));
        System.out.println();
        System.out.println("Table for Jira\n" + jiraTableBuilder.build(table));
    }
}
