package co.edu.udea.dojospring.repository;

import co.edu.udea.dojospring.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //Permite traer todos los métodos, save, findById, etc.. Sólo hay que invocarlo, Spring ya los maneja
public interface PostRepository extends JpaRepository<Post, Long> {

}