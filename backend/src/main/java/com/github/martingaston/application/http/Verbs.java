package com.github.martingaston.application.http;

public enum Verbs {
    GET ("GET"),
    POST ("POST"),
    HEAD ("HEAD"),
    OPTIONS("OPTIONS"),
    PUT("PUT"),
    INVALID ("Invalid");

    String name;

    Verbs(String name) {
        this.name = name;
    }

    static Verbs from(String name) {
        switch(name) {
            case "GET": return Verbs.GET;
            case "POST": return Verbs.POST;
            case "HEAD": return Verbs.HEAD;
            case "OPTIONS": return Verbs.OPTIONS;
            case "PUT": return Verbs.PUT;
            default: return Verbs.INVALID;
        }
    }
}
