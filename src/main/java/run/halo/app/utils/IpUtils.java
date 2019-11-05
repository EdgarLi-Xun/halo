package run.halo.app.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import run.halo.app.model.entity.IPCount;


/**
 * 版本        修改时间        作者        修改内容
 * V1.0        2019-11-05        wansu      原始版本
 * 文件说明: IpUtils
 *
 * @description:
 **/
public class IpUtils {

        public static IPCount getIpInfo(String ip){
            if (ip.startsWith("0")){
                return null;
            }
            RestTemplate restTemplate=new RestTemplate();
            String uri="http://ip.taobao.com/service/getIpInfo.php?ip=" + ip;
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
            HttpEntity<String> entity = new HttpEntity<String>(headers);
            String strbody=restTemplate.exchange(uri, HttpMethod.GET, entity,String.class).getBody();
            if (StringUtils.hasLength(strbody)){
                String data = JSONObject.parseObject(strbody).getString("data");
                IPCount ipCount = JSON.parseObject(data,IPCount.class);
                return ipCount;
            }
            return null;
        }

}
