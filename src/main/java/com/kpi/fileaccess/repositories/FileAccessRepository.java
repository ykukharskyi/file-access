package com.kpi.fileaccess.repositories;

import com.kpi.fileaccess.domain.FileAccess;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileAccessRepository extends CrudRepository<FileAccess, Long> {
}
