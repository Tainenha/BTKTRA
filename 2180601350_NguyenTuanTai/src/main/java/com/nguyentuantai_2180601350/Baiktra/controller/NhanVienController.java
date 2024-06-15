package com.nguyentuantai_2180601350.Baiktra.controller;

import com.nguyentuantai_2180601350.Baiktra.model.NhanVien;
import com.nguyentuantai_2180601350.Baiktra.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/nhanviens")
public class NhanVienController {

    @Autowired
    private NhanVienService nhanVienService;

    @GetMapping
    public String getAllNhanViens(@RequestParam(defaultValue = "0") int page, Model model) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<NhanVien> nhanVienPage = nhanVienService.getAllNhanViens(pageable);

        // Tránh gọi getContent() nhiều lần, lưu kết quả vào biến
        var nhanViens = nhanVienPage.getContent();

        model.addAttribute("nhanviens", nhanViens);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", nhanVienPage.getTotalPages());
        return "nhanvien/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("nhanvien", new NhanVien());
        return "nhanvien/new";
    }

    @PostMapping
    public String createNhanVien(@ModelAttribute("nhanvien") NhanVien nhanVien) {
        nhanVienService.createNhanVien(nhanVien);
        return "redirect:/nhanviens";
    }

    @GetMapping("/edit/{maNV}")
    public String showEditForm(@PathVariable String maNV, Model model) {
        Optional<NhanVien> nhanVienOptional = nhanVienService.getNhanVienById(maNV);

        // Sử dụng Optional để xử lý trường hợp không tìm thấy nhân viên
        if (nhanVienOptional.isPresent()) {
            model.addAttribute("nhanvien", nhanVienOptional.get());
            return "nhanvien/edit";
        } else {
            // Xử lý lỗi khi không tìm thấy nhân viên, ví dụ:
            model.addAttribute("errorMessage", "Không tìm thấy nhân viên có mã " + maNV);
            return "error"; // Hoặc trang hiển thị lỗi khác
        }
    }

    @PostMapping("/update/{maNV}")
    public String updateNhanVien(@PathVariable String maNV, @ModelAttribute("nhanvien") NhanVien nhanVienDetails) {
        nhanVienService.updateNhanVien(maNV, nhanVienDetails);
        return "redirect:/nhanviens";
    }

    @GetMapping("/delete/{maNV}")
    public String deleteNhanVien(@PathVariable String maNV) {
        nhanVienService.deleteNhanVien(maNV);
        return "redirect:/nhanviens";
    }
}