package com.epkorea.backoffice.service;

import com.epkorea.backoffice.dto.SocialFormRq;
import com.epkorea.backoffice.dto.SocialFormRs;
import com.epkorea.backoffice.dto.SocialPageDto;
import com.epkorea.backoffice.dto.SocialPageRs;
import com.epkorea.backoffice.entity.SocialContribution;
import com.epkorea.backoffice.entity.User;
import com.epkorea.backoffice.repository.SocialRepository;
import com.epkorea.backoffice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SocialService {

    private final SocialRepository socialRepository;
    private final UserRepository userRepository;

    public SocialPageRs getSocialList(int currentPage, String condition, String kwd) {
        int PAGE_WEIGHT = 1;
        int PAGE_LENGTH = 10;
        Page<SocialPageDto> socialListPageDto = socialRepository.findAllBySearchCondition(condition, kwd, PageRequest.of(currentPage - PAGE_WEIGHT, PAGE_LENGTH));

        return SocialPageRs.toDto(socialListPageDto, currentPage - PAGE_WEIGHT);
    }

    public SocialFormRs findSocial(Long socialId) {
        SocialContribution socialContribution = socialRepository.findById(socialId).get();
        return SocialFormRs.toDto(socialContribution);
    }

    @Transactional
    public Long createSocial(SocialFormRq socialFormRq, String userid) throws IOException {
        Long sid = socialFormRq.getSid();
        String title = socialFormRq.getTitle();
        String content = socialFormRq.getContent();
        boolean isShow = socialFormRq.getIsShow().equals("Y");
        LocalDate showDate = LocalDate.parse(socialFormRq.getShowDate());
        String originImagePath = null;
        String imagePath = null;
        MultipartFile picture = socialFormRq.getPicture();

        if (sid  != null && picture == null ) {
            SocialContribution socialContribution = socialRepository.findById(sid).get();
            originImagePath = socialContribution.getOriginImagePath();
            imagePath = socialContribution.getImagePath();
        } else {
            originImagePath = picture.getOriginalFilename();
            imagePath = storePicture(picture);
        }

        User writer = userRepository.findByUserid(userid)
                .orElseThrow(() -> {
                    throw new IllegalStateException("Not Found User");
                });

        SocialContribution socialContribution = SocialContribution.createSocialContribution(
                sid, title, content, originImagePath, imagePath, isShow, showDate, writer);

        return socialRepository.save(socialContribution).getSid();
    }
    private String storePicture(MultipartFile picture) throws IOException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String path = "images/" + simpleDateFormat.format(new Date());
        String absolutePath = new File("").getAbsolutePath() + "\\";

        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }

        if (!picture.isEmpty()) {
            String originalFileExtension = extensionValidator(picture);
            String newFile = System.nanoTime() + originalFileExtension;
            file = new File(absolutePath+ path + "/" + newFile);
            picture.transferTo(file);
            return path + "/" + newFile;
        } else {
            throw new InvalidObjectException("????????? ???????????? ????????????.");
        }

    }

    private String extensionValidator(MultipartFile picture) {
        String contentType = picture.getContentType();

        if (contentType == null) {
            throw new IllegalArgumentException("????????? ????????? ???????????????.");
        }
        if (contentType.contains("image/jpeg")) {
            return ".jpg";
        } else if (contentType.contains("image/png")) {
            return ".png";
        } else if (contentType.contains("image/gif")) {
            return ".gif";
        } else {
            throw new IllegalArgumentException("???????????? ?????? ??????????????????.");
        }
    }
}
