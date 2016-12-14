package org.tingeso.grupo4.pa.controller;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.tingeso.grupo4.pa.model.Producto;
import org.tingeso.grupo4.pa.service.ProductoCreacion;

// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://www.cdi-spec.org/faq/#accordion6
@Model
public class ProductoController {

    @Inject
    private FacesContext facesContext;

    @Inject
    private ProductoCreacion productoCreacion;

    @Produces
    @Named
    private Producto newProducto;

    @PostConstruct
    public void initNewProducto() {
        newProducto = new Producto();
    }

    public void crear() throws Exception {
        try {
            productoCreacion.crear(newProducto);
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Creado!", "Creado satisfactoriamente!");
            facesContext.addMessage(null, m);
            initNewProducto();
        } catch (Exception e) {
            String errorMessage = getRootErrorMessage(e);
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Error al crear.");
            facesContext.addMessage(null, m);
        }
    }

    private String getRootErrorMessage(Exception e) {
        // Default to general error message that registration failed.
        String errorMessage = "Error al crear. Revisar log para mas información.";
        if (e == null) {
            // This shouldn't happen, but return the default messages
            return errorMessage;
        }

        // Start with the exception and recurse to find the root cause
        Throwable t = e;
        while (t != null) {
            // Get the message from the Throwable class instance
            errorMessage = t.getLocalizedMessage();
            t = t.getCause();
        }
        // This is the root cause message
        return errorMessage;
    }

}
