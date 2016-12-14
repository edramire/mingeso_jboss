package org.tingeso.grupo4.pa.service;

import org.tingeso.grupo4.pa.model.Producto;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.logging.Logger;

@Stateless
public class ProductoCreacion {

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;

    @Inject
    private Event<Producto> productoEventSrc;

    public void crear(Producto producto) throws Exception {
        log.info("Registering " + producto.getNombre());
        em.persist(producto);
        productoEventSrc.fire(producto);
    }
}
