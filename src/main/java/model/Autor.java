package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "autor")
@Getter
@Setter
public class Autor {
    @Id
	private int id;
	private String nombreAutor;
	private String dni ;
	


	
}
