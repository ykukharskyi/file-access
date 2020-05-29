package com.kpi.fileaccess.repositories;

import com.kpi.fileaccess.domain.UserGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserGroupRepository extends CrudRepository<UserGroup, Long> {
}
