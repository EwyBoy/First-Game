package com.ewyboy.jgame.network;

import com.ewyboy.jgame.Game;
import com.ewyboy.jgame.entities.EntityPlayerMP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class GameServer extends Thread {

    private Game game;
    private DatagramSocket socket;
    private List<EntityPlayerMP> connectedPlayers = new ArrayList<>();

    public GameServer(Game game) {
        this.game = game;
        try {
            this.socket = new DatagramSocket(1331);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            byte[] data = new byte[1024];
            DatagramPacket packet = new DatagramPacket(data, data.length);

            try {
                socket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //TODO packet parser here please :)
        }
    }



}
