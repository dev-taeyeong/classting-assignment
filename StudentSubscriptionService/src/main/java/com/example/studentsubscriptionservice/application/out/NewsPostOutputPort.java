package com.example.studentsubscriptionservice.application.out;

import com.example.studentsubscriptionservice.application.in.dto.NewsPostDto;

import java.util.List;

public interface NewsPostOutputPort {

    List<NewsPostDto> getNewsPostDtosBySchoolPageIds(List<Long> schoolPageIds);
}
