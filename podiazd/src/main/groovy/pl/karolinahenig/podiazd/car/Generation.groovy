package pl.karolinahenig.podiazd.car

import com.fasterxml.jackson.annotation.JsonIgnore

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
class Generation {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id
    private  String generationName

    @ManyToOne
    @JsonIgnore
    private Model model

    Long getId() {
        return id
    }

    void setId(Long id) {
        this.id = id
    }

    String getGenerationName() {
        return generationName
    }

    void setGenerationName(String generationName) {
        this.generationName = generationName
    }

    Model getModel() {
        return model
    }

    void setModel(Model model) {
        this.model = model
    }
}
