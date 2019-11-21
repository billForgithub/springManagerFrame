package com.github.zuihou.msgs.api.fallback;

import java.util.Map;

import com.github.zuihou.base.R;
import com.github.zuihou.msgs.api.MsgsGeneralApi;

import org.springframework.stereotype.Component;

/**
 * 消息通用api
 *
 * @author zuihou
 * @date 2019/09/01
 */
@Component
public class MsgsGeneralApiFallback implements MsgsGeneralApi {
    @Override
    public R<Map<String, Map<String, String>>> enums() {
        return R.timeout();
    }
}
