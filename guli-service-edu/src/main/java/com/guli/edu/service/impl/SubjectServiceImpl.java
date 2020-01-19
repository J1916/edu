package com.guli.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guli.common.util.ExcelImportUtil;
import com.guli.edu.entity.Subject;
import com.guli.edu.mapper.SubjectMapper;
import com.guli.edu.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guli.edu.vo.SubjectNestedVo;
import com.guli.edu.vo.SubjectVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.record.DVALRecord;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.stream.IIOByteBuffer;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author Helen
 * @since 2020-01-05
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    /**
     * 批量导入课程一二级分类
     *
     * @param file
     * @return 返回没有一、二级科目分类的错误列表
     * @throws Exception
     */
    @Transactional
    @Override
    public List<String> batchImport(MultipartFile file) throws Exception {
        //错误消息列表
        List<String> errorMsg = new ArrayList<>();

        //创建工具类对象
        ExcelImportUtil excelHSSFUtil = new ExcelImportUtil(file.getInputStream());

        //获取工作列表
        HSSFSheet sheet = excelHSSFUtil.getSheet();

        int total = sheet.getPhysicalNumberOfRows(); //总行数

//        遍历获取每行的数据
        for (int i = 1; i <= total; i++) {
            HSSFRow row = sheet.getRow(i); //获取每行的数据

            if (row == null) {
                continue;
            }


            //获取一级分类
            String levelOneValue = null;
            Cell levelOneCell = row.getCell(0);//获取一级分类（第一列）
            if (levelOneCell != null) {

                levelOneValue = excelHSSFUtil.getCellValue(levelOneCell).trim();//一级分类

                if (StringUtils.isEmpty(levelOneValue)) {
                    errorMsg.add("第" + i + "行一级分类为空");
                    continue;
                }

                Subject subject = getSubjectByTitle(levelOneValue);
                String parentId = "";
                //判断一级分类是否已经存在
                if (subject == null) {
                    //将一级分类存入数据库
                    Subject oneSubject = new Subject();
                    oneSubject.setTitle(levelOneValue);
                    oneSubject.setSort(i);
                    baseMapper.insert(oneSubject); //添加
                    parentId = oneSubject.getParentId();
                } else {
                    parentId = subject.getParentId();
                }

                //获取二级分类 TODO
                //判断二级分类是否重复 TODO
                //将二级分类存入数据库 TODO

                //获取二级分类
                String levelTwoValue = null;
                Cell levelTwoCell = row.getCell(1);//获取二级分类（第二列）
                if (levelTwoCell != null) {
                    levelTwoValue = excelHSSFUtil.getCellValue(levelTwoCell).trim();//二级分类

                    if (StringUtils.isEmpty(levelTwoValue)) {
                        errorMsg.add("第" + i + "行二级分类为空");
                        continue;
                    }

                    //判断二级分类是否重复 TODO
                    if (getSubjectByTitle(levelTwoValue, parentId) != null) {
                        continue;
                    }
                    //将二级分类存入数据库 TODO
                    Subject twoSubject = new Subject();
                    twoSubject.setTitle(levelTwoValue);
                    twoSubject.setSort(i);
                    twoSubject.setParentId(parentId);
                    baseMapper.insert(twoSubject); //添加
                }
            }
        }

        return errorMsg;

    }

    /**
     * 根据分类名称查询这个一级分类中否存在
     *
     * @param title 分类名称
     * @return
     */
    @Override
    public Subject getSubjectByTitle(String title) {
        QueryWrapper<Subject> query = new QueryWrapper<>();
        query.eq("title", title); //分类名
        query.eq("parent_id", 0); //一级分类
        return baseMapper.selectOne(query);
    }

    /**
     * 根据分类名称查询这个二级分类中否存在
     *
     * @param title 分类名称
     * @return
     */
    @Override
    public Subject getSubjectByTitle(String title, String parentId) {
        QueryWrapper<Subject> query = new QueryWrapper<>();
        query.eq("title", title); //分类名
        query.eq("parent_id", parentId); //一级分类
        return baseMapper.selectOne(query);
    }

    @Override
    public List<SubjectNestedVo> getsubjectList() {
        QueryWrapper<Subject> query = new QueryWrapper<>();
        query.eq("parent_id", 0);  //先查询一级分类
        query.orderByAsc("sort", "id"); //排序
        List<Subject> subjects = baseMapper.selectList(query);


        List<SubjectNestedVo> nestedVoList = new ArrayList<>();


        for (Subject subject : subjects) {
            query = new QueryWrapper<>();
            query.eq("parent_id", subject.getId()); //根据二级分类的父分类的id查询
            List<Subject> childrenList = baseMapper.selectList(query);
            SubjectNestedVo subjectNestedVo = new SubjectNestedVo();
            subjectNestedVo.setId(subject.getId());
            subjectNestedVo.setTitle(subject.getTitle());
            subjectNestedVo.setChildren(childrenList);
            nestedVoList.add(subjectNestedVo);
        }

        return nestedVoList;
    }

}