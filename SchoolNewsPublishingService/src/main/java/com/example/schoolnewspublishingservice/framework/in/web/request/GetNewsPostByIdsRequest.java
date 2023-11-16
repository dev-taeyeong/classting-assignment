package com.example.schoolnewspublishingservice.framework.in.web.request;

import java.util.List;

public record GetNewsPostByIdsRequest(

        List<String> ids
) {
}
