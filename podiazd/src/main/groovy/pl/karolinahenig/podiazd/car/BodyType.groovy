package pl.karolinahenig.podiazd.car

import com.fasterxml.jackson.annotation.JsonIgnore

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
class BodyType {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id
    private  String bodyTypeName

    @ManyToOne
    @JsonIgnore
    private Generation generation

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

    Generation getGeneration() {
        return generation
    }

    void setGeneration(Generation generation) {
        this.generation = generation
    }
}
