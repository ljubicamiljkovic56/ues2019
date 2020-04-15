package ues.projekat.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@SuppressWarnings("serial")
@Entity                 
@Table(name = "photos")
public class Photo implements Serializable {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "photo_id") 
	private Long id;
	
	
	@Column(name = "path")
	private String path;
	
	public Photo() {
		super();
	}


	public Photo(Long id, String path) {
		super();
		this.id = id;
		this.path = path;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}


	@Override
	public String toString() {
		return "Photo [id=" + id + ", path=" + path + "]";
	}
	
	
	

}
