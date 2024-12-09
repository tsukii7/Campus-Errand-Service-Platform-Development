package com.example.ordertaker.util;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;

class UtilTest {

    @Test
    void testDate2String() {
        assertThat(Util.date2String(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())).isEqualTo("2020-01-01T00:00:00.000+08:00");
    }
}
