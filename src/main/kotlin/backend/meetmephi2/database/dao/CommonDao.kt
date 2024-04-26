package backend.meetmephi2.database.dao

import backend.meetmephi2.database.entity.AbstractEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.NoRepositoryBean
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
@NoRepositoryBean
interface CommonDao<T : AbstractEntity> : PagingAndSortingRepository<T, Long>, CrudRepository<T, Long> {
    fun findEntityById(id : Long) : T?
}