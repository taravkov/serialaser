package ru.taravkov.serialization.test;

import java.io.Serializable;
import java.util.Objects;


/**
 * @author vtaravkov
 * @since 1.0
 */
public class TestClass implements Serializable {
    private byte byte1 = 1;
    
    private short short1 = 2;
    
    private char char1 = 3;
    
    private int int1 = 4;
    
    private long long1 = 5;
    
    private float float1 = 6;
    
    private double double1 = 7;
    
    private boolean boolean1 = true;

    private Byte byte2 = 8;

    private Short short2 = 9;

    private Character char2 = 10;

    private Integer int2 = 11;

    private Long long2 = 12l;

    private Float float2 = 13f;

    private Double double2 = 14d;

    private Boolean boolean2 = true;

    public TestClass() {
    }

    public TestClass(byte byte1, short short1, char char1, int int1, long long1, float float1, double double1, boolean boolean1,
                     Byte byte2, Short short2, Character char2, Integer int2, Long long2, Float float2, Double double2, Boolean boolean2) {
        this.byte1 = byte1;
        this.short1 = short1;
        this.char1 = char1;
        this.int1 = int1;
        this.long1 = long1;
        this.float1 = float1;
        this.double1 = double1;
        this.boolean1 = boolean1;
        this.byte2 = byte2;
        this.short2 = short2;
        this.char2 = char2;
        this.int2 = int2;
        this.long2 = long2;
        this.float2 = float2;
        this.double2 = double2;
        this.boolean2 = boolean2;
    }

    public byte getByte1() {
        return byte1;
    }

    public short getShort1() {
        return short1;
    }

    public char getChar1() {
        return char1;
    }

    public int getInt1() {
        return int1;
    }

    public long getLong1() {
        return long1;
    }

    public float getFloat1() {
        return float1;
    }

    public double getDouble1() {
        return double1;
    }

    public boolean getBoolean1() {
        return boolean1;
    }

    public Byte getByte2() {
        return byte2;
    }

    public Short getShort2() {
        return short2;
    }

    public Character getChar2() {
        return char2;
    }

    public Integer getInt2() {
        return int2;
    }

    public Long getLong2() {
        return long2;
    }

    public Float getFloat2() {
        return float2;
    }

    public Double getDouble2() {
        return double2;
    }

    public Boolean getBoolean2() {
        return boolean2;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        TestClass testClass = (TestClass) object;
        return byte1 == testClass.byte1 &&
                short1 == testClass.short1 &&
                char1 == testClass.char1 &&
                int1 == testClass.int1 &&
                long1 == testClass.long1 &&
                Float.compare(testClass.float1, float1) == 0 &&
                Double.compare(testClass.double1, double1) == 0 &&
                boolean1 == testClass.boolean1 &&
                Objects.equals(byte2, testClass.byte2) &&
                Objects.equals(short2, testClass.short2) &&
                Objects.equals(char2, testClass.char2) &&
                Objects.equals(int2, testClass.int2) &&
                Objects.equals(long2, testClass.long2) &&
                Objects.equals(float2, testClass.float2) &&
                Objects.equals(double2, testClass.double2) &&
                Objects.equals(boolean2, testClass.boolean2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(byte1, short1, char1, int1, long1, float1, double1, boolean1, byte2, short2, char2, int2, long2, float2, double2, boolean2);
    }

    @Override
    public String toString() {
        return "TestClass{" +
                "byte1=" + byte1 +
                ", short1=" + short1 +
                ", char1=" + char1 +
                ", int1=" + int1 +
                ", long1=" + long1 +
                ", float1=" + float1 +
                ", double1=" + double1 +
                ", boolean1=" + boolean1 +
                ", byte2=" + byte2 +
                ", short2=" + short2 +
                ", char2=" + char2 +
                ", int2=" + int2 +
                ", long2=" + long2 +
                ", float2=" + float2 +
                ", double2=" + double2 +
                ", boolean2=" + boolean2 +
                '}';
    }
}
