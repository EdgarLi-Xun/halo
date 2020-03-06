package run.halo.app.utils;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import run.halo.app.model.entity.IPCount;

import java.io.IOException;
import java.util.HashMap;


/**
 * 版本        修改时间        作者        修改内容
 * V1.0        2019-11-05        wansu      原始版本
 * 文件说明: IpUtils
 *
 * @description:
 **/
public class IpUtils {

    private final static String JUHE_IP_KEY = "6708a225aee0c3c2eddf0edf56b40a2f";

    public static IPCount getIpInfo(String ip) {
        if (ip.startsWith("0")) {
            return null;
        }
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("ip", ip);
        paramMap.put("key", JUHE_IP_KEY);
        String result = HttpUtil.get("http://apis.juhe.cn/ip/ipNew", paramMap);
        if (StringUtils.hasLength(result)) {
            IPCount ipCount = new IPCount();
            JSONObject json = JSON.parseObject(result).getJSONObject("result");
            ipCount.setCity(json.getString("City"));
            ipCount.setCountry(json.getString("Country"));
            ipCount.setIp(ip);
            ipCount.setIsp(json.getString("Isp"));
            ipCount.setRegion(json.getString("Province"));
            return ipCount;
        }
        return null;
    }


}
