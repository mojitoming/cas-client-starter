package com.mojitoming.casclient.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class User implements Serializable {
    private static final long serialVersionUID = -1337841290061533570L;

    private String userId;
    private String username;
    private String password;
    private String nickname;
    private String salt;
    private LocalDateTime warrantStartDate;
    private LocalDateTime warrantEndDate;
    private String status;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public LocalDateTime getWarrantStartDate() {
        return warrantStartDate;
    }

    public void setWarrantStartDate(LocalDateTime warrantStartDate) {
        this.warrantStartDate = warrantStartDate;
    }

    public LocalDateTime getWarrantEndDate() {
        return warrantEndDate;
    }

    public void setWarrantEndDate(LocalDateTime warrantEndDate) {
        this.warrantEndDate = warrantEndDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(nickname, user.nickname) &&
                Objects.equals(salt, user.salt) &&
                Objects.equals(warrantStartDate, user.warrantStartDate) &&
                Objects.equals(warrantEndDate, user.warrantEndDate) &&
                Objects.equals(status, user.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, password, nickname, salt, warrantStartDate, warrantEndDate, status);
    }
}
