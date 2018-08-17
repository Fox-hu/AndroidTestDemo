package com.umeng.soexample.thirdLogin;

/**
 * @author fox.hu
 * @date 2018/8/16
 */

public class PlatFormInfo {
    private String id;
    private String nickName;
    private String gender;
    private String iconUrl;

    public PlatFormInfo(String id, String nickName, String gender, String iconUrl) {
        this.id = id;
        this.nickName = nickName;
        this.gender = gender;
        this.iconUrl = iconUrl;
    }

    @Override
    public String toString() {
        return "PlatFormInfo{" + "id='" + id + '\'' + ", nickName='" + nickName + '\'' +
               ", gender='" + gender + '\'' + ", iconUrl='" + iconUrl + '\'' + '}';
    }
}
