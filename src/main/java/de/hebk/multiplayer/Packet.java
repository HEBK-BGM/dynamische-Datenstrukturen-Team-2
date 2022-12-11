package de.hebk.multiplayer;

public class Packet {
    private PacketType type;
    private String content;

    public Packet(PacketType type, String content) {
        this.type = type;
        this.content = content;
    }

    public PacketType getPacketType() {
        return type;
    }

    public String getContent() {
        return content;
    }
}