package backend.meetmephi2.database.dao

import backend.meetmephi2.database.entity.AbstractEntity
import org.springframework.data.repository.PagingAndSortingRepository

interface CommonDao<T : AbstractEntity> : PagingAndSortingRepository<T, Long>