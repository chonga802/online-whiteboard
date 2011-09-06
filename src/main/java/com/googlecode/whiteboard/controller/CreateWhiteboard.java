/*
* @author  Oleg Varaksin (ovaraksin@googlemail.com)
* $$Id$$
*/

package com.googlecode.whiteboard.controller;

import com.googlecode.whiteboard.model.UserData;
import com.googlecode.whiteboard.model.Whiteboard;
import com.googlecode.whiteboard.utils.FacesAccessor;

import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class CreateWhiteboard implements Serializable
{
    private static final long serialVersionUID = 20110501L;

    private Whiteboard whiteboard;
    private WhiteboardsManager whiteboardsManager;
    private String pubSubTransport = "websocket";
    private List<SelectItem> pubSubTransports;

    @PostConstruct
    protected void initialize() {
        // create an empty whiteboard and uuid container of the current whiteboard 
        whiteboard = new Whiteboard();
    }

    public String getTitle() {
        return whiteboard.getTitle();
    }

    public void setTitle(String title) {
        whiteboard.setTitle(title);
    }

    public String getCreator() {
        return whiteboard.getCreator();
    }

    public void setCreator(String userName) {
        whiteboard.setCreator(userName);
    }

    public int getWidth() {
        return whiteboard.getWidth();
    }

    public void setWidth(int width) {
        whiteboard.setWidth(width);
    }

    public int getHeight() {
        return whiteboard.getHeight();
    }

    public void setHeight(int height) {
        whiteboard.setHeight(height);
    }

    public void setWhiteboardsManager(WhiteboardsManager whiteboardsManager) {
        this.whiteboardsManager = whiteboardsManager;
    }

    public String getPubSubTransport() {
        return pubSubTransport;
    }

    public void setPubSubTransport(String pubSubTransport) {
        this.pubSubTransport = pubSubTransport;
    }

    public String getWhiteboardId() {
        return whiteboard.getUuid();
    }

    public void setWhiteboardId(String whiteboardId) {
        this.whiteboard.setUuid(whiteboardId);
    }

    public String create() {
        String senderId = UUID.randomUUID().toString();

        whiteboard.setCreationDate(new Date());
        whiteboard.addUserData(new UserData(senderId, getCreator()));
        whiteboard.setPubSubTransport(pubSubTransport);
        whiteboardsManager.addWhiteboard(whiteboard);

        WhiteboardIdentifiers wi = ((WhiteboardIdentifiers) FacesAccessor.getManagedBean("whiteboardIdentifiers"));
        wi.setSenderId(senderId);
        wi.setWhiteboardId(whiteboard.getUuid());

        return "pretty:wbWorkplace";
    }

    public List getTransports() {
        if (pubSubTransports == null) {
            pubSubTransports = new ArrayList<SelectItem>();
            pubSubTransports.add(new SelectItem("websocket", "WebSocket"));
            pubSubTransports.add(new SelectItem("long-polling", "Long-Polling"));
            pubSubTransports.add(new SelectItem("streaming", "Streaming"));
        }

        return pubSubTransports;
    }
}
