/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import db.UserModel;

/**
 *
 * @author ASUS
 */
public class User {
    public int userId;
    public String username;
    public String email;
    public String avatar;
    
    public User(int userId, String username, String email, String avatar) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.avatar = avatar;
    }
    
    public User(UserModel userModel) {
        this.userId = userModel.getUserId();
        this.username = userModel.getUsername();
        this.email = userModel.getEmail();
        this.avatar = userModel.getAvatar();
    }
}
