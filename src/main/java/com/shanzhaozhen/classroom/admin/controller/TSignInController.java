package com.shanzhaozhen.classroom.admin.controller;

import com.shanzhaozhen.classroom.admin.service.TSignInService;
import com.shanzhaozhen.classroom.bean.TSignIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/admin")
public class TSignInController {

    @Autowired
    private TSignInService tSignInService;

    @GetMapping("/signins/{signInTaskId}")
    public Page<TSignIn> getTSignInPage(@PathVariable("signInTaskId") Integer signInTaskId, String keyword, Pageable pageable) {
        Page<TSignIn> page = tSignInService.getTSignInPage(signInTaskId, keyword, pageable);
        return page;
    }

    @PostMapping("/signin")
    public Map<String, Object> signInWitFace(@RequestBody TSignIn tSignIn) {
        return tSignInService.signIn(tSignIn);
    }

    @GetMapping("/signin/taskid/{signInTaskId}")
    public Map<String, Object> getSignInDetail(@PathVariable("signInTaskId") Integer signInTaskId) {
        return tSignInService.getTSignInBySignInTaskId(signInTaskId);
    }

}
