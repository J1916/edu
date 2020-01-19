package com.guli.common.handler;

import com.guli.common.constants.ResultCodeEnum;
import com.guli.common.exception.GuliException;
import com.guli.common.util.ExceptionUtil;
import com.guli.common.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
 * 统一异常处理类
 *
 * @author 1916
 * @create 2020-01-05 14:02
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public Result error(Exception e){
        logger.info(ExceptionUtil.getMessage(e)); //将日志打印到日志文件中
        return Result.error();
    }


    //自定义全局异常处理
    @ExceptionHandler(GuliException.class)
    public Result error(GuliException e){
        logger.info(ExceptionUtil.getMessage(e)); //将日志打印到日志文件中
        return Result.error().code(e.getCode()).message(e.getMessage());
    }


    /**
     * sql语法异常处理
     * @param e
     * @return
     */
//    @ExceptionHandler(BadSqlGrammarException.class)
//    public Result error(BadSqlGrammarException e){
//        logger.info(ExceptionUtil.getMessage(e)); //将日志打印到日志文件中
//        return Result.setResult(ResultCodeEnum.BAD_SQL_GRAMMAR);
//    }

    /**
     * json转换异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result error(JsonParseException e){
        logger.info(ExceptionUtil.getMessage(e)); //将日志打印到日志文件中
        return Result.setResult(ResultCodeEnum.JSON_PARSE_ERROR);
    }


}
