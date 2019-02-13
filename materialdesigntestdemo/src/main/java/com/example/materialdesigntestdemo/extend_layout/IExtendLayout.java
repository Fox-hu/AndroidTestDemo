package com.example.materialdesigntestdemo.extend_layout;

public interface IExtendLayout {
    enum State {
        /**
         * 初始状态
         */
        NONE,

        RESET,

        /**
         * 超过列表高度
         */
        BEYOND_LIST,
        /**
         * 开始显示列表
         */
        START_SHOW_LIST,
        /**
         * 到达列表高度
         */
        ARRIVED_LIST_HEIGHT,
    }

    void setState(State state);

    State getState();

    int getContentSize();

    int getListSize();

    void onPull(int offset);
}
