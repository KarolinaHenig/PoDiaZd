package pl.karolinahenig.podiazd.malfunction

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Category {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id
    private  String categoryName

    Long getId() {
        return id
    }

    void setId(Long id) {
        this.id = id
    }

    String getCategoryName() {
        return categoryName
    }

    void setCategoryName(String categoryName) {
        this.categoryName = categoryName
    }
}
