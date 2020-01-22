package com.guli.edu.service;

import com.guli.edu.entity.Video;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guli.edu.form.VideoInfoForm;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author Helen
 * @since 2020-01-05
 */
public interface VideoService extends IService<Video> {

    void saveVideoInfo(VideoInfoForm videoInfoForm);

    void updateVideoInfoById(VideoInfoForm videoInfoForm);

    VideoInfoForm getVideoInfoById(String id);
}
