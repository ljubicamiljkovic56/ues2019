package ues.projekat.app.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ues.projekat.app.model.Attachment;
import ues.projekat.dto.AttachmentDTO;
import ues.projekat.service.intrfc.AttachmentServiceInterface;

import ues.projekat.y.search.indexing.IndexerAttachments;

@RestController
@RequestMapping(value = "api/attachments")
public class AttachmentController {
	
	@Autowired
	private AttachmentServiceInterface attachmentServiceInterface;
	
	@GetMapping(value = "/getattach")
	public ResponseEntity<List<AttachmentDTO>> getAttachments() throws IOException {
		List<Attachment> attachments = attachmentServiceInterface.findAll();
		
		if(attachments == null) {
			return new ResponseEntity<List<AttachmentDTO>>(HttpStatus.NOT_FOUND);
		}
		List<AttachmentDTO> attachDTO = new ArrayList<AttachmentDTO>();
		for(Attachment a : attachments) {
			attachDTO.add(new AttachmentDTO(a));
		}
		
		Directory indexDirAttach;
		ResourceBundle rb = ResourceBundle.getBundle("ues.projekat.y.search.indexing.luceneindex");
		indexDirAttach = new SimpleFSDirectory(new File(rb.getString("indexDirAttach")));
		File dataDirAttach = new File(rb.getString("dataDirAttach"));
		IndexerAttachments.index(indexDirAttach, dataDirAttach);
		return new ResponseEntity<List<AttachmentDTO>>(attachDTO, HttpStatus.OK);
		
	}

}
