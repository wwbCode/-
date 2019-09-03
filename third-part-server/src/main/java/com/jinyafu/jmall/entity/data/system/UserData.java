package com.jinyafu.jmall.entity.data.system;


import com.jinyafu.jmall.entity.third.system.User;

import java.util.List;

/**
 * @author: XiaHui
 */
public class UserData extends User {

	private List<UserRoleInfo> userRoleInfoList;

    public List<UserRoleInfo> getUserRoleInfoList() {
        return userRoleInfoList;
    }

    public void setUserRoleInfoList(List<UserRoleInfo> userRoleInfoList) {
        this.userRoleInfoList = userRoleInfoList;
    }
}
