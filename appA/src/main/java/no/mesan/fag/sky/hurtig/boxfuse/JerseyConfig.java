package no.mesan.fag.sky.hurtig.boxfuse;

import no.mesan.fag.sky.hurtig.boxfuse.resource.MessageResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(MessageResource.class);
    }

}
