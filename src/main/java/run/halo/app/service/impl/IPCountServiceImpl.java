package run.halo.app.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import run.halo.app.model.entity.IPCount;
import run.halo.app.repository.IPCountRepository;
import run.halo.app.service.IPCountService;
import run.halo.app.utils.IpUtils;

import java.util.Date;
import java.util.List;

/**
 * 版本        修改时间        作者        修改内容
 * V1.0        2019-11-05        wansu      原始版本
 * 文件说明: IPCountServiceImpl
 *
 * @description: ipcount的统计实现类
 **/
@Slf4j
@Service
public class IPCountServiceImpl implements IPCountService {

    private final IPCountRepository ipCountRepository;

    public IPCountServiceImpl(IPCountRepository ipCountRepository) {
        this.ipCountRepository = ipCountRepository;
    }


    @Override
    public void saveIPCount(String ip, String path) {
        if (path.startsWith("/admin") || path.startsWith("/anatole") || path.startsWith("/api/content") || path.endsWith("/png") || path.endsWith("/ico")){
            return;
        }
        List<IPCount> ipCountList = ipCountRepository.queryIPCountByIPAAndPathAndCreateTime(ip,path,new Date());
        if (null == ipCountList || 0 == ipCountList.size()){
            IPCount ipCount = IpUtils.getIpInfo(ip);
            if (null == ipCount){
                return;
            }
            ipCount.setPath(path);
            ipCountRepository.save(ipCount);
        }


    }
}
