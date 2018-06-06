/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fmcservertest;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.AndroidNotification;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author dball7k
 */
public class Main {

    public static void main(String args[]) throws FileNotFoundException, IOException, InterruptedException, ExecutionException {

        FileInputStream serviceAccount = new FileInputStream("cubebid_firebase_credentials.json");

        
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://cubebid-8a156.firebaseio.com")
                .build();

        FirebaseApp.initializeApp(options);

        AndroidNotification androidNotif = AndroidNotification.builder()
                .setTitle("Bienes Título")
                .setBody("Actualización Bienes")
                .build();

        AndroidConfig androidConfig = AndroidConfig.builder()
                .setNotification(androidNotif)
                .build();

        Message message = Message.builder()
                .setAndroidConfig(androidConfig)
                .setTopic("Bienes")
                .build();

        String response = FirebaseMessaging.getInstance().sendAsync(message).get();

    }
}
