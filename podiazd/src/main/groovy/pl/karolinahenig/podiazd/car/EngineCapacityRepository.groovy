package pl.karolinahenig.podiazd.car

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface EngineCapacityRepository extends CrudRepository<EngineCapacity, Long> {
    List<EngineCapacity> findAllByFuelTypeId(long fuelTypeId)
}