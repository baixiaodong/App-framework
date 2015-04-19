package com.baixd.app.framework.downloader;

import java.util.List;

/**
 * Created by kk on 2015/4/19 019.
 */
public interface ThreadDAO {

    public void insertThread(ThreadInfo threadInfo);

    public void deleteThread(String url, int threadId);

    public void updateThread(String url, int threadId, int finished);

    public List<ThreadInfo> getThreads(String url);

    public boolean isExistsThread(String url, int threadId);

}
