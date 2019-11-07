package run.halo.app.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import run.halo.app.model.entity.IPCount;

import java.util.List;
import java.util.Map;

public interface IPCountService {

    /**
     * 将本次访问的ip存下来
     * @param ip
     */
    public void saveIPCount(String ip,String path);

    /**
     * 查询list
     * @return
     */
    public Page<IPCount> pageBy(Pageable pageable);


    /**
     * 按照ip统计每天的访问量
     * @param dayCount 统计的时间段 天数
     * @return
     */
    public List<Map<String,Object>> countByDate(Integer dayCount);
}
