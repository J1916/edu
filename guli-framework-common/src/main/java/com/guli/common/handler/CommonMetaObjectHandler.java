package com.guli.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.sun.istack.internal.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.poi.hssf.record.DVALRecord;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;


/**
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * 　　┃　　　┃神兽保佑
 * 　　┃　　　┃代码无BUG！
 * 　　┃　　　┗━━━┓
 * 　　┃　　　　　　　┣┓
 * 　　┃　　　　　　　┏┛
 * 　　┗┓┓┏━┳┓┏┛
 * 　　　┃┫┫　┃┫┫
 * 　　　┗┻┛　┗┻┛
 * 自动填充处理器,插入记录时，自动填充一些默认的字段值，或者一些时间字段
 *
 * @author 1916
 * @create 2020-01-05 12:56
 */
@Component
@Slf4j
public class CommonMetaObjectHandler implements MetaObjectHandler {

    private Logger logger = Logger.getLogger(CommonMetaObjectHandler.class);

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        System.out.println(new Date());
        this.setFieldValByName("gmtCreate", new Date(), metaObject); //插入数据时mybatis plus会自动填充该字段
        this.setFieldValByName("gmtModified", new Date(), metaObject);//插入数据时mybatis plus会自动填充该字段
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        this.setFieldValByName("gmtModified", new Date(), metaObject);//插入数据时mybatis plus会自动填充该字段
    }


}
