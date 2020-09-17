package ues.projekat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ues.projekat.app.model.Attachment;
import ues.projekat.app.repository.AttachmentRepository;
import ues.projekat.service.intrfc.AttachmentServiceInterface;

@Service
public class AttachmentService implements AttachmentServiceInterface {
	
	@Autowired
	private AttachmentRepository attachmentRepository;

	@Override
	public Attachment save(Attachment attachment) {
		return attachmentRepository.save(attachment);
	}

	@Override
	public List<Attachment> findAll() {
		return attachmentRepository.findAll();
	}

}
