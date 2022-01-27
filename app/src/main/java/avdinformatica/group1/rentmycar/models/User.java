package avdinformatica.group1.rentmycar.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class User {

    @PrimaryKey
    @NonNull
    private final Long clientId;
    private final String sessionId;
    private final String email;
    private String name;
    private final String surName;


    public Long getClientId() {
        return clientId;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getSurName() {
        return surName;
    }

    public String getSessionId() {
        return sessionId;
    }

    public User(Long clientId, String email, String name, String surName, @NonNull String sessionId) {
        this.clientId = clientId;
        this.email = email;
        this.name = name;
        this.surName = surName;
        this.sessionId = sessionId;
    }
}
