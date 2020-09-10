package ues.projekat.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ues.projekat.app.model.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {

}
