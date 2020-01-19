package com.guli.common.exception;

import com.guli.common.constants.ResultCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
 * 自定义异常类
 *
 * @author 1916
 * @create 2020-01-05 14:07
 */
@Data
@ApiModel("自定义全局异常类")
public class GuliException extends RuntimeException {

    @ApiModelProperty("状态码")
    private Integer code;

    public GuliException(Integer code,String message) {
        super(message);
        this.code=code;
    }

    public GuliException( ResultCodeEnum  resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code=resultCodeEnum.getCode();
    }

    @Override
    public String toString() {
        return "GuliException{" +
                "code=" + code +
                ",message="+this.getMessage()+
                '}';
    }
}
