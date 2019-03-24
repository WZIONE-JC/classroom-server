package com.shanzhaozhen.classroom.admin.service.impl;

import com.shanzhaozhen.classroom.admin.repository.TStudentRepository;
import com.shanzhaozhen.classroom.admin.service.SysUserService;
import com.shanzhaozhen.classroom.admin.service.TStudentService;
import com.shanzhaozhen.classroom.bean.SysUser;
import com.shanzhaozhen.classroom.bean.TStudent;
import com.shanzhaozhen.classroom.utils.UserDetailsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
public class TStudentServiceImpl implements TStudentService {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private TStudentRepository tStudentRepository;

    @Override
    public Page<TStudent> getTStudentPage(Integer classId, String keyword, Pageable pageable) {
        keyword = "%" + keyword + "%";
        return tStudentRepository.findPageTStudentsByClassIdAndKeyword(classId, keyword, keyword, pageable);
    }

    @Override
    @Transactional
    public Map<String, Object> joinClass(Integer classId) {
        Map<String, Object> map = new HashMap<>();
        String username = UserDetailsUtils.getUsername();
        SysUser sysUser = sysUserService.getSysUserByUsername(username);
        if (sysUser == null) {
            map.put("success", false);
            map.put("msg", "加入失败，没有找到该操作对应的用户");
            return map;
        }
        TStudent tStudent = tStudentRepository.findTStudentByClassIdAndStudentId(classId, sysUser.getId());
        if (tStudent == null) {
            tStudent = new TStudent();
            tStudent.setClassId(classId);
            tStudent.setStudentId(sysUser.getId());
            tStudentRepository.save(tStudent);
            map.put("success", true);
            map.put("msg", "加入成功");
            return map;

        } else {
            map.put("success", false);
            map.put("msg", "请勿重复加入");
            return map;
        }
    }

    @Override
    @Transactional
    public Map<String, Object> moveOutOfClass(Integer id) {
        Map<String, Object> map = new HashMap<>();

        TStudent tStudent = tStudentRepository.findTStudentById(id);

        if (tStudent == null) {
            map.put("success", false);
            map.put("msg", "该同学不在该班级或不存在");
            return map;
        }

        tStudentRepository.deleteById(id);

        map.put("success", true);
        map.put("msg", "移出班级成功");
        return map;
    }

    @Override
    @Transactional
    public Map<String, Object> leaveClass(Integer classId, Integer studentId) {
        Map<String, Object> map = new HashMap<>();

        TStudent tStudent = tStudentRepository.findTStudentByClassIdAndStudentId(classId, studentId);

        if (tStudent == null) {
            map.put("success", false);
            map.put("msg", "该同学不在该班级或不存在");
            return map;
        }

        tStudentRepository.delete(tStudent);

        map.put("success", true);
        map.put("msg", "退出班级成功");
        return map;
    }

    @Override
    @Transactional
    public void removeAllStudentByClassId(Integer classId) {
        tStudentRepository.deleteByClassId(classId);
    }
}
