package ues.projekat.dto;

import java.io.Serializable;

import ues.projekat.app.model.Photo;

@SuppressWarnings("serial")
public class PhotoDTO implements Serializable {
	
	private Long id;
	private String path;
	
	public PhotoDTO() {
		super();
	}
	
	
	public PhotoDTO(Long id, String path) {
		super();
		this.id = id;
		this.path = path;
	}
	
	public PhotoDTO(Photo photo) {
		this.id = photo.getId();
		this.path = photo.getPath();
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

}