package com.nguyentuantai_2180601350.Baiktra.controller;

import com.nguyentuantai_2180601350.Baiktra.model.PhongBan;
import com.nguyentuantai_2180601350.Baiktra.service.PhongBanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/phong-bans")
public class PhongBanController {
    @Autowired
    private PhongBanService phongBanService;

    @GetMapping
    public String phongBan(Model model){
        List<PhongBan> phongBans = phongBanService.getAll();
        model.addAttribute("phongBan", phongBans);
        return "phongBan/phong-bans";
    }

    @GetMapping("/add")
    public String addPhongBan(Model model){
        model.addAttribute("phongBan", new PhongBan());
        return "phongBan/add-phong";
    }

    @PostMapping("/add")
    public String add(@Valid PhongBan phongBan, BindingResult result, Model model){
        if (result.hasErrors()){
            model.addAttribute("phongBan", new PhongBan());
            return "phongBan/add-phong";
        }
        phongBanService.add(phongBan);
        return "redirect:/phong-bans";
    }

    @GetMapping("/update/{id}")
    public String updatePhongBanForm(@PathVariable("id") String id, Model model) {
        PhongBan phongBan = phongBanService.getById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid category id: " + id));
        model.addAttribute("phongBan", phongBan);
        return "phongBan/update-phong";
    }

    @PostMapping("/update/{id}")
    public String updatePhongBan(@PathVariable("id") String id, @Valid PhongBan phongBan,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "phongBan/update-phong";
        }
        phongBan.setMaPhong(id); // Ensure the ID is set correctly
        phongBanService.update(phongBan);
        return "redirect:/phong-bans"; // Redirect to the list page after update
    }

    @GetMapping("/delete/{id}")
    public String deletePhongBan(@PathVariable String id){
        phongBanService.deleteById(id);
        return "redirect:/phong-bans";
    }
}