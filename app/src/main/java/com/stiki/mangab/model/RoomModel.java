package com.stiki.mangab.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RoomModel extends RealmObject {

    @PrimaryKey
    String roomId;
    String roomName;

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}
