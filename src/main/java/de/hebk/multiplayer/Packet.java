package de.hebk.multiplayer;

public class Packet {
    private PacketType type;
    private String content;

    /**
     * Contructor for a packet
     * @param type      Type of packet
     * @param content   Content of the packet
     */
    public Packet(PacketType type, String content) {
        this.type = type;
        this.content = content;
    }

    /**
     * Returns the PacketType of the packet
     * @return PacketType
     */
    public PacketType getPacketType() {
        return type;
    }

    /**
     * Returns the content of the packet
     * @return content
     */
    public String getContent() {
        return content;
    }
}