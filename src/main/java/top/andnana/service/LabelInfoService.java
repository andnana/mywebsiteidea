package top.andnana.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.andnana.dao.LabelInfoMapper;
import top.andnana.entity.LabelInfo;

import java.util.List;

/**
 * Created by Administrator on 2018/1/2 0002.
 */
@Service
public class LabelInfoService
{
    @Autowired
    LabelInfoMapper labelInfoMapper;
    public void addLabelInfos(List<LabelInfo> labelInfos){
        labelInfoMapper.insertLabelInfos(labelInfos);
    }
}
