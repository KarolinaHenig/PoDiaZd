package pl.karolinahenig.podiazd.car

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
class Brand {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id
    @Column(unique = true)
    private String brandName
    @OneToMany (fetch = FetchType.LAZY, mappedBy = 'brand')
    private Set<Model> models = []

    Brand(){}

    Long getId() {
        return id
    }

    void setId(Long id) {
        this.id = id
    }

    String getBrandName() {
        return brandName
    }

    void setBrandName(String brandName) {
        this.brandName = brandName
    }

    Set<Model> getModels() {
        return models
    }

    void setModels(Set<Model> models) {
        this.models = models
    }
}
