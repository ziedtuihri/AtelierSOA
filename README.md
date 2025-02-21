# Exercice : Exposition des Web Services avec JAX-RS

## Objectif
L'objectif de cet exercice est de développer une API REST en utilisant **JAX-RS** pour exposer les services web associés aux entités **Logement** et **Rendez-vous**. Ensuite, vous allez implémenter une documentation API avec **Swagger**.

---

## Étapes à suivre

### 1. Mise en place du Backend avec JAX-RS
- Créez une application JAX-RS
- Implémentez les classes **LogementResource** et **RendezVousResource** pour exposer les endpoints REST.
- Assurez-vous que votre application est correctement déployée sur un serveur d'application compatible (TomEE, WildFly, GlassFish, etc.).
- Utilisez **JPA** pour la persistance des entités.

#### Exemple d'API pour l'entité Logement
```java
@Path("/logements")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LogementResource {
    
    @GET
    public List<Logement> getAllLogements() {
        // Retourne la liste des logements depuis la base de données
    }
    
    @POST
    public Response addLogement(Logement logement) {
        // Ajoute un logement dans la base de données
    }
}
```

### 3. Documentation avec Swagger
- Intégrez Swagger à votre projet pour documenter les services REST.
- Ajoutez les annotations Swagger à vos classes de ressource JAX-RS.
- Assurez-vous que la documentation Swagger est accessible via l'URL **/swagger-ui**.

#### Dépendance Maven pour Swagger (si applicable)
```xml
<dependency>
    <groupId>io.swagger</groupId>
    <artifactId>swagger-jaxrs</artifactId>
    <version>1.5.22</version>
</dependency>
```

---

## Critères d'évaluation
✅ Web Services REST bien exposés avec JAX-RS.  
✅ Documentation API complète avec Swagger.  
✅ Bonne structuration du code et respect des bonnes pratiques.  

---

Bonne chance ! 🚀
