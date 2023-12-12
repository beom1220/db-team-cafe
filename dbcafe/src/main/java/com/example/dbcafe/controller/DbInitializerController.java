package com.example.dbcafe.controller;

import com.example.dbcafe.domain.order.domain.Menu;
import com.example.dbcafe.domain.order.repository.MenuRepository;
import com.example.dbcafe.domain.order.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dbsetting")
public class DbInitializerController {
    private MenuRepository menuRepository;
    private MenuService menuService;
    @GetMapping("/add")
    public String addMenu(Model model){
        model.addAttribute("menu", new Menu());
        return "menu/add";
    }
    @PostMapping("/add")
    public String saveMenu(@ModelAttribute Menu menu) {
        //menuService.saveMenu(menu);
        return "redirect:/menus";
    }
}
