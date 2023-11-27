package edu.project3.logsprovider.sources;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public final class URLSource implements AbstractSource {
    private final URL url;

    public URLSource(String pattern) throws URISyntaxException, IllegalArgumentException, MalformedURLException {
        url = new URI(pattern).toURL();
    }

    public URL getURL() {
        return url;
    }
}
