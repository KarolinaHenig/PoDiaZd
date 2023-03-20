package pl.karolinahenig.podiazd.car

import com.fasterxml.jackson.annotation.JsonIgnore

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class Model {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id
    private  String modelName

    @ManyToOne
    @JsonIgnore
    private Brand brand

    Long getId() {
        return id
    }

    void setId(Long id) {
        this.id = id
    }

    String getModelName() {
        return modelName
    }

    void setModelName(String modelName) {
        this.modelName = modelName
    }

    Brand getBrand() {
        return brand
    }

    void setBrand(Brand brand) {
        this.brand = brand
    }

    Model(){}
}
