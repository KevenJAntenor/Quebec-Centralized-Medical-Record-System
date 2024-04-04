package com.santeConnect.domain.jwt;

/**
 * Record to store credentials for authentification
 * @param username
 * @param password
 */
public record AccountCredentials(String username, String password) {}
