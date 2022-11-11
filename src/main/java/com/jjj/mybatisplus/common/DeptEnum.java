package com.jjj.mybatisplus.common;

import com.baomidou.mybatisplus.annotation.EnumValue;


/**
 * @className: com.jjj.mybatisplus.common.DeptEnum
 * @description:
 * @author: 江骏杰
 * @create: 2022-11-11 19:54
 */
public enum DeptEnum {
    GROUP_ONE("第一组", 1),GROUP_TWO ("第二组", 2);
    private String groupName;
    @EnumValue
    private int groupId;

    DeptEnum() {
    }
    DeptEnum(String groupName, int groupId) {
        this.groupName = groupName;
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
}
