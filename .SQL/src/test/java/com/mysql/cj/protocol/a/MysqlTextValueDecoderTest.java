/*
 * Copyright (c) 2015, 2018, Oracle and/or its affiliates. All rights reserved.
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License, version 2.0, as published by the
 * Free Software Foundation.
 *
 * This program is also distributed with certain software (including but not
 * limited to OpenSSL) that is licensed under separate terms, as designated in a
 * particular file or component or in included license documentation. The
 * authors of MySQL hereby grant you an additional permission to link the
 * program and your derivative works with the separately licensed software that
 * they have included with MySQL.
 *
 * Without limiting anything contained in the foregoing, this file, which is
 * part of MySQL Connector/J, is also subject to the Universal FOSS Exception,
 * version 1.0, a copy of which can be found at
 * http://oss.oracle.com/licenses/universal-foss-exception.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License, version 2.0,
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin St, Fifth Floor, Boston, MA 02110-1301  USA
 */

package com.mysql.cj.protocol.a;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.math.BigInteger;

import org.junit.Test;

import com.mysql.cj.exceptions.NumberOutOfRange;
import com.mysql.cj.result.DefaultValueFactory;
import com.mysql.cj.result.StringValueFactory;
import com.mysql.cj.result.ValueFactory;

/**
 * Tests for {@link MysqlTextValueDecoder}.
 */
public class MysqlTextValueDecoderTest {
    private MysqlTextValueDecoder valueDecoder = new MysqlTextValueDecoder();

    @Test
    public void testNanosecondParsing() {
        // test value factory to extract the parsed nano-seconds
        ValueFactory<Integer> vf = new DefaultValueFactory<Integer>() {
            @Override
            public Integer createFromTime(int hours, int minutes, int seconds, int nanos) {
                return nanos;
            }

            @Override
            public Integer createFromTimestamp(int year, int month, int day, int hours, int minutes, int seconds, int nanos) {
                return nanos;
            }

            public String getTargetTypeName() {
                return Integer.class.getName();
            }
        };

        // the fractional second part is determined by the # of digits
        assertEquals(new Integer(900000000), this.valueDecoder.decodeTimestamp("2016-03-14 14:34:01.9".getBytes(), 0, 21, vf));
        assertEquals(new Integer(950000000), this.valueDecoder.decodeTimestamp("2016-03-14 14:34:01.95".getBytes(), 0, 22, vf));
        assertEquals(new Integer(956000000), this.valueDecoder.decodeTimestamp("2016-03-14 14:34:01.956".getBytes(), 0, 23, vf));

        assertEquals(new Integer(900000000), this.valueDecoder.decodeTime("14:34:01.9".getBytes(), 0, 10, vf));
        assertEquals(new Integer(950000000), this.valueDecoder.decodeTime("14:34:01.95".getBytes(), 0, 11, vf));
        assertEquals(new Integer(956000000), this.valueDecoder.decodeTime("14:34:01.956".getBytes(), 0, 12, vf));
    }

    @Test
    public void testIntValues() {
        ValueFactory<String> vf = new StringValueFactory();
        assertEquals(String.valueOf(Integer.MIN_VALUE), this.valueDecoder.decodeInt4(String.valueOf(Integer.MIN_VALUE).getBytes(), 0, 11, vf));
        assertEquals(String.valueOf(Integer.MAX_VALUE), this.valueDecoder.decodeInt4(String.valueOf(Integer.MAX_VALUE).getBytes(), 0, 10, vf));

        assertEquals(String.valueOf(Integer.MAX_VALUE), this.valueDecoder.decodeUInt4(String.valueOf(Integer.MAX_VALUE).getBytes(), 0, 10, vf));
        assertEquals("2147483648",
                this.valueDecoder.decodeUInt4(BigInteger.valueOf(Integer.MAX_VALUE).add(BigInteger.valueOf(1)).toString().getBytes(), 0, 10, vf));
        try {
            this.valueDecoder.decodeInt4(BigInteger.valueOf(Integer.MAX_VALUE).add(BigInteger.valueOf(1)).toString().getBytes(), 0, 10, vf);
            fail("Exception should be thrown for decodeInt4(Integer.MAX_VALUE + 1)");
        } catch (NumberOutOfRange ex) {
            // expected
        }

        byte[] uint8LessThanMaxLong = "8223372036854775807".getBytes();
        ValueFactory<String> fromLongOnly = new DefaultValueFactory<String>() {
            @Override
            public String createFromLong(long l) {
                return Long.valueOf(l).toString();
            }

            public String getTargetTypeName() {
                return null;
            }
        };
        assertEquals("8223372036854775807", this.valueDecoder.decodeUInt8(uint8LessThanMaxLong, 0, uint8LessThanMaxLong.length, fromLongOnly));
        byte[] uint8MoreThanMaxLong1 = "9223372036854775807".getBytes();
        byte[] uint8MoreThanMaxLong2 = "18223372036854775807".getBytes();
        assertEquals("9223372036854775807", this.valueDecoder.decodeUInt8(uint8MoreThanMaxLong1, 0, uint8MoreThanMaxLong1.length, vf));
        assertEquals("18223372036854775807", this.valueDecoder.decodeUInt8(uint8MoreThanMaxLong2, 0, uint8MoreThanMaxLong2.length, vf));
    }
}
