package com.epkorea.backoffice.controller;

import com.epkorea.backoffice.dto.SocialFormRequestDto;
import com.epkorea.backoffice.dto.SocialFormResponseDto;
import com.epkorea.backoffice.dto.SocialResponseDto;
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
        SocialResponseDto socialResponseDto = socialService.getSocialList(currentPage, condition, kwd);

        modelAndView.setViewName("social_list");
        modelAndView.addObject("social_list", socialResponseDto.getSocialList());
        modelAndView.addObject("current_page", socialResponseDto.getCurrentPage());
        modelAndView.addObject("total_pages", socialResponseDto.getTotalPages());
        modelAndView.addObject("total_elements", socialResponseDto.getTotalElements());

        return modelAndView;
    }

    @GetMapping("/new")
    public String createSocialForm() {
        return "social_new";
    }

    @PostMapping("/new")
    public String createNewSocial(SocialFormRequestDto socialFormRequestDto, @AuthenticationPrincipal User principal) throws IOException {
        socialService.createSocial(socialFormRequestDto, principal.getUsername());

        return "redirect:/social";
    }

    @GetMapping("/{socialId}/edit")
    public String updateSocial(@PathVariable("socialId") Long SocialId, Model model) {
        SocialFormResponseDto socialForm = socialService.findSocial(SocialId);
        model.addAttribute("social_form", socialForm);

        return "social_new";
    }

    @PostMapping("/{socialId}/edit")
    public String updateSocial(SocialFormRequestDto socialFormRequestDto, @AuthenticationPrincipal User principal, @PathVariable("socialId") Long SocialId) throws IOException {
        socialService.createSocial(socialFormRequestDto, principal.getUsername());
        return "redirect:/social";
    }
}
