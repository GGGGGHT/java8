package com.ggggght.learningjava8.jfr;

import jdk.jfr.Category;
import jdk.jfr.Description;
import jdk.jfr.Enabled;
import jdk.jfr.Event;
import jdk.jfr.Label;
import jdk.jfr.Name;
import jdk.jfr.Registered;
import org.springframework.stereotype.Component;

@Enabled
@Registered
@Name("jdk.HttpRequestEvent")
@Category("Java Application")
@Description("listens http request events")
@Component
public final class HttpRequestEvent extends Event {
    public static final ThreadLocal<HttpRequestEvent> EVENT =
        ThreadLocal.withInitial(HttpRequestEvent::new);

    @Label("Path")
    @Description("The path of the request")
    public String path;

    @Label("Method")
    @Description("The method of the request")
    public String method;

    public void reset() {
        path = null;
        method = null;
    }
}
