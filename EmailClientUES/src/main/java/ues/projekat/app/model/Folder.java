package ues.projekat.app.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "folders")
public class Folder implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "folder_id")
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_folder", referencedColumnName = "folder_id", nullable = true)
	private Folder parentFolder;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "folder")
	private List<Message> folderMessages;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Folder> subfolders = new ArrayList<Folder>();
	
	@ManyToOne
	@JoinColumn(name="account_id", referencedColumnName="account_id", nullable=false)
	private Account account;
	
	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, mappedBy="destination")
	private Set<Rule> rules = new HashSet<Rule>();

	
	public Folder() {
		super();
	}


	public Folder(Long id, String name, Folder parentFolder, List<Message> folderMessages, List<Folder> subfolders,
			Account account, Set<Rule> rules) {
		super();
		this.id = id;
		this.name = name;
		this.parentFolder = parentFolder;
		this.folderMessages = folderMessages;
		this.subfolders = subfolders;
		this.account = account;
		this.rules = rules;
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

	public List<Message> getFolderMessages() {
		return folderMessages;
	}

	public void setFolderMessages(ArrayList<Message> folderMessages) {
		this.folderMessages = folderMessages;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Set<Rule> getRules() {
		return rules;
	}

	public void setRules(Set<Rule> rules) {
		this.rules = rules;
	}

	public List<Folder> getSubfolders() {
		return subfolders;
	}


	public void setSubfolders(List<Folder> subfolders) {
		this.subfolders = subfolders;
	}


	public void setFolderMessages(List<Message> folderMessages) {
		this.folderMessages = folderMessages;
	}


	@Override
	public String toString() {
		return "Folder [id=" + id + ", name=" + name + ", parentFolder=" + parentFolder + ", folderMessages="
				+ folderMessages + ", subfolders=" + subfolders + ", account=" + account + ", rules=" + rules + "]";
	}



}