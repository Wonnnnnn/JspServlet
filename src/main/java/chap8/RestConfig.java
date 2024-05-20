package chap8;


import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import java.util.Hashtable;
import java.util.Map;

@ApplicationPath("/api")
public class RestConfig extends Application {

    @Override
    public Map<String, Object> getProperties() {
        Map<String, Object> properties = new Hashtable<>();
        properties.put("jersey.config.server.provider.packages", "chap8");
        return properties;
    }
}
