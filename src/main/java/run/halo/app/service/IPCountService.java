package run.halo.app.service;

public interface IPCountService {

    /**
     * 将本次访问的ip存下来
     * @param ip
     */
    public void saveIPCount(String ip,String path);
}
