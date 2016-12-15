package org.tingeso.grupo4.pa.model;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@SuppressWarnings("serial")
@Entity
@XmlRootElement
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "nombre_producto"))
public class Producto implements Serializable {

	@Id
    @GeneratedValue
    @Column(name = "id_producto")
	private int id;
	
    @NotNull
    @NotEmpty
    @Column(name = "nombre_producto")
	private String nombre;
	
    @NotNull
    @Min(0)
    @Max(10000000)
    private long precio;
	
    @NotNull
    @NotEmpty
    private String ciudad;
    
    @NotNull
    @NotEmpty
    private String telefono;
    
    
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public long getPrecio() {
		return this.precio;
	}

	public void setPrecio(long precio) {
		this.precio = precio;
	}
   
}
