/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ve.org.bcv.fts.Services.service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author furibe
 */
@javax.ws.rs.ApplicationPath("api")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(ve.org.bcv.fts.Services.service.FtsArchivoRecRESTFacade.class);
        resources.add(ve.org.bcv.fts.Services.service.FtsAuthenticationRESTFacade.class);
        resources.add(ve.org.bcv.fts.Services.service.FtsEstatusArcRESTFacade.class);
        resources.add(ve.org.bcv.fts.Services.service.FtsEstatusProRESTFacade.class);
        resources.add(ve.org.bcv.fts.Services.service.FtsInstAutorizadaRESTFacade.class);
        resources.add(ve.org.bcv.fts.Services.service.FtsRecepcionArch.class);
        resources.add(ve.org.bcv.fts.exception.GenericExceptionMapper.class);
        resources.add(ve.org.bcv.fts.exception.ServicesExceptionMapper.class);
    }
    
}
