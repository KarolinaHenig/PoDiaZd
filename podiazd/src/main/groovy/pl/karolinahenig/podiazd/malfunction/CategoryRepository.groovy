package pl.karolinahenig.podiazd.malfunction

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import pl.karolinahenig.podiazd.car.BodyType
import pl.karolinahenig.podiazd.car.Generation
import pl.karolinahenig.podiazd.car.Modification

@Repository
interface CategoryRepository extends CrudRepository<Category, Long> {

}