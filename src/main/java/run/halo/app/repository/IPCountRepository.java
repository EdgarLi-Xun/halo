package run.halo.app.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import run.halo.app.model.entity.IPCount;
import run.halo.app.repository.base.BaseRepository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * IPCountRepository
 *
 * @author wansu
 * @date 2019-11-05
 */
public interface IPCountRepository extends BaseRepository<IPCount, Integer>, JpaSpecificationExecutor<IPCount> {


    @Query(value = "select * from ipcount ipcount where deleted = 0 and ipcount.ip = ?1 and ipcount.path = ?2 " +
            "and date_format(ipcount.create_time,'%Y-%m-%d') = date_format(?3,'%Y-%m-%d')",nativeQuery = true)
    List<IPCount> queryIPCountByIPAAndPathAndCreateTime(String ip, String path, Date date);


    @Query(value = "SELECT " +
            " date_format( date.date, '%m%d' ) AS date, " +
                    " ifnull( count( ip.ip ), 0 ) AS count  " +
                    "FROM " +
                    " ( " +
                    " SELECT " +
                    " DATE_FORMAT( CURDATE( ) - INTERVAL ( a.n + ( 10 * b.n ) ) DAY, '%Y%m%d' ) AS date  " +
                    " FROM " +
                    " ( " +
                    " SELECT " +
                    " 0 AS n UNION ALL " +
                    " SELECT " +
                    " 1 UNION ALL " +
                    " SELECT " +
                    " 2 UNION ALL " +
                    " SELECT " +
                    " 3 UNION ALL " +
                    " SELECT " +
                    " 4 UNION ALL " +
                    " SELECT " +
                    " 5 UNION ALL " +
                    " SELECT " +
                    " 6 UNION ALL " +
                    " SELECT " +
                    " 7 UNION ALL " +
                    " SELECT " +
                    " 8 UNION ALL " +
                    " SELECT " +
                    " 9  " +
                    " ) AS a " +
                    " CROSS JOIN ( " +
                    " SELECT " +
                    " 0 AS n UNION ALL " +
                    " SELECT " +
                    " 1 UNION ALL " +
                    " SELECT " +
                    " 2 UNION ALL " +
                    " SELECT " +
                    " 3 UNION ALL " +
                    " SELECT " +
                    " 4 UNION ALL " +
                    " SELECT " +
                    " 5 UNION ALL " +
                    " SELECT " +
                    " 6 UNION ALL " +
                    " SELECT " +
                    " 7 UNION ALL " +
                    " SELECT " +
                    " 8 UNION ALL " +
                    " SELECT " +
                    " 9  " +
                    " ) AS b  " +
                    " WHERE " +
                    " ( CURDATE( ) - INTERVAL ( a.n + ( 10 * b.n ) ) DAY ) > ( CURDATE( ) - INTERVAL ?1 DAY )  " +
                    " ) date " +
                    " LEFT JOIN ( SELECT ip, create_time FROM ipcount WHERE deleted = 0 GROUP BY ip, date_format( create_time, '%Y%m%d' ) ) ip ON date.date = date_format( ip.create_time, '%Y%m%d' )  " +
                    "GROUP BY " +
                    " date.date",nativeQuery = true)
    List<Map<String,Object>> countByDate(int dayCount);

}
