package ues.projekat.entity;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "folders")
public class Folder implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_folder", referencedColumnName = "folder_id", nullable = true)
	private Folder parentFolder;
	
	private ArrayList<Message> folderMessages;
	
	public Folder() {
		super();
	}

	public Folder(Long id, String name, Folder parentFolder, ArrayList<Message> folderMessages) {
		super();
		this.id = id;
		this.name = name;
		this.parentFolder = parentFolder;
		this.folderMessages = folderMessages;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Folder getParentFolder() {
		return parentFolder;
	}

	public void setParentFolder(Folder parentFolder) {
		this.parentFolder = parentFolder;
	}

	public ArrayList<Message> getFolderMessages() {
		return folderMessages;
	}

	public void setFolderMessages(ArrayList<Message> folderMessages) {
		this.folderMessages = folderMessages;
	}

	@Override
	public String toString() {
		return "Folder [id=" + id + ", name=" + name + ", parentFolder=" + parentFolder + ", folderMessages="
				+ folderMessages + "]";
	}
	
	
}
