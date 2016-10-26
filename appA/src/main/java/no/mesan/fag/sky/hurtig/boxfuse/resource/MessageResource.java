package no.mesan.fag.sky.hurtig.boxfuse.resource;

import no.mesan.fag.sky.hurtig.boxfuse.storage.Message;
import no.mesan.fag.sky.hurtig.boxfuse.storage.MessageStorage;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
@Path("/messages")
public class MessageResource {

    private final MessageStorage messageStorage;

    @Inject
    public MessageResource(MessageStorage messageStorage) {
        this.messageStorage = messageStorage;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<MessageResponse> messages() {
        return messageStorage.getMessages().stream()
                .map(MessageResponse::from)
                .collect(toList());
    }

    @POST
    @Path("/{message}")
    public Response insertMessage(@PathParam("message") String message) {
        messageStorage.insert(new Message(message, LocalDateTime.now()));
        return Response.ok().build();
    }
}
