package com.nhnacademy.shoppingmall.common.initialize;

import com.nhnacademy.shoppingmall.thread.channel.RequestChannel;
import com.nhnacademy.shoppingmall.thread.worker.WorkerThread;

import jakarta.servlet.ServletContainerInitializer;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import java.util.Set;

public class PointThreadInitializer implements ServletContainerInitializer {

    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
        try {
            RequestChannel requestChannel = new RequestChannel(10);
            //todo#14-1 servletContext에 requestChannel을 등록합니다.
            ctx.setAttribute("requestChannel", requestChannel);

            //todo#14-2 WorkerThread 사작합니다.
            WorkerThread workerThread = new WorkerThread(requestChannel);
            workerThread.start();
        }catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Failed to initialize PointThreadInitializer", e);
        }
    }
}
