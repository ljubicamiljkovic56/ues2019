package ues.projekat.y.search.misc;

public class FoundDocumentContact {
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String note;
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
	public String getNote() {
		return note;
	}
	
	public void setNote(String note) {
		this.note = note;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public boolean validate()
	   {
	      return (firstName != null && firstName.length() > 0) 
	         && (lastName != null && lastName.length() > 0)
	         && (email != null && email.length() > 0)
	        ;
	   }

}
