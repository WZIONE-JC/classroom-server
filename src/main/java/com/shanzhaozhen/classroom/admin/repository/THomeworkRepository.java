package com.shanzhaozhen.classroom.admin.repository;

import com.shanzhaozhen.classroom.bean.THomework;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface THomeworkRepository extends JpaRepository<THomework, Integer> {

    THomework findTHomeworkById(Integer id);

    THomework findTHomeworkByCreaterIdAndHomeworkTaskId(Integer createrId, Integer homeworkTaskId);


    @Query("select new THomework(h, u.sysUserInfo.fullName, u.sysUserInfo.number, u.sysUserInfo.nickName) " +
            "from TStudent s " +
            "left join SysUser u on u.id = s.studentId " +
            "left join THomeworkTask ht on ht.classId = s.classId " +
            "left join THomework h on h.homeworkTaskId = ht.id and h.createrId = s.studentId " +
            "where ht.id = ?1 and (u.sysUserInfo.fullName like ?2 or u.sysUserInfo.nickName like ?3)")
    Page<THomework> findTHomeworksByHomeworkTaskIdAndKeyword(Integer homeworkTaskId, String keyword1, String keyword2, Pageable pageable);

    @Query("select new THomework(h, u.sysUserInfo.fullName, u.sysUserInfo.number, u.sysUserInfo.nickName) " +
            "from THomework h " +
            "left join SysUser u on u.id = h.createrId " +
            "where h.id = ?1")
    THomework findTHomeworkAndInfoById(Integer id);

    @Query("select new THomework(h, u.sysUserInfo.fullName, u.sysUserInfo.number, u.sysUserInfo.nickName, f) " +
            "from THomework h " +
            "left join TFileInfo f on f.id = h.fileInfoId " +
            "left join SysUser u on u.id = h.createrId " +
            "where h.id = ?1")
    THomework findTHomeworkAndInfoAndFileInfoById(Integer id);


}
