package pl.karolinahenig.podiazd.car

import com.fasterxml.jackson.annotation.JsonIgnore

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
class EngineCapacity {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id
    private  String engineCapacityName

    @ManyToOne
    @JsonIgnore
    private FuelType fuelType

    Long getId() {
        return id
    }

    void setId(Long id) {
        this.id = id
    }

    String getEngineCapacityName() {
        return engineCapacityName
    }

    void setEngineCapacityName(String engineCapacityName) {
        this.engineCapacityName = engineCapacityName
    }

    FuelType getFuelType() {
        return fuelType
    }

    void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType
    }
}
