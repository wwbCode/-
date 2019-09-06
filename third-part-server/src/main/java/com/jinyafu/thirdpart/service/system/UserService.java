package com.jinyafu.thirdpart.service.system;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jinyafu.jmall.entity.data.system.UserData;
import com.jinyafu.jmall.entity.data.system.UserQuery;
import com.jinyafu.jmall.entity.data.system.UserRoleInfo;
import com.jinyafu.jmall.entity.third.system.User;
import com.jinyafu.jmall.entity.third.system.UserRole;
import com.jinyafu.jmall.mapper.third.system.*;
import com.jinyafu.thirdpart.common.code.MessageOutput;
import com.jinyafu.thirdpart.common.code.OutputCode;
import com.jinyafu.thirdpart.common.code.PageOutput;
import com.jinyafu.thirdpart.common.consts.CommonConsts;
import com.jinyafu.thirdpart.common.data.PageInfos;
import com.jinyafu.thirdpart.common.util.common.OnlyMD5Util;
import com.jinyafu.thirdpart.config.AuthManager;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @CreateDate: 2019/8/30 15:03
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: xzq
 * @Version: 1.0
 */

@Service
@Transactional
public class UserService {

    @Resource
    UserMapper userMapper;
    @Resource
    UserDataMapper userDataMapper;
    @Resource
    UserRoleInfoMapper userRoleInfoMapper;
    @Resource
    UserRoleMapper userRoleMapper;
    @Resource
    UserMenuMapper userMenuMapper;
    @Resource
    AuthManager authManager;

    @SuppressWarnings("rawtypes")
    public PageOutput queryUserList(UserQuery userQuery, PageInfos pageInfos) {

        userQuery.setType(User.type_general);
        Page<UserData> page = PageHelper.offsetPage(pageInfos.getStartResult(), pageInfos.getPageSize());
        List<UserData> list = userDataMapper.queryList(userQuery);

        List<String> userIdList = new ArrayList<String>();
        Map<String, UserData> map = new HashMap<String, UserData>();
        for (UserData ud : list) {
            userIdList.add(ud.getId());
            map.put(ud.getId(), ud);
        }
        List<UserRoleInfo> urs = userRoleInfoMapper.getUserRoleInfoListByUserIdList(userIdList);

        for (UserRoleInfo ur : urs) {
            UserData ud = map.get(ur.getUserId());
            if (null != ud) {
                List<UserRoleInfo> userRoleList = ud.getUserRoleInfoList();
                if (null == userRoleList) {
                    userRoleList = new ArrayList<UserRoleInfo>();
                    ud.setUserRoleInfoList(userRoleList);
                }
                userRoleList.add(ur);
            }
        }
        return PageOutput.ok(page, list);
    }

    public User getById(String id) {

        return userMapper.get(id);
    }

    /**
     * 根据id是否存在执行新增或者修改
     *
     * @param user
     */

    public void addOrUpdate(User user, List<String> roleIds) {
        addOrUpdate(user);
        addOrUpdate(user.getId(), roleIds);
    }

    public void addOrUpdate(User user) {
        user.setPassword(OnlyMD5Util.md5L32(user.getPassword()));
        if (null == user.getId() || "".equals(user.getId())) {
            userMapper.insertUser(user);
        } else {
            userMapper.updateBySelective(user);
        }
    }

    public void addOrUpdate(String userId, List<String> roleIds) {
        List<UserRole> rmList = userRoleMapper.getListByUserId(userId);

        List<UserRole> deleteList = new ArrayList<UserRole>();

        Map<String, UserRole> map = new HashMap<String, UserRole>();
        for (UserRole ur : rmList) {
            if (map.containsKey(ur.getRoleId())) {
                deleteList.add(ur);
            }
            map.put(ur.getRoleId(), ur);
        }

        List<UserRole> addList = new ArrayList<UserRole>();
        for (String roleId : roleIds) {
            UserRole rm = map.remove(roleId);
            if (null == rm) {
                rm = new UserRole();
                rm.setRoleId(roleId);
                rm.setUserId(userId);
                addList.add(rm);
            }
        }
        deleteList.addAll(map.values());
        if (!addList.isEmpty()) {
            for (UserRole ur : addList) {
                userRoleMapper.insertUserRole(ur);
            }
        }

        if (!deleteList.isEmpty()) {
            for (UserRole ur : deleteList) {
                userRoleMapper.delete(ur.getId());
            }
        }
    }

    public void delete(String id) {
        userMapper.delete(id);
        userRoleMapper.deleteByUserId(id);
    }

    public List<UserRoleInfo> getUserRoleInfoListByUserId(String userId){
        List<String> userIdList = new ArrayList<String>();
        userIdList.add(userId);
        List<UserRoleInfo> urs = userRoleInfoMapper.getUserRoleInfoListByUserIdList(userIdList);
        return urs;
    }

    public Object login(String account, String password) {
        Map<String,Object> map=new HashMap<>();
        try {
            if (StringUtils.isNotBlank(account) && StringUtils.isNotBlank(password)) {
                User user = userMapper.getByAccount(account);
                if (null != user) {
                    if (password.equals(user.getPassword())) {
                        String userId = user.getId();
                        String token = authManager.createToken(userId);
                        authManager.putToken(token, userId);
                        if (user.getType() == User.type_system) {// 如果是超级管理员，则拥有所有权限
                            authManager.putAuth(token);
                        }
                        map.put("user", user);
                        map.put("token", token);
                    } else {
                        return  MessageOutput.get(OutputCode.PX.getCode(), OutputCode.PX.getMessage());
                    }
                } else {
                    return  MessageOutput.get(OutputCode.ACCOUNT_ISNOT_EXIST.getCode(), OutputCode.ACCOUNT_ISNOT_EXIST.getMessage());
                }
            } else {
                return  MessageOutput.get(OutputCode.PARAMS_INVALID_EMPTY.getCode(), OutputCode.PARAMS_INVALID_EMPTY.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            MessageOutput.get(OutputCode.EX.getCode(), OutputCode.EX.getMessage());
        }
        return MessageOutput.ok(map);
    }

    public void updatePassword(String id, String password) {
        password = OnlyMD5Util.md5L32(password);
        User user = new User();
        user.setId(id);
        user.setPassword(password);
        userMapper.updateBySelective(user);
    }

    public boolean isExistAccount(String account, String id) {
        boolean isExist = true;
        User data = userMapper.getAdminByAccount(account);
        if (null == data || data.getId().equals(id)) {
            isExist = false;
        }
        return isExist;
    }
}
