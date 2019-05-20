package com.component.location;


import com.component.location.vender.Vendor;

/**
 * 定位的执行策略
 *
 * @author fox.hu
 */
public interface LocationSyncStrategy {
    /**
     * 是否使能该定位器，确定使用哪些定位器
     *
     * @param vendor 定位器
     * @return true为使能，false为不使能
     * 默认都使能
     */
    default boolean isLocatorEnable(Vendor vendor) {
        return true;
    }

    default boolean isAnyOf() {
        return true;
    }

    /**
     * 确定该定位器的优先级 级高的优先执行
     *
     * @param vendor
     * @return 优先级
     * 默认优先使用默认定位
     */
    default int getResultPriority(Vendor vendor) {
        int priority = 0;
        switch (vendor) {
            case BAIDU:
                priority = 1;
                break;
            case DEFAULT:
            default:
                priority = 2;
                break;
        }
        return priority;
    }

    default long getOverTime(Vendor vendor) {
        return 3000;
    }
}
