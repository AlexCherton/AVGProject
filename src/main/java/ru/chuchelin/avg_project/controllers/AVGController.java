package ru.chuchelin.avg_project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.chuchelin.avg_project.dao.AVGReport;

/**
 * @author Chuchelin Aleksey
 */
@Controller
@RequestMapping("/report")
public class AVGController {

    private final AVGReport avgReports;

    @Autowired
    public AVGController(AVGReport avgReports) {
        this.avgReports = avgReports;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("report", avgReports.index());
        return "report/index";
    }

}
