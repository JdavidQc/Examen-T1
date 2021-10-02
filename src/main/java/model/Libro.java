package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "libro")
@Getter
@Setter
public class Libro {

    @Id
	private int id;
	private String nombre ;
	private String cate;
	private int n_hoja ;
	private String fecha_edicion ;
	private int id_autor; 
	
	
	
	public Libro(int id, String nombre, String cate, int n_hoja, String fecha_edicion, int id_autor) {
	
		this.id = id;
		this.nombre = nombre;
		this.cate = cate;
		this.n_hoja = n_hoja;
		this.fecha_edicion = fecha_edicion;
		this.id_autor = id_autor;
	}
	
	public Libro() {
		
	}


}
