package pl.karolinahenig.podiazd.car

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ModificationRepository extends CrudRepository<Modification, Long> {
    List<Modification> findAllByGenerationId(long generationId)
}