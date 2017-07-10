package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels;

/**
 * Created by toshiba on 10/07/2017.
 */

public class User
{
    private String Id;
    private String Email;
    private boolean EmailConfirmed;
    private String PasswordHash;
    private String SecurityStamp;
    private String PhoneNumber;
    private boolean PhoneNumberConfirmed;
    private boolean TwoFactorEnabled;
    private String LockoutEndDateUtc;
    private boolean LockoutEnabled;
    private int AccessFailedCount;
    private String UserName;
    private String FirstName;
    private String LastName;
    private String ImageData;
    private String Roles;

    public String getRoles() {
        return Roles;
    }

    public void setRoles(String roles) {
        Roles = roles;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public boolean isEmailConfirmed() {
        return EmailConfirmed;
    }

    public void setEmailConfirmed(boolean emailConfirmed) {
        EmailConfirmed = emailConfirmed;
    }

    public String getPasswordHash() {
        return PasswordHash;
    }

    public void setPasswordHash(String passwordHash) {
        PasswordHash = passwordHash;
    }

    public String getSecurityStamp() {
        return SecurityStamp;
    }

    public void setSecurityStamp(String securityStamp) {
        SecurityStamp = securityStamp;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public boolean isPhoneNumberConfirmed() {
        return PhoneNumberConfirmed;
    }

    public void setPhoneNumberConfirmed(boolean phoneNumberConfirmed) {
        PhoneNumberConfirmed = phoneNumberConfirmed;
    }

    public boolean isTwoFactorEnabled() {
        return TwoFactorEnabled;
    }

    public void setTwoFactorEnabled(boolean twoFactorEnabled) {
        TwoFactorEnabled = twoFactorEnabled;
    }

    public String getLockoutEndDateUtc() {
        return LockoutEndDateUtc;
    }

    public void setLockoutEndDateUtc(String lockoutEndDateUtc) {
        LockoutEndDateUtc = lockoutEndDateUtc;
    }

    public boolean isLockoutEnabled() {
        return LockoutEnabled;
    }

    public void setLockoutEnabled(boolean lockoutEnabled) {
        LockoutEnabled = lockoutEnabled;
    }

    public int getAccessFailedCount() {
        return AccessFailedCount;
    }

    public void setAccessFailedCount(int accessFailedCount) {
        AccessFailedCount = accessFailedCount;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getImageData() {
        return ImageData;
    }

    public void setImageData(String imageData) {
        ImageData = imageData;
    }



}
