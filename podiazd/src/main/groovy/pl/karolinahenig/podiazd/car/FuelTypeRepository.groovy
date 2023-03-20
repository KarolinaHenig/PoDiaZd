package pl.karolinahenig.podiazd.car

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface FuelTypeRepository extends CrudRepository<FuelType, Long> {
    List<FuelType> findAllByBodyTypeId(long bodyTypeId)
}