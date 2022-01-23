package avdinformatica.group1.rentmycar.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "user")
public class User {

    private String email;
    private String name;
    private String surName;
    @PrimaryKey
    @NonNull
    private String sessionId;
    private Long clientId;

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public User(String email, String name, String surName, @NonNull String sessionId, Long clientId) {
        this.email = email;
        this.name = name;
        this.surName = surName;
        this.sessionId = sessionId;
        this.clientId = clientId;
    }
}
