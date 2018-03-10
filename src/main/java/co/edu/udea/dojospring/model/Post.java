package co.edu.udea.dojospring.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity //Es una entidad
@Table(name = "post") //Es una tabla llamada post
@EntityListeners(AuditingEntityListener.class) //Escuchador
@JsonIgnoreProperties(value = {"createdAt","updatedAt"}, allowGetters = true) //ignora los valores

public class Post {
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String content; //no puede ser Null ni vacio

    @Column(nullable = false, updatable = false) //No puede ser Null ni actualizado
    @Temporal(TemporalType.TIMESTAMP) //Retorna la fecha y la hora
    @CreatedDate //Se llena con la fecha actual cada que se cree
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
