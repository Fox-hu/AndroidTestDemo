package com.example.materialdesigntestdemo.view;

public class GroupInfo {
    public GroupInfo(int groupId, String title) {
        this.groupId = groupId;
        this.title = title;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getGroupLength() {
        return groupLength;
    }

    public void setGroupLength(int groupLength) {
        this.groupLength = groupLength;
    }

    public boolean isFirstViewInGroup() {
        return position == 0;
    }

    public boolean isLastViewInGroup() {
        return position == groupLength - 1 && position >= 0;
    }

    private int groupId;
    private String title;
    private int position;
    private int groupLength;
}
