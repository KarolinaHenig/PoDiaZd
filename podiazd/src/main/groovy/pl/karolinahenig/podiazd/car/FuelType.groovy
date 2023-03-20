package pl.karolinahenig.podiazd.car

import com.fasterxml.jackson.annotation.JsonIgnore

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
class FuelType {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id
    private  String fuelTypeName

    @ManyToOne
    @JsonIgnore
    private BodyType bodyType

    Long getId() {

        return id
    }

    void setId(Long id) {
        this.id = id
    }

    String getFuelTypeName() {
        return fuelTypeName
    }

    void setFuelTypeName(String fuelTypeName) {
        this.fuelTypeName = fuelTypeName
    }

    BodyType getBodyType() {
        return bodyType
    }

    void setBodyType(BodyType bodyType) {
        this.bodyType = bodyType
    }
}
