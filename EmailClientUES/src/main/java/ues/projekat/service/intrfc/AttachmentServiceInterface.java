package ues.projekat.service.intrfc;

import java.util.List;

import ues.projekat.app.model.Attachment;

public interface AttachmentServiceInterface {

	Attachment save(Attachment attachment);
	
	
	
	List<Attachment> findAll();
}
