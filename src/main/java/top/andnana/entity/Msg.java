package top.andnana.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/17 0017.
 */
public class Msg {
    private Integer code;
    private String msg;
    private Map<String, Object> myInfo = new HashMap<>();

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getMyInfo() {
        return myInfo;
    }

    public void setMyInfo(Map<String, Object> myInfo) {
        this.myInfo = myInfo;
    }
    public static Msg success(){
        Msg msg = new Msg();
        msg.setCode(200);
        msg.setMsg("成功");
        return msg;
    }
    public static Msg failure(){
        Msg msg = new Msg();
        msg.setCode(300);
        msg.setMsg("失败");
        return msg;
    }
    public Msg add(String key, Object value){
        this.getMyInfo().put(key, value);
        return this;
    }
}
