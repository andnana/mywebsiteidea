package top.andnana.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.andnana.entity.Label;
import top.andnana.entity.LabelInfo;
import top.andnana.entity.LabelInfoExample;

public interface LabelInfoMapper {
    long countByExample(LabelInfoExample example);

    int deleteByExample(LabelInfoExample example);

    int insert(LabelInfo record);

    int insertSelective(LabelInfo record);

    List<LabelInfo> selectByExample(LabelInfoExample example);

    int updateByExampleSelective(@Param("record") LabelInfo record, @Param("example") LabelInfoExample example);

    int updateByExample(@Param("record") LabelInfo record, @Param("example") LabelInfoExample example);
    public void insertLabelInfos(List<LabelInfo> labelInfos);
}