package pl.karolinahenig.podiazd.car

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ModelRepository extends CrudRepository<Model, Long>{
   List<Model> findAllByBrandId(long brandId)
}