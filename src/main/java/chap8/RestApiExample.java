package chap8;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/test")
public class RestApiExample {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayhello() {
        return "hello api service";
    }

    @POST
    public String sayhello(@QueryParam("msg") String msg) {
        return msg + " api service";
    }

}
