/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *
 */
package service;

import cst8218.bans0027.entity.Sprite;
import java.util.List;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
/**
 *
 * @author harsh
 * Student Number - 041005659
 * This class inherits from the Abstract Facade class of type Sprite. It serves the client's transactions by responding to certain HTTP requests. It implements the RESTful API
 * In this case there is no need of JSF -- CRUD pages because a computer program. Information is represented in JSON or XML which can be parsed by the computer directly instead of 
 * being rendered in a field on a page for a human’s eyes to see. The clients/users/programmers/operators use a certain tool such as Postman to interact with all types of HTTP 
 * requests and respond accordingly
 * Running this code in the tool Postman where the client can 
 * -> get the sprite of particular id
 * -> get all the sprites -- implementation of findAll() method
 * -> replace the sprite entity of a particular id using the PUT request (PUT on a specific id
 * -> update the sprite entity of a particular id using the POST request (POST on a specific id)
 * -> update the sprite entity through a root resource using the POST request (POST on the root resource)
 */
@Stateless
@DeclareRoles({"Admin", "RestGroup"})
@RolesAllowed({"Admin", "RestGroup"})
@Path("cst8218.bans0027.entity.sprite")
public class SpriteFacadeREST extends AbstractFacade<Sprite> {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    /**
     * Constructor of the class. It inherits the constructor of Sprite entity
     */
    public SpriteFacadeREST() {
        super(Sprite.class);
    }

    /**
     * This si the first type of HTTP request response structire. In this case of POST on the root resource the id of the entity is taken from the body of the request. If the id 
     * is null then a new sprite entity is created. If the id is not null and exists in the database, then the entity values are updated as mentioned in the request. If the id is 
     * not null and it also does not exists in the database, then it is a bad request(response code 400)
     * @param entity -- taken from the body of the request
     * @return -- returns the response according to the condition return type is Response
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response updateSpritePost(Sprite entity) {
       // Sprite s = super.find(entity.getId());
//        for(Sprite s: super.findAll()){
//            if(s.getId() == entity.getId()){
//                s.updateSprite(entity, s);
//                return Response.status(Response.Status.CREATED).entity("All good").build();
//            }
//        }
        Sprite s = super.find(entity.getId());
        if(s != null){
            s.updateSprite(entity, s);
            return Response.status(Response.Status.CREATED).entity("Entity updates successsfully").build();
        }
        if(entity.getId() == null){
            super.create(entity);
            return Response.status(Response.Status.CREATED).entity("Entity created successfully").build();
        }  
        else{
            return Response.status(Response.Status.BAD_REQUEST).entity("Cannot proceed request").build();
        }
    }
    
    
    
    /**
     * POST on to the specific id
     * In this case it updates the sprite entity as mentioned in the URL of the request. If the sprite with the mentioned id does not exist in the database then, it is a bad 
     * request; otherwise if the sprite exists then it updates the entity values as mentioned in the body of the request
     * @param id -- of type Long and it is the id of the entity written in the request URL
     * @param entity -- the entity mentioned in the body of the request
     * @return 
     */
    @POST
    @Override
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response updateSprite(@PathParam("id") Long id, Sprite entity){
        Sprite s = super.find(id);
        if(s==null){
            return Response.status(Response.Status.BAD_REQUEST).entity("Cannot process request").build();
        }
        else{
            s.updateSprite(entity, s);
            return Response.ok().entity(s).build();
            
        }
    }

    
    public void edit(@PathParam("id") Long id, Sprite entity) {        
        super.edit(entity);
    }

    /**
     * PUT on a specific id:- as it will replace the specidic entity with the mentioned body of the request
     * @param id -- the param id
     * @param entity --  body of the request
     * @return the response generated
     */
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response replaceSprite(@PathParam("id") Long id, Sprite entity){
        Sprite s = super.find(id);
        if(s==null){
            return Response.status(Response.Status.BAD_REQUEST).entity("Cannot process request").build();
                    
        }
              
        if(entity.getId()!= s.getId()){
            return Response.status(Response.Status.BAD_REQUEST).entity("Cannot proceed").build();
        }
        
        
        else{
            s.replaceSprite(entity, s);
            return Response.ok("Entity created").build(); 
        }
       
  
    }
    /**
     * deletes the entity
     * @param id -- the entity id to be deleted
     */
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    /**
     * gets the entity of a given id
     * @param id -- the id of the entity
     * @return -- the entity Sprite
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Sprite find(@PathParam("id") Long id) {
        return super.find(id);
    }

    /**
     * Lists all the entities
     * @return return type os List<Sprite>
     */
    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Sprite> findAll() {
        return super.findAll();
    }

    /**
     * gets the entities under a specific range
     * @param from -- lowest value of id
     * @param to -- the entities upto where the user wants
     * @return 
     */
    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Sprite> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }
    
    /**
     * counts the number of entities
     * @return -- the string which is the number fo entities in database
     */
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    /**
     * 
     * @return the type entity manager
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    
}
