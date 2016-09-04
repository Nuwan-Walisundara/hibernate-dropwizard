/**
 * Example class
 * @author Sebastian Hennebrueder
 * created Jan 16, 2006
 * copyright 2006 by http://www.laliluna.de
 */
package de.laliluna.example;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;

@Entity
@Table(name = "thoney")
public class Honey {

	@Id
	@GenericGenerator(name="gen",strategy="increment")
	@GeneratedValue(generator="gen")
	@Column(name = "ID", unique = true, nullable = false, precision = 15, scale = 0)
	private Integer id;
	
	@Column(name = "fooname" )
	private String name;
	
	@Column(name = "bartaste" )
	private String taste;

	public Honey() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTaste() {
		return taste;
	}

	public void setTaste(String taste) {
		this.taste = taste;
	}

	@Override
	public String toString() {
		return "Honey: "+getId()+" Name: "+getName()+" Taste: "+getTaste();
	}
	
	
}
