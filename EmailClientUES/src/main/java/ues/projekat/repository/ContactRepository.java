package ues.projekat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ues.projekat.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {

}
