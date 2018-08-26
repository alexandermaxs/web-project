package team.model.bean;

import team.exception.InvalidArgumentException;


public enum UserType {
    MANAGER("manager"), CUSTOMER("customer"), DEVELOPER("developer");

    private String typeValue;

    UserType(String type) {
        typeValue = type;
    }

    static public UserType getUserType(String string) throws InvalidArgumentException {
        for (UserType type : UserType.values()) {
            if (type.getTypeValue().equals(string.toLowerCase())) {
                return type;
            }
        }
        throw new InvalidArgumentException("Invalid argument " + string);
    }

    public String getTypeValue() {
        return typeValue;
    }

}