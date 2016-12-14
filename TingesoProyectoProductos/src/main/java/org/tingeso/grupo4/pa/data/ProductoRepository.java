package org.tingeso.grupo4.pa.data;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

import org.tingeso.grupo4.pa.model.Producto;

@ApplicationScoped
public class ProductoRepository {

    @Inject
    private EntityManager em;

    public Producto findById(Long id) {
        return em.find(Producto.class, id);
    }

    public Producto findByNombre(String nombre) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Producto> criteria = cb.createQuery(Producto.class);
        Root<Producto> producto = criteria.from(Producto.class);
        criteria.select(producto).where(cb.equal(producto.get("nombre"), nombre));
        return em.createQuery(criteria).getSingleResult();
    }

    public List<Producto> findAllOrderedByName() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Producto> criteria = cb.createQuery(Producto.class);
        Root<Producto> producto = criteria.from(Producto.class);
        criteria.select(producto).orderBy(cb.asc(producto.get("nombre")));
        return em.createQuery(criteria).getResultList();
    }
}
