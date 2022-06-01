package com.epkorea.backoffice.controller;

import com.epkorea.backoffice.dto.SocialResponseDto;
import com.epkorea.backoffice.service.SocialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/social")
@RequiredArgsConstructor
public class SocialController {

    private final SocialService socialService;

    @GetMapping("")
    public ModelAndView showSocialList(
            @RequestParam(value="currentPage", defaultValue = "1") int currentPage,
            @RequestParam(value="condition", defaultValue = "") String condition,
            @RequestParam(value="kwd", defaultValue = "") String kwd,
            ModelAndView modelAndView) {
        SocialResponseDto socialResponseDto = socialService.getSocialList(currentPage, condition, kwd);

        modelAndView.setViewName("social_list");
        modelAndView.addObject("social_list", socialResponseDto.getSocialList());
        modelAndView.addObject("current_page", socialResponseDto.getCurrentPage());
        modelAndView.addObject("total_pages", socialResponseDto.getTotalPages());
        modelAndView.addObject("total_elements", socialResponseDto.getTotalElements());

        return modelAndView;
    }
}
