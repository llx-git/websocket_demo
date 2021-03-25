package com.ccc.conf;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @author: luolixi
 * @date: 2021-03-25
 */
@Component
@ServerEndpoint("/websocket/{sign}")
public class WebSocketServer {

    private static Map<String, List<Session>> connect = new HashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("sign") String sign){
        List<Session> sessions = connect.get(sign);
        if (CollectionUtils.isEmpty(sessions)) {
            sessions = new ArrayList<>();
            connect.put(sign, sessions);
        }
        sessions.add(session);
    }

    @OnMessage
    public void onMessage(Session session, String message){

    }

    @OnClose
    public void onClose(Session session, @PathParam("sign") String sign){
        List<Session> sessions = connect.get(sign);
        sessions.remove(session);
    }

    public static void sendInfo(String sign, String text) throws IOException {
        List<Session> sessions = connect.get(sign);
        if (!CollectionUtils.isEmpty(sessions)) {
            for (Session session : sessions) {
                session.getBasicRemote().sendText(text);
            }
        }
    }
}
