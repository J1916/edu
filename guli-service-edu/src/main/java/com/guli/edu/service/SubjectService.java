package com.guli.edu.service;

import com.guli.edu.entity.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guli.edu.vo.SubjectNestedVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author Helen
 * @since 2020-01-05
 */
public interface SubjectService extends IService<Subject> {
    /**
     * 批量导入科目分类到数据库
     *
     * @param file
     * @return
     * @throws Exception
     */
    List<String> batchImport(MultipartFile file) throws Exception;

    //获取一级分类
    Subject getSubjectByTitle(String title);

    //获取二级分类
    Subject getSubjectByTitle(String title, String parentId);

    List<SubjectNestedVo> getsubjectList();
}
