package run.halo.app.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import run.halo.app.model.entity.IPCount;
import run.halo.app.repository.base.BaseRepository;

import java.util.Date;
import java.util.List;

/**
 * IPCountRepository
 *
 * @author wansu
 * @date 2019-11-05
 */
public interface IPCountRepository extends BaseRepository<IPCount, Integer>, JpaSpecificationExecutor<IPCount> {


    @Query(value = "select * from ipcount ipcount where ipcount.ip = ?1 and ipcount.path = ?2 " +
            "and date_format(ipcount.create_time,'%Y-%m-%d') = date_format(?3,'%Y-%m-%d')",nativeQuery = true)
    List<IPCount> queryIPCountByIPAAndPathAndCreateTime(String ip, String path, Date date);

}
