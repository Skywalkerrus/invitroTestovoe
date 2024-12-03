package org.example.enums;

public enum HardVal {
    ПАЦИЕНТАМ("Пациентам"),
    ВРАЧАМ("Врачам"),
    ФРАНЧАЙЗИНГ("Франчайзинг"),
    КОРПОРАТИВНЫМ_КЛИЕНТАМ("Корпоративным клиентам"),
    ПРЕССЕ("Прессе");

    private final String value;

    HardVal(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static boolean isValid(String value) {
        for (HardVal section : values()) {
            if (section.getValue().equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }
}
