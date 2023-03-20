package pl.karolinahenig.podiazd.car

import com.fasterxml.jackson.annotation.JsonIgnore

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
class ProductionYear {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id
    private  String productionYearName

    @ManyToOne
    @JsonIgnore
    private EngineCapacity engineCapacity

    Long getId() {
        return id
    }

    void setId(Long id) {
        this.id = id
    }

    String getProductionYearName() {
        return productionYearName
    }

    void setProductionYearName(String productionYearName) {
        this.productionYearName = productionYearName
    }

    EngineCapacity getEngineCapacity() {
        return engineCapacity
    }

    void setEngineCapacity(EngineCapacity engineCapacity) {
        this.engineCapacity = engineCapacity
    }
}
