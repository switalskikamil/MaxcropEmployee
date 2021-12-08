package com.maxcropdata.maxcropemployee.shared.interfaces;

import com.maxcropdata.maxcropemployee.model.server.response.ServerResponse;

public interface AsyncResponseProcessor {
    void processFinish(ServerResponse response);
}
