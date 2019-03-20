package com.shanzhaozhen.classroom.admin.controller;

import com.shanzhaozhen.classroom.admin.service.THomeworkService;
import com.shanzhaozhen.classroom.admin.service.TSignInService;
import com.shanzhaozhen.classroom.bean.THomework;
import com.shanzhaozhen.classroom.bean.TSignIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin")
public class THomeworkController {

    @Autowired
    private THomeworkService tHomeworkService;

    @GetMapping("/homeworks/{homeworkTaskId}")
    public Page<THomework> getTSignInPage(@PathVariable("homeworkTaskId") Integer homeworkTaskId, String keyword, Pageable pageable) {
        Page<THomework> page = tHomeworkService.getTHomeworkPage(homeworkTaskId, keyword, pageable);
        return page;
    }

    @PutMapping("/homework/giveScore/{homeworkId}")
    public Map<String, Object> giveHomeworkScore(@PathVariable("homeworkId") Integer homeworkId, Integer score) {
        return tHomeworkService.giveHomeworkScore(homeworkId, score);
    }

}