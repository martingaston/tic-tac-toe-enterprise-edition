package com.github.martingaston.application.http;

public class URI {
    private final String URI;

    private URI(String URI) {
        this.URI = URI;
    }

    public static URI from(String URI) {
        return new URI(URI);
    }

    @Override
    public String toString() {
        return this.URI;
    }

    @Override
    public boolean equals(Object obj) {
        URI uri = (URI) obj;
        return getClass() == obj.getClass() && this.URI.equals(uri.URI);
    }

    @Override
    public int hashCode() {
        return this.URI.hashCode();
    }
}
