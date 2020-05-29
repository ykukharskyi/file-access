package com.kpi.fileaccess.repositories;

import com.kpi.fileaccess.domain.FileAccessRule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileAccessRuleRepository extends CrudRepository<FileAccessRule, Long> {
}
