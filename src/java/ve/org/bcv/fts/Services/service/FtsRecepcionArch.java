/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ve.org.bcv.fts.Services.service;

import java.io.InputStream;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import static javax.ws.rs.core.MediaType.MULTIPART_FORM_DATA;
import org.apache.commons.io.IOUtils;
import org.glassfish.jersey.media.multipart.FormDataParam;
import ve.org.bcv.fts.bean.FtsArcPro;
import ve.org.bcv.fts.bean.FtsPropMod;
import ve.org.bcv.fts.bean.FtsTipoArchivo;
import ve.org.bcv.fts.persistence.FtsArcProPersistence;
import ve.org.bcv.fts.persistence.FtsInstAutProModPersistence;
import ve.org.bcv.fts.persistence.FtsPropModPersistence;
import ve.org.bcv.fts.util.Jwt;

/**
 *
 * @author furibe
 */
@Path("ve.org.bcv.fts.bean.ftsarchivorec")
public class FtsRecepcionArch {

//    @PersistenceContext(unitName = "FTSPU")
//    protected EntityManager entityManager;
    private Jwt tokenObject = new Jwt();

    public FtsRecepcionArch() {
    }

    @POST
    @Path("/loadFile/{coPropMod}")
    @Consumes(MULTIPART_FORM_DATA)
    public String loadFile(@Context HttpHeaders headers, @FormDataParam("arcVentas") InputStream is, @PathParam("coPropMod") String coPropMod) {

        FtsPropModPersistence ftsPropModPersistence = null;
        FtsInstAutProModPersistence instAutProModPersistence = null;
        try {
            //Se obtiene el token de autenticacion
            String token = tokenObject.getTokenServiceContext(headers);
            System.out.println("token: " + token);

            //Autenticacion si el token es valido, de no ser asi lanza excepcion con error
            tokenObject.parseJWT();

            ftsPropModPersistence = new FtsPropModPersistence();
            FtsPropMod ftsPropMod = ftsPropModPersistence.findByCoPropMod(coPropMod);
            instAutProModPersistence = new FtsInstAutProModPersistence();
            Integer isAuthorized = instAutProModPersistence.isInstAut(tokenObject.getSubject(), Integer.valueOf(ftsPropMod.getIdPropMod()));

            if (isAuthorized == 0) {
                //Creando variables para la validación 
                
                //Se obtiene la informacion de ser un archivo JSON
                String imFile = IOUtils.toString(is, "UTF-8");
                
                
                
                
                
                FtsArcProPersistence ftsArcProPersistence = new FtsArcProPersistence();
                FtsArcPro ftsArcPro = ftsArcProPersistence.findById(ftsPropMod.getIdPropMod());

                
                FtsTipoArchivo tipoArchivo = ftsArcPro.getIdTipoArchivo();
                String claseValidate = tipoArchivo.getClaseValidate();
                StringBuilder urlClassValidate = new StringBuilder("ve.org.bcv.fts.util.");
                urlClassValidate.append(claseValidate);
                Class<?> validateClass = Class.forName(urlClassValidate.toString());                 
                Object validateFile = validateClass.newInstance();
                
                
                
                
                
              
                
                
                
                

            }

        } catch (Exception e) {
        }

        return null;

    }

//    @POST
//    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    @Transactional
//    public Response create(FtsArchivoRec entity) {
//        entityManager.persist(entity);
//        return Response.created(URI.create(entity.getNuRecepcion().toString())).build();
//    }
//
//    @PUT
//    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    @Transactional
//    public void edit(FtsArchivoRec entity) {
//        entityManager.merge(entity);
//    }
//
//    @DELETE
//    @Path("{id}")
//    @Transactional
//    public void remove(@PathParam("id") String id) {
//        FtsArchivoRec entity = entityManager.getReference(FtsArchivoRec.class, id);
//        entityManager.remove(entity);
//    }
//
//    @GET
//    @Path("{id}")
//    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    @Transactional
//    public FtsArchivoRec find(@PathParam("id") String id) {
//        return entityManager.find(FtsArchivoRec.class, id);
//    }
//
//    @GET
//    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    @Transactional
//    public List<FtsArchivoRec> findAll() {
//        return find(true, -1, -1);
//    }
//
//    @GET
//    @Path("{max}/{first}")
//    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    @Transactional
//    public List<FtsArchivoRec> findRange(@PathParam("max") Integer max, @PathParam("first") Integer first) {
//        return find(false, max, first);
//    }
//
//    @GET
//    @Path("count")
//    @Produces(MediaType.TEXT_PLAIN)
//    @Transactional
//    public String count() {
//        try {
//            Query query = entityManager.createQuery("SELECT count(o) FROM FtsArchivoRec AS o");
//            return query.getSingleResult().toString();
//        } finally {
//            entityManager.close();
//        }
//    }
//
//    private List<FtsArchivoRec> find(boolean all, int maxResults, int firstResult) {
//        try {
//            Query query = entityManager.createQuery("SELECT object(o) FROM FtsArchivoRec AS o");
//            if (!all) {
//                query.setMaxResults(maxResults);
//                query.setFirstResult(firstResult);
//            }
//            return query.getResultList();
//        } finally {
//            entityManager.close();
//        }
//    }
}
