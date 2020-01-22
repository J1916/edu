package com.guli.edu.service.impl;

import com.guli.edu.entity.Video;
import com.guli.edu.form.VideoInfoForm;
import com.guli.edu.mapper.VideoMapper;
import com.guli.edu.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author Helen
 * @since 2020-01-05
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {


    @Override
    public void saveVideoInfo(VideoInfoForm videoInfoForm) {

        Video video = new Video();
        BeanUtils.copyProperties(videoInfoForm,video);
        baseMapper.insert(video);
    }

    @Override
    public void updateVideoInfoById(VideoInfoForm videoInfoForm) {
        Video video = new Video();
        BeanUtils.copyProperties(videoInfoForm,video);
        baseMapper.updateById(video);
    }

    @Override
    public VideoInfoForm getVideoInfoById(String id) {
        Video video = baseMapper.selectById(id);
        VideoInfoForm videoInfoForm = new VideoInfoForm();
        BeanUtils.copyProperties(video,videoInfoForm);
        return videoInfoForm;
    }
}
