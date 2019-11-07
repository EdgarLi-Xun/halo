package run.halo.app.controller.admin.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import run.halo.app.model.entity.IPCount;
import run.halo.app.service.IPCountService;

import java.util.List;
import java.util.Map;

import static org.springframework.data.domain.Sort.Direction.DESC;

/**
 * 访问数据统计
 */
@RestController
@RequestMapping("/api/admin/dataStatistics")
public class DataStatisticsController {

    @Autowired
    private IPCountService ipCountService;


    @GetMapping
    public Page<IPCount> pageBy(@PageableDefault(sort = "createTime", direction = DESC) Pageable pageable) {
        Page<IPCount> ipCountPage = ipCountService.pageBy(pageable);
        return ipCountPage;
    }


    @PostMapping("countByDate")
    public List<Map<String,Object>> countByDate(Integer dayCount) {
        return ipCountService.countByDate(dayCount);
    }
}
