package com.component.vender.share;

import android.os.Bundle;

/**
 * @author fox.hu
 * @date 2018/9/4
 */

public interface ShareParamsHelper<T> {

    Bundle createParams(com.component.vender.share.ShareType type, T t);
}
