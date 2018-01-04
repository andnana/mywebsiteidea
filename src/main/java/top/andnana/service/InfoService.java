package top.andnana.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.andnana.dao.InfoMapper;
import top.andnana.entity.Info;
import top.andnana.entity.Job;

import java.util.List;

/**
 * Created by Administrator on 2017/12/28 0028.
 */
@Service
public class InfoService {
    @Autowired
    private InfoMapper infoMapper;

    public Info getInfoByTitle(String title) {
        Info info = infoMapper.selectByTitle(title);
        return info;
    }

    public void addInfo(Info info) {
        System.out.println(info);
        infoMapper.insertInfo(info);
    }

    public List<Info> selectAll() {
        return infoMapper.selectAll();
    }

    public List<Info> selectByCategory(Integer cateid) {
        return infoMapper.selectByCategoryId(cateid);
    }

    public Info selectByPrimaryKey(Integer id) {
        return infoMapper.selectByPrimaryKey(id);

    }
}
