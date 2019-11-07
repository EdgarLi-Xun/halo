package run.halo.app.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import run.halo.app.model.dto.base.OutputConverter;
import run.halo.app.model.entity.IPCount;

/**
 * UPCOUNT
 */
@Data
@ToString
@EqualsAndHashCode
public class IPCountDTO implements OutputConverter<IPCountDTO, IPCount> {


    private String ip;

    private String path;

    private String country;

    private String region;

    private String city;

    private String isp;

}
