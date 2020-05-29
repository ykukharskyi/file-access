package com.kpi.fileaccess.mocks;

import com.kpi.fileaccess.domain.FileAccessRule;
import com.kpi.fileaccess.services.FileAccessRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class FileAccessRulesMock {

    @Autowired
    private FileAccessRuleService fileAccessRuleService;

    @PostConstruct
    public void setUpFileAccessRulesMocks() {
        fileAccessRuleService.create(createFullAccessFileAccessRule());
        fileAccessRuleService.create(createReadOnlyFileAccessRule());
        fileAccessRuleService.create(createUpdateOnlyFileAccessRule());
        fileAccessRuleService.create(createNoAccessFileAccessRule());
    }

    private FileAccessRule createFullAccessFileAccessRule() {
        FileAccessRule rule = new FileAccessRule();
        rule.setTitle("Full Access");
        rule.setCreate(true);
        rule.setRead(true);
        rule.setUpdate(true);
        rule.setDelete(true);
        return rule;
    }

    private FileAccessRule createReadOnlyFileAccessRule() {
        FileAccessRule rule = new FileAccessRule();
        rule.setTitle("Read Only");
        rule.setRead(true);
        return rule;
    }

    private FileAccessRule createUpdateOnlyFileAccessRule() {
        FileAccessRule rule = new FileAccessRule();
        rule.setTitle("Update Only");
        rule.setRead(true);
        rule.setUpdate(true);
        return rule;
    }

    private FileAccessRule createNoAccessFileAccessRule() {
        FileAccessRule rule = new FileAccessRule();
        rule.setTitle("No Access");
        return rule;
    }
}
