package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels;

/**
 * Created by Esraa Hosny on 3/11/2017.
 */

public class UsersDataModel {
    public String Id;
    public String Email;
    public Boolean EmailConfirmed;
    public String PasswordHash;
    public String SecurityStamp;
    public String PhoneNumber;
    public Boolean PhoneNumberConfirmed;
    public Boolean TwoFactorEnabled;
    //  public DateTime LockoutEndDateUtc ;
    public Boolean LockoutEnabled;
    public int AccessFailedCount;
    public String UserName;
    public String FirstName;
    public String LastName;
    public String ImageData;

    public UsersDataModel() {

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

    public Boolean getEmailConfirmed() {
        return EmailConfirmed;
    }

    public void setEmailConfirmed(Boolean emailConfirmed) {
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

    public Boolean getPhoneNumberConfirmed() {
        return PhoneNumberConfirmed;
    }

    public void setPhoneNumberConfirmed(Boolean phoneNumberConfirmed) {
        PhoneNumberConfirmed = phoneNumberConfirmed;
    }

    public Boolean getTwoFactorEnabled() {
        return TwoFactorEnabled;
    }

    public void setTwoFactorEnabled(Boolean twoFactorEnabled) {
        TwoFactorEnabled = twoFactorEnabled;
    }

    public Boolean getLockoutEnabled() {
        return LockoutEnabled;
    }

    public void setLockoutEnabled(Boolean lockoutEnabled) {
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
