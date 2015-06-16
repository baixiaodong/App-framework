package com.baixd.app.framework.downloader;

import java.util.List;

/**
 * Created by kk on 2015/4/19 019.
 */
public interface ThreadDAO {

    void insertThread(ThreadInfo threadInfo);

    void deleteThread(String url, int threadId);

    void updateThread(String url, int threadId, int finished);

    List<ThreadInfo> getThreads(String url);

    boolean isExistsThread(String url, int threadId);

}
