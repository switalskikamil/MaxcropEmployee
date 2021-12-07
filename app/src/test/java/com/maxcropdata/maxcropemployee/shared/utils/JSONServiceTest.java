package com.maxcropdata.maxcropemployee.shared.utils;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.Objects;

import static org.junit.Assert.*;

public class JSONServiceTest {

    @Test
    public void isFormattingAsJSONProperly() {
        // given
        TestObject testObject = new TestObject(5);


        try {
            // when
            String formatted = JSONService.formatAsJSON(testObject);

            // then
            new JSONObject(formatted);

        } catch (IllegalAccessException | JSONException e) {
            fail(e.getMessage());
        }



    }

    @Test
    public void isReadJSONIntoObjectProperly() {
        // given
        TestObject expected = new TestObject(5);



        try {
            // when
            JSONObject json = new JSONObject(JSONService.formatAsJSON(expected));

            TestObject actual = new TestObject();
            JSONService.readJSONIntoObject(json, actual);

            // then
            assertEquals(expected, actual);

        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    class TestObject {
        int intField;
        String stringField;
        BigDecimal bigDecimalField;
        boolean boolField;
        long longField;
        Date dateField;

        TestObject() {

        }

        TestObject(int i) {
            this.intField = i;
            this.longField = i;
            this.stringField = "i=" + i;
            this.dateField = new Date(i*1000);
            this.bigDecimalField = new BigDecimal(i).setScale(2, RoundingMode.HALF_DOWN);
            this.boolField = i > 0;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TestObject that = (TestObject) o;
            return intField == that.intField &&
                    boolField == that.boolField &&
                    longField == that.longField &&
                    Objects.equals(stringField, that.stringField) &&
                    Objects.equals(bigDecimalField, that.bigDecimalField) &&
                    Objects.equals(dateField, that.dateField);
        }

        @Override
        public int hashCode() {
            return Objects.hash(intField, stringField, bigDecimalField, boolField, longField, dateField);
        }
    }
}