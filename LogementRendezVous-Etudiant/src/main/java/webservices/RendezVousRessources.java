package webservices;

import entities.RendezVous;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import metiers.RendezVousBusiness;

@Path("/rendezvous")
public class RendezVousRessources {

    private RendezVousBusiness rendezVousBusiness = new RendezVousBusiness();

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<RendezVous> liste = rendezVousBusiness.getListeRendezVous();
        return Response.ok(liste)
                .header("Access-Control-Allow-Origin", "*")
                .build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") int id) {
        RendezVous rdv = rendezVousBusiness.getRendezVousById(id);
        if (rdv != null) {
            return Response.ok(rdv)
                    .header("Access-Control-Allow-Origin", "*")
                    .build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Rendez-vous non trouvé")
                    .build();
        }
    }

    @GET
    @Path("/logement/{ref}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByLogementReference(@PathParam("ref") int reference) {
        List<RendezVous> liste = rendezVousBusiness.getListeRendezVousByLogementReference(reference);
        return Response.ok(liste)
                .header("Access-Control-Allow-Origin", "*")
                .build();
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addRendezVous(RendezVous rendezVous) {
        boolean added = rendezVousBusiness.addRendezVous(rendezVous);
        if (added) {
            return Response.status(Response.Status.CREATED)
                    .entity("Rendez-vous ajouté avec succès")
                    .header("Access-Control-Allow-Origin", "*")
                    .build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Erreur lors de l'ajout : logement non trouvé")
                    .build();
        }
    }

    @PUT
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateRendezVous(@PathParam("id") int id, RendezVous updatedRendezVous) {
        boolean updated = rendezVousBusiness.updateRendezVous(id, updatedRendezVous);
        if (updated) {
            return Response.ok("Rendez-vous mis à jour avec succès")
                    .header("Access-Control-Allow-Origin", "*")
                    .build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Rendez-vous ou logement non trouvé")
                    .build();
        }
    }

    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteRendezVous(@PathParam("id") int id) {
        boolean deleted = rendezVousBusiness.deleteRendezVous(id);
        if (deleted) {
            return Response.ok("Rendez-vous supprimé avec succès")
                    .header("Access-Control-Allow-Origin", "*")
                    .build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Rendez-vous non trouvé")
                    .build();
        }
    }
}
