package pl.karolinahenig.podiazd.car

import com.fasterxml.jackson.annotation.JsonIgnore

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinTable
import javax.persistence.ManyToMany

@Entity
class BodyType {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id
    private  String bodyTypeName

    @JsonIgnore
    @ManyToMany@JoinTable(name="body_generation")
    private Set<Generation> generations


    Long getId() {
        return id
    }

    void setId(Long id) {
        this.id = id
    }

    String getBodyTypeName() {
        return bodyTypeName
    }

    void setBodyTypeName(String bodyTypeName) {
        this.bodyTypeName = bodyTypeName
    }

    Set<Generation> getGenerations() {
        return generations
    }

    void setGenerations(Set<Generation> generations) {
        this.generations = generations
    }
}
