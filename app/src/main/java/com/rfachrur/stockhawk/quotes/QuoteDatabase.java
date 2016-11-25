package com.rfachrur.stockhawk.quotes;

import net.simonvt.schematic.annotation.Database;
import net.simonvt.schematic.annotation.Table;

/**
 * Created by rfachrur on 11/23/16.
 *
 */

@Database(version = QuoteDatabase.VERSION)
class QuoteDatabase {
    private QuoteDatabase(){}

    static final int VERSION = 7;

    @Table(QuoteColumns.class)
    static final String QUOTES = "quotes";
}
