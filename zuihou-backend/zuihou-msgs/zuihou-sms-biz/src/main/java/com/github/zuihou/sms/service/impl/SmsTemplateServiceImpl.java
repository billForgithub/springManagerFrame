package com.github.zuihou.sms.service.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.zuihou.exception.BizException;
import com.github.zuihou.sms.dao.SmsTemplateMapper;
import com.github.zuihou.sms.entity.SmsProvider;
import com.github.zuihou.sms.entity.SmsTemplate;
import com.github.zuihou.sms.service.SmsProviderService;
import com.github.zuihou.sms.service.SmsTemplateService;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.github.zuihou.exception.code.ExceptionCode.BASE_VALID_PARAM;
import static com.github.zuihou.utils.BizAssert.notNull;

/**
 * <p>
 * 业务实现类
 * 短信模板
 * </p>
 *
 * @author zuihou
 * @date 2019-08-01
 */
@Slf4j
@Service
public class SmsTemplateServiceImpl extends ServiceImpl<SmsTemplateMapper, SmsTemplate> implements SmsTemplateService {

    @Autowired
    private SmsProviderService smsProviderService;

    private static String getParamByContent(String content, String regEx) {
        //编译正则表达式
        Pattern pattern = Pattern.compile(regEx);
        //忽略大小写的写法:
        Matcher matcher = pattern.matcher(content);

        // 查找字符串中是否有匹配正则表达式的字符/字符串//有序， 目的是为了兼容 腾讯云参数
        JSONObject obj = new JSONObject(true);
        while (matcher.find()) {
            String key = matcher.group(1);
            obj.put(key, "");
        }
        return obj.toString();
    }

    private void buildParams(SmsTemplate smsTemplate) {
        SmsProvider smsProvider = smsProviderService.getById(smsTemplate.getProviderId());
        notNull(smsProvider, "短信供应商不存在");
        String content = smsTemplate.getContent();
        if (StrUtil.isNotEmpty(content)) {
            String param = getParamByContent(content, smsProvider.getProviderType().getRegex());
            smsTemplate.setTemplateParams(param);
        }
    }

    @Override
    public void saveTemplate(SmsTemplate smsTemplate) {
        buildParams(smsTemplate);
        int count = super.count(Wrappers.<SmsTemplate>lambdaQuery().eq(SmsTemplate::getCustomCode, smsTemplate.getCustomCode()));

        if (count > 0) {
            throw BizException.wrap(BASE_VALID_PARAM.build("自定义编码重复"));
        }

        super.save(smsTemplate);
    }

    @Override
    public void updateTemplate(SmsTemplate smsTemplate) {
        buildParams(smsTemplate);
        super.updateById(smsTemplate);
    }
}
