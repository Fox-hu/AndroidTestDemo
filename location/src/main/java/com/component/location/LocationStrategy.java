package com.component.location;


/**
 * 定位的执行策略
 * @author fox.hu
 */
public interface LocationStrategy {
    /**
     * 是否使能该定位器，确定使用哪些定位器
     * @param locator 定位器
     * @return true为使能，false为不使能
     */
    boolean isLocatorEnable(Locator locator);

    /**
     * 所有使能的定位器的执行方式 是串行还是并行
     * @return true是串行执行，false是并行执行
     */
    boolean isSequence();

    /**
     * 确定该定位器的优先级 级高的优先执行
     * @param locator
     * @return 优先级
     */
    int getLocatorPriority(Locator locator);

}
