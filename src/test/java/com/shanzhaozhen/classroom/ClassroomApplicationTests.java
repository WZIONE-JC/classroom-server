package com.shanzhaozhen.classroom;

import com.shanzhaozhen.classroom.admin.repository.*;
import com.shanzhaozhen.classroom.bean.SysPermission;
import com.shanzhaozhen.classroom.bean.SysRole;
import com.shanzhaozhen.classroom.bean.SysUser;
import com.shanzhaozhen.classroom.bean.TClassRoom;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClassroomApplicationTests {

    @Autowired
    private SysUserRepository sysUserRepository;

    @Autowired
    private SysRoleRepository sysRoleRepository;

    @Autowired
    private SysPermissionRepository sysPermissionRepository;

    @Autowired
    private TClassRoomRepository tClassRoomRepository;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testUser() {
        SysUser sysUser = sysUserRepository.findSysUserByUsername("admin");

        SysRole sysRole = sysRoleRepository.findSysRoleById(1);

        SysPermission sysPermission = sysPermissionRepository.findSysPermissionsById(1);

        System.out.println(sysUser);
    }

    @Test
    public void testJpa() {

        System.out.println();
    }
}
