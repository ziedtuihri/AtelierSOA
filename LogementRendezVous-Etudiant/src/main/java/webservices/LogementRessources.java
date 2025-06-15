package webservices;
import entities.Logement;
import metiers.LogementBusiness;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
@Path("/logement") public class LogementRessources {
    LogementBusiness help = new LogementBusiness();
    @GET @Path("/getAll") @Produces(MediaType.APPLICATION_JSON) public Response getAll() {
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(help.getLogements()).build();
    }
    @GET @Path("/getByRef/{ref}") @Produces(MediaType.APPLICATION_JSON) public Response getByReference(@PathParam("ref") int ref) {
        Logement logement = help.getLogementsByReference(ref);
        if (logement != null) {
            return Response.ok(logement).header("Access-Control-Allow-Origin", "*").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Logement non trouvé").build();
        }
    }
    @GET @Path("/getByDelegation/{delegation}") @Produces(MediaType.APPLICATION_JSON) public Response getByDelegation(@PathParam("delegation") String delegation) {
        List < Logement > list = help.getLogementsByDeleguation(delegation);
        return Response.ok(list).header("Access-Control-Allow-Origin", "*").build();
    }
    @POST @Path("/add") @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON) public Response addLogement(Logement logement) {
        boolean added = help.addLogement(logement);
        if (added) {
            return Response.status(Response.Status.CREATED).entity("Logement ajouté avec succès").header("Access-Control-Allow-Origin", "*").build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erreur lors de l'ajout").build();
        }
    }
    @PUT @Path("/update/{reference}") @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON) public Response updateLogement(@PathParam("reference") int reference, Logement logement) {
        boolean updated = help.updateLogement(reference, logement);
        if (updated) {
            return Response.status(Response.Status.OK).entity("Logement mis à jour avec succès").header("Access-Control-Allow-Origin", "*").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Logement non trouvé pour mise à jour").build();
        }
    }
    @DELETE @Path("/delete/{ref}") @Produces(MediaType.TEXT_PLAIN) public Response deleteLogement(@PathParam("ref") int ref) {
        boolean deleted = help.deleteLogement(ref);
        if (deleted) {
            return Response.ok("Logement supprimé").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Logement non trouvé").build();
        }
    }
}