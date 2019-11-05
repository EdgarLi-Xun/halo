package run.halo.app.event.post;

import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 版本        修改时间        作者        修改内容
 * V1.0        2019-11-05        wansu      原始版本
 * 文件说明: ServletContextListener
 *
 * @description:
 **/
@Component
public class ServletContextListener implements javax.servlet.ServletContextListener {
    private Map<String, List<HttpSession>> userMap = new HashMap<>();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("userMap",userMap);
    }
}