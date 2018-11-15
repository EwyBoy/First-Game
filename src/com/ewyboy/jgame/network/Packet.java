package com.ewyboy.jgame.network;

public abstract class Packet {

    public byte packetID;

    public Packet(int packetID) {
        this.packetID = (byte) packetID;
    }

    public abstract void write();

    public abstract void read();

}
