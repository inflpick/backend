package com.leesh.inflpick.user.core.domain;

public record UserEmail(String email) {

    public UserEmail {
        validateInput(email);
    }

    public static UserEmail from(String email) {
        return new UserEmail(email);
    }

    private void validateInput(String email) throws InvalidEmailFormatException {
        if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
            throw new InvalidEmailFormatException("Email is invalid");
        }
    }
}
