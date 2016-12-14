package org.tingeso.grupo4.pa.test;

import static org.junit.Assert.assertNotNull;

import java.util.logging.Logger;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.tingeso.grupo4.pa.model.Producto;
import org.tingeso.grupo4.pa.service.ProductoCreacion;
import org.tingeso.grupo4.pa.util.Resources;

public class ProductoCreacionTest {

	
    @Deployment
    public static Archive<?> createTestArchive() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(Producto.class, ProductoCreacion.class, Resources.class)
                .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                // Deploy our test datasource
                .addAsWebInfResource("test-ds.xml");
    }

    @Inject
    ProductoCreacion productoCreacion;

    @Inject
    Logger log;
    
    @Test
    public void testCreacion() throws Exception {
        Producto newProducto = new Producto();
        newProducto.setNombre("Producto numero 1");
        newProducto.setCantidad(23);
        productoCreacion.crear(newProducto);
        assertNotNull(newProducto.getId());
        log.info(newProducto.getNombre() + " guardado con id " + newProducto.getId());
    }
}
