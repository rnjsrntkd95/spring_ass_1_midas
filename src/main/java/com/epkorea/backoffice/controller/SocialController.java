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
    public ModelAndView showSocialList(@RequestParam(value="currentPage", defaultValue = "1") int currentPage, ModelAndView modelAndView) {
        SocialResponseDto socialResponseDto = socialService.getSocialList(currentPage);

        modelAndView.setViewName("social_list");
        modelAndView.addObject("socialList", socialResponseDto.getSocialList());
        modelAndView.addObject("currentPage", socialResponseDto.getCurrentPage());
        modelAndView.addObject("totalPages", socialResponseDto.getTotalPages());
        modelAndView.addObject("totalElements", socialResponseDto.getTotalElements());

        return modelAndView;
    }
}
