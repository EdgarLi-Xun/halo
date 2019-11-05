package run.halo.app.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

/**
 * 版本        修改时间        作者        修改内容
 * V1.0        2019-11-05        wansu      原始版本
 * 文件说明: IPCount
 *
 * @description: IP访问统计
 **/
@Data
@Entity
@Table(name = "ipcount")
@ToString
@EqualsAndHashCode(callSuper = true)
public class IPCount extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * Attachment name.
     */
    @Column(name = "ip", columnDefinition = "varchar(255) default ''")
    private String ip;

    /**
     * 路径
     */
    @Column(name = "path", columnDefinition = "varchar(1023) default ''")
    private String path;

    /**
     * 国家
     */
    @Column(name = "country", columnDefinition = "varchar(255) default ''")
    private String country;


    /**
     * 省
     */
    @Column(name = "region", columnDefinition = "varchar(255) default ''")
    private String region;
    /**
     * 市
     */
    @Column(name = "city", columnDefinition = "varchar(255) default ''")
    private String city;

    /**
     * isp供应商
     */
    @Column(name = "isp", columnDefinition = "varchar(255) default ''")
    private String isp;
}
