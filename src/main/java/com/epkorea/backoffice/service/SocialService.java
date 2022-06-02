package com.epkorea.backoffice.service;

import com.epkorea.backoffice.dto.SocialFormRequestDto;
import com.epkorea.backoffice.dto.SocialFormResponseDto;
import com.epkorea.backoffice.dto.SocialListPageDto;
import com.epkorea.backoffice.dto.SocialResponseDto;
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

    public SocialResponseDto getSocialList(int currentPage, String condition, String kwd) {
        int PAGE_WEIGHT = 1;
        int PAGE_LENGTH = 10;
        Page<SocialListPageDto> socialListPageDto = socialRepository.findAllBySearchCondition(condition, kwd, PageRequest.of(currentPage - PAGE_WEIGHT, PAGE_LENGTH));

        return SocialResponseDto.createSocialResponse(currentPage - PAGE_WEIGHT, socialListPageDto);
    }

    public SocialFormResponseDto findSocial(Long socialId) {
        SocialContribution socialContribution = socialRepository.findById(socialId).get();
        return SocialFormResponseDto.getSocialFrom(socialContribution);
    }

    @Transactional
    public Long createSocial(SocialFormRequestDto requestDto, String userid) throws IOException {
        Long sid = requestDto.getSid();
        String title = requestDto.getTitle();
        String content = requestDto.getContent();
        boolean isShow = requestDto.getIsShow().equals("Y");
        LocalDate showDate = LocalDate.parse(requestDto.getShowDate());
        String originImagePath = null;
        String imagePath = null;
        MultipartFile picture = requestDto.getPicture();

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
            throw new InvalidObjectException("파일이 존재하지 않습니다.");
        }

    }

    private String extensionValidator(MultipartFile picture) {
        String contentType = picture.getContentType();

        if (contentType == null) {
            throw new IllegalArgumentException("잘못된 형식의 파일입니다.");
        }
        if (contentType.contains("image/jpeg")) {
            return ".jpg";
        } else if (contentType.contains("image/png")) {
            return ".png";
        } else if (contentType.contains("image/gif")) {
            return ".gif";
        } else {
            throw new IllegalArgumentException("지원하지 않는 확장자입니다.");
        }
    }
}
