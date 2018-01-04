package top.andnana.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.andnana.dao.LabelMapper;
import top.andnana.entity.Category;
import top.andnana.entity.CategoryExample;
import top.andnana.entity.Label;
import top.andnana.entity.LabelExample;

import java.util.List;

/**
 * Created by Administrator on 2018/1/2 0002.
 */
@Service
public class LabelService {
    @Autowired
    private LabelMapper labelMapper;
    public Label getLabelByName(String labelName) {
        Label label = labelMapper.selectByLabelName(labelName);
        return label;
    }

    public void addLabel(Label label) {

        labelMapper.insertLabel(label);
    }

    public List<Label> getLabel() {
        List<Label> labelList = labelMapper.selectAll();
        return labelList;
    }

    public void deleteByPrimaryKey(Integer id) {
        labelMapper.deleteByPrimaryKey(id);
    }

    public void deleteByExample(List<Integer> ids) {
        LabelExample labelExample = new LabelExample();
        LabelExample.Criteria criteria = labelExample.createCriteria();
        criteria.andIdIn(ids);
        labelMapper.deleteByExample(labelExample);
    }
    public List<Label> selectAll(){
        List<Label> labelList = labelMapper.selectAll();
        return labelList;
    }

}
