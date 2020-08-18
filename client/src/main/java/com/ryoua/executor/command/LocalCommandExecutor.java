package com.ryoua.executor.command;

import com.ryoua.a.model.common.ExecuteResult;

/**
 * * @Author: RyouA
 * * @Date: 2020/8/5
 **/
public interface LocalCommandExecutor {
    ExecuteResult executeCommand(String command, long timeout);
}
