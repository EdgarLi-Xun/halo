package run.halo.app.event.post;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import run.halo.app.service.IPCountService;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 版本        修改时间        作者        修改内容
 * V1.0        2019-11-05        wansu      原始版本
 * 文件说明: RequestContextListener
 *
 * @description:
 **/
@Component
@Slf4j
public class RequestContextListener implements ServletRequestListener {

    @Autowired
    private IPCountService ipCountService;
    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        ServletRequest servletRequest = sre.getServletRequest();
        //获取ip,存入ServletContext
        String ip = servletRequest.getRemoteAddr();
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        //获取session
        HttpSession session = request.getSession();
        session.setAttribute("ip",ip);
        log.error("创建:"+ip);
        log.error("url:"+request.getRequestURI());
        if (null != ipCountService) {
            ipCountService.saveIPCount(ip,request.getRequestURI());
        }
        //获取map集合
        ServletContext servletContext = sre.getServletContext();
        Map<String, List<HttpSession>> userMap = (Map<String, List<HttpSession>>) servletContext.getAttribute("userMap");
        //获取sessionList
        List<HttpSession> sessionList=userMap.containsKey(ip) ? userMap.get(ip) : new ArrayList<>();
        if(!sessionList.contains(session)){
            sessionList.add(session);
        }
        userMap.put(ip, sessionList);
        //存入userMap
        servletContext.setAttribute("userMap",userMap);
    }
}