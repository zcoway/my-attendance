package org.lch.attendance.common;

import java.io.Serializable;

public enum Gender implements Serializable {
	Male("ÄÐ", 0), Female("Å®", 1), Other("±£ÃÜ", 2);
	private String name;
	private int value;

	public String getName() {
		return name;
	}

	public int getValue() {
		return value;
	}

	private Gender(String name, int value) {
		this.name = name;
		this.value = value;
	}

	public static Gender getGender(int value) { 
		if (0 == value){ 
            return Male; 
		}else if (1 == value){ 
            return Female; 
        }else{ 
            return Other; 
        }
    }

	@Override
	public String toString() {
		return this.name;
	}
}
