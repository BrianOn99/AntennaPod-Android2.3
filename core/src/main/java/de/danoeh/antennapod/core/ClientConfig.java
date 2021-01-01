package de.danoeh.antennapod.core;

import android.content.Context;

import java.security.Security;

import org.conscrypt.Conscrypt;

/**
 * Stores callbacks for core classes like Services, DB classes etc. and other configuration variables.
 * Apps using the core module of AntennaPod should register implementations of all interfaces here.
 */
public class ClientConfig {

    /**
     * Should be used when setting User-Agent header for HTTP-requests.
     */
    public static String USER_AGENT;

    public static ApplicationCallbacks applicationCallbacks;

    public static DownloadServiceCallbacks downloadServiceCallbacks;

    public static PlaybackServiceCallbacks playbackServiceCallbacks;

    public static GpodnetCallbacks gpodnetCallbacks;

    public static FlattrCallbacks flattrCallbacks;

    public static DBTasksCallbacks dbTasksCallbacks;

    private static boolean initialized = false;

    private static void installSslProvider(Context context) {
        // Insert bundled conscrypt as highest security provider (overrides OS version).
        Security.insertProviderAt(Conscrypt.newProvider(), 1);
    }

    public static synchronized void initialize(Context context) {
        if(initialized) {
            return;
        }
        installSslProvider(context);
        initialized = true;
    }
}
