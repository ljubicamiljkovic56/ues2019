package ues.projekat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ues.projekat.app.model.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Long> {

}
