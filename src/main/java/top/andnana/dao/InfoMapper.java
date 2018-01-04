package top.andnana.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import top.andnana.entity.Info;
import top.andnana.entity.InfoExample;

public interface InfoMapper {
    long countByExample(InfoExample example);

    int deleteByExample(InfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Info record);

    int insertSelective(Info record);

    List<Info> selectByExampleWithBLOBs(InfoExample example);

    List<Info> selectByExample(InfoExample example);

    public Info selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Info record, @Param("example") InfoExample example);

    int updateByExampleWithBLOBs(@Param("record") Info record, @Param("example") InfoExample example);

    int updateByExample(@Param("record") Info record, @Param("example") InfoExample example);

    int updateByPrimaryKeySelective(Info record);

    int updateByPrimaryKeyWithBLOBs(Info record);

    int updateByPrimaryKey(Info record);

    public List<Info> selectAll();

    public Info selectByTitle(String title);

    public void insertInfo(Info info);

    public List<Info> selectByCategoryId(Integer cateid);
}