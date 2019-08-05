package com.github.martingaston.application.http;

public enum Version {
    V1POINT1 ("HTTP/1.1"),
    INVALID("INVALID");

    String name;

    Version(String name) {
        this.name = name;
    }

    static Version from(String name) {
        switch(name) {
            case "HTTP/1.1": return Version.V1POINT1;
            default: return Version.INVALID;
        }
    }
}
