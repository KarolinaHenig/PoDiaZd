package pl.karolinahenig.podiazd.car

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface BodyTypeRepository extends CrudRepository<BodyType, Long> {
    List<BodyType> findAllByGenerationId(long generationId)
}