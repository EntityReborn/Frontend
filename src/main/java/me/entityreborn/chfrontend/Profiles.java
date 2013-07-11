package me.entityreborn.chfrontend;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import me.entityreborn.chfrontend.communication.Exceptions;
import me.entityreborn.chfrontend.communication.Publisher;
import me.entityreborn.chfrontend.communication.Subscriber;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author import
 */
public class Profiles {

    public static class Profile {
        private static Profile connected;

        String profname = "Default";
        String username = "RemoteUser";
        String outgoing = "localhost";
        String incoming = "localhost";
        long outport = 25559;
        long inport = 25560;
        private Publisher pub;
        private Subscriber sub;

        public Profile(String profname, String name, String outgoing,
                String incoming, long outport, long inport) {
            this.profname = profname;
            this.username = name;
            this.outgoing = outgoing;
            this.incoming = incoming;
            this.inport = inport;
            this.outport = outport;
        }

        public Profile() {
        }

        public void send(String channel, String message) {
            JSONObject obj = new JSONObject();
            
            obj.put("who", username);
            obj.put("message", message);
            obj.put("type", "remote");

            StringWriter out = new StringWriter();

            try {
                obj.writeJSONString(out);
            } catch (IOException ex) {
                Logger.getLogger(Profiles.class.getName()).log(Level.SEVERE, null, ex);
                return;
            }

            try {
                pub.publish(channel, out.toString());
            } catch (Exceptions.InvalidChannelException ex) {
                Logger.getLogger(Profiles.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        public void connect() {
            if (connected != null) {
                disconnect();
            }
            
            pub = new Publisher("remotepub");
            sub = new Subscriber("remotesub");

            pub.init(Frontend.getContext());
            sub.init(Frontend.getContext());

            pub.connect(getOutgoing());
            sub.connect(getIncoming());
            
            pub.start();
            sub.start();
            
            connected = this;

            try {
                sub.subscribe("chat");
                sub.subscribe("connect");
            } catch (Exceptions.InvalidChannelException ex) {
                Logger.getLogger(Frontend.class.getName()).log(Level.SEVERE, null, ex);
            }

            sub.addCallback(new Subscriber.MessageCallback() {
                public void process(String subscriber, String channel, String publisher, String message) {
                    JSONParser parser = new JSONParser();
                    JSONObject obj;

                    try {
                        obj = (JSONObject) parser.parse(message);
                    } catch (ParseException ex) {
                        Logger.getLogger(Frontend.class.getName()).log(Level.SEVERE, null, ex);
                        return;
                    }

                    if ("chat".equals(channel)) {
                        if (!obj.containsKey("who") || !obj.containsKey("message")
                                || !obj.containsKey("type")) {
                            return;
                        }

                        String from = (String) obj.get("who");
                        String msg = (String) obj.get("message");
                        String type = (String) obj.get("type");

                        if (from == null || from.trim().isEmpty()) {
                            return;
                        }

                        if (msg == null || msg.trim().isEmpty()) {
                            return;
                        }

                        if (type == null || type.trim().isEmpty()) {
                            return;
                        }

                        if ("remote".equals(type)) {
                            Frontend.getFrontend().addLine("*" + from + "*: " + msg);
                        } else if ("console".equals(type)) {
                            Frontend.getFrontend().addLine("[Console]: " + msg);
                        } else if ("player".equals(type)) {
                            Frontend.getFrontend().addLine("<" + from + ">: " + msg);
                        }
                    } else if ("connect".equals(channel)) {
                        if (!obj.containsKey("who") || !obj.containsKey("type")) {
                            return;
                        }

                        String who = (String) obj.get("who");
                        String type = (String) obj.get("type");

                        if ("+".equals(type)) {
                            Frontend.getFrontend().addLine("* " + who + " joined.");
                        } else {
                            Frontend.getFrontend().addLine("* " + who + " left.");
                        }
                    }
                }
            });
        }

        public String getIncoming() {
            String retn = incoming;
            retn += ":" + inport;

            return "tcp://" + retn;
        }

        public String getOutgoing() {
            String retn = outgoing;
            retn += ":" + outport;

            return "tcp://" + retn;
        }

        public String getUsername() {
            return username;
        }

        public String getProfileName() {
            return profname;
        }

        @Override
        public String toString() {
            return profname;
        }

        public void disconnect() {
            if (pub != null) {
                pub.stop();
            }

            if (sub != null) {
                sub.stop();
            }
            
            connected = null;
        }
    }
    
    private static Map<String, Profile> profiles = new HashMap<String, Profile>();

    public static void addProfile(Profile prof) {
        profiles.put(prof.profname, prof);
    }

    public static void delProfile(Profile prof) {
        profiles.remove(prof.profname);
    }

    public static void renameProfile(Profile prof, String name) {
        profiles.remove(prof.profname);
        profiles.put(name, prof);

        prof.profname = name;
    }

    public static Profile getProfile(String profile) {
        return profiles.get(profile);
    }

    public static Collection<Profile> getProfiles() {
        return profiles.values();
    }

    public static void load() {
        File file = new File("config.json");

        if (file.exists() && file.isFile()) {
            JSONParser parser = new JSONParser();

            try {
                FileReader fis = new FileReader(file);
                JSONObject obj = (JSONObject) parser.parse(fis);
                Set<String> keys = obj.keySet();

                if (!keys.isEmpty()) {
                    for (String key : keys) {
                        JSONObject sect = (JSONObject) obj.get(key);

                        String name = "RemoteUser";
                        String outgoing = "localhost";
                        String incoming = "localhost";
                        long outport = 25559;
                        long inport = 25560;

                        if (sect.containsKey("username")) {
                            name = (String) sect.get("username");
                        }

                        if (sect.containsKey("incoming")) {
                            incoming = (String) sect.get("incoming");
                        }

                        if (sect.containsKey("outgoing")) {
                            outgoing = (String) sect.get("outgoing");
                        }

                        if (sect.containsKey("outgoingport")) {
                            outport = (Long) sect.get("outgoingport");
                        }

                        if (sect.containsKey("incomingport")) {
                            inport = (Long) sect.get("incomingport");
                        }

                        Profile prof = new Profile(key, name, outgoing, incoming, outport, inport);
                        profiles.put(key, prof);
                    }
                } else {
                    profiles.put("Default", new Profile());
                }
            } catch (ParseException ex) {
                Logger.getLogger(Frontend.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Frontend.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            profiles.put("Default", new Profile());
        }
    }

    public static void save() {
        JSONObject root = new JSONObject();

        for (String key : profiles.keySet()) {
            Profile profile = getProfile(key);

            JSONObject obj = new JSONObject();

            obj.put("username", profile.username);
            obj.put("outgoing", profile.outgoing);
            obj.put("incoming", profile.incoming);
            obj.put("outgoingport", profile.outport);
            obj.put("incomingport", profile.inport);

            root.put(key, obj);
        }

        try {
            FileWriter file = new FileWriter("config.json");
            file.write(root.toJSONString());
            file.flush();
            file.close();
        } catch (IOException e) {
            Logger.getLogger(Frontend.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
