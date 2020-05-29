package com.kpi.fileaccess.controllers;

import com.kpi.fileaccess.domain.FileAccessRule;
import com.kpi.fileaccess.services.FileAccessRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/file-access-rules")
public class FileAccessRuleController {

    @Autowired
    private FileAccessRuleService fileAccessRuleService;

    @GetMapping
    public String renderFileAccessRulesList(Model model) {
        model.addAttribute("fileAccessRules", fileAccessRuleService.readAll());
        return "file_access_rules_list";
    }

    @GetMapping("/create")
    public String renderNewFileAccessRuleForm(Model model) {
        model.addAttribute("fileAccessRule", new FileAccessRule());
        return "file_access_rule_form";
    }

    @GetMapping("/edit/{id}")
    public String renderEditFileAccessRuleForm(@PathVariable Long id, Model model) {
        model.addAttribute("fileAccessRule", fileAccessRuleService.read(id));
        return "file_access_rule_form";
    }

    @GetMapping("/delete/{id}")
    public String deleteFileAccessRule(@PathVariable Long id) {
        fileAccessRuleService.delete(id);
        return "redirect:/file-access-rules";
    }

    @PostMapping("/create")
    public String createFileAccessRule(FileAccessRule fileAccessRule) {
        fileAccessRuleService.create(fileAccessRule);
        return "redirect:/file-access-rules";
    }

    @PostMapping("/edit/{id}")
    public String createFileAccessRule(@PathVariable Long id, FileAccessRule fileAccessRule) {
        fileAccessRuleService.edit(id, fileAccessRule);
        return "redirect:/file-access-rules";
    }
}
