package com.epkorea.backoffice.controller;

import com.epkorea.backoffice.dto.SocialFormRq;
import com.epkorea.backoffice.dto.SocialFormRs;
import com.epkorea.backoffice.dto.SocialPageRs;
import com.epkorea.backoffice.service.SocialService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
@RequestMapping(value = "/social")
@RequiredArgsConstructor
public class SocialController {

    private final SocialService socialService;

    @GetMapping("")
    public ModelAndView showSocialList(
            @RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
            @RequestParam(value = "condition", defaultValue = "") String condition,
            @RequestParam(value = "kwd", defaultValue = "") String kwd,
            ModelAndView modelAndView) {
        SocialPageRs socialPageRs = socialService.getSocialList(currentPage, condition, kwd);

        modelAndView.setViewName("social_list");
        modelAndView.addObject("social_list", socialPageRs.getSocialList());
        modelAndView.addObject("current_page", socialPageRs.getCurrentPage());
        modelAndView.addObject("total_pages", socialPageRs.getTotalPages());
        modelAndView.addObject("total_elements", socialPageRs.getTotalElements());

        return modelAndView;
    }

    @GetMapping("/new")
    public String createSocialForm(Model model) {
        model.addAttribute("social_form", new SocialFormRq());
        return "social_new";
    }

    @PostMapping("/new")
    public String createNewSocial(SocialFormRq socialFormRq, @AuthenticationPrincipal User principal) throws IOException {
        socialService.createSocial(socialFormRq, principal.getUsername());

        return "redirect:/social";
    }

    @GetMapping("/{socialId}/edit")
    public String updateSocial(@PathVariable("socialId") Long SocialId, Model model) {
        SocialFormRs socialFormRs = socialService.findSocial(SocialId);
        model.addAttribute("social_form", socialFormRs);

        return "social_new";
    }
}
