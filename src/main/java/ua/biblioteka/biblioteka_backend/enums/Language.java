package ua.biblioteka.biblioteka_backend.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Language {
//    UK, EN

    UK("uk"),
    EN("en");

    private final String mongoLanguageCode;

    Language(String mongoLanguageCode) {
        this.mongoLanguageCode = mongoLanguageCode;
    }

    public String getMongoLanguageCode() {
        return mongoLanguageCode;
    }

//    @JsonCreator
//    public static Language fromValue(String value) {
//        for (Language language : values()) {
//            if (language.mongoCode.equalsIgnoreCase(value) || language.name().equalsIgnoreCase(value)) {
//                return language;
//            }
//        }
//        throw new IllegalArgumentException("Unknown language: " + value);
//    }
}

