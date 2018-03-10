package co.edu.udea.dojospring.controller;


import co.edu.udea.dojospring.exception.ResourceNotFoundException;
import co.edu.udea.dojospring.model.Post;
import co.edu.udea.dojospring.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

//Creamos un CRUD básico

@RestController //Se usa para las interacciones Restful y hacer el mapeo. Nos permite usar las notaciones de mas abajo
@RequestMapping("/api") //URL donde vamos a mandar las peticiones 
public class PostController{
    @Autowired
    PostRepository post; //interface que trae todo de post con JPA repository

    @PostMapping("/Post") //hace lo mismo que request mapping pero se especifica que es el GET
    public Post createPost(@Valid @RequestBody Post post){ //Crea
        return this.post.save(post); //Guarda en el repositorio
    }  

    @GetMapping("/posts")
    public List<Post> getAllPost(){
        return post.findAll();
    }

    @GetMapping("/post/{id}") //Obtener un registro. conseguir un dato pro el id
    public Post getPostById(
        @PathVariable(value = "id") Long postId ){ 
        return post.findById(postId).orElseThrow(() -> new ResourceNotFoundException(
            "Post", "id", postId));
    }

    @PutMapping("/post/{id}") //Actualizar un registro
    public Post updatePost(@PathVariable(value = "id") Long postId, @Valid @RequestBody Post postDetails){
        Post postNote = post.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        postNote.setTitle(postDetails.getTitle());
        postNote.setContent(postDetails.getContent());
        return post.save(postNote);
    }

    @DeleteMapping("/post/{id}")
    public ResponseEntity<?> deletePost(@PathVariable(value = "id") Long postId) {
        Post post = this.post.findById(postId)
            .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId)); 

        this.post.delete(post); //Borra

        return ResponseEntity.ok().build(); //Objeto de respuesta para la solicitud, un 200
    }
}
