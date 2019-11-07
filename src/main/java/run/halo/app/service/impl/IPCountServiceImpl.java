package run.halo.app.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import run.halo.app.model.entity.IPCount;
import run.halo.app.repository.IPCountRepository;
import run.halo.app.service.IPCountService;
import run.halo.app.service.base.AbstractCrudService;
import run.halo.app.utils.IpUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 版本        修改时间        作者        修改内容
 * V1.0        2019-11-05        wansu      原始版本
 * 文件说明: IPCountServiceImpl
 *
 * @description: ipcount的统计实现类
 **/
@Slf4j
@Service
public class IPCountServiceImpl  extends AbstractCrudService<IPCount, Integer> implements IPCountService {

    private final IPCountRepository ipCountRepository;

    public IPCountServiceImpl(IPCountRepository ipCountRepository) {
        super(ipCountRepository);
        this.ipCountRepository = ipCountRepository;
    }


    @Override
    public void saveIPCount(String ip, String path) {
        if ((path.startsWith("/api/admin") || path.startsWith("/anatole") || path.startsWith("/api/content") || path.endsWith(".png") || path.endsWith(".ico")
                || path.endsWith(".css") ||  path.endsWith(".js")) && !path.startsWith("/archives")
        ){
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

    @Override
    public Page<IPCount> pageBy(Pageable pageable) {
        Assert.notNull(pageable, "Page info must not be null");
        return listAll(pageable);
    }

    @Override
    public List<Map<String, Object>> countByDate(Integer dayCount) {
        if (null == dayCount || dayCount <= 0){
            throw new RuntimeException("传入的参数不合法");
        }
        return ipCountRepository.countByDate(dayCount);
    }
}
